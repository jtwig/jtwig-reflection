package org.jtwig.reflection.model.java;

import com.google.common.base.Optional;
import org.junit.Test;

import java.util.Collection;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.jtwig.reflection.model.java.JavaClassManager.classManager;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class JavaClassTest {

    @Test
    public void constant() throws Exception {
        Optional<JavaConstant> result = classManager().metadata(TestClass.class).constant("CONSTANT");

        assertThat(result.isPresent(), is(true));
        assertThat(result.get().value().isPresent(), is(true));
        assertEquals(result.get().value().get().getValue(), "constant");
    }

    @Test
    public void constantWhenNotFound() throws Exception {

        Optional<JavaConstant> result = classManager().metadata(TestClass.class).constant("BLAH");

        assertThat(result.isPresent(), is(false));
    }

    @Test
    public void fields() throws Exception {
        Collection<JavaField> result = classManager().metadata(TestClass.class).fields();

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