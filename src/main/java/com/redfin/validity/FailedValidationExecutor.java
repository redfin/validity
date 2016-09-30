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

package com.redfin.validity;

/**
 * A functional interface that defines the creation of a throwable from
 * the input of failed validation. After creating the throwable, it
 * is then thrown.
 *
 * @param <X> the type of {@link Throwable} that should be thrown.
 */
public interface FailedValidationExecutor<X extends Throwable> {

    /**
     * Signals the {@link FailedValidationExecutor} that the validation of the subject
     * failed.
     *
     * @param expected the String expected state.
     *                 May not be null.
     * @param subject  the test subject.
     *                 May be null.
     * @param message  the String custom message supplied by the validation user.
     *                 May be null.
     * @param <T>      the type of subject.
     * @throws NullPointerException if expected is null.
     * @throws X                    always, unless expected is null.
     */
    <T> void fail(String expected, T subject, String message) throws X;
}
