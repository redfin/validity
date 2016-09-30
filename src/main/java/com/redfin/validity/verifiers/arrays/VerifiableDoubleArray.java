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
 * Concrete class for verifying primitive double array subjects.
 *
 * @param <X> the type of {@link Throwable} to be thrown on validation failure.
 */
public final class VerifiableDoubleArray<X extends Throwable> extends AbstractVerifiableObject<double[], X> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Instance Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Create a new {@link VerifiableDoubleArray} instance with the given values.
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
    public VerifiableDoubleArray(FailedValidationExecutor<X> failedValidationExecutor, double[] subject, String message) {
        super(failedValidationExecutor, subject, message);
    }

    /**
     * @param other the object to check for equality with against the subject.
     * @return the subject if it is equal to other.
     * @throws X if the subject is null or if it is not equal to other.
     */
    public double[] isEqualTo(double[] other) throws X {
        double[] subject = getSubject();
        if (null == subject || !Arrays.equals(subject, other)) {
            fail("t -> Arrays.equals(t, " + ValidityUtils.describe(other) + ")");
        }
        return subject;
    }

    /**
     * @param other the object to check for equality with against the subject.
     * @return the subject if it is not equal to other.
     * @throws X if the subject is null or if it is equal to other.
     */
    public double[] isNotEqualTo(double[] other) throws X {
        double[] subject = getSubject();
        if (null == subject || Arrays.equals(subject, other)) {
            fail("t -> !Arrays.equals(t, " + ValidityUtils.describe(other) + ")");
        }
        return subject;
    }

    /**
     * @return the subject if it is empty.
     * @throws X if the subject is null or is not empty.
     */
    public double[] isEmpty() throws X {
        double[] subject = getSubject();
        if (null == subject || subject.length != 0) {
            fail("t -> t.length == 0");
        }
        return subject;
    }

    /**
     * @return the subject if it is not empty.
     * @throws X if the subject is null or is empty.
     */
    public double[] isNotEmpty() throws X {
        double[] subject = getSubject();
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
    public double[] hasLengthOf(int n) throws X {
        double[] subject = getSubject();
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
    public double[] hasLengthOfAtLeast(int n) throws X {
        double[] subject = getSubject();
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
    public double[] hasLengthOfAtMost(int n) throws X {
        double[] subject = getSubject();
        if (null == subject || subject.length > n) {
            fail("t -> t.length <= " + ValidityUtils.describe(n));
        }
        return subject;
    }

    /**
     * @param value the value to look for in the array.
     * @return the subject if it contains the value.
     * @throws X if the subject is null or does not contain the value.
     */
    public double[] contains(double value) throws X {
        double[] subject = getSubject();
        if (null == subject || !containsHelper(subject, value)) {
            fail("t -> t.contains(" + ValidityUtils.describe(value) + ")");
        }
        return subject;
    }

    /**
     * @param value the value to look for in the array.
     * @return the subject if it does not contain the value.
     * @throws X if the subject is null or does contain the value.
     */
    public double[] doesNotContain(double value) throws X {
        double[] subject = getSubject();
        if (null == subject || containsHelper(subject, value)) {
            fail("t -> !t.contains(" + ValidityUtils.describe(value) + ")");
        }
        return subject;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Instance Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private static boolean containsHelper(double[] array, double value) {
        if (null == array) {
            throw new NullPointerException(ValidityUtils.nullArgumentMessage("array"));
        }
        for (double next : array) {
            if (Double.compare(next, value) == 0) {
                return true;
            }
        }
        return false;
    }
}