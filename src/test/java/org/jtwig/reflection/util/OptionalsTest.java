package org.jtwig.reflection.util;

import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

public class OptionalsTest {
    @Test
    public void orOptionalSupplierBothOptional() throws Exception {
        Optional<Object> result = Optionals.orOptionalSupplier(
                Optional.absent(),
                supplierOf(Optional.absent())
        );

        assertThat(result.isPresent(), is(false));
    }
    @Test
    public void orOptionalSupplierFirstIn() throws Exception {
        Object value = new Object();
        Supplier<Optional<Object>> supplier = supplierOf(Optional.absent());
        Optional<Object> result = Optionals.orOptionalSupplier(
                Optional.of(value),
                supplier
        );

        assertThat(result.isPresent(), is(true));
        assertThat(result.get(), is(value));
        verify(supplier, never()).get();
    }
    @Test
    public void orOptionalSupplierSecondIn() throws Exception {
        Object value = new Object();
        Optional<Object> result = Optionals.orOptionalSupplier(
                Optional.absent(),
                supplierOf(Optional.of(value))
        );

        assertThat(result.isPresent(), is(true));
        assertThat(result.get(), is(value));
    }
    @Test
    public void orOptionalSupplierBothIn() throws Exception {
        Object value = new Object();
        Supplier<Optional<Object>> supplier = supplierOf(Optional.of(new Object()));
        Optional<Object> result = Optionals.orOptionalSupplier(
                Optional.of(value),
                supplier
        );

        assertThat(result.isPresent(), is(true));
        assertThat(result.get(), is(value));
        verify(supplier, never()).get();
    }

    private Supplier<Optional<Object>> supplierOf(final Optional<Object> optional) {
        Supplier<Optional<Object>> supplier = mock(Supplier.class);
        when(supplier.get()).thenReturn(optional);
        return supplier;
    }
}