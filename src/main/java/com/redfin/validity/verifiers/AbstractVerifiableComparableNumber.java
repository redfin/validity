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

/**
 * Base class for verifiable objects that implement the Comparable interface and Number.
 *
 * @param <T> the type of the subject being validated.
 * @param <X> the type of {@link Throwable} to be thrown on validation failure.
 */
public abstract class AbstractVerifiableComparableNumber<T extends Number & Comparable<T>, X extends Throwable> extends AbstractVerifiableComparable<T, X> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Instance Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Create a new {@link AbstractVerifiableComparableNumber} instance with the given values.
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
    public AbstractVerifiableComparableNumber(FailedValidationExecutor<X> failedValidationExecutor, T subject, String message) {
        super(failedValidationExecutor, subject, message);
    }

    /**
     * @return the value of Zero for the implementing
     * subtype of number.
     */
    protected abstract T getZero();

    /**
     * @return the subject if it is comparable to zero.
     * @throws X if the subject is not comparable to zero.
     */
    public T isZero() throws X {
        return isComparableTo(getZero());
    }

    /**
     * @return the subject if it is not comparable to zero.
     * @throws X if the subject is comparable to zero.
     */
    public T isNotZero() throws X {
        return isNotComparableTo(getZero());
    }

    /**
     * @return the subject if it is greater than zero.
     * @throws X if the subject is less than or equal to zero.
     */
    public T isStrictlyPositive() throws X {
        return isGreaterThan(getZero());
    }

    /**
     * @return the subject if it is less than zero.
     * @throws X if the subject is greater than or equal to zero.
     */
    public T isStrictlyNegative() throws X {
        return isLessThan(getZero());
    }
}
