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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

final class DefaultValidityFailedValidationExecutorTest {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test values & contract implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    interface ValidationExecutorsMessageContract<X extends Throwable> extends FailedValidationExecutorContract<X> {

        String MESSAGE_FORMAT = "%s\n    expected : %s\n     subject : <%s>";

        @Test
        default void testThrownExceptionHasExpectedMessage() {
            String expected = "expected";
            String subject = "subject";
            String message = "message";
            X throwable = Assertions.assertThrows(getThrowableClass(),
                                                  () -> getFailedValidationExecutor().fail(expected, subject, () -> message));
            Assertions.assertEquals(String.format(MESSAGE_FORMAT,
                                                  message,
                                                  expected,
                                                  subject),
                                    throwable.getMessage(),
                                    "The failed validation executors for the validity library should have the expected message.");
        }

        @Test
        default void testThrownExceptionHasExpectedMessageWithNullMessage() {
            String expected = "expected";
            String subject = "subject";
            X throwable = Assertions.assertThrows(getThrowableClass(),
                                                  () -> getFailedValidationExecutor().fail(expected, subject, () -> null));
            Assertions.assertEquals(String.format(MESSAGE_FORMAT,
                                                  "Subject failed validation",
                                                  expected,
                                                  subject),
                                    throwable.getMessage(),
                                    "The failed validation executors for the validity library should have the expected message.");
        }
    }

    interface NoStackTraceValidationExecutorsContract<X extends Throwable> extends ValidationExecutorsMessageContract<X> {

        final class NoStackRuntimeException extends RuntimeException {
            NoStackRuntimeException(String message) {
                super(message);
            }

            @Override
            public StackTraceElement[] getStackTrace() {
                return null;
            }
        }

        FailedValidationExecutor<NoStackRuntimeException> getNoStackFailedValidationExecutor();

        @Test
        default void testValidationExecutorCanHandleEmptyStackTraceFromException() {
            String expected = "expected";
            String subject = "subject";
            String message = "message";
            Assertions.assertThrows(NoStackRuntimeException.class,
                                    () -> getNoStackFailedValidationExecutor().fail(expected, subject, () -> message));
        }
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test containers
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Nested
    final class DefaultValidityFailedValidationExecutorTestContainer implements NoStackTraceValidationExecutorsContract<IllegalStateException> {

        @Override
        public FailedValidationExecutor<IllegalStateException> getFailedValidationExecutor() {
            return new DefaultValidityFailedValidationExecutor<>(IllegalStateException::new);
        }

        @Override
        public Class<IllegalStateException> getThrowableClass() {
            return IllegalStateException.class;
        }

        @Override
        public FailedValidationExecutor<NoStackRuntimeException> getNoStackFailedValidationExecutor() {
            return new DefaultValidityFailedValidationExecutor<>(NoStackRuntimeException::new);
        }

        @Test
        void throwsExceptionForNullThrowableFunction() {
            NullPointerException exception = Assertions.assertThrows(NullPointerException.class,
                                                                     () -> new DefaultValidityFailedValidationExecutor<>(null));
            Assertions.assertEquals(ValidityUtils.nullArgumentMessage("throwableFunction"),
                                    exception.getMessage(),
                                    "Failed validation executors should throw an exception for a null throwable function.");
        }

        @Test
        void testFailedValidationExecutorThrowsExceptionForNullThrowableCreation() {
            NullPointerException exception = Assertions.assertThrows(NullPointerException.class,
                                                                     () -> new DefaultValidityFailedValidationExecutor<>(str -> null).fail("", "", () -> ""));
            Assertions.assertEquals(ValidityUtils.nullThrowableFromFunction(),
                                    exception.getMessage(),
                                    "Failed validation executors should throw the expected exception for a null throwable created.");
        }
    }
}
