package org.jtwig.reflection.model.java;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class JavaClassFactory {
    public JavaClass create(Class type) {
        Map<String, JavaConstant> constants = new HashMap<String, JavaConstant>();
        Map<String, JavaField> fields = new HashMap<String, JavaField>();
        Map<String, Map<MethodSignature, JavaMethod>> methodsByName = new HashMap<String, Map<MethodSignature, JavaMethod>>();
        Map<String, JavaMethods> javaMethods = new HashMap<String, JavaMethods>();

        extractFields(type, constants, fields);
        extractMethods(type, methodsByName);

        for (Map.Entry<String, Map<MethodSignature, JavaMethod>> entry : methodsByName.entrySet()) {
            javaMethods.put(entry.getKey(), new JavaMethods(entry.getValue()));
        }

        JavaClass result = new JavaClass(type, constants, fields, javaMethods);

        if (type.getSuperclass() != Object.class) {
            JavaClass javaClass = create(type.getSuperclass());
            result.merge(javaClass);
        }
        return result;
    }

    private void extractMethods(Class type, Map<String, Map<MethodSignature, JavaMethod>> methodsByName) {
        for (Method method : type.getDeclaredMethods()) {
            if (!Modifier.isStatic(method.getModifiers())) {
                JavaMethod javaMethod = new JavaMethod(method);

                if (!methodsByName.containsKey(method.getName())) {
                    methodsByName.put(method.getName(), new HashMap<MethodSignature, JavaMethod>());
                }

                methodsByName.get(method.getName()).put(new MethodSignature(method.getParameterTypes()), javaMethod);
            }
        }
    }

    private void extractFields(Class type, Map<String, JavaConstant> constants, Map<String, JavaField> fields) {
        for (Field field : type.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers())) {
                constants.put(field.getName(), new JavaConstant(field));
            } else {
                fields.put(field.getName(), new JavaField(field));
            }
        }
    }
}
