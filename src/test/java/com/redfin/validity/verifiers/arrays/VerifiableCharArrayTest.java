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

package com.redfin.validity.verifiers.arrays;

import com.redfin.validity.FailedValidationExecutor;
import com.redfin.validity.FailedValidationExecutors;
import com.redfin.validity.verifiers.AbstractVerifiableObjectContract;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

final class VerifiableCharArrayTest implements AbstractVerifiableObjectContract<IllegalArgumentException, char[], VerifiableCharArray<IllegalArgumentException>> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test values & contract implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public VerifiableCharArray<IllegalArgumentException> getNotValueTypeInstance() {
        return getInstance(getSubject());
    }

    @Override
    public FailedValidationExecutor<IllegalArgumentException> getAbstractVerifiablePrimitiveFailedValidationExecutor() {
        return FailedValidationExecutors.getDefaultFailureExecutor();
    }

    @Override
    public Class<IllegalArgumentException> getThrowableClass() {
        return IllegalArgumentException.class;
    }

    @Override
    public char[] getSubject() {
        return new char[]{0};
    }

    @Override
    public char[] getEqualSubject() {
        return new char[]{0};
    }

    @Override
    public char[] getNonEqualSubject() {
        return new char[]{1};
    }

    @Override
    public VerifiableCharArray<IllegalArgumentException> getAbstractVerifiableObjectInstance(FailedValidationExecutor<IllegalArgumentException> failedValidationExecutor, char[] subject, String message) {
        return new VerifiableCharArray<>(failedValidationExecutor, subject, message);
    }

    private VerifiableCharArray<IllegalArgumentException> getInstance(char[] subject) {
        return getAbstractVerifiableObjectInstance(getAbstractVerifiablePrimitiveFailedValidationExecutor(), subject, "message");
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    @Test
    void testIsEmptyReturnsSubjectForEmptySubject() {
        char[] subject = {};
        VerifiableCharArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isEmpty(),
                              "VerifiableArray should return the given subject for isEmpty with empty subject.");
    }

    @Test
    void testIsEmptyThrowsForNonEmptySubject() {
        char[] subject = {0};
        VerifiableCharArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isEmpty);
    }

    @Test
    void testIsEmptyThrowsForNullSubject() {
        VerifiableCharArray<IllegalArgumentException> verifiable = getInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isEmpty);
    }

    @Test
    void testIsNotEmptyReturnsSubjectForNonEmptySubject() {
        char[] subject = {0};
        VerifiableCharArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isNotEmpty(),
                              "VerifiableArray should return the given subject for isNotEmpty with non-empty subject.");
    }

    @Test
    void testIsNotEmptyThrowsForEmptySubject() {
        char[] subject = {};
        VerifiableCharArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isNotEmpty);
    }

    @Test
    void testIsNotEmptyThrowsForNullSubject() {
        VerifiableCharArray<IllegalArgumentException> verifiable = getInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isNotEmpty);
    }

    @Test
    void testHasLengthOfReturnsSubjectForSubjectWithMatchingLength() {
        char[] subject = {0};
        VerifiableCharArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.hasLengthOf(1),
                              "VerifiableArray should return the given subject for hasLengthOf with matching subject.");
    }

    @Test
    void testHasLengthOfThrowsForSubjectWithNonMatchingLength() {
        VerifiableCharArray<IllegalArgumentException> verifiable = getInstance(new char[]{0, 1});
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasLengthOf(1));
    }

    @Test
    void testHasLengthOfThrowsForNullSubject() {
        VerifiableCharArray<IllegalArgumentException> verifiable = getInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasLengthOf(1));
    }

    @Test
    void testHasLengthOfAtLeastReturnsSubjectForSubjectWithMatchingLength() {
        char[] subject = {0};
        VerifiableCharArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.hasLengthOfAtLeast(1),
                              "VerifiableArray should return the given subject for hasLengthOfAtLeast with matching length subject.");
    }

    @Test
    void testHasLengthOfAtLeastReturnsSubjectForSubjectWithGreaterLength() {
        char[] subject = {0, 1};
        VerifiableCharArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.hasLengthOfAtLeast(1),
                              "VerifiableArray should return the given subject for hasLengthOfAtLeast with greater length subject.");
    }

    @Test
    void testHasLengthOfAtLeastThrowsForSubjectWithNonMatchingLength() {
        VerifiableCharArray<IllegalArgumentException> verifiable = getInstance(new char[]{});
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasLengthOfAtLeast(1));
    }

    @Test
    void testHasLengthOfAtLeastThrowsForNullSubject() {
        VerifiableCharArray<IllegalArgumentException> verifiable = getInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasLengthOfAtLeast(1));
    }

    @Test
    void testHasLengthOfAtMostReturnsSubjectForSubjectWithMatchingLength() {
        char[] subject = {0};
        VerifiableCharArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.hasLengthOfAtMost(1),
                              "VerifiableArray should return the given subject for hasLengthOfAtMost with matching length subject.");
    }

    @Test
    void testHasLengthOfAtMostReturnsSubjectForSubjectWithGreaterLength() {
        char[] subject = {0};
        VerifiableCharArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.hasLengthOfAtMost(2),
                              "VerifiableArray should return the given subject for hasLengthOfAtMost with lesser length subject.");
    }

    @Test
    void testHasLengthOfAtMostThrowsForSubjectWithNonMatchingLength() {
        VerifiableCharArray<IllegalArgumentException> verifiable = getInstance(new char[]{0});
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasLengthOfAtMost(0));
    }

    @Test
    void testHasLengthOfAtMostThrowsForNullSubject() {
        VerifiableCharArray<IllegalArgumentException> verifiable = getInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasLengthOfAtMost(1));
    }

    @Test
    void testContainsReturnsSubjectForSubjectContaining() {
        char[] subject = {0};
        VerifiableCharArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.contains((char) 0),
                              "VerifiableChar should return subject for contains with matching element.");
    }

    @Test
    void testContainsThrowsForSubjectNotContaining() {
        VerifiableCharArray<IllegalArgumentException> verifiable = getInstance(new char[]{0});
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.contains((char) 1));
    }

    @Test
    void testContainsThrowsForNullSubject() {
        VerifiableCharArray<IllegalArgumentException> verifiable = getInstance(new char[]{0});
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.contains((char) 1));
    }

    @Test
    void testDoesNotContainReturnsSubjectForSubjectNotContaining() {
        char[] subject = {0};
        VerifiableCharArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.doesNotContain((char) 1),
                              "VerifiableChar should return subject for doesNotContain with non-matching element.");
    }

    @Test
    void testDoesNotContainThrowsForSubjectContaining() {
        VerifiableCharArray<IllegalArgumentException> verifiable = getInstance(new char[]{0});
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.doesNotContain((char) 0));
    }

    @Test
    void testDoesNotContainThrowsForNullSubject() {
        VerifiableCharArray<IllegalArgumentException> verifiable = getInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.doesNotContain((char) 0));
    }
}
