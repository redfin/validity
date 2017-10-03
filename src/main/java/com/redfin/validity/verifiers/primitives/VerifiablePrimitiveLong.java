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
import java.util.function.LongPredicate;

/**
 * Concrete class for verifying primitive long subjects.
 *
 * @param <X> the type of {@link Throwable} to be thrown on validation failure.
 */
public final class VerifiablePrimitiveLong<X extends Throwable>
           extends AbstractVerifiablePrimitive<X> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Fields
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private final long subject;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Instance Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Create a new {@link VerifiablePrimitiveLong} instance with the given values.
     *
     * @param failedValidationExecutor the {@link FailedValidationExecutor} to be called
     *                                 on validation failure.
     *                                 May not be null.
     * @param subject                  the subject to be validated.
     * @param message                  the String custom message to pre-pend a failure with.
     *                                 May be null.
     * @throws NullPointerException if failedValidationExecutor is null.
     */
    public VerifiablePrimitiveLong(FailedValidationExecutor<X> failedValidationExecutor, long subject, String message) {
        super(failedValidationExecutor, message);
        this.subject = subject;
    }

    /**
     * @param other the object to check for equality with against the subject.
     * @return the subject if it is equal to other.
     * @throws X if the subject is null or if it is not equal to other.
     */
    public long isEqualTo(long other) throws X {
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
    public long isNotEqualTo(long other) throws X {
        if (subject == other) {
            fail("t -> t != " + ValidityUtils.describe(other));
        }
        return subject;
    }

    /**
     * @return the subject if it is comparable to zero.
     * @throws X if the subject is not zero.
     */
    public long isZero() throws X {
        if (subject != 0) {
            fail("t -> t == 0");
        }
        return subject;
    }

    /**
     * @return the subject if it is not comparable to zero.
     * @throws X if the subject is zero.
     */
    public long isNotZero() throws X {
        if (subject == 0) {
            fail("t -> t != 0");
        }
        return subject;
    }

    /**
     * @return the subject if it is greater than zero.
     * @throws X if the subject is less than or equal to zero.
     */
    public long isStrictlyPositive() throws X {
        return isGreaterThan((long) 0);
    }

    /**
     * @return the subject if it is less than zero.
     * @throws X if the subject is greater than or equal to zero.
     */
    public long isStrictlyNegative() throws X {
        return isLessThan((long) 0);
    }

    /**
     * @param other the object to compare the subject against.
     * @return the subject if it is greater than other.
     * @throws X if the subject is null or not greater than other.
     */
    public long isGreaterThan(long other) throws X {
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
    public long isGreaterThanOrEqualTo(long other) throws X {
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
    public long isAtLeast(long other) throws X {
        return isGreaterThanOrEqualTo(other);
    }

    /**
     * @param other the object to compare the subject against.
     * @return the subject if it is less than other.
     * @throws X if the subject is null or is not less than other.
     */
    public long isLessThan(long other) throws X {
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
    public long isLessThanOrEqualTo(long other) throws X {
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
    public long isAtMost(long other) throws X {
        return isLessThanOrEqualTo(other);
    }

    /**
     * Tests the subject with the given predicate. Note that any {@link Throwable} thrown
     * during the testing of the predicate is not handled and will be thrown instead
     * of the usual throwable for this verifiable instance.
     *
     * @param expected the {@link LongPredicate} to use to test the subject.
     *                 May not be null.
     * @return the subject if it satisfies the predicate.
     * @throws X                    if the subject does not satisfy the predicate.
     * @throws NullPointerException if expected is null.
     */
    public long satisfies(LongPredicate expected) throws X {
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
