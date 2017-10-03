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

final class VerifiableDoubleTest
 implements AbstractVerifiableComparableNumberContract<IllegalArgumentException, Double, VerifiableDouble<IllegalArgumentException>> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test values & contract implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public Double getSubject() {
        return 0d;
    }

    @Override
    public Double getEqualSubject() {
        return 0d;
    }

    @Override
    public Double getNonEqualSubject() {
        return 1d;
    }

    @Override
    public Double getComparableSubject() {
        return 0d;
    }

    @Override
    public Double getNonComparableSubject() {
        return 1d;
    }

    @Override
    public Double getLessThanSubject() {
        return (double) -1;
    }

    @Override
    public Double getGreaterThanSubject() {
        return 1d;
    }

    @Override
    public Double getZeroSubject() {
        return 0d;
    }

    @Override
    public Double getNonZeroSubject() {
        return 1d;
    }

    @Override
    public Double getPositiveSubject() {
        return 1d;
    }

    @Override
    public Double getNegativeSubject() {
        return (double) -1;
    }

    @Override
    public Class<IllegalArgumentException> getThrowableClass() {
        return IllegalArgumentException.class;
    }

    @Override
    public FailedValidationExecutor<IllegalArgumentException> getFailedValidationExecutor() {
        return new DefaultValidityFailedValidationExecutor<>(IllegalArgumentException::new);
    }

    @Override
    public VerifiableDouble<IllegalArgumentException> getVerifiableInstance(FailedValidationExecutor<IllegalArgumentException> failedValidationExecutor, Double subject, String message) {
        return new VerifiableDouble<>(failedValidationExecutor, subject, message);
    }
}
