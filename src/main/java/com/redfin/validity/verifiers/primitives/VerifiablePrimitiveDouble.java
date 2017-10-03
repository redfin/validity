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
import java.util.function.DoublePredicate;

/**
 * Concrete class for verifying primitive double subjects.
 *
 * @param <X> the type of {@link Throwable} to be thrown on validation failure.
 */
public final class VerifiablePrimitiveDouble<X extends Throwable>
           extends AbstractVerifiablePrimitive<X> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Fields
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private final double subject;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Instance Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Create a new {@link VerifiablePrimitiveDouble} instance with the given values.
     *
     * @param failedValidationExecutor the {@link FailedValidationExecutor} to be called
     *                                 on validation failure.
     *                                 May not be null.
     * @param subject                  the subject to be validated.
     * @param message                  the String custom message to pre-pend a failure with.
     *                                 May be null.
     * @throws NullPointerException if failedValidationExecutor is null.
     */
    public VerifiablePrimitiveDouble(FailedValidationExecutor<X> failedValidationExecutor, double subject, String message) {
        super(failedValidationExecutor, message);
        this.subject = subject;
    }

    /**
     * @param other the object to check for equality with against the subject.
     * @return the subject if it is equal to other.
     * @throws X if the subject is null or if it is not equal to other.
     */
    public double isEqualTo(double other) throws X {
        if (subject != other) {
            fail("t -> t == " + ValidityUtils.describe(other));
        }
        return subject;
    }

    /**
     * @param other the object to check for equality with against the subject.
     * @return the subject if it is not equal to other.
     * @throws X if the subject is null or if it is equal to other.
     */
    public double isNotEqualTo(double other) throws X {
        if (subject == other) {
            fail("t -> t != " + ValidityUtils.describe(other));
        }
        return subject;
    }

    /**
     * @return the subject if it is comparable to zero.
     * @throws X if the subject is not zero.
     */
    public double isZero() throws X {
        if (subject != 0) {
            fail("t -> t == 0");
        }
        return subject;
    }

    /**
     * @return the subject if it is not comparable to zero.
     * @throws X if the subject is zero.
     */
    public double isNotZero() throws X {
        if (subject == 0) {
            fail("t -> t != 0");
        }
        return subject;
    }

    /**
     * @return the subject if it is greater than zero.
     * @throws X if the subject is less than or equal to zero.
     */
    public double isStrictlyPositive() throws X {
        return isGreaterThan((double) 0);
    }

    /**
     * @return the subject if it is less than zero.
     * @throws X if the subject is greater than or equal to zero.
     */
    public double isStrictlyNegative() throws X {
        return isLessThan((double) 0);
    }

    /**
     * @param other the object to compare the subject against.
     * @return the subject if it is greater than other.
     * @throws X if the subject is null or not greater than other.
     */
    public double isGreaterThan(double other) throws X {
        if (subject <= other) {
            fail("t -> t > " + ValidityUtils.describe(other));
        }
        return subject;
    }

    /**
     * @param other the object to compare the subject against.
     * @return the subject if it is greater than or equal to other.
     * @throws X if the subject is null or is not greater than or equal to other.
     */
    public double isGreaterThanOrEqualTo(double other) throws X {
        if (subject < other) {
            fail("t -> t >= " + ValidityUtils.describe(other));
        }
        return subject;
    }

    /**
     * @param other the object to compare the subject against.
     * @return the subject if it is greater than or equal to other.
     * @throws X if the subject is null or is not greater than or equal to other.
     */
    public double isAtLeast(double other) throws X {
        return isGreaterThanOrEqualTo(other);
    }

    /**
     * @param other the object to compare the subject against.
     * @return the subject if it is less than other.
     * @throws X if the subject is null or is not less than other.
     */
    public double isLessThan(double other) throws X {
        if (subject >= other) {
            fail("t -> t < " + ValidityUtils.describe(other));
        }
        return subject;
    }

    /**
     * @param other the object to compare the subject against.
     * @return the subject if it is less than or equal to other.
     * @throws X if the subject is null or is not less than or equal to to other.
     */
    public double isLessThanOrEqualTo(double other) throws X {
        if (subject > other) {
            fail("t -> t <= " + ValidityUtils.describe(other));
        }
        return subject;
    }

    /**
     * @param other the object to compare the subject against.
     * @return the subject if it is less than or equal to other.
     * @throws X if the subject is null or is not less than or equal to other.
     */
    public double isAtMost(double other) throws X {
        return isLessThanOrEqualTo(other);
    }

    /**
     * Tests the subject with the given predicate. Note that any {@link Throwable} thrown
     * during the testing of the predicate is not handled and will be thrown instead
     * of the usual throwable for this verifiable instance.
     *
     * @param expected the {@link DoublePredicate} to use to test the subject.
     *                 May not be null.
     * @return the subject if it satisfies the predicate.
     * @throws X                    if the subject does not satisfy the predicate.
     * @throws NullPointerException if expected is null.
     */
    public double satisfies(DoublePredicate expected) throws X {
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
    protected void fail(String expected) throws X {
        getFailedValidationExecutor().fail(expected, subject, getMessage());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": <" + ValidityUtils.describe(subject) + ">";
    }
}
