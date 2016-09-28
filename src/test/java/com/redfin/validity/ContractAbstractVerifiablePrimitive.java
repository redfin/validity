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

import org.junit.jupiter.api.Test;

/**
 * Contract to be implemented by tests for classes inheriting from
 * the {@link AbstractVerifiablePrimitive} class.
 *
 * @param <T> the type being tested. Must be a subclass of {@link AbstractVerifiablePrimitive}.
 */
interface ContractAbstractVerifiablePrimitive<T extends AbstractVerifiablePrimitive> extends ContractNotValueType<T> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test Values & Helpers
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Method for implementing class to allow for inheriting tests.
     *
     * @param description             the String description for the abstract verifiable primitive.
     * @param failedValidationHandler the {@link FailedValidationHandler} to use for the abstract verifiable primitive.
     * @return the abstract verifiable primitive with the given description
     * and failed validation handler.
     */
    T getAbstractVerifiablePrimitiveInstance(String description, FailedValidationHandler<?> failedValidationHandler);

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test Cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    default void testAbstractVerifiablePrimtitiveConstructorThrowsExceptionForNullFailedValidationHandler() {

    }

    @Test
    default void testAbstractVerifiablePrimtiveCanInstantiateWithNullDescription() {

    }

    @Test
    default void testCanAbstractVerifiablePrimitiveCanInstantiateWithValidArguments() {

    }

    // todo
}
