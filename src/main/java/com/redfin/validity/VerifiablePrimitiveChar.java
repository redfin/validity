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
        if (actual == c) {
            return actual;
        }
        throw fail("t -> t == " + Messages.describe(c));
    }

    public char isNotEqualTo(char c) throws X {
        if (actual != c) {
            return actual;
        }
        throw fail("t -> t != " + Messages.describe(c));
    }

    public char isUpperCase() throws X {
        if (Character.isUpperCase(actual)) {
            return actual;
        }
        throw fail("t -> Character.isUpperCase(t)");
    }

    public char isLowerCase() throws X {
        if (Character.isLowerCase(actual)) {
            return actual;
        }
        throw fail("t -> Character.isLowerCase(t)");
    }

    public char isAlphabetic() throws X {
        if (Character.isAlphabetic(actual)) {
            return actual;
        }
        throw fail("t -> Character.isAlphabetic(t)");
    }

    public char isDigit() throws X {
        if (Character.isDigit(actual)) {
            return actual;
        }
        throw fail("t -> Character.isDigit(t)");
    }

    public char satisfies(Predicate<Character> expected) throws X {
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
