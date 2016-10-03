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

final class VerifiablePrimitiveLongTest implements AbstractVerifiablePrimitiveContract<IllegalArgumentException, VerifiablePrimitiveLong<IllegalArgumentException>> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test values & contract implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public VerifiablePrimitiveLong<IllegalArgumentException> getNotValueTypeInstance() {
        return getInstance(0);
    }

    @Override
    public FailedValidationExecutor<IllegalArgumentException> getAbstractVerifiablePrimitiveFailedValidationExecutor() {
        return FailedValidationExecutors.getDefaultFailureExecutor();
    }

    @Override
    public VerifiablePrimitiveLong<IllegalArgumentException> getAbstractVerifiablePrimitive(FailedValidationExecutor<IllegalArgumentException> failedValidationExecutor, String message) {
        return new VerifiablePrimitiveLong<>(failedValidationExecutor, 0, message);
    }

    private VerifiablePrimitiveLong<IllegalArgumentException> getInstance(long subject) {
        return new VerifiablePrimitiveLong<>(getAbstractVerifiablePrimitiveFailedValidationExecutor(), subject, "message");
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    void testIsEqualToReturnsSubjectForEqual() {
        long subject = 0;
        VerifiablePrimitiveLong<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isEqualTo(0),
                              "VerifiablePrimitiveLong should return the subject for isEqualTo with equal.");
    }

    @Test
    void testIsEqualToThrowsForNotEqual() {
        long subject = 0;
        VerifiablePrimitiveLong<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isEqualTo(1));
    }

    @Test
    void testIsNotEqualToReturnsSubjectForNotEqual() {
        long subject = 0;
        VerifiablePrimitiveLong<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isNotEqualTo(1),
                              "VerifiablePrimitiveLong should return the subject for isNotEqualTo with non-equal.");
    }

    @Test
    void testIsNotEqualThrowsForEqual() {
        long subject = 0;
        VerifiablePrimitiveLong<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isNotEqualTo(subject));
    }

    @Test
    void testIsZeroReturnsSubjectForZero() {
        long subject = 0;
        VerifiablePrimitiveLong<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isZero(),
                              "VerifiablePrimitiveLong should return the subject for isZero with zero.");
    }

    @Test
    void testIsZeroThrowsForNonZero() {
        long subject = 1;
        VerifiablePrimitiveLong<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isZero);
    }

    @Test
    void testIsNotZeroReturnsSubjectForNonZero() {
        long subject = 1;
        VerifiablePrimitiveLong<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isNotZero(),
                              "VerifiablePrimitiveLong should return the subject for isNotZero with non-zero.");
    }

    @Test
    void testIsNotZeroThrowsForZero() {
        long subject = 0;
        VerifiablePrimitiveLong<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isNotZero);
    }

    @Test
    void testIsStrictlyPositiveReturnsSubjectForPositive() {
        long subject = 1;
        VerifiablePrimitiveLong<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isStrictlyPositive(),
                              "VerifiablePrimitiveLong should return the subject for isStrictlyPositive with positive.");
    }

    @Test
    void testIsStrictlyPositiveThrowsForZero() {
        long subject = 0;
        VerifiablePrimitiveLong<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isStrictlyPositive);
    }

    @Test
    void testIsStrictlyPositiveThrowsForNegative() {
        long subject = -1;
        VerifiablePrimitiveLong<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isStrictlyPositive);
    }

    @Test
    void testIsStrictlyNegativeReturnsSubjectForNegative() {
        long subject = -1;
        VerifiablePrimitiveLong<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isStrictlyNegative(),
                              "VerifiablePrimitiveLong should return the subject for isStrictlyNegative with positive.");
    }

    @Test
    void testIsStrictlyNegativeThrowsForZero() {
        long subject = 0;
        VerifiablePrimitiveLong<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isStrictlyNegative);
    }

    @Test
    void testIsStrictlyNegativeThrowsForPositive() {
        long subject = 1;
        VerifiablePrimitiveLong<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isStrictlyNegative);
    }

    @Test
    void testIsGreaterThanReturnsSubjectForMatchingSubject() {
        long subject = 1;
        VerifiablePrimitiveLong<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isGreaterThan(0),
                              "VerifiablePrimitiveLong should return the subject for greater isGreaterThan.");
    }

    @Test
    void testIsGreaterThanThrowsForNonMatchingSubject() {
        long subject = 0;
        VerifiablePrimitiveLong<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isGreaterThan(subject));
    }

    @Test
    void testIsGreaterThanOrEqualToReturnsSubjectForEqual() {
        long subject = 1;
        VerifiablePrimitiveLong<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isGreaterThanOrEqualTo(subject),
                              "VerifiablePrimitiveLong should return the subject for equal isGreaterThanOrEqualTo.");
    }

    @Test
    void testIsGreaterThanOrEqualToReturnsSubjectForGreaterThan() {
        long subject = 1;
        VerifiablePrimitiveLong<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isGreaterThanOrEqualTo(0),
                              "VerifiablePrimitiveLong should return the subject for greater isGreaterThanOrEqualTo.");
    }

    @Test
    void testIsGreaterThanOrEqualToThrowsForNonMatchingSubject() {
        long subject = 1;
        VerifiablePrimitiveLong<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isGreaterThanOrEqualTo(2));
    }

    @Test
    void testIsAtLeastReturnsSubjectForEqual() {
        long subject = 1;
        VerifiablePrimitiveLong<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isAtLeast(1),
                              "VerifiablePrimitiveLong should return the subject for equal isAtLeast.");
    }

    @Test
    void testIsAtLeastReturnsSubjectForGreaterThan() {
        long subject = 1;
        VerifiablePrimitiveLong<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isAtLeast(0),
                              "VerifiablePrimitiveLong should return the subject for less isAtLeast.");
    }

    @Test
    void testIsAtLeastThrowsForNonMatchingSubject() {
        long subject = 1;
        VerifiablePrimitiveLong<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isAtLeast(2));
    }

    @Test
    void testIsLessThanReturnsSubjectForMatchingSubject() {
        long subject = 1;
        VerifiablePrimitiveLong<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isLessThan(2),
                              "VerifiablePrimitiveLong should return the subject for greater isLessThan.");
    }

    @Test
    void testIsLessThanThrowsForNonMatchingSubject() {
        long subject = 2;
        VerifiablePrimitiveLong<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isLessThan(1));
    }

    @Test
    void testIsLessThanOrEqualToReturnsSubjectForEqual() {
        long subject = 1;
        VerifiablePrimitiveLong<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isLessThanOrEqualTo(1),
                              "VerifiablePrimitiveLong should return the subject for greater isLessThanOrEqualTo.");
    }

    @Test
    void testIsLessThanOrEqualToReturnsSubjectForLessThan() {
        long subject = 1;
        VerifiablePrimitiveLong<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isLessThanOrEqualTo(2),
                              "VerifiablePrimitiveLong should return the subject for greater isLessThanOrEqualTo.");
    }

    @Test
    void testIsLessThanOrEqualToThrowsForNonMatchingSubject() {
        long subject = 2;
        VerifiablePrimitiveLong<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isLessThanOrEqualTo(1));
    }

    @Test
    void testIsAtMostReturnsSubjectForEqual() {
        long subject = 1;
        VerifiablePrimitiveLong<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isAtMost(1),
                              "VerifiablePrimitiveLong should return the subject for equal isAtMost.");
    }

    @Test
    void testIsAtMostReturnsSubjectForLessThan() {
        long subject = 0;
        VerifiablePrimitiveLong<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isAtMost(1),
                              "VerifiablePrimitiveLong should return the subject for greater isAtMost.");
    }

    @Test
    void testIsAtMostThrowsForNonMatchingSubject() {
        long subject = 1;
        VerifiablePrimitiveLong<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isAtMost(0));
    }

    @Test
    void testSatisfiesReturnsSubjectForMatchingSubject() {
        long subject = 0;
        VerifiablePrimitiveLong<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.satisfies(t -> t == 0),
                              "VerifiablePrimitiveLong should return the subject for matching satisfies.");
    }

    @Test
    void testSatisfiesThrowsForNonMatchingSubject() {
        long subject = 0;
        VerifiablePrimitiveLong<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.satisfies(t -> t != 0));
    }

    @Test
    void testSatisfiesThrowsForNullPredicate() {
        long subject = 0;
        VerifiablePrimitiveLong<IllegalArgumentException> verifiable = getInstance(subject);
        NullPointerException exception = Assertions.expectThrows(NullPointerException.class,
                                                                 () -> verifiable.satisfies(null));
        Assertions.assertEquals(ValidityUtils.nullArgumentMessage("expected"),
                                exception.getMessage(),
                                "VerifiablePrimitiveLong satisfies should throw expected exception for null predicate.");
    }

    @Test
    void testToStringReturnsExpectedString() {
        long subject = 0;
        VerifiablePrimitiveLong<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertEquals(verifiable.getClass().getSimpleName() + ": <" + ValidityUtils.describe(subject) + ">",
                                verifiable.toString(),
                                "VerifiablePrimitiveBoolean should return expected String for toString.");
    }
}
