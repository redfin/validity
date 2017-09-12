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

final class ValidityTest implements VerifiableFactoryContract<IllegalArgumentException> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test values & contract implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public Validity getNotValueTypeInstance() {
        return Validity.validate();
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    void testRequireReturnsNonNull() {
        Assertions.assertNotNull(Validity.validate(),
                                 "Validity validate should return a non null object.");
    }

    @Test
    void testRequireWithMessageReturnsNonNull() {
        Assertions.assertNotNull(Validity.withMessage("hello").validate(),
                                 "Validity validate with message should return a non null object.");
    }

    @Test
    void testRequireWithNullMessageReturnsNonNullValidityBuilder() {
        Assertions.assertNotNull(Validity.withMessage(null),
                                 "Validity with null message should return a non null object.");
    }

    @Test
    void testRequireWithNullMessageReturnsNonNull() {
        Assertions.assertNotNull(Validity.withMessage(null).validate(),
                                 "Validity validate with null message should return a non null object.");
    }

    @Test
    void testRepeatedAssertsReturnsSameInstance() {
        Assertions.assertTrue(Validity.validate() == Validity.validate(),
                              "Repeated calls to validate should return the same instance.");
    }

    @Test
    void testRepeatedAssertsWithMessagesReturnDifferentInstances() {
        String message = "hello";
        Assertions.assertFalse(Validity.withMessage(message).validate() == Validity.withMessage(message).validate(),
                               "Repeated calls to validate with message should return different instances.");
    }
}
