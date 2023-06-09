from io import StringIO
from tkinter import Image

import pandas as pd
import pydotplus
from matplotlib import pyplot as plt
from scipy.io import arff
from sklearn.model_selection import train_test_split
from sklearn.tree import DecisionTreeClassifier, export_graphviz, plot_tree
from sklearn import metrics, __all__, datasets
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


#Decision Trees
clf = DecisionTreeClassifier()
clf = clf.fit(X_trainIris, y_trainIris)
y_predIris = clf.predict(X_testIris)
print("Accuracy Iris:", metrics.accuracy_score(y_testIris, y_predIris))

clf = DecisionTreeClassifier()
clf = clf.fit(X_trainCPU, y_trainCPU)
y_predCPU = clf.predict(X_testCPU)
print("Accuracy CPU:", metrics.accuracy_score(y_testCPU, y_predCPU))

clf = DecisionTreeClassifier()
clf = clf.fit(X_trainDiabetes, y_trainDiabetes)
y_predDiabetes = clf.predict(X_testDiabetes)
print("Accuracy Diabetes:", metrics.accuracy_score(y_testDiabetes, y_predDiabetes))

clf = DecisionTreeClassifier()
clf = clf.fit(X_trainIonosphere, y_trainIonosphere)
y_predIonosphere = clf.predict(X_testIonosphere)
print("Accuracy Ionosphere:", metrics.accuracy_score(y_testIonosphere, y_predIonosphere))

clf = DecisionTreeClassifier()
clf = clf.fit(X_trainSegment, y_trainSegment)
y_predSegment = clf.predict(X_testSegment)
print("Accuracy Segment:", metrics.accuracy_score(y_testSegment, y_predSegment))

clf = DecisionTreeClassifier()
clf = clf.fit(dfWine.data, dfWine.target)
y_predWine = clf.predict(dfWine.data)
print("Accuracy Wine:", metrics.accuracy_score(dfWine.target, y_predWine))

#KNN Classifier
knn = KNeighborsClassifier(n_neighbors=3)
knn.fit(X_trainIris, y_trainIris)
y_predIris = knn.predict(X_testIris)
print("Accuracy Iris:", metrics.accuracy_score(y_testIris, y_predIris))

knn = KNeighborsClassifier(n_neighbors=3)
knn.fit(X_trainCPU, y_trainCPU)
y_predCPU = knn.predict(X_testCPU)
print("Accuracy CPU:", metrics.accuracy_score(y_testCPU, y_predCPU))

knn = KNeighborsClassifier(n_neighbors=3)
knn.fit(X_trainDiabetes, y_trainDiabetes)
y_predDiabetes = knn.predict(X_testDiabetes)
print("Accuracy Diabetes:", metrics.accuracy_score(y_testDiabetes, y_predDiabetes))

knn = KNeighborsClassifier(n_neighbors=3)
knn.fit(X_trainIonosphere, y_trainIonosphere)
y_predIonosphere = knn.predict(X_testIonosphere)
print("Accuracy Ionosphere:", metrics.accuracy_score(y_testIonosphere, y_predIonosphere))

knn = KNeighborsClassifier(n_neighbors=3)
knn.fit(X_trainSegment, y_trainSegment)
y_predSegment = knn.predict(X_testSegment)
print("Accuracy Segment:", metrics.accuracy_score(y_testSegment, y_predSegment))

knn = KNeighborsClassifier(n_neighbors=3)
knn.fit(dfWine.data, dfWine.target)
y_predWine = knn.predict(dfWine.data)
print("Accuracy Wine:", metrics.accuracy_score(dfWine.target, y_predWine))

#Plot the decision tree for the Iris dataset using  plot_tree
plt.figure(figsize=(15,10))
plot_tree(clf, filled=True, rounded=True)
plt.show()

