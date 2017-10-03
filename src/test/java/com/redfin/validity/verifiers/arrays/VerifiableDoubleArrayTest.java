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

final class VerifiableDoubleArrayTest
 implements AbstractVerifiableObjectContract<IllegalArgumentException, double[], VerifiableDoubleArray<IllegalArgumentException>> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test values & contract implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public VerifiableDoubleArray<IllegalArgumentException> getNotValueTypeInstance() {
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
    public double[] getSubject() {
        return new double[]{0};
    }

    @Override
    public double[] getEqualSubject() {
        return new double[]{0};
    }

    @Override
    public double[] getNonEqualSubject() {
        return new double[]{1};
    }

    @Override
    public VerifiableDoubleArray<IllegalArgumentException> getVerifiableInstance(FailedValidationExecutor<IllegalArgumentException> failedValidationExecutor, double[] subject, String message) {
        return new VerifiableDoubleArray<>(failedValidationExecutor, subject, message);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    @Test
    void testIsEmptyReturnsSubjectForEmptySubject() {
        double[] subject = {};
        VerifiableDoubleArray<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.isEmpty(),
                              "VerifiableArray should return the given subject for isEmpty with empty subject.");
    }

    @Test
    void testIsEmptyThrowsForNonEmptySubject() {
        double[] subject = {0};
        VerifiableDoubleArray<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isEmpty);
    }

    @Test
    void testIsEmptyThrowsForNullSubject() {
        VerifiableDoubleArray<IllegalArgumentException> verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isEmpty);
    }

    @Test
    void testIsNotEmptyReturnsSubjectForNonEmptySubject() {
        double[] subject = {0};
        VerifiableDoubleArray<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.isNotEmpty(),
                              "VerifiableArray should return the given subject for isNotEmpty with non-empty subject.");
    }

    @Test
    void testIsNotEmptyThrowsForEmptySubject() {
        double[] subject = {};
        VerifiableDoubleArray<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isNotEmpty);
    }

    @Test
    void testIsNotEmptyThrowsForNullSubject() {
        VerifiableDoubleArray<IllegalArgumentException> verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isNotEmpty);
    }

    @Test
    void testHasLengthOfReturnsSubjectForSubjectWithMatchingLength() {
        double[] subject = {0};
        VerifiableDoubleArray<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.hasLengthOf(1),
                              "VerifiableArray should return the given subject for hasLengthOf with matching subject.");
    }

    @Test
    void testHasLengthOfThrowsForSubjectWithNonMatchingLength() {
        VerifiableDoubleArray<IllegalArgumentException> verifiable = getVerifiableInstance(new double[]{0, 1});
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasLengthOf(1));
    }

    @Test
    void testHasLengthOfThrowsForNullSubject() {
        VerifiableDoubleArray<IllegalArgumentException> verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasLengthOf(1));
    }

    @Test
    void testHasLengthOfAtLeastReturnsSubjectForSubjectWithMatchingLength() {
        double[] subject = {0};
        VerifiableDoubleArray<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.hasLengthOfAtLeast(1),
                              "VerifiableArray should return the given subject for hasLengthOfAtLeast with matching length subject.");
    }

    @Test
    void testHasLengthOfAtLeastReturnsSubjectForSubjectWithGreaterLength() {
        double[] subject = {0, 1};
        VerifiableDoubleArray<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.hasLengthOfAtLeast(1),
                              "VerifiableArray should return the given subject for hasLengthOfAtLeast with greater length subject.");
    }

    @Test
    void testHasLengthOfAtLeastThrowsForSubjectWithNonMatchingLength() {
        VerifiableDoubleArray<IllegalArgumentException> verifiable = getVerifiableInstance(new double[]{});
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasLengthOfAtLeast(1));
    }

    @Test
    void testHasLengthOfAtLeastThrowsForNullSubject() {
        VerifiableDoubleArray<IllegalArgumentException> verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasLengthOfAtLeast(1));
    }

    @Test
    void testHasLengthOfAtMostReturnsSubjectForSubjectWithMatchingLength() {
        double[] subject = {0};
        VerifiableDoubleArray<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.hasLengthOfAtMost(1),
                              "VerifiableArray should return the given subject for hasLengthOfAtMost with matching length subject.");
    }

    @Test
    void testHasLengthOfAtMostReturnsSubjectForSubjectWithGreaterLength() {
        double[] subject = {0};
        VerifiableDoubleArray<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.hasLengthOfAtMost(2),
                              "VerifiableArray should return the given subject for hasLengthOfAtMost with lesser length subject.");
    }

    @Test
    void testHasLengthOfAtMostThrowsForSubjectWithNonMatchingLength() {
        VerifiableDoubleArray<IllegalArgumentException> verifiable = getVerifiableInstance(new double[]{0});
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasLengthOfAtMost(0));
    }

    @Test
    void testHasLengthOfAtMostThrowsForNullSubject() {
        VerifiableDoubleArray<IllegalArgumentException> verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasLengthOfAtMost(1));
    }

    @Test
    void testContainsReturnsSubjectForSubjectContaining() {
        double[] subject = {0};
        VerifiableDoubleArray<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.contains(0),
                              "VerifiableChar should return subject for contains with matching element.");
    }

    @Test
    void testContainsThrowsForSubjectNotContaining() {
        VerifiableDoubleArray<IllegalArgumentException> verifiable = getVerifiableInstance(new double[]{0});
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.contains(1));
    }

    @Test
    void testContainsThrowsForNullSubject() {
        VerifiableDoubleArray<IllegalArgumentException> verifiable = getVerifiableInstance(new double[]{0});
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.contains(1));
    }

    @Test
    void testDoesNotContainReturnsSubjectForSubjectNotContaining() {
        double[] subject = {0};
        VerifiableDoubleArray<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.doesNotContain(1),
                              "VerifiableChar should return subject for doesNotContain with non-matching element.");
    }

    @Test
    void testDoesNotContainThrowsForSubjectContaining() {
        VerifiableDoubleArray<IllegalArgumentException> verifiable = getVerifiableInstance(new double[]{0});
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.doesNotContain(0));
    }

    @Test
    void testDoesNotContainThrowsForNullSubject() {
        VerifiableDoubleArray<IllegalArgumentException> verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.doesNotContain(0));
    }
}
