import numpy as np
import pandas as pd
from matplotlib import pyplot as plt
from scipy.io import arff
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

results = []

svc = SVC(kernel="linear")
#Iris dataset  SVC
svc.fit(X_trainIris, y_trainIris)
print("Iris dataset SVC")
print("Accuracy: ", svc.score(X_testIris, y_testIris))
results.append(svc.score(X_testIris, y_testIris))

#Diabetes dataset SVC
svc.fit(X_trainDiabetes, y_trainDiabetes)
print("Diabetes dataset SVC")
print("Accuracy: ", svc.score(X_testDiabetes, y_testDiabetes))
results.append(svc.score(X_testDiabetes, y_testDiabetes))

#Ionosphere dataset SVC
svc.fit(X_trainIonosphere, y_trainIonosphere)
print("Ionosphere dataset SVC")
print("Accuracy: ", svc.score(X_testIonosphere, y_testIonosphere))
results.append(svc.score(X_testIonosphere, y_testIonosphere))

#Segment Challenge dataset SVC
svc.fit(X_trainSegment, y_trainSegment)
print("Segment Challenge dataset SVC")
print("Accuracy: ", svc.score(X_testSegment, y_testSegment))
results.append(svc.score(X_testSegment, y_testSegment))

#Wine dataset SVC
svc.fit(dfWine.data, dfWine.target)
print("Wine dataset SVC")
print("Accuracy: ", svc.score(dfWine.data, dfWine.target))
results.append(svc.score(dfWine.data, dfWine.target))

#Breast Cancer dataset SVC
svc.fit(datasetBC.data, datasetBC.target)
print("Breast Cancer dataset SVC")
print("Accuracy: ", svc.score(datasetBC.data, datasetBC.target))
results.append(svc.score(datasetBC.data, datasetBC.target))

#Plot the results
plt.bar(['Iris', 'Diabetes', 'Ionosphere', 'Segment', 'Wine', 'Breast Cancer'], results)
plt.title('SVC')
plt.show()

