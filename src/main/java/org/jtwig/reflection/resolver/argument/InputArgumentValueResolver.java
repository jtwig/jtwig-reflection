package org.jtwig.reflection.resolver.argument;

import com.google.common.base.Optional;
import org.jtwig.reflection.input.InputParameterResolverContext;
import org.jtwig.reflection.model.Value;
import org.jtwig.reflection.model.java.JavaMethodArgument;

public class InputArgumentValueResolver<InputParameterType> {
    private final InputArgumentResolverConfiguration<InputParameterType> configuration;

    public InputArgumentValueResolver(InputArgumentResolverConfiguration<InputParameterType> configuration) {
        this.configuration = configuration;
    }

    public Optional<Value> resolve(JavaMethodArgument methodArgument, InputParameterResolverContext<InputParameterType> context) {
        return configuration.getInputParameterResolver()
                .resolve(methodArgument, context, methodArgument.type())
                .or(Optional.<Value>absent());
    }

}
