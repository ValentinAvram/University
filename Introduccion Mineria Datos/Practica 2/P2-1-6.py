from sklearn import datasets
from sklearn.tree import DecisionTreeClassifier
from sklearn.neighbors import KNeighborsClassifier
from sklearn.svm import SVC
from sklearn import metrics
from sklearn.model_selection import train_test_split
# Biblioteca de Ponferroni-Dunn

# Dataset a entrenar y testear
iris = datasets.load_iris()
wine = datasets.load_wine()
breast_cancer = datasets.load_breast_cancer()
digits = datasets.load_digits()

datasets = ['iris','wine','breast cancer','digits']

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

# Prueba de Dunn usando una correccion de Bonferroni para los valores p

p_arbol_knn = []; p_arbol_svm = []; p_knn_svm = []

# Calculamos los valores p
for i in range(4):
    p_arbol_knn.append(sp.posthoc_dunn([pred_arbol[i], pred_knn[i]], p_adjust='bonferroni'))
    p_arbol_svm.append(sp.posthoc_dunn([pred_arbol[i], pred_svm[i]], p_adjust='bonferroni'))
    p_knn_svm.append(sp.posthoc_dunn([pred_knn[i], pred_svm[i]], p_adjust='bonferroni'))

# Resultados del dataset Iris
print('Dataset Iris')
print('p_arbol_knn: \n', p_arbol_knn[0])
print('p_arbol_svm: \n', p_arbol_svm[0])
print('p_knn_svm: \n', p_knn_svm[0])

# Resultados del dataset Wine
print('Dataset Wine')
print('p_arbol_knn: \n', p_arbol_knn[1])
print('p_arbol_svm: \n', p_arbol_svm[1])
print('p_knn_svm: \n', p_knn_svm[1])

# Resultados del dataset Breast Cancer
print('Dataset Breast Cancer')
print('p_arbol_knn: \n', p_arbol_knn[2])
print('p_arbol_svm: \n', p_arbol_svm[2])
print('p_knn_svm: \n', p_knn_svm[2])

# Resultados del dataset Digits
print('Dataset Digits')
print('p_arbol_knn: \n', p_arbol_knn[3])
print('p_arbol_svm: \n', p_arbol_svm[3])
print('p_knn_svm: \n', p_knn_svm[3])