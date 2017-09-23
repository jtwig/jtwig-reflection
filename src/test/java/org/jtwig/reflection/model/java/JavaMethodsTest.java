package org.jtwig.reflection.model.java;

import org.junit.Test;

import static org.jtwig.reflection.model.java.JavaClassManager.*;
import static org.junit.Assert.*;

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

    @Test
    public void getMethodImplemented() throws Exception {
        JavaMethods javaMethods = classManager().metadata(ImplementedBeanTest.class).method("asd");

        assertEquals(javaMethods.getMethod().get().getNative(), TestInterface.class.getDeclaredMethod("asd"));
        assertEquals(javaMethods.getMethod(String.class).get().getNative(), TestInterface.class.getDeclaredMethod("asd", String.class));
        assertEquals(javaMethods.getMethod(String.class, Object.class).get().getNative(), TestInterface.class.getDeclaredMethod("asd", String.class, Object.class));
        assertEquals(javaMethods.getMethod(Object.class).get().getNative(), ImplementedBeanTest.class.getDeclaredMethod("asd", Object.class));
    }

    @Test
    public void objectGetClass() throws Exception {
        // getClass
        JavaMethods javaMethods = classManager().metadata(BeanTest.class).method("getClass");
        assertEquals(javaMethods.getMethod().get().getNative(), Object.class.getDeclaredMethod("getClass"));
    }

    @Test
    public void objectEquals() throws Exception {
        // equals
        JavaMethods javaMethods = classManager().metadata(BeanTest.class).method("equals");
        assertEquals(javaMethods.getMethod(Object.class).get().getNative(), Object.class.getDeclaredMethod("equals", Object.class));
    }

    @Test
    public void objectHashCode() throws Exception {
        // hashcode
        JavaMethods javaMethods = classManager().metadata(BeanTest.class).method("hashCode");
        assertEquals(javaMethods.getMethod().get().getNative(), Object.class.getDeclaredMethod("hashCode"));
    }

    @Test
    public void objectToString() throws Exception {
        // toString
        JavaMethods javaMethods = classManager().metadata(BeanTest.class).method("toString");
        assertEquals(javaMethods.getMethod().get().getNative(), Object.class.getDeclaredMethod("toString"));
    }

    public static class BeanTest {
        private void asd () {}
        private void asd (String asd) {}
        private void asd (String asd, Object object) {}
    }

    public static class ExtendedBeanTest extends BeanTest {
        private void asd (Object ob) {}
    }

    public interface TestInterface {
        default void asd () {}
        default void asd (String asd) {}
        default void asd (String asd, Object object) {}
    }

    public static class ImplementedBeanTest implements TestInterface {
        private void asd (Object ob) {}
    }
}