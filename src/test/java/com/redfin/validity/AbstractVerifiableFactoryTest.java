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

final class AbstractVerifiableFactoryTest {

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

    private static final class TestAbstractVerifiableFactory
                       extends AbstractVerifiableFactory<IllegalArgumentException, TestAbstractVerifiableFactory> {

        private TestAbstractVerifiableFactory(Supplier<String> messageSupplier,
                                              FailedValidationExecutor<IllegalArgumentException> failedValidationExecutor) {
            super(messageSupplier, failedValidationExecutor);
        }

        @Override
        protected TestAbstractVerifiableFactory getFactory(Supplier<String> messageSupplier,
                                                           FailedValidationExecutor<IllegalArgumentException> failedValidationExecutor) {
            return new TestAbstractVerifiableFactory(messageSupplier, failedValidationExecutor);
        }
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    void testCanInstantiateWithValidArguments() {
        try {
            new TestAbstractVerifiableFactory(VALID_MESSAGE_SUPPLIER, VALIDATION_EXECUTOR);
        } catch (Throwable thrown) {
            throw new RuntimeException("Should be able to instantiate a AbstractVerifiableFactory with valid arguments", thrown);
        }
    }

    @Test
    void testThrowsWithNullMessageSupplier() {
        NullPointerException exception = Assertions.assertThrows(NullPointerException.class,
                                                                 () -> new TestAbstractVerifiableFactory(null, VALIDATION_EXECUTOR));
        Assertions.assertEquals(ValidityUtils.nullArgumentMessage("messageSupplier"),
                                exception.getMessage(),
                                "Should not be able to instantiate a AbstractVerifiableFactory with a null message supplier.");
    }

    @Test
    void testCanInstantiateWithNullMessage() {
        try {
            new TestAbstractVerifiableFactory(() -> null, VALIDATION_EXECUTOR);
        } catch (Throwable thrown) {
            throw new RuntimeException("Should be able to instantiate a AbstractVerifiableFactory with a null message", thrown);
        }
    }

    @Test
    void testConstructorThrowsExceptionForNullValidationExecutor() {
        NullPointerException exception = Assertions.assertThrows(NullPointerException.class,
                                                                 () -> new TestAbstractVerifiableFactory(VALID_MESSAGE_SUPPLIER, null));
        Assertions.assertEquals(ValidityUtils.nullArgumentMessage("failedValidationExecutor"),
                                exception.getMessage(),
                                "Should not be able to instantiate a AbstractVerifiableFactory with a null failed validation executor.");
    }

    @Test
    void testReturnsGivenMessage() {
        Assertions.assertTrue( VALID_MESSAGE_SUPPLIER.get().equals(new TestAbstractVerifiableFactory(VALID_MESSAGE_SUPPLIER, VALIDATION_EXECUTOR).getMessageSupplier().get()),
                               "A verifiable factory should return the same string message instance it is given");
    }

    @Test
    void testReturnsGivenExecutor() {
        Assertions.assertTrue( VALIDATION_EXECUTOR.equals(new TestAbstractVerifiableFactory(VALID_MESSAGE_SUPPLIER, VALIDATION_EXECUTOR).getFailedValidationExecutor()),
                               "A verifiable factory should return the same FailedValidationExecutor instance it is given");
    }

    @Test
    void testWithMessageReturnsWithNewMessage() {
        String originalMessage = "hello";
        String secondMessage = "world";
        Assertions.assertEquals(secondMessage,
                                new TestAbstractVerifiableFactory(() -> originalMessage, VALIDATION_EXECUTOR).withMessage(secondMessage)
                                                                                                             .getMessageSupplier()
                                                                                                             .get(),
                                "A verifiable factory withMessage(String) should return a factory with the new message.");
    }

    @Test
    void testWithMessageReturnsWithNullMessage() {
        String originalMessage = "hello";
        Assertions.assertNull(new TestAbstractVerifiableFactory(() -> originalMessage, VALIDATION_EXECUTOR).withMessage((String) null)
                                                                                                             .getMessageSupplier()
                                                                                                             .get(),
                                "A verifiable factory withMessage(String) should return a factory with the new message.");
    }

    @Test
    void testWithMessageReturnsWithGivenSupplier() {
        String originalMessage = "hello";
        String secondMessage = "world";
        Assertions.assertEquals(secondMessage,
                                new TestAbstractVerifiableFactory(() -> originalMessage, VALIDATION_EXECUTOR).withMessage(() -> secondMessage)
                                                                                                             .getMessageSupplier()
                                                                                                             .get(),
                                "A verifiable factory withMessage(Supplier) should return a factory with the new message.");
    }

    @Test
    void testWithMessageReturnsWithNullMessageSupplier() {
        String originalMessage = "hello";
        Assertions.assertNull(new TestAbstractVerifiableFactory(() -> originalMessage, VALIDATION_EXECUTOR).withMessage((Supplier<String>) null)
                                                                                                           .getMessageSupplier()
                                                                                                           .get(),
                              "A verifiable factory withMessage(Supplier) should return a factory with the new message.");
    }

    @Test
    void testWIthMessageReturnsWithPreviousExecutor() {
        String originalMessage = "hello";
        String secondMessage = "world";
        Assertions.assertEquals(VALIDATION_EXECUTOR,
                                new TestAbstractVerifiableFactory(() -> originalMessage, VALIDATION_EXECUTOR).withMessage(secondMessage)
                                                                                                             .getFailedValidationExecutor(),
                                "A verifiable factory withMessage(String) should return a factory with the same failed validation executor.");
    }
}
