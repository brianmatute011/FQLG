package cu.datys.drix.backend.filter.simple.model.criteria;

import java.util.List;
import cu.datys.drix.backend.filter.simple.model.operator.Operator;
import cu.datys.drix.backend.filter.simple.model.value.Value;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Esta clase representa un filtro unario (sin condiciones AND u OR).
 */

@AllArgsConstructor
@Builder
public class UnaryFilter extends SimpleCriteria {
    /**
     * El operador de filtro a aplicar. Ver {@link Operator}
     * 
     */
    @Getter
    @Setter
    private Operator operator;

    /**
     * El valor a filtrar. Ver {@link Value}
     */
    @Getter
    @Setter
    private Value<?> value;

    /**
     * Los campos que se desean filtrar.
     */
    @Getter
    @Setter
    private List<String> fields;

    /**
     * Si el filtro se debe cumplir para todos los campos, o en cualquiera de ellos. 
     */
    @Getter
    @Setter
    private boolean all;
}