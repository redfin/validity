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

import java.util.function.IntPredicate;

/**
 * todo
 *
 * @param <X>
 */
public final class VerifiablePrimitiveInteger<X extends Throwable> extends AbstractVerifiablePrimitive<X> {

    private final int actual;

    public VerifiablePrimitiveInteger(int actual, String description, FailedValidationHandler<X> failedValidationHandler) {
        super(description, failedValidationHandler);
        this.actual = actual;
    }

    public int isZero() throws X {
        return isEqualTo(0);
    }

    public int isNotZero() throws X {
        return isNotEqualTo(0);
    }

    public int isEqualTo(int value) throws X {
        if (actual != value) {
            fail("t -> t == " + Descriptions.describe(value));
        }
        return actual;
    }

    public int isNotEqualTo(int value) throws X {
        if (actual == value) {
            fail("t -> t != " + Descriptions.describe(value));
        }
        return actual;
    }

    public int isStrictlyPositive() throws X {
        return isGreaterThan(0);
    }

    public int isStrictlyNegative() throws X {
        return isLessThan(0);
    }

    public int isGreaterThan(int value) throws X {
        if (actual <= value) {
            fail("t -> t > " + Descriptions.describe(value));
        }
        return actual;
    }

    public int isGreaterThanOrEqualTo(int value) throws X {
        if (actual < value) {
            fail("t -> t >= " + Descriptions.describe(value));
        }
        return actual;
    }

    public int isAtLeast(int value) throws X {
        return isGreaterThanOrEqualTo(value);
    }

    public int isLessThan(int value) throws X {
        if (actual >= value) {
            fail("t -> t < " + Descriptions.describe(value));
        }
        return actual;
    }

    public int isLessThanOrEqualTo(int value) throws X {
        if (actual > value) {
            fail("t -> t <= " + Descriptions.describe(value));
        }
        return actual;
    }

    public int isAtMost(int value) throws X {
        return isLessThanOrEqualTo(value);
    }

    public int satisfies(IntPredicate expected) throws X {
        if (null == expected) {
            throw new NullPointerException(Descriptions.nullArgumentMessage("expected"));
        }
        if (!expected.test(actual)) {
            fail(Descriptions.describe(expected));
        }
        return actual;
    }

    private void fail(String expected) throws X {
        fail(expected, Descriptions.describe(actual));
    }
}
