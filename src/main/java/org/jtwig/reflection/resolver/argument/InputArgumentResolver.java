package org.jtwig.reflection.resolver.argument;

import com.google.common.base.Optional;
import org.jtwig.reflection.input.InputParameterResolverContext;
import org.jtwig.reflection.model.Value;
import org.jtwig.reflection.model.java.JavaMethodArgument;

public class InputArgumentResolver<InputParameterType> implements ArgumentResolver {
    private final InputArgumentValueResolver<InputParameterType> resolver;
    private final InputParameterResolverContext<InputParameterType> context;

    public InputArgumentResolver(InputArgumentValueResolver<InputParameterType> resolver,
                                 InputParameterResolverContext<InputParameterType> context) {
        this.resolver = resolver;
        this.context = context;
    }

    @Override
    public Optional<Value> resolve(final JavaMethodArgument methodArgument) {
        InputParameterResolverContext<InputParameterType> clonedContext = context.clone();

        Optional<Value> result = resolver.resolve(methodArgument, clonedContext);

        if (result.isPresent()) {
            context.merge(clonedContext);
        }

        return result;
    }

    public boolean isFullyResolved () {
        return context.fullyUsed();
    }

}
