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

/**
 * Base class for the verifiable objects for primitive types.
 *
 * @param <X> the type of throwable this instance will throw on validation failure.
 */
public abstract class AbstractVerifiablePrimitive<X extends Throwable> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Fields
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private final String description;
    private final FailedValidationHandler<X> failedValidationHandler;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Instance Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Create a new {@link AbstractVerifiablePrimitive} instance with the given description and
     * {@link FailedValidationHandler}.
     *
     * @param description             the String description that will be given to the failedValidationHandler
     *                                on validation failure.
     *                                May be null.
     * @param failedValidationHandler the {@link FailedValidationHandler} to be called on validation failure.
     *                                May not be null.
     * @throws NullPointerException if failedValidationHandler is null.
     */
    public AbstractVerifiablePrimitive(String description, FailedValidationHandler<X> failedValidationHandler) {
        if (null == failedValidationHandler) {
            throw new NullPointerException(Messages.nullArgumentMessage("failedValidationHandler"));
        }
        this.description = description;
        this.failedValidationHandler = failedValidationHandler;
    }

    /**
     * Creates the desired throwable instance from the {@link FailedValidationHandler} and
     * then throws it.
     *
     * @param expected the String description of the expected state.
     * @param actual   the String description of the actual object being tested.
     * @throws X always.
     */
    protected final void fail(String expected, String actual) throws X {
        X throwable = failedValidationHandler.buildThrowable(description, expected, actual);
        if (null == throwable) {
            throw new NullPointerException("A null throwable was returned from the FailedValidationHandler");
        }
        throw throwable;
    }

    /**
     * @throws UnsupportedOperationException always
     * @deprecated {@link Object#equals(Object)} is not supported for verifiable primitive objects. Check
     * if the verifiable primitive has an isEqualTo method.
     */
    @Deprecated
    @Override
    public final boolean equals(Object obj) {
        throw new UnsupportedOperationException("A verifiable instance does not support equality.");
    }

    /**
     * @throws UnsupportedOperationException always
     * @deprecated {@link Object#hashCode()} is not supported for verifiable primitive objects.
     */
    @Deprecated
    @Override
    public final int hashCode() {
        throw new UnsupportedOperationException("A verifiable instance does not support hash code creation.");
    }
}
