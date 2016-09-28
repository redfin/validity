package com.redfin.validity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.function.Predicate;

final class DescriptivePredicateTest implements ContractAbstractDescriptivePredicate<DescriptivePredicate<String>> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test Values & Helpers
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private static final String DESCRIPTION = "null != {}";
    private static final Predicate<String> PREDICATE = t -> null != t;
    private static final String OTHER_DESCRIPTION = "!{}.isEmpty()";
    private static final Predicate<String> OTHER_PREDICATE = t -> !t.isEmpty();

    private DescriptivePredicate<String> getPredicate() {
        return new DescriptivePredicate<>(DESCRIPTION, PREDICATE);
    }

    private DescriptivePredicate<String> getOtherPredicate() {
        return new DescriptivePredicate<>(OTHER_DESCRIPTION, OTHER_PREDICATE);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test Contract Implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public DescriptivePredicate<String> getNotValueTypeInstance() {
        return new DescriptivePredicate<>(DESCRIPTION, PREDICATE);
    }

    @Override
    public DescriptivePredicate<String> getAbstractDescriptivePredicateInstance(String description) {
        return new DescriptivePredicate<>(description, t -> true);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test Cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    void testDescriptivePredicateHasExpectedPredicate() {
        Assertions.assertFalse(getPredicate().test(null),
                               "Expected false for null");
        Assertions.assertTrue(getPredicate().test("hello"),
                              "Expected true for non-null");
    }

    @Test
    void testDescriptivePredicateNegateHasExpectedPredicate() {
        Assertions.assertTrue(getPredicate().negate().test(null),
                              "Expected true for null)");
        Assertions.assertFalse(getPredicate().negate().test("hello"),
                               "Expected false for non-null");
    }

    @Test
    void testDescriptivePredicateAndHasExpectedPredicate() {
        Assertions.assertFalse(getPredicate().and(getOtherPredicate()).test(null),
                               "Expected false for null");
        Assertions.assertFalse(getPredicate().and(getOtherPredicate()).test(""),
                               "Expected false for the empty string");
        Assertions.assertTrue(getPredicate().and(getOtherPredicate()).test("hello"),
                              "Expected true for \"hello\"");
    }

    @Test
    void testDescriptivePredicateOrHasExpectedPredicate() {
        Assertions.assertThrows(NullPointerException.class,
                                () -> getPredicate().or(getOtherPredicate()).test(null));
        Assertions.assertTrue(getPredicate().or(getOtherPredicate()).test(""),
                              "Expected true for the empty string");
        Assertions.assertTrue(getPredicate().or(getOtherPredicate()).test("hello"),
                              "Expected true for \"hello\"");
    }
}
