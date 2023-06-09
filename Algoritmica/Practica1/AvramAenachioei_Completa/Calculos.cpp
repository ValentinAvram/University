#include "Calculos.hpp"

using namespace std;

void resolverSistemaEcuaciones(vector < vector < double > > A, vector < vector < double > > B, int n, vector < vector < double > > &X)
{
	vector < vector < double > > inversa; //matriz inversa de la matriz de coeficientes que hay que calcular para resolver el sistema.
	inversa = vector< vector< double > >(n, vector< double >(n)); //Matriz de N x N

	//Inicializamos la matriz inversa
	inicializarInversa(inversa);
	
	//Se triangulariza la matriz por debajo de la diagonal
	trianguloInferior(A, inversa);

	//Mostramos determinante
	double determinante = productoDiagonal(A);
	
	if (fabs(determinante) < 0.0000000001) //Si el determinante es 0 no hay solución
	{
		cout <<endl<<"entra"<<endl;
		exit(0);
	}
	
	//Se triangulariza la matriz por encima de la diagonal
	trianguloSuperior(A,inversa);
	
	obtenerUnidad(A, inversa);
	
	//Se muestra la inversa
	multiplicarMatrices(inversa, B, X);
}

double sumatorio(vector <double> &n, vector <double> &t, double expN, double expT)
{
	double suma = 0;
	for (int i = 0; i < n.size(); i++)
	{
		suma = suma + ((pow(n[i], expN)) * (pow(t[i], expT)));
	}
	return suma;
}

void inicializarInversa(vector < vector < double > >  &inversa)
{
 
 for(unsigned int i = 0; i < inversa.size(); i++)
  for(unsigned int j = 0; j < inversa.size(); j++)
   {
    if (i == j)
     inversa[i][j] = 1.0;
    else
     inversa[i][j] = 0.0;
   }
}

//Funcion para triangularizar la matriz por debajo de la diagonal principal
void trianguloInferior(vector < vector < double > > &A, vector < vector < double > > &inversa)
{	
	bool correcto; // A correcta hasta aqui
	for(unsigned int i = 0; i < A.size() - 1; i++)
	{
		
		if (fabs(A[i][i])< 0.0000000001) //Se ha encontrado un 0 en la diagonal principal, hay que eliminarlo
		{
				
			correcto = eliminarCero(A, inversa, i);
			if (correcto == false)
			{
				cout <<"salida en el exit de trianguloInf"<<endl;
				exit(0);
			}
		}
		hacerCerosColumna(A, inversa, i);
	}
}

//Funcion para eliminar un cero de la diagonal principal en la fila x
bool  eliminarCero(vector < vector < double > > &A, vector < vector < double > > &inversa, unsigned int x)
{
	unsigned int i;
	bool correcto = false;
	bool salir = false;

	i = x+1;
	while(salir == false)
	{
		if (i == A.size())
			salir = true;
		else if (fabs(A[i][x]) > 0.0000000001) //Comprueba que no es 0
		{
			salir = true;
			correcto = true;
		}
		else
			i = i + 1;
	}
	if (correcto == true)
	{
		combinarFilas(A, i, 1.0, x);
		combinarFilas(inversa, i, 1.0, x);
	}

	return correcto;
}

//Funcion que hace ceros en una columna

void hacerCerosColumna(vector < vector < double > > &A, vector < vector < double > > &inversa, unsigned int x)
{
	double aux;

	for(unsigned int i = x+1; i < A.size(); i++)
	{
		aux = -A[i][x]/A[x][x];
		combinarFilas(A, x, aux, i);
		combinarFilas(inversa, x, aux, i);
	}
}

//Esta funcion se usa para hacer combinaciones lineales en filas en una matriz
// En este caso, la fila origen se multiplica por factor y se suma a la destino
void combinarFilas(vector < vector < double > > &matriz, unsigned int filaOrigen, double factor, unsigned int filaDestino)
{
	for(unsigned int i = 0; i < matriz.size(); i++)
		matriz[filaDestino][i] = matriz[filaDestino][i] + factor * matriz[filaOrigen][i];
}

//Esta función calcula el valor del producto de los elementos de la diagonal principal de la matriz
double productoDiagonal(const vector < vector < double > > &matriz)
{
	double d = 1.0;
	

	for(unsigned int i = 0; i < matriz.size(); i++)
		d = d * matriz[i][i];

	return d;  
}

