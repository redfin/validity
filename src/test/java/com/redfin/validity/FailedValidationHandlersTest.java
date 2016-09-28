package com.redfin.validity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

final class FailedValidationHandlersTest implements ContractNonInstantiable<FailedValidationHandlers> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test Contract Implementations [ ContractNonInstantiable ]
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public Class<FailedValidationHandlers> getTestClass() {
        return FailedValidationHandlers.class;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Custom Test Contract
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * A contract interface that tests the messages of throwable instances built from FailedValidationHandler
     * implementations according to the FailedValidationHandler class.
     */
    interface ContractFailedValidationHandlersMessage {

        String VALID_DESCRIPTION = "Hello, world";
        String VALID_EXPECTED = "t -> null != t";
        String VALID_ACTUAL = "\"happy\"";

        String EXPECTED_MESSAGE_FORMAT = "%s\n\texpected : %s\n\t  actual : <%s>";

        FailedValidationHandler<?> getFailedValidationHandler();

        @Test
        default void testFailedValidationHandlerReturnsThrowableWithExpectedMessage() {
            Throwable t = getFailedValidationHandler().buildThrowable(VALID_DESCRIPTION, VALID_EXPECTED, VALID_ACTUAL);
            Assertions.assertNotNull(t.getMessage(),
                                     "A throwable built by a failed validation handler should not have a null message.");
            Assertions.assertEquals(String.format(EXPECTED_MESSAGE_FORMAT, VALID_DESCRIPTION, VALID_EXPECTED, VALID_ACTUAL),
                                    t.getMessage(),
                                    "A throwable built by a failed validation handler should have the expected message.");
        }

        @Test
        default void testFailedValidationHandlerReturnsThrowableWithExpectedMessageForNullMessage() {
            Throwable t = getFailedValidationHandler().buildThrowable(null, VALID_EXPECTED, VALID_ACTUAL);
            Assertions.assertNotNull(t.getMessage(),
                                     "A throwable built by a failed validation handler should not have a null message.");
            Assertions.assertEquals(String.format(EXPECTED_MESSAGE_FORMAT, "Failed validation", VALID_EXPECTED, VALID_ACTUAL),
                                    t.getMessage(),
                                    "A throwable built by a failed validation handler should have the expected message.");
        }
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Nested Test Containers
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /*
     * Using nested test containers so that the tests inherited from the contracts will be run
     * on each of the FailedValidationHelper instances returned by the various methods
     * of the FailedValidationHelpers class.
     */

    @Nested
    final class DefaultFailedValidationHandlerTest implements ContractFailedValidationHandler, ContractFailedValidationHandlersMessage {

        @Override
        public FailedValidationHandler<?> getFailedValidationHandler() {
            return FailedValidationHandlers.getDefaultValidationHandler();
        }
    }

    @Nested
    final class DefaultFailedValidationHandlerWithTypeTest implements ContractFailedValidationHandler, ContractFailedValidationHandlersMessage {

        @Override
        public FailedValidationHandler<?> getFailedValidationHandler() {
            return FailedValidationHandlers.getDefaultValidationHandler(IllegalArgumentException::new);
        }
    }

    @Nested
    final class StackTraceTrimmingValidationHandlerTest implements ContractFailedValidationHandler, ContractFailedValidationHandlersMessage {

        @Override
        public FailedValidationHandler<?> getFailedValidationHandler() {
            return FailedValidationHandlers.getStackTrimmingValidationHandler();
        }
    }

    @Nested
    class StackTraceTrimmingValidationHandlerWithTypeTest implements ContractFailedValidationHandler, ContractFailedValidationHandlersMessage {

        @Override
        public FailedValidationHandler<?> getFailedValidationHandler() {
            return FailedValidationHandlers.getStackTrimmingValidationHandler(AssertionError::new);
        }
    }
}
