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
public final class VerifiablePrimitiveChar<X extends Throwable> extends AbstractVerifiablePrimitive<X> {

    private final char actual;

    public VerifiablePrimitiveChar(char actual, String description, FailedValidationHandler<X> failedValidationHandler) {
        super(description, failedValidationHandler);
        this.actual = actual;
    }

    public char isEqualTo(char c) throws X {
        if (actual != c) {
            fail("t -> t == " + Messages.describe(c));
        }
        return actual;
    }

    public char isNotEqualTo(char c) throws X {
        if (actual == c) {
            fail("t -> t != " + Messages.describe(c));
        }
        return actual;
    }

    public char isUpperCase() throws X {
        if (!Character.isUpperCase(actual)) {
            fail("t -> Character.isUpperCase(t)");
        }
        return actual;
    }

    public char isLowerCase() throws X {
        if (!Character.isLowerCase(actual)) {
            fail("t -> Character.isLowerCase(t)");
        }
        return actual;
    }

    public char isAlphabetic() throws X {
        if (!Character.isAlphabetic(actual)) {
            fail("t -> Character.isAlphabetic(t)");
        }
        return actual;
    }

    public char isDigit() throws X {
        if (!Character.isDigit(actual)) {
            fail("t -> Character.isDigit(t)");
        }
        return actual;
    }

    public char satisfies(Predicate<Character> expected) throws X {
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
