package org.jtwig.reflection.model.bean;

import org.jtwig.reflection.exceptions.InvokeException;
import org.jtwig.reflection.model.java.JavaMethod;

import java.lang.reflect.InvocationTargetException;

public class BeanMethod {
    private final Object bean;
    private final JavaMethod method;

    public BeanMethod(Object bean, JavaMethod method) {
        this.bean = bean;
        this.method = method;
    }

    public JavaMethod method() {
        return method;
    }

    public Object invoke(Object[] arguments) {
        try {
            return method.invoke(bean, arguments);
        } catch (InvocationTargetException e) {
            throw new InvokeException(e);
        } catch (IllegalAccessException e) {
            throw new InvokeException(e);
        }
    }
}
