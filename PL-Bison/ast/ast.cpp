/*!	
	\file    ast.cpp
	\brief   Code of funcitons of AST clas
	\author  
	\date    2018-12-13
	\version 1.0
*/

#include <iostream>
#include <stdlib.h>
#include <string>
#include <list>

// Para usar la funciones pow y std::abs
#include <cmath>

#include "ast.hpp"

#include "../table/table.hpp"

// warning
#include "../error/error.hpp"

// Macros for the screen
#include "../includes/macros.hpp"

// 
#include "../table/numericVariable.hpp"
#include "../table/logicalVariable.hpp"
#include "../table/stringVariable.hpp"

#include "../table/numericConstant.hpp"
#include "../table/logicalConstant.hpp"

#include "../table/builtinParameter0.hpp"
#include "../table/builtinParameter1.hpp"
#include "../table/builtinParameter2.hpp"

#include "../parser/interpreter.tab.h"



///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

extern lp::Table table; //!< Reference to the Table of Symbols


extern lp::AST *root; //!< Reference to the object at the base of the AST


///////////////////////////////////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

int lp::VariableNode::getType() 
{ 
	// Get the identifier in the table of symbols as Variable
	lp::Variable *var = (lp::Variable *) table.getSymbol(this->_id);

	// Return the type of the Variable
	return var->getType();
}


void lp::VariableNode::printAST() 
{
  // std::cout << "VariableNode: " << this->_id;
  // std::cout << " (Type: " << this->getType() << ")" << std::endl;
}


double lp::VariableNode::evaluateNumber() 
{ 
	double result = 0.0;

	if (this->getType() == NUMBER)
	{
		// Get the identifier in the table of symbols as NumericVariable
		lp::NumericVariable *var = (lp::NumericVariable *) table.getSymbol(this->_id);

		// Copy the value of the NumericVariable
		result = var->getValue();
	}
	else
	{
		warning("Runtime error in evaluateNumber(): the variable is not numeric", 
				   this->_id);
	}

	// Return the value of the NumericVariable
	return result;
}


bool lp::VariableNode::evaluateBool() 
{ 
	bool result = false;

	if (this->getType() == BOOL)
	{
		// Get the identifier in the table of symbols as LogicalVariable
		lp::LogicalVariable *var = (lp::LogicalVariable *) table.getSymbol(this->_id);

		// Copy the value of the LogicalVariable
		result = var->getValue();
	}
	else
	{
		warning("Runtime error in evaluateBool(): the variable is not boolean",
				   this->_id);
	}

	// Return the value of the LogicalVariable
	return result;
}

/*String v 0.10*/

std::string lp::VariableNode::evaluateString()
{
	std::string result = "";
	
	if (this->getType() == STRING)
    {
        // Get the identifier in the table of symbols as StringVariable
        lp::StringVariable *var = (lp::StringVariable *)table.getSymbol(this->_id);

        // Copy the value of the StringVariable
        result = var->getValue();
    }
    else
    {
        warning("Runtime error in evaluateString(): the variable is not string",
                this->_id);
    }

    // Return the value of the StringlVariable
    return result;
}

///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

void lp::ConstantNode::printAST() 
{
  // std::cout << "ConstantNode: " << this->_id;
  // std::cout << " (Type: " << this->getType() << ")" << std::endl;
}

int lp::ConstantNode::getType() 
{ 
	// Get the identifier in the table of symbols as Constant
	lp::Constant *var = (lp::Constant *) table.getSymbol(this->_id);

	// Return the type of the Constant
	return var->getType();
}


double lp::ConstantNode::evaluateNumber() 
{ 
	double result = 0.0;

	if (this->getType() == NUMBER)
	{
		// Get the identifier in the table of symbols as NumericConstant
		lp::NumericConstant *constant = (lp::NumericConstant *) table.getSymbol(this->_id);

		// Copy the value of the NumericConstant
		result = constant->getValue();
	}
	else
	{
		warning("Runtime error in evaluateNumber(): the constant is not numeric", 
				   this->_id);
	}

	// Return the value of the NumericVariable
	return result;
}

bool lp::ConstantNode::evaluateBool() 
{ 
	bool result = false;

	if (this->getType() == BOOL)
	{
		// Get the identifier in the table of symbols as LogicalConstant
		lp::LogicalConstant *constant = (lp::LogicalConstant *) table.getSymbol(this->_id);

		// Copy the value of the LogicalConstant
		result = constant->getValue();
	}
	else
	{
		warning("Runtime error in evaluateBool(): the constant is not boolean",
				   this->_id);
	}

	// Return the value of the LogicalVariable
	return result;
}


//////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////
 

int lp::NumberNode::getType()
{
	return NUMBER;
}


void lp::NumberNode::printAST()
{
  // std::cout << "NumberNode: " << this->_number << std::endl;
}

double lp::NumberNode::evaluateNumber() 
{ 
    return this->_number; 
}

// TODO: BOOLEAN!!!

///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////
/* String made in v 0.10*/

int lp::StringNode::getType()
{
	return STRING;
}

void lp::StringNode::printAST()
{
	// std::cout << "StringNode: " << this->_string << std::endl;
}

std::string lp::StringNode::evaluateString()
{
	return this->_string;
}

/* Concatenation Node*/

int lp::ConcatenationNode::getType()
{
	return STRING;
}

void lp::ConcatenationNode::printAST()
{
	// std::cout<<"ConcatenationNode: "<<std::endl;
	// this->_left->printAST();
	//this->_right->printAST();	
}

std::string lp::ConcatenationNode::evaluateString()
{
	std::string result = "";
	
	if(this-> getType() != STRING)
	{
		execerror("Runtime error: expresions are not strin","Incompatible types","Use 2 string for concatenations");
	}
	else
	{
		result = this->_left->evaluateString() + this->_right->evaluateString();
	}

	return result;
}

///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

