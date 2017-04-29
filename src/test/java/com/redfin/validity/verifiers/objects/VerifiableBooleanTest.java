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

package com.redfin.validity.verifiers.objects;

import com.redfin.validity.DefaultValidityFailedValidationExecutor;
import com.redfin.validity.FailedValidationExecutor;
import com.redfin.validity.verifiers.AbstractVerifiableObjectContract;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

final class VerifiableBooleanTest implements AbstractVerifiableObjectContract<IllegalArgumentException, Boolean, VerifiableBoolean<IllegalArgumentException>> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test values & contract implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public Boolean getSubject() {
        return new Boolean(true);
    }

    @Override
    public Boolean getEqualSubject() {
        return new Boolean(true);
    }

    @Override
    public Boolean getNonEqualSubject() {
        return new Boolean(false);
    }

    @Override
    public VerifiableBoolean<IllegalArgumentException> getVerifiableInstance(FailedValidationExecutor<IllegalArgumentException> failedValidationExecutor, Boolean subject, String message) {
        return new VerifiableBoolean<>(failedValidationExecutor, subject, message);
    }

    @Override
    public Class<IllegalArgumentException> getThrowableClass() {
        return IllegalArgumentException.class;
    }

    @Override
    public FailedValidationExecutor<IllegalArgumentException> getFailedValidationExecutor() {
        return new DefaultValidityFailedValidationExecutor<>(IllegalArgumentException::new);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    void testIsTrueReturnsSubjectForTrue() {
        Boolean subject = new Boolean(true);
        Assertions.assertTrue(subject == getVerifiableInstance(subject).isTrue(),
                              "VerifiableBoolean should return subject for isTrue with true subject.");
    }

    @Test
    void testIsTrueThrowsForFalse() {
        Boolean subject = new Boolean(false);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> getVerifiableInstance(subject).isTrue());
    }

    @Test
    void testIsTrueThrowsForNull() {
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> getVerifiableInstance(null).isTrue());
    }

    @Test
    void testIsFalseReturnsSubjectForFalse() {
        Boolean subject = new Boolean(false);
        Assertions.assertTrue(subject == getVerifiableInstance(subject).isFalse(),
                              "VerifiableBoolean should return subject for isFalse with false subject.");
    }

    @Test
    void testIsFalseThrowsForTrue() {
        Boolean subject = new Boolean(true);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> getVerifiableInstance(subject).isFalse());
    }

    @Test
    void testIsFalseThrowsForNull() {
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> getVerifiableInstance(null).isFalse());
    }
}
