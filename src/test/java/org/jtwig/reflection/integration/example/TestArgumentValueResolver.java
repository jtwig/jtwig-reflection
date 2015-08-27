package org.jtwig.reflection.integration.example;

import org.jtwig.reflection.input.InputParameterValueResolver;

public class TestArgumentValueResolver implements InputParameterValueResolver<TestArgument> {
    @Override
    public Object resolve(TestArgument value) {
        return value.getValue();
    }
}
