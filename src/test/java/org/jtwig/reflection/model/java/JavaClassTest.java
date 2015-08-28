package org.jtwig.reflection.model.java;

import com.google.common.base.Optional;

import org.jtwig.reflection.model.Value;
import org.junit.Test;

import java.util.Collection;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class JavaClassTest {

    @Test
    public void constant() throws Exception {

        Optional<Value> result = new JavaClass(TestClass.class).constant("CONSTANT");

        assertThat(result.isPresent(), is(true));
        assertEquals(result.get().getValue(), "constant");

    }

    @Test
    public void constantWhenNotFound() throws Exception {

        Optional<Value> result = new JavaClass(TestClass.class).constant("BLAH");

        assertThat(result.isPresent(), is(false));
    }

    @Test
    public void methods() throws Exception {
        Collection<JavaMethod> result = new JavaClass(TestClass.class).methods();
        assertTrue(!result.isEmpty());
    }

    @Test
    public void fields() throws Exception {
        Collection<JavaField> result = new JavaClass(TestClass.class).fields();

        assertThat(result, hasSize(1));
    }

    public static class TestClass {
        public static final String CONSTANT = "constant";
        private String field = "test";

        public String hello() {
            return "world";
        }
    }
}