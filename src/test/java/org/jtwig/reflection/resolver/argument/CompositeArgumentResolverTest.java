package org.jtwig.reflection.resolver.argument;

import com.google.common.base.Optional;
import org.jtwig.reflection.model.Value;
import org.jtwig.reflection.model.java.JavaMethodArgument;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class CompositeArgumentResolverTest {
    private final ArrayList<ArgumentResolver> argumentResolvers = new ArrayList<ArgumentResolver>();
    private final JavaMethodArgument methodArgument = mock(JavaMethodArgument.class);
    private CompositeArgumentResolver underTest = new CompositeArgumentResolver(argumentResolvers);

    @Before
    public void setUp() throws Exception {
        argumentResolvers.clear();
    }

    @Test
    public void resolveWhenNoResolvers() throws Exception {
        Optional<Value> result = underTest.resolve(methodArgument);

        assertThat(result.isPresent(), is(false));
    }

    @Test
    public void resolveWhenOneNotResolves() throws Exception {
        ArgumentResolver argumentResolver = mock(ArgumentResolver.class);
        when(argumentResolver.resolve(methodArgument)).thenReturn(Optional.<Value>absent());
        argumentResolvers.add(argumentResolver);

        Optional<Value> result = underTest.resolve(methodArgument);

        assertThat(result.isPresent(), is(false));
    }

    @Test
    public void resolveWhenOneResolves() throws Exception {
        ArgumentResolver argumentResolver = mock(ArgumentResolver.class);
        when(argumentResolver.resolve(methodArgument)).thenReturn(Optional.of(new Value(1)));
        argumentResolvers.add(argumentResolver);

        Optional<Value> result = underTest.resolve(methodArgument);

        assertThat(result.isPresent(), is(true));
        assertEquals(1, result.get().getValue());
    }



    @Test
    public void resolveWhenMultipleFirstResolves() throws Exception {
        ArgumentResolver firstArgumentResolver = mock(ArgumentResolver.class);
        ArgumentResolver secondArgumentResolver = mock(ArgumentResolver.class);
        when(firstArgumentResolver.resolve(methodArgument)).thenReturn(Optional.of(new Value(2)));
        when(secondArgumentResolver.resolve(methodArgument)).thenReturn(Optional.<Value>absent());
        argumentResolvers.add(firstArgumentResolver);
        argumentResolvers.add(secondArgumentResolver);

        Optional<Value> result = underTest.resolve(methodArgument);

        assertThat(result.isPresent(), is(true));
        assertEquals(2, result.get().getValue());
        verify(secondArgumentResolver, never()).resolve(methodArgument);
    }


    @Test
    public void resolveWhenMultipleSecondResolves() throws Exception {
        ArgumentResolver firstArgumentResolver = mock(ArgumentResolver.class);
        ArgumentResolver secondArgumentResolver = mock(ArgumentResolver.class);
        when(firstArgumentResolver.resolve(methodArgument)).thenReturn(Optional.<Value>absent());
        when(secondArgumentResolver.resolve(methodArgument)).thenReturn(Optional.of(new Value(3)));
        argumentResolvers.add(firstArgumentResolver);
        argumentResolvers.add(secondArgumentResolver);

        Optional<Value> result = underTest.resolve(methodArgument);

        assertThat(result.isPresent(), is(true));
        assertEquals(3, result.get().getValue());
        verify(firstArgumentResolver).resolve(methodArgument);
    }
}