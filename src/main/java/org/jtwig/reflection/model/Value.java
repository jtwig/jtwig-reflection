package org.jtwig.reflection.model;

import org.jtwig.reflection.model.java.JavaClass;

import static org.jtwig.reflection.model.java.JavaClassManager.classManager;


public class Value {
    private final Object value;

    public Value(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public <T> T as (Class<T> type) {
        return type.cast(value);
    }

    public JavaClass type () {
        if (value == null) return null;
        return classManager().metadata(value.getClass());
    }
}
