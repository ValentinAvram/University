#include "DeterminanteIterativo.hpp"

using namespace std;

void tiempoDeterminanteIterativo(int nMin, int nMax, int incremento, int repeticiones, 
vector <double> &tiemposReales, vector <double> &n, int opc)
{
    Clock reloj;
    for(int i = nMin; i <= nMax; i )
    {
        
        for(int j = 0; j < repeticiones; j++)
        {
            vector < vector < double > > matriz;
            matriz = vector< vector< double > >(i, vector< double >(i));

            switch(opc)
            {
                case 1: 
                for(int k = 0; k < i; k++)
                {
                    for(int m = 0; m < i; m++)
                    {
                        cout <<"Introduzca el valor de la matriz de orden "<<i<<" en la posicion ["<<k<<"] ["<<m<<"]:"<<endl;
                        double value;
                        cin >> value;
                        matriz[k][m] = value;
                    }
                }
                break;
                case 2:
                for(int k = 0; k < i; k++)
                {
                    for(int m = 0; m < i; m++)
                    {
                        double f = (double)rand() / RAND_MAX;
                        double value = 0.95 + f * (1.05 - 0.95); 
                        matriz[k][m] = value;
                    }
                }
                break;
                case 0:
                exit(EXIT_SUCCESS);
                break;
                default:
                break;
            }            

            reloj.start();
            double determinante = determinanteTriangulacion(matriz, i);
            if(reloj.isStarted())
            {
                reloj.stop();
                tiemposReales.push_back(reloj.elapsed());
            }

            n.push_back(i);
        }
        i = i + incremento;
    }
}

void DeterminanteIterativo()
{
    cout << "Indique el orden minimo de la matriz" << endl;
    int nMin;
    cin >> nMin;

    cout << "Indique el orden maximo de la matrix" << endl;
    int nMax;
    cin >> nMax;

    while(nMax <= nMin)
    {
        cout << "Error, introduzca un orden mayor al indicado como 'orden minimo de la matriz'" <<endl;
        cin >> nMax;
    }

    cout << "Indique el incremento en el orden de la matriz" << endl;
    int incremento;
    cin >> incremento;

    cout << "Indique el numero de repeticiones para cada prueba" << endl;
    int repeticiones;
    cin >> repeticiones;

    cout << "Introduzca una de las siguientes opciones, o '-1' para salir del programa" <<endl;
    cout << "1 - Rellenar las matrices manualmente" <<endl;
    cout << "2 - Rellenar las matrices aleatoriamente" <<endl;
    int opc;
    cin >> opc;
    
    vector<double> a;
    vector <double> tiemposReales;
    vector <double> ordenesMatriz;
    vector <double> tiemposEstimados;
    
    cout <<"\nCalculando ..."<<endl;

    tiempoDeterminanteIterativo(nMin, nMax, incremento,repeticiones, tiemposReales, ordenesMatriz, opc);
    
    ofstream fich("tiemposRealesDetIter.txt");
    if (!fich)
    {
        cout << "Error al abrir el archivo\n";
        exit(EXIT_FAILURE);
    }

    for(int i = 0; i < tiemposReales.size(); i++)
    {
        fich << ordenesMatriz[i] << " " << tiemposReales[i] << endl;
    }

    ajustePolinomico(ordenesMatriz, tiemposReales, a);
    calcularTiemposEstimadosPolinomico(ordenesMatriz, a, tiemposEstimados);
    calcularCoeficienteDeterminacion(tiemposReales, tiemposEstimados);

    cout <<"\nLa funcion del ajuste es : t(n) = "<<a[0]<<" + "<<a[1]<<" * n + "<<a[2]<<" * n^2 + "<<a[3]<<" * n^3 "<<endl;

    ofstream fich2("datosFinalesDetIter.txt");
    if (!fich2)
    {
        cout << "Error al abrir el archivo\n";
        exit(EXIT_FAILURE);
    }

    for(int i = 0; i < tiemposReales.size(); i++)
    {
        fich2 << ordenesMatriz[i] << " "<<tiemposReales[i] <<" "<< tiemposEstimados[i] << endl;
    }

    double num;
    do
    {
        cout <<"\n\nIndique un determinado numero de elementos para estimar el tiempo que tardaria en ordenarse. Introduzca 0 para salir"<<endl;
        cin >> num;
        while(num < 1 && num != 0)
        {
            cout<<"Introduzca un numero mayor a 1"<<endl;
            cin >> num;
        }

        if(num == 0)
        {
            exit(EXIT_SUCCESS);
        }

        double numEstimado = calcularTiempoEstimadoPolinomico(num, a);

        long seconds = numEstimado / 1000000;
        long years = (seconds/31536000);
        long days = (seconds/86400) %365;
        long hours = (seconds/3600) %24;
        long minutes = (seconds/60) %60;
        long ss = seconds%60;

        cout <<"\n\nPara una matriz de orden " << num <<" el tiempo estimado es : "<< numEstimado <<" microsegundos ";
        if(ss > 0)
        {   
            cout<<", es decir: "<<endl;
            cout << ss << " segundos"<<endl;
        }

        if(minutes > 0)
        {
            cout << minutes << " minutos"<<endl;
        }

        if(hours > 0)
        {
            cout << hours << " horas"<<endl;
        }

        if(days > 0)
        {
            cout<< days <<" dias"<<endl;
        }

        if(years > 0)
        {
            cout<< years <<" años ";
        }
        
    }while(num != 0);  
}

double determinanteTriangulacion(vector < vector < double > > &Matriz, int n)
{


    vector < vector < double > > matrizAux;
	matrizAux = vector< vector< double > >(n, vector< double >(n));

    inicializarInversa(matrizAux);

    trianguloInferior(Matriz, matrizAux);

    double determinante = productoDiagonal(Matriz);

    return determinante;
    
}
