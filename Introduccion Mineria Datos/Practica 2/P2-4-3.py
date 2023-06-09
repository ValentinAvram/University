import numpy as np
import pandas as pd
from matplotlib import pyplot as plt
from scipy.io import arff
from scipy.stats import mannwhitneyu
from sklearn.model_selection import train_test_split, cross_val_score, GridSearchCV
from sklearn.multiclass import OneVsOneClassifier, OneVsRestClassifier, OutputCodeClassifier
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

results = []

print("\nOneVsOneClassifier\n")
model = OneVsOneClassifier(SVC(kernel='linear'))
model.fit(X_trainIris, y_trainIris)
print("Iris dataset")
print("Accuracy: ", model.score(X_testIris, y_testIris))
results.append(model.score(X_testIris, y_testIris))

model = OneVsOneClassifier(SVC(kernel='linear'))
model.fit(X_trainDiabetes, y_trainDiabetes)
print("Diabetes dataset")
print("Accuracy: ", model.score(X_testDiabetes, y_testDiabetes))
results.append(model.score(X_testDiabetes, y_testDiabetes))

model = OneVsOneClassifier(SVC(kernel='linear'))
model.fit(X_trainIonosphere, y_trainIonosphere)
print("Ionosphere dataset")
print("Accuracy: ", model.score(X_testIonosphere, y_testIonosphere))
results.append(model.score(X_testIonosphere, y_testIonosphere))

model = OneVsOneClassifier(SVC(kernel='linear'))
model.fit(X_trainSegment, y_trainSegment)
print("Segment dataset")
print("Accuracy: ", model.score(X_testSegment, y_testSegment))
results.append(model.score(X_testSegment, y_testSegment))

model = OneVsOneClassifier(SVC(kernel='linear'))
model.fit(dfWine.data, dfWine.target)
print("Wine dataset")
print("Accuracy: ", model.score(dfWine.data, dfWine.target))
results.append(model.score(dfWine.data, dfWine.target))

model = OneVsOneClassifier(SVC(kernel='linear'))
model.fit(datasetBC.data, datasetBC.target)
print("Breast Cancer dataset")
print("Accuracy: ", model.score(datasetBC.data, datasetBC.target))
results.append(model.score(datasetBC.data, datasetBC.target))

#plot the results
plt.bar(['Iris', 'Diabetes', 'Ionosphere', 'Segment', 'Wine', 'Breast Cancer'], results)
plt.title('Accuracy of OneVsOneClassifier with linear SVC')
plt.xlabel('Dataset')
plt.ylabel('Accuracy')
plt.show()

results2 = []

print("\nOneVsRestClassifier\n")
model = OneVsRestClassifier(SVC(kernel='linear'))
model.fit(X_trainIris, y_trainIris)
print("Iris dataset")
print("Accuracy: ", model.score(X_testIris, y_testIris))
results2.append(model.score(X_testIris, y_testIris))

model = OneVsRestClassifier(SVC(kernel='linear'))
model.fit(X_trainDiabetes, y_trainDiabetes)
print("Diabetes dataset")
print("Accuracy: ", model.score(X_testDiabetes, y_testDiabetes))
results2.append(model.score(X_testDiabetes, y_testDiabetes))

model = OneVsRestClassifier(SVC(kernel='linear'))
model.fit(X_trainIonosphere, y_trainIonosphere)
print("Ionosphere dataset")
print("Accuracy: ", model.score(X_testIonosphere, y_testIonosphere))
results2.append(model.score(X_testIonosphere, y_testIonosphere))

model = OneVsRestClassifier(SVC(kernel='linear'))
model.fit(X_trainSegment, y_trainSegment)
print("Segment dataset")
print("Accuracy: ", model.score(X_testSegment, y_testSegment))
results2.append(model.score(X_testSegment, y_testSegment))

model = OneVsRestClassifier(SVC(kernel='linear'))
model.fit(dfWine.data, dfWine.target)
print("Wine dataset")
print("Accuracy: ", model.score(dfWine.data, dfWine.target))
results2.append(model.score(dfWine.data, dfWine.target))

