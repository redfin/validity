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

import java.util.function.Supplier;

/**
 * Base class for all verifiable primitive objects. Stores the message and
 * validation executor, disallows use as a value type (equals and hashCode).
 *
 * @param <X> the type of {@link Throwable} to be thrown on validation failure.
 */
public abstract class AbstractVerifiablePrimitive<X extends Throwable> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Fields
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private final FailedValidationExecutor<X> failedValidationExecutor;
    private final Supplier<String> messageSupplier;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Instance Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Create a new {@link AbstractVerifiablePrimitive} instance with the given values.
     *
     * @param failedValidationExecutor the {@link FailedValidationExecutor} to be called
     *                                 on validation failure.
     *                                 May not be null.
     * @param messageSupplier          the {@link Supplier} of the String custom message to pre-pend a failure with.
     *                                 May not be null.
     *
     * @throws NullPointerException if failedValidationExecutor or messageSupplier are null.
     */
    public AbstractVerifiablePrimitive(FailedValidationExecutor<X> failedValidationExecutor,
                                       Supplier<String> messageSupplier) {
        if (null == failedValidationExecutor) {
            throw new NullPointerException(ValidityUtils.nullArgumentMessage("failedValidationExecutor"));
        }
        if (null == messageSupplier) {
            throw new NullPointerException(ValidityUtils.nullArgumentMessage("messageSupplier"));
        }
        this.failedValidationExecutor = failedValidationExecutor;
        this.messageSupplier = messageSupplier;
    }

    /**
     * @return the {@link FailedValidationExecutor} for this verifiable instance.
     */
    protected FailedValidationExecutor<X> getFailedValidationExecutor() {
        return failedValidationExecutor;
    }

    /**
     * @return the {@link Supplier} of the String message to pre-pend on validation failures.
     * May not be null.
     */
    protected Supplier<String> getMessageSupplier() {
        return messageSupplier;
    }

    /**
     * @throws UnsupportedOperationException always.
     * @deprecated verifiable objects cannot be tested for equality. If you want to validate that
     * a subject's equality then see if the concrete class has an isEqualTo type method.
     * methods.
     */
    @Deprecated
    @Override
    public final boolean equals(Object obj) {
        throw new UnsupportedOperationException("This class cannot be treated as a value and does not support the Object equals method");
    }

    /**
     * @throws UnsupportedOperationException always.
     * @deprecated verifiable objects cannot be hashed.
     */
    @Deprecated
    @Override
    public final int hashCode() {
        throw new UnsupportedOperationException("This class cannot be treated as a value and does not support the Object hashCode method");
    }
}
