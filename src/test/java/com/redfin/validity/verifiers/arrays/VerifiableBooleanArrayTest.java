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

final class VerifiableBooleanArrayTest implements AbstractVerifiableObjectContract<IllegalArgumentException, boolean[], VerifiableBooleanArray<IllegalArgumentException>> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test values & contract implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public VerifiableBooleanArray<IllegalArgumentException> getNotValueTypeInstance() {
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
    public boolean[] getSubject() {
        return new boolean[]{true};
    }

    @Override
    public boolean[] getEqualSubject() {
        return new boolean[]{true};
    }

    @Override
    public boolean[] getNonEqualSubject() {
        return new boolean[]{false};
    }

    @Override
    public VerifiableBooleanArray<IllegalArgumentException> getAbstractVerifiableObjectInstance(FailedValidationExecutor<IllegalArgumentException> failedValidationExecutor, boolean[] subject, String message) {
        return new VerifiableBooleanArray<>(failedValidationExecutor, subject, message);
    }

    private VerifiableBooleanArray<IllegalArgumentException> getInstance(boolean[] subject) {
        return getAbstractVerifiableObjectInstance(getAbstractVerifiablePrimitiveFailedValidationExecutor(), subject, "message");
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    @Test
    void testIsEmptyReturnsSubjectForEmptySubject() {
        boolean[] subject = {};
        VerifiableBooleanArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isEmpty(),
                              "VerifiableArray should return the given subject for isEmpty with empty subject.");
    }

    @Test
    void testIsEmptyThrowsForNonEmptySubject() {
        boolean[] subject = {true};
        VerifiableBooleanArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isEmpty);
    }

    @Test
    void testIsEmptyThrowsForNullSubject() {
        VerifiableBooleanArray<IllegalArgumentException> verifiable = getInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isEmpty);
    }

    @Test
    void testIsNotEmptyReturnsSubjectForNonEmptySubject() {
        boolean[] subject = {true};
        VerifiableBooleanArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isNotEmpty(),
                              "VerifiableArray should return the given subject for isNotEmpty with non-empty subject.");
    }

    @Test
    void testIsNotEmptyThrowsForEmptySubject() {
        boolean[] subject = {};
        VerifiableBooleanArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isNotEmpty);
    }

    @Test
    void testIsNotEmptyThrowsForNullSubject() {
        VerifiableBooleanArray<IllegalArgumentException> verifiable = getInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isNotEmpty);
    }

    @Test
    void testHasLengthOfReturnsSubjectForSubjectWithMatchingLength() {
        boolean[] subject = {true};
        VerifiableBooleanArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.hasLengthOf(1),
                              "VerifiableArray should return the given subject for hasLengthOf with matching subject.");
    }

    @Test
    void testHasLengthOfThrowsForSubjectWithNonMatchingLength() {
        VerifiableBooleanArray<IllegalArgumentException> verifiable = getInstance(new boolean[]{true, false});
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasLengthOf(1));
    }

    @Test
    void testHasLengthOfThrowsForNullSubject() {
        VerifiableBooleanArray<IllegalArgumentException> verifiable = getInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasLengthOf(1));
    }

    @Test
    void testHasLengthOfAtLeastReturnsSubjectForSubjectWithMatchingLength() {
        boolean[] subject = {true};
        VerifiableBooleanArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.hasLengthOfAtLeast(1),
                              "VerifiableArray should return the given subject for hasLengthOfAtLeast with matching length subject.");
    }

    @Test
    void testHasLengthOfAtLeastReturnsSubjectForSubjectWithGreaterLength() {
        boolean[] subject = {true, false};
        VerifiableBooleanArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.hasLengthOfAtLeast(1),
                              "VerifiableArray should return the given subject for hasLengthOfAtLeast with greater length subject.");
    }

    @Test
    void testHasLengthOfAtLeastThrowsForSubjectWithNonMatchingLength() {
        VerifiableBooleanArray<IllegalArgumentException> verifiable = getInstance(new boolean[]{});
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasLengthOfAtLeast(1));
    }

    @Test
    void testHasLengthOfAtLeastThrowsForNullSubject() {
        VerifiableBooleanArray<IllegalArgumentException> verifiable = getInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasLengthOfAtLeast(1));
    }

    @Test
    void testHasLengthOfAtMostReturnsSubjectForSubjectWithMatchingLength() {
        boolean[] subject = {true};
        VerifiableBooleanArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.hasLengthOfAtMost(1),
                              "VerifiableArray should return the given subject for hasLengthOfAtMost with matching length subject.");
    }

    @Test
    void testHasLengthOfAtMostReturnsSubjectForSubjectWithGreaterLength() {
        boolean[] subject = {true};
        VerifiableBooleanArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.hasLengthOfAtMost(2),
                              "VerifiableArray should return the given subject for hasLengthOfAtMost with lesser length subject.");
    }

    @Test
    void testHasLengthOfAtMostThrowsForSubjectWithNonMatchingLength() {
        VerifiableBooleanArray<IllegalArgumentException> verifiable = getInstance(new boolean[]{true});
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasLengthOfAtMost(0));
    }

    @Test
    void testHasLengthOfAtMostThrowsForNullSubject() {
        VerifiableBooleanArray<IllegalArgumentException> verifiable = getInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasLengthOfAtMost(1));
    }

    @Test
    void testContainsReturnsSubjectForSubjectContaining() {
        boolean[] subject = {true};
        VerifiableBooleanArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.contains(true),
                              "VerifiableBoolean should return subject for contains with matching element.");
    }

    @Test
    void testContainsThrowsForSubjectNotContaining() {
        VerifiableBooleanArray<IllegalArgumentException> verifiable = getInstance(new boolean[]{true});
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.contains(false));
    }

    @Test
    void testContainsThrowsForNullSubject() {
        VerifiableBooleanArray<IllegalArgumentException> verifiable = getInstance(new boolean[]{true});
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.contains(false));
    }

    @Test
    void testDoesNotContainReturnsSubjectForSubjectNotContaining() {
        boolean[] subject = {true};
        VerifiableBooleanArray<IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.doesNotContain(false),
                              "VerifiableBoolean should return subject for doesNotContain with non-matching element.");
    }

    @Test
    void testDoesNotContainThrowsForSubjectContaining() {
        VerifiableBooleanArray<IllegalArgumentException> verifiable = getInstance(new boolean[]{true});
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.doesNotContain(true));
    }

    @Test
    void testDoesNotContainThrowsForNullSubject() {
        VerifiableBooleanArray<IllegalArgumentException> verifiable = getInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.doesNotContain(true));
    }
}
