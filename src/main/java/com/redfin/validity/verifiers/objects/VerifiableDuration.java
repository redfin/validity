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
import com.redfin.validity.verifiers.AbstractVerifiableObject;

import java.time.Duration;
import java.util.function.Supplier;

/**
 * Concrete class for verifying {@link Duration} subjects.
 *
 * @param <X> the type of {@link Throwable} to be thrown on validation failure.
 */
public final class VerifiableDuration<X extends Throwable>
           extends AbstractVerifiableComparable<Duration, X> {

    /**
     * Create a new {@link AbstractVerifiableObject} instance with the given values.
     *
     * @param failedValidationExecutor the {@link FailedValidationExecutor} to be called
     *                                 on validation failure.
     *                                 May not be null.
     * @param subject                  the subject to be validated.
     *                                 May be null.
     * @param messageSupplier          the {@link Supplier} of the String custom message to pre-pend a failure with.
     *                                 May not be null.
     *
     * @throws NullPointerException if failedValidationExecutor or messageSupplier are null.
     * @throws NullPointerException if failedValidationExecutor is null.
     */
    public VerifiableDuration(FailedValidationExecutor<X> failedValidationExecutor,
                              Duration subject,
                              Supplier<String> messageSupplier) {
        super(failedValidationExecutor, subject, messageSupplier);
    }

    /**
     * @return the subject if it is zero.
     *
     * @throws X if the subject is null or is not zero.
     */
    public Duration isZero() throws X {
        Duration subject = getSubject();
        if (null == subject || !subject.isZero()) {
            fail("t -> t.isZero()");
        }
        return subject;
    }

    /**
     * @return the subject if it is not zero.
     *
     * @throws X if the subject is null or is zero.
     */
    public Duration isNotZero() throws X {
        Duration subject = getSubject();
        if (null == subject || subject.isZero()) {
            fail("t -> !t.isZero()");
        }
        return subject;
    }

    /**
     * @return the subject if it is zero or non-negative.
     *
     * @throws X if the subject is null or negative.
     */
    public Duration isGreaterThanOrEqualToZero() throws X {
        Duration subject = getSubject();
        if (null == subject || subject.isNegative()) {
            fail("t -> !t.isNegative()");
        }
        return subject;
    }

    /**
     * @return the subject if is it negative or zero.
     *
     * @throws X if the subject is null, or neither negative or zero.
     */
    public Duration isLessThanOrEqualToZero() throws X {
        Duration subject = getSubject();
        if (null == subject || !(subject.isZero() || subject.isNegative())) {
            fail("t -> !(t.isZero() || t.isNegative())");
        }
        return subject;
    }

    /**
     * @return the subject if it is not zero and not negative.
     *
     * @throws X if the subject is null, is zero, or is positive.
     */
    public Duration isStrictlyNegative() throws X {
        Duration subject = getSubject();
        if (null == subject || !subject.isNegative()) {
            fail("t -> t.isNegative()");
        }
        return subject;
    }

    /**
     * @return the subject if it is not zero and not positive.
     *
     * @throws X if the subject is null, is zero, or is negative.
     */
    public Duration isStrictlyPositive() throws X {
        Duration subject = getSubject();
        if (null == subject || subject.isZero() || subject.isNegative()) {
            fail("t -> !t.isZero() && !t.isNegative()");
        }
        return subject;
    }
}
