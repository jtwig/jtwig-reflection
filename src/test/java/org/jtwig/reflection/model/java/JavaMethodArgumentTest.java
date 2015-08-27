package org.jtwig.reflection.model.java;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JavaMethodArgumentTest {
    private final JavaMethod method = mock(JavaMethod.class);
    private final int position = 2;
    private JavaMethodArgument underTest = new JavaMethodArgument(method, position);

    @Test
    public void type() throws Exception {
        Class type = String.class;
        when(method.type(position)).thenReturn(type);

        Class result = underTest.type();

        assertEquals(type, result);
    }

    @Test
    public void isVarArgMethod() throws Exception {
        when(method.numberOfArguments()).thenReturn(position + 1);
        when(method.isVarArgs()).thenReturn(true);

        boolean result = underTest.isVarArg();

        assertThat(result, is(true));
    }

    @Test
    public void isVarArgMethodWhenNotLastArgument() throws Exception {
        when(method.numberOfArguments()).thenReturn(position);
        when(method.isVarArgs()).thenReturn(true);

        boolean result = underTest.isVarArg();

        assertThat(result, is(false));
    }

    @Test
    public void isVarArgMethodWhenNotVarArgs() throws Exception {
        when(method.numberOfArguments()).thenReturn(position + 1);
        when(method.isVarArgs()).thenReturn(false);

        boolean result = underTest.isVarArg();

        assertThat(result, is(false));
    }
}