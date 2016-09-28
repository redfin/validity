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
 * todo
 *
 * @param <X>
 */
public abstract class AbstractVerifiablePrimitive<X extends Throwable> {

    private final String description;
    private final FailedValidationHandler<X> failedValidationHandler;

    public AbstractVerifiablePrimitive(String description, FailedValidationHandler<X> failedValidationHandler) {
        if (null == failedValidationHandler) {
            throw new NullPointerException(Messages.nullArgumentMessage("failedValidationHandler"));
        }
        this.description = description;
        this.failedValidationHandler = failedValidationHandler;
    }

    protected final X fail(String expected, String actual) {
        X throwable = failedValidationHandler.buildThrowable(description, expected, actual);
        if (null == throwable) {
            throw new NullPointerException("A null throwable was returned from the FailedValidationHandler");
        }
        return throwable;
    }

    /**
     * @throws UnsupportedOperationException always
     * @deprecated {@link Object#equals(Object)} is not supported for verifiable primitive objects.
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
