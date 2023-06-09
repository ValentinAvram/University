/*! 
  \file interpreter.l
  \brief Lexical or scanner file
*/
%{
#include <iostream>

/*********************/
/* NEW in example 4 */
/* atof */
#include <stdlib.h>
/*********************/

/* Macros for the screen */
#include "../includes/macros.hpp"

/*********************/
/* 
  NEW in example 16 
  AST class
  IMPORTANT: must be written before interpreter.tab.h
*/
#include "../ast/ast.hpp"
/*********************/

/*  interpreter.tab.h contains the numeric values of the tokens produced by the parser */
#include "interpreter.tab.h"

/********************/
/* NEW in example 3 */
#include "../error/error.hpp"
/********************/


/*********************************************/
/* NEW in example 7 */
/* Table of symbols */
#include "../table/table.hpp"

#include "../table/numericVariable.hpp"

extern lp::Table table; //!< External Table of Symbols
/*********************************************/

extern int lineNumber; //!< External line counter

/* uso de strdup */
#include <string.h>


%}


/*! \name REGULAR DEFINITIONS  */

DIGIT  [0-9] 

LETTER [a-zA-Z]

/*! TODO: NUMBER IN SCINOT AND ITS ERROR*/

NUMBER1 {DIGIT}+\.?

NUMBER2 {DIGIT}*\.{DIGIT}+

SCIENTIFIC_NOTATION {DIGIT}+(\.{DIGIT}+)?((e|E)[+-]?{DIGIT}+)?

/*! NEW in v0.13*/

NUMBER_WRONG1			{DIGIT}+\.\.+{DIGIT}*   

NUMBER_WRONG2			{DIGIT}+(e|E)(e|E)+{DIGIT}*


NUMBER_WRONG3			{DIGIT}+(e|E)(\+|\-)(\+|\-)+{DIGIT}*


IDENTIFIER  {LETTER}({LETTER}|{DIGIT}|_({LETTER}|{DIGIT}))*

/*! NEW in v0.13*/

IDENTIFIER_WRONG1		_({LETTER}|{DIGIT}|_({LETTER}|{DIGIT}))*

IDENTIFIER_WRONG2		{LETTER}({LETTER}|{DIGIT}|_({LETTER}|{DIGIT}))*_

IDENTIFIER_WRONG3		{LETTER}({LETTER}|{DIGIT}|__({LETTER}|{DIGIT}))+

