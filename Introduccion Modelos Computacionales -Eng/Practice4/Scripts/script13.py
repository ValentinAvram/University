import time
import pandas as pd
import numpy as np
from sklearn import preprocessing, svm
from sklearn.model_selection import GridSearchCV, train_test_split

dataset3 = 'test_ildp.csv'

data = pd.read_csv(dataset3, header=None)
X = data.iloc[:, :-1].values
y = data.iloc[:, -1].values
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.25, random_state=35)

for K in [3, 5, 10]:
    start = time.time()
    scaler = preprocessing.StandardScaler()

    X_train = scaler.fit_transform(X_train)
    X_test = scaler.transform(X_test)

    svm_model = svm.SVC()

    Cs = np.logspace(-4, 4, num=9, base=10)
    Gs = np.logspace(-4, 4, num=9, base=10)

    optimal = GridSearchCV(estimator=svm_model, param_grid=dict(C=Cs, gamma=Gs), n_jobs=-1, cv=K)
    optimal.fit(X_train, y_train)

    chosen_c=optimal.best_estimator_.C
    chosen_gamma=optimal.best_estimator_.gamma

    print(f"K-value: {K}")
    print(f"Best estimator: C={chosen_c}, gamma={chosen_gamma}")
    print(f"Accuracy: Train->{optimal.score(X_train, y_train)*100}% Test->{optimal.score(X_test, y_test)*100}%")
    end = time.time()
    print(f"Time: {end-start}s")
    print()
