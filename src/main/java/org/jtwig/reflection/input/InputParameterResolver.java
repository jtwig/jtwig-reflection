package org.jtwig.reflection.input;

import com.google.common.base.Optional;
import org.jtwig.reflection.model.Value;
import org.jtwig.reflection.model.java.JavaMethodArgument;

public interface InputParameterResolver<InputParameterType> {
    Optional<Value> resolve (JavaMethodArgument argument, InputParameterResolverContext<InputParameterType> context, Class to);
}
