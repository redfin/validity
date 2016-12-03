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
 * {@link FailedValidationExecutor} implementations.
 */
public final class FailedValidationExecutors {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Constants
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private static final String DEFAULT_MESSAGE = "Subject failed validation";
    private static final String MESSAGE_FORMAT = "%s\n    expected : %s\n     subject : <%s>";
    private static final String PACKAGE_NAME = FailedValidationExecutors.class.getPackage().getName() + ".";

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Static Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Create a new {@link FailedValidationExecutor} that, when given valid input, throws
     * a {@link Throwable} of type (X) with the default message format of the Validity
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
     * @param throwableFunction the {@link Function} that takes in a String message and returns
     *                          a {@link Throwable} of type X.
     *                          May not be null or return null for any message (including null).
     * @param <X>               the type of the Throwable created by throwableFunction.
     * @return a new {@link FailedValidationExecutor} instance.
     * @throws NullPointerException if throwableFunction is null.
     */
    public static <X extends Throwable> FailedValidationExecutor<X> getDefaultFailureExecutor(Function<String, X> throwableFunction) {
        if (null == throwableFunction) {
            throw new NullPointerException(ValidityUtils.nullArgumentMessage("throwableFunction"));
        }
        return new FailedValidationExecutor<X>() {
            @Override
            public <T> void fail(String expected, T subject, String message) throws X {
                // Validate the arguments
                if (null == expected) {
                    throw new NullPointerException(ValidityUtils.nullArgumentMessage("expected"));
                }
                String subjectDescription = ValidityUtils.describe(subject);
                if (null == message) {
                    message = DEFAULT_MESSAGE;
                }
                // Create the throwable
                X throwable = throwableFunction.apply(String.format(MESSAGE_FORMAT, message, expected, subjectDescription));
                if (null == throwable) {
                    throw new NullPointerException(ValidityUtils.nullThrowableFromFunction());
                }
                // Trim the stack trace and throw
                StackTraceElement[] elements = throwable.getStackTrace();
                if (null != elements) {
                    throwable.setStackTrace(Arrays.stream(elements)
                                                  .filter(element -> !element.getClassName()
                                                                             .startsWith(PACKAGE_NAME))
                                                  .toArray(StackTraceElement[]::new));
                }
                throw throwable;
            }
        };
    }

    /**
     * Create a new {@link FailedValidationExecutor} that, when given valid input, throws
     * a {@link Throwable} of type (X) with the default message format of the Validity
     * framework. The exception will have the initial stack trace elements removed from
     * it's stack trace so that the first line in the call stack is the caller of the validation.<br>
     * <br>
     * Note that it does the caller discovery via package name, so the caller is the first line
     * in the stack trace that does not start with "com.redfi.validity.".
     * <br>
     * If the given throwableFunction ever returns a null throwable object, the Validity library
     * will throw a NullPointerException when it detects that.
     *
     * @param throwableFunction the {@link Function} that takes in a String message and returns
     *                          a {@link Throwable} of type X.
     *                          May not be null or return null for any message (including null).
     * @param <X>               the type of the Throwable created by throwableFunction.
     * @return a new {@link FailedValidationExecutor} instance.
     * @throws NullPointerException if throwableFunction is null.
     */
    public static <X extends Throwable> FailedValidationExecutor<X> getStackTrimmingFailureExecutor(Function<String, X> throwableFunction) {
        if (null == throwableFunction) {
            throw new NullPointerException(ValidityUtils.nullArgumentMessage("throwableFunction"));
        }
        return new FailedValidationExecutor<X>() {
            @Override
            public <T> void fail(String expected, T subject, String message) throws X {
                // Validate the arguments
                if (null == expected) {
                    throw new NullPointerException(ValidityUtils.nullArgumentMessage("expected"));
                }
                String subjectDescription = ValidityUtils.describe(subject);
                if (null == message) {
                    message = DEFAULT_MESSAGE;
                }
                // Create the throwable
                X throwable = throwableFunction.apply(String.format(MESSAGE_FORMAT, message, expected, subjectDescription));
                if (null == throwable) {
                    throw new NullPointerException(ValidityUtils.nullThrowableFromFunction());
                }
                // Trim the stack trace and throw
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
                StackTraceElement[] newStackTrace;
                newStackTrace = (null == caller) ? new StackTraceElement[0] : new StackTraceElement[] {caller};
                throwable.setStackTrace(newStackTrace);
                throw throwable;
            }
        };
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Instance Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * @throws AssertionError always.
     */
    private FailedValidationExecutors() {
        throw new AssertionError(ValidityUtils.nonInstantiableMessage());
    }
}
