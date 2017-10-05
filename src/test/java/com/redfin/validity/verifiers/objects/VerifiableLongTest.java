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

final class VerifiableLongTest
 implements AbstractVerifiableComparableNumberContract<IllegalArgumentException, Long, VerifiableLong<IllegalArgumentException>> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test values & contract implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public Long getSubject() {
        return 0L;
    }

    @Override
    public Long getEqualSubject() {
        return 0L;
    }

    @Override
    public Long getNonEqualSubject() {
        return 1L;
    }

    @Override
    public Long getComparableSubject() {
        return 0L;
    }

    @Override
    public Long getNonComparableSubject() {
        return 1L;
    }

    @Override
    public Long getLessThanSubject() {
        return (long) -1;
    }

    @Override
    public Long getGreaterThanSubject() {
        return 1L;
    }

    @Override
    public Long getZeroSubject() {
        return 0L;
    }

    @Override
    public Long getNonZeroSubject() {
        return 1L;
    }

    @Override
    public Long getPositiveSubject() {
        return 1L;
    }

    @Override
    public Long getNegativeSubject() {
        return (long) -1;
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
    public VerifiableLong<IllegalArgumentException> getVerifiableInstance(FailedValidationExecutor<IllegalArgumentException> failedValidationExecutor,
                                                                          Long subject,
                                                                          Supplier<String> messageSupplier) {
        return new VerifiableLong<>(failedValidationExecutor, subject, messageSupplier);
    }
}
