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
public final class VerifiableString<X extends Throwable> extends AbstractVerifiableObject<String, X> {

    public VerifiableString(String actual, String description, FailedValidationHandler<X> failedValidationHandler) {
        super(actual, description, failedValidationHandler);
    }

    public String isEmpty() throws X {
        String actual = getActual();
        if (null != actual && actual.isEmpty()) {
            return actual;
        }
        throw fail("t -> t.isEmpty()");
    }

    public String isNotEmpty() throws X {
        String actual = getActual();
        if (null != actual && !actual.isEmpty()) {
            return actual;
        }
        throw fail("t -> !t.isEmpty()");
    }

    public String startsWith(String prefix) throws X {
        String actual = getActual();
        if (null != actual && actual.startsWith(prefix)) {
            return actual;
        }
        throw fail("t -> t.startsWith(" + Messages.describe(prefix) + ")");
    }

    public String doesNotStartWith(String prefix) throws X {
        String actual = getActual();
        if (null != actual && !actual.startsWith(prefix)) {
            return actual;
        }
        throw fail("t -> !t.startsWith(" + Messages.describe(prefix) + ")");
    }

    public String endsWith(String suffix) throws X {
        String actual = getActual();
        if (null != actual && actual.endsWith(suffix)) {
            return actual;
        }
        throw fail("t -> t.endsWith(" + Messages.describe(suffix) + ")");
    }

    public String doesNotEndWith(String suffix) throws X {
        String actual = getActual();
        if (null != actual && !actual.endsWith(suffix)) {
            return actual;
        }
        throw fail("t -> !t.endsWith(" + Messages.describe(suffix) + ")");
    }

    public String matches(String regex) throws X {
        String actual = getActual();
        if (null != actual && actual.matches(regex)) {
            return actual;
        }
        throw fail("t -> t.matches(" + Messages.describe(regex) + ")");
    }

    public String doesNotMatch(String regex) throws X {
        String actual = getActual();
        if (null != actual && !actual.matches(regex)) {
            return actual;
        }
        throw fail("t -> !t.matches(" + Messages.describe(regex) + ")");
    }
}
