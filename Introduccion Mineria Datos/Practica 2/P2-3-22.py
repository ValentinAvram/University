import numpy as np
import pandas as pd
from matplotlib import pyplot as plt
from scipy.io import arff
from sklearn.ensemble import BaggingClassifier
from sklearn.model_selection import train_test_split, cross_val_score, GridSearchCV
from sklearn.svm import SVC
from sklearn.tree import DecisionTreeClassifier
from sklearn import datasets
from sklearn.neighbors import KNeighborsClassifier

#Iris dataset
dfIris = arff.loadarff('iris.arff')
dfIris = pd.DataFrame(dfIris[0])
dfIris['class'] = pd.Categorical(dfIris['class'])
dfIris['class'] = dfIris['class'].cat.codes
XIris = dfIris.drop('class', axis=1)
yIris = dfIris['class']
X_trainIris, X_testIris, y_trainIris, y_testIris = train_test_split(XIris, yIris, test_size=0.3)

#CPU dataset
dfCPU = arff.loadarff('cpu.arff')
dfCPU = pd.DataFrame(dfCPU[0])
dfCPU['class'] = pd.Categorical(dfCPU['class'])
dfCPU['class'] = dfCPU['class'].cat.codes
XCPU = dfCPU.drop('class', axis=1)
yCPU = dfCPU['class']
X_trainCPU, X_testCPU, y_trainCPU, y_testCPU = train_test_split(XCPU, yCPU, test_size=0.3)

#Diabetes dataset
dfDiabetes = arff.loadarff('diabetes.arff')
dfDiabetes = pd.DataFrame(dfDiabetes[0])
dfDiabetes['class'] = pd.Categorical(dfDiabetes['class'])
dfDiabetes['class'] = dfDiabetes['class'].cat.codes
XDiabetes = dfDiabetes.drop('class', axis=1)
yDiabetes = dfDiabetes['class']
X_trainDiabetes, X_testDiabetes, y_trainDiabetes, y_testDiabetes = train_test_split(XDiabetes, yDiabetes, test_size=0.3)

#Ionosphere dataset
dfIonosphere = arff.loadarff('ionosphere.arff')
dfIonosphere = pd.DataFrame(dfIonosphere[0])
dfIonosphere['class'] = pd.Categorical(dfIonosphere['class'])
dfIonosphere['class'] = dfIonosphere['class'].cat.codes
XIonosphere = dfIonosphere.drop('class', axis=1)
yIonosphere = dfIonosphere['class']
X_trainIonosphere, X_testIonosphere, y_trainIonosphere, y_testIonosphere = train_test_split(XIonosphere, yIonosphere, test_size=0.3)

#Segment Challenge dataset
dfSegment = arff.loadarff('segment-challenge.arff')
dfSegment = pd.DataFrame(dfSegment[0])
dfSegment['class'] = pd.Categorical(dfSegment['class'])
dfSegment['class'] = dfSegment['class'].cat.codes
XSegment = dfSegment.drop('class', axis=1)
ySegment = dfSegment['class']
X_trainSegment, X_testSegment, y_trainSegment, y_testSegment = train_test_split(XSegment, ySegment, test_size=0.3)

#Wine dataset
dfWine = datasets.load_wine()

#Breast Cancer dataset
datasetBC = datasets.load_breast_cancer()

precisionArbol = []; precisionSVC = []

#Iris dataset
scores = cross_val_score(BaggingClassifier(DecisionTreeClassifier(), max_samples=0.5, max_features=0.5), XIris, yIris, cv=10, scoring='accuracy')
precisionArbol.append(scores.mean())
scores = cross_val_score(BaggingClassifier(SVC(kernel='linear', C=1), max_samples=0.5, max_features=0.5), XIris, yIris, cv=10, scoring='accuracy')
precisionSVC.append(scores.mean())

#Diabetes dataset
scores = cross_val_score(BaggingClassifier(DecisionTreeClassifier(), max_samples=0.5, max_features=0.5), XDiabetes, yDiabetes, cv=10, scoring='accuracy')
precisionArbol.append(scores.mean())
scores = cross_val_score(BaggingClassifier(SVC(kernel='linear', C=1), max_samples=0.5, max_features=0.5), XDiabetes, yDiabetes, cv=10, scoring='accuracy')
precisionSVC.append(scores.mean())

#Ionosphere dataset
scores = cross_val_score(BaggingClassifier(DecisionTreeClassifier(), max_samples=0.5, max_features=0.5), XIonosphere, yIonosphere, cv=10, scoring='accuracy')
precisionArbol.append(scores.mean())
scores = cross_val_score(BaggingClassifier(SVC(kernel='linear', C=1), max_samples=0.5, max_features=0.5), XIonosphere, yIonosphere, cv=10, scoring='accuracy')
precisionSVC.append(scores.mean())

#Segment Challenge dataset
scores = cross_val_score(BaggingClassifier(DecisionTreeClassifier(), max_samples=0.5, max_features=0.5), XSegment, ySegment, cv=10, scoring='accuracy')
precisionArbol.append(scores.mean())
scores = cross_val_score(BaggingClassifier(SVC(kernel='linear', C=1), max_samples=0.5, max_features=0.5), XSegment, ySegment, cv=10, scoring='accuracy')
precisionSVC.append(scores.mean())

#Wine dataset
scores = cross_val_score(BaggingClassifier(DecisionTreeClassifier(), max_samples=0.5, max_features=0.5), dfWine.data, dfWine.target, cv=10, scoring='accuracy')
precisionArbol.append(scores.mean())
scores = cross_val_score(BaggingClassifier(SVC(kernel='linear', C=1), max_samples=0.5, max_features=0.5), dfWine.data, dfWine.target, cv=10, scoring='accuracy')
precisionSVC.append(scores.mean())

#Breast Cancer dataset
scores = cross_val_score(BaggingClassifier(DecisionTreeClassifier(), max_samples=0.5, max_features=0.5), datasetBC.data, datasetBC.target, cv=10, scoring='accuracy')
precisionArbol.append(scores.mean())
scores = cross_val_score(BaggingClassifier(SVC(kernel='linear', C=1), max_samples=0.5, max_features=0.5), datasetBC.data, datasetBC.target, cv=10, scoring='accuracy')
precisionSVC.append(scores.mean())

plt.plot(precisionArbol, label='Árbol de decisión')
plt.plot(precisionSVC, label='SVM')
plt.legend()
plt.show()

print("Datasets: Iris, Diabetes, Ionosphere, Segment, Wine, Breast Cancer")
print("Precision Arbol: ", precisionArbol)
print("Precision SVM: ", precisionSVC)
