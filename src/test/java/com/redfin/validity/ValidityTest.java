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

final class ValidityTest implements NonInstantiableContract<Validity> {

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
    void testAssertsReturnsNonNull() {
        Assertions.assertNotNull(Validity.asserts(),
                                 "Validity asserts should return a non null object.");
    }

    @Test
    void testAssertsWithMessageReturnsNonNull() {
        Assertions.assertNotNull(Validity.assertsWithMessage("hello"),
                                 "Validity asserts should return a non null object.");
    }

    @Test
    void testAssertsWithNullMessageReturnsNonNull() {
        Assertions.assertNotNull(Validity.assertsWithMessage(null),
                                 "Validity asserts with null message should return a non null object.");
    }

    @Test
    void testRepeatedAssertsReturnsSameInstance() {
        Assertions.assertTrue(Validity.asserts() == Validity.asserts(),
                              "Repeated calls to asserts should return the same instance.");
    }

    @Test
    void testRepeatedAssertsWithMessagesReturnDifferentInstances() {
        String message = "hello";
        Assertions.assertFalse(Validity.assertsWithMessage(message) == Validity.assertsWithMessage(message),
                               "Repeated calls to asserts with message should return different instances.");
    }

    @Test
    void testAssertsWithNullMessageReturnsSameInstanceAsAssertWithoutMessage() {
        Assertions.assertTrue(Validity.assertsWithMessage(null) == Validity.asserts(),
                              "Calling asserts with null message should return the same instance as asserts without message.");
    }

    @Test
    void testVerifyReturnsNonNull() {
        Assertions.assertNotNull(Validity.requires(),
                                 "Validity requires should return a non null object.");
    }

    @Test
    void testVerifyWithMessageReturnsNonNull() {
        Assertions.assertNotNull(Validity.requiresWithMessage("hello"),
                                 "Validity requires should return a non null object.");
    }

    @Test
    void testVerifyWithNullMessageReturnsNonNull() {
        Assertions.assertNotNull(Validity.requiresWithMessage(null),
                                 "Validity requires with null message should return a non null object.");
    }

    @Test
    void testRepeatedVerifyReturnsSameInstance() {
        Assertions.assertTrue(Validity.requires() == Validity.requires(),
                              "Repeated calls to requires should return the same instance.");
    }

    @Test
    void testRepeatedVerifyWithMessagesReturnDifferentInstances() {
        String message = "hello";
        Assertions.assertFalse(Validity.requiresWithMessage(message) == Validity.requiresWithMessage(message),
                               "Repeated calls to requires with message should return different instances.");
    }

    @Test
    void testVerifyWithNullMessageReturnsSameInstanceAsVerifyWithoutMessage() {
        Assertions.assertTrue(Validity.requiresWithMessage(null) == Validity.requires(),
                              "Calling requires with null message should return the same instance as requires without message.");
    }
}
