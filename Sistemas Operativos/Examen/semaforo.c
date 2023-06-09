#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <semaphore.h>

int glob = 0;
sem_t semaforo; // Declaro el semaforo

void th_func()
{
    for(int j = 0; j < 5; j++)
    {
        int s = sem_wait(&semaforo);
        if(s != 0)
        {
            printf("Error al bloquear el semaforo");
            exit(EXIT_FAILURE);
        }

        //SC
        int loc;
        glob ++;
        loc = glob;
        //SC
        
        printf("La hebra %lu ha aumentado el valor de la variable global a = %d \n", pthread_self(), loc);
        
        s = sem_post(&semaforo);
        if(s != 0)
        {
            printf("Error al desbloquear el semaforo");
            exit(EXIT_FAILURE);
        } 
    }
    pthread_exit(NULL);
}

void main (int argc, char * argv[])
{
    system("clear");
    if(argc < 2)
    {
        printf("Error en el numero de argumentos\n");
        exit(EXIT_FAILURE);
    }

    int n = atoi(argv[1]);

    int s = sem_init(&semaforo, 0, 1); 
    //Inicializo el semaforo, 0 por que no lo va a usar otro proceso, 1 su valor inicial
    if(s != 0)
    {
        printf("Error al inicializar el semaforo\n");
        exit(EXIT_FAILURE);
    }

    pthread_t threads[n];

    for(int i = 0; i < n; i++)
    {
        if(pthread_create(&(threads[i]), NULL, (void *) th_func, NULL))
        {
            printf("Error al crear la hebra \n");
            exit(EXIT_FAILURE);
        }
    }

    for(int i = 0; i < n ; i++)
    {
        if(pthread_join(threads[i], NULL))
        {
            printf("Error al recoger la hebra \n");
            exit(EXIT_FAILURE);
        }
    }

    s = sem_destroy(&semaforo);
    if(s != 0)
    {
        printf("Error al destruir el semaforo\n");
        exit(EXIT_FAILURE);
    }

    printf("El valor de la variable global es = %d\n", glob);
    exit(EXIT_SUCCESS);
}
