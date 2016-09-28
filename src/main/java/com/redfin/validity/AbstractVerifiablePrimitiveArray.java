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
 * todo
 *
 * @param <T>
 * @param <X>
 */
public abstract class AbstractVerifiablePrimitiveArray<T, X extends Throwable> extends AbstractVerifiablePrimitive<X> {

    public AbstractVerifiablePrimitiveArray(String description, FailedValidationHandler<X> failedValidationHandler) {
        super(description, failedValidationHandler);
    }

    protected abstract T getActual();

    public T isNull() throws X {
        T actual = getActual();
        if (null == actual) {
            return null;
        }
        throw fail("t -> null == t", Messages.describe(actual));
    }

    public T isNotNull() throws X {
        T actual = getActual();
        if (null != actual) {
            return actual;
        }
        throw fail("t -> null != t", Messages.describe(actual));
    }

    public T satisfies(Predicate<T> expected) throws X {
        if (null == expected) {
            throw new NullPointerException(Messages.nullArgumentMessage("expected"));
        }
        T actual = getActual();
        if (expected.test(actual)) {
            return actual;
        }
        String expectedString = Messages.describe(expected);
        if (expected instanceof AbstractDescriptivePredicate) {
            throw fail(expectedString);
        } else {
            throw fail("Unknown predicate: " + expectedString);
        }
    }

    protected X fail(String expected) {
        return fail(expected, Messages.describe(getActual()));
    }
}
