package org.jtwig.reflection.model.java;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Arrays;

public class MethodSignature {
    private final Class[] parameters;

    public MethodSignature(Class... parameters) {
        this.parameters = parameters;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        MethodSignature other = (MethodSignature) obj;
        return Arrays.equals(parameters, other.parameters);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(parameters)
                .toHashCode();
    }

    public static MethodSignature create(Class[] types) {
        return new MethodSignature(types);
    }
}
