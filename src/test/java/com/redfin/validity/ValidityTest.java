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

final class ValidityTest
 implements NonInstantiableContract<Validity> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test values & contract implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public Class<Validity> getNonInstantiableClassObject() {
        return Validity.class;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    void testValidateReturnsNonNull() {
        Assertions.assertNotNull(Validity.validate(),
                                 "Validity validate should return a non null object.");
    }

    @Test
    void testRepeatedCallsToValidateReturnTheSameInstance() {
        Assertions.assertTrue(Validity.validate() == Validity.validate(),
                              "Repeated calls to validate should return the same instance.");
    }

    @Test
    void testValidateReturnsFactoryWithNullMessage() {
        Assertions.assertNull(Validity.validate().getMessageSupplier().get(),
                              "Validity validate should return a factory with a null message.");
    }

    @Test
    void testExpectReturnsNonNull() {
        Assertions.assertNotNull(Validity.expect(),
                                 "Validity expect should return a non null object.");
    }

    @Test
    void testRepeatedCallsToExpectReturnTheSameInstance() {
        Assertions.assertTrue(Validity.expect() == Validity.expect(),
                              "Repeated calls to expect should return the same instance.");
    }

    @Test
    void testExpectReturnsFactoryWithNullMessage() {
        Assertions.assertNull(Validity.expect().getMessageSupplier().get(),
                              "Validity expect should return a factory with a null message.");
    }
}
