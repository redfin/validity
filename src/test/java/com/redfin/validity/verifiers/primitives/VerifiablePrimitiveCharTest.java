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

final class VerifiablePrimitiveCharTest
 implements AbstractVerifiablePrimitiveContract<IllegalArgumentException, VerifiablePrimitiveChar<IllegalArgumentException>> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test values & contract implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public VerifiablePrimitiveChar<IllegalArgumentException> getNotValueTypeInstance() {
        return getInstance('a');
    }

    @Override
    public FailedValidationExecutor<IllegalArgumentException> getAbstractVerifiablePrimitiveFailedValidationExecutor() {
        return new DefaultValidityFailedValidationExecutor<>(IllegalArgumentException::new);
    }

    @Override
    public VerifiablePrimitiveChar<IllegalArgumentException> getAbstractVerifiablePrimitive(FailedValidationExecutor<IllegalArgumentException> failedValidationExecutor, String message) {
        return new VerifiablePrimitiveChar<>(failedValidationExecutor, 'a', message);
    }

    private VerifiablePrimitiveChar<IllegalArgumentException> getInstance(char subject) {
        return new VerifiablePrimitiveChar<>(getAbstractVerifiablePrimitiveFailedValidationExecutor(), subject, "message");
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    void testIsEqualToReturnsSubjectForEqualSubject() {
        char subject = 'a';
        VerifiablePrimitiveChar<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isEqualTo(subject),
                              "VerifiablePrimitiveChar should return the subject for equal isEqualTo.");
    }

    @Test
    void testIsEqualToThrowsForNonEqualSubject() {
        char subject = 'a';
        VerifiablePrimitiveChar<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isEqualTo('b'));
    }

    @Test
    void testIsNotEqualToReturnsSubjectForNonEqualSubject() {
        char subject = 'a';
        VerifiablePrimitiveChar<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isNotEqualTo('b'),
                              "VerifiablePrimitiveChar should return the subject for non-equal isNotEqualTo.");
    }

    @Test
    void testIsNotEqualToThrowsForEqualSubject() {
        char subject = 'a';
        VerifiablePrimitiveChar<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isNotEqualTo(subject));
    }

    @Test
    void testIsGreaterThanReturnsSubjectForMatchingSubject() {
        char subject = 'b';
        VerifiablePrimitiveChar<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isGreaterThan('a'),
                              "VerifiablePrimitiveChar should return the subject for greater isGreaterThan.");
    }

    @Test
    void testIsGreaterThanThrowsForNonMatchingSubject() {
        char subject = 'a';
        VerifiablePrimitiveChar<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isGreaterThan('b'));
    }

    @Test
    void testIsGreaterThanOrEqualToReturnsSubjectForEqual() {
        char subject = 'b';
        VerifiablePrimitiveChar<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isGreaterThanOrEqualTo(subject),
                              "VerifiablePrimitiveChar should return the subject for equal isGreaterThanOrEqualTo.");
    }

    @Test
    void testIsGreaterThanOrEqualToReturnsSubjectForGreaterThan() {
        char subject = 'b';
        VerifiablePrimitiveChar<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isGreaterThanOrEqualTo('b'),
                              "VerifiablePrimitiveChar should return the subject for greater isGreaterThanOrEqualTo.");
    }

    @Test
    void testIsGreaterThanOrEqualToThrowsForNonMatchingSubject() {
        char subject = 'a';
        VerifiablePrimitiveChar<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isGreaterThanOrEqualTo('b'));
    }

    @Test
    void testIsAtLeastReturnsSubjectForEqual() {
        char subject = 'a';
        VerifiablePrimitiveChar<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isAtLeast('a'),
                              "VerifiablePrimitiveChar should return the subject for equal isAtLeast.");
    }

    @Test
    void testIsAtLeastReturnsSubjectForGreaterThan() {
        char subject = 'b';
        VerifiablePrimitiveChar<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isAtLeast('a'),
                              "VerifiablePrimitiveChar should return the subject for less isAtLeast.");
    }

    @Test
    void testIsAtLeastThrowsForNonMatchingSubject() {
        char subject = 'a';
        VerifiablePrimitiveChar<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isAtLeast('b'));
    }

    @Test
    void testIsLessThanReturnsSubjectForMatchingSubject() {
        char subject = 'a';
        VerifiablePrimitiveChar<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isLessThan('b'),
                              "VerifiablePrimitiveChar should return the subject for greater isLessThan.");
    }

    @Test
    void testIsLessThanThrowsForNonMatchingSubject() {
        char subject = 'b';
        VerifiablePrimitiveChar<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isLessThan('a'));
    }

    @Test
    void testIsLessThanOrEqualToReturnsSubjectForEqual() {
        char subject = 'a';
        VerifiablePrimitiveChar<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isLessThanOrEqualTo('a'),
                              "VerifiablePrimitiveChar should return the subject for greater isLessThanOrEqualTo.");
    }

    @Test
    void testIsLessThanOrEqualToReturnsSubjectForLessThan() {
        char subject = 'a';
        VerifiablePrimitiveChar<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isLessThanOrEqualTo('b'),
                              "VerifiablePrimitiveChar should return the subject for greater isLessThanOrEqualTo.");
    }

    @Test
    void testIsLessThanOrEqualToThrowsForNonMatchingSubject() {
        char subject = 'b';
        VerifiablePrimitiveChar<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isLessThanOrEqualTo('a'));
    }

    @Test
    void testIsAtMostReturnsSubjectForEqual() {
        char subject = 'a';
        VerifiablePrimitiveChar<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isAtMost('a'),
                              "VerifiablePrimitiveChar should return the subject for equal isAtMost.");
    }

    @Test
    void testIsAtMostReturnsSubjectForLessThan() {
        char subject = 'a';
        VerifiablePrimitiveChar<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isAtMost('b'),
                              "VerifiablePrimitiveChar should return the subject for greater isAtMost.");
    }

    @Test
    void testIsAtMostThrowsForNonMatchingSubject() {
        char subject = 'b';
        VerifiablePrimitiveChar<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isAtMost('a'));
    }

    @Test
    void testIsUpperCaseReturnsSubjectForUpperCase() {
        char subject = 'A';
        VerifiablePrimitiveChar<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isUpperCase(),
                              "VerifiablePrimitiveChar should return the subject for isUpperCase.");
    }

    @Test
    void testIsUpperCaseThrowsForLowerCaseSubject() {
        char subject = 'b';
        VerifiablePrimitiveChar<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isUpperCase);
    }

    @Test
    void testIsLowerCaseReturnsSubjectForLowerCase() {
        char subject = 'a';
        VerifiablePrimitiveChar<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isLowerCase(),
                              "VerifiablePrimitiveChar should return the subject for isLowerCase.");
    }

    @Test
    void testIsLowerCaseThrowsForUpperCaseSubject() {
        char subject = 'A';
        VerifiablePrimitiveChar<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isLowerCase);
    }

    @Test
    void testIsLetterOrDigitReturnsSubjectForLetter() {
        char subject = 'a';
        VerifiablePrimitiveChar<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isLetterOrDigit(),
                              "VerifiablePrimitiveChar should return the subject for letter isLetterOrDigit.");
    }

    @Test
    void testIsLetterOrDigitReturnsSubjectForDigit() {
        char subject = '2';
        VerifiablePrimitiveChar<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isLetterOrDigit(),
                              "VerifiablePrimitiveChar should return the subject for digit isLetterOrDigit.");
    }

    @Test
    void testIsLetterOrDigitThrowsForNeither() {
        char subject = '\n';
        VerifiablePrimitiveChar<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isLetterOrDigit);
    }

    @Test
    void testIsAlphabeticReturnsSubjectForAlphabetic() {
        char subject = 'a';
        VerifiablePrimitiveChar<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isAlphabetic(),
                              "VerifiablePrimitiveChar should return the subject for alphabetic isAlphabetic.");
    }

    @Test
    void testIsAlphabeticThrowsForNonAlphabetic() {
        char subject = '2';
        VerifiablePrimitiveChar<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isAlphabetic);
    }

    @Test
    void testIsDigitReturnsSubjectForDigit() {
        char subject = '2';
        VerifiablePrimitiveChar<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isDigit(),
                              "VerifiablePrimitiveChar should return the subject for digit isDigit.");
    }

    @Test
    void testIsDigitThrowsForNonDigit() {
        char subject = 'a';
        VerifiablePrimitiveChar<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isDigit);
    }

    @Test
    void testSatisfiesReturnsSubjectForMatchingSubject() {
        char subject = '2';
        VerifiablePrimitiveChar<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.satisfies(Character::isDigit),
                              "VerifiablePrimitiveChar should return the subject for matching satisfies.");
    }

    @Test
    void testSatisfiesThrowsForNonMatchingSubject() {
        char subject = 'a';
        VerifiablePrimitiveChar<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.satisfies(Character::isDigit));
    }

    @Test
    void testSatisfiesThrowsForNullPredicate() {
        char subject = 'a';
        VerifiablePrimitiveChar<IllegalArgumentException> verifiable = getInstance(subject);
        NullPointerException exception = Assertions.assertThrows(NullPointerException.class,
                                                                 () -> verifiable.satisfies(null));
        Assertions.assertEquals(ValidityUtils.nullArgumentMessage("expected"),
                                exception.getMessage(),
                                "VerifiablePrimitiveChar satisfies should throw expected exception for null predicate.");
    }

    @Test
    void testToStringReturnsExpectedString() {
        char subject = 'a';
        VerifiablePrimitiveChar<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertEquals(verifiable.getClass().getSimpleName() + ": <" + ValidityUtils.describe(subject) + ">",
                                verifiable.toString(),
                                "VerifiablePrimitiveBoolean should return expected String for toString.");
    }
}
