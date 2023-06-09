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

precisionSVC = []; precisionSVCBagging = []; precisionSVCAdaBoost = []; precisionSVCGradientBoosting = []
rechazoBaseBaggingSVC = []; rechazoBaseAdaBoostSVC = []; rechazoBaseGradientBoostingSVC = []
rechazoBaggingAdaBoostSVC = []; rechazoBaggingGradientBoostingSVC = []; rechazoAdaBoostGradientBoostingSVC = []

#Iris dataset
scores = cross_val_score(SVC(), XIris, yIris, cv=10, scoring='accuracy')
precisionSVC.append(scores.mean())
scores = cross_val_score(BaggingClassifier(SVC(), max_samples=0.5, max_features=0.5), XIris, yIris, cv=10, scoring='accuracy')
precisionSVCBagging.append(scores.mean())
scores = cross_val_score(AdaBoostClassifier(), XIris, yIris, cv=10, scoring='accuracy')
precisionSVCAdaBoost.append(scores.mean())
scores = cross_val_score(GradientBoostingClassifier(), XIris, yIris, cv=10, scoring='accuracy')
precisionSVCGradientBoosting.append(scores.mean())

#Diabetes dataset
scores = cross_val_score(SVC(), XDiabetes, yDiabetes, cv=10, scoring='accuracy')
precisionSVC.append(scores.mean())
scores = cross_val_score(BaggingClassifier(SVC(), max_samples=0.5, max_features=0.5), XDiabetes, yDiabetes, cv=10, scoring='accuracy')
precisionSVCBagging.append(scores.mean())
scores = cross_val_score(AdaBoostClassifier(), XDiabetes, yDiabetes, cv=10, scoring='accuracy')
precisionSVCAdaBoost.append(scores.mean())
scores = cross_val_score(GradientBoostingClassifier(), XDiabetes, yDiabetes, cv=10, scoring='accuracy')
precisionSVCGradientBoosting.append(scores.mean())

#Ionosphere dataset
scores = cross_val_score(SVC(), XIonosphere, yIonosphere, cv=10, scoring='accuracy')
precisionSVC.append(scores.mean())
scores = cross_val_score(BaggingClassifier(SVC(), max_samples=0.5, max_features=0.5), XIonosphere, yIonosphere, cv=10, scoring='accuracy')
precisionSVCBagging.append(scores.mean())
scores = cross_val_score(AdaBoostClassifier(), XIonosphere, yIonosphere, cv=10, scoring='accuracy')
precisionSVCAdaBoost.append(scores.mean())
scores = cross_val_score(GradientBoostingClassifier(), XIonosphere, yIonosphere, cv=10, scoring='accuracy')
precisionSVCGradientBoosting.append(scores.mean())

#Segment Challenge dataset
scores = cross_val_score(SVC(), XSegment, ySegment, cv=10, scoring='accuracy')
precisionSVC.append(scores.mean())
scores = cross_val_score(BaggingClassifier(SVC(), max_samples=0.5, max_features=0.5), XSegment, ySegment, cv=10, scoring='accuracy')
precisionSVCBagging.append(scores.mean())
scores = cross_val_score(AdaBoostClassifier(), XSegment, ySegment, cv=10, scoring='accuracy')
precisionSVCAdaBoost.append(scores.mean())
scores = cross_val_score(GradientBoostingClassifier(), XSegment, ySegment, cv=10, scoring='accuracy')
precisionSVCGradientBoosting.append(scores.mean())

#Wine dataset
scores = cross_val_score(SVC(), dfWine.data, dfWine.target, cv=10, scoring='accuracy')
precisionSVC.append(scores.mean())
scores = cross_val_score(BaggingClassifier(SVC(), max_samples=0.5, max_features=0.5), dfWine.data, dfWine.target, cv=10, scoring='accuracy')
precisionSVCBagging.append(scores.mean())
scores = cross_val_score(AdaBoostClassifier(), dfWine.data, dfWine.target, cv=10, scoring='accuracy')
precisionSVCAdaBoost.append(scores.mean())
scores = cross_val_score(GradientBoostingClassifier(), dfWine.data, dfWine.target, cv=10, scoring='accuracy')
precisionSVCGradientBoosting.append(scores.mean())

