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

package com.redfin.validity.verifiers;

import com.redfin.validity.FailedValidationExecutor;
import com.redfin.validity.ValidityUtils;
import java.util.function.Predicate;

/**
 * Base class for all verifiable objects. Stores the values, disallows the use
 * of these instances as value types (cannot perform equals or hashing), and
 * defines some generic validation methods that all types will inherit.
 *
 * @param <T> the type of the subject being validated.
 * @param <X> the type of {@link Throwable} to be thrown on validation failure.
 */
public abstract class AbstractVerifiableObject<T, X extends Throwable> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Fields
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private final FailedValidationExecutor<X> failedValidationExecutor;
    private final T subject;
    private final String message;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Instance Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Create a new {@link AbstractVerifiableObject} instance with the given values.
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
    public AbstractVerifiableObject(FailedValidationExecutor<X> failedValidationExecutor, T subject, String message) {
        if (null == failedValidationExecutor) {
            throw new NullPointerException(ValidityUtils.nullArgumentMessage("failedValidationExecutor"));
        }
        this.failedValidationExecutor = failedValidationExecutor;
        this.subject = subject;
        this.message = message;
    }

    // --------------------------------------------------------------
    // Getter methods
    // --------------------------------------------------------------

    /**
     * @return the given {@link FailedValidationExecutor}.
     */
    protected final FailedValidationExecutor<X> getFailedValidationExecutor() {
        return failedValidationExecutor;
    }

    /**
     * @return the given test subject.
     */
    protected final T getSubject() {
        return subject;
    }

    /**
     * @return the given message.
     */
    protected final String getMessage() {
        return message;
    }

    // --------------------------------------------------------------
    // Verification methods & helpers
    // --------------------------------------------------------------

    /**
     * @return the subject if it is null.
     * @throws X if the subject is not null.
     */
    public T isNull() throws X {
        if (null != subject) {
            fail("t -> null == t");
        }
        return subject;
    }

    /**
     * @return the subject if it is not null.
     * @throws X if the subject is null.
     */
    public T isNotNull() throws X {
        if (null == subject) {
            fail("t -> null != t");
        }
        return subject;
    }

    /**
     * @param other the object to check for equality with against the subject.
     * @return the subject if it is equal to other.
     * @throws X if the subject is null or if it is not equal to other.
     */
    public T isEqualTo(T other) throws X {
        if (null == subject || !subject.equals(other)) {
            fail("t -> t.equals(" + ValidityUtils.describe(other) + ")");
        }
        return subject;
    }

    /**
     * @param other the object to check for equality with against the subject.
     * @return the subject if it is not equal to other.
     * @throws X if the subject is null or if it is equal to other.
     */
    public T isNotEqualTo(T other) throws X {
        if (null == subject || subject.equals(other)) {
            fail("t -> !t.equals(" + ValidityUtils.describe(other) + ")");
        }
        return subject;
    }

    /**
     * Tests the subject with the given predicate. Note that any {@link Throwable} thrown
     * during the testing of the predicate is not handled and will be thrown instead
     * of the usual throwable for this verifiable instance.
     *
     * @param expected the {@link Predicate} to use to test the subject.
     *                 May not be null.
     * @return the subject if it satisfies the predicate.
     * @throws X                    if the subject does not satisfy the predicate.
     * @throws NullPointerException if expected is null.
     */
    public T satisfies(Predicate<T> expected) throws X {
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
     * @throws NullPointerException if expected is null.
     * @throws X                    always, unless expected is null.
     */
    protected final void fail(String expected) throws X {
        failedValidationExecutor.fail(expected, subject, message);
    }

    // --------------------------------------------------------------
    // Object overridden methods
    // --------------------------------------------------------------

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": <" + ValidityUtils.describe(subject) + ">";
    }

    /**
     * @throws UnsupportedOperationException always.
     * @deprecated verifiable objects cannot be tested for equality. If you want to validate that
     * a subject's equality then see the {@link #isEqualTo(Object)} or {@link #isNotEqualTo(Object)}
     * methods.
     */
    @Deprecated
    @Override
    public final boolean equals(Object obj) {
        throw new UnsupportedOperationException("This class cannot be treated as a value and does not support the Object equals method");
    }

    /**
     * @throws UnsupportedOperationException always.
     * @deprecated verifiable objects cannot be hashed.
     */
    @Deprecated
    @Override
    public final int hashCode() {
        throw new UnsupportedOperationException("This class cannot be treated as a value and does not support the Object hashCode method");
    }
}
