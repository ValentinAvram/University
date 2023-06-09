import pandas as pd
from scipy.io import arff
from sklearn.model_selection import train_test_split
from sklearn.tree import DecisionTreeClassifier
from sklearn import metrics, preprocessing, datasets
from sklearn.neighbors import KNeighborsClassifier


#Iris dataset
dfIris = arff.loadarff('iris.arff')
dfIris = pd.DataFrame(dfIris[0])
dfIris['class'] = pd.Categorical(dfIris['class'])
dfIris['class'] = dfIris['class'].cat.codes
XIris = dfIris.drop('class', axis=1)
yIris = dfIris['class']
dfIris = preprocessing.normalize(dfIris)
X_trainIris, X_testIris, y_trainIris, y_testIris = train_test_split(XIris, yIris, test_size=0.3)

#CPU dataset
dfCPU = arff.loadarff('cpu.arff')
dfCPU = pd.DataFrame(dfCPU[0])
dfCPU['class'] = pd.Categorical(dfCPU['class'])
dfCPU['class'] = dfCPU['class'].cat.codes
XCPU = dfCPU.drop('class', axis=1)
yCPU = dfCPU['class']
dfCPU = preprocessing.normalize(dfCPU)
X_trainCPU, X_testCPU, y_trainCPU, y_testCPU = train_test_split(XCPU, yCPU, test_size=0.3)

#Diabetes dataset
dfDiabetes = arff.loadarff('diabetes.arff')
dfDiabetes = pd.DataFrame(dfDiabetes[0])
dfDiabetes['class'] = pd.Categorical(dfDiabetes['class'])
dfDiabetes['class'] = dfDiabetes['class'].cat.codes
XDiabetes = dfDiabetes.drop('class', axis=1)
yDiabetes = dfDiabetes['class']
dfDiabetes = preprocessing.normalize(dfDiabetes)
X_trainDiabetes, X_testDiabetes, y_trainDiabetes, y_testDiabetes = train_test_split(XDiabetes, yDiabetes, test_size=0.3)

#Ionosphere dataset
dfIonosphere = arff.loadarff('ionosphere.arff')
dfIonosphere = pd.DataFrame(dfIonosphere[0])
dfIonosphere['class'] = pd.Categorical(dfIonosphere['class'])
dfIonosphere['class'] = dfIonosphere['class'].cat.codes
XIonosphere = dfIonosphere.drop('class', axis=1)
yIonosphere = dfIonosphere['class']
dfIonosphere = preprocessing.normalize(dfIonosphere)
X_trainIonosphere, X_testIonosphere, y_trainIonosphere, y_testIonosphere = train_test_split(XIonosphere, yIonosphere, test_size=0.3)

#Segment Challenge dataset
dfSegment = arff.loadarff('segment-challenge.arff')
dfSegment = pd.DataFrame(dfSegment[0])
dfSegment['class'] = pd.Categorical(dfSegment['class'])
dfSegment['class'] = dfSegment['class'].cat.codes
XSegment = dfSegment.drop('class', axis=1)
ySegment = dfSegment['class']
dfSegment = preprocessing.normalize(dfSegment)
X_trainSegment, X_testSegment, y_trainSegment, y_testSegment = train_test_split(XSegment, ySegment, test_size=0.3)

#Wine dataset
dfWine = datasets.load_wine()
#Estandarizar los datos

#Arbol de decision
clf = DecisionTreeClassifier()

clf = clf.fit(X_trainIris, y_trainIris)
y_pred = clf.predict(X_testIris)
print("Error de clasificacion de Iris: ", 1-metrics.accuracy_score(y_testIris, y_pred))

clf = clf.fit(X_trainCPU, y_trainCPU)
y_pred = clf.predict(X_testCPU)
print("Error de CPU: ", 1 - metrics.accuracy_score(y_testCPU, y_pred))

clf = clf.fit(X_trainDiabetes, y_trainDiabetes)
y_pred = clf.predict(X_testDiabetes)
print("Error de Diabetes: ", 1 - metrics.accuracy_score(y_testDiabetes, y_pred))

clf = clf.fit(X_trainIonosphere, y_trainIonosphere)
y_pred = clf.predict(X_testIonosphere)
print("Error de Ionosphere: ", 1 - metrics.accuracy_score(y_testIonosphere, y_pred))

clf = clf.fit(X_trainSegment, y_trainSegment)
y_pred = clf.predict(X_testSegment)
print("Error de Segment: ", 1 - metrics.accuracy_score(y_testSegment, y_pred))

clf = clf.fit(dfWine.data, dfWine.target)
y_pred = clf.predict(dfWine.data)
print("Error de Wine: ", 1 - metrics.accuracy_score(dfWine.target, y_pred))

#KNN
knn = KNeighborsClassifier(n_neighbors=3)

knn = knn.fit(X_trainIris, y_trainIris)
y_pred = knn.predict(X_testIris)
print("Error de Iris: ", 1 - metrics.accuracy_score(y_testIris, y_pred))

knn = knn.fit(X_trainCPU, y_trainCPU)
y_pred = knn.predict(X_testCPU)
print("Error de CPU: ", 1 - metrics.accuracy_score(y_testCPU, y_pred))

knn = knn.fit(X_trainDiabetes, y_trainDiabetes)
y_pred = knn.predict(X_testDiabetes)
print("Error de Diabetes: ", 1 - metrics.accuracy_score(y_testDiabetes, y_pred))

knn = knn.fit(X_trainIonosphere, y_trainIonosphere)
y_pred = knn.predict(X_testIonosphere)
print("Error de Ionosphere: ", 1 - metrics.accuracy_score(y_testIonosphere, y_pred))

knn = knn.fit(X_trainSegment, y_trainSegment)
y_pred = knn.predict(X_testSegment)
print("Error de Segment: ", 1 - metrics.accuracy_score(y_testSegment, y_pred))

knn = knn.fit(dfWine.data, dfWine.target)
y_pred = knn.predict(dfWine.data)