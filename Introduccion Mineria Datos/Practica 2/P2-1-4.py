import pandas as pd
import sns as sns
from sklearn import datasets
import matplotlib.pyplot as plt
# Arbol de decision
from sklearn.tree import DecisionTreeClassifier
# k-vecinos
from sklearn.neighbors import KNeighborsClassifier
# SVM
from sklearn.svm import SVC
# Wilcoxon test para comparar dos metodos de clasificacion en N datasets
from scipy.stats import wilcoxon
from sklearn.model_selection import train_test_split

# Test de Friedman para comparar tres metodos de clasificacion en N datasets
from scipy.stats import friedmanchisquare

# Biblioteca test Iman-Davenport: mannwhitneyu
from scipy.stats import mannwhitneyu

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

# Test de Wilcoxon para comparar dos metodos de clasificacion en N datasets
# Comparacion arbol de decision y k-vecinos

datasets = ['iris', 'wine', 'breast_cancer', 'digits']
resultadosWilcoxon = []
resultadosFriedman = []
resultadosMannWhitney = []

# Almacenamos los resultados de la comparacion
wilcoxon_arbol_knn_iris = wilcoxon(pred_arbol[0], pred_knn[0])
wilcoxon_arbol_knn_wine = wilcoxon(pred_arbol[1], pred_knn[1])
wilcoxon_arbol_knn_breast_cancer = wilcoxon(pred_arbol[2], pred_knn[2])
wilcoxon_arbol_knn_digits = wilcoxon(pred_arbol[3], pred_knn[3])
resultadosWilcoxon.append([wilcoxon_arbol_knn_iris, wilcoxon_arbol_knn_wine, wilcoxon_arbol_knn_breast_cancer, wilcoxon_arbol_knn_digits])

# Test de Friedman para comparar tres metodos de clasificacion en N datasets
# Comparacion arbol de decision, k-vecinos y SVM

# Almacenamos los resultados de la comparacion
friedman_arbol_knn_svm_iris = friedmanchisquare(pred_arbol[0], pred_knn[0], pred_svm[0])
friedman_arbol_knn_svm_wine = friedmanchisquare(pred_arbol[1], pred_knn[1], pred_svm[1])
friedman_arbol_knn_svm_breast_cancer = friedmanchisquare(pred_arbol[2], pred_knn[2], pred_svm[2])
friedman_arbol_knn_svm_digits = friedmanchisquare(pred_arbol[3], pred_knn[3], pred_svm[3])

# Generamos un grafico con los resultados del test de Friedman
plt.figure()
# Titulo de la figura
plt.title("Friedman: arbol de decision, k-vecinos y SVM")
# Etiquetas de los ejes
plt.xlabel("Dataset")
plt.ylabel("P-value")
# Datos de los ejes
plt.plot(["Iris", "Wine", "Breast cancer", "Digits"], [friedman_arbol_knn_svm_iris[1], friedman_arbol_knn_svm_wine[1], friedman_arbol_knn_svm_breast_cancer[1], friedman_arbol_knn_svm_digits[1]], 'ro')
# Guardamos la figura en el directorio images
plt.show()
# Limpiamos la imagen
plt.clf()

# Test de Iman-Davenport para comparar tres metodos de clasificacion en N datasets
# Comparacion arbol de decision, k-vecinos y SVM

# Almacenamos los resultados de la comparacion
imandavenport_arbol_knn_svm_iris = mannwhitneyu(pred_arbol[0], pred_knn[0])
imandavenport_arbol_knn_svm_wine = mannwhitneyu(pred_arbol[1], pred_knn[1])
imandavenport_arbol_knn_svm_breast_cancer = mannwhitneyu(pred_arbol[2], pred_knn[2])
imandavenport_arbol_knn_svm_digits = mannwhitneyu(pred_arbol[3], pred_knn[3])

# Generamos un grafico con los resultados del test de Iman-Davenport
plt.figure()
# Titulo de la figura
plt.title("Iman-Davenport: arbol de decision, k-vecinos y SVM")
# Etiquetas de los ejes
plt.xlabel("Dataset")
plt.ylabel("P-value")
# Datos de los ejes
plt.plot(["Iris", "Wine", "Breast cancer", "Digits"], [imandavenport_arbol_knn_svm_iris[1], imandavenport_arbol_knn_svm_wine[1], imandavenport_arbol_knn_svm_breast_cancer[1], imandavenport_arbol_knn_svm_digits[1]], 'ro')
# Guardamos la figura en el directorio images
plt.show()
# Limpiamos la imagen
plt.clf()

#Print de los resultados de los tests
print("\nWilcoxon: arbol de decision y k-vecinos")
print("Iris: ", wilcoxon_arbol_knn_iris)
print("Wine: ", wilcoxon_arbol_knn_wine)
print("Breast cancer: ", wilcoxon_arbol_knn_breast_cancer)
print("Digits: ", wilcoxon_arbol_knn_digits)

print("\nFriedman: arbol de decision, k-vecinos y SVM")
print("Iris: ", friedman_arbol_knn_svm_iris)
print("Wine: ", friedman_arbol_knn_svm_wine)
print("Breast cancer: ", friedman_arbol_knn_svm_breast_cancer)
print("Digits: ", friedman_arbol_knn_svm_digits)

print("\nIman-Davenport: arbol de decision, k-vecinos y SVM")
print("Iris: ", imandavenport_arbol_knn_svm_iris)
print("Wine: ", imandavenport_arbol_knn_svm_wine)
print("Breast cancer: ", imandavenport_arbol_knn_svm_breast_cancer)
print("Digits: ", imandavenport_arbol_knn_svm_digits)


# Plot del rango de Friedman
plt.figure()
# Titulo de la figura
plt.title("Rango de Friedman: arbol de decision, k-vecinos y SVM")
# Etiquetas de los ejes
plt.xlabel("Dataset")
plt.ylabel("Rango")
# Datos de los ejes
plt.plot(["Iris", "Wine", "Breast cancer", "Digits"], [friedman_arbol_knn_svm_iris[0], friedman_arbol_knn_svm_wine[0], friedman_arbol_knn_svm_breast_cancer[0], friedman_arbol_knn_svm_digits[0]], 'ro')
# Guardamos la figura en el directorio images
plt.show()