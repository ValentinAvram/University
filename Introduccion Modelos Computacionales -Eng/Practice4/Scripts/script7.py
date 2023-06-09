import numpy as np
import matplotlib.pyplot as plt
import pandas as pd

from sklearn import svm


data = pd.read_csv('dataset3.csv', header=None)
X = data.iloc[:,:-1].values
y = data.iloc[:,-1].values

    # Train the non-linear SVM model using a RBF kernel
svm_model = svm.SVC(kernel='rbf',C=20, gamma=2)
svm_model.fit(X, y)

    # Show the points
plt.figure(1)
plt.clf()
plt.scatter(X[:, 0], X[:, 1], c=y, zorder=10, cmap=plt.cm.Paired)
    #name the plot
plt.title('SVM with C = 20 and gamma = 2')

    # Show the separating hyperplane
plt.axis('tight')
    # Extract the limit of the data to construct the mesh
x_min = X[:, 0].min()
x_max = X[:, 0].max()
y_min = X[:, 1].min()
y_max = X[:, 1].max()

    # Create the mesh and obtain the Z value returned by the SVM
XX, YY = np.mgrid[x_min:x_max:500j, y_min:y_max:500j]
Z = svm_model.decision_function(np.c_[XX.ravel(), YY.ravel()])

    # Make a plot including the margin hyperplanes (Z=-1 and Z=1) and the
    # separating hyperplane (Z=0)
Z = Z.reshape(XX.shape)
plt.pcolormesh(XX, YY, Z > 0)
plt.contour(XX, YY, Z, colors=['k', 'k', 'k'], linestyles=['--', '-', '--'],
                levels=[-1, 0, 1])

plt.show()