int lp::BoolNode::getType()
{
	return BOOL;
}

bool lp::BoolNode::evaluateBool()
{
	return this->_bool;
}

void lp::BoolNode::printAST()
{
	// std::cout << "BoolNode: " << this->_bool << std::endl;
}

///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////
int lp::NumericUnaryOperatorNode::getType()
{
	int result;

	if(this->_exp->getType() == NUMBER)
	{
		result = NUMBER;
	}
	else
	{
		warning("Runtime error: incompatible types for", "Numeric Unary Operator");
	}

	return result;
}

///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

int lp::LogicalUnaryOperatorNode::getType()
{
	int result;

	if(this->_exp->getType() == BOOL)
	{
		result = BOOL;
	}
	else
	{
		warning("Runtime error: incompatible types for", "Logical Unary Operator");
	}
	
	return result;
}


///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

int lp::NumericOperatorNode::getType()
{
	int result = 0;
		
	if ( (this->_left->getType() == NUMBER) and (this->_right->getType() == NUMBER))
		result = NUMBER;
	else
		warning("Runtime error: incompatible types for", "Numeric Operator");

	return	result;
}

///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////


int lp::RelationalOperatorNode::getType()
{
	int result = 0;
		
	if ( (this->_left->getType() == NUMBER) and (this->_right->getType() == NUMBER))
		result = BOOL;
	else if ( (this->_left->getType() == BOOL) and (this->_right->getType() == BOOL))
		result = BOOL;
	else if ( (this->_left->getType() == STRING) and (this->_right->getType() == STRING))
		result = BOOL;
	else
		warning("Runtime error: incompatible types for", "Relational Operator");

	return	result;
}


///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

int lp::LogicalOperatorNode:: getType()
{
	int result = 0;
		
	if ( (this->_left->getType() == BOOL) and (this->_right->getType() == BOOL))
	{
		// 
		result = BOOL;
	}
	else
		warning("Runtime error: incompatible types for", "Logical Operator");

	return	result;
}


///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////


void lp::UnaryMinusNode::printAST() 
{
  // std::cout << "UnaryMinusNode: -"  << std::endl;
  // std::cout << "\t"; 
  // this->_exp->printAST();
}

double lp::UnaryMinusNode::evaluateNumber()
{
	double result = 0.0;

	// Ckeck the type of the expression
	if (this->getType() == NUMBER)
	{
		// Minus
		result = - this->_exp->evaluateNumber();
	}
	else
	{
		warning("Runtime error: the expressions are not numeric for ", "UnaryMinus");
	}

  return result;
}


///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////


void lp::UnaryPlusNode::printAST() 
{
  // std::cout << "UnaryPlusNode: +"  << std::endl;
  // std::cout << "\t"; 
  // this->_exp->printAST();
}

double lp::UnaryPlusNode::evaluateNumber()
{
	double result = 0.0;

	// Ckeck the type of the expression
	if (this->getType() == NUMBER)
	{
		result = this->_exp->evaluateNumber();
	}
	else
	{
		warning("Runtime error: the expressions are not numeric for ","UnaryPlus");
	}

  return result;
}


///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

void lp::PlusNode::printAST() 
{
  // std::cout << "PlusNode: +"  << std::endl;
  // std::cout << "\t"; 
  //this->_left->printAST();
	//std::cout << "\t"; 
	//this->_right->printAST();
}

double lp::PlusNode::evaluateNumber()
{
	double result = 0.0;

	// Ckeck the types of the expressions
	if (this->getType() == NUMBER)
	{
		result = this->_left->evaluateNumber() + this->_right->evaluateNumber();
	}
	else
	{
		warning("Runtime error: the expressions are not numeric for ", "Plus");
	}

  return result;
}


///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

void lp::MinusNode::printAST() 
{
  //std::cout << "MinusNode: -"  << std::endl;
  //std::cout << "\t"; 
	//this->_left->printAST();
	//std::cout << "\t"; 
	//this->_right->printAST();
}

double lp::MinusNode::evaluateNumber() 
{
	double result = 0.0;

	// Ckeck the types of the expressions
	if (this->getType() == NUMBER)
	{
		result = this->_left->evaluateNumber() - this->_right->evaluateNumber();
	}
	else
	{
		warning("Runtime error: the expressions are not numeric for ", "Minus");
	}

  return result;
}

///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////


void lp::MultiplicationNode::printAST() 
{
  //std::cout << "MultiplicationNode: *"  << std::endl;
  //std::cout << "\t"; 
	//this->_left->printAST();
	//std::cout << "\t"; 
	//this->_right->printAST();
}

double lp::MultiplicationNode::evaluateNumber() 
{
	double result = 0.0;

	// Ckeck the types of the expressions
	if (this->getType() == NUMBER)
	{
		result = this->_left->evaluateNumber() * this->_right->evaluateNumber();
	}
	else
	{
		warning("Runtime error: the expressions are not numeric for ","Multiplication", "Note: use numeric values");
	}

  return result;
}



///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

void lp::DivisionNode::printAST()
{
  //std::cout << "DivisionNode: /" << std::endl;
  //std::cout << "\t"; 
	//this->_left->printAST();
	//std::cout << "\t"; 
	//this->_right->printAST();
}

double lp::DivisionNode::evaluateNumber() 
{
	double result = 0.0;

	// Ckeck the types of the expressions
	if (this->getType() == NUMBER)
	{
		double leftNumber, rightNumber;

		leftNumber = this->_left->evaluateNumber();
		rightNumber = this->_right->evaluateNumber();
	
		// The divisor is not zero
    	if(std::abs(rightNumber) > ERROR_BOUND)
		{
				result = leftNumber / rightNumber;
		}
		else
		{
			warning("Runtime error", "Division by zero");
		}
	}
	else
	{
		warning("Runtime error: the expressions are not numeric for", "Division", "Note: use numeric values");
	}

  return result;
}

