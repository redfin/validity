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

final class ExpectVerifiableFactoryTest
 implements AbstractVerifiableFactoryContract<IllegalStateException, ExpectVerifiableFactory> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test requirements
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private static final String VALID_MESSAGE = "hello";

    private static final FailedValidationExecutor<IllegalStateException> VALIDATION_EXECUTOR = new FailedValidationExecutor<IllegalStateException>() {
        @Override
        public <T> void fail(String expected, T subject, String message) throws IllegalStateException {
            throw new IllegalArgumentException(expected + subject + message);
        }
    };

    private ExpectVerifiableFactory getInstance() {
        return new ExpectVerifiableFactory(VALID_MESSAGE, VALIDATION_EXECUTOR);
    }

    @Override
    public AbstractVerifiableFactory<IllegalStateException, ExpectVerifiableFactory> getNotValueTypeInstance() {
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
            new ExpectVerifiableFactory(null, VALIDATION_EXECUTOR);
        } catch (Throwable thrown) {
            throw new RuntimeException("Should be able to instantiate a ValidityVerifiableFactory with a null message", thrown);
        }
    }

    @Test
    void testConstructorThrowsExceptionForNullValidationExecutor() {
        NullPointerException exception = Assertions.assertThrows(NullPointerException.class,
                                                                 () -> new ValidityVerifiableFactory(VALID_MESSAGE, null));
        Assertions.assertEquals(ValidityUtils.nullArgumentMessage("failedValidationExecutor"),
                                exception.getMessage(),
                                "Should not be able to instantiate a ValidityVerifiableFactory with a null failed validation executor.");
    }

    @Test
    void testReturnsGivenMessage() {
        Assertions.assertTrue( VALID_MESSAGE.equals(getInstance().getMessage()),
                               "A verifiable factory should return the same string message instance it is given");
    }

    @Test
    void testReturnsGivenExecutor() {
        Assertions.assertTrue( VALIDATION_EXECUTOR.equals(getInstance().getFailedValidationExecutor()),
                               "A verifiable factory should return the same FailedValidationExecutor instance it is given");
    }

    @Test
    void testGetFactoryReturnsNonNUllInstance() {
        Assertions.assertNotNull(getInstance().getFactory(VALID_MESSAGE, VALIDATION_EXECUTOR),
                                 "The getFactory method of the verifiable factory should return a non-null instance.");
    }

    @Test
    void testGetFactoryReturnsGivenMessage() {
        Assertions.assertTrue( VALID_MESSAGE.equals(getInstance().getFactory(VALID_MESSAGE, VALIDATION_EXECUTOR)
                                                                 .getMessage()),
                               "A verifiable factory should return the same string message instance it is given");
    }

    @Test
    void testGetFactoryReturnsGivenExecutor() {
        Assertions.assertTrue( VALIDATION_EXECUTOR.equals(getInstance().getFactory(VALID_MESSAGE, VALIDATION_EXECUTOR)
                                                                       .getFailedValidationExecutor()),
                               "A verifiable factory should return the same FailedValidationExecutor instance it is given");
    }
}
