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

import java.util.function.Predicate;

/**
 * Base class for the verifiable objects for arrays of primitive types.
 *
 * @param <T> the type of primitive array for this verifiable instance.
 * @param <X> the type of throwable this instance will throw on validation failure.
 */
public abstract class AbstractVerifiablePrimitiveArray<T, X extends Throwable> extends AbstractVerifiablePrimitive<X> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Instance Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Create a new {@link AbstractVerifiablePrimitiveArray} instance with the given description and
     * {@link FailedValidationHandler}.
     *
     * @param description             the String description that will be given to the failedValidationHandler
     *                                on validation failure.
     *                                May be null.
     * @param failedValidationHandler the {@link FailedValidationHandler} to be called on validation failure.
     *                                May not be null.
     * @throws NullPointerException if failedValidationHandler is null.
     */
    public AbstractVerifiablePrimitiveArray(String description, FailedValidationHandler<X> failedValidationHandler) {
        super(description, failedValidationHandler);
    }

    /**
     * @return the actual array test subject that is being verified.
     */
    protected abstract T getActual();

    /**
     * Verify the test subject is null. This method will
     * always either return null or throw X.
     *
     * @return null if the test subject is null.
     * @throws X if the test subject is not null.
     */
    public T isNull() throws X {
        T actual = getActual();
        if (null != actual) {
            fail("t -> null == t", Descriptions.describe(actual));
        }
        return null;
    }

    /**
     * Verify the actual instance is not null. This method will
     * either return the given object or throw X.
     *
     * @return the test subject is not null.
     * @throws X if the test subject is null.
     */
    public T isNotNull() throws X {
        T actual = getActual();
        if (null == actual) {
            fail("t -> null != t", Descriptions.describe((T) null));
        }
        return actual;
    }

    /**
     * Verify the test subject satisfies the given predicate. Note that if the
     * predicate test itself throws an exception, that will not be handled and the
     * validation library will not throw it's usual throwable.
     *
     * @param expected the {@link Predicate} to apply to the test subject.
     *                 May not be null.
     * @return the test subject if it satisfies the given predicate.
     * @throws X if the test subject does not satisfy the predicate.
     */
    public T satisfies(Predicate<T> expected) throws X {
        if (null == expected) {
            throw new NullPointerException(Descriptions.nullArgumentMessage("expected"));
        }
        T actual = getActual();
        if (!expected.test(actual)) {
            fail(Descriptions.describe(expected));
        }
        return actual;
    }

    /**
     * Creates the desired throwable instance from the {@link FailedValidationHandler} and
     * then throws it.
     *
     * @param expected the String description of the expected state.
     * @throws X always.
     */
    protected final void fail(String expected) throws X {
        fail(expected, Descriptions.describe(getActual()));
    }
}
