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

package com.redfin.validity.verifiers.objects;

import com.redfin.validity.DefaultValidityFailedValidationExecutor;
import com.redfin.validity.FailedValidationExecutor;
import com.redfin.validity.verifiers.AbstractVerifiableComparableContract;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.function.Supplier;

final class VerifiableDurationTest
 implements AbstractVerifiableComparableContract<IllegalArgumentException, Duration, VerifiableDuration<IllegalArgumentException>> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test values & contract implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private static final Duration SUBJECT = Duration.ofMinutes(1);
    private static final Duration EQUAL = Duration.ofMinutes(1);
    private static final Duration NON_EQUAL = Duration.ofMinutes(2);
    private static final Duration LESS_THAN = Duration.ofMinutes(-1);
    private static final Duration GREATER_THAN = Duration.ofMinutes(10);


    @Override
    public Duration getSubject() {
        return SUBJECT;
    }

    @Override
    public Duration getEqualSubject() {
        return EQUAL;
    }

    @Override
    public Duration getNonEqualSubject() {
        return NON_EQUAL;
    }

    @Override
    public VerifiableDuration<IllegalArgumentException> getVerifiableInstance(FailedValidationExecutor<IllegalArgumentException> failedValidationExecutor,
                                                                              Duration subject,
                                                                              Supplier<String> messageSupplier) {
        return new VerifiableDuration<>(failedValidationExecutor, subject, messageSupplier);
    }

    @Override
    public Class<IllegalArgumentException> getThrowableClass() {
        return IllegalArgumentException.class;
    }

    @Override
    public FailedValidationExecutor<IllegalArgumentException> getFailedValidationExecutor() {
        return new DefaultValidityFailedValidationExecutor<>(IllegalArgumentException::new);
    }

    @Override
    public Duration getComparableSubject() {
        return EQUAL;
    }

    @Override
    public Duration getNonComparableSubject() {
        return NON_EQUAL;
    }

    @Override
    public Duration getLessThanSubject() {
        return LESS_THAN;
    }

    @Override
    public Duration getGreaterThanSubject() {
        return GREATER_THAN;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    void testIsZeroReturnsSubjectForZeroSubject() {
        Duration subject = Duration.ZERO;
        VerifiableDuration<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.isZero(),
                              "VerifiableDuration should return it's given subject for isZero when it's zero");
    }

    @Test
    void testIsZeroThrowsForNonZeroSubject() {
        Duration subject = Duration.ofMinutes(1);
        VerifiableDuration<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isZero);
    }

    @Test
    void testIsZeroThrowsForNullSubject() {
        VerifiableDuration<IllegalArgumentException> verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isZero);
    }

    @Test
    void testIsNotZeroReturnsSubjectForNonZeroSubject() {
        Duration subject = Duration.ofMinutes(1);
        VerifiableDuration<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.isNotZero(),
                              "VerifiableDuration should return it's given subject for isNotZero when it's not zero");
    }

    @Test
    void testIsNotZeroThrowsForZeroSubject() {
        Duration subject = Duration.ZERO;
        VerifiableDuration<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isNotZero);
    }

    @Test
    void testIsNotZeroThrowsForNullSubject() {
        VerifiableDuration<IllegalArgumentException> verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isNotZero);
    }

    @Test
    void testIsStrictlyPositiveReturnsSubjectForPositiveSubject() {
        Duration subject = Duration.ofMinutes(1);
        VerifiableDuration<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.isStrictlyPositive(),
                              "VerifiableDuration should return it's given subject for isStrictlyPositive when it's positive");
    }

    @Test
    void testIsStrictlyPositiveThrowsForZeroSubject() {
        Duration subject = Duration.ZERO;
        VerifiableDuration<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isStrictlyPositive);
    }

    @Test
    void testIsStrictlyPositiveThrowsForNegativeSubject() {
        Duration subject = Duration.ofMinutes(-1);
        VerifiableDuration<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isStrictlyPositive);
    }

    @Test
    void testIsStrictlyPositiveThrowsForNullSubject() {
        VerifiableDuration<IllegalArgumentException> verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isStrictlyPositive);
    }

    @Test
    void testIsStrictlyNegativeReturnsSubjectForNegativeSubject() {
        Duration subject = Duration.ofMinutes(-1);
        VerifiableDuration<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.isStrictlyNegative(),
                              "VerifiableDuration should return it's given subject for isStrictlyNegative when it's negative");
    }

    @Test
    void testIsStrictlyNegativeThrowsForZeroSubject() {
        Duration subject = Duration.ZERO;
        VerifiableDuration<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isStrictlyNegative);
    }

    @Test
    void testIsStrictlyNegativeThrowsForPositiveSubject() {
        Duration subject = Duration.ofMinutes(1);
        VerifiableDuration<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isStrictlyNegative);
    }

    @Test
    void testIsStrictlyNegativeThrowsForNullSubject() {
        VerifiableDuration<IllegalArgumentException> verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isStrictlyNegative);
    }

    @Test
    void testIsGreaterThanOrEqualToZeroReturnsSubjectForPositiveSubject() {
        Duration subject = Duration.ofMinutes(1);
        VerifiableDuration<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.isGreaterThanOrEqualToZero(),
                              "VerifiableDuration should return it's given subject for isGreaterThanOrEqualToZero when it's positive");
    }

    @Test
    void testIsGreaterThanOrEqualToZeroReturnsSubjectForZeroSubject() {
        Duration subject = Duration.ZERO;
        VerifiableDuration<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.isGreaterThanOrEqualToZero(),
                              "VerifiableDuration should return it's given subject for isGreaterThanOrEqualToZero when it's zero");
    }

    @Test
    void testIsGreaterThanOrEqualToZeroThrowsForNegativeSubject() {
        VerifiableDuration<IllegalArgumentException> verifiable = getVerifiableInstance(Duration.ofMillis(-500));
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isGreaterThanOrEqualToZero);
    }

    @Test
    void testIsGreaterThanOrEqualToZeroThrowsForNullSubject() {
        VerifiableDuration<IllegalArgumentException> verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isGreaterThanOrEqualToZero);
    }

    @Test
    void testIsLessThanOrEqualToZeroReturnsSubjectForNegativeSubject() {
        Duration subject = Duration.ofMinutes(-1);
        VerifiableDuration<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.isLessThanOrEqualToZero(),
                              "VerifiableDuration should return it's given subject for isLessThanOrEqualToZero when it's negative");
    }

    @Test
    void testIsLessThanOrEqualToZeroReturnsSubjectForZeroSubject() {
        Duration subject = Duration.ZERO;
        VerifiableDuration<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.isLessThanOrEqualToZero(),
                              "VerifiableDuration should return it's given subject for isLessThanOrEqualToZero when it's zero");
    }

    @Test
    void testIsLessThanOrEqualToZeroThrowsForPositiveSubject() {
        VerifiableDuration<IllegalArgumentException> verifiable = getVerifiableInstance(Duration.ofMillis(500));
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isLessThanOrEqualToZero);
    }

    @Test
    void testIsLessThanOrEqualToZeroThrowsForNullSubject() {
        VerifiableDuration<IllegalArgumentException> verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isLessThanOrEqualToZero);
    }
}
