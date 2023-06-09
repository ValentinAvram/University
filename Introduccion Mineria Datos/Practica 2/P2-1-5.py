# Ejercicio 5 practica 2 de IMD
# Realizado por Jaime Lorenzo Sanchez

# Listado de bibliotecas
from sklearn import datasets
from sklearn.tree import DecisionTreeClassifier
from sklearn.neighbors import KNeighborsClassifier
from sklearn.svm import SVC
# Test de Friedman para comparar tres metodos de clasificacion en N datasets
from scipy.stats import friedmanchisquare
from sklearn.model_selection import train_test_split

# Dataset a entrenar y testear
iris = datasets.load_iris()
wine = datasets.load_wine()
breast_cancer = datasets.load_breast_cancer()
digits = datasets.load_digits()

# Modelos de entrenamiento
arbol = DecisionTreeClassifier()
knn = KNeighborsClassifier(n_neighbors=5)
svm = SVC(kernel='linear', C=1)
pred_arbol = []; pred_knn = []; pred_svm = []

# Entrenamos el arbol de decision
X_train, X_test, y_train, y_test = train_test_split(iris.data, iris.target, test_size=0.3, random_state=1)
arbol.fit(X_train, y_train)
pred_arbol.append(arbol.predict(X_test))

X_train, X_test, y_train, y_test = train_test_split(wine.data, wine.target, test_size=0.3, random_state=1)
arbol.fit(X_train, y_train)
pred_arbol.append(arbol.predict(X_test))

X_train, X_test, y_train, y_test = train_test_split(breast_cancer.data, breast_cancer.target, test_size=0.3, random_state=1)
arbol.fit(X_train, y_train)
pred_arbol.append(arbol.predict(X_test))

X_train, X_test, y_train, y_test = train_test_split(digits.data, digits.target, test_size=0.3, random_state=1)
arbol.fit(X_train, y_train)
pred_arbol.append(arbol.predict(X_test))

# Entrenamos el k-vecinos
X_train, X_test, y_train, y_test = train_test_split(iris.data, iris.target, test_size=0.3, random_state=1)
knn.fit(X_train, y_train)
pred_knn.append(knn.predict(X_test))

X_train, X_test, y_train, y_test = train_test_split(wine.data, wine.target, test_size=0.3, random_state=1)
knn.fit(X_train, y_train)
pred_knn.append(knn.predict(X_test))

X_train, X_test, y_train, y_test = train_test_split(breast_cancer.data, breast_cancer.target, test_size=0.3, random_state=1)
knn.fit(X_train, y_train)
pred_knn.append(knn.predict(X_test))

X_train, X_test, y_train, y_test = train_test_split(digits.data, digits.target, test_size=0.3, random_state=1)
knn.fit(X_train, y_train)
pred_knn.append(knn.predict(X_test))

# Entrenamos el SVM
X_train, X_test, y_train, y_test = train_test_split(iris.data, iris.target, test_size=0.3, random_state=1)
svm.fit(X_train, y_train)
pred_svm.append(svm.predict(X_test))

X_train, X_test, y_train, y_test = train_test_split(wine.data, wine.target, test_size=0.3, random_state=1)
svm.fit(X_train, y_train)
pred_svm.append(svm.predict(X_test))

X_train, X_test, y_train, y_test = train_test_split(breast_cancer.data, breast_cancer.target, test_size=0.3, random_state=1)
svm.fit(X_train, y_train)
pred_svm.append(svm.predict(X_test))

X_train, X_test, y_train, y_test = train_test_split(digits.data, digits.target, test_size=0.3, random_state=1)
svm.fit(X_train, y_train)
pred_svm.append(svm.predict(X_test))

# Metodo de comparacion de tres metodos de clasificacion en N datasets (p<0.05) usando Friedman
friedman_arbol_knn_svm_iris = friedmanchisquare(pred_arbol[0], pred_knn[0], pred_svm[0])

print("Rangos de los metodos de clasificacion en el dataset iris")
print("Arbol de decision: ", pred_arbol[0])
print("P-value: ", friedman_arbol_knn_svm_iris.pvalue)

friedman_arbol_knn_svm_wine = friedmanchisquare(pred_arbol[1], pred_knn[1], pred_svm[1])
friedman_arbol_knn_svm_breast_cancer = friedmanchisquare(pred_arbol[2], pred_knn[2], pred_svm[2])
friedman_arbol_knn_svm_digits = friedmanchisquare(pred_arbol[3], pred_knn[3], pred_svm[3])

# Rangos de Friedman
rangos_friedman = [friedman_arbol_knn_svm_iris[0], friedman_arbol_knn_svm_wine[0], friedman_arbol_knn_svm_breast_cancer[0], friedman_arbol_knn_svm_digits[0]]
# Rango medio de Friedman
rango_medio_friedman = sum(rangos_friedman)/len(rangos_friedman)
# Rango medio de Friedman por dataset
rango_medio_friedman_dataset = [rango_medio_friedman, rango_medio_friedman, rango_medio_friedman, rango_medio_friedman]



