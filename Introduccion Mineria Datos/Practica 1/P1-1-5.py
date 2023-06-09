import pandas as pd
from scipy.io import arff
from sklearn.decomposition import PCA
from sklearn.model_selection import train_test_split
from sklearn.tree import DecisionTreeClassifier
from sklearn import metrics
from sklearn.neighbors import KNeighborsClassifier


#Iris dataset
dfIris = arff.loadarff('iris.arff')
dfIris = pd.DataFrame(dfIris[0])
dfIris['class'] = pd.Categorical(dfIris['class'])
dfIris['class'] = dfIris['class'].cat.codes
XIris = dfIris.drop('class', axis=1)
yIris = dfIris['class']
X_trainIris, X_testIris, y_trainIris, y_testIris = train_test_split(XIris, yIris, test_size=0.1, random_state=0, stratify=yIris)

#Diabetes dataset
dfDiabetes = arff.loadarff('diabetes.arff')
dfDiabetes = pd.DataFrame(dfDiabetes[0])
dfDiabetes['class'] = pd.Categorical(dfDiabetes['class'])
dfDiabetes['class'] = dfDiabetes['class'].cat.codes
XDiabetes = dfDiabetes.drop('class', axis=1)
yDiabetes = dfDiabetes['class']
X_trainDiabetes, X_testDiabetes, y_trainDiabetes, y_testDiabetes = train_test_split(XDiabetes, yDiabetes, test_size=0.1, random_state=0, stratify=yDiabetes)

#Ionosphere dataset
dfIonosphere = arff.loadarff('ionosphere.arff')
dfIonosphere = pd.DataFrame(dfIonosphere[0])
dfIonosphere['class'] = pd.Categorical(dfIonosphere['class'])
dfIonosphere['class'] = dfIonosphere['class'].cat.codes
XIonosphere = dfIonosphere.drop('class', axis=1)
yIonosphere = dfIonosphere['class']
X_trainIonosphere, X_testIonosphere, y_trainIonosphere, y_testIonosphere = train_test_split(XIonosphere, yIonosphere, test_size=0.1, random_state=0, stratify=yIonosphere)

#Segment Challenge dataset
dfSegment = arff.loadarff('segment-challenge.arff')
dfSegment = pd.DataFrame(dfSegment[0])
dfSegment['class'] = pd.Categorical(dfSegment['class'])
dfSegment['class'] = dfSegment['class'].cat.codes
XSegment = dfSegment.drop('class', axis=1)
ySegment = dfSegment['class']
X_trainSegment, X_testSegment, y_trainSegment, y_testSegment = train_test_split(XSegment, ySegment, test_size=0.1, random_state=0, stratify=ySegment)

#Muestreo Aleatorio
arbolPrecision = []
knnPrecision = []

def muestreoAleatorio(features,label,modelo):
  X_train, X_test, y_train, y_test = train_test_split(features, label, test_size=0.1, random_state=0)
  modelo.fit(X_train,y_train)
  y_pred = modelo.predict(X_test)
  precision = metrics.accuracy_score(y_test,y_pred)
  return precision;

arbolPrecision.append(muestreoAleatorio(XIris,yIris,DecisionTreeClassifier()))
arbolPrecision.append(muestreoAleatorio(XDiabetes,yDiabetes,DecisionTreeClassifier()))
arbolPrecision.append(muestreoAleatorio(XIonosphere,yIonosphere,DecisionTreeClassifier()))
arbolPrecision.append(muestreoAleatorio(XSegment,ySegment,DecisionTreeClassifier()))
knnPrecision.append(muestreoAleatorio(XIris,yIris,KNeighborsClassifier()))
knnPrecision.append(muestreoAleatorio(XDiabetes,yDiabetes,KNeighborsClassifier()))
knnPrecision.append(muestreoAleatorio(XIonosphere,yIonosphere,KNeighborsClassifier()))
knnPrecision.append(muestreoAleatorio(XSegment,ySegment,KNeighborsClassifier()))

print("Muestreo Aleatorio")
print("Arbol de decision: ",arbolPrecision)
print("KNN: ",knnPrecision)

#Muestreo Estratificado

def muestreoEstratificado(features,label,modelo):
  X_train, X_test, y_train, y_test = train_test_split(features, label, test_size=0.1, random_state=0,stratify=label)
  modelo.fit(X_train,y_train)
  y_pred = modelo.predict(X_test)
  precision = metrics.accuracy_score(y_test,y_pred)
  return precision;

arbolPrecision = []
knnPrecision = []

arbolPrecision.append(muestreoEstratificado(XIris,yIris,DecisionTreeClassifier()))
arbolPrecision.append(muestreoEstratificado(XDiabetes,yDiabetes,DecisionTreeClassifier()))
arbolPrecision.append(muestreoEstratificado(XIonosphere,yIonosphere,DecisionTreeClassifier()))
arbolPrecision.append(muestreoEstratificado(XSegment,ySegment,DecisionTreeClassifier()))
knnPrecision.append(muestreoEstratificado(XIris,yIris,KNeighborsClassifier()))
knnPrecision.append(muestreoEstratificado(XDiabetes,yDiabetes,KNeighborsClassifier()))
knnPrecision.append(muestreoEstratificado(XIonosphere,yIonosphere,KNeighborsClassifier()))
knnPrecision.append(muestreoEstratificado(XSegment,ySegment,KNeighborsClassifier()))

print("Muestreo Estratificado")
print("Arbol de decision: ",arbolPrecision)
print("KNN: ",knnPrecision)

print("Datasets:")
print("Iris | Diabetes | Ionosphere | Segment")
# EL DATASET CPU NO SE USA, NO SE PUEDE ESTRATIFICAR PORQUE TIENE UNA COLUMNA DE TIPO STRING