#include <stdio.h> 
#include <pthread.h> 
#include <semaphore.h>

#define DATOS_A_ESCRIBIR_LEER 5  
#define NUM_LECTORES 2 
#define NUM_ESCRITORES 2 

int dato = 0; 
int n_lectores = 0; 
sem_t sem_lec; 
sem_t mutex; 


void * Lector(void * arg) 
{
    int i=0;
    for(i=0; i < DATOS_A_ESCRIBIR_LEER; i++ ) 
    {
        sem_wait(&sem_lec); 
        n_lectores = n_lectores + 1; 
        if (n_lectores == 1)
        {
            sem_wait(&mutex); 
        }
        sem_post(&sem_lec);
            
        printf("Lector %lu, valor leido = %d\n", (unsigned long)pthread_self(), dato); 
        
        sem_wait(&sem_lec); 
        
        n_lectores = n_lectores - 1; 
        if (n_lectores == 0)
        { 
            sem_post(&mutex); 
        }
        sem_post(&sem_lec);        
    }  
    pthread_exit(NULL); 
}

void * Escritor(void * arg) 
{
    int i=0;
    for(i=0; i < DATOS_A_ESCRIBIR_LEER; i++ ) 
    {
        sem_wait(&mutex);
        
        dato = dato + 1; 
        printf("Escritor %lu, incrementando a valor = %d\n", (unsigned long) pthread_self(), dato); 
        
        sem_post(&mutex);
    }
    pthread_exit(NULL); 
}


int main(void) 
{
    int i;
    pthread_t thlectores[NUM_LECTORES], thescritores[NUM_ESCRITORES];
    sem_init(&mutex, 0, 1);  
    sem_init(&sem_lec, 0, 1); 
    
    for(i=0; i < NUM_LECTORES; i++ )
    { 
        pthread_create(&thlectores[i], NULL, Lector, NULL); 
    }
    
    for(i=0; i < NUM_ESCRITORES; i++ )
    { 
        pthread_create(&thescritores[i], NULL, Escritor, NULL); 
    }

    for(i=0; i < NUM_LECTORES; i++ )
    {
        pthread_join(thlectores[i], NULL);
    }

    for(i=0; i < NUM_ESCRITORES; i++ ) 
    {
        pthread_join(thescritores[i], NULL);
    }

    sem_destroy(&mutex); 
    sem_destroy(&sem_lec);
}