///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

void lp::IntegerDivisionNode::printAST()
{
  //std::cout << "IntegerDivisionNode: //" << std::endl;
  //std::cout << "\t"; 
	//this->_left->printAST();
	//std::cout << "\t"; 
	//this->_right->printAST();
}

double lp::IntegerDivisionNode::evaluateNumber() 
{
	double result = 0.0;

	// Ckeck the types of the expressions
	if (this->getType() == NUMBER)
	{
		double leftNumber, rightNumber;

		leftNumber = this->_left->evaluateNumber();
		rightNumber = this->_right->evaluateNumber();
	
		// The divisor is not zero
		if(std::abs(rightNumber) > ERROR_BOUND)
		{
				result = (int) leftNumber / (int) rightNumber;
		}
		else
		{
			warning("Runtime error", "Division by zero");
		}
	}
	else
	{
		warning("Runtime error: the expressions are not numeric for", "IntegerDivision");
	}

  return result;
}

///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

void lp::ModuloNode::printAST()
{
  //std::cout << "ModuloNode: %" << std::endl;
  //std::cout << "\t"; 
	//this->_left->printAST();
	//std::cout << "\t"; 
	//this->_right->printAST();
}

double lp::ModuloNode::evaluateNumber() 
{
	double result = 0.0;

	// Ckeck the types of the expressions
	if (this->getType() == NUMBER)
	{
		double leftNumber, rightNumber;

		leftNumber = this->_left->evaluateNumber();
		rightNumber = this->_right->evaluateNumber();
	
    	if(std::abs(rightNumber) > ERROR_BOUND)
				result = (int) leftNumber % (int) rightNumber;
		else
			warning("Runtime error", "Division by zero", "Note: Check the expression ");
	}
	else
	{
		warning("Runtime error: the expressions are not numeric for", "Modulo");
	}

  return result;
}



///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

void lp::PowerNode::printAST() 
{
  //std::cout << "PowerNode: ^"  << std::endl;
  //std::cout << "\t"; 
	//this->_left->printAST();
	//std::cout << "\t"; 
	//this->_right->printAST();
}

double lp::PowerNode::evaluateNumber() 
{
	double result = 0.0;

	// Ckeck the types of the expressions
	if (this->getType() == NUMBER)
	{
		result = pow(this->_left->evaluateNumber(), this->_right->evaluateNumber());
	}
	else
	{
		warning("Runtime error: the expressions are not numeric for", "Power");
	}

  return result;
}

///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////
int lp::BuiltinFunctionNode_0::getType()
{
	return	NUMBER;
}


void lp::BuiltinFunctionNode_0::printAST() 
{
  //std::cout << "BuiltinFunctionNode_0: "  << std::endl;
  //std::cout << "\t";
  //std::cout << this->_id << std::endl;
}

double lp::BuiltinFunctionNode_0::evaluateNumber() 
{
	// Get the identifier in the table of symbols as BuiltinParameter0
	lp::BuiltinParameter0 *f = (lp::BuiltinParameter0 *) table.getSymbol(this->_id);

	// Apply the function and copy the result
   	return f->getFunction()();
}


///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

int lp::BuiltinFunctionNode_1::getType()
{
	int result = 0;
		
	if (this->_exp->getType() == NUMBER)
		result = NUMBER;
	else
		warning("Runtime error: incompatible type for", "BuiltinFunctionNode_1");

	return	result;
}

void lp::BuiltinFunctionNode_1::printAST() 
{
  //std::cout << "BuiltinFunctionNode_1: "  << std::endl;
  //std::cout << "\t";
  //std::cout << this->_id << std::endl;  
  //std::cout << "\t";
	//this->_exp->printAST();
	//std::cout << std::endl;
}

double lp::BuiltinFunctionNode_1::evaluateNumber() 
{
	double result = 0.0;

	// Ckeck the type of the expression
	if (this->getType() == NUMBER)
	{
		// Get the identifier in the table of symbols as BuiltinParameter1
		lp::BuiltinParameter1 *f = (lp::BuiltinParameter1 *) table.getSymbol( this->_id);

		// Apply the function to the parameter and copy the result
		result = f->getFunction()(this->_exp->evaluateNumber());
	}
	else
	{
		warning("Runtime error: incompatible type of parameter for ", this->_id);
	}

	return result;
}


///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

int lp::BuiltinFunctionNode_2::getType()
{
	int result = 0;
		
	if (this->_exp1->getType() == this->_exp2->getType())
		result = this->_exp1->getType();
	else
		warning("Runtime error: incompatible types for", "BuiltinFunctionNode_2");

	return	result;
}


void lp::BuiltinFunctionNode_2::printAST() 
{
  //std::cout << "BuiltinFunctionNode_2: " << std::endl;
  //std::cout << "\t";
  //std::cout << this->_id << std::endl;
  //std::cout << "\t";
  //this->_exp1->printAST();
  //std::cout << "\t";
	//this->_exp2->printAST();
	//std::cout << std::endl;
}

double lp::BuiltinFunctionNode_2::evaluateNumber() 
{
	double result = 0.0;

	// Ckeck the types of the expressions
	if (this->getType() == NUMBER)
	{
		// Get the identifier in the table of symbols as BuiltinParameter2
		lp::BuiltinParameter2 *f = (lp::BuiltinParameter2 *) table.getSymbol(this->_id);

		// Apply the function to the parameters and copy the result
	  	result = f->getFunction()(this->_exp1->evaluateNumber(),this->_exp2->evaluateNumber());
	}
	else
	{
		warning("Runtime error: incompatible types of parameters for ", this->_id);
	}

  return result;
}



///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

void lp::GreaterThanNode::printAST()
{
  //std::cout << "GreaterThanNode: >" << std::endl;
  //std::cout << "\t"; 
	//this->_left->printAST();
	//std::cout << "\t"; 
	//this->_right->printAST();
}

