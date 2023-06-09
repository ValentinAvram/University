#ifndef FUNCIONESOBLIGATORIAS_HPP
#define FUNCIONESOBLIGATORIAS_HPP

#include <iostream>
#include <vector>

using namespace std;

//Necesito una struct para guardar las soluciones?

struct Tablero
{
    int numSoluciones;
    vector<int> solucionActual;
};

void metodo1();

void metodo2();

void metodo3();

void solucionesNReinas(int n, vector<Tablero> &soluciones);

bool esValida(vector<int> solucionActual, int columna);

void solucionUnicaNReinas(int n, vector<Tablero> &soluciones);

bool solucionLasVegas(int n, vector<Tablero> &soluciones);

void mostrarSoluciones(vector<Tablero> soluciones);

#endif