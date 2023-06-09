#include "funcionesObligatorias.hpp"
#include "reloj.cpp"
#include "stdlib.h"
#include "time.h"
using namespace std;

//METODO 1
void metodo1()
{
    int n = 0;

    cout << "Introduzca el número de reinas: ";
    cin >> n;

    vector<Tablero> soluciones;

    solucionesNReinas(n, soluciones);

    mostrarSoluciones(soluciones);
}

void solucionesNReinas(int n, vector<Tablero> &soluciones)
{
    int numeroSoluciones = 0;
    vector<int> solucionActual(n + 1);

    solucionActual[0] = 1;

    int columna = 1;

    while(columna > 0)
    {
        solucionActual[columna]++;
        while((solucionActual[columna] <= n) && !esValida(solucionActual, columna))
        {
            solucionActual[columna]++;
        }

        if(solucionActual[columna] <= n)
        {
            if(columna == n)
            {
                numeroSoluciones++;
                Tablero solucion;
                solucion.numSoluciones = numeroSoluciones;
                solucion.solucionActual = solucionActual;
                soluciones.push_back(solucion);
            }
            else
            {
                columna++;
                solucionActual[columna] = 0;
            }
        }
        else
        {
            columna--;
        }
    }
}

//METODO 2

void metodo2()
{
    int n = 0;

    cout << "Introduzca el número de reinas: ";
    cin >> n;

    vector<Tablero> soluciones;

    Clock reloj;
    double tiempoEjecucion;
    
    reloj.start();
    solucionUnicaNReinas(n, soluciones);
    if(reloj.isStarted())
    {
        reloj.stop();
        tiempoEjecucion = reloj.elapsed();
    }

    mostrarSoluciones(soluciones);

    cout << "Tiempo de ejecución: " << tiempoEjecucion << " microsegundos" << endl;
}

void solucionUnicaNReinas(int n, vector<Tablero> &soluciones)
{
    vector<int> solucionActual(n + 1);

    solucionActual[0] = 1;
    bool encontrada = false;

    int columna = 1;

    while(columna > 0 && encontrada == false)
    {
        solucionActual[columna]++;
        while((solucionActual[columna] <= n) && !esValida(solucionActual, columna))
        {
            solucionActual[columna]++;
        }

        if(solucionActual[columna] <= n)
        {
            if(columna == n)
            {
                Tablero solucion;
                solucion.numSoluciones = 1;
                solucion.solucionActual = solucionActual;
                soluciones.push_back(solucion);
                columna--;
                encontrada = true;
            }
            else
            {
                columna++;
                solucionActual[columna] = 0;
            }
        }
        else
        {
            columna--;
        }
    }
}

//METODO3
void metodo3()
{
    int n = 0;
    int intentos = 0;
    bool encontrado = false;

    cout << "Introduzca el número de reinas: ";
    cin >> n;

    vector<Tablero> soluciones;

    Clock reloj;
    double tiempoEjecucion;

    srand(time(NULL));

    reloj.start();
    
    while(encontrado == false)
    {
        intentos++;
        encontrado = solucionLasVegas(n, soluciones);
    }
    
    if(reloj.isStarted())
    {
        reloj.stop();
        tiempoEjecucion = reloj.elapsed();
    }

    mostrarSoluciones(soluciones);

    cout<<endl<<"Intentos: "<<intentos<<endl<<endl;;
    cout << "Tiempo de ejecución: " << tiempoEjecucion << " microsegundos" << endl;
}

bool solucionLasVegas(int n, vector<Tablero> &soluciones)
{
    int contador = 0;
    bool encontrado = false;

    vector<int> solucionActual(n + 1);

    solucionActual[0] = 1; 

    for(int i = 1; i <= n; i++)
    {
        solucionActual[i] = 0;
    }

    vector<int> reinas(n + 1);

    for(int i = 1; i <= n; i++)
    {
        contador = 0;

        for(int j = 1; j <= n; j++)
        {
            solucionActual[i] = j;
            if(esValida(solucionActual, i))
            {
                contador++;
                reinas[contador] = j;
            }
        }

        if(contador == 0)
        {
            break;
        }
        int columna = reinas[rand() % contador + 1];
        solucionActual[i] = columna;
    }

    if(contador  == 0)
    {
        encontrado = false;
    }
    else
    {
        Tablero solucion;
        solucion.numSoluciones = 1;
        solucion.solucionActual = solucionActual;
        soluciones.push_back(solucion);
        encontrado = true;
    }

    return encontrado;
}

//Funciones auxiliares

bool esValida(vector<int> solucionActual, int columna)
{
    bool esValida = true;
    int i = 1;

    while((i < columna) && esValida)
    {
        if((solucionActual[i] == solucionActual[columna]) )
        {
            esValida = false;
        }
        if((abs(solucionActual[i] - solucionActual[columna]) == abs(i - columna)))
        {
            esValida = false;
        }
        if((abs(solucionActual[i] - solucionActual[columna]) == 1) && (abs(i - columna) == 2))
        {
            esValida = false;
        }
        if((abs(solucionActual[i] - solucionActual[columna]) == 2) && (abs(i - columna) == 1))
        {
            esValida = false;
        }

        i++;
    }

    return esValida;
}


void mostrarSoluciones(vector<Tablero> soluciones)
{
    cout<<endl;
    for(int i = 0; i < soluciones.size(); i++)
    {
        cout << "Solución " << i + 1 << endl;
        for(int j = 1; j < soluciones[i].solucionActual.size(); j++)
        {
            cout << soluciones[i].solucionActual[j] << " ";
        }
        cout << endl<<endl;
    }
}