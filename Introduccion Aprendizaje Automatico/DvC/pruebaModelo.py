import numpy as np
import pandas as pd
import os
import tensorflow as tf
from keras import Sequential
from keras.layers import Dense, BatchNormalization, Flatten, MaxPooling2D, Conv2D
from matplotlib import pyplot as plt
from sklearn.metrics import accuracy_score
from sklearn.model_selection import train_test_split
from tensorflow.keras.preprocessing.image import ImageDataGenerator
from tensorflow.keras import layers
from tensorflow.keras.layers import Dropout
import time


categories = []
filenames = os.listdir('DogsVsCats/train')

for filename in filenames:
    category = filename.split('.')[0]
    if (category == 'dog'):
        categories.append('dog')
    else:
        categories.append('cat')

# create a dataframe
df = pd.DataFrame({
    'filename': filenames,
    'category': categories
})

## Dividir en train, validation y test
xTrain, xTest = train_test_split(df, test_size=0.2, random_state=42)
xTrain, xVal = train_test_split(xTrain, test_size=0.2, random_state=42)

yReal = xTest['category']

# Shuffle the dataframes
xTrain.reset_index(drop=True)
xVal.reset_index(drop=True)


batchSize = 64
height = 128
width = 128

imageGenerator = ImageDataGenerator( rescale=1./255,
                                        rotation_range=15,
                                        shear_range=0.1,
                                        zoom_range=0.5,
                                        horizontal_flip=True,
                                        width_shift_range=0.2,
                                        height_shift_range=0.2,
                                    )
validationImgGenerator = ImageDataGenerator(rescale=1./255,
                                            rotation_range=15,
                                            shear_range=0.2,
                                            zoom_range=0.5,
                                            horizontal_flip=True,
                                            width_shift_range=0.2,
                                            height_shift_range=0.2,
                                            )


trainData = imageGenerator.flow_from_dataframe(xTrain,
                                                'DogsVsCats/train',
                                                x_col='filename',
                                                y_col='category',
                                                target_size=(height, width),
                                                class_mode='categorical',
                                                batch_size=batchSize,
                                                shuffle=True
                                                )


validationData = validationImgGenerator.flow_from_dataframe(xVal,
                                                            'DogsVsCats/train',
                                                            x_col='filename',
                                                            y_col='category',
                                                            target_size=(height, width),
                                                            class_mode='categorical',
                                                            batch_size=batchSize,
                                                            shuffle=True
                                                            )

testImgGenerator = ImageDataGenerator(rescale=1./255)
testData = testImgGenerator.flow_from_dataframe(xTest,
                                                'DogsVsCats/train',
                                                x_col='filename',
                                                y_col=None,
                                                target_size=(height, width),
                                                class_mode=None,
                                                batch_size=batchSize,
                                                shuffle=False
                                                )

numeroClases = 2

# Cargar modelo desde Github
modelo = tf.keras.models.load_model('modelResnet50.h5')

# Obtener las etiquetas reales de los datos de prueba
yReal = []
for filename in xTest['filename']:
    if filename.startswith('dog'):
        yReal.append(1)
    else:
        yReal.append(0)
yReal = np.array(yReal)

# Hacer predicciones sobre los datos de prueba
yPred = modelo.predict(testData)
yPred = np.argmax(yPred, axis=1)

# Calcular la precisión de las predicciones
accuracy = accuracy_score(yReal, yPred)
print("Accuracy:", accuracy)

data = {'ModelName': ['modelResnet50'], 'Accuracy': [accuracy]}
df = pd.DataFrame(data)
#Add header if file is empty
#Create file if it doesn't exist
if not os.path.exists('traniningData.csv'):
    open('traniningData.csv', 'w').close()
#Add data to file
if os.stat('traniningData.csv').st_size == 0:
    df.to_csv('traniningData.csv', header=True)
else:
    df.to_csv('traniningData.csv', mode='a', header=False)