model = OneVsRestClassifier(SVC(kernel='linear'))
model.fit(datasetBC.data, datasetBC.target)
print("Breast Cancer dataset")
print("Accuracy: ", model.score(datasetBC.data, datasetBC.target))
results2.append(model.score(datasetBC.data, datasetBC.target))

#plot the results
plt.bar(['Iris', 'Diabetes', 'Ionosphere', 'Segment', 'Wine', 'Breast Cancer'], results)
plt.title('Accuracy of OneVsRestClassifier with linear SVC')
plt.xlabel('Dataset')
plt.ylabel('Accuracy')
plt.show()

print("\nECOC Classifier\n")
results3 = []

model = OutputCodeClassifier(SVC(kernel='linear'))
model.fit(X_trainIris, y_trainIris)
print("Iris dataset")
print("Accuracy: ", model.score(X_testIris, y_testIris))
results3.append(model.score(X_testIris, y_testIris))

model = OutputCodeClassifier(SVC(kernel='linear'))
model.fit(X_trainDiabetes, y_trainDiabetes)
print("Diabetes dataset")
print("Accuracy: ", model.score(X_testDiabetes, y_testDiabetes))
results3.append(model.score(X_testDiabetes, y_testDiabetes))

model = OutputCodeClassifier(SVC(kernel='linear'))
model.fit(X_trainIonosphere, y_trainIonosphere)
print("Ionosphere dataset")
print("Accuracy: ", model.score(X_testIonosphere, y_testIonosphere))
results3.append(model.score(X_testIonosphere, y_testIonosphere))

model = OutputCodeClassifier(SVC(kernel='linear'))
model.fit(X_trainSegment, y_trainSegment)
print("Segment dataset")
print("Accuracy: ", model.score(X_testSegment, y_testSegment))
results3.append(model.score(X_testSegment, y_testSegment))

model = OutputCodeClassifier(SVC(kernel='linear'))
model.fit(dfWine.data, dfWine.target)
print("Wine dataset")
print("Accuracy: ", model.score(dfWine.data, dfWine.target))
results3.append(model.score(dfWine.data, dfWine.target))

model = OutputCodeClassifier(SVC(kernel='linear'))
model.fit(datasetBC.data, datasetBC.target)
print("Breast Cancer dataset")
print("Accuracy: ", model.score(datasetBC.data, datasetBC.target))
results3.append(model.score(datasetBC.data, datasetBC.target))

#plot the results
plt.bar(['Iris', 'Diabetes', 'Ionosphere', 'Segment', 'Wine', 'Breast Cancer'], results)
plt.title('Accuracy of ECOC Classifier with linear SVC')
plt.xlabel('Dataset')
plt.ylabel('Accuracy')
plt.show()

datasets = ['Iris', 'Diabetes', 'Ionosphere', 'Segment', 'Wine', 'Breast Cancer']

#Test de Iman-Davenport
print("\nIman-Davenport Classifier\n")

for i in range(0, len(datasets)):
    print(datasets[i])
    print("---------")
    stat , p = mannwhitneyu(results, results2)
    if p > 0.05:
        print("OneVsOneClassifier and OneVsRestClassifier are the same, with p-value: ", p)
    else:
        print("OneVsOneClassifier and OneVsRestClassifier are different, with p-value: ", p)

    stat , p = mannwhitneyu(results, results3)
    if p > 0.05:
        print("OneVsOneClassifier and ECOC Classifier are the same, with p-value: ", p)
    else:
        print("OneVsOneClassifier and ECOC Classifier are different, with p-value: ", p)

    stat , p = mannwhitneyu(results2, results3)
    if p > 0.05:
        print("OneVsRestClassifier and ECOC Classifier are the same, with p-value: ", p)
    else:
        print("OneVsRestClassifier and ECOC Classifier are different, with p-value: ", p)