bool lp::GreaterThanNode::evaluateBool() 
{
	bool result = false;

	if (this->getType() == BOOL)
	{
		double leftNumber, rightNumber;
		leftNumber = this->_left->evaluateNumber();
		rightNumber = this->_right->evaluateNumber();

		result = (leftNumber > rightNumber);
	}
	else if(this->getType() == STRING)
	{
		std::string leftString, rightString;
		leftString = this->_left->evaluateString();
		rightString = this->_right->evaluateString();

		result = (leftString > rightString);
	}
	else if(this->getType() == NUMBER)
	{
		double leftNumber, rightNumber;
		leftNumber = this->_left->evaluateNumber();
		rightNumber = this->_right->evaluateNumber();

		result = (leftNumber > rightNumber);
	}
	else
	{
		warning("Runtime error: incompatible types of parameters for ", "operator Greater than");
	}

	return result;
}


///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

void lp::GreaterOrEqualNode::printAST()
{
  //std::cout << "GreaterOrEqualNode: >= " << std::endl;
  //std::cout << "\t"; 
	//this->_left->printAST();
	//std::cout << "\t"; 
	//this->_right->printAST();
}

bool lp::GreaterOrEqualNode::evaluateBool() 
{
	bool result = false;

	if (this->getType() == BOOL)
	{
		double leftNumber, rightNumber;
		leftNumber = this->_left->evaluateNumber();
		rightNumber = this->_right->evaluateNumber();

		result = (leftNumber >= rightNumber);
	}
	else if(this->getType() == STRING)
	{
		std::string leftString, rightString;
		leftString = this->_left->evaluateString();
		rightString = this->_right->evaluateString();

		result = (leftString >= rightString);
	}
	else if(this->getType() == NUMBER)
	{
		double leftNumber, rightNumber;
		leftNumber = this->_left->evaluateNumber();
		rightNumber = this->_right->evaluateNumber();

		result = (leftNumber >= rightNumber);
	}
	else
	{
		warning("Runtime error: incompatible types of parameters for ", "operator Greater or equal than");
	}

	return result;
}



///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

void lp::LessThanNode::printAST()
{
  //std::cout << "LessThanNode: <" << std::endl;
  //std::cout << "\t"; 
	//this->_left->printAST();
	//std::cout << "\t"; 
	//this->_right->printAST();
}

bool lp::LessThanNode::evaluateBool() 
{
	bool result = false;

	if (this->getType() == BOOL)
	{
		double leftNumber, rightNumber;
		leftNumber = this->_left->evaluateNumber();
		rightNumber = this->_right->evaluateNumber();

		result = (leftNumber < rightNumber);
	}
	else if(this-> getType() == STRING)
	{
		std::string leftString, rightString;
		leftString = this->_left->evaluateString();
		rightString = this->_right->evaluateString();

		result = (leftString < rightString);
	}
	else
	{
		warning("Runtime error: incompatible types of parameters for ", "operator Less than");
	}

	return result;
}


///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

void lp::LessOrEqualNode::printAST()
{
  //std::cout << "LessOrEqualNode: <=" << std::endl;
  //std::cout << "\t"; 
	//this->_left->printAST();
	//std::cout << "\t"; 
	//this->_right->printAST();
}

bool lp::LessOrEqualNode::evaluateBool() 
{
	bool result = false;

	if (this->getType() == BOOL)
	{
		double leftNumber, rightNumber;
		leftNumber = this->_left->evaluateNumber();
		rightNumber = this->_right->evaluateNumber();

		result = (leftNumber <= rightNumber);
	}
	else if(this->getType() == STRING)
	{
		std::string leftString, rightString;
		leftString = this->_left->evaluateString();
		rightString = this->_right->evaluateString();

		result = (leftString <= rightString);
	}
	else if(this->getType() == NUMBER)
	{
		double leftNumber, rightNumber;
		leftNumber = this->_left->evaluateNumber();
		rightNumber = this->_right->evaluateNumber();

		result = (leftNumber <= rightNumber);
	}
	else
	{
		warning("Runtime error: incompatible types of parameters for ", "operator Less or equal than");
	}

	return result;
}



///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

void lp::EqualNode::printAST()
{
  //std::cout << "EqualNode: ==" << std::endl;
  //std::cout << "\t"; 
	//this->_left->printAST();
	//std::cout << "\t"; 
	//this->_right->printAST();
}


bool lp::EqualNode::evaluateBool() 
{
	bool result = false;
	std::string leftString, rightString;
	bool leftBool, rightBool;
	
	if (this->getType() == BOOL)
	{
		switch(this->_left->getType())
		{
			case NUMBER:
				double leftNumber, rightNumber;
				leftNumber = this->_left->evaluateNumber();
				rightNumber = this->_right->evaluateNumber();

				// ERROR_BOUND to control the precision of real numbers
				result = ( std::abs(leftNumber - rightNumber) < ERROR_BOUND );
				break;
			case STRING:
				leftString = this->_left->evaluateString();
				rightString = this->_right->evaluateString();

				result = (leftString == rightString);
				break;

			case BOOL:
				leftBool = this->_left->evaluateBool();
				rightBool = this->_right->evaluateBool();

				result = (leftBool == rightBool);
				break;
		  	default:
				warning("Runtime error: incompatible types of parameters for ", 
								"Equal operator");				
		}
	}
	else
	{
		warning("Runtime error: incompatible types of parameters for ", 
						"Equal operator");
	}

	return result;
}


///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

void lp::NotEqualNode::printAST()
{
  //std::cout << "NotEqualNode: !=" << std::endl;
  //std::cout << "\t"; 
	//this->_left->printAST();
	//std::cout << "\t"; 
	//this->_right->printAST();
}

