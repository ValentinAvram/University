import pandas as pd
from matplotlib import pyplot as plt
from scipy.io import arff
from sklearn.model_selection import train_test_split
from sklearn.tree import DecisionTreeClassifier
from sklearn import metrics
from sklearn.neighbors import KNeighborsClassifier
import seaborn as sns

#Iris dataset
dfIris = arff.loadarff('iris.arff')
dfIris = pd.DataFrame(dfIris[0])
dfIris['class'] = pd.Categorical(dfIris['class'])
dfIris['class'] = dfIris['class'].cat.codes

#CPU dataset
dfCPU = arff.loadarff('cpu.arff')
dfCPU = pd.DataFrame(dfCPU[0])
dfCPU['class'] = pd.Categorical(dfCPU['class'])
dfCPU['class'] = dfCPU['class'].cat.codes

#Diabetes dataset
dfDiabetes = arff.loadarff('diabetes.arff')
dfDiabetes = pd.DataFrame(dfDiabetes[0])
dfDiabetes['class'] = pd.Categorical(dfDiabetes['class'])
dfDiabetes['class'] = dfDiabetes['class'].cat.codes

#Ionosphere dataset
dfIonosphere = arff.loadarff('ionosphere.arff')
dfIonosphere = pd.DataFrame(dfIonosphere[0])
dfIonosphere['class'] = pd.Categorical(dfIonosphere['class'])
dfIonosphere['class'] = dfIonosphere['class'].cat.codes

#Segment Challenge dataset
dfSegment = arff.loadarff('segment-challenge.arff')
dfSegment = pd.DataFrame(dfSegment[0])
dfSegment['class'] = pd.Categorical(dfSegment['class'])
dfSegment['class'] = dfSegment['class'].cat.codes

#Correlation matrix with heatmap for Iris dataset
corr = dfIris.corr()
sns.heatmap(corr, annot=True)
plt.show()

#Correlation matrix with heatmap for CPU dataset
corr = dfCPU.corr()
sns.heatmap(corr, annot=True)
plt.show()

#Correlation matrix with heatmap for Diabetes dataset
corr = dfDiabetes.corr()
sns.heatmap(corr, annot=True)
plt.show()

#Correlation matrix with heatmap for Ionosphere dataset
corr = dfIonosphere.corr()
sns.heatmap(corr, annot=True, fmt=".1f")
plt.show()

#Correlation matrix with heatmap for Segment Challenge dataset
corr = dfSegment.corr()
sns.heatmap(corr, annot=True, fmt=".1f")
plt.show()
