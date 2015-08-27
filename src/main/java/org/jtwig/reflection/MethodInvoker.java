package org.jtwig.reflection;

import com.google.common.base.Optional;
import org.jtwig.reflection.model.bean.BeanMethod;

import java.util.Collection;
import java.util.List;

public interface MethodInvoker<InputParameter> {
    Optional<Executable> invoke (Request<InputParameter> request);

    public static class Request<InputParameter> {
        private final Collection<BeanMethod> beanMethods;
        private final List<InputParameter> inputParameters;

        public Request(Collection<BeanMethod> beanMethods, List<InputParameter> inputParameters) {
            this.beanMethods = beanMethods;
            this.inputParameters = inputParameters;
        }

        public Collection<BeanMethod> getBeanMethods() {
            return beanMethods;
        }

        public List<InputParameter> getInputParameters() {
            return inputParameters;
        }
    }
}
