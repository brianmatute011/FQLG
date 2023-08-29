package cu.datys.drix.backend.filter.simple.model.criteria;

import cu.datys.drix.backend.filter.simple.model.types.Condition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * Esta clase representa un árbol binario donde las hojas son los filtros y los nodos padres las condiciones AND u OR.
 */
@AllArgsConstructor
public class BinarySimpleCriteria extends SimpleCriteria{
    /**
     * El operador lógico (AND u OR) que representa.
     */
    @Getter
    @Setter
    @NonNull
    private Condition condition;  
    
    /**
     * El {@link SimpleCriteria} de la izquierda en la consulta.
     */
    @Getter
    @Setter
    @NonNull
    private SimpleCriteria left;

    /**
     * El {@link SimpleCriteria} de la derecha en la consulta 
     */
    @Getter
    @Setter
    @NonNull
    private SimpleCriteria right;
}