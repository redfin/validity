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

package com.redfin.validity.verifiable.objects;

import com.redfin.validity.ValidityUtils;
import com.redfin.validity.FailedValidationHandler;
import java.util.function.Predicate;

/**
 * todo
 *
 * @param <X>
 */
public final class VerifiableCharacter<X extends Throwable> extends AbstractVerifiableComparable<Character, X> {

    public VerifiableCharacter(Character actual, String description, FailedValidationHandler<X> failedValidationHandler) {
        super(actual, description, failedValidationHandler);
    }

    public Character isEqualTo(Character c) throws X {
        Character actual = getActual();
        if (null == actual || null == c || actual.compareTo(c) != 0) {
            fail("t -> t == " + ValidityUtils.describe(c));
        }
        return actual;
    }

    public Character isNotEqualTo(Character c) throws X {
        Character actual = getActual();
        if (actual == c) {
            fail("t -> t != " + ValidityUtils.describe(c));
        }
        return actual;
    }

    public Character isUpperCase() throws X {
        Character actual = getActual();
        if (!Character.isUpperCase(actual)) {
            fail("t -> Character.isUpperCase(t)");
        }
        return actual;
    }

    public Character isLowerCase() throws X {
        Character actual = getActual();
        if (!Character.isLowerCase(actual)) {
            fail("t -> Character.isLowerCase(t)");
        }
        return actual;
    }

    public Character isAlphabetic() throws X {
        Character actual = getActual();
        if (!Character.isAlphabetic(actual)) {
            fail("t -> Character.isAlphabetic(t)");
        }
        return actual;
    }

    public Character isDigit() throws X {
        Character actual = getActual();
        if (!Character.isDigit(actual)) {
            fail("t -> Character.isDigit(t)");
        }
        return actual;
    }

    public Character satisfies(Predicate<Character> expected) throws X {
        Character actual = getActual();
        if (null == expected) {
            throw new NullPointerException(ValidityUtils.nullArgumentMessage("expected"));
        }
        if (!expected.test(actual)) {
            fail(ValidityUtils.describe(expected));
        }
        return actual;
    }
}