bool lp::NotEqualNode::evaluateBool() 
{
	bool result = false;
	std::string leftString, rightString;
	bool leftBoolean, rightBoolean;
	
	if (this->getType() == BOOL)
	{
		switch(this->_left->getType()){
			case NUMBER:
				double leftNumber, rightNumber;
				leftNumber = this->_left->evaluateNumber();
				rightNumber = this->_right->evaluateNumber();

				// ERROR_BOUND to control the precision of real numbers
				result = ( std::abs(leftNumber - rightNumber) >= ERROR_BOUND );
				break;
			case STRING:
				leftString = this->_left->evaluateString();
				rightString = this->_right->evaluateString();

				result = (leftString != rightString);
				break;
			case BOOL:			
				leftBoolean = this->_left->evaluateBool();
				rightBoolean = this->_right->evaluateBool();
				
				result = (leftBoolean != rightBoolean);
				break;
		  default:
				warning("Runtime error: incompatible types of parameters for ", 
								"Not Equal operator");				
		}
	}
	else
	{
		warning("Runtime error: incompatible types of parameters for ", "Not Equal operator");
	}

	return result;
}



///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

void lp::AndNode::printAST()
{
  //std::cout << "AndNode: &&" << std::endl;
  //std::cout << "\t"; 
	//this->_left->printAST();
	//std::cout << "\t"; 
	//this->_right->printAST();
}

bool lp::AndNode::evaluateBool() 
{
	bool result = false;

	if (this->getType() == BOOL)
	{
		bool leftBool, rightBool;

		leftBool = this->_left->evaluateBool();
		rightBool = this->_right->evaluateBool();

		result = leftBool and rightBool;
	}
	else
	{
		warning("Runtime error: incompatible types of parameters for ", "operator And");
	}

	return result;
}



///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

void lp::OrNode::printAST()
{
  //std::cout << "OrNode: <>" << std::endl;
  //std::cout << "\t"; 
	//this->_left->printAST();
	//std::cout << "\t"; 
	//this->_right->printAST();
}

bool lp::OrNode::evaluateBool() 
{
	bool result = false;

	if (this->getType() == BOOL)
	{
		bool leftBool, rightBool;

		leftBool = this->_left->evaluateBool();
		rightBool = this->_right->evaluateBool();

		result = leftBool or rightBool;
	}
	else
	{
		warning("Runtime error: incompatible types of parameters for ", "operator Or");
	}

	return result;
}



///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

void lp::NotNode::printAST()
{
  //std::cout << "NotNode: !" << std::endl;
  //std::cout << "\t";
  //this->_exp->printAST();
}

bool lp::NotNode::evaluateBool() 
{
	bool result = false;

	if (this->getType() == BOOL)
	{
		result = not this->_exp->evaluateBool();
	}
	else
	{
		warning("Runtime error: incompatible types of parameters for ", "operator Not");
	}

	return result;
}


///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

void lp::AssignmentStmt::printAST() 
{
  //std::cout << "assignment_node: ="  << std::endl;
  //std::cout << "\t";
  //std::cout << this->_id << std::endl;
  //std::cout << "\t";

  // Check the expression
	//if (this->_exp != NULL)
	//{
	  //this->_exp->printAST();
    //std::cout << std::endl;
  //}
  // this->_asgn is not NULL: multiple assingment
  //else 
    //this->_asgn->printAST();

}


