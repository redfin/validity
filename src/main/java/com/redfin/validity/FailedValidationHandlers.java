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

import java.util.Arrays;
import java.util.function.Function;

/**
 * todo
 */
final class FailedValidationHandlers {

    private static final String DEFAULT_DESCRIPTION = "Failed validation";
    private static final String DEFAULT_MESSAGE_FORMAT = "%s\n\texpected : %s\n\t  actual : <%s>";
    private static final String PACKAGE_NAME = FailedValidationHandlers.class.getPackage()
                                                                             .getName() + ".";

    private static String buildMessage(String description, String expected, String actual) {
        // Validate the inputs
        if (null == expected) {
            throw new NullPointerException(Messages.nullArgumentMessage("expected"));
        }
        if (null == actual) {
            throw new NullPointerException(Messages.nullArgumentMessage("actual"));
        }
        if (null == description) {
            description = DEFAULT_DESCRIPTION;
        }
        // Build and return the validation message
        return String.format(DEFAULT_MESSAGE_FORMAT, description, expected, actual);
    }

    static FailedValidationHandler<IllegalArgumentException> getDefaultValidationHandler() {
        return getDefaultValidationHandler(IllegalArgumentException::new);
    }

    static <X extends Throwable> FailedValidationHandler<X> getDefaultValidationHandler(Function<String, X> throwableFunction) {
        if (null == throwableFunction) {
            throw new NullPointerException(Messages.nullArgumentMessage("throwableFunction"));
        }
        return (String description, String expected, String actual) -> {
            // Create the throwable
            String message = buildMessage(description, expected, actual);
            X throwable = throwableFunction.apply(message);
            if (null == throwable) {
                throw new NullPointerException("Received a null throwable from the given throwable function");
            }
            // Trim out the validity library from the stack trace and throw it
            StackTraceElement[] elements = throwable.getStackTrace();
            if (null != elements) {
                throwable.setStackTrace(Arrays.stream(elements)
                                              .filter(element -> !element.getClassName()
                                                                         .startsWith(PACKAGE_NAME))
                                              .toArray(StackTraceElement[]::new));
            }
            return throwable;
        };
    }

    static FailedValidationHandler<AssertionError> getStackTrimmingValidationHandler() {
        return getStackTrimmingValidationHandler(AssertionError::new);
    }

    static <X extends Throwable> FailedValidationHandler<X> getStackTrimmingValidationHandler(Function<String, X> throwableFunction) {
        if (null == throwableFunction) {
            throw new NullPointerException(Messages.nullArgumentMessage("throwableFunction"));
        }
        return (String description, String expected, String actual) -> {
            // Create the throwable
            String message = buildMessage(description, expected, actual);
            X throwable = throwableFunction.apply(message);
            if (null == throwable) {
                throw new NullPointerException("Received a null throwable from the given throwable function");
            }
            // Trim out everything but the caller from the stack trace and throw it
            StackTraceElement[] elements = throwable.getStackTrace();
            StackTraceElement caller = null;
            if (null != elements) {
                for (StackTraceElement element : elements) {
                    if (!element.getClassName()
                                .startsWith(PACKAGE_NAME)) {
                        caller = element;
                        break;
                    }
                }
            }
            if (null == caller) {
                throwable.setStackTrace(new StackTraceElement[0]);
            } else {
                throwable.setStackTrace(new StackTraceElement[]{caller});
            }
            return throwable;
        };
    }

    private FailedValidationHandlers() {
        throw new AssertionError(Messages.nonInstantiableMessage());
    }
}
