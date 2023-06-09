/* A Bison parser, made by GNU Bison 3.8.2.  */

/* Bison interface for Yacc-like parsers in C

   Copyright (C) 1984, 1989-1990, 2000-2015, 2018-2021 Free Software Foundation,
   Inc.

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <https://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.

   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

/* DO NOT RELY ON FEATURES THAT ARE NOT DOCUMENTED in the manual,
   especially those whose name start with YY_ or yy_.  They are
   private implementation details that can be changed or removed.  */

#ifndef YY_YY_INTERPRETER_TAB_H_INCLUDED
# define YY_YY_INTERPRETER_TAB_H_INCLUDED
/* Debug traces.  */
#ifndef YYDEBUG
# define YYDEBUG 1
#endif
#if YYDEBUG
extern int yydebug;
#endif

/* Token kinds.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
  enum yytokentype
  {
    YYEMPTY = -2,
    YYEOF = 0,                     /* "end of file"  */
    YYerror = 256,                 /* error  */
    YYUNDEF = 257,                 /* "invalid token"  */
    SEMICOLON = 258,               /* SEMICOLON  */
    PRINT = 259,                   /* PRINT  */
    READ = 260,                    /* READ  */
    IF = 261,                      /* IF  */
    ELSE = 262,                    /* ELSE  */
    WHILE = 263,                   /* WHILE  */
    FOR = 264,                     /* FOR  */
    PRINT_STRING = 265,            /* PRINT_STRING  */
    READ_STRING = 266,             /* READ_STRING  */
    THEN = 267,                    /* THEN  */
    END_IF = 268,                  /* END_IF  */
    DO = 269,                      /* DO  */
    END_WHILE = 270,               /* END_WHILE  */
    REPEAT = 271,                  /* REPEAT  */
    UNTIL = 272,                   /* UNTIL  */
    END_FOR = 273,                 /* END_FOR  */
    FROM = 274,                    /* FROM  */
    STEP = 275,                    /* STEP  */
    TO = 276,                      /* TO  */
    CASE = 277,                    /* CASE  */
    VALUE = 278,                   /* VALUE  */
    AND = 279,                     /* AND  */
    NOT = 280,                     /* NOT  */
    OR = 281,                      /* OR  */
    CLEAR_SCREEN_TOKEN = 282,      /* CLEAR_SCREEN_TOKEN  */
    PLACE_TOKEN = 283,             /* PLACE_TOKEN  */
    TYPE_OF = 284,                 /* TYPE_OF  */
    END_CASE = 285,                /* END_CASE  */
    DEFAULT = 286,                 /* DEFAULT  */
    LETFCURLYBRACKET = 287,        /* LETFCURLYBRACKET  */
    RIGHTCURLYBRACKET = 288,       /* RIGHTCURLYBRACKET  */
    ASSIGNMENT = 289,              /* ASSIGNMENT  */
    COMMA = 290,                   /* COMMA  */
    TWO_DOTS = 291,                /* TWO_DOTS  */
    NUMBER = 292,                  /* NUMBER  */
    BOOL = 293,                    /* BOOL  */
    STRING = 294,                  /* STRING  */
    VARIABLE = 295,                /* VARIABLE  */
    UNDEFINED = 296,               /* UNDEFINED  */
    CONSTANT = 297,                /* CONSTANT  */
    BUILTIN = 298,                 /* BUILTIN  */
    KEYWORD = 299,                 /* KEYWORD  */
    GREATER_OR_EQUAL = 300,        /* GREATER_OR_EQUAL  */
    LESS_OR_EQUAL = 301,           /* LESS_OR_EQUAL  */
    GREATER_THAN = 302,            /* GREATER_THAN  */
    LESS_THAN = 303,               /* LESS_THAN  */
    EQUAL = 304,                   /* EQUAL  */
    NOT_EQUAL = 305,               /* NOT_EQUAL  */
    PLUS = 306,                    /* PLUS  */
    MINUS = 307,                   /* MINUS  */
    CONCATENATION = 308,           /* CONCATENATION  */
    MULTIPLICATION = 309,          /* MULTIPLICATION  */
    DIVISION = 310,                /* DIVISION  */
    MODULO = 311,                  /* MODULO  */
    INT_DIVISION = 312,            /* INT_DIVISION  */
    LPAREN = 313,                  /* LPAREN  */
    RPAREN = 314,                  /* RPAREN  */
    UNARY = 315,                   /* UNARY  */
    POWER = 316                    /* POWER  */
  };
  typedef enum yytokentype yytoken_kind_t;
#endif

/* Value type.  */
#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
union YYSTYPE
{
#line 142 "interpreter.y"

  double number;
  char * string; 				 /* NEW in example 7 */
  bool logic;						 /* NEW in example 15 */
  lp::ExpNode *expNode;  			 /* NEW in example 16 */
  std::list<lp::ExpNode *>  *parameters;    // New in example 16; NOTE: #include<list> must be in interpreter.l, init.cpp, interpreter.cpp
  std::list<lp::Statement *> *stmts; /* NEW in example 16 */
  lp::Statement *st;				 /* NEW in example 16 */
  lp::AST *prog;					 /* NEW in example 16 */
  std::list<lp::ValueNode *> *values;  					/* NEW in v. 0.11 */  
  lp::ValueNode *individualValue;							/* New in v. 0.11 */
  lp::BlockCaseValueNode *caseValue; 					/* NEW in v. 0.11*/

#line 139 "interpreter.tab.h"

};
typedef union YYSTYPE YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define YYSTYPE_IS_DECLARED 1
#endif


extern YYSTYPE yylval;


int yyparse (void);


#endif /* !YY_YY_INTERPRETER_TAB_H_INCLUDED  */
