package org.jtwig.reflection.model.java;

import java.lang.reflect.Field;

public class JavaField {
    private final Field original;

    public JavaField(Field original) {
        this.original = original;
    }

    public Object value (Object instance) throws IllegalAccessException {
        return value(instance, false);
    }

    public String name () {
        return original.getName();
    }

    public Class type () {
        return original.getType();
    }

    public Object value (Object instance, boolean tryPrivate) throws IllegalAccessException {
        if (tryPrivate) {
            original.setAccessible(true);
        }
        return original.get(instance);
    }
}
