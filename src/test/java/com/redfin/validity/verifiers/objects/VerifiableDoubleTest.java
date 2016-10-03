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
    public VerifiableDouble<IllegalArgumentException> getNotValueTypeInstance() {
        return getAbstractVerifiableObjectInstance(getAbstractVerifiablePrimitiveFailedValidationExecutor(), 0.0, "message");
    }

    @Override
    public VerifiableDouble<IllegalArgumentException> getAbstractVerifiableComparableNumber() {
        return getNotValueTypeInstance();
    }

    @Override
    public VerifiableDouble<IllegalArgumentException> getAbstractVerifiableComparable() {
        return getNotValueTypeInstance();
    }

    @Override
    public FailedValidationExecutor<IllegalArgumentException> getAbstractVerifiablePrimitiveFailedValidationExecutor() {
        return FailedValidationExecutors.getDefaultFailureExecutor();
    }

    @Override
    public Class<IllegalArgumentException> getThrowableClass() {
        return IllegalArgumentException.class;
    }

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
    public VerifiableDouble<IllegalArgumentException> getAbstractVerifiableObjectInstance(FailedValidationExecutor<IllegalArgumentException> failedValidationExecutor, Double subject, String message) {
        return new VerifiableDouble<>(failedValidationExecutor, subject, message);
    }
}
