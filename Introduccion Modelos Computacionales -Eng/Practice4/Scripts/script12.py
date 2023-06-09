import pandas as pd
import numpy as np
from sklearn import preprocessing, svm
from sklearn.model_selection import GridSearchCV, train_test_split

dataset3='train_ildp.csv'

data = pd.read_csv(dataset3, header=None)

#without doing the split
X = data.iloc[:, :-1].values
y = data.iloc[:, -1].values

scaler = preprocessing.StandardScaler()

X = scaler.fit_transform(X)
svm_model = svm.SVC()

Cs = np.logspace(-4, 4, num=9, base=10)
Gs = np.logspace(-4, 4, num=9, base=10)

optimal=GridSearchCV(estimator=svm_model, param_grid=dict(C=Cs,gamma=Gs), n_jobs=2, cv=5)

optimal.fit(X, y)

chosen_c=optimal.best_estimator_.C
chosen_gamma=optimal.best_estimator_.gamma

print(f"Best estimator: C={chosen_c}, gamma={chosen_gamma}")

print(f"Accuracy: Train->{optimal.score(X, y)*100}%")
