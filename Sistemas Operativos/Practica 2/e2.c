#include <stdlib.h>
#include <stdio.h>
#include <pthread.h>

void * th_file(void * argv)
{
    int *nlines = malloc(sizeof(int));
    *nlines = 0;

    char const *fname;
    char aux[256];

    fname = (char*) argv;
    FILE * f = fopen(fname, "r");

    if(f == NULL)
    {
        printf("Error al abrir el archivo\n");
        exit(EXIT_FAILURE);
    }

    while(fgets(aux,256,f)!= NULL)
    {
        ++*nlines;
    }

    fclose(f);
    pthread_exit((void*) nlines);
}

void main(int argc, char const * argv[])
{

    system("clear");

    if(argc < 2)
    {
        printf("Error en el numero de argumentos, ./main.exe fichero1 fichero2\n");
        exit(EXIT_FAILURE);
    }

    int n = (argc-1);
    pthread_t thread[n];
    int *ret, suma=0;

    for(int i = 0; i < n; i++)
    {
        if(pthread_create(&(thread[i]), NULL, (void *)th_file, (void*) argv[i+1]))
        {
            printf("Error al crear las hebras \n");
            exit(EXIT_FAILURE);
        }
    }

    for(int i = 0; i < n; i++)
    {
        if(pthread_join(thread[i], (void *) &ret))
        {
            printf("Error al recoger las hebras \n");
            exit(EXIT_FAILURE);
        }
        suma += *ret;
    }


    printf("El numero de lineas es = %d \n", suma);
    exit(EXIT_SUCCESS);

}