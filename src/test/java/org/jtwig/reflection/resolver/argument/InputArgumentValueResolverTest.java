package org.jtwig.reflection.resolver.argument;

import com.google.common.base.Optional;
import org.jtwig.reflection.input.InputParameterResolverContext;
import org.jtwig.reflection.model.Value;
import org.jtwig.reflection.model.java.JavaMethodArgument;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class InputArgumentValueResolverTest {
    private final InputArgumentResolverConfiguration configuration = mock(InputArgumentResolverConfiguration.class, RETURNS_DEEP_STUBS);
    private final JavaMethodArgument methodArgument = mock(JavaMethodArgument.class);
    private final InputParameterResolverContext context = mock(InputParameterResolverContext.class);
    private InputArgumentValueResolver underTest = new InputArgumentValueResolver(configuration);

    @Test
    public void resolveWhenInputParameterAbsent() throws Exception {
        when(configuration.getInputParameterResolver().resolve(methodArgument, context, String.class)).thenReturn(Optional.absent());

        Optional<Value> result = underTest.resolve(methodArgument, context);

        assertThat(result.isPresent(), is(false));
    }


    @Test
    public void resolveWhenConvertFails() throws Exception {
        when(methodArgument.type()).thenReturn(String.class);
        when(configuration.getInputParameterResolver().resolve(methodArgument, context, String.class)).thenReturn(Optional.of("one"));

        Optional<Value> result = underTest.resolve(methodArgument, context);

        assertThat(result.isPresent(), is(false));
    }


    @Test
    public void resolveWhenConvertWorks() throws Exception {
        when(methodArgument.type()).thenReturn(String.class);
        when(configuration.getInputParameterResolver().resolve(methodArgument, context, String.class)).thenReturn(Optional.of("hi"));
        when(configuration.getInputParameterValueResolver().resolve("hi")).thenReturn("one");

        Optional<Value> result = underTest.resolve(methodArgument, context);

        assertThat(result.isPresent(), is(true));
        assertEquals(result.get().getValue(), "test");
    }
}