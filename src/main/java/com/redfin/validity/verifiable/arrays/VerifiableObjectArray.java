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

package com.redfin.validity.verifiable.arrays;

import com.redfin.validity.verifiable.AbstractVerifiableObject;
import com.redfin.validity.ValidityUtils;
import com.redfin.validity.FailedValidationHandler;
import java.util.Arrays;

/**
 * todo
 *
 * @param <E>
 * @param <X>
 */
public final class VerifiableObjectArray<E, X extends Throwable> extends AbstractVerifiableObject<E[], X> {

    public VerifiableObjectArray(E[] actual, String description, FailedValidationHandler<X> failedValidationHandler) {
        super(actual, description, failedValidationHandler);
    }

    @Override
    public E[] isEqualTo(E[] other) throws X {
        E[] actual = getActual();
        if (null == actual || !Arrays.deepEquals(actual, other)) {
            fail("t -> Arrays.deepEquals(t, " + ValidityUtils.describe(other) + ")");
        }
        return actual;
    }

    @Override
    public E[] isNotEqualTo(E[] other) throws X {
        E[] actual = getActual();
        if (null == actual || Arrays.deepEquals(actual, other)) {
            fail("t -> !Arrays.deepEquals(t, " + ValidityUtils.describe(other) + ")");
        }
        return actual;
    }

    public E[] isEmpty() throws X {
        E[] actual = getActual();
        if (null == actual || actual.length != 0) {
            fail("t -> t.length == 0");
        }
        return actual;
    }

    public E[] isNotEmpty() throws X {
        E[] actual = getActual();
        if (null == actual || actual.length <= 0) {
            fail("t -> t.length > 0");
        }
        return actual;
    }

    public E[] hasLengthOf(int length) throws X {
        E[] actual = getActual();
        if (null == actual || actual.length != length) {
            fail("t -> t.length == " + length);
        }
        return actual;
    }

    public E[] hasLengthOfAtLeast(int length) throws X {
        E[] actual = getActual();
        if (null == actual || actual.length < length) {
            fail("t -> t.length >= " + length);
        }
        return actual;
    }

    public E[] hasLengthOfAtMost(int length) throws X {
        E[] actual = getActual();
        if (null == actual || actual.length > length) {
            fail("t -> t.length <= " + length);
        }
        return actual;
    }
}
