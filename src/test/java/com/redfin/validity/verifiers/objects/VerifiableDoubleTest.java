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

import com.redfin.validity.FailedValidationExecutor;
import com.redfin.validity.FailedValidationExecutors;
import com.redfin.validity.verifiers.AbstractVerifiableComparableNumberContract;

final class VerifiableDoubleTest implements AbstractVerifiableComparableNumberContract<IllegalArgumentException, Double, VerifiableDouble<IllegalArgumentException>> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test values & contract implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public Double getSubject() {
        return new Double(0);
    }

    @Override
    public Double getEqualSubject() {
        return new Double(0);
    }

    @Override
    public Double getNonEqualSubject() {
        return new Double(1);
    }

    @Override
    public Double getComparableSubject() {
        return new Double(0);
    }

    @Override
    public Double getNonComparableSubject() {
        return new Double(1);
    }

    @Override
    public Double getLessThanSubject() {
        return new Double(-1);
    }

    @Override
    public Double getGreaterThanSubject() {
        return new Double(1);
    }

    @Override
    public Double getZeroSubject() {
        return new Double(0);
    }

    @Override
    public Double getNonZeroSubject() {
        return new Double(1);
    }

    @Override
    public Double getPositiveSubject() {
        return new Double(1);
    }

    @Override
    public Double getNegativeSubject() {
        return new Double(-1);
    }

    @Override
    public Class<IllegalArgumentException> getThrowableClass() {
        return IllegalArgumentException.class;
    }

    @Override
    public FailedValidationExecutor<IllegalArgumentException> getFailedValidationExecutor() {
        return FailedValidationExecutors.getDefaultFailureExecutor();
    }

    @Override
    public VerifiableDouble<IllegalArgumentException> getVerifiableInstance(FailedValidationExecutor<IllegalArgumentException> failedValidationExecutor, Double subject, String message) {
        return new VerifiableDouble<>(failedValidationExecutor, subject, message);
    }
}
