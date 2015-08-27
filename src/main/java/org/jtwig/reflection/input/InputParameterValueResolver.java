package org.jtwig.reflection.input;

public interface InputParameterValueResolver<InputParameterType> {
    Object resolve (InputParameterType value);
}
