package org.jtwig.reflection.model;

import org.jtwig.reflection.model.java.JavaClass;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

public class ValueTest {

    @Test
    public void as() throws Exception {
        String value = "value";

        String result = new Value(value).as(String.class);

        assertThat(result, is(value));
    }

    @Test
    public void type() throws Exception {

        JavaClass result = new Value("").type();


        assertThat(result, notNullValue());
    }

    @Test
    public void typeWhenNull() throws Exception {

        JavaClass result = new Value(null).type();

        assertThat(result, nullValue());
    }
}