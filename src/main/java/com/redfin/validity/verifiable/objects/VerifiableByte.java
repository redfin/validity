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

package com.redfin.validity.verifiable.objects;

import com.redfin.validity.FailedValidationHandler;

/**
 * A test subject instance that allows for fluent
 * validation of Byte values.
 *
 * @param <X> the type of {@link Throwable} thrown on validation failures.
 */
public final class VerifiableByte<X extends Throwable> extends AbstractVerifiableNumber<Byte, X> {

    /**
     * Create a new {@link VerifiableByte} instance with the given values.
     *
     * @param actual                  the test subject.
     * @param description             the String description to append to throwable messages if validation fails.
     *                                May be null.
     * @param failedValidationHandler the {@link FailedValidationHandler} used to create the throwable if validation fails.
     *                                May not be null. Should never return a null throwable.
     * @throws NullPointerException if failedValidationHandler is null.
     */
    public VerifiableByte(Byte actual, String description, FailedValidationHandler<X> failedValidationHandler) {
        super(actual, description, failedValidationHandler);
    }

    @Override
    protected Byte getZero() {
        return 0;
    }
}
