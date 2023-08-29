package cu.datys.drix.backend.filter.simple.model.types;

/**
 * Enumerativo con las operaciones binarias And y Or
 *
 */
public enum Condition {
    AND("AND"), 
    OR("OR");

    /**
     * Nombre de la condición.
     */
    private String condition;

    private Condition(String condition) {
        this.condition = condition;
    }

    public String getCondition() {
        return this.condition;
    }
}
