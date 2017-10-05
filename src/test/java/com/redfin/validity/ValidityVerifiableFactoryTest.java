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

import java.util.function.Supplier;

final class ValidityVerifiableFactoryTest
 implements AbstractVerifiableFactoryContract<IllegalArgumentException,
                                              ValidityVerifiableFactory> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test requirements
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private static final Supplier<String> VALID_MESSAGE_SUPPLIER = () -> "hello";

    private static final FailedValidationExecutor<IllegalArgumentException> VALIDATION_EXECUTOR = new FailedValidationExecutor<IllegalArgumentException>() {
        @Override
        public <T> void fail(String expected, T subject, Supplier<String> messageSupplier) throws IllegalArgumentException {
            throw new IllegalArgumentException(expected + subject + messageSupplier.get());
        }
    };

    private ValidityVerifiableFactory getInstance() {
        return new ValidityVerifiableFactory(VALID_MESSAGE_SUPPLIER, VALIDATION_EXECUTOR);
    }

    @Override
    public AbstractVerifiableFactory<IllegalArgumentException, ValidityVerifiableFactory> getNotValueTypeInstance() {
        return getInstance();
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    void testCanInstantiateWithValidArguments() {
        try {
            getInstance();
        } catch (Throwable thrown) {
            throw new RuntimeException("Should be able to instantiate a ValidityVerifiableFactory with valid arguments", thrown);
        }
    }

    @Test
    void testCanInstantiateWithNullMessage() {
        try {
            new ValidityVerifiableFactory(() ->null, VALIDATION_EXECUTOR);
        } catch (Throwable thrown) {
            throw new RuntimeException("Should be able to instantiate a ValidityVerifiableFactory with a null message", thrown);
        }
    }

    @Test
    void testConstructorThrowsExceptionForNullMessageSupplier() {
        NullPointerException exception = Assertions.assertThrows(NullPointerException.class,
                                                                 () -> new ValidityVerifiableFactory(null, VALIDATION_EXECUTOR));
        Assertions.assertEquals(ValidityUtils.nullArgumentMessage("messageSupplier"),
                                exception.getMessage(),
                                "Should not be able to instantiate a ValidityVerifiableFactory with a null message supplier.");
    }

    @Test
    void testConstructorThrowsExceptionForNullValidationExecutor() {
        NullPointerException exception = Assertions.assertThrows(NullPointerException.class,
                                                                 () -> new ValidityVerifiableFactory(VALID_MESSAGE_SUPPLIER, null));
        Assertions.assertEquals(ValidityUtils.nullArgumentMessage("failedValidationExecutor"),
                                exception.getMessage(),
                                "Should not be able to instantiate a ValidityVerifiableFactory with a null failed validation executor.");
    }

    @Test
    void testReturnsGivenMessage() {
        Assertions.assertTrue( VALID_MESSAGE_SUPPLIER.get().equals(getInstance().getMessageSupplier().get()),
                               "A verifiable factory should return the same string message instance it is given");
    }

    @Test
    void testReturnsGivenExecutor() {
        Assertions.assertTrue( VALIDATION_EXECUTOR.equals(getInstance().getFailedValidationExecutor()),
                               "A verifiable factory should return the same FailedValidationExecutor instance it is given");
    }

    @Test
    void testGetFactoryReturnsNonNUllInstance() {
        Assertions.assertNotNull(getInstance().getFactory(VALID_MESSAGE_SUPPLIER, VALIDATION_EXECUTOR),
                                 "The getFactory method of the verifiable factory should return a non-null instance.");
    }

    @Test
    void testGetFactoryReturnsGivenMessage() {
        Assertions.assertTrue( VALID_MESSAGE_SUPPLIER.get().equals(getInstance().getFactory(VALID_MESSAGE_SUPPLIER, VALIDATION_EXECUTOR)
                                                                                .getMessageSupplier()
                                                                                .get()),
                               "A verifiable factory should return the same string message instance it is given");
    }

    @Test
    void testGetFactoryReturnsGivenExecutor() {
        Assertions.assertTrue( VALIDATION_EXECUTOR.equals(getInstance().getFactory(VALID_MESSAGE_SUPPLIER, VALIDATION_EXECUTOR)
                                                                       .getFailedValidationExecutor()),
                               "A verifiable factory should return the same FailedValidationExecutor instance it is given");
    }
}
