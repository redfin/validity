package com.redfin.validity;

import com.redfin.validity.FailedValidationExecutor;
import com.redfin.validity.ValidityUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

final class VerifiableFactoryTest {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test requirements
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private static final String VALID_MESSAGE = "hello";
    private static final FailedValidationExecutor<IllegalArgumentException> VALIDATION_EXECUTOR = new FailedValidationExecutor<IllegalArgumentException>() {
        @Override
        public <T> void fail(String expected, T subject, String message) throws IllegalArgumentException {
            throw new IllegalArgumentException(expected + subject + message);
        }
    };

    private static final class TestVerifiableFactory extends VerifiableFactory<IllegalArgumentException> {
        private TestVerifiableFactory(String message, FailedValidationExecutor<IllegalArgumentException> failedValidationExecutor) {
            super(message, failedValidationExecutor);
        }
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    void testCanInstantiateWithValidArguments() {
        Assertions.assertNotNull(new TestVerifiableFactory(VALID_MESSAGE, VALIDATION_EXECUTOR),
                                 "Should be able to instantiate a VerifiableFactory with valid arguments.");
    }

    @Test
    void testCanInstantiateWithNullMessage() {
        Assertions.assertNotNull(new TestVerifiableFactory(null, VALIDATION_EXECUTOR),
                                 "Should be able to instantiate a VerifiableFactory with a null message.");
    }

    @Test
    void testConstructorThrowsExceptionForNullValidationExecutor() {
        NullPointerException exception = Assertions.expectThrows(NullPointerException.class,
                                                                 () -> new TestVerifiableFactory(VALID_MESSAGE, null));
        Assertions.assertEquals(ValidityUtils.nullArgumentMessage("failedValidationExecutor"),
                                exception.getMessage(),
                                "Should not be able to instantiate a VerifiableFactory with a null failed validation executor.");
    }
}
