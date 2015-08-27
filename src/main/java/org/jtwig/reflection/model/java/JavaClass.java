package org.jtwig.reflection.model.java;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.Collections2;
import org.jtwig.reflection.model.Value;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;

import static java.util.Arrays.asList;

public class JavaClass {
    private final Class aClass;

    public JavaClass(Class aClass) {
        this.aClass = aClass;
    }

    public Optional<Value> constant (String name) {
        try {
            Field declaredField = aClass.getDeclaredField(name);
            if (declaredField == null) {
                return Optional.absent();
            } else {
                try {
                    return Optional.of(new Value(declaredField.get(null)));
                } catch (IllegalAccessException e) {
                    return Optional.absent();
                }
            }
        } catch (NoSuchFieldException e) {
            return Optional.absent();
        }
    }

    public Collection<JavaMethod> methods () {
        Method[] declaredMethods = aClass.getDeclaredMethods();
        return Collections2.transform(asList(declaredMethods), new Function<Method, JavaMethod>() {
            @Override
            public JavaMethod apply(Method input) {
                return new JavaMethod(input);
            }
        });
    }

    public Optional<JavaField> field (String name) {
        try {
            return Optional.of(new JavaField(aClass.getDeclaredField(name)));
        } catch (NoSuchFieldException e) {
            return Optional.absent();
        }
    }

    public Collection<JavaField> fields () {
        return Collections2.transform(asList(aClass.getDeclaredFields()), new Function<Field, JavaField>() {
            @Override
            public JavaField apply(Field input) {
                return new JavaField(input);
            }
        });
    }
}
