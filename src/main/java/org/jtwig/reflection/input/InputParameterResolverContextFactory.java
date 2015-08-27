package org.jtwig.reflection.input;

import java.util.List;

public class InputParameterResolverContextFactory<InputParameterType> {
    public InputParameterResolverContext<InputParameterType> create (List<InputParameterType> inputParameters) {
        return new InputParameterResolverContext<InputParameterType>(inputParameters);
    }
}
