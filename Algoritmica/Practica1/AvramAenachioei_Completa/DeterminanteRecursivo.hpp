#ifndef DETERMINANTERECURSIVO_HPP
#define DETERMINANTERECURSIVO_HPP

#include <iostream>
#include <algorithm>  
#include <vector> 
#include <cmath>
#include <cstdlib>
#include <fstream> 

using namespace std;

void algoritmoIterativo();

double DeterminanteRecursivo(vector < vector < double > > &matriz, int n);

void tiemposDeterminanteRecursivo(int nMin, int nMax, int incremento, int repeticiones, 
vector <double> &tiemposReales, vector <double> &n, int opc);

#endif