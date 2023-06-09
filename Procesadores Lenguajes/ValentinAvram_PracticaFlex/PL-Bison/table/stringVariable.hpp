/*!	
	\file    stringVariable.hpp
	\brief   Declaration of StringVariable class
	\author  Valentín Avram
	\date    2023-05-20
	\version 0.10
*/

#ifndef _STRINGVARIABLE_HPP_
#define _STRINGVARIABLE_HPP_

#include <string>
#include <iostream>

#include "variable.hpp"

/*!	
    \namespace lp
    \brief Name space for the subject Language Processors
*/

namespace lp
{
    class StringVariable : public lp::Variable
    {
        private:
            std::string _value; //!< Name of the variable

        public:

            inline void setValue(std::string value)
            {
                this->_value = value;
            }

            inline StringVariable(std::string name="", int token = 0, int type = 0, std::string value = "") : Variable(name,token,type)
            {
                this->setValue(value);
            }

            StringVariable(const StringVariable &s)
            {
                this->setName(s.getName());
                this->setToken(s.getToken());
                this->setValue(s.getValue());
            }

            inline std::string getValue() const
            {
                return this->_value;
            }

            void read();

            void write() const;

            StringVariable &operator=(const StringVariable &s);

            friend std::istream &operator>>(std::istream &is, StringVariable &s);

            friend std::ostream &operator<<(std::ostream &os, const StringVariable &s);
    };
}   

#endif