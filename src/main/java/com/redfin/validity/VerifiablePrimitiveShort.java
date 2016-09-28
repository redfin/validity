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
public final class VerifiablePrimitiveShort<X extends Throwable> extends AbstractVerifiablePrimitive<X> {

    private final short actual;

    public VerifiablePrimitiveShort(short actual, String description, FailedValidationHandler<X> failedValidationHandler) {
        super(description, failedValidationHandler);
        this.actual = actual;
    }

    public short isZero() throws X {
        return isEqualTo((short) 0);
    }

    public short isNotZero() throws X {
        return isNotEqualTo((short) 0);
    }

    public short isEqualTo(short value) throws X {
        if (actual != value) {
            fail("t -> t == " + Messages.describe(value));
        }
        return actual;
    }

    public short isNotEqualTo(short value) throws X {
        if (actual == value) {
            fail("t -> t != " + Messages.describe(value));
        }
        return actual;
    }

    public short isStrictlyPositive() throws X {
        return isGreaterThan((short) 0);
    }

    public short isStrictlyNegative() throws X {
        return isLessThan((short) 0);
    }

    public short isGreaterThan(short value) throws X {
        if (actual <= value) {
            fail("t -> t > " + Messages.describe(value));
        }
        return actual;
    }

    public short isGreaterThanOrEqualTo(short value) throws X {
        if (actual < value) {
            fail("t -> t >= " + Messages.describe(value));
        }
        return actual;
    }

    public short isAtLeast(short value) throws X {
        return isGreaterThanOrEqualTo(value);
    }

    public short isLessThan(short value) throws X {
        if (actual >= value) {
            fail("t -> t < " + Messages.describe(value));
        }
        return actual;
    }

    public short isLessThanOrEqualTo(short value) throws X {
        if (actual > value) {
            fail("t -> t <= " + Messages.describe(value));
        }
        return actual;
    }

    public short isAtMost(short value) throws X {
        return isLessThanOrEqualTo(value);
    }

    public short satisfies(IntPredicate expected) throws X {
        if (null == expected) {
            throw new NullPointerException(Messages.nullArgumentMessage("expected"));
        }
        if (!expected.test(actual)) {
            fail(Messages.describePredicate(expected));
        }
        return actual;
    }

    private void fail(String expected) throws X {
        fail(expected, Messages.describe(actual));
    }
}
