package org.jtwig.reflection.model.java;

import com.google.common.base.Optional;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.jtwig.reflection.model.java.JavaClassManager.classManager;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class JavaFieldTest {
    @Test
    public void fieldByNamePrivate() throws Exception {
        Optional<JavaField> result = classManager().metadata(TestClass.class).field("field");

        assertThat(result.isPresent(), is(true));
        JavaField javaField = result.get();

        assertEquals(javaField.value(new TestClass()), "test");
        assertThat(javaField.name(), is("field"));
        assertEquals(javaField.type(), String.class);
    }

    @Test
    public void extendedFieldByNamePrivate() throws Exception {
        Optional<JavaField> result = classManager().metadata(ExtendedTestClass.class).field("field");

        assertThat(result.isPresent(), is(true));
        JavaField javaField = result.get();

        assertEquals(javaField.value(new ExtendedTestClass()), "test1");
        assertThat(javaField.name(), is("field"));
        assertEquals(javaField.type(), String.class);
    }

    public static class TestClass {
        public static final String CONSTANT = "constant";
        private String field = "test";
    }

    public static class ExtendedTestClass extends TestClass {
        public static final String CONSTANT1 = "constant";
        private String field = "test1";
    }
}