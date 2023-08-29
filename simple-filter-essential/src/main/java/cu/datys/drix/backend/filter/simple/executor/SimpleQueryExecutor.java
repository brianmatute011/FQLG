package cu.datys.drix.backend.filter.simple.executor;

import lombok.NonNull;

/**
 * Esta interfaz define el punto de entrada para la creación del filtro genérico.
 * 
 * @param <T> El tipo de dato de la consulta creada.
 */
public interface SimpleQueryExecutor<T> {
    /**
     * Crea la consulta específica a partir de la genérica. 
     * @param criteria Consulta genérica.
     * @return <code>T</code> Tipo de la consulta creada. 
     */
    T query(@NonNull String criteria);
}
