/*
 * Copyright: (c) 2016 Redfin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.redfin.validity.verifiers.primitives;

import com.redfin.validity.FailedValidationExecutor;
import com.redfin.validity.FailedValidationExecutors;
import com.redfin.validity.ValidityUtils;
import com.redfin.validity.verifiers.AbstractVerifiablePrimitiveContract;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

final class VerifiablePrimitiveDoubleTest implements AbstractVerifiablePrimitiveContract<IllegalArgumentException, VerifiablePrimitiveDouble<IllegalArgumentException>> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test values & contract implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public VerifiablePrimitiveDouble<IllegalArgumentException> getNotValueTypeInstance() {
        return getInstance(0);
    }

    @Override
    public FailedValidationExecutor<IllegalArgumentException> getAbstractVerifiablePrimitiveFailedValidationExecutor() {
        return FailedValidationExecutors.getDefaultFailureExecutor();
    }

    @Override
    public VerifiablePrimitiveDouble<IllegalArgumentException> getAbstractVerifiablePrimitive(FailedValidationExecutor<IllegalArgumentException> failedValidationExecutor, String message) {
        return new VerifiablePrimitiveDouble<>(failedValidationExecutor, 0, message);
    }

    private VerifiablePrimitiveDouble<IllegalArgumentException> getInstance(double subject) {
        return new VerifiablePrimitiveDouble<>(getAbstractVerifiablePrimitiveFailedValidationExecutor(), subject, "message");
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    void testIsEqualToReturnsSubjectForEqual() {
        double subject = 0;
        VerifiablePrimitiveDouble<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isEqualTo(0),
                              "VerifiablePrimitiveDouble should return the subject for isEqualTo with equal.");
    }

    @Test
    void testIsEqualToThrowsForNotEqual() {
        double subject = 0;
        VerifiablePrimitiveDouble<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isEqualTo(1));
    }

    @Test
    void testIsNotEqualToReturnsSubjectForNotEqual() {
        double subject = 0;
        VerifiablePrimitiveDouble<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isNotEqualTo(1),
                              "VerifiablePrimitiveDouble should return the subject for isNotEqualTo with non-equal.");
    }

    @Test
    void testIsNotEqualThrowsForEqual() {
        double subject = 0;
        VerifiablePrimitiveDouble<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isNotEqualTo(subject));
    }

    @Test
    void testIsZeroReturnsSubjectForZero() {
        double subject = 0;
        VerifiablePrimitiveDouble<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isZero(),
                              "VerifiablePrimitiveDouble should return the subject for isZero with zero.");
    }

    @Test
    void testIsZeroThrowsForNonZero() {
        double subject = 1;
        VerifiablePrimitiveDouble<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isZero);
    }

    @Test
    void testIsNotZeroReturnsSubjectForNonZero() {
        double subject = 1;
        VerifiablePrimitiveDouble<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isNotZero(),
                              "VerifiablePrimitiveDouble should return the subject for isNotZero with non-zero.");
    }

    @Test
    void testIsNotZeroThrowsForZero() {
        double subject = 0;
        VerifiablePrimitiveDouble<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isNotZero);
    }

    @Test
    void testIsStrictlyPositiveReturnsSubjectForPositive() {
        double subject = 1;
        VerifiablePrimitiveDouble<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isStrictlyPositive(),
                              "VerifiablePrimitiveDouble should return the subject for isStrictlyPositive with positive.");
    }

    @Test
    void testIsStrictlyPositiveThrowsForZero() {
        double subject = 0;
        VerifiablePrimitiveDouble<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isStrictlyPositive);
    }

    @Test
    void testIsStrictlyPositiveThrowsForNegative() {
        double subject = -1;
        VerifiablePrimitiveDouble<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isStrictlyPositive);
    }

    @Test
    void testIsStrictlyNegativeReturnsSubjectForNegative() {
        double subject = -1;
        VerifiablePrimitiveDouble<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isStrictlyNegative(),
                              "VerifiablePrimitiveDouble should return the subject for isStrictlyNegative with positive.");
    }

    @Test
    void testIsStrictlyNegativeThrowsForZero() {
        double subject = 0;
        VerifiablePrimitiveDouble<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isStrictlyNegative);
    }

    @Test
    void testIsStrictlyNegativeThrowsForPositive() {
        double subject = 1;
        VerifiablePrimitiveDouble<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isStrictlyNegative);
    }

    @Test
    void testIsGreaterThanReturnsSubjectForMatchingSubject() {
        double subject = 1;
        VerifiablePrimitiveDouble<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isGreaterThan(0),
                              "VerifiablePrimitiveDouble should return the subject for greater isGreaterThan.");
    }

    @Test
    void testIsGreaterThanThrowsForNonMatchingSubject() {
        double subject = 0;
        VerifiablePrimitiveDouble<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isGreaterThan(subject));
    }

    @Test
    void testIsGreaterThanOrEqualToReturnsSubjectForEqual() {
        double subject = 1;
        VerifiablePrimitiveDouble<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isGreaterThanOrEqualTo(subject),
                              "VerifiablePrimitiveDouble should return the subject for equal isGreaterThanOrEqualTo.");
    }

    @Test
    void testIsGreaterThanOrEqualToReturnsSubjectForGreaterThan() {
        double subject = 1;
        VerifiablePrimitiveDouble<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isGreaterThanOrEqualTo(0),
                              "VerifiablePrimitiveDouble should return the subject for greater isGreaterThanOrEqualTo.");
    }

    @Test
    void testIsGreaterThanOrEqualToThrowsForNonMatchingSubject() {
        double subject = 1;
        VerifiablePrimitiveDouble<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isGreaterThanOrEqualTo(2));
    }

    @Test
    void testIsAtLeastReturnsSubjectForEqual() {
        double subject = 1;
        VerifiablePrimitiveDouble<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isAtLeast(1),
                              "VerifiablePrimitiveDouble should return the subject for equal isAtLeast.");
    }

    @Test
    void testIsAtLeastReturnsSubjectForGreaterThan() {
        double subject = 1;
        VerifiablePrimitiveDouble<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isAtLeast(0),
                              "VerifiablePrimitiveDouble should return the subject for less isAtLeast.");
    }

    @Test
    void testIsAtLeastThrowsForNonMatchingSubject() {
        double subject = 1;
        VerifiablePrimitiveDouble<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isAtLeast(2));
    }

    @Test
    void testIsLessThanReturnsSubjectForMatchingSubject() {
        double subject = 1;
        VerifiablePrimitiveDouble<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isLessThan(2),
                              "VerifiablePrimitiveDouble should return the subject for greater isLessThan.");
    }

    @Test
    void testIsLessThanThrowsForNonMatchingSubject() {
        double subject = 2;
        VerifiablePrimitiveDouble<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isLessThan(1));
    }

    @Test
    void testIsLessThanOrEqualToReturnsSubjectForEqual() {
        double subject = 1;
        VerifiablePrimitiveDouble<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isLessThanOrEqualTo(1),
                              "VerifiablePrimitiveDouble should return the subject for greater isLessThanOrEqualTo.");
    }

    @Test
    void testIsLessThanOrEqualToReturnsSubjectForLessThan() {
        double subject = 1;
        VerifiablePrimitiveDouble<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isLessThanOrEqualTo(2),
                              "VerifiablePrimitiveDouble should return the subject for greater isLessThanOrEqualTo.");
    }

    @Test
    void testIsLessThanOrEqualToThrowsForNonMatchingSubject() {
        double subject = 2;
        VerifiablePrimitiveDouble<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isLessThanOrEqualTo(1));
    }

    @Test
    void testIsAtMostReturnsSubjectForEqual() {
        double subject = 1;
        VerifiablePrimitiveDouble<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isAtMost(1),
                              "VerifiablePrimitiveDouble should return the subject for equal isAtMost.");
    }

    @Test
    void testIsAtMostReturnsSubjectForLessThan() {
        double subject = 0;
        VerifiablePrimitiveDouble<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isAtMost(1),
                              "VerifiablePrimitiveDouble should return the subject for greater isAtMost.");
    }

    @Test
    void testIsAtMostThrowsForNonMatchingSubject() {
        double subject = 1;
        VerifiablePrimitiveDouble<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isAtMost(0));
    }

    @Test
    void testSatisfiesReturnsSubjectForMatchingSubject() {
        double subject = 0;
        VerifiablePrimitiveDouble<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.satisfies(t -> t == 0),
                              "VerifiablePrimitiveDouble should return the subject for matching satisfies.");
    }

    @Test
    void testSatisfiesThrowsForNonMatchingSubject() {
        double subject = 0;
        VerifiablePrimitiveDouble<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.satisfies(t -> t != 0));
    }

    @Test
    void testSatisfiesThrowsForNullPredicate() {
        double subject = 0;
        VerifiablePrimitiveDouble<IllegalArgumentException> verifiable = getInstance(subject);
        NullPointerException exception = Assertions.expectThrows(NullPointerException.class,
                                                                 () -> verifiable.satisfies(null));
        Assertions.assertEquals(ValidityUtils.nullArgumentMessage("expected"),
                                exception.getMessage(),
                                "VerifiablePrimitiveDouble satisfies should throw expected exception for null predicate.");
    }

    @Test
    void testToStringReturnsExpectedString() {
        double subject = 0;
        VerifiablePrimitiveDouble<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertEquals(verifiable.getClass().getSimpleName() + ": <" + ValidityUtils.describe(subject) + ">",
                                verifiable.toString(),
                                "VerifiablePrimitiveBoolean should return expected String for toString.");
    }
}
