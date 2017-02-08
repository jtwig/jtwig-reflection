package org.jtwig.reflection.model.java;

import java.lang.reflect.Field;

public class JavaField {
    private final Field field;

    public JavaField(Field original) {
        this.field = original;
        this.field.setAccessible(true);
    }

    public Object value (Object instance) throws IllegalAccessException {
        return field.get(instance);
    }

    public String name () {
        return field.getName();
    }

    public Class type () {
        return field.getType();
    }

    public Field getNative () {
        return field;
    }
}
