import numpy as np
import pandas as pd
from matplotlib import pyplot as plt
from scipy.io import arff
from sklearn.model_selection import train_test_split, cross_val_score
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

datasets = ["Iris", "CPU", "Diabetes", "Ionosphere", "Segment", "Wine"]

arbol = DecisionTreeClassifier(); precisionArbol = []
svm = SVC(kernel='linear', C=1); precisionSVM = []

#Iris
arbol.fit(X_trainIris, y_trainIris)
precisionArbol.append(arbol.score(X_testIris, y_testIris))
svm.fit(X_trainIris, y_trainIris)
precisionSVM.append(svm.score(X_testIris, y_testIris))

#CPU
arbol.fit(X_trainCPU, y_trainCPU)
precisionArbol.append(arbol.score(X_testCPU, y_testCPU))
svm.fit(X_trainCPU, y_trainCPU)
precisionSVM.append(svm.score(X_testCPU, y_testCPU))

#Diabetes
arbol.fit(X_trainDiabetes, y_trainDiabetes)
precisionArbol.append(arbol.score(X_testDiabetes, y_testDiabetes))
svm.fit(X_trainDiabetes, y_trainDiabetes)
precisionSVM.append(svm.score(X_testDiabetes, y_testDiabetes))

#Ionosphere
arbol.fit(X_trainIonosphere, y_trainIonosphere)
precisionArbol.append(arbol.score(X_testIonosphere, y_testIonosphere))
svm.fit(X_trainIonosphere, y_trainIonosphere)
precisionSVM.append(svm.score(X_testIonosphere, y_testIonosphere))

#Segment
arbol.fit(X_trainSegment, y_trainSegment)
precisionArbol.append(arbol.score(X_testSegment, y_testSegment))
svm.fit(X_trainSegment, y_trainSegment)
precisionSVM.append(svm.score(X_testSegment, y_testSegment))

#Wine
arbol.fit(dfWine.data, dfWine.target)
precisionArbol.append(arbol.score(dfWine.data, dfWine.target))
svm.fit(dfWine.data, dfWine.target)
precisionSVM.append(svm.score(dfWine.data, dfWine.target))

#Print the results
print("Árbol de decisión: ", precisionArbol)
print("SVM: ", precisionSVM)

#Plot the results
plt.plot(datasets, precisionArbol, label='Árbol de decisión')
plt.plot(datasets, precisionSVM, label='SVM')
plt.xlabel('Dataset')
plt.ylabel('Precisión')
plt.title('Precisión SVM vs Arbol de decisión')
plt.legend()
plt.show()