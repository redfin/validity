package com.redfin.validity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.function.DoublePredicate;

final class DescriptiveDoublePredicateTest implements ContractAbstractDescriptivePredicate<DescriptiveDoublePredicate> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test Values & Helpers
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private static final String DESCRIPTION = "{} > 0.0";
    private static final DoublePredicate PREDICATE = t -> t > 0.0;
    private static final String OTHER_DESCRIPTION = "{} < 10.0";
    private static final DoublePredicate OTHER_PREDICATE = t -> t < 10.0;

    private DescriptiveDoublePredicate getPredicate() {
        return new DescriptiveDoublePredicate(DESCRIPTION, PREDICATE);
    }

    private DescriptiveDoublePredicate getOtherPredicate() {
        return new DescriptiveDoublePredicate(OTHER_DESCRIPTION, OTHER_PREDICATE);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test Contract Implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public DescriptiveDoublePredicate getInstance(String description) {
        return new DescriptiveDoublePredicate(description, t -> true);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test Cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    void testDescriptiveDoublePredicateHasExpectedPredicate() {
        Assertions.assertFalse(getPredicate().test(-1),
                               "Expected false for -1 > 0.0");
        Assertions.assertFalse(getPredicate().test(0),
                               "Expected false for 0 > 0.0");
        Assertions.assertTrue(getPredicate().test(1),
                              "Expected true for 1 > 0.0");
        Assertions.assertTrue(getPredicate().test(9),
                              "Expected true for 9 > 0.0");
    }

    @Test
    void testDescriptiveDoublePredicateNegateHasExpectedPredicate() {
        Assertions.assertTrue(getPredicate().negate().test(-1),
                              "Expected true for !(-1 > 0.0)");
        Assertions.assertTrue(getPredicate().negate().test(0),
                              "Expected true for !(0 > 0.0)");
        Assertions.assertFalse(getPredicate().negate().test(1),
                               "Expected false for !(1 > 0.0)");
        Assertions.assertFalse(getPredicate().negate().test(9),
                               "Expected false for !(9 > 0.0)");
    }

    @Test
    void testDescriptiveDoublePredicateAndHasExpectedPredicate() {
        Assertions.assertFalse(getPredicate().and(getOtherPredicate()).test(-1),
                               "Expected false for (-1 > 0.0) && (-1 < 10)");
        Assertions.assertFalse(getPredicate().and(getOtherPredicate()).test(0),
                               "Expected false for (0 > 0.0) && (0 < 10)");
        Assertions.assertFalse(getPredicate().and(getOtherPredicate()).test(10),
                               "Expected false for (10 > 0.0) && (10 < 10)");
        Assertions.assertFalse(getPredicate().and(getOtherPredicate()).test(11),
                               "Expected false for (11 > 0.0) && (11 < 10)");
        Assertions.assertTrue(getPredicate().and(getOtherPredicate()).test(4),
                              "Expected true for (4 > 0.0) && (4 < 10)");
    }

    @Test
    void testDescriptiveDoublePredicateOrHasExpectedPredicate() {
        Assertions.assertTrue(getPredicate().or(getOtherPredicate()).test(-1),
                              "Expected true for (-1 > 0.0) || (-1 < 10)");
        Assertions.assertTrue(getPredicate().or(getOtherPredicate()).test(0),
                              "Expected true for (0 > 0.0) || (0 < 10)");
        Assertions.assertTrue(getPredicate().or(getOtherPredicate()).test(1),
                              "Expected true for (1 > 0.0) || (1 < 10)");
    }
}
