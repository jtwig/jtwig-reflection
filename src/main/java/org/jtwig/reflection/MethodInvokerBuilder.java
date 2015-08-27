package org.jtwig.reflection;

import org.jtwig.reflection.input.InputParameterResolverContextFactory;
import org.jtwig.reflection.input.InputParameterResolverFactory;
import org.jtwig.reflection.input.InputParameterValueResolver;
import org.jtwig.reflection.resolver.BeanMethodResolverImpl;
import org.jtwig.reflection.resolver.argument.ArgumentResolver;
import org.jtwig.reflection.resolver.argument.CompositeArgumentResolver;
import org.jtwig.reflection.resolver.argument.InputArgumentResolverFactory;
import org.jtwig.reflection.resolver.argument.ParameterResolver;

import java.util.ArrayList;
import java.util.Collection;

public class MethodInvokerBuilder<T> {
    private final Collection<ArgumentResolver> argumentResolvers = new ArrayList<ArgumentResolver>();
    private InputParameterValueResolver<T> inputParameterValueResolver;
    private InputParameterResolverFactory<T> inputParameterResolverFactory;

    public MethodInvokerBuilder<T> withInputParameterValueResolver(InputParameterValueResolver<T> inputParameterValueResolver) {
        this.inputParameterValueResolver = inputParameterValueResolver;
        return this;
    }


    public MethodInvokerBuilder<T> withInputParameterResolverFactory(InputParameterResolverFactory<T> inputParameterResolver) {
        this.inputParameterResolverFactory = inputParameterResolver;
        return this;
    }

    public MethodInvokerBuilder<T> withArgumentResolver (ArgumentResolver resolver) {
        this.argumentResolvers.add(resolver);
        return this;
    }

    public MethodInvokerBuilder<T> withArgumentResolvers (Collection<ArgumentResolver> resolvers) {
        this.argumentResolvers.addAll(resolvers);
        return this;
    }

    public MethodInvoker<T> build () {
        return new MethodInvokerImpl<T>(new BeanMethodResolverImpl<T>(
                new InputArgumentResolverFactory<T>(inputParameterValueResolver, inputParameterResolverFactory, new InputParameterResolverContextFactory<T>()),
                new ParameterResolver<T>(new CompositeArgumentResolver(argumentResolvers))
        ));
    }
}
