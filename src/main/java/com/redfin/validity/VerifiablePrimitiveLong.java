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

import java.util.function.LongPredicate;

/**
 * todo
 *
 * @param <X>
 */
public final class VerifiablePrimitiveLong<X extends Throwable> extends AbstractVerifiablePrimitive<X> {

    private final long actual;

    public VerifiablePrimitiveLong(long actual, String description, FailedValidationHandler<X> failedValidationHandler) {
        super(description, failedValidationHandler);
        this.actual = actual;
    }

    public long isZero() throws X {
        return isEqualTo(0);
    }

    public long isNotZero() throws X {
        return isNotEqualTo(0);
    }

    public long isEqualTo(long value) throws X {
        if (actual != value) {
            fail("t -> t == " + Descriptions.describe(value));
        }
        return actual;
    }

    public long isNotEqualTo(long value) throws X {
        if (actual == value) {
            fail("t -> t != " + Descriptions.describe(value));
        }
        return actual;
    }

    public long isStrictlyPositive() throws X {
        return isGreaterThan(0);
    }

    public long isStrictlyNegative() throws X {
        return isLessThan(0);
    }

    public long isGreaterThan(long value) throws X {
        if (actual <= value) {
            fail("t -> t > " + Descriptions.describe(value));
        }
        return actual;
    }

    public long isGreaterThanOrEqualTo(long value) throws X {
        if (actual < value) {
            fail("t -> t >= " + Descriptions.describe(value));
        }
        return actual;
    }

    public long isAtLeast(long value) throws X {
        return isGreaterThanOrEqualTo(value);
    }

    public long isLessThan(long value) throws X {
        if (actual >= value) {
            fail("t -> t < " + Descriptions.describe(value));
        }
        return actual;
    }

    public long isLessThanOrEqualTo(long value) throws X {
        if (actual > value) {
            fail("t -> t <= " + Descriptions.describe(value));
        }
        return actual;
    }

    public long isAtMost(long value) throws X {
        return isLessThanOrEqualTo(value);
    }

    public long satisfies(LongPredicate expected) throws X {
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
