package org.jtwig.reflection.resolver.argument;

import com.google.common.base.Optional;
import org.jtwig.reflection.model.Value;
import org.jtwig.reflection.model.java.JavaMethodArgument;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParameterResolverTest {
    private final ArgumentResolver argumentResolver = mock(ArgumentResolver.class);
    private final InputArgumentResolver<Integer> inputArgumentResolver = mock(InputArgumentResolver.class);
    private ParameterResolver<Integer> underTest = new ParameterResolver<Integer>(argumentResolver);

    @Test
    public void resolveWhenBothAbsent() throws Exception {
        JavaMethodArgument methodArgument = mock(JavaMethodArgument.class);
        when(inputArgumentResolver.resolve(methodArgument)).thenReturn(Optional.<Value>absent());
        when(argumentResolver.resolve(methodArgument)).thenReturn(Optional.<Value>absent());

        Optional<Value> result = underTest.resolve(inputArgumentResolver, methodArgument);

        assertThat(result.isPresent(), is(false));
    }

    @Test
    public void resolveWhenOnlyInputArgumentResolves() throws Exception {
        JavaMethodArgument methodArgument = mock(JavaMethodArgument.class);
        when(inputArgumentResolver.resolve(methodArgument)).thenReturn(Optional.of(new Value(1)));
        when(argumentResolver.resolve(methodArgument)).thenReturn(Optional.<Value>absent());

        Optional<Value> result = underTest.resolve(inputArgumentResolver, methodArgument);

        assertThat(result.isPresent(), is(true));
        assertEquals(1, result.get().getValue());
    }

    @Test
    public void resolveWhenOnlyArgumentResolves() throws Exception {
        JavaMethodArgument methodArgument = mock(JavaMethodArgument.class);
        when(inputArgumentResolver.resolve(methodArgument)).thenReturn(Optional.<Value>absent());
        when(argumentResolver.resolve(methodArgument)).thenReturn(Optional.of(new Value(2)));

        Optional<Value> result = underTest.resolve(inputArgumentResolver, methodArgument);

        assertThat(result.isPresent(), is(true));
        assertEquals(2, result.get().getValue());
    }

    @Test
    public void resolveWhenBothResolves() throws Exception {
        JavaMethodArgument methodArgument = mock(JavaMethodArgument.class);
        when(inputArgumentResolver.resolve(methodArgument)).thenReturn(Optional.of(new Value(1)));
        when(argumentResolver.resolve(methodArgument)).thenReturn(Optional.of(new Value(2)));

        Optional<Value> result = underTest.resolve(inputArgumentResolver, methodArgument);

        assertThat(result.isPresent(), is(true));
        assertEquals(1, result.get().getValue());
    }


}