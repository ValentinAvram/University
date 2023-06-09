import pandas as pd
import os
import numpy as np
import tensorflow as tf
from matplotlib import pyplot as plt
from sklearn.model_selection import train_test_split
from tensorflow.keras.preprocessing.image import ImageDataGenerator,load_img
from tensorflow.keras import layers
from tensorflow.keras.layers import Dropout
from keras.preprocessing import image
from sklearn.metrics import classification_report, confusion_matrix


numeroClases = 2
sample_size_per_class = 5000 # número de imágenes por clase que se incluirán en la muestra. Max 12.000

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

## Escoger una muestra de imágenes
# Seleccionar aleatoriamente 500 imágenes de cada clase
df = df.groupby('category').apply(lambda x: x.sample(sample_size_per_class)).reset_index(drop=True)

xTrain, xVal = train_test_split(df, test_size=0.20, random_state=42)
yTest = xVal['category']


# Shuffle the dataframes
xTrain.reset_index(drop=True)
xVal.reset_index(drop=True)


batchSize = 150
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
# Testing
test_filenames = os.listdir('DogsVsCats/test1')
test_df = pd.DataFrame({
    'filename': test_filenames
})

testImgGenerator = ImageDataGenerator(rescale=1./255)
testData = testImgGenerator.flow_from_dataframe(test_df,
                                                'DogsVsCats/test1',
                                                x_col='filename',
                                                y_col=None,
                                                target_size=(height, width),
                                                class_mode=None,
                                                batch_size=batchSize,
                                                shuffle=False
                                                )

##Modelo
modeloUnderfitted = tf.keras.Sequential([
    layers.Conv2D(16, (3, 3), activation='relu', input_shape=(height, width, 3)),
    layers.MaxPooling2D((2, 2)),
    layers.MaxPooling2D((2, 2)),
    layers.Flatten(),
    layers.Dense(32, activation='relu', name ="capa1"), Dropout(0.2),
    layers.Dense(numeroClases, activation='softmax', name ="capa2")
], name = "modeloUnderfitted")


modeloUnderfitted.compile(loss='categorical_crossentropy', optimizer='adam', metrics=['accuracy'])
history = modeloUnderfitted.fit(trainData, epochs=2, validation_data=validationData)


print("Accuracy en el entrenamiento : ", history.history['accuracy'][-1])
print("Loss en la entrenamiento : ", history.history['loss'][-1])
print("Accuracy en la validacion : ", history.history['val_accuracy'][-1])
print("Loss en la validacion : ", history.history['val_loss'][-1])

plt.plot(history.history['accuracy'])
plt.plot(history.history['val_accuracy'])
plt.title('Model accuracy')
plt.ylabel('Accuracy')
plt.xlabel('Epoch')
plt.legend(['Train', 'Validation'], loc='upper left')
plt.show()

plt.plot(history.history['loss'])
plt.plot(history.history['val_loss'])
plt.title('Model loss')
plt.ylabel('Loss')
plt.xlabel('Epoch')
plt.legend(['Train', 'Validation'], loc='upper left')
plt.show()

predict = modelo1.evaluate(testData, verbose=1)
print("Accuracy en el test : ", predict[1])
print("Loss en el test : ", predict[0])



## Modelo2 Falla

model2 = tf.keras.models.Sequential()
model2.add(tf.keras.layers.Conv2D(16, kernel_size=(3,3), activation='relu',input_shape=(height,width,3), padding='same'))
# model.add(LeakyReLU(alpha=0.1))
model2.add(tf.keras.layers.MaxPooling2D(pool_size=(2,2), padding='same'))
model2.add(tf.keras.layers.Conv2D(32, kernel_size=(3,3), activation='relu', padding='same'))
# model.add(tf.keras.activations.relu(alpha=0.1))
model2.add(tf.keras.layers.MaxPooling2D(pool_size=(2,2), padding='same'))
model2.add(tf.keras.layers.Conv2D(64, kernel_size=(3,3), activation='relu', padding='same'))
# model.add(tf.keras.activations.relu(alpha=0.1))
model2.add(tf.keras.layers.MaxPooling2D(pool_size=(2,2), padding='same'))
model2.add(tf.keras.layers.Conv2D(64, kernel_size=(3,3), activation='relu', padding='same'))
# model.add(tf.keras.activations.relu(alpha=0.1))
model2.add(tf.keras.layers.MaxPooling2D(pool_size=(2,2), padding='same'))
model2.add(tf.keras.layers.Flatten())
model2.add(tf.keras.layers.Dropout(0.5))
model2.add(tf.keras.layers.Dense(128, activation=tf.nn.relu))
# model.add(tf.keras.activations.relu(alpha=0.1))
model2.add(Dropout(0.5))
model2.add(tf.keras.layers.Dense(256, activation=tf.nn.relu))
model2.add(tf.keras.layers.Dense(1, activation='sigmoid'))

