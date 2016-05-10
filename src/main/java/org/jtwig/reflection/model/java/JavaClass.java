package org.jtwig.reflection.model.java;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import org.jtwig.reflection.model.Value;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        Class<?> parent = this.aClass;
        List<Method> methods = new ArrayList<>();
        do {
            methods.addAll(asList(parent.getDeclaredMethods()));
        } while ((parent = parent.getSuperclass()) != Object.class);
        return Collections2.transform(methods, new Function<Method, JavaMethod>() {
            @Override
            public JavaMethod apply(Method input) {
                return new JavaMethod(input);
            }
        });
    }

    public Optional<JavaField> field (String name) {
        Class<?> parent = this.aClass;
        do {
            try {
                return Optional.of(new JavaField(parent.getDeclaredField(name)));
            } catch (NoSuchFieldException ex) {
                //
            }
        } while((parent = parent.getSuperclass()) != Object.class);
        return Optional.absent();
    }

    public Collection<JavaField> fields () {
        Class<?> parent = this.aClass;
        List<Field> fields = new ArrayList<>();
        do {
            fields.addAll(asList(parent.getDeclaredFields()));
        } while ((parent = parent.getSuperclass()) != Object.class);
        return Collections2.transform(Collections2.filter(fields, new Predicate<Field>() {
            @Override
            public boolean apply(Field input) {
                return !Modifier.isStatic(input.getModifiers());
            }
        }), new Function<Field, JavaField>() {
            @Override
            public JavaField apply(Field input) {
                return new JavaField(input);
            }
        });
    }
}
