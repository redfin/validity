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
import com.redfin.validity.verifiers.AbstractVerifiableComparableContract;

import java.time.Duration;
import java.time.Instant;

final class VerifiableInstantTest implements AbstractVerifiableComparableContract<IllegalArgumentException, Instant, VerifiableInstant<IllegalArgumentException>> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test values & contract implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private static final Instant SUBJECT = Instant.now();
    private static final Instant EQUAL = Instant.ofEpochMilli(SUBJECT.toEpochMilli());
    private static final Instant NON_EQUAL = SUBJECT.minus(Duration.ofSeconds(10));
    private static final Instant LESS_THAN = SUBJECT.minus(Duration.ofMinutes(1));
    private static final Instant GREATER_THAN = SUBJECT.plus(Duration.ofMinutes(1));

    @Override
    public Instant getSubject() {
        return SUBJECT;
    }

    @Override
    public Instant getEqualSubject() {
        return EQUAL;
    }

    @Override
    public Instant getNonEqualSubject() {
        return NON_EQUAL;
    }

    @Override
    public VerifiableInstant<IllegalArgumentException> getVerifiableInstance(FailedValidationExecutor<IllegalArgumentException> failedValidationExecutor, Instant subject, String message) {
        return new VerifiableInstant<>(failedValidationExecutor, subject, message);
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
    public Instant getComparableSubject() {
        return EQUAL;
    }

    @Override
    public Instant getNonComparableSubject() {
        return NON_EQUAL;
    }

    @Override
    public Instant getLessThanSubject() {
        return LESS_THAN;
    }

    @Override
    public Instant getGreaterThanSubject() {
        return GREATER_THAN;
    }
}
