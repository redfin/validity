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

final class VerifiableLongArrayTest implements AbstractVerifiableObjectContract<IllegalArgumentException, long[], VerifiableLongArray<IllegalArgumentException>> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test values & contract implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public VerifiableLongArray<IllegalArgumentException> getNotValueTypeInstance() {
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
    public long[] getSubject() {
        return new long[]{0};
    }

    @Override
    public long[] getEqualSubject() {
        return new long[]{0};
    }

    @Override
    public long[] getNonEqualSubject() {
        return new long[]{1};
    }

    @Override
    public VerifiableLongArray<IllegalArgumentException> getAbstractVerifiableObjectInstance(FailedValidationExecutor<IllegalArgumentException> failedValidationExecutor, long[] subject, String message) {
        return new VerifiableLongArray<>(failedValidationExecutor, subject, message);
    }

    private VerifiableLongArray<IllegalArgumentException> getInstance(long[] subject) {
        return getAbstractVerifiableObjectInstance(getAbstractVerifiablePrimitiveFailedValidationExecutor(), subject, "message");
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    @Test
    void testIsEmptyReturnsSubjectForEmptySubject() {
        long[] subject = {};
        VerifiableLongArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isEmpty(),
                              "VerifiableArray should return the given subject for isEmpty with empty subject.");
    }

    @Test
    void testIsEmptyThrowsForNonEmptySubject() {
        long[] subject = {0};
        VerifiableLongArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isEmpty);
    }

    @Test
    void testIsEmptyThrowsForNullSubject() {
        VerifiableLongArray<IllegalArgumentException> verifiable = getInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isEmpty);
    }

    @Test
    void testIsNotEmptyReturnsSubjectForNonEmptySubject() {
        long[] subject = {0};
        VerifiableLongArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isNotEmpty(),
                              "VerifiableArray should return the given subject for isNotEmpty with non-empty subject.");
    }

    @Test
    void testIsNotEmptyThrowsForEmptySubject() {
        long[] subject = {};
        VerifiableLongArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isNotEmpty);
    }

    @Test
    void testIsNotEmptyThrowsForNullSubject() {
        VerifiableLongArray<IllegalArgumentException> verifiable = getInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isNotEmpty);
    }

    @Test
    void testHasLengthOfReturnsSubjectForSubjectWithMatchingLength() {
        long[] subject = {0};
        VerifiableLongArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.hasLengthOf(1),
                              "VerifiableArray should return the given subject for hasLengthOf with matching subject.");
    }

    @Test
    void testHasLengthOfThrowsForSubjectWithNonMatchingLength() {
        VerifiableLongArray<IllegalArgumentException> verifiable = getInstance(new long[]{0, 1});
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasLengthOf(1));
    }

    @Test
    void testHasLengthOfThrowsForNullSubject() {
        VerifiableLongArray<IllegalArgumentException> verifiable = getInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasLengthOf(1));
    }

    @Test
    void testHasLengthOfAtLeastReturnsSubjectForSubjectWithMatchingLength() {
        long[] subject = {0};
        VerifiableLongArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.hasLengthOfAtLeast(1),
                              "VerifiableArray should return the given subject for hasLengthOfAtLeast with matching length subject.");
    }

    @Test
    void testHasLengthOfAtLeastReturnsSubjectForSubjectWithGreaterLength() {
        long[] subject = {0, 1};
        VerifiableLongArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.hasLengthOfAtLeast(1),
                              "VerifiableArray should return the given subject for hasLengthOfAtLeast with greater length subject.");
    }

    @Test
    void testHasLengthOfAtLeastThrowsForSubjectWithNonMatchingLength() {
        VerifiableLongArray<IllegalArgumentException> verifiable = getInstance(new long[]{});
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasLengthOfAtLeast(1));
    }

    @Test
    void testHasLengthOfAtLeastThrowsForNullSubject() {
        VerifiableLongArray<IllegalArgumentException> verifiable = getInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasLengthOfAtLeast(1));
    }

    @Test
    void testHasLengthOfAtMostReturnsSubjectForSubjectWithMatchingLength() {
        long[] subject = {0};
        VerifiableLongArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.hasLengthOfAtMost(1),
                              "VerifiableArray should return the given subject for hasLengthOfAtMost with matching length subject.");
    }

    @Test
    void testHasLengthOfAtMostReturnsSubjectForSubjectWithGreaterLength() {
        long[] subject = {0};
        VerifiableLongArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.hasLengthOfAtMost(2),
                              "VerifiableArray should return the given subject for hasLengthOfAtMost with lesser length subject.");
    }

    @Test
    void testHasLengthOfAtMostThrowsForSubjectWithNonMatchingLength() {
        VerifiableLongArray<IllegalArgumentException> verifiable = getInstance(new long[]{0});
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasLengthOfAtMost(0));
    }

    @Test
    void testHasLengthOfAtMostThrowsForNullSubject() {
        VerifiableLongArray<IllegalArgumentException> verifiable = getInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasLengthOfAtMost(1));
    }

    @Test
    void testContainsReturnsSubjectForSubjectContaining() {
        long[] subject = {0};
        VerifiableLongArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.contains(0),
                              "VerifiableChar should return subject for contains with matching element.");
    }

    @Test
    void testContainsThrowsForSubjectNotContaining() {
        VerifiableLongArray<IllegalArgumentException> verifiable = getInstance(new long[]{0});
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.contains(1));
    }

    @Test
    void testContainsThrowsForNullSubject() {
        VerifiableLongArray<IllegalArgumentException> verifiable = getInstance(new long[]{0});
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.contains(1));
    }

    @Test
    void testDoesNotContainReturnsSubjectForSubjectNotContaining() {
        long[] subject = {0};
        VerifiableLongArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.doesNotContain(1),
                              "VerifiableChar should return subject for doesNotContain with non-matching element.");
    }

    @Test
    void testDoesNotContainThrowsForSubjectContaining() {
        VerifiableLongArray<IllegalArgumentException> verifiable = getInstance(new long[]{0});
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.doesNotContain(0));
    }

    @Test
    void testDoesNotContainThrowsForNullSubject() {
        VerifiableLongArray<IllegalArgumentException> verifiable = getInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.doesNotContain(0));
    }
}