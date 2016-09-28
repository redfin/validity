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
public final class VerifiablePrimitiveDouble<X extends Throwable> extends AbstractVerifiablePrimitive<X> {

    private final double actual;

    public VerifiablePrimitiveDouble(double actual, String description, FailedValidationHandler<X> failedValidationHandler) {
        super(description, failedValidationHandler);
        this.actual = actual;
    }

    public double isZero() throws X {
        return isEqualTo(0);
    }

    public double isNotZero() throws X {
        return isNotEqualTo(0);
    }

    public double isEqualTo(double value) throws X {
        if (Double.compare(actual, value) != 0) {
            fail("t -> t == " + Descriptions.describe(value));
        }
        return actual;
    }

    public double isNotEqualTo(double value) throws X {
        if (Double.compare(actual, value) == 0) {
            fail("t -> t != " + Descriptions.describe(value));
        }
        return actual;
    }

    public double isStrictlyPositive() throws X {
        return isGreaterThan(0);
    }

    public double isStrictlyNegative() throws X {
        return isLessThan(0);
    }

    public double isGreaterThan(double value) throws X {
        if (Double.compare(actual, value) <= 0) {
            fail("t -> t > " + Descriptions.describe(value));
        }
        return actual;
    }

    public double isGreaterThanOrEqualTo(double value) throws X {
        if (Double.compare(actual, value) < 0) {
            fail("t -> t >= " + Descriptions.describe(value));
        }
        return actual;
    }

    public double isAtLeast(double value) throws X {
        return isGreaterThanOrEqualTo(value);
    }

    public double isLessThan(double value) throws X {
        if (Double.compare(actual, value) >= 0) {
            fail("t -> t < " + Descriptions.describe(value));
        }
        return actual;
    }

    public double isLessThanOrEqualTo(double value) throws X {
        if (Double.compare(actual, value) > 0) {
            fail("t -> t <= " + Descriptions.describe(value));
        }
        return actual;
    }

    public double isAtMost(double value) throws X {
        return isLessThanOrEqualTo(value);
    }

    public double satisfies(DoublePredicate expected) throws X {
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
