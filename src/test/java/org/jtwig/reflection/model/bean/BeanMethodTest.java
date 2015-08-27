package org.jtwig.reflection.model.bean;

import org.jtwig.reflection.exceptions.InvokeException;
import org.jtwig.reflection.model.java.JavaMethod;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BeanMethodTest {
    private final JavaMethod javaMethod = mock(JavaMethod.class);
    private final Object bean = new Object();
    private BeanMethod underTest = new BeanMethod(bean, javaMethod);

    @Test
    public void invoke() throws Exception {
        Object[] arguments = new Object[0];
        Object value = new Object();
        when(javaMethod.invoke(bean, arguments)).thenReturn(value);

        Object result = underTest.invoke(arguments);

        assertEquals(value, result);
    }

    @Test(expected = InvokeException.class)
    public void invokeWhenInvocationTargetException() throws Exception {
        Object[] arguments = new Object[0];
        when(javaMethod.invoke(bean, arguments)).thenThrow(InvocationTargetException.class);

        underTest.invoke(arguments);
    }

    @Test(expected = InvokeException.class)
    public void invokeWhenIllegalAccessException() throws Exception {
        Object[] arguments = new Object[0];
        when(javaMethod.invoke(bean, arguments)).thenThrow(IllegalAccessException.class);

        underTest.invoke(arguments);
    }
}