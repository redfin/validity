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

import com.redfin.validity.FailedValidationExecutor;
import com.redfin.validity.FailedValidationExecutors;
import com.redfin.validity.verifiers.AbstractVerifiableComparableContract;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

final class VerifiableStringTest implements AbstractVerifiableComparableContract<IllegalArgumentException, String, VerifiableString<IllegalArgumentException>> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test values & contract implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public String getSubject() {
        return "hello";
    }

    @Override
    public String getEqualSubject() {
        return "hello";
    }

    @Override
    public String getNonEqualSubject() {
        return "world";
    }

    @Override
    public String getComparableSubject() {
        return "hello";
    }

    @Override
    public String getNonComparableSubject() {
        return "world";
    }

    @Override
    public String getLessThanSubject() {
        return "a";
    }

    @Override
    public String getGreaterThanSubject() {
        return "z";
    }

    @Override
    public VerifiableString<IllegalArgumentException> getVerifiableInstance(FailedValidationExecutor<IllegalArgumentException> failedValidationExecutor, String subject, String message) {
        return new VerifiableString<>(failedValidationExecutor, subject, message);
    }

    @Override
    public FailedValidationExecutor<IllegalArgumentException> getFailedValidationExecutor() {
        return FailedValidationExecutors.getDefaultFailureExecutor();
    }

    @Override
    public Class<IllegalArgumentException> getThrowableClass() {
        return IllegalArgumentException.class;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    void testIsEmptyReturnsSubjectForEmptySubject() {
        String subject = "";
        VerifiableString<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.isEmpty(),
                              "VerifiableString should return given subject for isEmpty with empty subject.");
    }

    @Test
    void testIsEmptyThrowsForNonEmptySubject() {
        String subject = "hello";
        VerifiableString<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isEmpty);
    }

    @Test
    void testIsEmptyThrowsForNullSubject() {
        VerifiableString<IllegalArgumentException> verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isEmpty);
    }

    @Test
    void testIsNotEmptyReturnsSubjectForNotEmptySubject() {
        String subject = "hello";
        VerifiableString<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.isNotEmpty(),
                              "VerifiableString should return given subject for isNotEmpty with non-empty subject.");
    }

    @Test
    void testIsNotEmptyThrowsForEmptySubject() {
        VerifiableString<IllegalArgumentException> verifiable = getVerifiableInstance("");
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isNotEmpty);
    }

    @Test
    void testIsNotEmptyThrowsForNullSubject() {
        VerifiableString<IllegalArgumentException> verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isNotEmpty);
    }

    @Test
    void testStartsWithReturnsSubjectForMatchingSubject() {
        String subject = "hello";
        VerifiableString<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.startsWith("h"),
                              "VerifiableString should return given subject for startsWith with matching subject.");
    }

    @Test
    void testStartsWithThrowsForNonMatchingSubject() {
        VerifiableString<IllegalArgumentException> verifiable = getVerifiableInstance("hello");
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.startsWith("world"));
    }

    @Test
    void testStartsWithThrowsForNullSubject() {
        VerifiableString<IllegalArgumentException> verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.startsWith("world"));
    }

    @Test
    void testDoesNotStartWithReturnsSubjectForNonMatchingSubject() {
        String subject = "hello";
        VerifiableString<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.doesNotStartWith("world"),
                              "VerifiableString should return given subject for doesNotStartWith with non-matching subject.");
    }

    @Test
    void testDoesNotStartWithThrowsForMatchingSubject() {
        VerifiableString<IllegalArgumentException> verifiable = getVerifiableInstance("hello");
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.doesNotStartWith("he"));
    }

    @Test
    void testDoesNotStartWithThrowsForNullSubject() {
        VerifiableString<IllegalArgumentException> verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.doesNotStartWith("world"));
    }

    @Test
    void testEndsWithReturnsSubjectForMatchingSubject() {
        String subject = "hello";
        VerifiableString<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.endsWith("o"),
                              "VerifiableString should return given subject for endsWith with matching subject.");
    }

    @Test
    void testEndsWithThrowsForNonMatchingSubject() {
        VerifiableString<IllegalArgumentException> verifiable = getVerifiableInstance("hello");
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.endsWith("he"));
    }

    @Test
    void testEndsWithThrowsForNullSubject() {
        VerifiableString<IllegalArgumentException> verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.endsWith("world"));
    }

    @Test
    void testDoesNotEndWithReturnsSubjectForNonMatchingSubject() {
        String subject = "hello";
        VerifiableString<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.doesNotEndWith("h"),
                              "VerifiableString should return given subject for doesNotEndWith with non-matching subject.");
    }

    @Test
    void testDoesNotEndWithThrowsForMatchingSubject() {
        VerifiableString<IllegalArgumentException> verifiable = getVerifiableInstance("hello");
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.doesNotEndWith("o"));
    }

    @Test
    void testDoesNotEndWithThrowsForNullSubject() {
        VerifiableString<IllegalArgumentException> verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.doesNotEndWith("world"));
    }

    @Test
    void testMatchesReturnsSubjectForMatchingSubject() {
        String subject = "hello";
        VerifiableString<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.matches(".*"),
                              "VerifiableString should return given subject for matches with matching subject.");
    }

    @Test
    void testMatchesThrowsForNonMatchingSubject() {
        VerifiableString<IllegalArgumentException> verifiable = getVerifiableInstance("hello");
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.matches("h"));
    }

    @Test
    void testMatchesThrowsForNullSubject() {
        VerifiableString<IllegalArgumentException> verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.matches("h"));
    }

    @Test
    void testDoesNotMatchReturnsSubjectForNonMatchingSubject() {
        String subject = "hello";
        VerifiableString<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.doesNotMatch("h"),
                              "VerifiableString should return given subject for doesNotMatch with non-matching subject.");
    }

    @Test
    void testDoesNotMatchThrowsForMatchingSubject() {
        VerifiableString<IllegalArgumentException> verifiable = getVerifiableInstance("hello");
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.doesNotMatch(".*"));
    }

    @Test
    void testDoesNotMatchThrowsForNullSubject() {
        VerifiableString<IllegalArgumentException> verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.doesNotMatch("h"));
    }
}
