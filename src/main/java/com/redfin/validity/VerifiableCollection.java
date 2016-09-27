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

import java.util.Collection;

/**
 * todo
 *
 * @param <E>
 * @param <T>
 * @param <X>
 */
public final class VerifiableCollection<E, T extends Collection<E>, X extends Throwable> extends AbstractVerifiableObject<T, X> {

    public VerifiableCollection(T actual, String description, FailedValidationHandler<X> failedValidationHandler) {
        super(actual, description, failedValidationHandler);
    }

    public T isEmpty() throws X {
        T actual = getActual();
        if (null != actual && actual.isEmpty()) {
            return actual;
        }
        throw fail("t -> t.isEmpty()");
    }

    public T isNotEmpty() throws X {
        T actual = getActual();
        if (null != actual && !actual.isEmpty()) {
            return actual;
        }
        throw fail("t -> !t.isEmpty()");
    }

    public T hasSizeOf(int length) throws X {
        T actual = getActual();
        if (null != actual && actual.size() == length) {
            return actual;
        }
        throw fail("t -> t.size() == " + length);
    }

    public T hasSizeOfAtLeast(int length) throws X {
        T actual = getActual();
        if (null != actual && actual.size() >= length) {
            return actual;
        }
        throw fail("t -> t.size() >= " + length);
    }

    public T hasSizeOfAtMost(int length) throws X {
        T actual = getActual();
        if (null != actual && actual.size() <= length) {
            return actual;
        }
        throw fail("t -> t.size() <= " + length);
    }

    public T contains(E e) throws X {
        T actual = getActual();
        if (null != actual && actual.contains(e)) {
            return actual;
        }
        throw fail("t -> t.contains(" + Messages.describe(e) + ")");
    }

    public T doesNotContain(E e) throws X {
        T actual = getActual();
        if (null != actual && !actual.contains(e)) {
            return actual;
        }
        throw fail("t -> !t.contains(" + Messages.describe(e) + ")");
    }
}
