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

final class VerifiableFloatArrayTest implements AbstractVerifiableObjectContract<IllegalArgumentException, float[], VerifiableFloatArray<IllegalArgumentException>> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test values & contract implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public VerifiableFloatArray<IllegalArgumentException> getNotValueTypeInstance() {
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
    public float[] getSubject() {
        return new float[]{0};
    }

    @Override
    public float[] getEqualSubject() {
        return new float[]{0};
    }

    @Override
    public float[] getNonEqualSubject() {
        return new float[]{1};
    }

    @Override
    public VerifiableFloatArray<IllegalArgumentException> getAbstractVerifiableObjectInstance(FailedValidationExecutor<IllegalArgumentException> failedValidationExecutor, float[] subject, String message) {
        return new VerifiableFloatArray<>(failedValidationExecutor, subject, message);
    }

    private VerifiableFloatArray<IllegalArgumentException> getInstance(float[] subject) {
        return getAbstractVerifiableObjectInstance(getAbstractVerifiablePrimitiveFailedValidationExecutor(), subject, "message");
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    @Test
    void testIsEmptyReturnsSubjectForEmptySubject() {
        float[] subject = {};
        VerifiableFloatArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isEmpty(),
                              "VerifiableArray should return the given subject for isEmpty with empty subject.");
    }

    @Test
    void testIsEmptyThrowsForNonEmptySubject() {
        float[] subject = {0};
        VerifiableFloatArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isEmpty);
    }

    @Test
    void testIsEmptyThrowsForNullSubject() {
        VerifiableFloatArray<IllegalArgumentException> verifiable = getInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isEmpty);
    }

    @Test
    void testIsNotEmptyReturnsSubjectForNonEmptySubject() {
        float[] subject = {0};
        VerifiableFloatArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isNotEmpty(),
                              "VerifiableArray should return the given subject for isNotEmpty with non-empty subject.");
    }

    @Test
    void testIsNotEmptyThrowsForEmptySubject() {
        float[] subject = {};
        VerifiableFloatArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isNotEmpty);
    }

    @Test
    void testIsNotEmptyThrowsForNullSubject() {
        VerifiableFloatArray<IllegalArgumentException> verifiable = getInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isNotEmpty);
    }

    @Test
    void testHasLengthOfReturnsSubjectForSubjectWithMatchingLength() {
        float[] subject = {0};
        VerifiableFloatArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.hasLengthOf(1),
                              "VerifiableArray should return the given subject for hasLengthOf with matching subject.");
    }

    @Test
    void testHasLengthOfThrowsForSubjectWithNonMatchingLength() {
        VerifiableFloatArray<IllegalArgumentException> verifiable = getInstance(new float[]{0, 1});
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasLengthOf(1));
    }

    @Test
    void testHasLengthOfThrowsForNullSubject() {
        VerifiableFloatArray<IllegalArgumentException> verifiable = getInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasLengthOf(1));
    }

    @Test
    void testHasLengthOfAtLeastReturnsSubjectForSubjectWithMatchingLength() {
        float[] subject = {0};
        VerifiableFloatArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.hasLengthOfAtLeast(1),
                              "VerifiableArray should return the given subject for hasLengthOfAtLeast with matching length subject.");
    }

    @Test
    void testHasLengthOfAtLeastReturnsSubjectForSubjectWithGreaterLength() {
        float[] subject = {0, 1};
        VerifiableFloatArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.hasLengthOfAtLeast(1),
                              "VerifiableArray should return the given subject for hasLengthOfAtLeast with greater length subject.");
    }

    @Test
    void testHasLengthOfAtLeastThrowsForSubjectWithNonMatchingLength() {
        VerifiableFloatArray<IllegalArgumentException> verifiable = getInstance(new float[]{});
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasLengthOfAtLeast(1));
    }

    @Test
    void testHasLengthOfAtLeastThrowsForNullSubject() {
        VerifiableFloatArray<IllegalArgumentException> verifiable = getInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasLengthOfAtLeast(1));
    }

    @Test
    void testHasLengthOfAtMostReturnsSubjectForSubjectWithMatchingLength() {
        float[] subject = {0};
        VerifiableFloatArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.hasLengthOfAtMost(1),
                              "VerifiableArray should return the given subject for hasLengthOfAtMost with matching length subject.");
    }

    @Test
    void testHasLengthOfAtMostReturnsSubjectForSubjectWithGreaterLength() {
        float[] subject = {0};
        VerifiableFloatArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.hasLengthOfAtMost(2),
                              "VerifiableArray should return the given subject for hasLengthOfAtMost with lesser length subject.");
    }

    @Test
    void testHasLengthOfAtMostThrowsForSubjectWithNonMatchingLength() {
        VerifiableFloatArray<IllegalArgumentException> verifiable = getInstance(new float[]{0});
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasLengthOfAtMost(0));
    }

    @Test
    void testHasLengthOfAtMostThrowsForNullSubject() {
        VerifiableFloatArray<IllegalArgumentException> verifiable = getInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasLengthOfAtMost(1));
    }

    @Test
    void testContainsReturnsSubjectForSubjectContaining() {
        float[] subject = {0};
        VerifiableFloatArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.contains(0),
                              "VerifiableChar should return subject for contains with matching element.");
    }

    @Test
    void testContainsThrowsForSubjectNotContaining() {
        VerifiableFloatArray<IllegalArgumentException> verifiable = getInstance(new float[]{0});
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.contains(1));
    }

    @Test
    void testContainsThrowsForNullSubject() {
        VerifiableFloatArray<IllegalArgumentException> verifiable = getInstance(new float[]{0});
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.contains(1));
    }

    @Test
    void testDoesNotContainReturnsSubjectForSubjectNotContaining() {
        float[] subject = {0};
        VerifiableFloatArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.doesNotContain(1),
                              "VerifiableChar should return subject for doesNotContain with non-matching element.");
    }

    @Test
    void testDoesNotContainThrowsForSubjectContaining() {
        VerifiableFloatArray<IllegalArgumentException> verifiable = getInstance(new float[]{0});
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.doesNotContain(0));
    }

    @Test
    void testDoesNotContainThrowsForNullSubject() {
        VerifiableFloatArray<IllegalArgumentException> verifiable = getInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.doesNotContain(0));
    }
}
