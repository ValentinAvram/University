import numpy as np
import pandas as pd
from fairlearn import preprocessing
from sklearn.metrics import confusion_matrix
from sklearn import svm
from sklearn.model_selection import GridSearchCV

data = pd.read_csv('train_spam.csv',header=None)
X_train = data.iloc[:,:-1].values
y_train = data.iloc[:,-1].values
data = pd.read_csv('test_spam.csv',header=None)
X_test = data.iloc[:,:-1].values
y_test = data.iloc[:,-1].values

svm_model = svm.SVC(kernel='linear',C=0.1)
svm_model.fit(X_train,y_train)

Cs = np.logspace(-2, 1, num=4, base=10)
Gs = np.logspace(-2, 1, num=4, base=10)

optimal = GridSearchCV(estimator=svm_model, param_grid=dict(C=Cs,gamma=Gs), n_jobs=-1, cv=5)
optimal.fit(X_train, y_train)

print(f'Accuracy: {optimal.score(X_test, y_test)}')

predict = optimal.predict(X_test)
cm = confusion_matrix(y_test,predict)
print(cm)

for i in  range (predict.shape[0]):
    if predict[i] != y_test[i]:
        print(f"Email {i + 1}, {'SPAM' if y_test[i] == 1 else 'NO SPAM'}  - Predicted:  {'SPAM' if predict[i] == 1 else 'NO SPAM'}")

        keywords = pd.read_csv('vocab.txt', header=None, sep='\t')
        keywordsIndex = np.where(data.iloc[i, :-1].values == 1)
        df = pd.DataFrame(keywords)
        keywords = df[df.columns[1]]

        for i in keywordsIndex[0]:
            print(keywords[i])