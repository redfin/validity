package com.redfin.validity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.function.Predicate;

final class DescriptionsTest implements ContractNonInstantiable<Descriptions> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test Contract Implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public Class<Descriptions> getTestClass() {
        return Descriptions.class;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test Cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    void testGetNullArgumentMessageReturnsExpectedString() {
        String argumentName = "hello";
        Assertions.assertEquals("May not have null as the value for the argument: " + argumentName,
                                Descriptions.nullArgumentMessage(argumentName),
                                "Descriptions null argument message should return the expected string.");
    }

    @Test
    void testGetNullArgumentMessageThrowsExceptionForNullValue() {
        NullPointerException exception = Assertions.expectThrows(NullPointerException.class, () -> Descriptions.nullArgumentMessage(null));
        Assertions.assertEquals("Cannot call nullArgumentMessage with a null argumentName",
                                exception.getMessage(),
                                "Descriptions null argument message should throw the expected exception with a null argumentName.");
    }

    /*
     * Most of the methods in the Descriptions class are simply using the
     * String.valueOf methods to convert them. Kind of pointless
     * to unit test those at this time. If we start decorating them
     * in the future, we should add tests to make sure they are decorated
     * at that point.
     */

    @Test
    void testDescribeNullStringReturnsExpectedString() {
        Assertions.assertEquals("null",
                                Descriptions.describe((String) null),
                                "Describing a null object should return the expected String.");
    }

    @Test
    void testDescribeStringReturnsQuotedString() {
        String str = "hello";
        Assertions.assertEquals("\"" + str + "\"",
                                Descriptions.describe(str),
                                "Describing a String should return the same string in quotes.");
    }

    @Test
    void testDescribeMultiDimensionArrayDoesDeepToString() {
        int[][] arr = {{1, 2}, {3, 4}};
        Assertions.assertEquals("[[1, 2], [3, 4]]",
                                Descriptions.describe(arr),
                                "Describing a multi dimensional array should return the expected String.");
    }

    @Test
    void testDescribeNullObjectReturnsExpectedString() {
        Assertions.assertEquals("null",
                                Descriptions.describe((Object) null),
                                "Describing a null object should return the expected String.");
    }

    @Test
    void testDescribePredicateReturnsExpectedString() {
        Predicate<String> predicate = t -> null != t;
        Assertions.assertEquals(Descriptions.unknownPredicatePrefix() + predicate.toString(),
                                Descriptions.describe(predicate),
                                "Describing a non-descriptive predicate should return the expected String.");
    }

    @Test
    void testDescribeDescriptivePredicateReturnsExpectedString() {
        DescriptivePredicate<String> predicate = new DescriptivePredicate<>("null != {}", t -> null != t);
        Assertions.assertEquals(predicate.toString(),
                                Descriptions.describe(predicate),
                                "Describing a non-descriptive predicate should return the expected String.");
    }
}