# Comparamos el mejor metodo segun el rango medio de Friedman con el resto de metodos (p<0.05) usando Holm (p<0.05)
# Dataset 1
if (friedman_arbol_knn_svm_iris[0] > rango_medio_friedman_dataset[0]):
    print("El mejor metodo de clasificacion para el dataset 1 es el arbol de decision")
    print("El arbol de decision es mejor que el k-vecinos con un p-value de " + str(friedman_arbol_knn_svm_iris[1]))
    print("El arbol de decision es mejor que el SVM con un p-value de " + str(friedman_arbol_knn_svm_iris[1]))
elif (friedman_arbol_knn_svm_iris[0] < rango_medio_friedman_dataset[0]):
    print("El mejor metodo de clasificacion para el dataset 1 es el k-vecinos")
    print("El k-vecinos es mejor que el arbol de decision con un p-value de " + str(friedman_arbol_knn_svm_iris[1]))
    print("El k-vecinos es mejor que el SVM con un p-value de " + str(friedman_arbol_knn_svm_iris[1]))
else:
    print("El mejor metodo de clasificacion para el dataset 1 es el SVM")
    print("El SVM es mejor que el arbol de decision con un p-value de " + str(friedman_arbol_knn_svm_iris[1]))
    print("El SVM es mejor que el k-vecinos con un p-value de " + str(friedman_arbol_knn_svm_iris[1]))
# Dataset 2
if (friedman_arbol_knn_svm_wine[0] > rango_medio_friedman_dataset[1]):
    print("El mejor metodo de clasificacion para el dataset 2 es el arbol de decision")
    print("El arbol de decision es mejor que el k-vecinos con un p-value de " + str(friedman_arbol_knn_svm_wine[1]))
    print("El arbol de decision es mejor que el SVM con un p-value de " + str(friedman_arbol_knn_svm_wine[1]))
elif (friedman_arbol_knn_svm_wine[0] < rango_medio_friedman_dataset[1]):
    print("El mejor metodo de clasificacion para el dataset 2 es el k-vecinos")
    print("El k-vecinos es mejor que el arbol de decision con un p-value de " + str(friedman_arbol_knn_svm_wine[1]))
    print("El k-vecinos es mejor que el SVM con un p-value de " + str(friedman_arbol_knn_svm_wine[1]))
else:
    print("El mejor metodo de clasificacion para el dataset 2 es el SVM")
    print("El SVM es mejor que el arbol de decision con un p-value de " + str(friedman_arbol_knn_svm_wine[1]))
    print("El SVM es mejor que el k-vecinos con un p-value de " + str(friedman_arbol_knn_svm_wine[1]))
# Dataset 3
if (friedman_arbol_knn_svm_breast_cancer[0] > rango_medio_friedman_dataset[2]):
    print("El mejor metodo de clasificacion para el dataset 3 es el arbol de decision")
    print("El arbol de decision es mejor que el k-vecinos con un p-value de " + str(friedman_arbol_knn_svm_breast_cancer[1]))
    print("El arbol de decision es mejor que el SVM con un p-value de " + str(friedman_arbol_knn_svm_breast_cancer[1]))
elif (friedman_arbol_knn_svm_breast_cancer[0] < rango_medio_friedman_dataset[2]):
    print("El mejor metodo de clasificacion para el dataset 3 es el k-vecinos")
    print("El k-vecinos es mejor que el arbol de decision con un p-value de " + str(friedman_arbol_knn_svm_breast_cancer[1]))
    print("El k-vecinos es mejor que el SVM con un p-value de " + str(friedman_arbol_knn_svm_breast_cancer[1]))
else:
    print("El mejor metodo de clasificacion para el dataset 3 es el SVM")
    print("El SVM es mejor que el arbol de decision con un p-value de " + str(friedman_arbol_knn_svm_breast_cancer[1]))
    print("El SVM es mejor que el k-vecinos con un p-value de " + str(friedman_arbol_knn_svm_breast_cancer[1]))
# Dataset 4
if (friedman_arbol_knn_svm_digits[0] > rango_medio_friedman_dataset[3]):
    print("El mejor metodo de clasificacion para el dataset 4 es el arbol de decision")
    print("El arbol de decision es mejor que el k-vecinos con un p-value de " + str(friedman_arbol_knn_svm_digits[1]))
    print("El arbol de decision es mejor que el SVM con un p-value de " + str(friedman_arbol_knn_svm_digits[1]))
elif (friedman_arbol_knn_svm_digits[0] < rango_medio_friedman_dataset[3]):
    print("El mejor metodo de clasificacion para el dataset 4 es el k-vecinos")
    print("El k-vecinos es mejor que el arbol de decision con un p-value de " + str(friedman_arbol_knn_svm_digits[1]))
    print("El k-vecinos es mejor que el SVM con un p-value de " + str(friedman_arbol_knn_svm_digits[1]))
else:
    print("El mejor metodo de clasificacion para el dataset 4 es el SVM")
    print("El SVM es mejor que el arbol de decision con un p-value de " + str(friedman_arbol_knn_svm_digits[1]))
    print("El SVM es mejor que el k-vecinos con un p-value de " + str(friedman_arbol_knn_svm_digits[1]))