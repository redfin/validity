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

package com.redfin.validity.verifiers.primitives;

import com.redfin.validity.FailedValidationExecutor;
import com.redfin.validity.FailedValidationExecutors;
import com.redfin.validity.ValidityUtils;
import com.redfin.validity.verifiers.AbstractVerifiablePrimitiveContract;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

final class VerifiablePrimitiveBooleanTest implements AbstractVerifiablePrimitiveContract<IllegalArgumentException, VerifiablePrimitiveBoolean<IllegalArgumentException>> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test values & contract implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public VerifiablePrimitiveBoolean<IllegalArgumentException> getNotValueTypeInstance() {
        return getInstance(true);
    }

    @Override
    public FailedValidationExecutor<IllegalArgumentException> getAbstractVerifiablePrimitiveFailedValidationExecutor() {
        return FailedValidationExecutors.getDefaultFailureExecutor(IllegalArgumentException::new);
    }

    @Override
    public VerifiablePrimitiveBoolean<IllegalArgumentException> getAbstractVerifiablePrimitive(FailedValidationExecutor<IllegalArgumentException> failedValidationExecutor, String message) {
        return new VerifiablePrimitiveBoolean<>(failedValidationExecutor, true, message);
    }

    private VerifiablePrimitiveBoolean<IllegalArgumentException> getInstance(boolean subject) {
        return new VerifiablePrimitiveBoolean<>(getAbstractVerifiablePrimitiveFailedValidationExecutor(), subject, "message");
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    void testIsTrueReturnsSubjectForTrueSubject() {
        VerifiablePrimitiveBoolean<IllegalArgumentException> verifiable = getInstance(true);
        Assertions.assertTrue(verifiable.isTrue(),
                              "VerifiablePrimitiveBoolean should return true for isTrue with true subject.");
    }

    @Test
    void testIsTrueThrowsForFalseSubject() {
        VerifiablePrimitiveBoolean<IllegalArgumentException> verifiable = getInstance(false);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isTrue);
    }

    @Test
    void testIsFalseReturnsSubjectForFalseSubject() {
        VerifiablePrimitiveBoolean<IllegalArgumentException> verifiable = getInstance(false);
        Assertions.assertFalse(verifiable.isFalse(),
                               "VerifiablePrimitiveBoolean should return false for isFalse with false subject.");
    }

    @Test
    void testIsFalseThrowsForTrueSubject() {
        VerifiablePrimitiveBoolean<IllegalArgumentException> verifiable = getInstance(true);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isFalse);
    }

    @Test
    void testToStringReturnsExpectedString() {
        VerifiablePrimitiveBoolean<IllegalArgumentException> verifiable = getInstance(true);
        Assertions.assertEquals(verifiable.getClass().getSimpleName() + ": <" + ValidityUtils.describe(true) + ">",
                                verifiable.toString(),
                                "VerifiablePrimitiveBoolean should return expected String for toString.");
    }
}
