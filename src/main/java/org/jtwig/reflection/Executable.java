package org.jtwig.reflection;

import com.google.common.base.Supplier;
import org.jtwig.reflection.model.bean.BeanMethod;

public class Executable implements Supplier<Object> {
    private final BeanMethod beanMethod;
    private final Object[] arguments;

    public Executable(BeanMethod beanMethod, Object[] arguments) {
        this.beanMethod = beanMethod;
        this.arguments = arguments;
    }

    @Override
    public Object get() {
        return beanMethod.invoke(arguments);
    }
}
