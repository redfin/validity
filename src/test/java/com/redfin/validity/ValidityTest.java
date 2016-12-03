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
        return Validity.require();
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    void testRequireReturnsNonNull() {
        Assertions.assertNotNull(Validity.require(),
                                 "Validity require should return a non null object.");
    }

    @Test
    void testRequireWithMessageReturnsNonNull() {
        Assertions.assertNotNull(Validity.requireAs("hello"),
                                 "Validity require with message should return a non null object.");
    }

    @Test
    void testRequireWithNullMessageReturnsNonNull() {
        Assertions.assertNotNull(Validity.requireAs(null),
                                 "Validity require with null message should return a non null object.");
    }

    @Test
    void testRepeatedAssertsReturnsSameInstance() {
        Assertions.assertTrue(Validity.require() == Validity.require(),
                              "Repeated calls to require should return the same instance.");
    }

    @Test
    void testRepeatedAssertsWithMessagesReturnDifferentInstances() {
        String message = "hello";
        Assertions.assertFalse(Validity.requireAs(message) == Validity.requireAs(message),
                               "Repeated calls to require with message should return different instances.");
    }

    @Test
    void testRequireWithNullMessageReturnsSameInstanceAsAssertWithoutMessage() {
        Assertions.assertTrue(Validity.requireAs(null) == Validity.require(),
                              "Calling require with null message should return the same instance as asserts without message.");
    }
}
