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

import com.redfin.validity.ValidityUtils;
import com.redfin.validity.FailedValidationHandler;
import com.redfin.validity.verifiable.AbstractVerifiableObject;
import java.util.Arrays;

/**
 * todo
 *
 * @param <X>
 */
public final class VerifiablePrimitiveLongArray<X extends Throwable> extends AbstractVerifiableObject<long[], X> {

    public VerifiablePrimitiveLongArray(long[] actual, String description, FailedValidationHandler<X> failedValidationHandler) {
        super(actual, description, failedValidationHandler);
    }

    @Override
    public long[] isEqualTo(long[] other) throws X {
        long[] actual = getActual();
        if (null == actual || !Arrays.equals(actual, other)) {
            fail("t -> Arrays.equals(t, " + ValidityUtils.describe(other) + ")");
        }
        return actual;
    }

    @Override
    public long[] isNotEqualTo(long[] other) throws X {
        long[] actual = getActual();
        if (null == actual || Arrays.equals(actual, other)) {
            fail("t -> !Arrays.equals(t, " + ValidityUtils.describe(other) + ")");
        }
        return actual;
    }

    public long[] isEmpty() throws X {
        long[] actual = getActual();
        if (null == actual || actual.length != 0) {
            fail("t -> t.length == 0");
        }
        return actual;
    }

    public long[] isNotEmpty() throws X {
        long[] actual = getActual();
        if (null == actual || actual.length <= 0) {
            fail("t -> t.length > 0");
        }
        return actual;
    }

    public long[] hasLengthOf(int length) throws X {
        long[] actual = getActual();
        if (null == actual || actual.length != length) {
            fail("t -> t.length == " + ValidityUtils.describe(length));
        }
        return actual;
    }

    public long[] hasLengthOfAtLeast(int length) throws X {
        long[] actual = getActual();
        if (null == actual || actual.length < length) {
            fail("t -> t.length >= " + ValidityUtils.describe(length));
        }
        return actual;
    }

    public long[] hasLengthOfAtMost(int length) throws X {
        long[] actual = getActual();
        if (null == actual || actual.length > length) {
            fail("t -> t.length <= " + ValidityUtils.describe(length));
        }
        return actual;
    }

    public long[] contains(long value) throws X {
        long[] actual = getActual();
        if (!containsHelper(value)) {
            fail("t -> t.contains(" + ValidityUtils.describe(value) + ")");
        }
        return actual;
    }

    public long[] doesNotContain(long value) throws X {
        long[] actual = getActual();
        if (containsHelper(value)) {
            fail("t -> !t.contains(" + ValidityUtils.describe(value) + ")");
        }
        return actual;
    }

    private boolean containsHelper(long value) {
        long[] actual = getActual();
        if (null == actual) {
            return false;
        }
        for (long next : actual) {
            if (next == value) {
                return true;
            }
        }
        return false;
    }
}
