package org.jtwig.reflection.resolver.argument;

import org.jtwig.reflection.input.InputParameterResolver;
import org.jtwig.reflection.input.InputParameterResolverContextFactory;
import org.jtwig.reflection.input.InputParameterResolverFactory;
import org.jtwig.reflection.input.InputParameterValueResolver;
import org.jtwig.reflection.model.bean.BeanMethod;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class InputArgumentResolverFactoryTest {
    private final InputParameterResolver inputParameterResolver = mock(InputParameterResolver.class);
    private final InputParameterValueResolver inputParameterValueResolver = mock(InputParameterValueResolver.class);
    private final InputParameterResolverFactory inputParameterResolverFactory = mock(InputParameterResolverFactory.class);
    private final InputParameterResolverContextFactory parameterResolverContextFactory = mock(InputParameterResolverContextFactory.class);
    private InputArgumentResolverFactory underTest = new InputArgumentResolverFactory(inputParameterValueResolver, inputParameterResolverFactory, parameterResolverContextFactory);

    @Test
    public void create() throws Exception {
        BeanMethod beanMethod = mock(BeanMethod.class);
        ArrayList inputParameterList = new ArrayList();

        InputArgumentResolver<Integer> result = underTest.create(beanMethod, inputParameterList);

        assertNotNull(result);
    }
}