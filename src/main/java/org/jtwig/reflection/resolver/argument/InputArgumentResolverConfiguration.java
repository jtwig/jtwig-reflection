package org.jtwig.reflection.resolver.argument;

import org.jtwig.reflection.input.InputParameterResolver;
import org.jtwig.reflection.input.InputParameterValueResolver;

public class InputArgumentResolverConfiguration<InputParameterType> {
    private final InputParameterResolver<InputParameterType> inputParameterResolver;
    private final InputParameterValueResolver<InputParameterType> inputParameterValueResolver;


    public InputArgumentResolverConfiguration(InputParameterResolver<InputParameterType> inputParameterResolver, InputParameterValueResolver<InputParameterType> inputParameterValueResolver) {
        this.inputParameterResolver = inputParameterResolver;
        this.inputParameterValueResolver = inputParameterValueResolver;
    }

    public InputParameterValueResolver<InputParameterType> getInputParameterValueResolver() {
        return inputParameterValueResolver;
    }

    public InputParameterResolver<InputParameterType> getInputParameterResolver() {
        return inputParameterResolver;
    }
}
