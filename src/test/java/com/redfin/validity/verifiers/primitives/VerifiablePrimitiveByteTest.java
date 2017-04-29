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

import com.redfin.validity.DefaultValidityFailedValidationExecutor;
import com.redfin.validity.FailedValidationExecutor;
import com.redfin.validity.ValidityUtils;
import com.redfin.validity.verifiers.AbstractVerifiablePrimitiveContract;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

final class VerifiablePrimitiveByteTest implements AbstractVerifiablePrimitiveContract<IllegalArgumentException, VerifiablePrimitiveByte<IllegalArgumentException>> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test values & contract implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public VerifiablePrimitiveByte<IllegalArgumentException> getNotValueTypeInstance() {
        return getInstance((byte) 0);
    }

    @Override
    public FailedValidationExecutor<IllegalArgumentException> getAbstractVerifiablePrimitiveFailedValidationExecutor() {
        return new DefaultValidityFailedValidationExecutor<>(IllegalArgumentException::new);
    }

    @Override
    public VerifiablePrimitiveByte<IllegalArgumentException> getAbstractVerifiablePrimitive(FailedValidationExecutor<IllegalArgumentException> failedValidationExecutor, String message) {
        return new VerifiablePrimitiveByte<>(failedValidationExecutor, (byte) 0, message);
    }

    private VerifiablePrimitiveByte<IllegalArgumentException> getInstance(byte subject) {
        return new VerifiablePrimitiveByte<>(getAbstractVerifiablePrimitiveFailedValidationExecutor(), subject, "message");
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    void testIsEqualToReturnsSubjectForEqual() {
        byte subject = 0;
        VerifiablePrimitiveByte<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isEqualTo((byte) 0),
                              "VerifiablePrimitiveByte should return the subject for isEqualTo with equal.");
    }

    @Test
    void testIsEqualToThrowsForNotEqual() {
        byte subject = 0;
        VerifiablePrimitiveByte<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isEqualTo((byte) 1));
    }

    @Test
    void testIsNotEqualToReturnsSubjectForNotEqual() {
        byte subject = 0;
        VerifiablePrimitiveByte<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isNotEqualTo((byte) 1),
                              "VerifiablePrimitiveByte should return the subject for isNotEqualTo with non-equal.");
    }

    @Test
    void testIsNotEqualThrowsForEqual() {
        byte subject = 0;
        VerifiablePrimitiveByte<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isNotEqualTo(subject));
    }

    @Test
    void testIsZeroReturnsSubjectForZero() {
        byte subject = 0;
        VerifiablePrimitiveByte<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isZero(),
                              "VerifiablePrimitiveByte should return the subject for isZero with zero.");
    }

    @Test
    void testIsZeroThrowsForNonZero() {
        byte subject = 1;
        VerifiablePrimitiveByte<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isZero);
    }

    @Test
    void testIsNotZeroReturnsSubjectForNonZero() {
        byte subject = 1;
        VerifiablePrimitiveByte<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isNotZero(),
                              "VerifiablePrimitiveByte should return the subject for isNotZero with non-zero.");
    }

    @Test
    void testIsNotZeroThrowsForZero() {
        byte subject = 0;
        VerifiablePrimitiveByte<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isNotZero);
    }

    @Test
    void testIsStrictlyPositiveReturnsSubjectForPositive() {
        byte subject = 1;
        VerifiablePrimitiveByte<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isStrictlyPositive(),
                              "VerifiablePrimitiveByte should return the subject for isStrictlyPositive with positive.");
    }

    @Test
    void testIsStrictlyPositiveThrowsForZero() {
        byte subject = 0;
        VerifiablePrimitiveByte<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isStrictlyPositive);
    }

    @Test
    void testIsStrictlyPositiveThrowsForNegative() {
        byte subject = -1;
        VerifiablePrimitiveByte<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isStrictlyPositive);
    }

    @Test
    void testIsStrictlyNegativeReturnsSubjectForNegative() {
        byte subject = -1;
        VerifiablePrimitiveByte<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isStrictlyNegative(),
                              "VerifiablePrimitiveByte should return the subject for isStrictlyNegative with positive.");
    }

    @Test
    void testIsStrictlyNegativeThrowsForZero() {
        byte subject = 0;
        VerifiablePrimitiveByte<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isStrictlyNegative);
    }

    @Test
    void testIsStrictlyNegativeThrowsForPositive() {
        byte subject = 1;
        VerifiablePrimitiveByte<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isStrictlyNegative);
    }

    @Test
    void testIsGreaterThanReturnsSubjectForMatchingSubject() {
        byte subject = 1;
        VerifiablePrimitiveByte<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isGreaterThan((byte) 0),
                              "VerifiablePrimitiveByte should return the subject for greater isGreaterThan.");
    }

    @Test
    void testIsGreaterThanThrowsForNonMatchingSubject() {
        byte subject = 0;
        VerifiablePrimitiveByte<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isGreaterThan(subject));
    }

    @Test
    void testIsGreaterThanOrEqualToReturnsSubjectForEqual() {
        byte subject = 1;
        VerifiablePrimitiveByte<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isGreaterThanOrEqualTo(subject),
                              "VerifiablePrimitiveByte should return the subject for equal isGreaterThanOrEqualTo.");
    }

    @Test
    void testIsGreaterThanOrEqualToReturnsSubjectForGreaterThan() {
        byte subject = 1;
        VerifiablePrimitiveByte<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isGreaterThanOrEqualTo((byte) 0),
                              "VerifiablePrimitiveByte should return the subject for greater isGreaterThanOrEqualTo.");
    }

    @Test
    void testIsGreaterThanOrEqualToThrowsForNonMatchingSubject() {
        byte subject = 1;
        VerifiablePrimitiveByte<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isGreaterThanOrEqualTo((byte) 2));
    }

    @Test
    void testIsAtLeastReturnsSubjectForEqual() {
        byte subject = 1;
        VerifiablePrimitiveByte<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isAtLeast((byte) 1),
                              "VerifiablePrimitiveByte should return the subject for equal isAtLeast.");
    }

    @Test
    void testIsAtLeastReturnsSubjectForGreaterThan() {
        byte subject = 1;
        VerifiablePrimitiveByte<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isAtLeast((byte) 0),
                              "VerifiablePrimitiveByte should return the subject for less isAtLeast.");
    }

    @Test
    void testIsAtLeastThrowsForNonMatchingSubject() {
        byte subject = 1;
        VerifiablePrimitiveByte<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isAtLeast((byte) 2));
    }

    @Test
    void testIsLessThanReturnsSubjectForMatchingSubject() {
        byte subject = 1;
        VerifiablePrimitiveByte<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isLessThan((byte) 2),
                              "VerifiablePrimitiveByte should return the subject for greater isLessThan.");
    }

    @Test
    void testIsLessThanThrowsForNonMatchingSubject() {
        byte subject = 2;
        VerifiablePrimitiveByte<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isLessThan((byte) 1));
    }

    @Test
    void testIsLessThanOrEqualToReturnsSubjectForEqual() {
        byte subject = 1;
        VerifiablePrimitiveByte<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isLessThanOrEqualTo((byte) 1),
                              "VerifiablePrimitiveByte should return the subject for greater isLessThanOrEqualTo.");
    }

    @Test
    void testIsLessThanOrEqualToReturnsSubjectForLessThan() {
        byte subject = 1;
        VerifiablePrimitiveByte<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isLessThanOrEqualTo((byte) 2),
                              "VerifiablePrimitiveByte should return the subject for greater isLessThanOrEqualTo.");
    }

    @Test
    void testIsLessThanOrEqualToThrowsForNonMatchingSubject() {
        byte subject = 2;
        VerifiablePrimitiveByte<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isLessThanOrEqualTo((byte) 1));
    }

    @Test
    void testIsAtMostReturnsSubjectForEqual() {
        byte subject = 1;
        VerifiablePrimitiveByte<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isAtMost((byte) 1),
                              "VerifiablePrimitiveByte should return the subject for equal isAtMost.");
    }

    @Test
    void testIsAtMostReturnsSubjectForLessThan() {
        byte subject = 0;
        VerifiablePrimitiveByte<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isAtMost((byte) 1),
                              "VerifiablePrimitiveByte should return the subject for greater isAtMost.");
    }

    @Test
    void testIsAtMostThrowsForNonMatchingSubject() {
        byte subject = 1;
        VerifiablePrimitiveByte<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isAtMost((byte) 0));
    }

    @Test
    void testSatisfiesReturnsSubjectForMatchingSubject() {
        byte subject = 0;
        VerifiablePrimitiveByte<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.satisfies(t -> t == 0),
                              "VerifiablePrimitiveByte should return the subject for matching satisfies.");
    }

    @Test
    void testSatisfiesThrowsForNonMatchingSubject() {
        byte subject = 0;
        VerifiablePrimitiveByte<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.satisfies(t -> t != 0));
    }

    @Test
    void testSatisfiesThrowsForNullPredicate() {
        byte subject = 0;
        VerifiablePrimitiveByte<IllegalArgumentException> verifiable = getInstance(subject);
        NullPointerException exception = Assertions.assertThrows(NullPointerException.class,
                                                                 () -> verifiable.satisfies(null));
        Assertions.assertEquals(ValidityUtils.nullArgumentMessage("expected"),
                                exception.getMessage(),
                                "VerifiablePrimitiveByte satisfies should throw expected exception for null predicate.");
    }

    @Test
    void testToStringReturnsExpectedString() {
        byte subject = 0;
        VerifiablePrimitiveByte<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertEquals(verifiable.getClass().getSimpleName() + ": <" + ValidityUtils.describe(subject) + ">",
                                verifiable.toString(),
                                "VerifiablePrimitiveBoolean should return expected String for toString.");
    }
}
