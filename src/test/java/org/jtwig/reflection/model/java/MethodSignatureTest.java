package org.jtwig.reflection.model.java;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MethodSignatureTest {
    @Test
    public void equalsTest() throws Exception {

        assertEquals(
                new MethodSignature(String.class, Object.class),
                new MethodSignature(String.class, Object.class)
        );

        assertEquals(
                new MethodSignature(),
                new MethodSignature()
        );

        assertNotEquals(
                new MethodSignature(String.class),
                new MethodSignature(Object.class)
        );
    }

    @Test
    public void testHashCode() throws Exception {
        assertEquals(
                new MethodSignature(String.class, Object.class).hashCode(),
                new MethodSignature(String.class, Object.class).hashCode()
        );

        assertEquals(
                new MethodSignature().hashCode(),
                new MethodSignature().hashCode()
        );

        assertNotEquals(
                new MethodSignature(String.class).hashCode(),
                new MethodSignature(Object.class).hashCode()
        );
    }

}