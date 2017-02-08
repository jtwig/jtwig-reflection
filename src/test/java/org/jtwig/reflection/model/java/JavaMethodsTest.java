package org.jtwig.reflection.model.java;

import org.junit.Test;

import static org.jtwig.reflection.model.java.JavaClassManager.classManager;
import static org.junit.Assert.assertEquals;

public class JavaMethodsTest {
    @Test
    public void getMethod() throws Exception {
        JavaMethods javaMethods = classManager().metadata(BeanTest.class).method("asd");

        assertEquals(javaMethods.getMethod().get().getNative(), BeanTest.class.getDeclaredMethod("asd"));
        assertEquals(javaMethods.getMethod(String.class).get().getNative(), BeanTest.class.getDeclaredMethod("asd", String.class));
        assertEquals(javaMethods.getMethod(String.class, Object.class).get().getNative(), BeanTest.class.getDeclaredMethod("asd", String.class, Object.class));
    }

    @Test
    public void getMethodExtended() throws Exception {
        JavaMethods javaMethods = classManager().metadata(ExtendedBeanTest.class).method("asd");

        assertEquals(javaMethods.getMethod().get().getNative(), BeanTest.class.getDeclaredMethod("asd"));
        assertEquals(javaMethods.getMethod(String.class).get().getNative(), BeanTest.class.getDeclaredMethod("asd", String.class));
        assertEquals(javaMethods.getMethod(String.class, Object.class).get().getNative(), BeanTest.class.getDeclaredMethod("asd", String.class, Object.class));
        assertEquals(javaMethods.getMethod(Object.class).get().getNative(), ExtendedBeanTest.class.getDeclaredMethod("asd", Object.class));
    }

    public static class BeanTest {
        private void asd () {}
        private void asd (String asd) {}
        private void asd (String asd, Object object) {}
    }

    public static class ExtendedBeanTest extends BeanTest {
        private void asd (Object ob) {}
    }
}