// TODO: AÑADIR STRINGS!!!
void lp::AssignmentStmt::evaluate() 
{
	/* Get the identifier in the table of symbols as Variable */
	/* 
		a = 2;
		a = b = 2;

		a: firstVar
		b: secondVar
	*/
	lp::Variable *firstVar = (lp::Variable *) table.getSymbol(this->_id);

	// Check the expression
	if (this->_exp != NULL)
	{
		// Check the type of the expression of the asgn
		switch(this->_exp->getType())
		{
			case NUMBER:
			{
				double value;
				// evaluate the expression as NUMBER
			 	value = this->_exp->evaluateNumber();

				// Check the type of the first varible
				if (firstVar->getType() == NUMBER)
				{
				  	// Get the identifier in the table of symbols as NumericVariable
					lp::NumericVariable *v = (lp::NumericVariable *) table.getSymbol(this->_id);

					// Assignment the value to the identifier in the table of symbols
					v->setValue(value);
				}
				// The type of variable is not NUMBER
				else
				{
					// Delete the variable from the table of symbols 
					table.eraseSymbol(this->_id);

					// Insert the variable in the table of symbols as NumericVariable 
					// with the type NUMBER and the value 
					lp::NumericVariable *v = new lp::NumericVariable(this->_id,
											VARIABLE,NUMBER,value);
					table.installSymbol(v);
				}
			}
			break;
			case STRING:
			{
				std::string value;
				value = this->_exp->evaluateString();

				if(firstVar->getType() == STRING)
				{
					lp::StringVariable *v = (lp::StringVariable *) table.getSymbol(this->_id);
					v->setValue(value);
				}
				else
				{
					table.eraseSymbol(this->_id);
					lp::StringVariable *v = new lp::StringVariable(this->_id,
											VARIABLE,STRING,value);
					table.installSymbol(v);
				}
			}
			break;
			case BOOL:
			{
				bool value;
				// evaluate the expression as BOOL
			 	value = this->_exp->evaluateBool();

				if (firstVar->getType() == BOOL)
				{
				  	// Get the identifier in the table of symbols as LogicalVariable
					lp::LogicalVariable *v = (lp::LogicalVariable *) table.getSymbol(this->_id);

					// Assignment the value to the identifier in the table of symbols
					v->setValue(value);
				}
				// The type of variable is not BOOL
				else
				{
					// Delete the variable from the table of symbols 
					table.eraseSymbol(this->_id);

					// Insert the variable in the table of symbols as NumericVariable 
					// with the type BOOL and the value 
					lp::LogicalVariable *v = new lp::LogicalVariable(this->_id,
											VARIABLE,BOOL,value);
					table.installSymbol(v);
				}
			}
			break;

			default:
				warning("Runtime error: incompatible type of expression for ", "Assigment");
		}

	}

	//////////////////////////////////////////////
	// Allow multiple assigment -> a = b = c = 2;

	else // this->_asgn is not NULL
	{
		// IMPORTANT
		//  evaluate the assigment child
		this->_asgn->evaluate();


		/* Get the identifier of the previous asgn in the table of symbols as Variable */
		lp::Variable *secondVar = (lp::Variable *) table.getSymbol(this->_asgn->_id);

		// Get the type of the variable of the previous asgn
		switch(secondVar->getType())
		{
			case NUMBER:
			{
				/* Get the identifier of the previous asgn in the table of symbols as NumericVariable */
				lp::NumericVariable *secondVar = (lp::NumericVariable *) table.getSymbol(this->_asgn->_id);
				// Check the type of the first variable
				if (firstVar->getType() == NUMBER)
				{
				/* Get the identifier of the first variable in the table of symbols as NumericVariable */
				lp::NumericVariable *firstVar = (lp::NumericVariable *) table.getSymbol(this->_id);
				  	// Get the identifier o f the in the table of symbols as NumericVariable
//					lp::NumericVariable *n = (lp::NumericVariable *) table.getSymbol(this->_id);

					// Assignment the value of the second variable to the first variable
					firstVar->setValue(secondVar->getValue());

				}
				// The type of variable is not NUMBER
				else
				{
					// Delete the first variable from the table of symbols 
					table.eraseSymbol(this->_id);

					// Insert the first variable in the table of symbols as NumericVariable 
					// with the type NUMBER and the value of the previous variable 
					lp::NumericVariable *firstVar = new lp::NumericVariable(this->_id,
											VARIABLE,NUMBER,secondVar->getValue());
					table.installSymbol(firstVar);
				}
			}
			break;

			case BOOL:
			{
				/* Get the identifier of the previous asgn in the table of symbols as LogicalVariable */
				lp::LogicalVariable *secondVar = (lp::LogicalVariable *) table.getSymbol(this->_asgn->_id);
				// Check the type of the first variable
				if (firstVar->getType() == BOOL)
				{
				/* Get the identifier of the first variable in the table of symbols as LogicalVariable */
				lp::LogicalVariable *firstVar = (lp::LogicalVariable *) table.getSymbol(this->_id);
				  	// Get the identifier o f the in the table of symbols as NumericVariable
//					lp::NumericVariable *n = (lp::NumericVariable *) table.getSymbol(this->_id);

					// Assignment the value of the second variable to the first variable
					firstVar->setValue(secondVar->getValue());

				}
				// The type of variable is not BOOL
				else
				{
					// Delete the first variable from the table of symbols 
					table.eraseSymbol(this->_id);

					// Insert the first variable in the table of symbols as NumericVariable 
					// with the type BOOL and the value of the previous variable 
					lp::LogicalVariable *firstVar = new lp::LogicalVariable(this->_id,
											VARIABLE,BOOL,secondVar->getValue());
					table.installSymbol(firstVar);
				}
			}
			break;
			case STRING:
			{
				lp::StringVariable *secondVar = (lp::StringVariable *) table.getSymbol(this->_asgn->_id);

				if(firstVar->getType() == STRING)
				{
					lp::StringVariable *v = (lp::StringVariable *) table.getSymbol(this->_id);
					v->setValue(secondVar->getValue());
				}
				else
				{
					table.eraseSymbol(this->_id);
					lp::StringVariable *v = new lp::StringVariable(this->_id,
											VARIABLE,STRING,secondVar->getValue());
					table.installSymbol(v);
				}
			}
			break;

			default:
				warning("Runtime error: incompatible type of expression for ", "Assigment");
		}
	}
}


///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

void lp::PrintStmt::printAST() 
{
  //std::cout << "printASTStmt: printAST"  << std::endl;
  //std::cout << "\t";
  //this->_exp->printAST();
  //std::cout << std::endl;
}


void lp::PrintStmt::evaluate() 
{
	std::cout << BIYELLOW; 
	std::cout << RESET; 

	switch(this->_exp->getType())
	{
		case NUMBER:
				std::cout << this->_exp->evaluateNumber() << std::endl;
				break;
		case BOOL:
			if (this->_exp->evaluateBool())
				std::cout << "true" << std::endl;
			else
				std::cout << "false" << std::endl;
		
			break;
		case STRING:
			std::cout << this->_exp->evaluateString() << std::endl;
			break;

		
		/*NEW in v 0.13*/		
		case UNDEFINED:
        	execerror("Runtime error: trying to print a not defined variable", "The variable is not defined");

		default:
			warning("Runtime error: incompatible type for ", "print");
	}
}

/*NEW in v 0.10*/

void lp::PrintStringStmt::printAST() 
{
  //std::cout << "printStringStmt: "  << std::endl;
  //std::cout << this->_exp;
  //std::cout << std::endl;
}

void lp::PrintStringStmt::evaluate()
{
	std::string var = this->_exp->evaluateString();

	if (this->_exp->getType() == STRING)
	{
		for(size_t index = 0; index < var.size(); index++)
		{
			if (var[index] == '\\' and var[index+1] == 'n')
			{
				std::cout << "\n";
				index++;
			}
			else if (var[index] == '\\' and var[index+1] == 't')
			{
				std::cout << "\t";
				index++;
			}
			else if (var[index] == '\\' and var[index+1] == '\'')
			{
				std::cout << "\'";
				index++;
			}
			else if (var[index] == '\\')
			{
				std::cout << var[index+1];
				index++;
			}
			else
				std::cout << var[index];
		}
	}
	/*NEW in v 0.13*/
	else if(this->_exp->getType() == UNDEFINED)
	{
		execerror("Runtime error: trying to print a not defined variable", "The variable is not defined", "Note: use 'type_of' if variable has not been defined"); 
	}
	else
		execerror("Runtime error: incompatible type for print_string", "Use alphanumerical variables", 
					"Note: Use print_string for this datatype");

}

