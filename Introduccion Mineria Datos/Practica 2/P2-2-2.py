from sklearn import datasets
from sklearn.tree import DecisionTreeClassifier
from sklearn.model_selection import train_test_split
import wittgenstein as lw
import pandas as pd
import matplotlib.pyplot as plt

# Read dataset
df = pd.read_csv("iris.csv")
train, test = train_test_split(df, test_size=.33)
datasets = ["iris"]
# Create and train model
ripper_clf = lw.RIPPER(); ripper_accuracy = []
ripper_clf.fit(df, class_feat="variety", pos_class='Versicolor')

# Score
X_test = test.drop('variety', axis=1)
y_test = test['variety']
ripper_accuracy.append(ripper_clf.score(X_test, y_test)) # Accuracy

# Entrenamiento y test del arbol de decision
X_train = train.drop('variety', axis=1)
y_train = train['variety']
tree_clf = DecisionTreeClassifier(); tree_accuracy = []
tree_clf.fit(X_train, y_train)
tree_accuracy.append(tree_clf.score(X_test, y_test)) # Accuracy

# Dataset de prueba Breast Cancer
df = pd.read_csv("breast_cancer.csv")
train, test = train_test_split(df, test_size=.33)
datasets.append("breast_cancer")
# Create and train model
ripper_clf = lw.RIPPER()
ripper_clf.fit(df, class_feat="diagnosis", pos_class='M')
# Score
X_test = test.drop('diagnosis', axis=1)
y_test = test['diagnosis']
ripper_accuracy.append(ripper_clf.score(X_test, y_test)) # Accuracy

# Entrenamiento y test del arbol de decision
X_train = train.drop('diagnosis', axis=1)
y_train = train['diagnosis']
tree_clf = DecisionTreeClassifier()
tree_clf.fit(X_train, y_train)
tree_accuracy.append(tree_clf.score(X_test, y_test)) # Accuracy

# Dataset de prueba titanic
df = pd.read_csv("titanic.csv")
# Creamos una copia del dataset para poder eliminar las filas que den errores
df2 = df.copy()
df2.drop(['Name', 'Ticket', 'Cabin', 'Sex','Age','Embarked'], axis=1)
train, test = train_test_split(df2, test_size=.33)
datasets.append("titanic")
# Create and train model
ripper_clf = lw.RIPPER()
ripper_clf.fit(df2, class_feat="Survived", pos_class=1)
# Score
X_test = test.drop('Survived', axis=1)
y_test = test['Survived']
ripper_accuracy.append(ripper_clf.score(X_test, y_test)) # Accuracy

# Entrenamiento y test del arbol de decision
X_train = train.drop('Survived', axis=1)
X_train = X_train.drop(['Name', 'Ticket', 'Cabin', 'Sex','Age','Embarked'], axis=1)
y_train = train['Survived']
tree_clf = DecisionTreeClassifier()
tree_clf.fit(X_train, y_train)
X_test = test.drop('Survived', axis=1)
X_test = X_test.drop(['Name', 'Ticket', 'Cabin', 'Sex','Age','Embarked'], axis=1)
y_test = test['Survived']
tree_accuracy.append(tree_clf.score(X_test, y_test)) # Accuracy

# Mostramos los modelos de Ripper y el arbol de decision en un grafico
plt.plot(datasets, ripper_accuracy, label='Ripper')
plt.plot(datasets, tree_accuracy, label='Tree')
plt.xlabel('Dataset')
plt.ylabel('Accuracy')
plt.title('Accuracy of Ripper and Tree')
plt.legend()
plt.show()

#Print accuracy of each dataset
print("Datasets: ", datasets)
print("Accuracy of Ripper: ", ripper_accuracy)
print("Accuracy of Tree: ", tree_accuracy)

