/*
 * utils.h
 *
 *  Created on: Dec 17, 2022
 *      Author: vic
 */

#ifndef UTILS_H_
#define UTILS_H_



#include <iostream>
#include <string>
#include <cstdlib>
#include <vector>
#include <fstream>
#include <sstream>

//Incluimos user.h para poder usar la clase Usuario
#include "user.h"

using namespace std;

void menuInicio();

void login(vector<user> usuarios);
bool checkUser(vector<user> usuarios, string correo, string password);
user getUser(vector<user> usuarios, string correo, string password);
void openFile(vector<user> &usuarios);
vector<string> split(string linea, char separador);
void checkType(user usuario);
void menuVisitante();
void menuCoord(user usuario);
void menuAdmin(user usuario);
void clearScreen();

#endif /* UTILS_H_ */
