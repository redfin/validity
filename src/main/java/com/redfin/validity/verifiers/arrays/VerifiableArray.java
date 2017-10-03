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
import com.redfin.validity.ValidityUtils;
import com.redfin.validity.verifiers.AbstractVerifiableObject;
import java.util.Arrays;

/**
 * Concrete class for verifying object array subjects. This will also be the
 * type used for multi-dimensional arrays of any type (including primitive).
 *
 * @param <X> the type of {@link Throwable} to be thrown on validation failure.
 */
public final class VerifiableArray<E, X extends Throwable>
           extends AbstractVerifiableObject<E[], X> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Instance Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Create a new {@link VerifiableArray} instance with the given values.
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
    public VerifiableArray(FailedValidationExecutor<X> failedValidationExecutor, E[] subject, String message) {
        super(failedValidationExecutor, subject, message);
    }

    @Override
    public E[] isEqualTo(E[] other) throws X {
        E[] subject = getSubject();
        if (null == subject || !Arrays.deepEquals(subject, other)) {
            fail("t -> Arrays.deepEquals(t, " + ValidityUtils.describe(other) + ")");
        }
        return subject;
    }

    @Override
    public E[] isNotEqualTo(E[] other) throws X {
        E[] subject = getSubject();
        if (null == subject || Arrays.deepEquals(subject, other)) {
            fail("t -> !Arrays.deepEquals(t, " + ValidityUtils.describe(other) + ")");
        }
        return subject;
    }

    /**
     * @return the subject if it is empty.
     * @throws X if the subject is null or is not empty.
     */
    public E[] isEmpty() throws X {
        E[] subject = getSubject();
        if (null == subject || subject.length != 0) {
            fail("t -> t.length == 0");
        }
        return subject;
    }

    /**
     * @return the subject if it is not empty.
     * @throws X if the subject is null or is empty.
     */
    public E[] isNotEmpty() throws X {
        E[] subject = getSubject();
        if (null == subject || subject.length == 0) {
            fail("t -> t.length != 0");
        }
        return subject;
    }

    /**
     * @param n the value to check against the subject length.
     * @return the subject if its length equals "n".
     * @throws X if the subject is null or if it's length isn't "n".
     */
    public E[] hasLengthOf(int n) throws X {
        E[] subject = getSubject();
        if (null == subject || subject.length != n) {
            fail("t -> t.length == " + ValidityUtils.describe(n));
        }
        return subject;
    }

    /**
     * @param n the value to check against the subject length.
     * @return the subject if its length is greater than or equal to "n".
     * @throws X if the subject is null or has length less than "n".
     */
    public E[] hasLengthOfAtLeast(int n) throws X {
        E[] subject = getSubject();
        if (null == subject || subject.length < n) {
            fail("t -> t.length >= " + ValidityUtils.describe(n));
        }
        return subject;
    }

    /**
     * @param n the value to check against the subject length.
     * @return the subject if its length is less than or equal to "n".
     * @throws X if the subject is null or has length greater than "n".
     */
    public E[] hasLengthOfAtMost(int n) throws X {
        E[] subject = getSubject();
        if (null == subject || subject.length > n) {
            fail("t -> t.length <= " + ValidityUtils.describe(n));
        }
        return subject;
    }
}
