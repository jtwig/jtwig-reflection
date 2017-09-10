package org.jtwig.reflection.integration;

import com.google.common.base.Optional;
import org.jtwig.reflection.Executable;
import org.jtwig.reflection.MethodInvoker;
import org.jtwig.reflection.MethodInvokerBuilder;
import org.jtwig.reflection.extractor.BeanMethodExtractor;
import org.jtwig.reflection.input.InputParameterResolver;
import org.jtwig.reflection.input.InputParameterResolverFactory;
import org.jtwig.reflection.integration.example.TestArgument;
import org.jtwig.reflection.integration.example.TestArgumentResolver;
import org.jtwig.reflection.integration.example.TestArgumentValueResolver;
import org.jtwig.reflection.model.bean.BeanMethod;
import org.jtwig.reflection.model.java.JavaMethod;
import org.jtwig.reflection.resolver.argument.ArgumentResolver;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class MethodInvokerTest {
    private final BeanMethodExtractor extractor = new BeanMethodExtractor();
    private final MethodInvoker<TestArgument> invoker = new MethodInvokerBuilder<TestArgument>()
            .withInputParameterResolverFactory(new InputParameterResolverFactory<TestArgument>() {
                @Override
                public InputParameterResolver<TestArgument> create(JavaMethod method, List<TestArgument> testArguments) {
                    return new TestArgumentResolver();
                }
            })
            .withInputParameterValueResolver(new TestArgumentValueResolver())
            .build();


    @Test
    public void twoArguments() throws Exception {
        Collection<BeanMethod> beanMethods = extractor.extract(new MyTest());
        Object result = invoker.invoke(new MethodInvoker.Request<TestArgument>(beanMethods,
                asList(new TestArgument("one"), new TestArgument(" hehe")))).get().get();

        assertEquals("one hehe", result);
    }

    @Test
    public void noFoundNoBeans() throws Exception {
        Collection<BeanMethod> beanMethods = extractor.extract(new MyTest());
        Optional<Executable> result = invoker.invoke(new MethodInvoker.Request<TestArgument>(Collections.<BeanMethod>emptyList(),
                asList(new TestArgument("one"))));

        assertFalse(result.isPresent());
    }

    @Test
    public void noFoundWithBeans() throws Exception {
        Collection<BeanMethod> beanMethods = extractor.extract(new MyTest());
        Optional<Executable> result = invoker.invoke(new MethodInvoker.Request<>(beanMethods,
                asList(new TestArgument("1"), new TestArgument("3"), new TestArgument("2"))));

        assertFalse(result.isPresent());
    }

    @Test
    public void builder() throws Exception {
        MethodInvoker<TestArgument> result = new MethodInvokerBuilder<TestArgument>()
                .withArgumentResolver(mock(ArgumentResolver.class))
                .withArgumentResolvers(Collections.singleton(mock(ArgumentResolver.class)))
                .build();

        assertThat(result, notNullValue());
    }

    public static class MyTest {
        public String one (Integer value) {
            return value + "3";
        }

        public String one (String value) {
            return value + "3";
        }

        public String one (String value, String va) {
            return value + va;
        }
    }
}
