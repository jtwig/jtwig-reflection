package org.jtwig.reflection.model.java;

import com.google.common.base.Optional;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class JavaFieldTest {
    @Test
    public void fieldByNamePrivate() throws Exception {
        Optional<JavaField> result = new JavaClass(TestClass.class).field("field");

        assertThat(result.isPresent(), is(true));
        JavaField javaField = result.get();

        assertEquals(javaField.value(new TestClass(), true), "test");
        assertThat(javaField.name(), is("field"));
        assertEquals(javaField.type(), String.class);
    }

    @Test(expected = IllegalAccessException.class)
    public void fieldByNamePrivateNotAccessible() throws Exception {
        Optional<JavaField> result = new JavaClass(TestClass.class).field("field");

        assertThat(result.isPresent(), is(true));
        assertEquals(result.get().value(new TestClass()), "test");
    }

    public static class TestClass {
        public static final String CONSTANT = "constant";
        private String field = "test";
    }
}