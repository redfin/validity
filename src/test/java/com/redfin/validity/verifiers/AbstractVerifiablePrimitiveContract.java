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

package com.redfin.validity.verifiers;

import com.redfin.validity.FailedValidationExecutor;
import com.redfin.validity.NotValueTypeContract;
import com.redfin.validity.ValidityUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * A test contract that validates that the class under test is
 * maintaining the contract of its {@link AbstractVerifiablePrimitive} super class.
 *
 * @param <X> the throwable type of the class under test.
 * @param <T> the type of the class under test.
 */
public interface AbstractVerifiablePrimitiveContract<X extends Throwable, T extends AbstractVerifiablePrimitive<X>> extends NotValueTypeContract<T> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test contract requirements
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    FailedValidationExecutor<X> getAbstractVerifiablePrimitiveFailedValidationExecutor();

    /**
     * @param failedValidationExecutor the {@link FailedValidationExecutor} to use to instantiate the object.
     * @param message                  the String message for the object.
     * @return an instance of {@link AbstractVerifiablePrimitive}.
     */
    T getAbstractVerifiablePrimitive(FailedValidationExecutor<X> failedValidationExecutor, String message);

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    default void testCanInstantiateWithValidArguments() {
        Assertions.assertNotNull(getAbstractVerifiablePrimitive(getAbstractVerifiablePrimitiveFailedValidationExecutor(), "hello"),
                                 "Should be able to instantiate an abstract verifiable primitive with valid arguments.");
    }

    @Test
    default void testCanInstantiateWithNullMessage() {
        Assertions.assertNotNull(getAbstractVerifiablePrimitive(getAbstractVerifiablePrimitiveFailedValidationExecutor(), null),
                                 "Should be able to instantiate an abstract verifiable primitive with a null message.");
    }

    @Test
    default void testThrowsExpectedExceptionForNullFailedValidationExecutor() {
        NullPointerException exception = Assertions.assertThrows(NullPointerException.class,
                                                                 () -> getAbstractVerifiablePrimitive(null, "hello"));
        Assertions.assertEquals(ValidityUtils.nullArgumentMessage("failedValidationExecutor"),
                                exception.getMessage(),
                                "Should throw the expected exception when you try to construct an AbstractVerifiablePrimitive with a null validation executor.");
    }

    @Test
    default void testReturnsGivenFailedValidationExecutorInstance() {
        FailedValidationExecutor<X> executor = getAbstractVerifiablePrimitiveFailedValidationExecutor();
        Assertions.assertTrue(executor == getAbstractVerifiablePrimitive(executor, "hello").getFailedValidationExecutor(),
                              "AbstractVerifiablePrimitive should return the expected validation executor.");
    }

    @Test
    default void testReturnsGivenMessageInstance() {
        String message = "hello";
        Assertions.assertTrue(message .equals(getAbstractVerifiablePrimitive(getAbstractVerifiablePrimitiveFailedValidationExecutor(), message).getMessage()),
                              "AbstractVerifiablePrimitive should return the expected validation executor.");
    }
}
