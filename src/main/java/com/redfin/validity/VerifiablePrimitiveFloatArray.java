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
public final class VerifiablePrimitiveFloatArray<X extends Throwable> extends AbstractVerifiablePrimitiveArray<float[], X> {

    private final float[] actual;

    public VerifiablePrimitiveFloatArray(float[] actual, String description, FailedValidationHandler<X> failedValidationHandler) {
        super(description, failedValidationHandler);
        this.actual = actual;
    }

    @Override
    protected float[] getActual() {
        return actual;
    }

    public float[] isEmpty() throws X {
        if (null != actual && actual.length == 0) {
            return actual;
        }
        throw fail("t -> t.length == 0");
    }

    public float[] isNotEmpty() throws X {
        if (null != actual && actual.length > 0) {
            return actual;
        }
        throw fail("t -> t.length > 0");
    }

    public float[] hasLengthOf(int length) throws X {
        if (null != actual && actual.length == length) {
            return actual;
        }
        throw fail("t -> t.length == " + Messages.describe(length));
    }

    public float[] hasLengthOfAtLeast(int length) throws X {
        if (null != actual && actual.length >= length) {
            return actual;
        }
        throw fail("t -> t.length >= " + Messages.describe(length));
    }

    public float[] hasLengthOfAtMost(int length) throws X {
        if (null != actual && actual.length <= length) {
            return actual;
        }
        throw fail("t -> t.length <= " + Messages.describe(length));
    }

    public float[] contains(float value) throws X {
        if (null != actual) {
            for (float next : actual) {
                if (Float.compare(next, value) == 0) {
                    return actual;
                }
            }
        }
        throw fail("t -> t.contains(" + Messages.describe(value) + ")");
    }

    public float[] doesNotContain(float value) throws X {
        if (null != actual) {
            for (float next : actual) {
                if (Float.compare(next, value) == 0) {
                    throw fail("t -> t.contains(" + Messages.describe(value) + ")");
                }
            }
        }
        return actual;
    }
}
