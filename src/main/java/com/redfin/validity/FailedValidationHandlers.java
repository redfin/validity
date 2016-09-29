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
 * A static class containing methods to create the built-in
 * {@link FailedValidationHandler} implementations.
 */
public final class FailedValidationHandlers {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Constants
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private static final String DEFAULT_DESCRIPTION = "Failed validation";
    private static final String MESSAGE_FORMAT = "%s\n\texpected : %s\n\t  actual : <%s>";
    private static final String PACKAGE_NAME = FailedValidationHandlers.class.getPackage().getName() + ".";

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Static Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Create a new {@link FailedValidationHandler} that, when given valid input, creates
     * an {@link IllegalArgumentException} with the default message format of the Validity
     * framework. The exception will have the initial stack trace elements removed from
     * it's stack trace so that the first line in the call stack is the caller of the validation.<br>
     * <br>
     * Note that it does the stack trace removal via package name, so if the caller were from
     * a package that starts with "com.redfin.validity." then that would be considered part of
     * the Validity library and be removed as well.
     *
     * @return a new {@link FailedValidationHandler} instance.
     */
    public static FailedValidationHandler<IllegalArgumentException> getDefaultValidationHandler() {
        return getDefaultValidationHandler(IllegalArgumentException::new);
    }

    /**
     * Create a new {@link FailedValidationHandler} that, when given valid input, creates
     * a {@link Throwable} with the default message format of the Validity
     * framework. The exception will have the initial stack trace elements removed from
     * it's stack trace so that the first line in the call stack is the caller of the validation.<br>
     * <br>
     * Note that it does the stack trace removal via package name, so if the caller were from
     * a package that starts with "com.redfin.validity." then that would be considered part of
     * the Validity library and be removed as well.<br>
     * <br>
     * If the given throwableFunction ever returns a null throwable object, the Validity library
     * will throw a NullPointerException when it detects that.
     *
     * @param throwableFunction the {@link Function} that takes in a String message and constructs
     *                          a {@link Throwable} of type X.
     * @param <X>               the type of the Throwable created by throwableFunction.
     * @return a new {@link FailedValidationHandler} instance.
     * @throws NullPointerException if throwableFunction is null.
     */
    public static <X extends Throwable> FailedValidationHandler<X> getDefaultValidationHandler(Function<String, X> throwableFunction) {
        if (null == throwableFunction) {
            throw new NullPointerException(ValidityUtils.nullArgumentMessage("throwableFunction"));
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

    /**
     * Create a new {@link FailedValidationHandler} that, when given valid input, creates
     * an {@link AssertionError} with the default message format of the Validity
     * framework. The handler will look through the created throwable's stack trace and find the
     * first frame that does not start with the library package name ("com.redfin.validity.").
     * That element will be set as the entirety of the stack trace and all others will be removed.
     * <br>
     * If the given throwableFunction ever returns a null throwable object, the Validity library
     * will throw a NullPointerException when it detects that.
     *
     * @return a new {@link FailedValidationHandler} instance.
     */
    public static FailedValidationHandler<AssertionError> getStackTrimmingValidationHandler() {
        return getStackTrimmingValidationHandler(AssertionError::new);
    }

    /**
     * Create a new {@link FailedValidationHandler} that, when given valid input, creates
     * a {@link Throwable} with the default message format of the Validity
     * framework. The handler will look through the created throwable's stack trace and find the
     * first frame that does not start with the library package name ("com.redfin.validity.").
     * That element will be set as the entirety of the stack trace and all others will be removed.
     * <br>
     * If the given throwableFunction ever returns a null throwable object, the Validity library
     * will throw a NullPointerException when it detects that.
     *
     * @param throwableFunction the {@link Function} that takes in a String message and constructs
     *                          a {@link Throwable} of type X.
     * @param <X>               the type of the Throwable created by throwableFunction.
     * @return a new {@link FailedValidationHandler} instance.
     * @throws NullPointerException if throwableFunction is null.
     */
    public static <X extends Throwable> FailedValidationHandler<X> getStackTrimmingValidationHandler(Function<String, X> throwableFunction) {
        if (null == throwableFunction) {
            throw new NullPointerException(ValidityUtils.nullArgumentMessage("throwableFunction"));
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

    // --------------------------------------------------------------
    // Private Methods
    // --------------------------------------------------------------

    private static String buildMessage(String description, String expected, String actual) {
        // Validate the inputs
        if (null == expected) {
            throw new NullPointerException(ValidityUtils.nullArgumentMessage("expected"));
        }
        if (null == actual) {
            throw new NullPointerException(ValidityUtils.nullArgumentMessage("actual"));
        }
        if (null == description) {
            description = DEFAULT_DESCRIPTION;
        }
        // Build and return the validation message
        return String.format(MESSAGE_FORMAT, description, expected, actual);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Private Constructor
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Force the class to be non-instantiable
     *
     * @throws AssertionError always.
     */
    private FailedValidationHandlers() {
        throw new AssertionError(ValidityUtils.nonInstantiableMessage());
    }
}
