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
public final class VerifiablePrimitiveBoolean<X extends Throwable> extends AbstractVerifiablePrimitive<X> {

    private final boolean actual;

    public VerifiablePrimitiveBoolean(boolean actual, String description, FailedValidationHandler<X> failedValidationHandler) {
        super(description, failedValidationHandler);
        this.actual = actual;
    }

    public boolean isTrue() throws X {
        if (actual) {
            return true;
        }
        throw fail("t -> t == true");
    }

    public boolean isFalse() throws X {
        if (!actual) {
            return false;
        }
        throw fail("t -> t == false");
    }

    private X fail(String expected) {
        return fail(expected, Messages.describe(actual));
    }
}
