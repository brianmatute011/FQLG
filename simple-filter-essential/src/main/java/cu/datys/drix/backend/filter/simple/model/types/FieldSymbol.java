package cu.datys.drix.backend.filter.simple.model.types;

/**
 * Enumerativo para los cuantificadores lógicos sobre los campos.
 */
public enum FieldSymbol {
    ALL("^"),
    ANY("|");

    /**
     * Símbolo del cuantificador. 
     */
    private String symbol;

    private FieldSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }
}
