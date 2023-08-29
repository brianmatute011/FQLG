package cu.datys.drix.backend.filter.simple.model.value;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

/**
 * Esta clase es la base para los valores permitidos por el filtro.
 * 
 * @param <T> Tipo de dato del valor.
 */
public abstract class Value<T> {
    /**
     * Nombre del tipo de valor.
     */
    @Getter
    @Setter
    String type;

    /**
     * Valor real.
     */
    @Getter
    @Setter
    T value;

    /**
     * Si no es null, el valor es un rango y este es su inicio. 
     */
    @Getter
    @Setter
    T from;

    /**
     * Si no es null, el valor es un rango y este es su final. 
     */
    @Getter
    @Setter
    T to;

    /**
     * Si no es null, el valor es una lista de valores.
     */
    @Getter
    @Setter
    List<T> listValue;

    public Value(@NonNull String type, T value, T from, T to, List<T> listValue) {
        this.type = type;
        this.value = value;
        this.from = from;
        this.to = to;
        this.listValue = listValue;
    }
}