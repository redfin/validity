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
public final class VerifiablePrimitiveLongArray<X extends Throwable> extends AbstractVerifiablePrimitiveArray<long[], X> {

    private final long[] actual;

    public VerifiablePrimitiveLongArray(long[] actual, String description, FailedValidationHandler<X> failedValidationHandler) {
        super(description, failedValidationHandler);
        this.actual = actual;
    }

    @Override
    protected long[] getActual() {
        return actual;
    }

    public long[] isEmpty() throws X {
        if (null != actual && actual.length == 0) {
            return actual;
        }
        throw fail("t -> t.length == 0");
    }

    public long[] isNotEmpty() throws X {
        if (null != actual && actual.length > 0) {
            return actual;
        }
        throw fail("t -> t.length > 0");
    }

    public long[] hasLengthOf(int length) throws X {
        if (null != actual && actual.length == length) {
            return actual;
        }
        throw fail("t -> t.length == " + Messages.describe(length));
    }

    public long[] hasLengthOfAtLeast(int length) throws X {
        if (null != actual && actual.length >= length) {
            return actual;
        }
        throw fail("t -> t.length >= " + Messages.describe(length));
    }

    public long[] hasLengthOfAtMost(int length) throws X {
        if (null != actual && actual.length <= length) {
            return actual;
        }
        throw fail("t -> t.length <= " + Messages.describe(length));
    }

    public long[] contains(long value) throws X {
        if (null != actual) {
            for (long next : actual) {
                if (next == value) {
                    return actual;
                }
            }
        }
        throw fail("t -> t.contains(" + Messages.describe(value) + ")");
    }

    public long[] doesNotContain(long value) throws X {
        if (null != actual) {
            for (long next : actual) {
                if (next == value) {
                    throw fail("t -> t.contains(" + Messages.describe(value) + ")");
                }
            }
        }
        return actual;
    }
}
