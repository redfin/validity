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

final class VerifiableFloatTest implements AbstractVerifiableComparableNumberContract<IllegalArgumentException, Float, VerifiableFloat<IllegalArgumentException>> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test values & contract implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public Float getSubject() {
        return new Float(0);
    }

    @Override
    public Float getEqualSubject() {
        return new Float(0);
    }

    @Override
    public Float getNonEqualSubject() {
        return new Float(1);
    }

    @Override
    public Float getComparableSubject() {
        return new Float(0);
    }

    @Override
    public Float getNonComparableSubject() {
        return new Float(1);
    }

    @Override
    public Float getLessThanSubject() {
        return new Float(-1);
    }

    @Override
    public Float getGreaterThanSubject() {
        return new Float(1);
    }

    @Override
    public Float getZeroSubject() {
        return new Float(0);
    }

    @Override
    public Float getNonZeroSubject() {
        return new Float(1);
    }

    @Override
    public Float getPositiveSubject() {
        return new Float(1);
    }

    @Override
    public Float getNegativeSubject() {
        return new Float(-1);
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
    public VerifiableFloat<IllegalArgumentException> getVerifiableInstance(FailedValidationExecutor<IllegalArgumentException> failedValidationExecutor, Float subject, String message) {
        return new VerifiableFloat<>(failedValidationExecutor, subject, message);
    }
}