model2.compile(loss='binary_crossentropy', optimizer='adam', metrics=['accuracy'])
history2 = model2.fit(trainData, epochs=2, validation_data=validationData)

## Imprimir Accuracy, Loss, hacer predicciones, etc

#Modelo 3
model3 = tf.keras.models.Sequential()
model3.add(tf.keras.layers.Conv2D(32, kernel_size=(3, 3),activation='linear',padding='same',input_shape=(height,width,3)))
model3.add(tf.keras.layers.LeakyReLU(alpha=0.1))
model3.add(tf.keras.layers.MaxPooling2D((2, 2),padding='same'))
model3.add(Dropout(0.5))

model3.add(tf.keras.layers.Flatten())
model3.add(tf.keras.layers.Dense(32, activation='linear'))
model3.add(tf.keras.layers.LeakyReLU(alpha=0.1))
model3.add(Dropout(0.5))
model3.add(tf.keras.layers.Dense(numeroClases, activation='softmax'))

model3.compile(loss=tf.keras.losses.categorical_crossentropy, optimizer=tf.keras.optimizers.Adam(),metrics=['accuracy'])
history3 = model3.fit(trainData, epochs=2, validation_data=validationData)

## Imprimir Accuracy, Loss, hacer predicciones, etc

#Modelo 4

from keras.models import Sequential
from keras.layers import Conv2D,MaxPooling2D,\
     Dropout,Flatten,Dense,Activation,\
     BatchNormalization
model=Sequential()
model.add(Conv2D(32,(3,3),activation='relu',input_shape=(height,width,3)))
model.add(BatchNormalization())
model.add(MaxPooling2D(pool_size=(2,2)))
model.add(Dropout(0.25))
model.add(Conv2D(64,(3,3),activation='relu'))
model.add(BatchNormalization())
model.add(MaxPooling2D(pool_size=(2,2)))
model.add(Dropout(0.25))
model.add(Conv2D(128,(3,3),activation='relu'))
model.add(BatchNormalization())
model.add(MaxPooling2D(pool_size=(2,2)))
model.add(Dropout(0.25))
model.add(Flatten())
model.add(Dense(512,activation='relu'))
model.add(BatchNormalization())
model.add(Dropout(0.5))
model.add(Dense(2,activation='softmax'))
model.compile(loss='categorical_crossentropy',optimizer='adam',metrics=['accuracy'])
history4 = model.fit(trainData, epochs=2, validation_data=validationData)

## Imprimir Accuracy, Loss, hacer predicciones, etc

#Modelo 5  ## MODELO TOCHO PRECREADO
modelVGG16 = tf.keras.applications.VGG16(
    include_top=True,
    weights="imagenet",
    input_shape=(height,width,3),
    classes=2,
    classifier_activation="softmax",
)
modelVGG16.compile(loss='binary_crossentropy', optimizer='adam', metrics=['accuracy'])
history5 = modelVGG16.fit(trainData, epochs=2, validation_data=validationData)

## Imprimir Accuracy, Loss, hacer predicciones, etc

#Modelo 6  ## MODELO TOCHO PRECREADO
modelResNet50 = tf.keras.applications.ResNet50(
    include_top=True,
    weights="imagenet",
    input_shape=(height,width,3),
    classes=2,
    classifier_activation="softmax",
)
modelResNet50.compile(loss='binary_crossentropy', optimizer='adam', metrics=['accuracy'])
history6 = modelResNet50.fit(trainData, epochs=1, validation_data=validationData)

## Imprimir Accuracy, Loss, hacer predicciones, etc