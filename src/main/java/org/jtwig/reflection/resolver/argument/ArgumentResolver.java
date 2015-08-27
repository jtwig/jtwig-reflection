package org.jtwig.reflection.resolver.argument;

import com.google.common.base.Optional;
import org.jtwig.reflection.model.Value;
import org.jtwig.reflection.model.java.JavaMethodArgument;

public interface ArgumentResolver {
    Optional<Value> resolve (JavaMethodArgument methodArgument);
}
