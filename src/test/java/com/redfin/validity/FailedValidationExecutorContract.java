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
import org.junit.jupiter.api.Test;

/**
 * A test contract that is applied to all implementations of the
 * {@link FailedValidationExecutor} interface in the Validity library.
 *
 * @param <X> the type of throwable from the validation executor being tested.
 */
interface FailedValidationExecutorContract<X extends Throwable> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test contract requirements
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * @return the actual failed validation executor being tested.
     */
    FailedValidationExecutor<X> getFailedValidationExecutor();

    /**
     * @return the class object of the throwable type thrown by the executor.
     */
    Class<X> getThrowableClass();

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    default void testValidationExecutorIsNotNull() {
        Assertions.assertNotNull(getFailedValidationExecutor(),
                                 "The FailedValidationExecutor returned should never be null.");
    }

    @Test
    default void testValidationExecutorThrowsExpectedExceptionForNullExpected() {
        NullPointerException exception = Assertions.assertThrows(NullPointerException.class,
                                                                 () -> getFailedValidationExecutor().fail(null, "subject", () -> "message"));
        Assertions.assertEquals(ValidityUtils.nullArgumentMessage("expected"),
                                exception.getMessage(),
                                "FailedValidationExecutor should throw NullPointerException for null expected.");
    }

    @Test
    default void testValidationExecutorThrowsExpectedExceptionForNullSubject() {
        Assertions.assertThrows(getThrowableClass(),
                                () -> getFailedValidationExecutor().fail("expected", null, () -> "message"));
    }

    @Test
    default void testValidationExecutorThrowsExpectedExceptionForNullMessageSupplier() {
        Assertions.assertThrows(NullPointerException.class,
                                () -> getFailedValidationExecutor().fail("expected", "subject", null));
    }

    @Test
    default void testValidationExecutorThrowsExpectedExceptionForNullMessageFromSupplier() {
        Assertions.assertThrows(getThrowableClass(),
                                () -> getFailedValidationExecutor().fail("expected", "subject", () -> null));
    }

    @Test
    default void testValidationExecutorThrowsExpectedExceptionWithValidArguments() {
        Assertions.assertThrows(getThrowableClass(),
                                () -> getFailedValidationExecutor().fail("expected", "subject", () -> "message"));
    }
}
