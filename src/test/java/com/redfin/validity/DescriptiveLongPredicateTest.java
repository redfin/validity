package com.redfin.validity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.function.LongPredicate;

final class DescriptiveLongPredicateTest implements ContractAbstractDescriptivePredicate<DescriptiveLongPredicate> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test Values & Helpers
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private static final String DESCRIPTION = "{} > 0";
    private static final LongPredicate PREDICATE = t -> t > 0;
    private static final String OTHER_DESCRIPTION = "{} < 10";
    private static final LongPredicate OTHER_PREDICATE = t -> t < 10;

    private DescriptiveLongPredicate getPredicate() {
        return new DescriptiveLongPredicate(DESCRIPTION, PREDICATE);
    }

    private DescriptiveLongPredicate getOtherPredicate() {
        return new DescriptiveLongPredicate(OTHER_DESCRIPTION, OTHER_PREDICATE);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test Contract Implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public DescriptiveLongPredicate getInstance(String description) {
        return new DescriptiveLongPredicate(description, t -> true);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test Cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    void testDescriptiveLongPredicateHasExpectedPredicate() {
        Assertions.assertFalse(getPredicate().test(-1),
                               "Expected false for -1 > 0");
        Assertions.assertFalse(getPredicate().test(0),
                               "Expected false for 0 > 0");
        Assertions.assertTrue(getPredicate().test(1),
                              "Expected true for 1 > 0");
        Assertions.assertTrue(getPredicate().test(9),
                              "Expected true for 9 > 0");
    }

    @Test
    void testDescriptiveLongPredicateNegateHasExpectedPredicate() {
        Assertions.assertTrue(getPredicate().negate().test(-1),
                              "Expected true for !(-1 > 0)");
        Assertions.assertTrue(getPredicate().negate().test(0),
                              "Expected true for !(0 > 0)");
        Assertions.assertFalse(getPredicate().negate().test(1),
                               "Expected false for !(1 > 0)");
        Assertions.assertFalse(getPredicate().negate().test(9),
                               "Expected false for !(9 > 0)");
    }

    @Test
    void testDescriptiveLongPredicateAndHasExpectedPredicate() {
        Assertions.assertFalse(getPredicate().and(getOtherPredicate()).test(-1),
                               "Expected false for (-1 > 0) && (-1 < 10)");
        Assertions.assertFalse(getPredicate().and(getOtherPredicate()).test(0),
                               "Expected false for (0 > 0) && (0 < 10)");
        Assertions.assertFalse(getPredicate().and(getOtherPredicate()).test(10),
                               "Expected false for (10 > 0) && (10 < 10)");
        Assertions.assertFalse(getPredicate().and(getOtherPredicate()).test(11),
                               "Expected false for (11 > 0) && (11 < 10)");
        Assertions.assertTrue(getPredicate().and(getOtherPredicate()).test(4),
                              "Expected true for (4 > 0) && (4 < 10)");
    }

    @Test
    void testDescriptiveLongPredicateOrHasExpectedPredicate() {
        Assertions.assertTrue(getPredicate().or(getOtherPredicate()).test(-1),
                              "Expected true for (-1 > 0) || (-1 < 10)");
        Assertions.assertTrue(getPredicate().or(getOtherPredicate()).test(0),
                              "Expected true for (0 > 0) || (0 < 10)");
        Assertions.assertTrue(getPredicate().or(getOtherPredicate()).test(1),
                              "Expected true for (1 > 0) || (1 < 10)");
    }
}
