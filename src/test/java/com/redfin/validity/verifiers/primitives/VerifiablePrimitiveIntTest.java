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

final class VerifiablePrimitiveIntTest implements AbstractVerifiablePrimitiveContract<IllegalArgumentException, VerifiablePrimitiveInt<IllegalArgumentException>> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test values & contract implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public VerifiablePrimitiveInt<IllegalArgumentException> getNotValueTypeInstance() {
        return getInstance(0);
    }

    @Override
    public FailedValidationExecutor<IllegalArgumentException> getAbstractVerifiablePrimitiveFailedValidationExecutor() {
        return FailedValidationExecutors.getDefaultFailureExecutor(IllegalArgumentException::new);
    }

    @Override
    public VerifiablePrimitiveInt<IllegalArgumentException> getAbstractVerifiablePrimitive(FailedValidationExecutor<IllegalArgumentException> failedValidationExecutor, String message) {
        return new VerifiablePrimitiveInt<>(failedValidationExecutor, 0, message);
    }

    private VerifiablePrimitiveInt<IllegalArgumentException> getInstance(int subject) {
        return new VerifiablePrimitiveInt<>(getAbstractVerifiablePrimitiveFailedValidationExecutor(), subject, "message");
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    void testIsEqualToReturnsSubjectForEqual() {
        int subject = 0;
        VerifiablePrimitiveInt<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isEqualTo(0),
                              "VerifiablePrimitiveInt should return the subject for isEqualTo with equal.");
    }

    @Test
    void testIsEqualToThrowsForNotEqual() {
        int subject = 0;
        VerifiablePrimitiveInt<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isEqualTo(1));
    }

    @Test
    void testIsNotEqualToReturnsSubjectForNotEqual() {
        int subject = 0;
        VerifiablePrimitiveInt<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isNotEqualTo(1),
                              "VerifiablePrimitiveInt should return the subject for isNotEqualTo with non-equal.");
    }

    @Test
    void testIsNotEqualThrowsForEqual() {
        int subject = 0;
        VerifiablePrimitiveInt<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isNotEqualTo(subject));
    }

    @Test
    void testIsZeroReturnsSubjectForZero() {
        int subject = 0;
        VerifiablePrimitiveInt<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isZero(),
                              "VerifiablePrimitiveInt should return the subject for isZero with zero.");
    }

    @Test
    void testIsZeroThrowsForNonZero() {
        int subject = 1;
        VerifiablePrimitiveInt<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isZero);
    }

    @Test
    void testIsNotZeroReturnsSubjectForNonZero() {
        int subject = 1;
        VerifiablePrimitiveInt<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isNotZero(),
                              "VerifiablePrimitiveInt should return the subject for isNotZero with non-zero.");
    }

    @Test
    void testIsNotZeroThrowsForZero() {
        int subject = 0;
        VerifiablePrimitiveInt<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isNotZero);
    }

    @Test
    void testIsStrictlyPositiveReturnsSubjectForPositive() {
        int subject = 1;
        VerifiablePrimitiveInt<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isStrictlyPositive(),
                              "VerifiablePrimitiveInt should return the subject for isStrictlyPositive with positive.");
    }

    @Test
    void testIsStrictlyPositiveThrowsForZero() {
        int subject = 0;
        VerifiablePrimitiveInt<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isStrictlyPositive);
    }

    @Test
    void testIsStrictlyPositiveThrowsForNegative() {
        int subject = -1;
        VerifiablePrimitiveInt<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isStrictlyPositive);
    }

    @Test
    void testIsStrictlyNegativeReturnsSubjectForNegative() {
        int subject = -1;
        VerifiablePrimitiveInt<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isStrictlyNegative(),
                              "VerifiablePrimitiveInt should return the subject for isStrictlyNegative with positive.");
    }

    @Test
    void testIsStrictlyNegativeThrowsForZero() {
        int subject = 0;
        VerifiablePrimitiveInt<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isStrictlyNegative);
    }

    @Test
    void testIsStrictlyNegativeThrowsForPositive() {
        int subject = 1;
        VerifiablePrimitiveInt<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isStrictlyNegative);
    }

    @Test
    void testIsGreaterThanReturnsSubjectForMatchingSubject() {
        int subject = 1;
        VerifiablePrimitiveInt<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isGreaterThan(0),
                              "VerifiablePrimitiveInt should return the subject for greater isGreaterThan.");
    }

    @Test
    void testIsGreaterThanThrowsForNonMatchingSubject() {
        int subject = 0;
        VerifiablePrimitiveInt<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isGreaterThan(subject));
    }

    @Test
    void testIsGreaterThanOrEqualToReturnsSubjectForEqual() {
        int subject = 1;
        VerifiablePrimitiveInt<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isGreaterThanOrEqualTo(subject),
                              "VerifiablePrimitiveInt should return the subject for equal isGreaterThanOrEqualTo.");
    }

    @Test
    void testIsGreaterThanOrEqualToReturnsSubjectForGreaterThan() {
        int subject = 1;
        VerifiablePrimitiveInt<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isGreaterThanOrEqualTo(0),
                              "VerifiablePrimitiveInt should return the subject for greater isGreaterThanOrEqualTo.");
    }

    @Test
    void testIsGreaterThanOrEqualToThrowsForNonMatchingSubject() {
        int subject = 1;
        VerifiablePrimitiveInt<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isGreaterThanOrEqualTo(2));
    }

    @Test
    void testIsAtLeastReturnsSubjectForEqual() {
        int subject = 1;
        VerifiablePrimitiveInt<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isAtLeast(1),
                              "VerifiablePrimitiveInt should return the subject for equal isAtLeast.");
    }

    @Test
    void testIsAtLeastReturnsSubjectForGreaterThan() {
        int subject = 1;
        VerifiablePrimitiveInt<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isAtLeast(0),
                              "VerifiablePrimitiveInt should return the subject for less isAtLeast.");
    }

    @Test
    void testIsAtLeastThrowsForNonMatchingSubject() {
        int subject = 1;
        VerifiablePrimitiveInt<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isAtLeast(2));
    }

    @Test
    void testIsLessThanReturnsSubjectForMatchingSubject() {
        int subject = 1;
        VerifiablePrimitiveInt<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isLessThan(2),
                              "VerifiablePrimitiveInt should return the subject for greater isLessThan.");
    }

    @Test
    void testIsLessThanThrowsForNonMatchingSubject() {
        int subject = 2;
        VerifiablePrimitiveInt<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isLessThan(1));
    }

    @Test
    void testIsLessThanOrEqualToReturnsSubjectForEqual() {
        int subject = 1;
        VerifiablePrimitiveInt<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isLessThanOrEqualTo(1),
                              "VerifiablePrimitiveInt should return the subject for greater isLessThanOrEqualTo.");
    }

    @Test
    void testIsLessThanOrEqualToReturnsSubjectForLessThan() {
        int subject = 1;
        VerifiablePrimitiveInt<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isLessThanOrEqualTo(2),
                              "VerifiablePrimitiveInt should return the subject for greater isLessThanOrEqualTo.");
    }

    @Test
    void testIsLessThanOrEqualToThrowsForNonMatchingSubject() {
        int subject = 2;
        VerifiablePrimitiveInt<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isLessThanOrEqualTo(1));
    }

    @Test
    void testIsAtMostReturnsSubjectForEqual() {
        int subject = 1;
        VerifiablePrimitiveInt<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isAtMost(1),
                              "VerifiablePrimitiveInt should return the subject for equal isAtMost.");
    }

    @Test
    void testIsAtMostReturnsSubjectForLessThan() {
        int subject = 0;
        VerifiablePrimitiveInt<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isAtMost(1),
                              "VerifiablePrimitiveInt should return the subject for greater isAtMost.");
    }

    @Test
    void testIsAtMostThrowsForNonMatchingSubject() {
        int subject = 1;
        VerifiablePrimitiveInt<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isAtMost(0));
    }

    @Test
    void testSatisfiesReturnsSubjectForMatchingSubject() {
        int subject = 0;
        VerifiablePrimitiveInt<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.satisfies(t -> t == 0),
                              "VerifiablePrimitiveInt should return the subject for matching satisfies.");
    }

    @Test
    void testSatisfiesThrowsForNonMatchingSubject() {
        int subject = 0;
        VerifiablePrimitiveInt<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.satisfies(t -> t != 0));
    }

    @Test
    void testSatisfiesThrowsForNullPredicate() {
        int subject = 0;
        VerifiablePrimitiveInt<IllegalArgumentException> verifiable = getInstance(subject);
        NullPointerException exception = Assertions.expectThrows(NullPointerException.class,
                                                                 () -> verifiable.satisfies(null));
        Assertions.assertEquals(ValidityUtils.nullArgumentMessage("expected"),
                                exception.getMessage(),
                                "VerifiablePrimitiveInt satisfies should throw expected exception for null predicate.");
    }

    @Test
    void testToStringReturnsExpectedString() {
        int subject = 0;
        VerifiablePrimitiveInt<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertEquals(verifiable.getClass().getSimpleName() + ": <" + ValidityUtils.describe(subject) + ">",
                                verifiable.toString(),
                                "VerifiablePrimitiveBoolean should return expected String for toString.");
    }
}
