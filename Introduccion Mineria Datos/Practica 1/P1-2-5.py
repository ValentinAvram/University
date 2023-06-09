import pandas as pd
from matplotlib import pyplot as plt
from scipy.io import arff
from sklearn.model_selection import train_test_split
from sklearn.tree import DecisionTreeClassifier
from sklearn import metrics
from sklearn.neighbors import KNeighborsClassifier
import seaborn as sns
from sklearn import datasets

# Load dataset
dataset = pd.read_csv('iris.data', header=None, names=["sepal length","sepal width","petal length","petal width","class"])
iris_features = ["sepal length","sepal width","petal length","petal width"]
iris_X = dataset[iris_features]
iris_y = dataset["class"]

plt.figure(figsize=(10,10))
sns.heatmap(iris_X.T.corr(), annot=True, fmt='.1f')
plt.show()