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

final class VerifiableByteTest implements AbstractVerifiableComparableNumberContract<IllegalArgumentException, Byte, VerifiableByte<IllegalArgumentException>> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test values & contract implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public Byte getSubject() {
        return new Byte((byte) 0);
    }

    @Override
    public Byte getEqualSubject() {
        return new Byte((byte) 0);
    }

    @Override
    public Byte getNonEqualSubject() {
        return new Byte((byte) 1);
    }

    @Override
    public Byte getComparableSubject() {
        return new Byte((byte) 0);
    }

    @Override
    public Byte getNonComparableSubject() {
        return new Byte((byte) 1);
    }

    @Override
    public Byte getLessThanSubject() {
        return new Byte((byte) -1);
    }

    @Override
    public Byte getGreaterThanSubject() {
        return new Byte((byte) 1);
    }

    @Override
    public Byte getZeroSubject() {
        return new Byte((byte) 0);
    }

    @Override
    public Byte getNonZeroSubject() {
        return new Byte((byte) 1);
    }

    @Override
    public Byte getPositiveSubject() {
        return new Byte((byte) 1);
    }

    @Override
    public Byte getNegativeSubject() {
        return new Byte((byte) -1);
    }

    @Override
    public VerifiableByte<IllegalArgumentException> getVerifiableInstance(FailedValidationExecutor<IllegalArgumentException> failedValidationExecutor, Byte subject, String message) {
        return new VerifiableByte<>(failedValidationExecutor, subject, message);
    }

    @Override
    public Class<IllegalArgumentException> getThrowableClass() {
        return IllegalArgumentException.class;
    }

    @Override
    public FailedValidationExecutor<IllegalArgumentException> getFailedValidationExecutor() {
        return FailedValidationExecutors.getDefaultFailureExecutor(IllegalArgumentException::new);
    }
}
