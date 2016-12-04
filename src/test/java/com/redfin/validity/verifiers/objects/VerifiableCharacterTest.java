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

final class VerifiableCharacterTest implements AbstractVerifiableComparableContract<IllegalArgumentException, Character, VerifiableCharacter<IllegalArgumentException>> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test values & contract implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public Character getSubject() {
        return new Character('b');
    }

    @Override
    public Character getEqualSubject() {
        return new Character('b');
    }

    @Override
    public Character getNonEqualSubject() {
        return new Character('c');
    }

    @Override
    public Character getComparableSubject() {
        return new Character('b');
    }

    @Override
    public Character getNonComparableSubject() {
        return new Character('c');
    }

    @Override
    public Character getLessThanSubject() {
        return new Character('a');
    }

    @Override
    public Character getGreaterThanSubject() {
        return new Character('c');
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
    public VerifiableCharacter<IllegalArgumentException> getVerifiableInstance(FailedValidationExecutor<IllegalArgumentException> failedValidationExecutor, Character subject, String message) {
        return new VerifiableCharacter<>(failedValidationExecutor, subject, message);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    void testIsUpperCaseReturnsSubjectForUpperCase() {
        Character subject = 'A';
        VerifiableCharacter<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.isUpperCase(),
                              "VerifiableCharacter should return it's given subject for isUpperCase when it's upper case.");
    }

    @Test
    void testIsUpperCaseThrowsForLowerCase() {
        Character subject = 'a';
        VerifiableCharacter<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isUpperCase());
    }

    @Test
    void testIsUpperCaseThrowsForNull() {
        Character subject = null;
        VerifiableCharacter<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isUpperCase());
    }

    @Test
    void testIsLowerCaseThrowsForUpperCase() {
        Character subject = 'A';
        VerifiableCharacter<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isLowerCase());
    }

    @Test
    void testIsLowerCaseReturnsSubjectLowerCase() {
        Character subject = 'a';
        VerifiableCharacter<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.isLowerCase(),
                              "VerifiableCharacter should return it's given subject for isLowerCase when it's lower case.");
    }

    @Test
    void testIsLowerCaseThrowsForNull() {
        Character subject = null;
        VerifiableCharacter<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isLowerCase());
    }

    @Test
    void testIsLetterOrDigitReturnsSubjectForLetter() {
        Character subject = 'a';
        VerifiableCharacter<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.isLetterOrDigit(),
                              "VerifiableCharacter should return it's given subject for isLetterOrDigit when it's a letter.");
    }

    @Test
    void testIsLetterOrDigitReturnsSubjectForDigit() {
        Character subject = '0';
        VerifiableCharacter<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.isLetterOrDigit(),
                              "VerifiableCharacter should return it's given subject for isLetterOrDigit when it's a digit.");
    }

    @Test
    void testIsLetterOrDigitThrowsForNonLetterOrDigit() {
        Character subject = '\n';
        VerifiableCharacter<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isLetterOrDigit());
    }

    @Test
    void testIsLetterOrDigitThrowsForNull() {
        Character subject = null;
        VerifiableCharacter<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isLetterOrDigit());
    }

    @Test
    void testIsAlphabeticReturnsSubjectForAlphabetic() {
        Character subject = 'a';
        VerifiableCharacter<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.isAlphabetic(),
                              "VerifiableCharacter should return it's given subject for isAlphabetic when it's a letter.");
    }

    @Test
    void testIsAlphabeticThrowsForNonAlphabetic() {
        Character subject = '0';
        VerifiableCharacter<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isAlphabetic());
    }

    @Test
    void testIsAlphabeticThrowsForNull() {
        Character subject = null;
        VerifiableCharacter<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isAlphabetic());
    }

    @Test
    void testIsDigitReturnsSubjectForDigit() {
        Character subject = '0';
        VerifiableCharacter<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.isDigit(),
                              "VerifiableCharacter should return it's given subject for isDigit when it's a number.");
    }

    @Test
    void testIsDigitThrowsForNonDigit() {
        Character subject = 'a';
        VerifiableCharacter<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isDigit());
    }

    @Test
    void testIsDigitThrowsForNull() {
        Character subject = null;
        VerifiableCharacter<IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isDigit());
    }
}
