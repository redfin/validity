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
import com.redfin.validity.verifiers.AbstractVerifiableObject;
import java.util.Collection;

/**
 * Concrete class for verifying Collection subjects.
 *
 * @param <X> the type of {@link Throwable} to be thrown on validation failure.
 */
public final class VerifiableCollection<E, T extends Collection<E>, X extends Throwable>
           extends AbstractVerifiableObject<T, X> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Instance Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Create a new {@link VerifiableCollection} instance with the given values.
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
    public VerifiableCollection(FailedValidationExecutor<X> failedValidationExecutor, T subject, String message) {
        super(failedValidationExecutor, subject, message);
    }

    /**
     * @return the subject if it is empty.
     * @throws X if the subject is null or is not empty.
     */
    public T isEmpty() throws X {
        T subject = getSubject();
        if (null == subject || !subject.isEmpty()) {
            fail("t -> t.isEmpty()");
        }
        return subject;
    }

    /**
     * @return the subject if it is not empty.
     * @throws X if the subject is null or is empty.
     */
    public T isNotEmpty() throws X {
        T subject = getSubject();
        if (null == subject || subject.isEmpty()) {
            fail("t -> !t.isEmpty()");
        }
        return subject;
    }

    /**
     * @param n the value to check against the subject size.
     * @return the subject if its size equals "n".
     * @throws X if the subject is null or if it's size isn't "n".
     */
    public T hasSizeOf(int n) throws X {
        T subject = getSubject();
        if (null == subject || subject.size() != n) {
            fail("t -> t.size() == " + ValidityUtils.describe(n));
        }
        return subject;
    }

    /**
     * @param n the value to check against the subject size.
     * @return the subject if its size is greater than or equal to "n".
     * @throws X if the subject is null or has size less than "n".
     */
    public T hasSizeOfAtLeast(int n) throws X {
        T subject = getSubject();
        if (null == subject || subject.size() < n) {
            fail("t -> t.size() >= " + ValidityUtils.describe(n));
        }
        return subject;
    }

    /**
     * @param n the value to check against the subject size.
     * @return the subject if its size is less than or equal to "n".
     * @throws X if the subject is null or has size greater than "n".
     */
    public T hasSizeOfAtMost(int n) throws X {
        T subject = getSubject();
        if (null == subject || subject.size() > n) {
            fail("t -> t.size() <= " + ValidityUtils.describe(n));
        }
        return subject;
    }

    /**
     * @param e the object to check if the subject contains.
     * @return the subject if it contains "e".
     * @throws X if the subject is null or does not contain "e".
     */
    public T contains(E e) throws X {
        T subject = getSubject();
        if (null == subject || !subject.contains(e)) {
            fail("t -> t.contains(" + ValidityUtils.describe(e) + ")");
        }
        return subject;
    }

    /**
     * @param e the object to check if the subject contains.
     * @return the subject if it does not contain "e".
     * @throws X if the subject is null or does contain "e".
     */
    public T doesNotContain(E e) throws X {
        T subject = getSubject();
        if (null == subject || subject.contains(e)) {
            fail("t -> !t.contains(" + ValidityUtils.describe(e) + ")");
        }
        return subject;
    }
}
