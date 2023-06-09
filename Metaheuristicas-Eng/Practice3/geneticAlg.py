import random
import importlib.util
import csv

# Define the genetic algorithm parameters
POPULATION_SIZE = 150
GENE_LENGTH = 70
MUTATION_RATE = 0.15
GENERATIONS = 500


# Define the genetic algorithm functions
def create_individual():
    return bestVector
    # Create a random individual with GENE_LENGTH genes
    # return [0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 2, 0, 0, 3, 0, 0, 0, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 2, 3, 2, 0, 1, 2, 3, 0, 0, 0, 1, 0, 1, 3, 1, 3, 3, 1, 1, 3, 1, 1, 2, 3, 1, 1, 1, 1, 2, 3, 2, 1, 2, 1, 1, 1, 1, 2, 2, 2, 2, 2]


def calculate_fitness(population):
    # Calculate the fitness of each individual in the population
    fitness_values = []
    for individual in population:
        fitness_values.append(nTreasures(individual))
    return fitness_values


def select_parents(population, fitness_values):
    # Select two parents based on fitness using rank selection
    parent1, parent2 = rank_selection(population, fitness_values)
    return parent1, parent2


def crossover(parent1, parent2):
    crossover_points = sorted(random.sample(range(len(parent1)), 2))
    child1 = parent1.copy()
    child2 = parent2.copy()
    child1[crossover_points[0]:crossover_points[1]] = parent2[crossover_points[0]:crossover_points[1]]
    child2[crossover_points[0]:crossover_points[1]] = parent1[crossover_points[0]:crossover_points[1]]
    return [child1, child2]


def mutate(individual):
    # Avoid mutating the first and last genes
    mutation_range = range(10, len(individual) - 1)

    # Choose a mutation type at random: replace, insert or delete
    mutation_type = random.choice(['replace', 'insert', 'delete'])

    # Replace a gene with a new one
    if mutation_type == 'replace':
        index = random.choice(mutation_range)
        individual[index] = random.randint(0, 3)

    # Insert a new gene
    elif mutation_type == 'insert':
        # If the individual is already at the maximum length, delete a gene first
        if len(individual) >= 75:
            index = random.choice(mutation_range)
            del individual[index]
        # Insert a new gene at a random position
        else:
            index = random.choice(range(len(individual) + 1))
            individual.insert(index, random.randint(0, 3))

    # Delete a gene
    elif mutation_type == 'delete':
        # If the individual is already at the minimum length, insert a new gene first
        if len(individual) <= 65:
            index = random.choice(range(1, len(individual)))
            individual.insert(index, random.randint(0, 3))
        # Delete a gene at a random position
        else:
            index = random.choice(mutation_range)
            del individual[index]

    return individual


def rank_selection(population, fitness_values):
    # Sort the population by fitness
    sorted_population = [individual for _, individual in
                         sorted(zip(fitness_values, population), key=lambda x: x[0], reverse=True)]

    # Calculate the rank-based probabilities
    probabilities = [(2.0 - (2.0 - 1.0) * (i - 1) / (POPULATION_SIZE - 1)) / POPULATION_SIZE for i in
                     range(1, POPULATION_SIZE + 1)]

    # Select two parents based on rank-based probabilities
    parent1 = random.choices(sorted_population, weights=probabilities)[0]
    parent2 = random.choices(sorted_population, weights=probabilities)[0]

    return parent1, parent2


# Define the genetic algorithm
# Define the genetic algorithm
def genetic_algorithm(elitism=True, elite_size=2):
    # Create an initial population
    population = [create_individual() for _ in range(POPULATION_SIZE)]

    # Evolve the population for GENERATIONS generations
    for generation in range(GENERATIONS):
        # Calculate the fitness of the population
        fitness_values = calculate_fitness(population)

        # Select the parents and perform crossover and mutation to create a new population
        new_population = []

        if elitism:
            # Add the elite individuals from the previous generation directly to the new population
            elite_individuals = [population[i] for i in
                                 sorted(range(len(fitness_values)), key=lambda k: fitness_values[k], reverse=True)[
                                 :elite_size]]
            new_population.extend(elite_individuals)

        # Create the remaining individuals using crossover and mutation
        for _ in range((POPULATION_SIZE - elite_size) // 2):
            parent1, parent2 = select_parents(population, fitness_values)
            child1, child2 = crossover(parent1, parent2)
            if random.random() < MUTATION_RATE:
                child1 = mutate(child1)
            if random.random() < MUTATION_RATE:
                child2 = mutate(child2)
            new_population.append(child1)
            new_population.append(child2)

        # Replace the old population with the new population
        population = new_population

    # Select the best individual from the final population
    best_individual = max(population, key=nTreasures)

    # Return the best individual and its fitness value
    return best_individual, nTreasures(best_individual)


# Run the genetic algorithm and print the result
bestFitness = 0
bestVector = []
for i in range(50):
    best_individual, fitness_value = genetic_algorithm()
    if fitness_value > bestFitness:
        bestFitness = fitness_value
        bestVector = best_individual
print("\n\nBest individual: ", bestVector)
print("Fitness value: ", bestFitness)