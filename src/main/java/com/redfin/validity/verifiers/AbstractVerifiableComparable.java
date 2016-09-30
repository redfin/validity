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

/**
 * Base class for verifiable objects that are {@link Comparable}.
 *
 * @param <T> the type of the subject being validated.
 *            Must be a {@link Comparable} type.
 * @param <X> the type of {@link Throwable} to be thrown on validation failure.
 */
public abstract class AbstractVerifiableComparable<T extends Comparable<T>, X extends Throwable> extends AbstractVerifiableObject<T, X> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Instance Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Create a new {@link AbstractVerifiableComparable} instance with the given values.
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
    public AbstractVerifiableComparable(FailedValidationExecutor<X> failedValidationExecutor, T subject, String message) {
        super(failedValidationExecutor, subject, message);
    }

    /**
     * Note that this is uses the equals method of the number object type. This may
     * not be what you are looking for with comparable types. See {@link #isNotComparableTo(Comparable)}.
     *
     * @param other the object to check for equality with against the subject.
     * @return the subject if it is equal to other.
     * @throws X if the subject is null or if it is not equal to other.
     */
    public T isEqualTo(T other) throws X {
        return super.isEqualTo(other);
    }

    /**
     * Note that this is uses the equals method of the comparable object type. This may
     * not be what you are looking for with comparable types. See {@link #isNotComparableTo(Comparable)}.
     *
     * @param other the object to check for equality with against the subject.
     * @return the subject if it is not equal to other.
     * @throws X if the subject is null or if it is equal to other.
     */
    public T isNotEqualTo(T other) throws X {
        return super.isNotEqualTo(other);
    }

    /**
     * Tests for equality using the {@link Comparable#compareTo(Object)} method. If the
     * subject is not null and the compareTo method returns 0, then the values are considered
     * to be equal.
     *
     * @param other the object to compare the subject against.
     * @return the subject if it is comparable to other.
     * @throws X if the subject is null or not comparable to other.
     */
    public T isComparableTo(T other) throws X {
        T subject = getSubject();
        if (null == subject || subject.compareTo(other) != 0) {
            fail("t -> t == " + ValidityUtils.describe(other));
        }
        return subject;
    }

    /**
     * Tests for equality using the {@link Comparable#compareTo(Object)} method. If the
     * subject is not null and the compareTo method returns 0, then the values are considered
     * to be equal.
     *
     * @param other the object to compare the subject against.
     * @return the subject if it is not comparable to other.
     * @throws X if the subject is null or is comparable to other.
     */
    public T isNotComparableTo(T other) throws X {
        T subject = getSubject();
        if (null == subject || subject.compareTo(other) == 0) {
            fail("t -> t.compareTo(" + ValidityUtils.describe(other) + ") != 0");
        }
        return subject;
    }

    /**
     * @param other the object to compare the subject against.
     * @return the subject if it is greater than other.
     * @throws X if the subject is null or not greater than other.
     */
    public T isGreaterThan(T other) throws X {
        T subject = getSubject();
        if (null == subject || subject.compareTo(other) <= 0) {
            fail("t -> t > " + ValidityUtils.describe(other));
        }
        return subject;
    }

    /**
     * @param other the object to compare the subject against.
     * @return the subject if it is greater than or equal to other.
     * @throws X if the subject is null or is not greater than or equal to other.
     */
    public T isGreaterThanOrEqualTo(T other) throws X {
        T subject = getSubject();
        if (null == subject || subject.compareTo(other) < 0) {
            fail("t -> t >= " + ValidityUtils.describe(other));
        }
        return subject;
    }

    /**
     * @param other the object to compare the subject against.
     * @return the subject if it is greater than or equal to other.
     * @throws X if the subject is null or is not greater than or equal to other.
     */
    public T isAtLeast(T other) throws X {
        return isGreaterThanOrEqualTo(other);
    }

    /**
     * @param other the object to compare the subject against.
     * @return the subject if it is less than other.
     * @throws X if the subject is null or is not less than other.
     */
    public T isLessThan(T other) throws X {
        T subject = getSubject();
        if (null == subject || subject.compareTo(other) >= 0) {
            fail("t -> t < " + ValidityUtils.describe(other));
        }
        return subject;
    }

    /**
     * @param other the object to compare the subject against.
     * @return the subject if it is less than or equal to other.
     * @throws X if the subject is null or is not less than or equal to to other.
     */
    public T isLessThanOrEqualTo(T other) throws X {
        T subject = getSubject();
        if (null == subject || subject.compareTo(other) > 0) {
            fail("t -> t <= " + ValidityUtils.describe(other));
        }
        return subject;
    }

    /**
     * @param other the object to compare the subject against.
     * @return the subject if it is less than or equal to other.
     * @throws X if the subject is null or is not less than or equal to other.
     */
    public T isAtMost(T other) throws X {
        return isLessThanOrEqualTo(other);
    }
}
