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
import com.redfin.validity.ValidityUtils;
import com.redfin.validity.verifiers.AbstractVerifiableComparable;

/**
 * Concrete class for verifying String subjects.
 *
 * @param <X> the type of {@link Throwable} to be thrown on validation failure.
 */
public final class VerifiableString<X extends Throwable> extends AbstractVerifiableComparable<String, X> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Instance Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Create a new {@link VerifiableString} instance with the given values.
     *
     * @param failedValidationExecutor the {@link FailedValidationExecutor} to be called
     *                                 on validation failure.
     *                                 May not be null.
     * @param subject                  the subject to be validated.
     *                                 May be null.
     * @param message                  the String custom message to pre-pend a failure with.
     *                                 May be null.
     * @throws NullPointerException if failedValidationExecutor is null.
     */
    public VerifiableString(FailedValidationExecutor<X> failedValidationExecutor, String subject, String message) {
        super(failedValidationExecutor, subject, message);
    }

    /**
     * @return the subject if it is empty.
     * @throws X if the subject is null or not empty.
     */
    public String isEmpty() throws X {
        String subject = getSubject();
        if (null == subject || !subject.isEmpty()) {
            fail("t -> t.isEmpty()");
        }
        return subject;
    }

    /**
     * @return the subject if it is not empty.
     * @throws X if the subject is null or empty.
     */
    public String isNotEmpty() throws X {
        String subject = getSubject();
        if (null == subject || subject.isEmpty()) {
            fail("t -> !t.isEmpty()");
        }
        return subject;
    }

    /**
     * @param prefix the String prefix the subject should start with.
     * @return the subject if it starts with the prefix.
     * @throws X if the subject is null or does not start with the prefix.
     */
    public String startsWith(String prefix) throws X {
        String subject = getSubject();
        if (null == subject || !subject.startsWith(prefix)) {
            fail("t -> t.startsWith(" + ValidityUtils.describe(prefix) + ")");
        }
        return subject;
    }

    /**
     * @param prefix the String prefix the subject should not start with.
     * @return the subject if it does not start with the prefix.
     * @throws X if the subject is null or does start with the prefix.
     */
    public String doesNotStartWith(String prefix) throws X {
        String subject = getSubject();
        if (null == subject || subject.startsWith(prefix)) {
            fail("t -> !t.startsWith(" + ValidityUtils.describe(prefix) + ")");
        }
        return subject;
    }

    /**
     * @param suffix the String suffix the subject should end with.
     * @return the subject if it ends with the suffix.
     * @throws X if the subject is null or does not end with the suffix.
     */
    public String endsWith(String suffix) throws X {
        String subject = getSubject();
        if (null == subject || !subject.endsWith(suffix)) {
            fail("t -> t.endsWith(" + ValidityUtils.describe(suffix) + ")");
        }
        return subject;
    }

    /**
     * @param suffix the String suffix the subject should not end with.
     * @return the subject if it does not end with the suffix.
     * @throws X if the subject is null or does end with the suffix.
     */
    public String doesNotEndWith(String suffix) throws X {
        String subject = getSubject();
        if (null == subject || subject.endsWith(suffix)) {
            fail("t -> !t.endsWith(" + ValidityUtils.describe(suffix) + ")");
        }
        return subject;
    }

    /**
     * @param regex the String regex the subject should match.
     * @return the subject if it does match the regex.
     * @throws X if the subject is null or does not match the regex.
     */
    public String matches(String regex) throws X {
        String subject = getSubject();
        if (null == subject || !subject.matches(regex)) {
            fail("t -> t.matches(" + ValidityUtils.describe(regex) + ")");
        }
        return subject;
    }

    /**
     * @param regex the String regex the subject should not match.
     * @return the subject if it does not match the regex.
     * @throws X if the subject is null or does match the regex.
     */
    public String doesNotMatch(String regex) throws X {
        String subject = getSubject();
        if (null == subject || subject.matches(regex)) {
            fail("t -> !t.matches(" + ValidityUtils.describe(regex) + ")");
        }
        return subject;
    }
}
