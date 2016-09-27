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
        if (actual == value) {
            return actual;
        }
        throw fail("t -> t == " + Messages.describe(value));
    }

    public long isNotEqualTo(long value) throws X {
        if (actual != value) {
            return actual;
        }
        throw fail("t -> t != " + Messages.describe(value));
    }

    public long isStrictlyPositive() throws X {
        return isGreaterThan(0);
    }

    public long isStrictlyNegative() throws X {
        return isLessThan(0);
    }

    public long isGreaterThan(long value) throws X {
        if (actual > value) {
            return actual;
        }
        throw fail("t -> t > " + Messages.describe(value));
    }

    public long isGreaterThanOrEqualTo(long value) throws X {
        if (actual >= value) {
            return actual;
        }
        throw fail("t -> t >= " + Messages.describe(value));
    }

    public long isAtLeast(long value) throws X {
        return isGreaterThanOrEqualTo(value);
    }

    public long isLessThan(long value) throws X {
        if (actual < value) {
            return actual;
        }
        throw fail("t -> t < " + Messages.describe(value));
    }

    public long isLessThanOrEqualTo(long value) throws X {
        if (actual <= value) {
            return actual;
        }
        throw fail("t -> t <= " + Messages.describe(value));
    }

    public long isAtMost(long value) throws X {
        return isLessThanOrEqualTo(value);
    }

    public long satisfies(LongPredicate expected) throws X {
        if (null == expected) {
            throw new NullPointerException(Messages.nullArgumentMessage("expected"));
        }
        if (expected.test(actual)) {
            return actual;
        }
        throw fail(Messages.buildFailSatisfiesMessage(expected));
    }

    private X fail(String expected) {
        return fail(expected, Messages.describe(actual));
    }
}
