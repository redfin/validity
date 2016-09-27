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
public class VerifiablePrimitiveBooleanArray<X extends Throwable> extends AbstractVerifiablePrimitiveArray<boolean[], X> {

    private final boolean[] actual;

    public VerifiablePrimitiveBooleanArray(boolean[] actual, String description, FailedValidationHandler<X> failedValidationHandler) {
        super(description, failedValidationHandler);
        this.actual = actual;
    }

    @Override
    protected boolean[] getActual() {
        return actual;
    }

    public boolean[] isEmpty() throws X {
        if (null != actual && actual.length == 0) {
            return actual;
        }
        throw fail("t -> t.length == 0");
    }

    public boolean[] isNotEmpty() throws X {
        if (null != actual && actual.length > 0) {
            return actual;
        }
        throw fail("t -> t.length > 0");
    }

    public boolean[] hasLengthOf(int length) throws X {
        if (null != actual && actual.length == length) {
            return actual;
        }
        throw fail("t -> t.length == " + Messages.describe(length));
    }

    public boolean[] hasLengthOfAtLeast(int length) throws X {
        if (null != actual && actual.length >= length) {
            return actual;
        }
        throw fail("t -> t.length >= " + Messages.describe(length));
    }

    public boolean[] hasLengthOfAtMost(int length) throws X {
        if (null != actual && actual.length <= length) {
            return actual;
        }
        throw fail("t -> t.length <= " + Messages.describe(length));
    }

    public boolean[] contains(boolean value) throws X {
        if (null != actual) {
            for (boolean next : actual) {
                if (next == value) {
                    return actual;
                }
            }
        }
        throw fail("t -> t.contains(" + Messages.describe(value) + ")");
    }

    public boolean[] doesNotContain(boolean value) throws X {
        if (null != actual) {
            for (boolean next : actual) {
                if (next == value) {
                    throw fail("t -> t.contains(" + Messages.describe(value) + ")");
                }
            }
        }
        return actual;
    }
}
