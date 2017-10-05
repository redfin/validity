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

import java.time.Instant;
import java.util.function.Supplier;

/**
 * Concrete class for verifying {@link Instant} subjects.
 *
 * @param <X> the type of {@link Throwable} to be thrown on validation failure.
 */
public final class VerifiableInstant<X extends Throwable>
           extends AbstractVerifiableComparable<Instant, X> {

    /**
     * Create a new {@link AbstractVerifiableComparable} instance with the given values.
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
    public VerifiableInstant(FailedValidationExecutor<X> failedValidationExecutor,
                             Instant subject,
                             Supplier<String> messageSupplier) {
        super(failedValidationExecutor, subject, messageSupplier);
    }
}
