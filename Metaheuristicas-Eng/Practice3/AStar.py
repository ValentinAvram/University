import math


def euclidean_distance(p1, p2):
    return math.sqrt((p1[0] - p2[0]) ** 2 + (p1[1] - p2[1]) ** 2)


def get_neighbors(current, rocks, holes):
    neighbors = []
    for r in rocks:
        if r != current and r not in holes:
            neighbors.append(r)
    return neighbors


def a_star(start, end, rocks, holes):
    g_scores = {start: 0}
    f_scores = {start: euclidean_distance(start, end)}
    open_set = set([start])
    closed_set = set()
    came_from = {}

    while len(open_set) > 0:
        current = min(open_set, key=lambda x: f_scores[x])
        if current == end:
            path = [current]
            while current in came_from:
                current = came_from[current]
                path.append(current)
            return list(reversed(path))

        open_set.remove(current)
        closed_set.add(current)

        for neighbor in get_neighbors(current, rocks, holes):
            if neighbor in closed_set:
                continue

            tentative_g_score = g_scores[current] + euclidean_distance(current, neighbor)
            if neighbor not in open_set:
                open_set.add(neighbor)
            elif tentative_g_score >= g_scores[neighbor]:
                continue

            came_from[neighbor] = current
            g_scores[neighbor] = tentative_g_score
            f_scores[neighbor] = tentative_g_score + euclidean_distance(neighbor, end)


    return None


start = (0, 0)
end = rocks[-1]
path = a_star(start, end, rocks, holes)
print(path)



start = (0, 0)
vectorPath = []
for i in range(len(rocks)):
    end = rocks[i]
    path = a_star(start, end, rocks, holes)
    vectorPath.append(path)
    start = end

print(vectorPath)

def translatePath(vectorPath):
    vector = []
    # Delete repeated points
    for i in range(len(vectorPath)):
        for j in range(len(vectorPath[i])):
            if vectorPath[i][j] not in vector:
                vector.append(vectorPath[i][j])

    # Create the path

    path = []
    for i in range(len(vector)):
        pointX = vector[i][0]
        pointY = vector[i][1]
        if i == 0:
            diffX = pointX
            diffY = pointY
            for j in range(pointX):
                path.append(2)
            for j in range(pointY):
                path.append(0)
        else:
            diffX = pointX - vector[i-1][0]
            diffY = pointY - vector[i-1][1]
            if diffX > 0:
                for j in range(diffX):
                    path.append(2)
            elif diffX < 0:
                for j in range(abs(diffX)):
                    path.append(3)
            if diffY > 0:
                for j in range(diffY):
                    path.append(0)
            elif diffY < 0:
                for j in range(abs(diffY)):
                    path.append(1)
    return path

print(fitness(translatePath(vectorPath)))