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

import java.util.function.Supplier;

final class VerifiableShortTest
 implements AbstractVerifiableComparableNumberContract<IllegalArgumentException, Short, VerifiableShort<IllegalArgumentException>> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test values & contract implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public Short getSubject() {
        return 0;
    }

    @Override
    public Short getEqualSubject() {
        return 0;
    }

    @Override
    public Short getNonEqualSubject() {
        return 1;
    }

    @Override
    public Short getComparableSubject() {
        return 0;
    }

    @Override
    public Short getNonComparableSubject() {
        return 1;
    }

    @Override
    public Short getLessThanSubject() {
        return -1;
    }

    @Override
    public Short getGreaterThanSubject() {
        return 1;
    }

    @Override
    public Short getZeroSubject() {
        return 0;
    }

    @Override
    public Short getNonZeroSubject() {
        return 1;
    }

    @Override
    public Short getPositiveSubject() {
        return 1;
    }

    @Override
    public Short getNegativeSubject() {
        return -1;
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
    public VerifiableShort<IllegalArgumentException> getVerifiableInstance(FailedValidationExecutor<IllegalArgumentException> failedValidationExecutor,
                                                                           Short subject,
                                                                           Supplier<String> messageSupplier) {
        return new VerifiableShort<>(failedValidationExecutor, subject, messageSupplier);
    }
}
