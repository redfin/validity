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
import java.util.function.Supplier;

/**
 * An implementation of the {@link FailedValidationExecutor} interface. This implementation
 * will throw {@link Throwable}s of type X when the {@link #fail(String, Object, Supplier)} method
 * is called that have the Validity library stack frames removed so that the first line of
 * the stack trace is the line the actual validation call. The rest of the stack trace is
 * left alone.
 *
 * @param <X> the type of {@link Throwable} the {@link #fail(String, Object, Supplier)} method
 *            will throw.
 */
public final class DefaultValidityFailedValidationExecutor<X extends Throwable>
        implements FailedValidationExecutor<X> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Constants
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private static final String DEFAULT_MESSAGE = "Subject failed validation";
    private static final String MESSAGE_FORMAT = "%s\n    expected : %s\n     subject : <%s>";
    private static final String PACKAGE_NAME = DefaultValidityFailedValidationExecutor.class.getPackage().getName() + ".";

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Members
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private final Function<String, X> throwableFunction;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Instance Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Create a new {@link DefaultValidityFailedValidationExecutor} instance with
     * the given throwableFunction.
     *
     * @param throwableFunction the function that takes in a String and returns a throwable
     *                          of the correct type.
     *                          May not be null.
     *                          Should never return a null throwable.
     *
     * @throws NullPointerException if throwableFunction is null or if throwableFunction ever
     *                              returns a null {@link Throwable}.
     */
    public DefaultValidityFailedValidationExecutor(Function<String, X> throwableFunction) {
        if (null == throwableFunction) {
            throw new NullPointerException(ValidityUtils.nullArgumentMessage("throwableFunction"));
        }
        this.throwableFunction = throwableFunction;
    }

    @Override
    public <T> void fail(String expected,
                         T subject,
                         Supplier<String> messageSupplier) throws X {
        if (null == expected) {
            throw new NullPointerException(ValidityUtils.nullArgumentMessage("expected"));
        }
        if (null == messageSupplier) {
            throw new NullPointerException(ValidityUtils.nullArgumentMessage("messageSupplier"));
        }
        String subjectDescription = ValidityUtils.describe(subject);
        String message = messageSupplier.get();
        if (null == message) {
            message = DEFAULT_MESSAGE;
        }
        // Create the throwable
        X throwable = throwableFunction.apply(String.format(MESSAGE_FORMAT, message, expected, subjectDescription));
        if (null == throwable) {
            throw new NullPointerException(ValidityUtils.nullThrowableFromFunction());
        }
        // Trim the validity frames from the stack trace and throw
        StackTraceElement[] elements = throwable.getStackTrace();
        if (null != elements) {
            throwable.setStackTrace(Arrays.stream(elements)
                                          .filter(element -> !element.getClassName()
                                                                     .startsWith(PACKAGE_NAME))
                                          .toArray(StackTraceElement[]::new));
        }
        throw throwable;
    }
}
