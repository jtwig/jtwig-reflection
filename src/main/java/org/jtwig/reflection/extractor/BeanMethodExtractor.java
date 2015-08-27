package org.jtwig.reflection.extractor;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.jtwig.reflection.model.bean.BeanMethod;
import org.jtwig.reflection.model.java.JavaMethod;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

public class BeanMethodExtractor {
    public Collection<BeanMethod> extract (Object bean, Predicate<JavaMethod> predicate) {
        Collection<BeanMethod> result = new ArrayList<BeanMethod>();
        for (Method method : bean.getClass().getMethods()) {
            JavaMethod javaMethod = new JavaMethod(method);
            if (predicate.apply(javaMethod)) {
                result.add(new BeanMethod(bean, javaMethod));
            }
        }
        return result;
    }
    public Collection<BeanMethod> extract (Object bean) {
        return extract(bean, Predicates.<JavaMethod>alwaysTrue());
    }
}
