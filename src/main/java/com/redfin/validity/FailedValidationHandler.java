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
 * the input of failed validation.
 *
 * @param <X> the type of {@link Throwable} that should be returned from
 *            the {@link #buildThrowable(String, String, String)} method.
 */
@FunctionalInterface
public interface FailedValidationHandler<X extends Throwable> {

    /**
     * Create and return a {@link Throwable} of the type for this handler instance.
     *
     * @param description         the String use supplied description of what is
     *                            being tested. May be null.
     * @param expectedDescription the String description of the expected state.
     *                            May not be null.
     * @param actualDescription   the String description of the actual state.
     *                            May not be null.
     * @return a {@link Throwable} created from the given input.
     * @throws NullPointerException if expectedDescription or actualDescription are null.
     */
    X buildThrowable(String description, String expectedDescription, String actualDescription);
}
