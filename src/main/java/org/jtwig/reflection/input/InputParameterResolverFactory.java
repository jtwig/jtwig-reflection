package org.jtwig.reflection.input;

import org.jtwig.reflection.model.java.JavaMethod;

import java.util.List;

public interface InputParameterResolverFactory<InputParameter> {
    InputParameterResolver<InputParameter> create (JavaMethod method, List<InputParameter> inputParameters);
}
