package com.redfin.validity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Contract to be implemented by tests for classes that disallow value type
 * semantics (e.g. should throw an exception if the equals or hashCode methods
 * are called).
 *
 * @param <T> the type that is being tested.
 */
interface ContractNotValueType<T> {

    /**
     * Method for implementing class to allow for inheriting tests.
     *
     * @return an instance of the type under test.
     */
    T getNotValueTypeInstance();

    @Test
    default void testNotValueTypeThrowsExpectedExceptionForEquals() {
        T instance = getNotValueTypeInstance();
        UnsupportedOperationException exception = Assertions.expectThrows(UnsupportedOperationException.class, () -> instance.equals(instance));
        Assertions.assertEquals(Descriptions.unsupportedEqualsMessage(),
                                exception.getMessage(),
                                "A non-value type class should throw an exception for equals with the expected message.");
    }

    @Test
    default void testNotValueTypeThrowsExpectedExceptionForHashCode() {
        T instance = getNotValueTypeInstance();
        UnsupportedOperationException exception = Assertions.expectThrows(UnsupportedOperationException.class, instance::hashCode);
        Assertions.assertEquals(Descriptions.unsupportedHashCodeMessage(),
                                exception.getMessage(),
                                "A non-value type class should throw an exception for hashCode with the expected message.");
    }
}
