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

import com.redfin.validity.DefaultValidityFailedValidationExecutor;
import com.redfin.validity.FailedValidationExecutor;
import com.redfin.validity.verifiers.AbstractVerifiableObjectContract;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

final class VerifiableShortArrayTest implements AbstractVerifiableObjectContract<IllegalArgumentException, short[], VerifiableShortArray<IllegalArgumentException>> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test values & contract implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public VerifiableShortArray<IllegalArgumentException> getNotValueTypeInstance() {
        return getVerifiableInstance(getSubject());
    }

    @Override
    public FailedValidationExecutor<IllegalArgumentException> getFailedValidationExecutor() {
        return new DefaultValidityFailedValidationExecutor<>(IllegalArgumentException::new);
    }

    @Override
    public Class<IllegalArgumentException> getThrowableClass() {
        return IllegalArgumentException.class;
    }

    @Override
    public short[] getSubject() {
        return new short[]{0};
    }

    @Override
    public short[] getEqualSubject() {
        return new short[]{0};
    }

    @Override
    public short[] getNonEqualSubject() {
        return new short[]{1};
    }

    @Override
    public VerifiableShortArray<IllegalArgumentException> getVerifiableInstance(FailedValidationExecutor<IllegalArgumentException> failedValidationExecutor, short[] subject, String message) {
        return new VerifiableShortArray<>(failedValidationExecutor, subject, message);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    @Test
    void testIsEmptyReturnsSubjectForEmptySubject() {
        short[] subject = {};
        VerifiableShortArray<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.isEmpty(),
                              "VerifiableArray should return the given subject for isEmpty with empty subject.");
    }

    @Test
    void testIsEmptyThrowsForNonEmptySubject() {
        short[] subject = {0};
        VerifiableShortArray<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isEmpty);
    }

    @Test
    void testIsEmptyThrowsForNullSubject() {
        VerifiableShortArray<IllegalArgumentException> verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isEmpty);
    }

    @Test
    void testIsNotEmptyReturnsSubjectForNonEmptySubject() {
        short[] subject = {0};
        VerifiableShortArray<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.isNotEmpty(),
                              "VerifiableArray should return the given subject for isNotEmpty with non-empty subject.");
    }

    @Test
    void testIsNotEmptyThrowsForEmptySubject() {
        short[] subject = {};
        VerifiableShortArray<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isNotEmpty);
    }

    @Test
    void testIsNotEmptyThrowsForNullSubject() {
        VerifiableShortArray<IllegalArgumentException> verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isNotEmpty);
    }

    @Test
    void testHasLengthOfReturnsSubjectForSubjectWithMatchingLength() {
        short[] subject = {0};
        VerifiableShortArray<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.hasLengthOf(1),
                              "VerifiableArray should return the given subject for hasLengthOf with matching subject.");
    }

    @Test
    void testHasLengthOfThrowsForSubjectWithNonMatchingLength() {
        VerifiableShortArray<IllegalArgumentException> verifiable = getVerifiableInstance(new short[]{0, 1});
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasLengthOf(1));
    }

    @Test
    void testHasLengthOfThrowsForNullSubject() {
        VerifiableShortArray<IllegalArgumentException> verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasLengthOf(1));
    }

    @Test
    void testHasLengthOfAtLeastReturnsSubjectForSubjectWithMatchingLength() {
        short[] subject = {0};
        VerifiableShortArray<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.hasLengthOfAtLeast(1),
                              "VerifiableArray should return the given subject for hasLengthOfAtLeast with matching length subject.");
    }

    @Test
    void testHasLengthOfAtLeastReturnsSubjectForSubjectWithGreaterLength() {
        short[] subject = {0, 1};
        VerifiableShortArray<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.hasLengthOfAtLeast(1),
                              "VerifiableArray should return the given subject for hasLengthOfAtLeast with greater length subject.");
    }

    @Test
    void testHasLengthOfAtLeastThrowsForSubjectWithNonMatchingLength() {
        VerifiableShortArray<IllegalArgumentException> verifiable = getVerifiableInstance(new short[]{});
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasLengthOfAtLeast(1));
    }

    @Test
    void testHasLengthOfAtLeastThrowsForNullSubject() {
        VerifiableShortArray<IllegalArgumentException> verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasLengthOfAtLeast(1));
    }

    @Test
    void testHasLengthOfAtMostReturnsSubjectForSubjectWithMatchingLength() {
        short[] subject = {0};
        VerifiableShortArray<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.hasLengthOfAtMost(1),
                              "VerifiableArray should return the given subject for hasLengthOfAtMost with matching length subject.");
    }

    @Test
    void testHasLengthOfAtMostReturnsSubjectForSubjectWithGreaterLength() {
        short[] subject = {0};
        VerifiableShortArray<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.hasLengthOfAtMost(2),
                              "VerifiableArray should return the given subject for hasLengthOfAtMost with lesser length subject.");
    }

    @Test
    void testHasLengthOfAtMostThrowsForSubjectWithNonMatchingLength() {
        VerifiableShortArray<IllegalArgumentException> verifiable = getVerifiableInstance(new short[]{0});
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasLengthOfAtMost(0));
    }

    @Test
    void testHasLengthOfAtMostThrowsForNullSubject() {
        VerifiableShortArray<IllegalArgumentException> verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasLengthOfAtMost(1));
    }

    @Test
    void testContainsReturnsSubjectForSubjectContaining() {
        short[] subject = {0};
        VerifiableShortArray<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.contains((short) 0),
                              "VerifiableChar should return subject for contains with matching element.");
    }

    @Test
    void testContainsThrowsForSubjectNotContaining() {
        VerifiableShortArray<IllegalArgumentException> verifiable = getVerifiableInstance(new short[]{0});
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.contains((short) 1));
    }

    @Test
    void testContainsThrowsForNullSubject() {
        VerifiableShortArray<IllegalArgumentException> verifiable = getVerifiableInstance(new short[]{0});
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.contains((short) 1));
    }

    @Test
    void testDoesNotContainReturnsSubjectForSubjectNotContaining() {
        short[] subject = {0};
        VerifiableShortArray<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.doesNotContain((short) 1),
                              "VerifiableChar should return subject for doesNotContain with non-matching element.");
    }

    @Test
    void testDoesNotContainThrowsForSubjectContaining() {
        VerifiableShortArray<IllegalArgumentException> verifiable = getVerifiableInstance(new short[]{0});
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.doesNotContain((short) 0));
    }

    @Test
    void testDoesNotContainThrowsForNullSubject() {
        VerifiableShortArray<IllegalArgumentException> verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.doesNotContain((short) 0));
    }
}
