package org.jtwig.reflection.resolver;

import com.google.common.base.Optional;
import org.jtwig.reflection.Executable;
import org.jtwig.reflection.model.bean.BeanMethod;

import java.util.List;

public interface BeanMethodResolver<InputParameterType> {
    Optional<Executable> resolve (BeanMethod beanMethod, List<InputParameterType> inputParameterList);
}
