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

import java.util.function.Predicate;

/**
 * todo
 *
 * @param <X>
 */
public final class VerifiablePrimitiveByte<X extends Throwable> extends AbstractVerifiablePrimitive<X> {

    private final byte actual;

    public VerifiablePrimitiveByte(byte actual, String description, FailedValidationHandler<X> failedValidationHandler) {
        super(description, failedValidationHandler);
        this.actual = actual;
    }

    public byte isEqualTo(byte value) throws X {
        if (Byte.compare(actual, value) == 0) {
            return actual;
        }
        throw fail("t -> Byte.compare(t, " + Messages.describe(value) + ") == 0");
    }

    public byte isNotEqualTo(byte value) throws X {
        if (Byte.compare(actual, value) != 0) {
            return actual;
        }
        throw fail("t -> Byte.compare(t, " + Messages.describe(value) + ") != 0");
    }

    public byte satisfies(Predicate<Byte> expected) throws X {
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
