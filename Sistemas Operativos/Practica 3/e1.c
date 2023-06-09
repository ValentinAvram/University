#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

int glob = 0;
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;

void * th_fun(void *arg)
{
    int loops = *((int*) arg);
    int loc, j, s;

    for(j = 0; j < loops; j++)
    {
        s = pthread_mutex_lock(&mutex);
        if(s != 0)
        {
            printf("Error al bloquear el mutex");
            pthread_exit(NULL);
        }

        //Seccion critica
        loc = glob;
        loc ++;
        glob = loc;
        //Seccion critica
        
        printf("Hebra con ID = %lu aumentando la variable global \n", pthread_self());
        s=pthread_mutex_unlock(&mutex);

        if(s != 0)
        {
            printf("Error al desbloquear el mutex\n");
            pthread_exit(NULL);
        }
    }
    pthread_exit(NULL);
}

int main(int argc, char * argv[])
{
    pthread_t t1, t2;
    int loop, s;

    if(argc != 2)
    {
        printf("Error en el numero de argumentos. ./e1.exe numero\n");
        exit(EXIT_FAILURE);
    }

    loop = atoi(argv[1]);

    s = pthread_create(&t1, NULL, th_fun, &loop);
    if(s != 0)
    {
        printf("Error al crear la hebra 1. \n");
        exit(EXIT_FAILURE);
    }

    s = pthread_create(&t2, NULL, th_fun, &loop);
    if(s != 0)
    {
        printf("Error al crear la hebra 2. \n");
        exit(EXIT_FAILURE);
    }

    s = pthread_join(t1, NULL);
    if(s !=0)
    {
        printf("Error al recoger la hebra 1. \n");
        exit(EXIT_FAILURE);
    }

    s = pthread_join(t2, NULL);
    if(s !=0)
    {
        printf("Error al recoger la hebra 2. \n");
        exit(EXIT_FAILURE);
    }

    printf("El valor de la variable global es = %d\n", glob);
    exit(EXIT_SUCCESS);
}