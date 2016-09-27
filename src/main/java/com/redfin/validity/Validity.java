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
 * todo
 */
public final class Validity {

    private static final FailedValidationHandler<IllegalArgumentException> VERIFY_HANDLER = FailedValidationHandlers.getDefaultValidationHandler();
    private static final FailedValidationHandler<AssertionError> AFFIRM_HANDLER = FailedValidationHandlers.getStackTrimmingValidationHandler();

    private static final ValidityFactory<IllegalArgumentException> CACHED_VERIFY_BUILDER = new ValidityFactory<>(VERIFY_HANDLER, null);
    private static final ValidityFactory<AssertionError> CACHED_AFFIRM_BUILDER = new ValidityFactory<>(AFFIRM_HANDLER, null);

    public static ValidityFactory<IllegalArgumentException> verify() {
        return CACHED_VERIFY_BUILDER;
    }

    public static ValidityFactory<IllegalArgumentException> verifyAs(String description) {
        return new ValidityFactory<>(FailedValidationHandlers.getDefaultValidationHandler(), description);
    }

    public static ValidityFactory<AssertionError> asserts() {
        return CACHED_AFFIRM_BUILDER;
    }

    public static ValidityFactory<AssertionError> assertsAs(String description) {
        return new ValidityFactory<>(FailedValidationHandlers.getStackTrimmingValidationHandler(), description);
    }

    private Validity() {
        throw new AssertionError(Messages.nonInstantiableMessage());
    }
}
