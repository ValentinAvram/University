import random
import importlib.util
import csv

spec = importlib.util.spec_from_file_location("nTreasures", "nTreasures.pyc")
nTreasures_module = importlib.util.module_from_spec(spec)
spec.loader.exec_module(nTreasures_module)

nTreasures = nTreasures_module.nTreasures

# Define the problem parameters
max_moves = 10
num_particles = 100
max_iterations = 100
num_dimensions = 68

# Define the PSO parameters
c1 = 0.1
c2 = 0.9
w = 0.9

# Define MOEA parameters
num_archive = 1
num_selected = 120
# Define the fitness function
def fitness_function(directions):
    # Evaluate fitness of a particle
    return nTreasures(directions)

def pso(max_moves, num_particles, num_dimensions, max_iterations, c1, c2, w):
    header = False

    # Define the bounds for the particle positions
    bounds = [(0, 3) for _ in range(num_dimensions)]

    # Initialize the particle positions and velocities
    particles_pos = [[random.randint(0, 3) for _ in range(num_dimensions)] for _ in range(num_particles)]
    particles_vel = [[0 for _ in range(num_dimensions)] for _ in range(num_particles)]

    # Initialize the global best position and fitness value
    global_best_pos = particles_pos[0]
    global_best_fitness = fitness_function(global_best_pos)
    particles_best_pos = particles_pos.copy()
    particles_fitness = [fitness_function(p) for p in particles_pos]

    archive = []
    for i in range(num_archive):
        archive.append([random.randint(0, 3) for _ in range(num_dimensions)])

    data = []
    last_best_positions = [particles_best_pos[0]] * 5

    # Iterate for the maximum number of iterations
    for i in range(max_iterations):

        # Update each particle's position and velocity
        for j in range(num_particles):

            # Generate new velocity
            for k in range(num_dimensions):
                r1 = random.random()
                r2 = random.random()
                vel_cognitive = c1 * r1 * (particles_best_pos[j][k] - particles_pos[j][k])
                vel_social = c2 * r2 * (global_best_pos[k] - particles_pos[j][k])
                particles_vel[j][k] = w * particles_vel[j][k] + vel_cognitive + vel_social

            # Generate new position
            new_pos = []
            for k in range(num_dimensions):
                new_pos_k = particles_pos[j][k] + int(round(particles_vel[j][k]))
                new_pos_k = max(bounds[k][0], min(bounds[k][1], new_pos_k))  # clip the position to the bounds
                new_pos.append(new_pos_k)

            # Evaluate the fitness of the new position
            new_fitness = fitness_function(new_pos)

            # Apply hill climbing to refine the solution
            while True:
                improved = False
                for k in range(num_dimensions):
                    for dx in [-1, 0, 1]:
                        if bounds[k][0] <= new_pos[k] + dx <= bounds[k][1]:
                            temp_pos = new_pos.copy()
                            temp_pos[k] += dx
                            temp_fitness = fitness_function(temp_pos)
                            if temp_fitness > new_fitness:
                                new_pos = temp_pos
                                new_fitness = temp_fitness
                                improved = True
                if not improved:
                    break

            # Update the particle's best position
            if new_fitness > particles_fitness[j]:
                particles_best_pos[j] = new_pos
                particles_fitness[j] = new_fitness

            # Update the global best position
            if new_fitness > global_best_fitness:
                global_best_pos = new_pos
                global_best_fitness = new_fitness
                last_best_positions = [global_best_pos] * 5

        # MOEA part: generate a new set of particles by clustering and recombining the current particles
        clusters = []
        cluster_centers = []
        for k in range(num_dimensions):
            values = [p[k] for p in particles_pos]
            center = sum(values) / len(values)
            cluster = [p for p in particles_pos if abs(p[k] - center) < 1]
            clusters.append(cluster)
            cluster_centers.append(center)

        new_particles_pos = []
        for _ in range(num_particles):
            # Choose two random clusters
            clusters_idxs = random.sample(range(num_dimensions), 2)
            cluster1 = clusters[clusters_idxs[0]]
            cluster2 = clusters[clusters_idxs[1]]

            # Choose two random clusters
            cluster1, cluster2 = None, None
            while cluster1 is None or cluster2 is None:
                clusters_idxs = random.sample(range(num_dimensions), 2)
                cluster1 = clusters[clusters_idxs[0]] if len(clusters[clusters_idxs[0]]) > 0 else None
                cluster2 = clusters[clusters_idxs[1]] if len(clusters[clusters_idxs[1]]) > 0 else None

            # Choose two random particles from the clusters
            particle1 = random.choice(cluster1)
            particle2 = random.choice(cluster2)

            # Recombine the particles to generate a new particle
            new_particle = [0 for _ in range(num_dimensions)]
            for k in range(num_dimensions):
                if random.random() < 0.5:
                    new_particle[k] = particle1[k]
                else:
                    new_particle[k] = particle2[k]

            new_particles_pos.append(new_particle)

        # Update the particle positions and velocities with the new particles
        particles_pos = new_particles_pos
        particles_vel = [[0 for _ in range(num_dimensions)] for _ in range(num_particles)]

        # Update the particles best positions and fitness values
        particles_best_pos = particles_pos.copy()
        particles_fitness = [fitness_function(p) for p in particles_pos]
        for j in range(num_particles):
            if particles_fitness[j] > global_best_fitness:
                global_best_pos = particles_pos[j]
                global_best_fitness = particles_fitness[j]

        # Print the current best solution
        print(f"Iteration {i + 1}, max_moves {max_moves}, num_dimentions {num_dimensions}, Best Solution = {','.join(str(x) for x in global_best_pos)}, Fitness Value = {global_best_fitness}")

        # If the first obtained fitness is less than 3000, the restart the algorithm
        if global_best_fitness < 3000:
            return pso(max_moves, num_particles, num_dimensions, max_iterations, c1, c2, w)
            break

    return global_best_pos, global_best_fitness

best_pos, best_fitness = pso(max_moves, num_particles, num_dimensions, max_iterations, c1, c2, w)