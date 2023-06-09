/*
 * utils.cpp
 *
 *  Created on: Dec 17, 2022
 *      Author: vic
 */

#include "utils.h"

#define passlenght 8

using namespace std;



void clearScreen(){
	system("clear");
}

void menuInicio(){

	int opt;
	vector<user> usuarios;

	do{
		cout<<"Bienvenido"<<endl;
		cout << "1. Iniciar sesión" << endl;
		cout << "2. Continuar sin iniciar sesion" << endl;
		cout << "0. Salir" << endl;
		cout << "Introduzca una opción: ";

		cin>>opt;

		switch(opt){
		case 1:
			usuarios.clear();
			openFile(usuarios);
			login(usuarios);
			break;
		case 2:
			menuVisitante();
			break;
		case 0:
			cout<<"Gracias por su visita"<<endl;
			break;
		default:
			cout<<"Opcion no valida"<<endl;
			break;
		}

	}while(opt!=0);
	exit(0);
}

void login(vector<user> usuarios){
	//clearScreen();

	  string correo;
	  string password;

	  cout << "Introduzca su correo: ";
	  cin >> correo;
	  cout<<correo<<endl;
	  cout << "Introduzca su contraseña: ";
	  cin >> password;
	  cout<<password<<endl;

	  if (checkUser(usuarios, correo, password))
	  {
	    cout << "Bienvenido al sistema" << endl;
	    //Creamos un usuario especifico para el usuario que se ha logueado
	    user user = getUser(usuarios, correo, password);
	    //Comprobamos el tipo de usuario
	    checkType(user);
	  }
	  else
	  {
	    cout << "Usuario o contraseña incorrectos" << endl;
	  }
}

bool checkUser(vector<user> usuarios, string correo, string password){
  for (int i = 0; i < usuarios.size(); i++)
  {
    if (usuarios[i].getCorreo() == correo && usuarios[i].getPassword() == password)
    {
      return true;
    }
  }
  return false;
}

user getUser(vector<user> usuarios, string correo, string password){
  for (int i = 0; i < usuarios.size(); i++)
  {
    if (usuarios[i].getCorreo() == correo && usuarios[i].getPassword() == password)
    {
      return usuarios[i];
    }
  }
}

void checkType(user usuario){

  if(usuario.getTipo()=="0"){
    menuAdmin(usuario);
  }
  else if(usuario.getTipo()=="1"){
    menuCoord(usuario);
  }
}

void openFile(vector<user> &usuarios){
	//Abrimos el archivo
	  ifstream archivo("usuario.txt");
	  //Creamos una variable para guardar los datos del archivo
	  string linea;
	  //Creamos un vector para guardar los datos de cada usuario
	  vector<string> datos;

	  //Comprobamos que el archivo se ha abierto correctamente
	  if (archivo.is_open())
	  {
		  cout<<"Abre el archivo bien"<<endl;
	    //Leemos el archivo linea a linea

		  int count = 0;
	    while (!archivo.eof())
	    {
	    	cout<<"Hace el bucle"<<endl;
	    	count ++;

	    	getline(archivo,linea);
	    	cout<<"LA LINEA:"<<linea<<endl;
	      //Separamos los datos de cada usuario
	      datos = split(linea, ',');
	      //Creamos un usuario con los datos del vector
	      user usuario(datos[0], datos[1], datos[2], datos[3]);
	      cout<<datos[0]<<" "<<datos[1]<<" "<<datos[2]<<" "<<datos[3]<<endl;
	      //Añadimos el usuario al vector de usuarios
	      usuarios.push_back(usuario);
	    }
	    cout<<count;
	  }
	  else
	  {
	    cout << "Error al abrir el archivo" << endl;
	  }
}

vector<string> split(string linea, char separador){
	cout<<"Entra en split"<<endl;
  //Creamos un vector para guardar los datos
  vector<string> datos;
  //Creamos un string para guardar los datos temporales
  string temp;
  cout<<linea.length()<<endl;

  //Recorremos la linea
  for (int i = 0; i < linea.length(); i++)
  {
    //Comprobamos si el caracter es el separador
    if (linea[i] == separador)
    {
      //Añadimos el dato al vector
      datos.push_back(temp);
      //Vaciamos el string temporal
      temp = "";
    }
    else
    {
      //Añadimos el caracter al string temporal
      temp += linea[i];
    }
  }
  //Añadimos el ultimo dato al vector
  datos.push_back(temp);

  return datos;
}

void menuVisitante(){

  clearScreen();
  int wait;
  cout<<"Bienvenido visitante"<<endl;
  cin>>wait;
}

void menuCoord(user usuario){

  clearScreen();

  //Comprobamos que el usuario es un coordinador
  if(usuario.getTipo()=="1"){

    int opcion;

    cout<<"Bienvenido Coordinador"<<endl;
    cin>>opcion;

    switch(opcion){

    }

  }
}

void menuAdmin(user usuario){

  clearScreen();

  //Comprobamos que el usuario es un administrador
  if(usuario.getTipo()=="0"){

    int opcion;

    cout<<""<<endl;
    cin>>opcion;

    switch(opcion){

    }

  }
}
