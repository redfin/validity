package com.redfin.validity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

final class AbstractDescriptivePredicateTest {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // IntPredicate Description Tests
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    void testGetDescriptionForIntPredicateReturnsExpectedValueForJavaPredicate() {
        IntPredicate predicate = t -> t > 0;
        Assertions.assertEquals(Descriptions.unknownPredicatePrefix() + predicate.toString(),
                                AbstractDescriptivePredicate.getDescription(predicate),
                                "AbstractDescriptivePredicate should return the expected description for a Java predicate.");
    }

    @Test
    void testGetDescriptionForIntPredicateReturnsExpectedValueForDescriptivePredicate() {
        DescriptiveIntPredicate predicate = new DescriptiveIntPredicate("{} > 0", t -> t > 0);
        Assertions.assertEquals(predicate.getDescription(),
                                AbstractDescriptivePredicate.getDescription(predicate),
                                "AbstractDescriptivePredicate should return the expected description for a descriptive predicate.");
    }

    @Test
    void testGetDescriptionForIntPredicateThrowsExpectedExceptionForNullPredicate() {
        NullPointerException exception = Assertions.expectThrows(NullPointerException.class, () -> AbstractDescriptivePredicate.getDescription((IntPredicate) null));
        Assertions.assertEquals(Descriptions.nullArgumentMessage("other"),
                                exception.getMessage(),
                                "AbstractDescriptivePredicate should throw the expected exception for a null other.");
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // LongPredicate Description Tests
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    void testGetDescriptionForLongPredicateReturnsExpectedValueForJavaPredicate() {
        LongPredicate predicate = t -> t > 0;
        Assertions.assertEquals(Descriptions.unknownPredicatePrefix() + predicate.toString(),
                                AbstractDescriptivePredicate.getDescription(predicate),
                                "AbstractDescriptivePredicate should return the expected description for a Java predicate.");
    }

    @Test
    void testGetDescriptionForLongPredicateReturnsExpectedValueForDescriptivePredicate() {
        DescriptiveLongPredicate predicate = new DescriptiveLongPredicate("{} > 0", t -> t > 0);
        Assertions.assertEquals(predicate.getDescription(),
                                AbstractDescriptivePredicate.getDescription(predicate),
                                "AbstractDescriptivePredicate should return the expected description for a descriptive predicate.");
    }

    @Test
    void testGetDescriptionForLongPredicateThrowsExpectedExceptionForNullPredicate() {
        NullPointerException exception = Assertions.expectThrows(NullPointerException.class, () -> AbstractDescriptivePredicate.getDescription((LongPredicate) null));
        Assertions.assertEquals(Descriptions.nullArgumentMessage("other"),
                                exception.getMessage(),
                                "AbstractDescriptivePredicate should throw the expected exception for a null other.");
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // DoublePredicate Description Tests
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    void testGetDescriptionForDoublePredicateReturnsExpectedValueForJavaPredicate() {
        DoublePredicate predicate = t -> t > 0;
        Assertions.assertEquals(Descriptions.unknownPredicatePrefix() + predicate.toString(),
                                AbstractDescriptivePredicate.getDescription(predicate),
                                "AbstractDescriptivePredicate should return the expected description for a Java predicate.");
    }

    @Test
    void testGetDescriptionForDoublePredicateReturnsExpectedValueForDescriptivePredicate() {
        DescriptiveDoublePredicate predicate = new DescriptiveDoublePredicate("{} > 0", t -> t > 0);
        Assertions.assertEquals(predicate.getDescription(),
                                AbstractDescriptivePredicate.getDescription(predicate),
                                "AbstractDescriptivePredicate should return the expected description for a descriptive predicate.");
    }

    @Test
    void testGetDescriptionForDoublePredicateThrowsExpectedExceptionForNullPredicate() {
        NullPointerException exception = Assertions.expectThrows(NullPointerException.class, () -> AbstractDescriptivePredicate.getDescription((DoublePredicate) null));
        Assertions.assertEquals(Descriptions.nullArgumentMessage("other"),
                                exception.getMessage(),
                                "AbstractDescriptivePredicate should throw the expected exception for a null other.");
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Predicate Description Tests
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    void testGetDescriptionForPredicateReturnsExpectedValueForJavaPredicate() {
        Predicate<String> predicate = t -> null != t;
        Assertions.assertEquals(Descriptions.unknownPredicatePrefix() + predicate.toString(),
                                AbstractDescriptivePredicate.getDescription(predicate),
                                "AbstractDescriptivePredicate should return the expected description for a Java predicate.");
    }

    @Test
    void testGetDescriptionForPredicateReturnsExpectedValueForDescriptivePredicate() {
        DescriptivePredicate<String> predicate = new DescriptivePredicate<>("null != {}", t -> null != t);
        Assertions.assertEquals(predicate.getDescription(),
                                AbstractDescriptivePredicate.getDescription(predicate),
                                "AbstractDescriptivePredicate should return the expected description for a descriptive predicate.");
    }

    @Test
    void testGetDescriptionForPredicateThrowsExpectedExceptionForNullPredicate() {
        NullPointerException exception = Assertions.expectThrows(NullPointerException.class, () -> AbstractDescriptivePredicate.getDescription((Predicate<String>) null));
        Assertions.assertEquals(Descriptions.nullArgumentMessage("other"),
                                exception.getMessage(),
                                "AbstractDescriptivePredicate should throw the expected exception for a null other.");
    }
}
