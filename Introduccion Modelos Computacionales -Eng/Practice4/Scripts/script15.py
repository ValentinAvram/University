from collections import Counter

import numpy as np
import pandas as pd


from sklearn import svm
from sklearn.model_selection import GridSearchCV
from fairlearn.metrics import MetricFrame
from fairlearn.metrics import false_negative_rate
from fairlearn.metrics import false_positive_rate
from sklearn.metrics import recall_score

trainData = pd.read_csv('train_ildp.csv', header=None)
testData = pd.read_csv('test_ildp.csv', header=None)

x_train = trainData.iloc[:,:-1].values
y_train = trainData.iloc[:,-1].values
x_test = testData.iloc[:,:-1].values
y_test = testData.iloc[:,-1].values

Cs = [0.001, 0.01, 0.1, 1, 10, 100, 1000]
Gs = np.logspace(-3, 3, num=7, base=2)

svmModel = svm.SVC(kernel='rbf',C=100, gamma=1)
svmModel.fit(x_train, y_train)

optimal = GridSearchCV(estimator=svmModel, param_grid=dict(C=Cs, gamma=Gs), n_jobs=-1, cv=3)
optimal.fit(x_train, y_train)

print("The best parameters are %s with a score of %0.2f" % (optimal.best_params_, optimal.best_score_))
print("Minority label of the dataset:", Counter(y_train).most_common()[-1][0])

lu = np.unique(x_train[:, -1])
train_gender_bin = np.zeros(trainData.iloc[:,-1].shape)
train_gender_bin[ x_train[:,-1] == lu[1]] = 1
test_gender_bin = np.zeros(testData.iloc[:,-1].shape)
test_gender_bin[ x_test[:,-1] == lu[1]] = 1

metrics = {
    'false positive rate': false_positive_rate,
    'false negative rate': false_negative_rate
}

testMetrics = MetricFrame(metrics=metrics, y_true=y_test, y_pred=optimal.predict(x_test), sensitive_features=test_gender_bin)

print('Overall test FM: ')
print(testMetrics.overall)
print('Group test FM:')
print(testMetrics.by_group)
print('Accuracy: ', optimal.score(x_test, y_test))

print('recall: ', recall_score(y_test, optimal.predict(x_test), average=None))