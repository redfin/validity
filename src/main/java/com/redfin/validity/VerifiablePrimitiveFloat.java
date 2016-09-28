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

import java.util.function.DoublePredicate;

/**
 * todo
 *
 * @param <X>
 */
public final class VerifiablePrimitiveFloat<X extends Throwable> extends AbstractVerifiablePrimitive<X> {

    private final float actual;

    public VerifiablePrimitiveFloat(float actual, String description, FailedValidationHandler<X> failedValidationHandler) {
        super(description, failedValidationHandler);
        this.actual = actual;
    }

    public float isZero() throws X {
        return isEqualTo(0);
    }

    public float isNotZero() throws X {
        return isNotEqualTo(0);
    }

    public float isEqualTo(float value) throws X {
        if (Float.compare(actual, value) != 0) {
            fail("t -> t == " + Messages.describe(value));
        }
        return actual;
    }

    public float isNotEqualTo(float value) throws X {
        if (Float.compare(actual, value) == 0) {
            fail("t -> t != " + Messages.describe(value));
        }
        return actual;
    }

    public float isStrictlyPositive() throws X {
        return isGreaterThan(0);
    }

    public float isStrictlyNegative() throws X {
        return isLessThan(0);
    }

    public float isGreaterThan(float value) throws X {
        if (Float.compare(actual, value) <= 0) {
            fail("t -> t > " + Messages.describe(value));
        }
        return actual;
    }

    public float isGreaterThanOrEqualTo(float value) throws X {
        if (Float.compare(actual, value) < 0) {
            fail("t -> t >= " + Messages.describe(value));
        }
        return actual;
    }

    public float isAtLeast(float value) throws X {
        return isGreaterThanOrEqualTo(value);
    }

    public float isLessThan(float value) throws X {
        if (Float.compare(actual, value) >= 0) {
            fail("t -> t < " + Messages.describe(value));
        }
        return actual;
    }

    public float isLessThanOrEqualTo(float value) throws X {
        if (Float.compare(actual, value) > 0) {
            fail("t -> t <= " + Messages.describe(value));
        }
        return actual;
    }

    public float isAtMost(float value) throws X {
        return isLessThanOrEqualTo(value);
    }

    public float satisfies(DoublePredicate expected) throws X {
        if (null == expected) {
            throw new NullPointerException(Messages.nullArgumentMessage("expected"));
        }
        if (!expected.test(actual)) {
            fail(Messages.buildFailSatisfiesMessage(expected));
        }
        return actual;
    }

    private void fail(String expected) throws X {
        fail(expected, Messages.describe(actual));
    }
}
