/*!	
	\file    stringVariable.cpp
	\brief   Code of StringVariable class
	\author  Valentín Avram
	\date    2023-05-20
	\version 0.10
*/

#include "stringVariable.hpp"

#include <iostream>

void lp::StringVariable::read()
{
    // Inherited attributes
    std::cout << "Name of the StringVariable: ";
    std::cin >> this->_name;

    std::cout << "Token of the StringVariable: ";
    std::cin >> this->_token;
    // The \n character is read 
    std::cin.ignore(); 

    std::cout << "Type of the StringVariable: ";
    std::cin >> this->_type;
    // The \n character is read
    std::cin.ignore();

    std::cout << "Value of the StringVariable: ";
    std::cin >> this->_value;
    // The \n character is read
    std::cin.ignore();

}

void lp::StringVariable::write() const
{
    std::cout << "Name:" << this->getName() << std::endl;
    std::cout << "Token:" << this->getToken() << std::endl;
    std::cout << "Value:" << this->getValue() << std::endl;
    std::cout << "Type:" << this->getType() << std::endl;
}

lp::StringVariable &lp::StringVariable::operator=(const lp::StringVariable &s)
{
    // Check that is not the current object
    if (this != &s) 
    {
        // Inherited methods
        this->setName(s.getName());

        this->setToken(s.getToken());

        this->setValue(s.getValue());

        this->setType(s.getType());
    }

    // Return the current object
    return *this;
}

namespace lp
{
    std::istream &operator>>(std::istream &i, lp::StringVariable &s)
    {
        i >> s._name;
        i >> s._token;
        i.ignore();
        
        i >> s._type;
        i.ignore();
        
        i >> s._token;
        i.ignore();

        i >> s._value;
        i.ignore();

        return i;
    }

    std::ostream &operator<<(std::ostream &o, const lp::StringVariable &s)
    {
        o << s._name << std::endl;
        o << s._token << std::endl;
        o << s._type << std::endl;
        o << s._value << std::endl;

        return o;
    }
}