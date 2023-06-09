#!  /bin/python3

import pandas as pd
from apyori import apriori
import numpy as np
import matplotlib.pyplot as plt

dataset = pd.read_csv('car.csv')
print(dataset.shape)

# Transforming the list into a list of lists, so that each transaction can be indexed easier
transactions = []
for i in range(0, dataset.shape[0]):
    transactions.append([str(dataset.values[i, j]) for j in range(0, dataset.shape[1])])

rules = apriori(transactions, min_support = 0.005, min_confidence = 0.25, min_lift = 3, min_length = 2)

results = []
for item in rules:

    # first index of the inner list    # Contains base item and add item
    pair = item[0]
    items = [x for x in pair]
    print("Rule: " + items[0] + " -> " + items[1])

    # second index of the inner list
    print("Support: " + str(item[1]))

    #third index of the list located at 0th
    #of the third index of the inner list

    print("Confidence: " + str(item[2][0][2]))
    print("Lift: " + str(item[2][0][3]))
    print("=====================================")

    results.append(item)


# viewing the rules
print(len(results))

print(results.head(5))