///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

void lp::ReadStmt::printAST() 
{
  //std::cout << "ReadStmt: read"  << std::endl;
  //std::cout << "\t";
  //std::cout << this->_id;
  //std::cout << std::endl;
}


void lp::ReadStmt::evaluate() 
{   
	double value;
	std::cout << BIYELLOW; 
	std::cout << RESET; 
	std::cin >> value;

	/* Get the identifier in the table of symbols as Variable */
	lp::Variable *var = (lp::Variable *) table.getSymbol(this->_id);

	// Check if the type of the variable is NUMBER
	if (var->getType() == NUMBER)
	{
		/* Get the identifier in the table of symbols as NumericVariable */
		lp::NumericVariable *n = (lp::NumericVariable *) table.getSymbol(this->_id);
						
		/* Assignment the read value to the identifier */
		n->setValue(value);
	}
	// The type of variable is not NUMBER
	else
	{
		// Delete $1 from the table of symbols as Variable
		table.eraseSymbol(this->_id);

			// Insert $1 in the table of symbols as NumericVariable 
		// with the type NUMBER and the read value 
		lp::NumericVariable *n = new lp::NumericVariable(this->_id, 
									  VARIABLE,NUMBER,value);

		table.installSymbol(n);
	}
}

/*NEW in v 0.10*/
void lp::ReadStringStmt::printAST() 
{
  //std::cout << "ReadStringStmt: read_string"  << std::endl;
  //std::cout << this->_id;
  //std::cout << std::endl;
}

