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
import com.redfin.validity.verifiers.AbstractVerifiableComparableNumberContract;

final class VerifiableShortTest implements AbstractVerifiableComparableNumberContract<IllegalArgumentException, Short, VerifiableShort<IllegalArgumentException>> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test values & contract implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public Short getSubject() {
        return new Short((short) 0);
    }

    @Override
    public Short getEqualSubject() {
        return new Short((short) 0);
    }

    @Override
    public Short getNonEqualSubject() {
        return new Short((short) 1);
    }

    @Override
    public Short getComparableSubject() {
        return new Short((short) 0);
    }

    @Override
    public Short getNonComparableSubject() {
        return new Short((short) 1);
    }

    @Override
    public Short getLessThanSubject() {
        return new Short((short) -1);
    }

    @Override
    public Short getGreaterThanSubject() {
        return new Short((short) 1);
    }

    @Override
    public Short getZeroSubject() {
        return new Short((short) 0);
    }

    @Override
    public Short getNonZeroSubject() {
        return new Short((short) 1);
    }

    @Override
    public Short getPositiveSubject() {
        return new Short((short) 1);
    }

    @Override
    public Short getNegativeSubject() {
        return new Short((short) -1);
    }

    @Override
    public FailedValidationExecutor<IllegalArgumentException> getFailedValidationExecutor() {
        return new DefaultValidityFailedValidationExecutor<>(IllegalArgumentException::new);
    }

    @Override
    public Class<IllegalArgumentException> getThrowableClass() {
        return IllegalArgumentException.class;
    }

    @Override
    public VerifiableShort<IllegalArgumentException> getVerifiableInstance(FailedValidationExecutor<IllegalArgumentException> failedValidationExecutor, Short subject, String message) {
        return new VerifiableShort<>(failedValidationExecutor, subject, message);
    }
}
