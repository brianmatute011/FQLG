package cu.datys.drix.backend.filter.simple.model.value;

import lombok.NonNull;

public class BooleanValue extends Value<Boolean>{
    public BooleanValue(@NonNull Boolean value) {
        super("boolean", value, null, null, null);
    }
}
