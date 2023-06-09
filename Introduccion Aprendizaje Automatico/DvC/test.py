import numpy as np
import pandas as pd
from scipy import stats
from scipy.stats import ttest_1samp, wilcoxon, ttest_ind, mannwhitneyu
from statsmodels.formula.api import ols
from sklearn.metrics import confusion_matrix
import numpy as np
from scipy.stats import chi2_contingency
import matplotlib.pyplot as plt
import seaborn as sns


df = pd.read_csv('traniningData.csv', sep=',')

# Vector que almacene los valores de trainAccuracy cuando el ModelName sea X
trainAccModelo1 = df[df['ModelName'] == 'modelo1']['trainAccuracy'].values
trainAccModelo3 = df[df['ModelName'] == 'modelo3']['trainAccuracy'].values
trainAccModelo4 = df[df['ModelName'] == 'modelo4']['trainAccuracy'].values
trainAccModelo5VGG16 = df[df['ModelName'] == 'modelo5VGG16']['trainAccuracy'].values
trainAccModelResNet50 = df[df['ModelName'] == 'modelResNet50']['trainAccuracy'].values

## Tests de 1 v 1
## Test T de Stundent

t_statistic, p_value = stats.ttest_ind(trainAccModelo1,trainAccModelo3)
print("\nModelo 1 vs Modelo 3. T-Student en trainAccuracy")
print("t: ", t_statistic, " p-value: ", p_value)

## Hacer los demás T-Student ...


## Test de Wilcoxon
resultWilcoxon = stats.wilcoxon(trainAccModelo1,trainAccModelo3)
print("\nModelo 1 vs Modelo 3. Wilcoxon en trainAccuracy")
print(resultWilcoxon)

## Hacer los demás Wilcoxon ...


## Todos vs Todos
## Test de ANOVA. Puede que sea inutil por que los datos no siguen una distribucion normal, no se?
F_statistic, p_value = stats.f_oneway(trainAccModelo1, trainAccModelo3, trainAccModelo4, trainAccModelo5VGG16, trainAccModelResNet50)

print("\nANOVA")
print("F: ", F_statistic, " p-value: ", p_value)

## Test de Friedman Iman-Davenport
resultsFriedman = stats.friedmanchisquare(trainAccModelo1, trainAccModelo3, trainAccModelo4, trainAccModelo5VGG16, trainAccModelResNet50)
print("\nFriedman Iman-Davenport")
print(resultsFriedman)

## Test de Nemenyi
## Pedirselo a JoseMaria


## Test de Bonferroni-Dunn
## Se haría con la librería Orange, pero no funciona en mi ordenador. Dejo el código por si acaso.
# Si no funciona, mejor ignorarlo
# accuracies = [0.85, 0.89, 0.92, 0.88, 0.90]

#p_values = Orange.evaluation.compute_CD(accuracies, len(accuracies))

# Imprimir los p-valor de las comparaciones
#print("P-valor para las comparaciones:")
#for i in range(len(accuracies)):
#    for j in range(i+1, len(accuracies)):
#        print("Modelo %d vs. Modelo %d: %.4f" % (i+1, j+1, p_values[i][j]))


## Test de McNemar -> Una vez hechas las predicciones. SE NECESITA FASE DEL TESTING @ANGEL
## NOTA: Para hacer McNemar, todos los modelos deberian probarse A MANO. Escoger 10-15 fotos y probar los modelos
## con LAS MISMAS FOTOS, pasar info a un vector y hacer McNemar con ese vector.

# Vector de etiquetas verdaderas y predichas de los modelos A y B
## CAMBIAR POR LOS DATOS REALES

y_true = np.array([1, 0, 1, 1, 0, 0, 1, 0, 1, 0])
y_pred_A = np.array([1, 1, 0, 1, 0, 0, 1, 0, 1, 0])
y_pred_B = np.array([1, 0, 1, 1, 0, 1, 1, 0, 1, 0])

# Crear tabla de contingencia
table = confusion_matrix(y_true, y_pred_A)
print("\nMatriz de confusión para modelo A:")
print(table)

# Calcular estadístico y p-valor de la prueba de McNemar
stat, p_value = chi2_contingency(table, correction=False)[:2]
print("Estadístico de prueba: %.2f" % stat)
print("p-valor: %.4f" % p_value)

# Represnetar gráficamente la matriz de confusión del modelo A
plt.figure(figsize=(5, 5))
sns.heatmap(table, annot=True, cmap="YlGnBu", fmt="d", cbar=False)
plt.title("Matriz de confusión para modelo A")
plt.show()

## Añadir el resto de McNemars ...

