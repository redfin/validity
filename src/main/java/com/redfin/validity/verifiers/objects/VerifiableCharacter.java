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
import com.redfin.validity.verifiers.AbstractVerifiableComparable;

/**
 * Concrete class for verifying Character subjects.
 *
 * @param <X> the type of {@link Throwable} to be thrown on validation failure.
 */
public final class VerifiableCharacter<X extends Throwable> extends AbstractVerifiableComparable<Character, X> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Instance Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Create a new {@link VerifiableCharacter} instance with the given values.
     *
     * @param failedValidationExecutor the {@link FailedValidationExecutor} to be called
     *                                 on validation failure.
     *                                 May not be null.
     * @param subject                  the subject to be validated.
     *                                 May be null.
     * @param message                  the String custom message to pre-pend a failure with.
     *                                 May be null.
     * @throws NullPointerException if failedValidationExecutor is null.
     */
    public VerifiableCharacter(FailedValidationExecutor<X> failedValidationExecutor, Character subject, String message) {
        super(failedValidationExecutor, subject, message);
    }

    /**
     * @return the subject if it is upper case.
     * @throws X if the subject is null or not upper case.
     */
    public Character isUpperCase() throws X {
        Character subject = getSubject();
        if (null == subject || !Character.isUpperCase(subject)) {
            fail("t -> Character.isUpperCase(t)");
        }
        return subject;
    }

    /**
     * @return the subject if it is lower case.
     * @throws X if the subject is null or not lower case.
     */
    public Character isLowerCase() throws X {
        Character subject = getSubject();
        if (null == subject || !Character.isLowerCase(subject)) {
            fail("t -> Character.isLowerCase(t)");
        }
        return subject;
    }

    /**
     * @return the subject if it is a letter or digit case.
     * @throws X if the subject is null is not a letter or digit.
     */
    public Character isLetterOrDigit() throws X {
        Character subject = getSubject();
        if (null == subject || !Character.isLetterOrDigit(subject)) {
            fail("t -> Character.isLetterOrDigit(t)");
        }
        return subject;
    }

    /**
     * @return the subject if it is alphabetic.
     * @throws X if the subject is null or is not alphabetic.
     */
    public Character isAlphabetic() throws X {
        Character subject = getSubject();
        if (null == subject || !Character.isAlphabetic(subject)) {
            fail("t -> Character.isAlphabetic(t)");
        }
        return subject;
    }

    /**
     * @return the subject if it is a digit.
     * @throws X if the subject is null or is not a digit.
     */
    public Character isDigit() throws X {
        Character subject = getSubject();
        if (null == subject || !Character.isDigit(subject)) {
            fail("t -> Character.isDigit(t)");
        }
        return subject;
    }
}
