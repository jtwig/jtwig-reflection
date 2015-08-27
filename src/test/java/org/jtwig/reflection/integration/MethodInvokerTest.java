package org.jtwig.reflection.integration;

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
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.TestCase.assertEquals;

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
    public void oneArgument() throws Exception {
        Collection<BeanMethod> beanMethods = extractor.extract(new MyTest());
        Object result = invoker.invoke(new MethodInvoker.Request<TestArgument>(beanMethods,
                asList(new TestArgument("one")))).get().get();

        assertEquals("one3", result);
    }

    @Test
    public void twoArguments() throws Exception {
        Collection<BeanMethod> beanMethods = extractor.extract(new MyTest());
        Object result = invoker.invoke(new MethodInvoker.Request<TestArgument>(beanMethods,
                asList(new TestArgument("one"), new TestArgument(" hehe")))).get().get();

        assertEquals("one hehe", result);
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
