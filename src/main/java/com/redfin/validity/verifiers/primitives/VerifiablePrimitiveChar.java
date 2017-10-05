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

package com.redfin.validity.verifiers.primitives;

import com.redfin.validity.FailedValidationExecutor;
import com.redfin.validity.ValidityUtils;
import com.redfin.validity.verifiers.AbstractVerifiablePrimitive;

import java.util.function.IntPredicate;
import java.util.function.Supplier;

/**
 * Concrete class for verifying primitive char subjects.
 *
 * @param <X> the type of {@link Throwable} to be thrown on validation failure.
 */
public final class VerifiablePrimitiveChar<X extends Throwable>
           extends AbstractVerifiablePrimitive<X> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Fields
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private final char subject;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Instance Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Create a new {@link VerifiablePrimitiveChar} instance with the given values.
     *
     * @param failedValidationExecutor the {@link FailedValidationExecutor} to be called
     *                                 on validation failure.
     *                                 May not be null.
     * @param subject                  the subject to be validated.
     * @param messageSupplier          the {@link Supplier} of the String custom message to pre-pend a failure with.
     *                                 May not be null.
     *
     * @throws NullPointerException if failedValidationExecutor or messageSupplier are null.
     */
    public VerifiablePrimitiveChar(FailedValidationExecutor<X> failedValidationExecutor,
                                   char subject,
                                   Supplier<String> messageSupplier) {
        super(failedValidationExecutor, messageSupplier);
        this.subject = subject;
    }

    /**
     * @param other the object to check for equality with against the subject.
     *
     * @return the subject if it is equal to other.
     *
     * @throws X if the subject is null or if it is not equal to other.
     */
    public char isEqualTo(char other) throws X {
        if (subject != other) {
            fail("t -> t == " + ValidityUtils.describe(other));
        }
        return subject;
    }

    /**
     * @param other the object to check for equality with against the subject.
     *
     * @return the subject if it is not equal to other.
     *
     * @throws X if the subject is null or if it is equal to other.
     */
    public char isNotEqualTo(char other) throws X {
        if (subject == other) {
            fail("t -> t != " + ValidityUtils.describe(other));
        }
        return subject;
    }

    /**
     * @param other the object to compare the subject against.
     *
     * @return the subject if it is greater than other.
     *
     * @throws X if the subject is null or not greater than other.
     */
    public char isGreaterThan(char other) throws X {
        if (subject <= other) {
            fail("t -> t > " + ValidityUtils.describe(other));
        }
        return subject;
    }

    /**
     * @param other the object to compare the subject against.
     *
     * @return the subject if it is greater than or equal to other.
     *
     * @throws X if the subject is null or is not greater than or equal to other.
     */
    public char isGreaterThanOrEqualTo(char other) throws X {
        if (subject < other) {
            fail("t -> t >= " + ValidityUtils.describe(other));
        }
        return subject;
    }

    /**
     * @param other the object to compare the subject against.
     *
     * @return the subject if it is greater than or equal to other.
     *
     * @throws X if the subject is null or is not greater than or equal to other.
     */
    public char isAtLeast(char other) throws X {
        return isGreaterThanOrEqualTo(other);
    }

    /**
     * @param other the object to compare the subject against.
     *
     * @return the subject if it is less than other.
     *
     * @throws X if the subject is null or is not less than other.
     */
    public char isLessThan(char other) throws X {
        if (subject >= other) {
            fail("t -> t < " + ValidityUtils.describe(other));
        }
        return subject;
    }

    /**
     * @param other the object to compare the subject against.
     *
     * @return the subject if it is less than or equal to other.
     *
     * @throws X if the subject is null or is not less than or equal to to other.
     */
    public char isLessThanOrEqualTo(char other) throws X {
        if (subject > other) {
            fail("t -> t <= " + ValidityUtils.describe(other));
        }
        return subject;
    }

    /**
     * @param other the object to compare the subject against.
     *
     * @return the subject if it is less than or equal to other.
     *
     * @throws X if the subject is null or is not less than or equal to other.
     */
    public char isAtMost(char other) throws X {
        return isLessThanOrEqualTo(other);
    }

    /**
     * @return the subject if it is upper case.
     *
     * @throws X if the subject is null or not upper case.
     */
    public Character isUpperCase() throws X {
        if (!Character.isUpperCase(subject)) {
            fail("t -> Character.isUpperCase(t)");
        }
        return subject;
    }

    /**
     * @return the subject if it is lower case.
     *
     * @throws X if the subject is null or not lower case.
     */
    public char isLowerCase() throws X {
        if (!Character.isLowerCase(subject)) {
            fail("t -> Character.isLowerCase(t)");
        }
        return subject;
    }

    /**
     * @return the subject if it is a letter or digit case.
     *
     * @throws X if the subject is null is not a letter or digit.
     */
    public char isLetterOrDigit() throws X {
        if (!Character.isLetterOrDigit(subject)) {
            fail("t -> Character.isLetterOrDigit(t)");
        }
        return subject;
    }

    /**
     * @return the subject if it is alphabetic.
     *
     * @throws X if the subject is null or is not alphabetic.
     */
    public char isAlphabetic() throws X {
        if (!Character.isAlphabetic(subject)) {
            fail("t -> Character.isAlphabetic(t)");
        }
        return subject;
    }

    /**
     * @return the subject if it is a digit.
     *
     * @throws X if the subject is null or is not a digit.
     */
    public char isDigit() throws X {
        if (!Character.isDigit(subject)) {
            fail("t -> Character.isDigit(t)");
        }
        return subject;
    }

    /**
     * Tests the subject with the given predicate. Note that any {@link Throwable} thrown
     * during the testing of the predicate is not handled and will be thrown instead
     * of the usual throwable for this verifiable instance.
     *
     * @param expected the {@link IntPredicate} to use to test the subject.
     *                 May not be null.
     *
     * @return the subject if it satisfies the predicate.
     *
     * @throws X                    if the subject does not satisfy the predicate.
     * @throws NullPointerException if expected is null.
     */
    public char satisfies(IntPredicate expected) throws X {
        if (null == expected) {
            throw new NullPointerException(ValidityUtils.nullArgumentMessage("expected"));
        }
        if (!expected.test(subject)) {
            fail(ValidityUtils.describe(expected));
        }
        return subject;
    }

    /**
     * @param expected the String description of the expected value.
     *
     * @throws NullPointerException if expected is null.
     * @throws X                    always, unless expected is null.
     */
    protected void fail(String expected) throws X {
        getFailedValidationExecutor().fail(expected, subject, getMessageSupplier());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": <" + ValidityUtils.describe(subject) + ">";
    }
}