//Funcion para triangularizar la matriz por encima de la diagonal principal
void trianguloSuperior(vector < vector < double > > &A, vector < vector < double > > &inversa)
{
	bool correcto;
	for(unsigned int i = A.size() - 1; i > 0; i--)
	{
		if (fabs(A[i][i])< 0.0000000001) //Comprueba que es 0
		{
			correcto = eliminarCero2(A, inversa, i);
			if (correcto == false)
			{
				exit(0);
			}
		}
		hacerCerosColumna2(A, inversa, i);
	}
}

//Funcion para eliminar ceros hacia arriba
int eliminarCero2(vector < vector < double > > &A, vector < vector < double > > &inversa, unsigned int x)
{
	int i;
	bool correcto = false;
	bool salir = false;

	i = x - 1;
	while(salir == false)
	{
		if (i == -1)
			salir = true;
		else if (fabs(A[i][x]) > 0.0000000001)
		{
			salir = true;
			correcto = true;
		}
		else
			i = i - 1;
	}
	if (correcto == true)
	{
		combinarFilas(A, i, 1.0, x);
		combinarFilas(inversa, i, 1.0, x);
	}
	return correcto;
}

//Funcion para hacer ceros en el triángulo superior
void hacerCerosColumna2(vector < vector < double > > &A, vector < vector < double > > &inversa, unsigned int x)
{
	double aux;

	for(int i = x-1; i >= 0; i--)
	{
		aux = -A[i][x] / A[x][x];
		combinarFilas(A, x, aux, i);
		combinarFilas(inversa, x, aux, i);
	}
}

void obtenerUnidad(vector < vector < double > > &A, vector < vector < double > > &inversa)
{
	double aux;

	for(unsigned int i = 0; i < A.size(); i++)
	{
		aux = A[i][i];
		for(unsigned int j = 0; j < A.size(); j++)
		{
			A[i][j] = A[i][j] / aux;
			inversa[i][j] = inversa[i][j] / aux;
		}
	}
}

void multiplicarMatrices(const vector < vector < double > > &m1, const vector < vector < double > > &m2, vector < vector < double > > &producto)
{

	for(unsigned int i = 0; i < m1.size(); i++) //Recorre filas de la primera matriz
	{
		for(unsigned int j = 0; j < m2[0].size(); j++) //Recorre columnas de la segunda matriz
		{
			producto[i][j] = 0.0;
			for(unsigned int k = 0; k < m1[0].size(); k++) //Recorre los elementos a multiplicar (numero de columnas de la primera matriz)
				producto[i][j] = producto [i][j] + m1[i][k]*m2[k][j];
		}
	}
}

void calcularTiemposEstimadosNlogN(vector <double> &numeroElementos, vector <double> &a, vector <double> &tiemposEstimados)
{
    double a0 = a[0];
    double a1 = a[1];

    for(int i = 0; i < numeroElementos.size(); i++)
    {
        double estTime = (a0) + (a1 * (numeroElementos[i]) * log10(numeroElementos[i]));
        tiemposEstimados.push_back(estTime);
    }
}

double calcularCoeficienteDeterminacion(vector <double> &tiemposReales, vector <double> &tiemposEstimados)
{
	double mediaTiempoReal = (sumatorio(tiemposReales, tiemposEstimados, 1, 0))/tiemposReales.size();
	double mediaTiempoEstimado = sumatorio(tiemposReales, tiemposEstimados, 0, 1)/tiemposReales.size();

	double varianzaReales = 0;
	for (int i = 0; i < tiemposReales.size(); i++)
	{

		varianzaReales = varianzaReales + pow((tiemposReales[i] - mediaTiempoReal),2);
	}
	varianzaReales = varianzaReales/tiemposReales.size();


	double varianzaEstimados = 0;
	for (int i = 0; i < tiemposEstimados.size(); i++)
	{
		varianzaEstimados = varianzaEstimados + pow((tiemposEstimados[i] - mediaTiempoEstimado), 2);
	}
	varianzaEstimados = varianzaEstimados/tiemposEstimados.size();

	double CoeficienteDet = varianzaEstimados / varianzaReales;

	cout << "\nEl coeficiente de Determinacion es " << CoeficienteDet << endl;

	return CoeficienteDet;
}

double calcularTiempoEstimadoNlogN(double &n, vector <double> &a)
{
    double a0 = a[0];
    double a1 = a[1];

    double estTime = a0 + a1 * (n) * log10(n);
 
	return estTime;
}

