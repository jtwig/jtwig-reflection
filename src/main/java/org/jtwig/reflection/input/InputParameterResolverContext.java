package org.jtwig.reflection.input;

import com.google.common.base.Function;
import org.jtwig.reflection.util.Lists2;

import java.util.List;

public class InputParameterResolverContext<T> {
    private final List<InputParameter<T>> inputParameters;

    private InputParameterResolverContext (InputParameterResolverContext<T> prototype) {
        this.inputParameters = Lists2.transform(prototype.inputParameters, new Function<InputParameter<T>, InputParameter<T>>() {
            @Override
            public InputParameter<T> apply(InputParameter<T> input) {
                InputParameter<T> clone = new InputParameter<T>(input.value);
                clone.used = input.used;
                return clone;
            }
        });
    }

    public InputParameterResolverContext(List<T> inputParameters) {
        this.inputParameters = Lists2.transform(inputParameters, new Function<T, InputParameter<T>>() {
            @Override
            public InputParameter<T> apply(T input) {
                return new InputParameter<T>(input);
            }
        });
    }

    public InputParameterResolverContext<T> markAsUsed(int position) {
        inputParameters.get(position).used = true;
        return this;
    }

    public boolean isUsed (int position) {
        return inputParameters.get(position).used;
    }

    public T value (int position) {
        return inputParameters.get(position).value;
    }

    public int size () {
        return inputParameters.size();
    }

    @Override
    public InputParameterResolverContext<T> clone () {
        return new InputParameterResolverContext<T>(this);
    }

    public void merge(InputParameterResolverContext<T> clonedContext) {
        for (int i = 0; i < clonedContext.inputParameters.size(); i++) {
            inputParameters.get(i).used = clonedContext.inputParameters.get(i).used;
        }
    }

    public boolean fullyUsed() {
        for (InputParameter<T> inputParameter : inputParameters) {
            if (!inputParameter.used) return false;
        }
        return true;
    }

    private static class InputParameter<T> {
        private final T value;
        private boolean used;

        public InputParameter(T value) {
            this.value = value;
        }
    }
}
