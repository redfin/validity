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
public abstract class AbstractVerifiableObject<T, X extends Throwable> {

    private final T actual;
    private final String description;
    private final FailedValidationHandler<X> failedValidationHandler;

    public AbstractVerifiableObject(T actual, String description, FailedValidationHandler<X> failedValidationHandler) {
        if (null == failedValidationHandler) {
            throw new NullPointerException(Messages.nullArgumentMessage("failedValidationHandler"));
        }
        this.actual = actual;
        this.description = description;
        this.failedValidationHandler = failedValidationHandler;
    }

    protected T getActual() {
        return actual;
    }

    protected String describeType(T t) {
        return Messages.describe(t);
    }

    protected X fail(String expected) {
        X throwable = failedValidationHandler.buildThrowable(description, expected, describeType(actual));
        if (null == throwable) {
            throw new NullPointerException("A null throwable was returned from the FailedValidationHandler");
        }
        return throwable;
    }

    public T isNull() throws X {
        if (null == actual) {
            return null;
        }
        throw fail("t -> null == t");
    }

    public T isNotNull() throws X {
        if (null != actual) {
            return actual;
        }
        throw fail("t -> null != t");
    }

    public T isEqualTo(T other) throws X {
        if (null != actual && !actual.equals(other)) {
            return actual;
        }
        throw fail("t -> t.equals(" + Messages.describe(other) + ")");
    }

    public T isNotEqualTo(T other) throws X {
        if (null != actual && actual.equals(other)) {
            return actual;
        }
        throw fail("t -> !t.equals(" + Messages.describe(other) + ")");
    }

    public T satisfies(Predicate<T> expected) throws X {
        if (null == expected) {
            throw new NullPointerException(Messages.nullArgumentMessage("expected"));
        }
        if (expected.test(actual)) {
            return actual;
        }
        throw fail(Messages.buildFailSatisfiesMessage(expected));
    }
}