CHARSTRING "'"([^'\\]|"\\\'"|\\n|\\t)*"'"

/* NEW in example 3 */
/* STATE  */
%x ERROR
%x COMMENTS

/*! \name REGULAR EXPRESSIONS  */
%%

[ \t] 	{ ; }    /* skip white space and tabular */

\n      { 
			/* Line counter */
           	lineNumber++; 
	
			/* MODIFIED in example 3 */
			/* COMMENTED in example 5 */
        	/* return NEWLINE; */
        }

";"		{
			/* NEW in example 5 */
			return SEMICOLON;
		}


","		{
			/* NEW in example 14 */
			return COMMA;
		}

{NUMBER1}|{NUMBER2}|{SCIENTIFIC_NOTATION}  {
					  /* MODIFIED in example 4 */
					  /* Conversion of type and sending of the numerical value to the parser */
					   yylval.number = atof(yytext); 

                       return NUMBER; 
                      }



{NUMBER_WRONG1}					{
									execerror("Lexical error:", "Misspelled floating number", "Note: Removes the second decimal point. Use the format expression \'{DIGIT}+(.{DIGIT}+)\'");
								}

{NUMBER_WRONG2}					{
									execerror("Lexical error:", "Power misspelled", "Note: Removes the second exponent. Use the format expression \'{DIGIT}+(.{DIGIT}+)?((e|E)[+-]?{DIGIT}+)?\'");
								}

{NUMBER_WRONG3}					{
									execerror("Lexical error:", "Power misspelled", "Note: Removes the second exponent sign. Use the format expression \'{DIGIT}+(.{DIGIT}+)?((e|E)[+-]?{DIGIT}+)?\'");
								}


(?i:for)	{	
				return FOR;
			}

(?i:if)	{	
				return IF;
			}

(?i:then)	{	
				return THEN;
			}

(?i:end_if)	{	
				return END_IF;
			}

(?i:else)	{	
				return ELSE;
			}

(?i:while)	{	
				return WHILE;
			}

(?i:do)	{	
				return DO;
			}

(?i:end_while)	{	
				return END_WHILE;
			}

(?i:repeat)	{	
				return REPEAT;
			}

(?i:until)	{	
				return UNTIL;
			}

(?i:end_for)	{	
				return END_FOR;
			}

(?i:from)	{	
				return FROM;
			}

(?i:step)	{	
				return STEP;
			}

(?i:to)	{	
				return TO;
			}

(?i:case)	{	
				return CASE;
			}

(?i:value)	{	
				return VALUE;
			}

(?i:and)	{	
				return AND;
			}

(?i:not)	{	
				return NOT;
			}

(?i:or)	{	
				return OR;
			}

(?i:read)	{	
				return READ;
			}

(?i:read_string)	{	
				return READ_STRING;
			}

(?i:print)	{	
				return PRINT;
			}

(?i:print_string)	{	
				return PRINT_STRING;
			}

(?i:clear_screen)	{	
				return CLEAR_SCREEN_TOKEN;
			}

(?i:place)	{	
				return PLACE_TOKEN;
			}

(?i:default)	{	
				return DEFAULT;
			}

(?i:end_case)	{	
				return END_CASE;
			}

(?i:type_of)	{	
				return TYPE_OF;
			}
{IDENTIFIER}   		 {
						 /* NEW in example 7 */
						 std::string identifier(yytext);

						/* 
                          strdup() function returns a pointer to a new string 
                          which  is a duplicate of the string yytext
                        */
						  yylval.string =  strdup(yytext);

						/* If the identifier is not in the table of symbols then it is inserted */	
						 if (table.lookupSymbol(identifier) == false)
							{
								/* 
								 The identifier is inserted into the symbol table 
								 as undefined Variable with value 0.0
								*/
								lp::NumericVariable *n = new lp::NumericVariable(identifier,VARIABLE,UNDEFINED,0.0);

								/* A pointer to the new NumericVariable is inserted into the table of symbols */
								table.installSymbol(n);

								return VARIABLE;
							}

						/* MODIFIED in example 11 */
						/* 
							If the identifier is in the table of symbols then its token is returned 
								The identifier can be a variable or a numeric constant
						*/
						else 
							{
								lp::Symbol	*s = table.getSymbol(identifier);

/*
								std::cout << "lex: "<< s->getName() 
										  << "token " << s->getToken()
  										  << std::endl; 
*/

								/*  If the identifier is in the table then its token is returned  */
								return s->getToken();
							}
                     }

{IDENTIFIER_WRONG1}		{
							execerror("Lexical error: Incorrectly spelled identifier", "The identifier cannot begin with an underscore", "Note: delete the first underscore");
						}

{IDENTIFIER_WRONG2}		{
							execerror("Lexical error: Incorrectly spelled identifier", "The identifier cannot end with an underscore", "Note: delete the last underscore");
						}

{IDENTIFIER_WRONG3}		{
							execerror("Lexical error: Incorrectly spelled identifier", "The identifier cannot have double underscores", "Note: delete one underscore");
						}	

{CHARSTRING}			{
						memmove(yytext, yytext+1, strlen(yytext)); 
						yytext[strlen(yytext)-1] = '\0'; 
						yylval.string = strdup(yytext);

						return STRING;
					}

	

"-"		{ return MINUS;  } 			/* NEW in example 3 */
"+"		{ return PLUS;   } 			/* NEW in example 3 */

"*"	    { return MULTIPLICATION; } 	/* NEW in example 3 */
"/"     { return DIVISION; } 		/* NEW in example 3 */
"//"	{ return INT_DIVISION; } 

"("     { return LPAREN; } 			/* NEW in example 3 */
")"	    { return RPAREN; } 			/* NEW in example 3 */



"%"     { return MODULO; }			/* NEW in example 5 */

"^"	    { return POWER; }			/* NEW in example 5 */

":=" 	{ return ASSIGNMENT; }		/* NEW in example 7 */


"="	{ return EQUAL; }			/* NEW in example 15 */

"<>"	{ return NOT_EQUAL; }		/* NEW in example 15 */

">="	{ return GREATER_OR_EQUAL; }/* NEW in example 15 */

"<="	{ return LESS_OR_EQUAL; }	/* NEW in example 15 */

">"		{ return GREATER_THAN; }	/* NEW in example 15 */

"<"		{ return LESS_THAN; }		/* NEW in example 15 */

"!"		{ return NOT; }				/* NEW in example 15 */

"||"	{ return CONCATENATION; }	

"&&"	{ return AND; }				/* NEW in example 15 */

"{"		{ return LETFCURLYBRACKET; } 	/* NEW in example 17 */

"}"		{ return RIGHTCURLYBRACKET; }	/* NEW in example 17 */

":"		{ return TWO_DOTS; }			/* V 0.9 */

#.*   { } /* V 0.9*/

"<<"	{
			BEGIN(COMMENTS);
			yymore();
		}

<COMMENTS>"<<"	{
					execerror("Nested comments are not allowed", yytext);
					BEGIN(INITIAL);
				}

<COMMENTS>[^>>\n]	{
						yymore();
					}

<COMMENTS>\n	{
					lineNumber++;
					yymore();
				}
<COMMENTS>">>"	{
					BEGIN(INITIAL);
				}

<COMMENTS><<EOF>	{
						execerror("End of file found in comment", "You must close the comment");
						return 0; 
					}

<<EOF>> { /* The interpreter finishes when finds the end of file character */
		/*  PLACE(24,10);
	  	  std::cout <<  BICYAN;
	   	  std::cout <<  ">>>>>>> End of program <<<<<<<";
	 	  std::cout << std::endl;
	   	  std::cout << RESET;
		  PLACE(25,1);
		*/
	  	  return 0;
	}


.								{ 	
									/* Any other character */
									/* MODIFIIED in example 3 */
									/* Change to error state */
									BEGIN(ERROR); 

									/* 
										The current value of yytext 
										will be concatenated with the next character
									*/
									yymore();  
								}

<ERROR>[^0-9+\-*/()\^% \t\n\;a-zA-Z=<>!&]	{ /* MODIFIED in examples 5, 7, 15 */
								  /* NEW in example 3 */
								  /* 
									The current value of yytext 
									will be concatenated with the next character
								  */
									yymore(); 
								}

<ERROR>(.|\n)                { 
								/* NEW in example 3 */
								/* Rewind one position */
								yyless(yyleng-1); 

								/* Display the error message */
								warning("Lexical error", yytext);

								/* Restore the initial state */
   								BEGIN(INITIAL);
							}



%%