#Breast Cancer dataset
scores = cross_val_score(SVC(), datasetBC.data, datasetBC.target, cv=10, scoring='accuracy')
precisionSVC.append(scores.mean())
scores = cross_val_score(BaggingClassifier(SVC(), max_samples=0.5, max_features=0.5), datasetBC.data, datasetBC.target, cv=10, scoring='accuracy')
precisionSVCBagging.append(scores.mean())
scores = cross_val_score(AdaBoostClassifier(), datasetBC.data, datasetBC.target, cv=10, scoring='accuracy')
precisionSVCAdaBoost.append(scores.mean())
scores = cross_val_score(GradientBoostingClassifier(), datasetBC.data, datasetBC.target, cv=10, scoring='accuracy')
precisionSVCGradientBoosting.append(scores.mean())

plt.figure(figsize=(10, 10))
plt.plot(precisionSVC, label='SVC')
plt.plot(precisionSVCBagging, label='SVCBagging')
plt.plot(precisionSVCAdaBoost, label='SVCAdaBoost')
plt.plot(precisionSVCGradientBoosting, label='SVCGradientBoosting')
plt.xlabel('Dataset')
plt.ylabel('Precision')
plt.title('Precision de los clasificadores')
plt.xticks(range(6), ['Iris', 'Diabetes', 'Ionosphere', 'Segment', 'Wine', 'Breast Cancer'])
plt.legend()
plt.show()

print("Datasets: Iris, Diabetes, Ionosphere, Segment, Wine, Breast Cancer")
print("SVC: ", precisionSVC)
print("SVCBagging: ", precisionSVCBagging)
print("SVCAdaBoost: ", precisionSVCAdaBoost)

datasets = ['Iris', 'Diabetes', 'Ionosphere', 'Segment', 'Wine', 'Breast Cancer']

print("Test de Iman-Davenport : SVC")
print("-----------------------------")
for i in range (0, len(datasets)):
    print("Dataset: ", datasets[i])
    stat, p = mannwhitneyu(precisionSVC[i], precisionSVCBagging[i])
    if p > 0.05:
        rechazoBaseBaggingSVC.append("No")
    else:
        rechazoBaseBaggingSVC.append("Si")
    print("P-value SVC Base vs Bagging : ", p, " Rechazo: ", rechazoBaseBaggingSVC[i])

    stat, p = mannwhitneyu(precisionSVC[i], precisionSVCAdaBoost[i])
    if p > 0.05:
        rechazoBaseAdaBoostSVC.append("No")
    else:
        rechazoBaseAdaBoostSVC.append("Si")
    print("P-value SVC Base vs AdaBoost : ", p, " Rechazo: ", rechazoBaseAdaBoostSVC[i])

    stat, p = mannwhitneyu(precisionSVC[i], precisionSVCGradientBoosting[i])
    if p > 0.05:
        rechazoBaseGradientBoostingSVC.append("No")
    else:
        rechazoBaseGradientBoostingSVC.append("Si")
    print("P-value SVC Base vs GradientBoosting : ", p, " Rechazo: ", rechazoBaseGradientBoostingSVC[i])

    stat, p = mannwhitneyu(precisionSVCBagging[i], precisionSVCAdaBoost[i])
    if p > 0.05:
        rechazoBaggingAdaBoostSVC.append("No")
    else:
        rechazoBaggingAdaBoostSVC.append("Si")
    print("P-value SVC Bagging vs AdaBoost : ", p, " Rechazo: ", rechazoBaggingAdaBoostSVC[i])

    stat, p = mannwhitneyu(precisionSVCBagging[i], precisionSVCGradientBoosting[i])
    if p > 0.05:
        rechazoBaggingGradientBoostingSVC.append("No")
    else:
        rechazoBaggingGradientBoostingSVC.append("Si")
    print("P-value SVC Bagging vs GradientBoosting : ", p, " Rechazo: ", rechazoBaggingGradientBoostingSVC[i])

    stat, p = mannwhitneyu(precisionSVCAdaBoost[i], precisionSVCGradientBoosting[i])
    if p > 0.05:
        rechazoAdaBoostGradientBoostingSVC.append("No")
    else:
        rechazoAdaBoostGradientBoostingSVC.append("Si")
    print("P-value SVC AdaBoost vs GradientBoosting : ", p, " Rechazo: ", rechazoAdaBoostGradientBoostingSVC[i])