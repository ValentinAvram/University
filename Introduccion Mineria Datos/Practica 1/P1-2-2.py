import pandas as pd
from matplotlib import pyplot as plt
from scipy.io import arff
from sklearn.model_selection import train_test_split
from sklearn.tree import DecisionTreeClassifier
from sklearn import metrics
from sklearn.neighbors import KNeighborsClassifier

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

#Box plot Iris
plt.figure(1)
plt.boxplot(dfIris)
plt.title('BoxPlot Iris Dataset')

plt.xticks([1, 2, 3, 4], ['Sepal Length', 'Sepal Width', 'Petal Length', 'Petal Width'])
plt.ylabel('cm')
plt.show()

#Box plot CPU
plt.figure(2)
plt.boxplot(dfCPU)
plt.title('BoxPlot CPU Dataset')

#Box plot Diabetes
plt.figure(3)
plt.boxplot(dfDiabetes)
plt.title('BoxPlot Diabetes Dataset')

#Box plot Ionosphere
plt.figure(4)
plt.boxplot(dfIonosphere)
plt.title('BoxPlot Ionosphere Dataset')

#Box plot Segment
plt.figure(5)
plt.boxplot(dfSegment)
plt.title('BoxPlot Segment Dataset')

#Box plot Iris por clases
dfIris.boxplot(by='class', figsize=(10,10))
plt.title('BoxPlot Iris Dataset por clases')

plt.show()