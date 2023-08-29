package cu.datys.drix.backend.filter.simple.model.operator;

/**
 * Enumerativo de operadores
 * 
 */
public enum Operator {
    // Operadores para términos.
    IS("IS"), IS_NOT("IS_NOT"), // (no) es igual a un valor.

    // Operadores para cadenas de caracteres y términos.
    STARTS_WITH("STARTS_WITH"), DOES_NOT_STARTS_WITH("OES_NOT_STARTS_WITH"), ENDS_WITH("ENDS_WITH"), DOES_NOT_END_WITH("OES_NOT_END_WITH"), // (no) comienza/termina con un valor.
    CONTAINS("CONTAINS"), DOES_NOT_CONTAINS("DOES_NOT_CONTAINS"), // (no) contiene un valor.
    
    
    EQUAL("=="), NOT_EQUAL("!="), // (no) es igual a un valor.
    
    // Operadores para numeros.
    GREATER_THAN(">"), GREATER_THAN_EQUALS(">="), LESS_THAN("<"), LESS_THAN_EQUALS("<="), // (no) es mayor/menor que un valor
    
    // Operadores para rangos.
    RANGE("RANGE"), RANGOUT("RANGOUT"), // (no) esta en el rango.
    
    // Operadores para listas.
    ALL("ALL"), NONE("NONE"), ANY("ANY"); // Contiene todos, algunos o ninguno de los valores

    /**
     * Nombre del operador
     */
    private final String operator;

    Operator(String operator) {
        this.operator = operator;
    }

    public final String getOperator() {
        return this.operator;
    }
}