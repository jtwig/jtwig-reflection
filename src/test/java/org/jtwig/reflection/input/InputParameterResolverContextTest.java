package org.jtwig.reflection.input;

import org.junit.Test;

import java.util.Collections;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class InputParameterResolverContextTest {
    private InputParameterResolverContext underTest;

    @Test
    public void size() throws Exception {
        underTest = new InputParameterResolverContext(Collections.emptyList());

        int size = underTest.size();

        assertThat(size, is(0));
    }

    @Test
    public void cloneTest() throws Exception {
        underTest = new InputParameterResolverContext(asList(1));

        InputParameterResolverContext result = underTest.clone();
        result.markAsUsed(0);

        assertFalse(underTest.isUsed(0));
        assertFalse(underTest.fullyUsed());
        assertTrue(result.isUsed(0));
        assertTrue(result.fullyUsed());
    }

    @Test
    public void merge() throws Exception {
        underTest = new InputParameterResolverContext(asList(1));

        InputParameterResolverContext result = underTest.clone();
        result.markAsUsed(0);
        underTest.merge(result);

        assertTrue(underTest.isUsed(0));
        assertTrue(underTest.fullyUsed());
        assertTrue(result.isUsed(0));
        assertTrue(result.fullyUsed());
    }
}