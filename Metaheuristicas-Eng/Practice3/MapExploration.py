import numpy as np
import copy
import importlib.util
import random

spec = importlib.util.spec_from_file_location("nTreasures", "nTreasures.pyc")
nTreasures_module = importlib.util.module_from_spec(spec)
spec.loader.exec_module(nTreasures_module)

nTreasures = nTreasures_module.nTreasures

def fitness(solVector):
    fitValue = nTreasures(solVector)
    return fitValue

def partial_fitness(solVector, end):
    partial_sol = solVector[:end]
    fitValue = nTreasures(partial_sol)
    return fitValue

def calcPosition(vector):
    position = [0, 0]
    for i in range(len(vector)):
        if vector[i] == 0:
            position[1] += 1
        elif vector[i] == 1:
            position[1] -= 1
        elif vector[i] == 2:
            position[0] += 1
        elif vector[i] == 3:
            position[0] -= 1
    return position

mapSize = 25

def findRocks():
    rocks = []
    holes = []
    vectorExp = []
    for i in range(mapSize + 1):
        if i % 2 == 0:
            for j in range(mapSize + 1):
                vectorExp.append(0)
            vectorExp.append(2)
        else:
            for j in range(mapSize + 1):
                vectorExp.append(1)
            vectorExp.append(2)

    prevFit = 0
    for i in range(len(vectorExp)):
        fit = partial_fitness(vectorExp, i)
        if fit > prevFit and i > 0:
            rocks.append(calcPosition(vectorExp[:i]))
        elif fit == prevFit - 1010 and i > 0:
            holes.append(calcPosition(vectorExp[:i]))
        prevFit = fit
    return rocks, holes

def calculateDistance(vector, pos):
    actualPos = calcPosition(vector)
    xDifference = (pos[0] - actualPos[0])
    yDifference = (pos[0] - actualPos[0])
    return xDifference, yDifference

rocks,holes = findRocks()

print("nRocks: ",len(rocks))
print("nHoles: ",len(holes))
print(rocks)
print(holes)