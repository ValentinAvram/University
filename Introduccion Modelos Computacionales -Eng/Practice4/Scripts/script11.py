import pandas as pd
import numpy as np
from sklearn import preprocessing, svm
from sklearn.model_selection import StratifiedKFold, train_test_split

dataset3 = 'dataset3.csv'

data = pd.read_csv(dataset3, header=None)
X = data.iloc[:, :-1].values
y = data.iloc[:, -1].values
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.25, random_state=20)

scaler = preprocessing.StandardScaler()

X_train = scaler.fit_transform(X_train)
X_test = scaler.transform(X_test)

kfold = StratifiedKFold(n_splits=5, shuffle=True)

folds = list(zip(*[fold for fold in kfold.split(X_train, y_train)]))

train_sets, test_sets = folds[0], folds[1]

Cs = np.logspace(-4, 4, num=9, base=10)
Gs = np.logspace(-4, 4, num=9, base=10)

average_scores = np.zeros((len(Cs), len(Gs)))

# K-fold nested cross validation made by hand NOT MINE
for c_index, C in enumerate(Cs):
    for g_index, gamma in enumerate(Gs):
        for train_set, test_set in zip(train_sets, test_sets):
            model = svm.SVC(C=C, gamma=gamma)
            model.fit([X_train[i] for i in train_set], [y_train[i] for i in train_set])

            average_scores[c_index, g_index] += model.score([X_train[i] for i in test_set],
                                                            [y_train[i] for i in test_set])
        average_scores[c_index, g_index] /= 5

best_config = np.unravel_index(np.argmax(average_scores), average_scores.shape)
best_c = Cs[best_config[0]]
best_gamma = Gs[best_config[1]]
best_score = average_scores[best_config[0], best_config[1]]

print(f"Best configuration => C={best_c}, gamma={best_gamma}.\nTrain accuracy = {best_score * 100}%")

model = svm.SVC(C=best_c, gamma=best_gamma)
model.fit(X_train, y_train)

print(f"Test accuracy = {model.score(X_test, y_test) * 100}%")
