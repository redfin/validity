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
import com.redfin.validity.verifiers.AbstractVerifiableObject;

import java.util.function.Supplier;

/**
 * Concrete class for verifying Boolean subjects.
 *
 * @param <X> the type of {@link Throwable} to be thrown on validation failure.
 */
public final class VerifiableBoolean<X extends Throwable>
           extends AbstractVerifiableObject<Boolean, X> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Instance Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Create a new {@link VerifiableBoolean} instance with the given values.
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
     */
    public VerifiableBoolean(FailedValidationExecutor<X> failedValidationExecutor,
                             Boolean subject,
                             Supplier<String> messageSupplier) {
        super(failedValidationExecutor, subject, messageSupplier);
    }

    /**
     * @return the Boolean true if the subject is true.
     *
     * @throws X if the subject is null or false.
     */
    public Boolean isTrue() throws X {
        Boolean subject = getSubject();
        if (null == subject || !subject) {
            fail("t -> t");
        }
        return subject;
    }

    /**
     * @return the Boolean false if the subject is false.
     *
     * @throws X if the subject is null or true.
     */
    public Boolean isFalse() throws X {
        Boolean subject = getSubject();
        if (null == subject || subject) {
            fail("t -> !t");
        }
        return subject;
    }
}
