package org.jtwig.reflection.integration.example;

import com.google.common.base.Optional;
import org.jtwig.reflection.input.InputParameterResolver;
import org.jtwig.reflection.input.InputParameterResolverContext;
import org.jtwig.reflection.model.Value;
import org.jtwig.reflection.model.java.JavaMethodArgument;

public class TestArgumentResolver implements InputParameterResolver<TestArgument> {
    @Override
    public Optional<Value> resolve(JavaMethodArgument argument, InputParameterResolverContext<TestArgument> context, Class type) {
        int position = argument.position();
        if (position >= context.size()) return Optional.absent();
        context.markAsUsed(position);
        return Optional.fromNullable(new Value(context.value(position).getValue()));
    }
}
