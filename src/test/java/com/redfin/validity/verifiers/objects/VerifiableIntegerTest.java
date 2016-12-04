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

final class VerifiableIntegerTest implements AbstractVerifiableComparableNumberContract<IllegalArgumentException, Integer, VerifiableInteger<IllegalArgumentException>> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test values & contract implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public Integer getSubject() {
        return new Integer(0);
    }

    @Override
    public Integer getEqualSubject() {
        return new Integer(0);
    }

    @Override
    public Integer getNonEqualSubject() {
        return new Integer(1);
    }

    @Override
    public Integer getComparableSubject() {
        return new Integer(0);
    }

    @Override
    public Integer getNonComparableSubject() {
        return new Integer(1);
    }

    @Override
    public Integer getLessThanSubject() {
        return new Integer(-1);
    }

    @Override
    public Integer getGreaterThanSubject() {
        return new Integer(1);
    }

    @Override
    public Integer getZeroSubject() {
        return new Integer(0);
    }

    @Override
    public Integer getNonZeroSubject() {
        return new Integer(1);
    }

    @Override
    public Integer getPositiveSubject() {
        return new Integer(1);
    }

    @Override
    public Integer getNegativeSubject() {
        return new Integer(-1);
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
    public VerifiableInteger<IllegalArgumentException> getVerifiableInstance(FailedValidationExecutor<IllegalArgumentException> failedValidationExecutor, Integer subject, String message) {
        return new VerifiableInteger<>(failedValidationExecutor, subject, message);
    }
}
