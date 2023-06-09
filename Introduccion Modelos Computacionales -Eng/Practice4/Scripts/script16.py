import pandas as pd
from sklearn import svm

# Cargar el dataset
data = pd.read_csv('train_spam.csv',header=None)
X_train = data.iloc[:,:-1].values
y_train = data.iloc[:,-1].values
data = pd.read_csv('test_spam.csv',header=None)
X_test = data.iloc[:,:-1].values
y_test = data.iloc[:,-1].values

# Entrenar el modelo SVM
for x in [.01,.1,1,10]:
  svm_model = svm.SVC(kernel='linear',C=x)
  svm_model.fit(X_train,y_train)
  print("C-value: ",x)
  print("Accuracy: ",svm_model.score(X_test,y_test))
  print()