void lp::ReadStringStmt::evaluate()
{
	std::string value;

	std::cout << BIYELLOW; 
	std::cout << RESET; 

	std::getline(std::cin, value);

	lp::Variable *var = (lp::Variable *) table.getSymbol(this->_id);
	
	if(var->getType() != STRING)
	{
		table.eraseSymbol(this->_id);

		lp::StringVariable *n = new lp::StringVariable(this->_id, 
									  VARIABLE,STRING,value);

		table.installSymbol(n);
	}
	else
	{
		lp::StringVariable *n = (lp::StringVariable *) table.getSymbol(this->_id);
		n->setValue(value);
		std::cout << n->getValue() << std::endl;
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

void lp::EmptyStmt::printAST() 
{
  //std::cout << "EmptyStmt "  << std::endl;
}

void lp::EmptyStmt::evaluate() 
{
  // Empty
}


///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////
// NEW in example 17

void lp::IfStmt::printAST() 
{
  /**
  std::cout << "IfStmt: "  << std::endl;
  // Condition
  std::cout << "\t";
  this->_cond->printAST();

  // Consequent
  std::cout << "\t";
  this->_block1->printAST();

 // The alternative is printASTed if exists
  if (this->_block2 != NULL)
     {  
       std::cout << "\t";
	   this->_block2->printAST();
     }

  std::cout << std::endl;
  **/
}


void lp::IfStmt::evaluate() 
{
   // If the condition is true,
	if (this->_cond->evaluateBool() == true )
     // the consequent is run 
	  this->_block1->evaluate();

    // Otherwise, the alternative is run if exists
	else if (this->_block2 != NULL)
		  this->_block2->evaluate();
}




///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////
// NEW in example 17

void lp::WhileStmt::printAST() 
{
	/**
  std::cout << "WhileStmt: "  << std::endl;
  // Condition
  std::cout << "\t";
  this->_cond->printAST();

  // Body of the while loop
  std::cout << "\t";
  this->_block->printAST();

  std::cout << std::endl;
  */
}


void lp::WhileStmt::evaluate() 
{
  // While the condition is true. the body is run 
  while (this->_cond->evaluateBool() == true)
  {	
	  this->_block->evaluate();
  }

}


/* NEW in version 0.3*/

void lp::ForStmt::printAST() 
{
	/**
   std::cout << "ForStmt: " << std::endl;
    // Variable to initialize
    std::cout << "\t";
    std::cout << this->_var;

    // Since expresion
    std::cout << "\t";
    this->_from->printAST();

    // Until expresion
    std::cout << "\t";
    this->_to->printAST();

    // Body statement
    std::cout << "\t";

    std::cout << std::endl;
	*/
}

void lp::ForStmt::evaluate()
{

    lp::NumericVariable *n = new lp::NumericVariable();

    // Check if Var exists in the table of symbols
    if (table.lookupSymbol(this->_var) == false)
        n = new lp::NumericVariable(this->_var, VARIABLE, NUMBER, this->_from->evaluateNumber());

    else
    {
        lp::Variable *var = (lp::Variable *)table.getSymbol(this->_var);

        if (var->getType() == NUMBER)
            n = (lp::NumericVariable *)table.getSymbol(this->_var);

        else
        {
            // Delete $1 from the table of symbols as Variable
            table.eraseSymbol(this->_var);

            // Insert $1 in the table of symbols as NUMBERVariable
            // with the type NUMBER and the read value
            n = new lp::NumericVariable(this->_var, VARIABLE, NUMBER);

            table.installSymbol(n);
        }

        n->setValue(this->_from->evaluateNumber());
    }
	
	//Initialize the variable
    double step = 1.0;

	/*NEW in v0.13*/
	if (this->_step != NULL and this->_step->evaluateNumber() <= ERROR_BOUND)
        execerror("Runtime error", "Step has no increment, infinite loop", "Note: Use step default value");

    if (this->_step != NULL)
        step = this->_step->evaluateNumber();

    for (; n->getValue() <= this->_to->evaluateNumber(); n->setValue(n->getValue() + step))
    {
        this->_block->evaluate();
    }
}


///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

//NEW in v 0.6

void lp::RepeatStmt::printAST() 
{
	/**
  std::cout << "RepeatStmt: "  << std::endl;
  // Condition
  std::cout << "\t";
  this->_cond->printAST();

  // Body of the while loop
  std::cout << "\t";
  this->_block->printAST();

  std::cout << std::endl;
  */
}

void lp::RepeatStmt::evaluate()
{
	do{
		this->_block->evaluate();
	}while(this->_cond->evaluateBool() == false);
}



///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////



// New in v 0.5

void lp::ClearScreenStmt::printAST() 
{
  //std::cout << "ClearScreenStmt:"  << std::endl;
}

void lp::ClearScreenStmt::evaluate() 
{
  std::cout << CLEAR_SCREEN;
}


// New in v 0.8

void lp::PlaceStmt::printAST() 
{
	/**
  std::cout << "PlaceStmt:"  << std::endl;

  this->_x->printAST();
  std::cout << "\t";
  this->_y->printAST();
  */
}

void lp::PlaceStmt::evaluate() 
{
  if (this->_x->getType() == NUMBER && this->_y->getType() == NUMBER)
  {
	PLACE((int)this->_x->evaluateNumber(), (int)this->_y->evaluateNumber());
  }
  else
  {
	execerror("Runtime error", "The variable is not a number", "Note: Use a number");
  }
  
}

///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

/*NEW in version 0.11*/

void lp::TypeOfStmt::printAST()
{
	/**
	std::cout << "TypeOfStmt: print" << std::endl;
	std::cout << "\t";
	this->_exp->printAST();
	std::cout << std::endl;
	*/
}

void lp::TypeOfStmt::evaluate()
{
	switch(this->_exp->getType()){
		case NUMBER:
			std::cout<<"Numero"<< std::endl;
			break;

		case BOOL:
			std::cout<<"Booleano"<< std::endl;
			break;
		
		case STRING:
			std::cout<<"Cadena"<< std::endl;
			break;
		
		default:
			std::cout<<"Indefinido"<< std::endl;
			break;
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////
/*NEW in version 0.11*/

void lp::BlockCaseValueNode::printAST()
{
	/**
	std::list<ValueNode *>::iterator itr;
	std::cout <<"ValuesStmt" <<std::endl;
	std::cout <<"\t";
	this->_exp->printAST();
	std::cout << "\t";

	for(itr = this->_values->begin(); itr != this->_values->end(); itr++)
	{
		(*itr)->printAST();
	}
	if(this->_defaultValue != NULL)
	{
		this->_defaultValue->printAST();
	}
	std::cout << std::endl;
	*/
}


void lp::BlockCaseValueNode::evaluate()
{
	int type = this->_exp->getType();
	std::list<ValueNode *>::iterator valueIter;
	bool enteredValue = false;

	for(valueIter = this->_values->begin(); valueIter != this->_values->end(); valueIter ++)
	{
		if((*valueIter)->getType() == type)
		{

			switch(type){
				case NUMBER:
				{
					if((*valueIter)->getExp()->evaluateNumber() == this->_exp->evaluateNumber())
					{
						(*valueIter)->evaluate();
						enteredValue = true;
					}
				}
				break;

				case BOOL:
				{
					if((*valueIter)->getExp()->evaluateBool() == this->_exp->evaluateBool())
					{
						(*valueIter)->evaluate();
						enteredValue = true;
					}
				}
				break;

				case STRING:
				{
					if((*valueIter)->getExp()->evaluateString() == this->_exp->evaluateString())
					{
						(*valueIter)->evaluate();
						enteredValue = true;
					}
				}
				break;

				default:
                	warning("Runtime error: incompatible types for ", "valor");
				}
			}
			else{
				warning("Runtime error: incompatible types for ", "valor");
			}
		}
		if(!enteredValue && this->_defaultValue != NULL)
		{
			this->_defaultValue->evaluate();
		}
}


int lp::ValueNode::getType()
{
	return this->_exp->getType();
}

void lp::ValueNode::printAST()
{
	/**
	std::list<Statement *>::iterator itr;
	std::cout << "ValueNode: " << std::endl;
	std::cout << "\t";
	for(itr = this->_stmtList->begin(); itr !=this->_stmtList->end(); itr++){
		(*itr)->printAST();
	}
	std::cout << std::endl;
	*/
}

void lp::ValueNode::evaluate()
{
	std::list<Statement *>::iterator itr;

	for(itr = this->_stmtList->begin(); itr !=this->_stmtList->end(); itr++){
		(*itr)->evaluate();
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////
// NEW in example 17

void lp::BlockStmt::printAST() 
{
	/**
  std::list<Statement *>::iterator stmtIter;

  std::cout << "BlockStmt: "  << std::endl;

  for (stmtIter = this->_stmts->begin(); stmtIter != this->_stmts->end(); stmtIter++) 
  {
     (*stmtIter)->printAST();
  }
	*/
}


void lp::BlockStmt::evaluate() 
{
  std::list<Statement *>::iterator stmtIter;

  for (stmtIter = this->_stmts->begin(); stmtIter != this->_stmts->end(); stmtIter++) 
  {
    (*stmtIter)->evaluate();
  }
}


int lp::StringOperatorNode::getType()
{
	if(this->_left->getType() == STRING && this->_right->getType() == STRING)
		return STRING;
	else
		execerror("Runtime error: expressions are not string", "The expresions have incompatible types", "Note: Use strings");
		return UNDEFINED;
}


///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

void lp::AST::printAST() 
{
	/**
  std::list<Statement *>::iterator stmtIter;

  for (stmtIter = stmts->begin(); stmtIter != stmts->end(); stmtIter++) 
  {
     (*stmtIter)->printAST();
  }
  */
}



void lp::AST::evaluate() 
{
  std::list<Statement *>::iterator stmtIter;

  for (stmtIter = stmts->begin(); stmtIter != stmts->end(); stmtIter++) 
  {
    (*stmtIter)->evaluate();
  }
}
