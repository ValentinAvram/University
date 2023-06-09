#include <pthread.h> 
#include <semaphore.h>
#include <errno.h>
#include <stdio.h> 
#include <stdlib.h>
#include <string.h>

int n, res = 0;

sem_t semaforo;

void * th_func()
{
    int loc, sem;
    for(int i = 0; i < 100 ; i++)
    {
        sem = sem_wait(&semaforo);
        if(sem != 0)
        {
            printf("Error en sem_wait.\nValor de errno = %d, definido como %s\n", errno, strerror(errno));
            pthread_exit(NULL);
        }

        res += n;
        sem = sem_post(&semaforo);

        if(sem != 0)
        {
            printf("Error en sem_post.\nValor de errno = %d, definido como %s\n", errno, strerror(errno));
            pthread_exit(NULL);   
        }
    }
    pthread_exit(NULL);
}

void main(int argc, char * argv[])
{
    system("clear");
    pthread_t thread[10];
    n = atoi(argv[1]);

    int sem = sem_init(&semaforo, 0 , 1);

    if(sem != 0)
    {
        printf("Error al inicializar el semaforo.\nValor de errno = %d, definido como %s\n", errno, strerror(errno));
        pthread_exit(NULL);
    }

    for(int i = 0; i < 10; i++)
    {
        if(pthread_create(&(thread[i]), NULL, th_func, NULL))
        {
            printf("Error al crear la hebra\n");
            exit(EXIT_FAILURE);
        }
    }
        
    for(int i = 0; i < 10; i++)
    {
        if(pthread_join(thread[i], NULL))
        {
            printf("Error al recoger la hebra\n");
            exit(EXIT_FAILURE);
        }
    }

    printf("El valor final es %d, deberia ser %d\n", res, n*1000);
}