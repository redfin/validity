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

import java.util.function.Supplier;

final class VerifiablePrimitiveFloatTest
 implements AbstractVerifiablePrimitiveContract<IllegalArgumentException, VerifiablePrimitiveFloat<IllegalArgumentException>> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test values & contract implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public VerifiablePrimitiveFloat<IllegalArgumentException> getNotValueTypeInstance() {
        return getInstance(0);
    }

    @Override
    public FailedValidationExecutor<IllegalArgumentException> getAbstractVerifiablePrimitiveFailedValidationExecutor() {
        return new DefaultValidityFailedValidationExecutor<>(IllegalArgumentException::new);
    }

    @Override
    public VerifiablePrimitiveFloat<IllegalArgumentException> getAbstractVerifiablePrimitive(FailedValidationExecutor<IllegalArgumentException> failedValidationExecutor,
                                                                                             Supplier<String> messageSupplier) {
        return new VerifiablePrimitiveFloat<>(failedValidationExecutor, 0, messageSupplier);
    }

    private VerifiablePrimitiveFloat<IllegalArgumentException> getInstance(float subject) {
        return new VerifiablePrimitiveFloat<>(getAbstractVerifiablePrimitiveFailedValidationExecutor(), subject, () -> "message");
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    void testIsEqualToReturnsSubjectForEqual() {
        float subject = 0;
        VerifiablePrimitiveFloat<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isEqualTo(0),
                              "VerifiablePrimitiveFloat should return the subject for isEqualTo with equal.");
    }

    @Test
    void testIsEqualToThrowsForNotEqual() {
        float subject = 0;
        VerifiablePrimitiveFloat<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isEqualTo(1));
    }

    @Test
    void testIsNotEqualToReturnsSubjectForNotEqual() {
        float subject = 0;
        VerifiablePrimitiveFloat<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isNotEqualTo(1),
                              "VerifiablePrimitiveFloat should return the subject for isNotEqualTo with non-equal.");
    }

    @Test
    void testIsNotEqualThrowsForEqual() {
        float subject = 0;
        VerifiablePrimitiveFloat<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isNotEqualTo(subject));
    }

    @Test
    void testIsZeroReturnsSubjectForZero() {
        float subject = 0;
        VerifiablePrimitiveFloat<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isZero(),
                              "VerifiablePrimitiveFloat should return the subject for isZero with zero.");
    }

    @Test
    void testIsZeroThrowsForNonZero() {
        float subject = 1;
        VerifiablePrimitiveFloat<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isZero);
    }

    @Test
    void testIsNotZeroReturnsSubjectForNonZero() {
        float subject = 1;
        VerifiablePrimitiveFloat<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isNotZero(),
                              "VerifiablePrimitiveFloat should return the subject for isNotZero with non-zero.");
    }

    @Test
    void testIsNotZeroThrowsForZero() {
        float subject = 0;
        VerifiablePrimitiveFloat<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isNotZero);
    }

    @Test
    void testIsStrictlyPositiveReturnsSubjectForPositive() {
        float subject = 1;
        VerifiablePrimitiveFloat<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isStrictlyPositive(),
                              "VerifiablePrimitiveFloat should return the subject for isStrictlyPositive with positive.");
    }

    @Test
    void testIsStrictlyPositiveThrowsForZero() {
        float subject = 0;
        VerifiablePrimitiveFloat<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isStrictlyPositive);
    }

    @Test
    void testIsStrictlyPositiveThrowsForNegative() {
        float subject = -1;
        VerifiablePrimitiveFloat<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isStrictlyPositive);
    }

    @Test
    void testIsStrictlyNegativeReturnsSubjectForNegative() {
        float subject = -1;
        VerifiablePrimitiveFloat<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isStrictlyNegative(),
                              "VerifiablePrimitiveFloat should return the subject for isStrictlyNegative with positive.");
    }

    @Test
    void testIsStrictlyNegativeThrowsForZero() {
        float subject = 0;
        VerifiablePrimitiveFloat<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isStrictlyNegative);
    }

    @Test
    void testIsStrictlyNegativeThrowsForPositive() {
        float subject = 1;
        VerifiablePrimitiveFloat<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isStrictlyNegative);
    }

    @Test
    void testIsGreaterThanReturnsSubjectForMatchingSubject() {
        float subject = 1;
        VerifiablePrimitiveFloat<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isGreaterThan(0),
                              "VerifiablePrimitiveFloat should return the subject for greater isGreaterThan.");
    }

    @Test
    void testIsGreaterThanThrowsForNonMatchingSubject() {
        float subject = 0;
        VerifiablePrimitiveFloat<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isGreaterThan(subject));
    }

    @Test
    void testIsGreaterThanOrEqualToReturnsSubjectForEqual() {
        float subject = 1;
        VerifiablePrimitiveFloat<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isGreaterThanOrEqualTo(subject),
                              "VerifiablePrimitiveFloat should return the subject for equal isGreaterThanOrEqualTo.");
    }

    @Test
    void testIsGreaterThanOrEqualToReturnsSubjectForGreaterThan() {
        float subject = 1;
        VerifiablePrimitiveFloat<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isGreaterThanOrEqualTo(0),
                              "VerifiablePrimitiveFloat should return the subject for greater isGreaterThanOrEqualTo.");
    }

    @Test
    void testIsGreaterThanOrEqualToThrowsForNonMatchingSubject() {
        float subject = 1;
        VerifiablePrimitiveFloat<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isGreaterThanOrEqualTo(2));
    }

    @Test
    void testIsAtLeastReturnsSubjectForEqual() {
        float subject = 1;
        VerifiablePrimitiveFloat<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isAtLeast(1),
                              "VerifiablePrimitiveFloat should return the subject for equal isAtLeast.");
    }

    @Test
    void testIsAtLeastReturnsSubjectForGreaterThan() {
        float subject = 1;
        VerifiablePrimitiveFloat<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isAtLeast(0),
                              "VerifiablePrimitiveFloat should return the subject for less isAtLeast.");
    }

    @Test
    void testIsAtLeastThrowsForNonMatchingSubject() {
        float subject = 1;
        VerifiablePrimitiveFloat<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isAtLeast(2));
    }

    @Test
    void testIsLessThanReturnsSubjectForMatchingSubject() {
        float subject = 1;
        VerifiablePrimitiveFloat<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isLessThan(2),
                              "VerifiablePrimitiveFloat should return the subject for greater isLessThan.");
    }

    @Test
    void testIsLessThanThrowsForNonMatchingSubject() {
        float subject = 2;
        VerifiablePrimitiveFloat<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isLessThan(1));
    }

    @Test
    void testIsLessThanOrEqualToReturnsSubjectForEqual() {
        float subject = 1;
        VerifiablePrimitiveFloat<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isLessThanOrEqualTo(1),
                              "VerifiablePrimitiveFloat should return the subject for greater isLessThanOrEqualTo.");
    }

    @Test
    void testIsLessThanOrEqualToReturnsSubjectForLessThan() {
        float subject = 1;
        VerifiablePrimitiveFloat<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isLessThanOrEqualTo(2),
                              "VerifiablePrimitiveFloat should return the subject for greater isLessThanOrEqualTo.");
    }

    @Test
    void testIsLessThanOrEqualToThrowsForNonMatchingSubject() {
        float subject = 2;
        VerifiablePrimitiveFloat<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isLessThanOrEqualTo(1));
    }

    @Test
    void testIsAtMostReturnsSubjectForEqual() {
        float subject = 1;
        VerifiablePrimitiveFloat<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isAtMost(1),
                              "VerifiablePrimitiveFloat should return the subject for equal isAtMost.");
    }

    @Test
    void testIsAtMostReturnsSubjectForLessThan() {
        float subject = 0;
        VerifiablePrimitiveFloat<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isAtMost(1),
                              "VerifiablePrimitiveFloat should return the subject for greater isAtMost.");
    }

    @Test
    void testIsAtMostThrowsForNonMatchingSubject() {
        float subject = 1;
        VerifiablePrimitiveFloat<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isAtMost(0));
    }

    @Test
    void testSatisfiesReturnsSubjectForMatchingSubject() {
        float subject = 0;
        VerifiablePrimitiveFloat<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.satisfies(t -> t == 0),
                              "VerifiablePrimitiveFloat should return the subject for matching satisfies.");
    }

    @Test
    void testSatisfiesThrowsForNonMatchingSubject() {
        float subject = 0;
        VerifiablePrimitiveFloat<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.satisfies(t -> t != 0));
    }

    @Test
    void testSatisfiesThrowsForNullPredicate() {
        float subject = 0;
        VerifiablePrimitiveFloat<IllegalArgumentException> verifiable = getInstance(subject);
        NullPointerException exception = Assertions.assertThrows(NullPointerException.class,
                                                                 () -> verifiable.satisfies(null));
        Assertions.assertEquals(ValidityUtils.nullArgumentMessage("expected"),
                                exception.getMessage(),
                                "VerifiablePrimitiveFloat satisfies should throw expected exception for null predicate.");
    }

    @Test
    void testToStringReturnsExpectedString() {
        float subject = 0;
        VerifiablePrimitiveFloat<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertEquals(verifiable.getClass().getSimpleName() + ": <" + ValidityUtils.describe(subject) + ">",
                                verifiable.toString(),
                                "VerifiablePrimitiveBoolean should return expected String for toString.");
    }
}
