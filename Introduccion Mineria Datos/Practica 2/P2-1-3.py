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

dataset_names = ["Iris", "Diabetes", "Ionosphere", "Segment", "Wine"]
precisionKnn= []; errorKnn = []; precisionTree = []; errorTree = []; precisionSVM = []; errorSVM = []

#Iris
#Validacion cruzada con 10 folds y arbol de decision
scores = cross_val_score(DecisionTreeClassifier(), XIris, yIris, cv=10, scoring='accuracy')
precisionTree.append(scores.mean())
errorTree.append(1-scores.mean())
#Validacion cruzada con 10 folds y KNN
scores = cross_val_score(KNeighborsClassifier(n_neighbors=5), XIris, yIris, cv=10, scoring='accuracy')
precisionKnn.append(scores.mean())
errorKnn.append(1-scores.mean())
#Validacion cruzada con 10 folds y SVM
scores = cross_val_score(SVC(kernel='linear', C=1), XIris, yIris, cv=10, scoring='accuracy')
precisionSVM.append(scores.mean())
errorSVM.append(1-scores.mean())

#Diabetes
#Validacion cruzada con 10 folds y arbol de decision
scores = cross_val_score(DecisionTreeClassifier(), XDiabetes, yDiabetes, cv=10, scoring='accuracy')
precisionTree.append(scores.mean())
errorTree.append(1-scores.mean())
#Validacion cruzada con 10 folds y KNN
scores = cross_val_score(KNeighborsClassifier(n_neighbors=5), XDiabetes, yDiabetes, cv=10, scoring='accuracy')
precisionKnn.append(scores.mean())
errorKnn.append(1-scores.mean())
#Validacion cruzada con 10 folds y SVM
scores = cross_val_score(SVC(kernel='linear', C=1), XDiabetes, yDiabetes, cv=10, scoring='accuracy')
precisionSVM.append(scores.mean())
errorSVM.append(1-scores.mean())

#Ionosphere
#Validacion cruzada con 10 folds y arbol de decision
scores = cross_val_score(DecisionTreeClassifier(), XIonosphere, yIonosphere, cv=10, scoring='accuracy')
precisionTree.append(scores.mean())
errorTree.append(1-scores.mean())
#Validacion cruzada con 10 folds y KNN
scores = cross_val_score(KNeighborsClassifier(n_neighbors=5), XIonosphere, yIonosphere, cv=10, scoring='accuracy')
precisionKnn.append(scores.mean())
errorKnn.append(1-scores.mean())
#Validacion cruzada con 10 folds y SVM
scores = cross_val_score(SVC(kernel='linear', C=1), XIonosphere, yIonosphere, cv=10, scoring='accuracy')
precisionSVM.append(scores.mean())
errorSVM.append(1-scores.mean())

#Segment
#Validacion cruzada con 10 folds y arbol de decision
scores = cross_val_score(DecisionTreeClassifier(), XSegment, ySegment, cv=10, scoring='accuracy')
precisionTree.append(scores.mean())
errorTree.append(1-scores.mean())
#Validacion cruzada con 10 folds y KNN
scores = cross_val_score(KNeighborsClassifier(n_neighbors=5), XSegment, ySegment, cv=10, scoring='accuracy')
precisionKnn.append(scores.mean())
errorKnn.append(1-scores.mean())
#Validacion cruzada con 10 folds y SVM
scores = cross_val_score(SVC(kernel='linear', C=1), XSegment, ySegment, cv=10, scoring='accuracy')
precisionSVM.append(scores.mean())
errorSVM.append(1-scores.mean())

#Wine
#Validacion cruzada con 10 folds y arbol de decision
scores = cross_val_score(DecisionTreeClassifier(), dfWine.data, dfWine.target, cv=10, scoring='accuracy')
precisionTree.append(scores.mean())
errorTree.append(1-scores.mean())
#Validacion cruzada con 10 folds y KNN
scores = cross_val_score(KNeighborsClassifier(n_neighbors=5), dfWine.data, dfWine.target, cv=10, scoring='accuracy')
precisionKnn.append(scores.mean())
errorKnn.append(1-scores.mean())
#Validacion cruzada con 10 folds y SVM
scores = cross_val_score(SVC(kernel='linear', C=1), dfWine.data, dfWine.target, cv=10, scoring='accuracy')
precisionSVM.append(scores.mean())
errorSVM.append(1-scores.mean())


#Print results
print("Datasets: ", dataset_names)
print("Precision de los algoritmos")
print("Arbol de decision: ", precisionTree)
print("KNN: ", precisionKnn)
print("SVM: ", precisionSVM)

print("Error de los algoritmos")
print("Arbol de decision: ", errorTree)
print("KNN: ", errorKnn)
print("SVM: ", errorSVM)


#Plot results
plt.figure(figsize=(10, 5))
plt.plot(dataset_names, precisionTree, label='Arbol de decision')
plt.plot(dataset_names, precisionKnn, label='KNN')
plt.plot(dataset_names, precisionSVM, label='SVM')
plt.title('Precision de los algoritmos')
plt.xlabel('Datasets')
plt.ylabel('Precision')
plt.legend()
plt.show()

