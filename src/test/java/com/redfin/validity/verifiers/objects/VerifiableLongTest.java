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

final class VerifiableLongTest implements AbstractVerifiableComparableNumberContract<IllegalArgumentException, Long, VerifiableLong<IllegalArgumentException>> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test values & contract implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public Long getSubject() {
        return new Long(0);
    }

    @Override
    public Long getEqualSubject() {
        return new Long(0);
    }

    @Override
    public Long getNonEqualSubject() {
        return new Long(1);
    }

    @Override
    public Long getComparableSubject() {
        return new Long(0);
    }

    @Override
    public Long getNonComparableSubject() {
        return new Long(1);
    }

    @Override
    public Long getLessThanSubject() {
        return new Long(-1);
    }

    @Override
    public Long getGreaterThanSubject() {
        return new Long(1);
    }

    @Override
    public Long getZeroSubject() {
        return new Long(0);
    }

    @Override
    public Long getNonZeroSubject() {
        return new Long(1);
    }

    @Override
    public Long getPositiveSubject() {
        return new Long(1);
    }

    @Override
    public Long getNegativeSubject() {
        return new Long(-1);
    }

    @Override
    public FailedValidationExecutor<IllegalArgumentException> getFailedValidationExecutor() {
        return FailedValidationExecutors.getDefaultFailureExecutor();
    }

    @Override
    public Class<IllegalArgumentException> getThrowableClass() {
        return IllegalArgumentException.class;
    }

    @Override
    public VerifiableLong<IllegalArgumentException> getVerifiableInstance(FailedValidationExecutor<IllegalArgumentException> failedValidationExecutor, Long subject, String message) {
        return new VerifiableLong<>(failedValidationExecutor, subject, message);
    }
}
