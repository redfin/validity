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

    protected final void fail(String expected) throws X {
        X throwable = failedValidationHandler.buildThrowable(description, expected, describeType(actual));
        if (null == throwable) {
            throw new NullPointerException("A null throwable was returned from the FailedValidationHandler");
        }
        throw throwable;
    }

    public T isNull() throws X {
        if (null != actual) {
            fail("t -> null == t");
        }
        return null;
    }

    public T isNotNull() throws X {
        if (null == actual) {
            fail("t -> null != t");
        }
        return actual;
    }

    public T isEqualTo(T other) throws X {
        if (null == actual || !actual.equals(other)) {
            fail("t -> t.equals(" + Messages.describe(other) + ")");
        }
        return actual;
    }

    public T isNotEqualTo(T other) throws X {
        if (null == actual || actual.equals(other)) {
            fail("t -> !t.equals(" + Messages.describe(other) + ")");
        }
        return actual;
    }

    public T satisfies(Predicate<T> expected) throws X {
        if (null == expected) {
            throw new NullPointerException(Messages.nullArgumentMessage("expected"));
        }
        if (!expected.test(actual)) {
            fail(Messages.describePredicate(expected));
        }
        return actual;
    }

    /**
     * @throws UnsupportedOperationException always
     * @deprecated use {@link #isEqualTo(Object)} instead to test a verifiable for equality.
     */
    @Deprecated
    @Override
    public final boolean equals(Object obj) {
        throw new UnsupportedOperationException("A verifiable instance does not support equality. Use .isEqualTo instead.");
    }

    /**
     * @throws UnsupportedOperationException always
     * @deprecated {@link Object#hashCode()} is not supported for verifiable objects.
     */
    @Deprecated
    @Override
    public final int hashCode() {
        throw new UnsupportedOperationException("A verifiable instance does not support hash code creation.");
    }
}
