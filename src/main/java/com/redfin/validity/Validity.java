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
 * The entry point for the Validity library. This is a specific sub-class
 * of the {@link VerifiableFactory} that throws {@link IllegalArgumentException}s on
 * validation failure.
 */
public final class Validity extends VerifiableFactory<IllegalArgumentException> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Constants
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /*
     * For performance, cache instances of the null message Validity instance.
     * Since it and the default validation executor is thread safe and re-usable,
     * we can simply re-use the same instances each time if there isn't a given
     * message. If a message is, given, though, a new instance is required.
     */

    private static final FailedValidationExecutor<IllegalArgumentException> VERIFY_FAILURE = new DefaultValidityFailedValidationExecutor<>(IllegalArgumentException::new);
    private static final Validity NO_MESSAGE_INSTANCE = new Validity(null);

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Instance Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private Validity(String message) {
        super(message, VERIFY_FAILURE);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Static Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * @return a {@link Validity} instance with the default message
     * prefix.
     */
    public static Validity validate() {
        return NO_MESSAGE_INSTANCE;
    }

    /**
     * @param message the String message to use as a prefix for the validation.
     *                May be null.
     *
     * @return a {@link ValidityBuilder} instance with the given message prefix.
     */
    public static ValidityBuilder withMessage(String message) {
        return new ValidityBuilder(message);
    }

    /**
     * Helper class to store the given message used when generating the
     * {@link Validity} instance.
     */
    public static final class ValidityBuilder {

        private final String message;

        /**
         * Create a new {@link ValidityBuilder} instance with the given string message.
         *
         * @param message the String message prefix for the build Validity instance.
         */
        public ValidityBuilder(String message) {
            this.message = message;
        }

        /**
         * @return a {@link Validity} instance with the message given when this builder
         * was created.
         */
        public Validity validate() {
            return new Validity(message);
        }
    }
}
