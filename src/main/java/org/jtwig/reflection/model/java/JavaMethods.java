package org.jtwig.reflection.model.java;

import com.google.common.base.Optional;

import java.util.Collection;
import java.util.Map;

public class JavaMethods {
    private final Map<MethodSignature, JavaMethod> methods;

    public JavaMethods(Map<MethodSignature, JavaMethod> methods) {
        this.methods = methods;
    }

    public Collection<JavaMethod> getMethods() {
        return methods.values();
    }

    public Optional<JavaMethod> getMethod(Class... types) {
        return Optional.fromNullable(methods.get(MethodSignature.create(types)));
    }

    public void merge(JavaMethods origin) {
        for (Map.Entry<MethodSignature, JavaMethod> entry : origin.methods.entrySet()) {
            if (!methods.containsKey(entry.getKey())) {
                methods.put(entry.getKey(), entry.getValue());
            }
        }
    }
}
