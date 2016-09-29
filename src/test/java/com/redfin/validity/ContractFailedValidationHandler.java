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
 * A contract to be implemented by test classes for testing failed validation handler
 * instances.
 */
interface ContractFailedValidationHandler {

    String VALID_DESCRIPTION = "Hello, world";
    String VALID_EXPECTED = "t -> null != t";
    String VALID_ACTUAL = "\"happy\"";

    /**
     * Method for implementing class to allow for inheriting tests.
     *
     * @return the FailedValidationHandler to be tested.
     */
    FailedValidationHandler<?> getFailedValidationHandler();

    @Test
    default void testFailedValidationHandlerReturnsNonNullThrowableForValidInputs() {
        Assertions.assertNotNull(getFailedValidationHandler().buildThrowable(VALID_DESCRIPTION, VALID_EXPECTED, VALID_ACTUAL),
                                 "A failed validation handler should not return null for non-null arguments.");
    }

    @Test
    default void testFailedValidationHandlerReturnsNonNullThrowableForNullDescription() {
        Assertions.assertNotNull(getFailedValidationHandler().buildThrowable(null, VALID_EXPECTED, VALID_ACTUAL),
                                 "A failed validation handler should not return null for non-null arguments.");
    }

    @Test
    default void testFailedValidationHandlerThrowsExceptionForNullExpected() {
        NullPointerException exception = Assertions.expectThrows(NullPointerException.class, () -> getFailedValidationHandler().buildThrowable(VALID_DESCRIPTION, null, VALID_ACTUAL));
        Assertions.assertEquals(ValidityUtils.nullArgumentMessage("expected"),
                                exception.getMessage(),
                                "A failed validation handler should throw the expected null pointer exception for a null expected.");
    }

    @Test
    default void testFailedValidationHandlerThrowsExceptionForNullActual() {
        NullPointerException exception = Assertions.expectThrows(NullPointerException.class, () -> getFailedValidationHandler().buildThrowable(VALID_DESCRIPTION, VALID_EXPECTED, null));
        Assertions.assertEquals(ValidityUtils.nullArgumentMessage("actual"),
                                exception.getMessage(),
                                "A failed validation handler should throw the expected null pointer exception for a null actual.");
    }

    @Test
    default void testFailedValidationHandlerThrowsExceptionForNullActualAndExpected() {
        NullPointerException exception = Assertions.expectThrows(NullPointerException.class, () -> getFailedValidationHandler().buildThrowable(VALID_DESCRIPTION, null, null));
        Assertions.assertEquals(ValidityUtils.nullArgumentMessage("expected"),
                                exception.getMessage(),
                                "A failed validation handler should throw the expected null pointer exception for a null actual and expected.");
    }
}
