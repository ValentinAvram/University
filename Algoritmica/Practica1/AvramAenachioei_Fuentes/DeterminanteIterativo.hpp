#ifndef DETERMINANTEITERATIVO_HPP
#define DETERMINANTEITERATIVO_HPP

#include <iostream>
#include <algorithm>  
#include <vector> 
#include <cmath>
#include <cstdlib>
#include <fstream> 

using namespace std;

void DeterminanteIterativo();

double determinanteTriangulacion(vector < vector < double > > &Matriz, int n);

void tiemposDeterminanteIterativo(int nMin, int nMax, int incremento, int repeticiones, 
vector <double> &tiemposReales, vector <double> &n, int opc);

#endif