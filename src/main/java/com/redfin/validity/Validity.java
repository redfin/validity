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

/**
 * Static class meant to be the entry point for validation.
 */
public final class Validity {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Constants
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /*
     * For performance, cache instances of the null message ValidationBuilders.
     * Since it and the default validation executors are as well, we can
     * simply re-use the same instances each time if there isn't a given
     * message. If a message is, given, though, a new instance is required.
     */

    private static final FailedValidationExecutor<IllegalArgumentException> VERIFY_FAILURE = FailedValidationExecutors.getDefaultFailureExecutor();
    private static final FailedValidationExecutor<AssertionError> ASSERT_FAILURE = FailedValidationExecutors.getStackTrimmingFailureExecutor();

    private static final VerifiableFactory<IllegalArgumentException> VERIFY_BUILDER = new VerifiableFactory<>(null, VERIFY_FAILURE);
    private static final VerifiableFactory<AssertionError> ASSERT_BUILDER = new VerifiableFactory<>(null, ASSERT_FAILURE);

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Static Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    // --------------------------------------------------------------
    // Verify Methods
    // --------------------------------------------------------------

    /**
     * @return a {@link VerifiableFactory} instance with the default message
     * prefix and the default {@link FailedValidationExecutor} for argument
     * validation.
     */
    public static VerifiableFactory<IllegalArgumentException> verify() {
        return VERIFY_BUILDER;
    }

    /**
     * @param message the String message to use as a prefix for the validation.
     * @return a {@link VerifiableFactory} instance with the given message and
     * the default {@link FailedValidationExecutor} for argument validation.
     */
    public static VerifiableFactory<IllegalArgumentException> verifyWithMessage(String message) {
        if (null == message) {
            return VERIFY_BUILDER;
        } else {
            return new VerifiableFactory<>(message, FailedValidationExecutors.getDefaultFailureExecutor());
        }
    }

    // --------------------------------------------------------------
    // Asserts Methods
    // --------------------------------------------------------------

    /**
     * @return a {@link VerifiableFactory} instance with the default message
     * prefix and the default {@link FailedValidationExecutor} for assertions.
     */
    public static VerifiableFactory<AssertionError> asserts() {
        return ASSERT_BUILDER;
    }

    /**
     * @param message the String message to use as a prefix for the validation.
     * @return a {@link VerifiableFactory} instance with the given message and
     * the default {@link FailedValidationExecutor} for assertions.
     */
    public static VerifiableFactory<AssertionError> assertsWithMessage(String message) {
        if (null == message) {
            return ASSERT_BUILDER;
        } else {
            return new VerifiableFactory<>(message, FailedValidationExecutors.getStackTrimmingFailureExecutor());
        }
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Instance Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * @throws AssertionError always.
     */
    private Validity() {
        throw new AssertionError(ValidityUtils.nonInstantiableMessage());
    }
}
