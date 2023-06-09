/*
 * user.h
 *
 *  Created on: Dec 17, 2022
 *      Author: vic
 */

#ifndef USER_H_
#define USER_H_

#include <iostream>
#include <string>

using namespace std;

class user{
private:
	string nombre;
	string correo;
	string password;
	string tipo;
public:
	user(string nombre, string correo, string password, string tipo);
	user(){};

	string getNombre();
	string getCorreo();
	string getPassword();
	string getTipo();

	void setNombre(string nombre);
	void setCorreo(string correo);
	void setPassword(string password);
	void setTipo(string tipo);

};

#endif /* USER_H_ */