void ajustePolinomico(vector <double> &n, vector <double> &tiemposReales, vector <double> &a)
{
	vector < vector < double > > A;
	A = vector< vector< double > >(4, vector< double >(4));

	for(int i = 0; i < 4; i++)
	{
		for(int j = 0; j < 4; j++)
		{

			A[i][j] = sumatorio(n, tiemposReales, i+j, 0);
		}
	}

	vector < vector < double > > B;
	B = vector< vector< double > >(4, vector< double >(1));

	for(int i = 0; i < 4; i++)
	{
		B[i][0] = sumatorio(n, tiemposReales, i, 1);
	}

	vector < vector < double > > X;
	X = vector< vector< double > >(4, vector< double >(1));

  	resolverSistemaEcuaciones(A, B, 4, X);

    for(int i = 0; i < X.size(); i++)
    {
        a.push_back(X[i][0]);
    }
}

void ajusteNlogN(vector <double> &numeroElementos, vector <double> &tiemposReales, 
vector <double> &a)
{
	vector < vector < double > > A;
	A = vector< vector< double > >(2, vector< double >(2));
	
	vector <double> zetaValues;

	for(int i = 0; i < numeroElementos.size(); i++)
	{
		zetaValues.push_back(((numeroElementos[i])*(log10(numeroElementos[i]))));
	}

	for(int i = 0; i < 2; i++)
	{
		for(int j = 0; j < 2; j++)
		{

			A[i][j] = sumatorio(zetaValues, tiemposReales, i+j, 0);
		}
	}

	vector < vector < double > > B;
	B = vector< vector< double > >(2, vector< double >(1));

	for(int i = 0; i < 2; i++)
	{
		B[i][0] = sumatorio(zetaValues, tiemposReales, i, 1);
	}

    
	vector < vector < double > > X;
	X = vector< vector< double > >(2, vector< double >(1));

  	resolverSistemaEcuaciones(A, B, 2, X);

    for(int i = 0; i < X.size(); i++)
    {
        a.push_back(X[i][0]);
    }
}

void calcularTiemposEstimadosPolinomico(vector <double> &n, const vector <double> &a, vector
<double> &tiemposEstimados)
{
	double a0 = a[0];
    double a1 = a[1];
	double a2 = a[2];
	double a3 = a[3];


    for(int i = 0; i < n.size(); i++)
    {
        double estTime = a0 + (a1 * n[i]) + (a2 * pow(n[i],2)) + (a3 * pow(n[i],3));
        tiemposEstimados.push_back(estTime);
    }
}

double calcularTiempoEstimadoPolinomico(const double &n, const vector <double> &a)
{
	double a0 = a[0];
    double a1 = a[1];
	double a2 = a[2];
	double a3 = a[3];

    double estTime = a0 + (a1 * (n)) + (a2 * pow(n, 2)) + (a3 * pow(n, 3));
 
	return estTime;
}

void ajusteFactorial(vector <double> &numeroElementos, vector <double> &tiemposReales,
vector <double> &a)
{
	vector < vector < double > > A;
	A = vector< vector< double > >(2, vector< double >(2));
	
	vector <double> zetaValues;

	for(int i = 0; i < numeroElementos.size(); i++)
	{
		zetaValues.push_back(factorial(numeroElementos[i]));
	}

	for(int i = 0; i < 2; i++)
	{
		for(int j = 0; j < 2; j++)
		{

			A[i][j] = sumatorio(zetaValues, tiemposReales, i+j, 0);
		}
	}

	vector < vector < double > > B;
	B = vector< vector< double > >(2, vector< double >(1));

	for(int i = 0; i < 2; i++)
	{
		B[i][0] = sumatorio(zetaValues, tiemposReales, i, 1);
	}

	
	vector < vector < double > > X;
	X = vector< vector< double > >(2, vector< double >(1));

  	resolverSistemaEcuaciones(A, B, 2, X);

	for(int i = 0; i < X.size(); i++)
	{
		a.push_back(X[i][0]);
	}
}

double factorial(double n)
{
	double factorial = 1;

	for(int i = 1; i <= n; i++)
	{
		factorial = factorial * i;
	}

	return factorial;
}

void calcularTiemposEstimadosFactorial(const vector <double> &n, const vector <double> &tiemposReales, const vector <double> &a, vector
<double> &tiemposEstimados)
{
	double a0 = a[0];
	double a1 = a[1];

	for(int i = 0; i < n.size(); i++)
	{
		double zeta = factorial(n[i]);
		double estTime = a0 + a1 * zeta;
		tiemposEstimados.push_back(estTime);
	}
}

//Calcular tiempo estimado factorial
double calcularTiempoEstimadoFactorial(const double &n, const vector <double> &a)
{
	double a0 = a[0];
	double a1 = a[1];

	double zeta = factorial(n);
	double estTime = a0 + a1 * zeta;

	return estTime;
}