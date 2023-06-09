#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

int main(int argc, char * const argv[])
{
    if(argc != 2)
    {
        printf("Error en el numero de arguentos en la funcion del factorial!");
    }
    int res = 1, n = atoi(argv[1]);

    for(int i = n; i > 0; i--)
    {
        sleep(1);
        printf("%d\n", res);
        res *= i;
    }

    printf("El resultado del factorial de %d es %d\n\n", n, res);
    return res;
}