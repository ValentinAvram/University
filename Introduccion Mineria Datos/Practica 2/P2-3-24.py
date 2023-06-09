import pandas as pd
from matplotlib import pyplot as plt
from scipy.io import arff
from scipy.stats import mannwhitneyu
from sklearn.ensemble import BaggingClassifier, AdaBoostClassifier, GradientBoostingClassifier
from sklearn.model_selection import train_test_split, cross_val_score, GridSearchCV
from sklearn.svm import SVC
from sklearn.tree import DecisionTreeClassifier
from sklearn import datasets

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

precisionArbol = []; precisionArbolBagging = []; precisionArbolAdaBoost = []; precisionArbolGradientBoosting = []
rechazoBaseBagging = []; rechazoBaseAdaBoost = []; rechazoBaseGradientBoosting = []
rechazoBaggingAdaBoost = []; rechazoBaggingGradientBoosting = []; rechazoAdaBoostGradientBoosting = []

#Iris dataset
scores = cross_val_score(DecisionTreeClassifier(), XIris, yIris, cv=10, scoring='accuracy')
precisionArbol.append(scores.mean())
scores = cross_val_score(BaggingClassifier(DecisionTreeClassifier(), max_samples=0.5, max_features=0.5), XIris, yIris, cv=10, scoring='accuracy')
precisionArbolBagging.append(scores.mean())
scores = cross_val_score(AdaBoostClassifier(DecisionTreeClassifier(max_depth=1), algorithm="SAMME", n_estimators=200), XIris, yIris, cv=10, scoring='accuracy')
precisionArbolAdaBoost.append(scores.mean())
scores = cross_val_score(GradientBoostingClassifier(n_estimators=100, learning_rate=1.0, max_depth=1, random_state=0), XIris, yIris, cv=10, scoring='accuracy')
precisionArbolGradientBoosting.append(scores.mean())

#Diabetes dataset
scores = cross_val_score(DecisionTreeClassifier(), XDiabetes, yDiabetes, cv=10, scoring='accuracy')
precisionArbol.append(scores.mean())
scores = cross_val_score(BaggingClassifier(DecisionTreeClassifier(), max_samples=0.5, max_features=0.5), XDiabetes, yDiabetes, cv=10, scoring='accuracy')
precisionArbolBagging.append(scores.mean())
scores = cross_val_score(AdaBoostClassifier(DecisionTreeClassifier(max_depth=1), algorithm="SAMME", n_estimators=200), XDiabetes, yDiabetes, cv=10, scoring='accuracy')
precisionArbolAdaBoost.append(scores.mean())
scores = cross_val_score(GradientBoostingClassifier(n_estimators=100, learning_rate=1.0, max_depth=1, random_state=0), XDiabetes, yDiabetes, cv=10, scoring='accuracy')
precisionArbolGradientBoosting.append(scores.mean())

#Ionosphere dataset
scores = cross_val_score(DecisionTreeClassifier(), XIonosphere, yIonosphere, cv=10, scoring='accuracy')
precisionArbol.append(scores.mean())
scores = cross_val_score(BaggingClassifier(DecisionTreeClassifier(), max_samples=0.5, max_features=0.5), XIonosphere, yIonosphere, cv=10, scoring='accuracy')
precisionArbolBagging.append(scores.mean())
scores = cross_val_score(AdaBoostClassifier(DecisionTreeClassifier(max_depth=1), algorithm="SAMME", n_estimators=200), XIonosphere, yIonosphere, cv=10, scoring='accuracy')
precisionArbolAdaBoost.append(scores.mean())
scores = cross_val_score(GradientBoostingClassifier(n_estimators=100, learning_rate=1.0, max_depth=1, random_state=0), XIonosphere, yIonosphere, cv=10, scoring='accuracy')
precisionArbolGradientBoosting.append(scores.mean())

#Segment dataset
scores = cross_val_score(DecisionTreeClassifier(), XSegment, ySegment, cv=10, scoring='accuracy')
precisionArbol.append(scores.mean())
scores = cross_val_score(BaggingClassifier(DecisionTreeClassifier(), max_samples=0.5, max_features=0.5), XSegment, ySegment, cv=10, scoring='accuracy')
precisionArbolBagging.append(scores.mean())
scores = cross_val_score(AdaBoostClassifier(DecisionTreeClassifier(max_depth=1), algorithm="SAMME", n_estimators=200), XSegment, ySegment, cv=10, scoring='accuracy')
precisionArbolAdaBoost.append(scores.mean())
scores = cross_val_score(GradientBoostingClassifier(n_estimators=100, learning_rate=1.0, max_depth=1, random_state=0), XSegment, ySegment, cv=10, scoring='accuracy')
precisionArbolGradientBoosting.append(scores.mean())

