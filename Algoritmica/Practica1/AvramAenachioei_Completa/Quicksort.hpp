#ifndef QUICKSORT_HPP
#define QUICKSORT_HPP

#include <iostream>
#include <algorithm>  
#include <vector> 
#include <cmath>
#include <cstdlib>
#include <fstream> 

using namespace std;

void rellenarVector(vector<int> &v); // Función que rellena el vector con números aleatorios 0 y 9999999.

bool estaOrdenado(const vector <int> &v);

void algoritmoQuicksort();

void tiemposOrdenacionQuickSort(int nMin, int nMax, int incremento, int repeticiones, 
vector <double> &tiemposReales, vector <double> &numeroElementos);

void calcularTiemposEstimadosNlogN(const vector <double> &numeroElementos, const vector <double> &a, vector <double> &tiemposEstimados);


#endif