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
public final class VerifiablePrimitiveIntegerArray<X extends Throwable> extends AbstractVerifiablePrimitiveArray<int[], X> {

    private final int[] actual;

    public VerifiablePrimitiveIntegerArray(int[] actual, String description, FailedValidationHandler<X> failedValidationHandler) {
        super(description, failedValidationHandler);
        this.actual = actual;
    }

    @Override
    protected int[] getActual() {
        return actual;
    }

    public int[] isEmpty() throws X {
        if (null == actual || actual.length != 0) {
            fail("t -> t.length == 0");
        }
        return actual;
    }

    public int[] isNotEmpty() throws X {
        if (null == actual || actual.length <= 0) {
            fail("t -> t.length > 0");
        }
        return actual;
    }

    public int[] hasLengthOf(int length) throws X {
        if (null == actual || actual.length != length) {
            fail("t -> t.length == " + Messages.describe(length));
        }
        return actual;
    }

    public int[] hasLengthOfAtLeast(int length) throws X {
        if (null == actual || actual.length < length) {
            fail("t -> t.length >= " + Messages.describe(length));
        }
        return actual;
    }

    public int[] hasLengthOfAtMost(int length) throws X {
        if (null == actual || actual.length > length) {
            fail("t -> t.length <= " + Messages.describe(length));
        }
        return actual;
    }

    public int[] contains(int value) throws X {
        if (!containsHelper(value)) {
            fail("t -> t.contains(" + Messages.describe(value) + ")");
        }
        return actual;
    }

    public int[] doesNotContain(int value) throws X {
        if (containsHelper(value)) {
            fail("t -> !t.contains(" + Messages.describe(value) + ")");
        }
        return actual;
    }

    private boolean containsHelper(int value) {
        if (null == actual) {
            return false;
        }
        for (int next : actual) {
            if (next == value) {
                return true;
            }
        }
        return false;
    }
}
