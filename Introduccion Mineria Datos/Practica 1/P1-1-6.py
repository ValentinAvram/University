import pandas as pd
import numpy as np
from sklearn import metrics
from sklearn.datasets import load_breast_cancer
from sklearn.experimental import enable_iterative_imputer
from sklearn.impute import SimpleImputer, IterativeImputer
from sklearn.model_selection import train_test_split
from sklearn.neighbors import KNeighborsClassifier
from sklearn.tree import DecisionTreeClassifier

arbol = DecisionTreeClassifier()
knn = KNeighborsClassifier(n_neighbors=3)

def PrecisionSimpleImputer(metodo1, metodo2):
  # SimpleImputer es una clase que permite rellenar valores faltantes en un array de numpy
  breast_cancer_X = load_breast_cancer().data
  breast_cancer_Y = load_breast_cancer().target
  breast_cancer_imputer = SimpleImputer(missing_values=np.nan, strategy='mean')
  # Ajustamos el imputer a los datos
  breast_cancer_imputer.fit(breast_cancer_X)
  # Transformamos los datos
  X_breast_cancer = breast_cancer_imputer.transform(breast_cancer_X)
  # Split data into training and test sets
  X_train_breast_cancer, X_test_breast_cancer, y_train_breast_cancer, y_test_breast_cancer = train_test_split(X_breast_cancer, breast_cancer_Y, test_size=0.1, random_state=0)
  # Entrenamos el modelo arbol de decision
  metodo1.fit(X_train_breast_cancer, y_train_breast_cancer)
  y_pred_arbol = metodo1.predict(X_test_breast_cancer)
  metodo1_precision = metrics.accuracy_score(y_test_breast_cancer, y_pred_arbol)

  # Entrenamos el modelo KNN
  metodo2.fit(X_train_breast_cancer, y_train_breast_cancer)
  y_pred_knn = metodo2.predict(X_test_breast_cancer)
  metodo2_precision = metrics.accuracy_score(y_test_breast_cancer, y_pred_knn)
  return metodo1_precision, metodo2_precision

def PrecisionIterativeImputer(metodo1, metodo2):
  # SimpleImputer es una clase que permite rellenar valores faltantes en un array de numpy
  breast_cancer_imputer = IterativeImputer(max_iter=10, random_state=0)
  breast_cancer_X = load_breast_cancer().data
  breast_cancer_Y = load_breast_cancer().target
  # Ajustamos el imputer a los datos
  breast_cancer_imputer.fit(breast_cancer_X)
  # Transformamos los datos
  X_breast_cancer = breast_cancer_imputer.transform(breast_cancer_X)
  # Split data into training and test sets
  X_train_breast_cancer, X_test_breast_cancer, y_train_breast_cancer, y_test_breast_cancer = train_test_split(
    X_breast_cancer, breast_cancer_Y, test_size=0.1, random_state=0)
  # Entrenamos el modelo arbol de decision
  metodo1.fit(X_train_breast_cancer, y_train_breast_cancer)
  y_pred_arbol = metodo1.predict(X_test_breast_cancer)
  metodo1_precision = metrics.accuracy_score(y_test_breast_cancer, y_pred_arbol)

  # Entrenamos el modelo KNN
  metodo2.fit(X_train_breast_cancer, y_train_breast_cancer)
  y_pred_knn = metodo2.predict(X_test_breast_cancer)
  metodo2_precision = metrics.accuracy_score(y_test_breast_cancer, y_pred_knn)
  return metodo1_precision, metodo2_precision

arbol_precision, knn_precision = PrecisionSimpleImputer(arbol, knn)
# Mostramos los resultados
print("Precision del arbol de decision: ", arbol_precision)
print("Precision del KNN: ", knn_precision)

arbol_precision, knn_precision = PrecisionIterativeImputer(arbol, knn)
# Mostramos los resultados
print("Precision del arbol de decision: ", arbol_precision)
print("Precision del KNN: ", knn_precision)
