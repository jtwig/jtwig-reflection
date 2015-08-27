package org.jtwig.reflection.util;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;

import java.util.ArrayList;
import java.util.List;

public final class Lists2 {
    private Lists2 () {}

    public static <O, I> List<O> transform (List<I> inputList, Function<I, O> transformation) {
        List<O> result = new ArrayList<O>();
        for (I input : inputList) {
            result.add(transformation.apply(input));
        }
        return result;
    }
    public static <T> List<T> filter (List<T> inputList, Predicate<T> filter) {
        List<T> result = new ArrayList<T>();
        for (T input : inputList) {
            if (filter.apply(input)) {
                result.add(input);
            }
        }
        return result;
    }

    public static <T> Optional<T> find(List<T> list, Predicate<T> predicate) {
        for (T element : list) {
            if (element != null && predicate.apply(element)) {
                return Optional.of(element);
            }
        }
        return Optional.absent();
    }

    public static <T> int positionOf(List<T> inputParameters, Predicate<T> predicate) {
        for (int i = 0; i < inputParameters.size(); i++) {
            if (predicate.apply(inputParameters.get(i))) {
                return i;
            }
        }
        return -1;
    }
}
