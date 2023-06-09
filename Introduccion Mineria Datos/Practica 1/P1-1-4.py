import pandas as pd
from matplotlib import pyplot as plt
from scipy.io import arff
from sklearn.decomposition import PCA
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
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

datasets = ["Iris", "CPU", "Diabetes", "Ionosphere", "Segment"]
arbolPrecision = []
knnPrecision = []

#Iris
X_train, X_test, y_train, y_test = train_test_split(XIris, yIris, test_size=0.3, random_state=1)
scaler = StandardScaler()
scaler.fit(X_train)

X_train = scaler.transform(X_train)
X_test = scaler.transform(X_test)

pca = PCA(n_components=2)
pca.fit(X_train)
X_train = pca.fit_transform(X_train)
X_test = pca.transform(X_test)


arbol = DecisionTreeClassifier()
arbol.fit(X_train, y_train)
y_pred = arbol.predict(X_test)
arbolPrecision.append(metrics.accuracy_score(y_test, y_pred))

knn = KNeighborsClassifier(n_neighbors=5)
knn.fit(X_train, y_train)
y_pred = knn.predict(X_test)
knnPrecision.append(metrics.accuracy_score(y_test, y_pred))


#CPU
X_train, X_test, y_train, y_test = train_test_split(XCPU, yCPU, test_size=0.3, random_state=1)
scaler = StandardScaler()
scaler.fit(X_train)

X_train = scaler.transform(X_train)
X_test = scaler.transform(X_test)

pca = PCA(n_components=2)
pca.fit(X_train)
X_train = pca.fit_transform(X_train)
X_test = pca.transform(X_test)


arbol = DecisionTreeClassifier()
arbol.fit(X_train, y_train)
y_pred = arbol.predict(X_test)
arbolPrecision.append(metrics.accuracy_score(y_test, y_pred))

knn = KNeighborsClassifier(n_neighbors=5)
knn.fit(X_train, y_train)
y_pred = knn.predict(X_test)
knnPrecision.append(metrics.accuracy_score(y_test, y_pred))

#Diabetes
X_train, X_test, y_train, y_test = train_test_split(XDiabetes, yDiabetes, test_size=0.3, random_state=1)
scaler = StandardScaler()
scaler.fit(X_train)

X_train = scaler.transform(X_train)
X_test = scaler.transform(X_test)

pca = PCA(n_components=2)
pca.fit(X_train)
X_train = pca.fit_transform(X_train)
X_test = pca.transform(X_test)


arbol = DecisionTreeClassifier()
arbol.fit(X_train, y_train)
y_pred = arbol.predict(X_test)
arbolPrecision.append(metrics.accuracy_score(y_test, y_pred))

knn = KNeighborsClassifier(n_neighbors=5)
knn.fit(X_train, y_train)
y_pred = knn.predict(X_test)
knnPrecision.append(metrics.accuracy_score(y_test, y_pred))

#Ionosphere
X_train, X_test, y_train, y_test = train_test_split(XIonosphere, yIonosphere, test_size=0.3, random_state=1)
scaler = StandardScaler()
scaler.fit(X_train)

X_train = scaler.transform(X_train)
X_test = scaler.transform(X_test)

pca = PCA(n_components=2)
pca.fit(X_train)
X_train = pca.fit_transform(X_train)
X_test = pca.transform(X_test)

arbol = DecisionTreeClassifier()
arbol.fit(X_train, y_train)
y_pred = arbol.predict(X_test)
arbolPrecision.append(metrics.accuracy_score(y_test, y_pred))

knn = KNeighborsClassifier(n_neighbors=5)
knn.fit(X_train, y_train)
y_pred = knn.predict(X_test)
knnPrecision.append(metrics.accuracy_score(y_test, y_pred))

#Segment
X_train, X_test, y_train, y_test = train_test_split(XSegment, ySegment, test_size=0.3, random_state=1)
scaler = StandardScaler()
scaler.fit(X_train)

X_train = scaler.transform(X_train)
X_test = scaler.transform(X_test)

pca = PCA(n_components=2)
pca.fit(X_train)
X_train = pca.fit_transform(X_train)
X_test = pca.transform(X_test)

arbol = DecisionTreeClassifier()
arbol.fit(X_train, y_train)
y_pred = arbol.predict(X_test)
arbolPrecision.append(metrics.accuracy_score(y_test, y_pred))

knn = KNeighborsClassifier(n_neighbors=5)
knn.fit(X_train, y_train)
y_pred = knn.predict(X_test)
knnPrecision.append(metrics.accuracy_score(y_test, y_pred))

plt.plot(datasets, arbolPrecision, label='Árbol de decisión')
plt.plot(datasets, knnPrecision, label='KNN')
plt.legend()
plt.show()

#print results
print("Árbol de decisión")
print(arbolPrecision)
print("KNN")
print(knnPrecision)