#Wine dataset
scores = cross_val_score(DecisionTreeClassifier(), dfWine.data, dfWine.target, cv=10, scoring='accuracy')
precisionArbol.append(scores.mean())
scores = cross_val_score(BaggingClassifier(DecisionTreeClassifier(), max_samples=0.5, max_features=0.5), dfWine.data, dfWine.target, cv=10, scoring='accuracy')
precisionArbolBagging.append(scores.mean())
scores = cross_val_score(AdaBoostClassifier(DecisionTreeClassifier(max_depth=1), algorithm="SAMME", n_estimators=200), dfWine.data, dfWine.target, cv=10, scoring='accuracy')
precisionArbolAdaBoost.append(scores.mean())
scores = cross_val_score(GradientBoostingClassifier(n_estimators=100, learning_rate=1.0, max_depth=1, random_state=0), dfWine.data, dfWine.target, cv=10, scoring='accuracy')
precisionArbolGradientBoosting.append(scores.mean())

#Breast Cancer dataset
scores = cross_val_score(DecisionTreeClassifier(), datasetBC.data, datasetBC.target, cv=10, scoring='accuracy')
precisionArbol.append(scores.mean())
scores = cross_val_score(BaggingClassifier(DecisionTreeClassifier(), max_samples=0.5, max_features=0.5), datasetBC.data, datasetBC.target, cv=10, scoring='accuracy')
precisionArbolBagging.append(scores.mean())
scores = cross_val_score(AdaBoostClassifier(DecisionTreeClassifier(max_depth=1), algorithm="SAMME", n_estimators=200), datasetBC.data, datasetBC.target, cv=10, scoring='accuracy')
precisionArbolAdaBoost.append(scores.mean())
scores = cross_val_score(GradientBoostingClassifier(n_estimators=100, learning_rate=1.0, max_depth=1, random_state=0), datasetBC.data, datasetBC.target, cv=10, scoring='accuracy')
precisionArbolGradientBoosting.append(scores.mean())

datasets = ['Iris', 'Diabetes', 'Ionosphere', 'Segment', 'Wine', 'Breast Cancer']

plt.figure(figsize=(10, 5))
plt.plot(datasets, precisionArbol, label='Árbol de decisión')
plt.plot(datasets, precisionArbolBagging, label='Árbol de decisión con Bagging')
plt.plot(datasets, precisionArbolAdaBoost, label='Árbol de decisión con AdaBoost')
plt.plot(datasets, precisionArbolGradientBoosting, label='Árbol de decisión con Gradient Boosting')
plt.legend()
plt.title('Precisión de los algoritmos')
plt.xlabel('Dataset')
plt.ylabel('Precisión')
plt.show()

#Print the results
print('Precisión de los algoritmos')
print('\nÁrbol de decisión: ', precisionArbol)
print('Árbol de decisión con Bagging: ', precisionArbolBagging)
print('Árbol de decisión con AdaBoost: ', precisionArbolAdaBoost)
print('Árbol de decisión con Gradient Boosting: ', precisionArbolGradientBoosting)


print('\nTest de Iman-Davenport para un Arbol de Decision')
print('------------------------------------------------')

for i in range (0, len(datasets)):
    print('\nDataset: ', datasets[i])
    print('------------------------------------------------')
    stat, p = mannwhitneyu(precisionArbol[i], precisionArbolBagging[i])
    if p > 0.05:
        rechazoBaseBagging.append('No')
    else:
        rechazoBaseBagging.append('Si')
    print("P-valor Base vs Bagging: ", p, " Rechazo: ", rechazoBaseBagging[i])

    stat, p = mannwhitneyu(precisionArbol[i], precisionArbolAdaBoost[i])
    if p > 0.05:
        rechazoBaseAdaBoost.append('No')
    else:
        rechazoBaseAdaBoost.append('Si')
    print("P-valor Base vs AdaBoost: ", p, " Rechazo: ", rechazoBaseAdaBoost[i])

    stat, p = mannwhitneyu(precisionArbol[i], precisionArbolGradientBoosting[i])
    if p > 0.05:
        rechazoBaseGradientBoosting.append('No')
    else:
        rechazoBaseGradientBoosting.append('Si')
    print("P-valor Base vs Gradient Boosting: ", p, " Rechazo: ", rechazoBaseGradientBoosting[i])

    stat, p = mannwhitneyu(precisionArbolBagging[i], precisionArbolAdaBoost[i])
    if p > 0.05:
        rechazoBaggingAdaBoost.append('No')
    else:
        rechazoBaggingAdaBoost.append('Si')
    print("P-valor Bagging vs AdaBoost: ", p, " Rechazo: ", rechazoBaggingAdaBoost[i])

    stat, p = mannwhitneyu(precisionArbolBagging[i], precisionArbolGradientBoosting[i])
    if p > 0.05:
        rechazoBaggingGradientBoosting.append('No')
    else:
        rechazoBaggingGradientBoosting.append('Si')
    print("P-valor Bagging vs Gradient Boosting: ", p, " Rechazo: ", rechazoBaggingGradientBoosting[i])

    stat, p = mannwhitneyu(precisionArbolAdaBoost[i], precisionArbolGradientBoosting[i])
    if p > 0.05:
        rechazoAdaBoostGradientBoosting.append('No')
    else:
        rechazoAdaBoostGradientBoosting.append('Si')
    print("P-valor AdaBoost vs Gradient Boosting: ", p, " Rechazo: ", rechazoAdaBoostGradientBoosting[i])

