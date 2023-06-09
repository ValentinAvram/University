vec1= [0,0]
def shortestPath(vector, pos, holes):
    originalDistance = calculateDistance(vector, pos)
    originalPos = calcPosition(vector)
    max_iterations = len(pos) * 1000  # Condición de parada adicional para evitar bucles infinitos
    iterations = 0
    while calcPosition(vector) != pos and iterations < max_iterations:
        for i in range(3):
            vector.append(i)
            xDistance, yDistance = calculateDistance(vector, pos)

            if xDistance > 0:
                for j in range(xDistance):
                    vector.append(2)
            elif xDistance < 0:
                for j in range(-xDistance):
                    vector.append(3)

            if yDistance > 0:
                for j in range(yDistance):
                    vector.append(0)
            elif yDistance < 0:
                for j in range(-yDistance):
                    vector.append(1)

            if calcPosition(vector) == pos:
                break
            else:
                vector = vector[:len(vector) - xDistance - yDistance]

        iterations += 1
        if iterations == max_iterations:
            vector = originalPos
            break
    return vector
def algorithm(vector, vecRocks):
    for i in range(len(vecRocks)):
        vector = shortestPath(vector, vecRocks[i], holes)
    return vector


def eliminar_movimientos_contrarios(originalVector):
    i = 0
    originalFitness = fitness(originalVector)
    vector = originalVector
    vectoraux=[]
    while i < len(vector) - 1:
        if vector[i] == vector[i+1]:
            i += 1
        elif vector[i] == 0 and vector[i+1] == 1:
            vectoraux = vector
            vectoraux.pop(i)
            vectoraux.pop(i)
            i -= 1
            if (fitness(vectoraux) > originalFitness):
                originalVector = vectoraux.copy()
                originalFitness = fitness(originalVector)

        elif vector[i] == 1 and vector[i+1] == 0:
            vectoraux = vector
            vectoraux.pop(i)
            vectoraux.pop(i)
            i -= 1
            if (fitness(vectoraux) > originalFitness):
                originalVector = vectoraux.copy()
                originalFitness = fitness(originalVector)

        elif vector[i] == 2 and vector[i+1] == 3:
            vectoraux = vector
            vectoraux.pop(i)
            vectoraux.pop(i)
            i -= 1
            if (fitness(vectoraux) > originalFitness):
                originalVector = vectoraux.copy()
                originalFitness = fitness(originalVector)

        elif vector[i] == 3 and vector[i+1] == 2:
            vectoraux = vector
            vectoraux.pop(i)
            vectoraux.pop(i)
            i -= 1
            if (fitness(vectoraux) > originalFitness):
                originalVector = vectoraux.copy()
                originalFitness = fitness(originalVector)
        else:
            i += 1
    return originalVector


auxVec = algorithm(vec1, rocks)
print(calcPosition(auxVec))
auxVec = eliminar_movimientos_contrarios(auxVec)
print(calcPosition(auxVec))
print(auxVec)
print(fitness(auxVec))
print(rocks)
print(holes)