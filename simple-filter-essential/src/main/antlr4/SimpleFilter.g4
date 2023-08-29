grammar SimpleFilter;

@header {
package cu.datys.drix.backend.filter.simple.parser;
}
/*
* Reglas del parser
*/
simple_filter: parenthesis_expression ';';

parenthesis_expression:
        simple_parenthesis
        | parenthesis_expression LOGICAL_OPERATOR parenthesis_expression
        | simple_expr;

simple_parenthesis: '(' parenthesis_expression ')'; // Una expresion con parentesis anidados Ej; ([aaa] EQUAL 1 AND ([aaa] EQUAL 1 AND))

simple_expr: filter_expr | filter_expr LOGICAL_OPERATOR parenthesis_expression; // Una expresion simple consta de solo una expresion
                                                                               // o varias unidas por operaciones logicas.

filter_expr : FIELD_SYMBOL? fields OPERATOR value; // Una expresion de filtrado puede comensar o no con el cuantificador,
                                                   // la lista de campos, el operador de filtro y el valor.

/* La lista de campos a consultar puede ser todos los campos (*), o tener los valores separados por coma.*/
fields : '*' | '[' field_list ']';

value: range_val | list_val | simple_val;

field_list : IDENT (',' IDENT) *; // Los campos pueden ser uno solo o la lista de ellos separados por coma.

range_val  : 'FROM' (DATE_VALUE | NUMBER_VALUE) 'TO' (DATE_VALUE | NUMBER_VALUE); // Un valor de rango es de la forma
                                                                                  // FROM <valor inicial> TO <valor final>

// Una lista de valores esta encerrada entre corchetes.
list_val : '[' (term_value | NUMBER_VALUE | STRING_VALUE) (',' (term_value | NUMBER_VALUE | STRING_VALUE)) * ']';
simple_val: term_value | DATE_VALUE | NUMBER_VALUE | BOOLEAN_VALUE | STRING_VALUE;

/*Un tipo término comienza con el caracter T y el valor encerrado entre comillas*/
term_value: 'T' STRING_VALUE;

/*
* Reglas del Lexer
*/

/* Símbolos "para todos los campos" o "algun campo"*/
FIELD_SYMBOL: ('^' | '|');

/* Operadores lógicos AND y OR*/
LOGICAL_OPERATOR: 'AND' | 'OR';

OPERATOR: 'IS' | 'IS_NOT' | 'STARTS_WITH' | 'NO_STARTS_WITH' | 'ENDS_WITH' | 'NO_END_WITH' | 'CONTAINS'
            | 'NO_CONTAINS' | '==' | '!=' | '>' | '>=' | '<'
            | '<=' | 'RANGE' | 'RANGOUT' | 'ALL' | 'ANY' | 'NONE';

fragment INT: [0-9] [0-9]* ;

/* Valores booleanos*/
BOOLEAN_VALUE: 'true'| 'false';

/*Una fecha tiene el formato dia/mes/año*/
DATE_VALUE: INT '/' INT '/' INT | INT '/' INT '/' INT '-' INT ':' INT ':' INT ;

/*Un número puede ser decimal o entero, positivo o negativo*/
NUMBER_VALUE: '-'? INT '.' INT | '-'? INT;
STRING_VALUE: '\'' .+? '\''; // Una valor cadena de caracteres esta encerrada entre comillas simples

/* Un identificador es una cadena de caracteres formada por letras y números y guión bajo*/
IDENT: [_a-zA-Z] [a-zA-Z0-9_.] *;

/*Se omiten los espacios en blanco y las tabulaciones*/
WHITESPACE : (' ' | '\t') -> skip ;