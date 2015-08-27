package org.jtwig.reflection.model.java;

import com.google.common.base.Optional;

import java.lang.annotation.Annotation;

public class JavaMethodArgument {
    private final JavaMethod method;
    private final int position;

    public JavaMethodArgument(JavaMethod method, int position) {
        this.method = method;
        this.position = position;
    }

    public Class type() {
        return method.type(position);
    }

    public int position() {
        return position;
    }

    public boolean isVarArg () {
        return method.isVarArgs() && method.numberOfArguments() == position + 1;
    }

    public <T extends Annotation> Optional<T> annotation (Class<T> type) {
        return method.argumentAnnotation(position, type);
    }

    public JavaMethod method () {
        return method;
    }
}
