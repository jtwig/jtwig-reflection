package org.jtwig.reflection.util;

import com.google.common.base.Optional;
import com.google.common.base.Supplier;

public final class Optionals {

    private Optionals () {}
    public static <T> Optional<T> orOptionalSupplier (Optional<T> input, Supplier<Optional<T>> supplier) {
        if (!input.isPresent()) {
            return supplier.get();
        } else {
            return input;
        }
    }


}
