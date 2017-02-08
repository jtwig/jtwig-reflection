package org.jtwig.reflection.model.java;

import com.google.common.base.Optional;
import org.jtwig.reflection.model.Value;

import java.lang.reflect.Field;

public class JavaConstant {
    private final Field field;

    public JavaConstant(Field field) {
        this.field = field;
        this.field.setAccessible(true);
    }

    public Optional<Value> value () {
        try {
            return Optional.of(new Value(field.get(null)));
        } catch (IllegalAccessException e) {
            return Optional.absent();
        }
    }

    public String name () {
        return field.getName();
    }
}
