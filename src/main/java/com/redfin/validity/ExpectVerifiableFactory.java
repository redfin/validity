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

package com.redfin.validity;

import java.util.function.Supplier;

/**
 * A default implementation of the {@link AbstractVerifiableFactory} for the Validity library
 * for throwing IllegalStateExceptions.
 */
public final class ExpectVerifiableFactory
           extends AbstractVerifiableFactory<IllegalStateException, ExpectVerifiableFactory> {

    /**
     * Create a new {@link AbstractVerifiableFactory} instance with the given message and failed validation
     * executor.
     *
     * @param messageSupplier          the {@link Supplier} of the String message to pre-pend the failure message with, if necessary.
     *                                 May not be null.
     * @param failedValidationExecutor the {@link FailedValidationExecutor} to use in case
     *                                 of failed validation.
     *                                 May not be null.
     *
     * @throws NullPointerException if messageSupplier or failedValidationExecutor is null.
     */
    public ExpectVerifiableFactory(Supplier<String> messageSupplier,
                                   FailedValidationExecutor<IllegalStateException> failedValidationExecutor) {
        super(messageSupplier, failedValidationExecutor);
    }

    @Override
    protected ExpectVerifiableFactory getFactory(Supplier<String> messageSupplier,
                                                 FailedValidationExecutor<IllegalStateException> failedValidationExecutor) {
        return new ExpectVerifiableFactory(messageSupplier,
                                           failedValidationExecutor);
    }
}
