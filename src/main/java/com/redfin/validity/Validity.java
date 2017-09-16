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
 * The entry point for the Validity library.
 */
public final class Validity  {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Constants
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /*
     * For performance, cache instances of the null message Validity instance.
     * Since it and the default validation executor is thread safe and re-usable,
     * we can simply re-use the same instances each time if there isn't a given
     * message. If a message is, given, though, a new instance is required.
     */

    private static final FailedValidationExecutor<IllegalArgumentException> VERIFY_FAILURE;
    private static final ValidityVerifiableFactory NO_MESSAGE_INSTANCE;

    static {
        VERIFY_FAILURE = new DefaultValidityFailedValidationExecutor<>(IllegalArgumentException::new);
        NO_MESSAGE_INSTANCE = new ValidityVerifiableFactory(null, VERIFY_FAILURE);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Static Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * @return a {@link Validity} instance with the default message
     * prefix.
     */
    public static ValidityVerifiableFactory validate() {
        return NO_MESSAGE_INSTANCE;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Instance Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /*
     * Make sure you cannot instantiate the static Validity class even with reflection.
     */

    private Validity() {
        throw new AssertionError(ValidityUtils.nonInstantiableMessage());
    }
}
