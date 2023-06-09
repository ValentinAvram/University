import matplotlib.pyplot as plt
import numpy as np

def plot_rocks_and_holes(rocks, holes, path=None):
    """
    Esta función toma dos vectores de coordenadas, uno para las rocas y otro para los hoyos,
    y los representa gráficamente en un mapa. Si se proporciona un camino, también lo agrega a la gráfica.
    """
    # Crear una figura vacía
    fig, ax = plt.subplots()

    # Agregar las rocas y los hoyos al mapa como puntos
    ax.scatter(rocks[:,0], rocks[:,1], c='grey', marker='o', label='Rocas')
    ax.scatter(holes[:,0], holes[:,1], c='black', marker='x', label='Hoyos')

    # Agregar el camino al mapa como una línea
    if path is not None:
        x, y = path[:,0], path[:,1]
        ax.plot(x, y, c='blue', label='Camino')

    # Añadir etiquetas y leyenda
    ax.set_xlabel('Coordenada X')
    ax.set_ylabel('Coordenada Y')
    ax.set_title('Mapa de Rocas y Hoyos')
    ax.legend()

    # Mostrar el gráfico
    plt.show()

def vector_to_path(vector):
    """
    Esta función toma un vector de movimientos y devuelve una matriz de coordenadas
    que representa el camino resultante.
    """
    # Definir la coordenada inicial como (0, 0)
    x, y = 0, 0

    # Crear una matriz vacía para las coordenadas
    path = np.zeros((len(vector), 2))

    # Actualizar las coordenadas en función de cada movimiento
    for i, move in enumerate(vector):
        if move == 0:  # Arriba
            y += 1
        elif move == 1:  # Abajo
            y -= 1
        elif move == 2:  # Derecha
            x += 1
        elif move == 3:  # Izquierda
            x -= 1

        # Guardar las coordenadas en la matriz
        path[i] = (x, y)

    return path

vector = np.array([0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 2, 0, 0, 0, 0, 0, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 0, 3, 1, 3, 1, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 2, 2, 2, 2])
rocks = np.array([[1, 7], [4, 7], [7, 0], [11, 15], [12, 15], [13, 13], [13, 9], [17, 0], [18, 0], [19, 0]])
holes = np.array([[3, 8], [3, 7], [3, 6], [3, 5], [6, 0], [6, 1], [7, 1], [8, 1], [9, 16], [9, 14], [10, 14], [10, 16], [11, 16], [11, 14], [12, 14], [12, 16], [13, 16], [13, 14], [14, 14], [14, 16], [15, 1], [16, 1], [17, 1], [18, 1], [19, 1], [20, 0], [20, 1]])

path = vector_to_path(vector)
plot_rocks_and_holes(rocks, holes, path)