from matplotlib import pyplot as plt
from sklearn.datasets import load_breast_cancer
from sklearn.model_selection import train_test_split
from sklearn.neighbors import KNeighborsClassifier
from sklearn.preprocessing import Binarizer
from sklearn.tree import DecisionTreeClassifier

# Cargamos el archivo breast cancer
breast_cancer = load_breast_cancer()
X_breast_cancer = breast_cancer.data
y_breast_cancer = breast_cancer.target
# Modelos de clasificacion
arbol = DecisionTreeClassifier()
arbol_accuracy = []
knn = KNeighborsClassifier(n_neighbors=3)
knn_accuracy = []
umbral = []
# Split data into training and test sets
X_train, X_test, y_train, y_test = train_test_split(X_breast_cancer, y_breast_cancer, test_size=0.1, random_state=0)

# Estudiamos el efecto de la binarizacion en el modelo de KNN
for i in range(1, 10):
    # Creamos el modelo de binarizacion
    binarizer = Binarizer(threshold=i / 10)
    # Binarizamos los datos de entrenamiento
    X_train_binarized = binarizer.fit_transform(X_train)
    # Binarizamos los datos de test
    X_test_binarized = binarizer.fit_transform(X_test)
    # Creamos el modelo de KNN
    knn.fit(X_train_binarized, y_train)
    # Creamos el modelo de arbol
    arbol.fit(X_train_binarized, y_train)
    # Calculamos el accuracy del modelo de KNN
    knn_accuracy.append(knn.score(X_test_binarized, y_test))
    # Calculamos el accuracy del modelo de arbol
    arbol_accuracy.append(arbol.score(X_test_binarized, y_test))

    # Guardamos el valor de la binarizacion
    umbral.append(i / 10)

print("Accuracy KNN:", knn_accuracy)
print("Accuracy Arbol:", arbol_accuracy)

# Creamos el grafico de accuracy
plt.clf()
plt.plot(umbral, arbol_accuracy, label='Arbol Accuracy')
plt.plot(umbral, knn_accuracy, label='knn_accuracy')
plt.xlabel('Umbral')
plt.ylabel('Accuracy')
plt.title("Feature Binarization")
plt.legend()
plt.show()