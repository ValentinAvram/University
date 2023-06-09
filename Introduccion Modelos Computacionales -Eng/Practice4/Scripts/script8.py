import pandas as pd
import numpy as np
from sklearn import preprocessing, model_selection, svm

#Load the data
dataset3='dataset3.csv'
data = pd.read_csv(dataset3, header=None)
X = data.iloc[:, :-1].values
y = data.iloc[:, -1].values

#Split the data into training and testing sets
X_train, X_test, y_train, y_test = model_selection.train_test_split(X, y, test_size=0.25, random_state=20)

#Train the model
scaler = preprocessing.StandardScaler()
X_train = scaler.fit_transform(X_train)
X_test = scaler.transform(X_test)

#Train a linear and non-linear SVM model, finding the best parameters
configs = [(c_exp, g_exp) for c_exp in range(-2, 5) for g_exp in range(-2, 5)]
acc_linear = []
acc_rbf = []
for c_exp, g_exp in configs:
    linear_model = svm.SVC(kernel='linear', C=10 ** c_exp)
    rbf_model = svm.SVC(C=10 ** c_exp, gamma=10 ** g_exp)

    linear_model.fit(X_train, y_train)
    rbf_model.fit(X_train, y_train)

    acc_linear.append(linear_model.score(X_test, y_test))
    acc_rbf.append(rbf_model.score(X_test, y_test))

best_linear_config = configs[np.argmax(acc_linear)]
best_linear_score = acc_linear[np.argmax(acc_linear)]

best_rbf_config = configs[np.argmax(acc_rbf)]
best_rbf_score = acc_rbf[np.argmax(acc_rbf)]

print(f"Best linear configuration: C=10^{best_linear_config[0]} - Score: {best_linear_score}")
print(
    f"Best RBF configuration: C=10^{best_rbf_config[0]}, gamma=10^{best_rbf_config[1]} - Score: {best_rbf_score}")
