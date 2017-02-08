package org.jtwig.reflection.model.java;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class JavaClassManager {
    private static JavaClassManager INSTANCE = new JavaClassManager(new JavaClassFactory(), new ConcurrentHashMap<Class, JavaClass>());

    public static JavaClassManager classManager () {
        return INSTANCE;
    }

    private final JavaClassFactory javaClassFactory;
    private final ConcurrentMap<Class, JavaClass> javaClassMap;

    public JavaClassManager(JavaClassFactory javaClassFactory, ConcurrentMap<Class, JavaClass> javaClassMap) {
        this.javaClassFactory = javaClassFactory;
        this.javaClassMap = javaClassMap;
    }

    public JavaClass metadata (Class type) {
        if (!javaClassMap.containsKey(type)) {
            javaClassMap.put(type, javaClassFactory.create(type));
        }
        return javaClassMap.get(type);
    }
}
