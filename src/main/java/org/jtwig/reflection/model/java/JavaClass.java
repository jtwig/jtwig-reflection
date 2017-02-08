package org.jtwig.reflection.model.java;

import com.google.common.base.Optional;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class JavaClass {
    private final Class nativeType;
    private final Map<String, JavaConstant> constants;
    private final Map<String, JavaField> fields;
    private final Map<String, JavaMethods> methodsByName;

    public JavaClass(Class nativeType, Map<String, JavaConstant> constants, Map<String, JavaField> fields, Map<String, JavaMethods> methodsByName) {
        this.nativeType = nativeType;
        this.constants = constants;
        this.fields = fields;
        this.methodsByName = methodsByName;
    }

    public Optional<JavaConstant> constant (String name) {
        return Optional.fromNullable(constants.get(name));
    }

    public Collection<JavaConstant> constants () {
        return constants.values();
    }

    public Optional<JavaField> field (String name) {
        return Optional.fromNullable(fields.get(name));
    }

    public Collection<JavaField> fields () {
        return fields.values();
    }

    public JavaMethods method (String name) {
        return Optional.fromNullable(methodsByName.get(name))
                .or(new JavaMethods(Collections.<MethodSignature, JavaMethod>emptyMap()));
    }


    public void merge (JavaClass origin) {
        for (Map.Entry<String, JavaMethods> entry : origin.methodsByName.entrySet()) {
            if (methodsByName.containsKey(entry.getKey())) {
                methodsByName.get(entry.getKey()).merge(origin.methodsByName.get(entry.getKey()));
            } else {
                methodsByName.put(entry.getKey(), entry.getValue());
            }
        }

        for (Map.Entry<String, JavaField> entry : origin.fields.entrySet()) {
            if (!fields.containsKey(entry.getKey())) {
                fields.put(entry.getKey(), entry.getValue());
            }
        }

        for (Map.Entry<String, JavaConstant> entry : constants.entrySet()) {
            if (!constants.containsKey(entry.getKey())) {
                constants.put(entry.getKey(), entry.getValue());
            }
        }
    }

    public Class getNative() {
        return nativeType;
    }
}
