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

import java.lang.annotation.Annotation;
import java.util.function.Supplier;

/**
 * Concrete class for verifying class subjects.
 *
 * @param <T> the type of the class object being verified.
 * @param <X> the type of {@link Throwable} to be thrown on validation failure.
 */
public final class VerifiableClass<T, X extends Throwable>
           extends AbstractVerifiableObject<Class<T>, X> {

    /**
     * Create a new {@link VerifiableClass} instance with the given values.
     *
     * @param failedValidationExecutor the {@link FailedValidationExecutor} to be called
     *                                 on validation failure.
     *                                 May not be null.
     * @param subject                  the subject to be validated.
     *                                 May be null.
     * @param messageSupplier          the {@link Supplier} of the String custom message to pre-pend a failure with.
     *                                 May not be null.
     *
     * @throws NullPointerException if failedValidationExecutor or messageSupplier are null.
     */
    public VerifiableClass(FailedValidationExecutor<X> failedValidationExecutor,
                           Class<T> subject,
                           Supplier<String> messageSupplier) {
        super(failedValidationExecutor, subject, messageSupplier);
    }

    /**
     * @param clazz the class to check if the subject is assignable from.
     *
     * @return the subject if it is assignable from clazz.
     *
     * @throws X if the subject is null or is not assignable from clazz.
     */
    public Class<T> isAssignableFrom(Class<?> clazz) throws X {
        Class<T> subject = getSubject();
        if (null == subject || !subject.isAssignableFrom(clazz)) {
            fail("t -> t.isAssignableFrom(" + ValidityUtils.describe(clazz) + ")");
        }
        return subject;
    }

    /**
     * @param clazz the annotation to check for on the subject.
     *
     * @return the subject if it has the clazz annotation.
     *
     * @throws X if the subject is null or does not have the clazz annotation.
     */
    public Class<T> hasAnnotation(Class<? extends Annotation> clazz) throws X {
        Class<T> subject = getSubject();
        if (null == subject || null == subject.getAnnotation(clazz)) {
            fail("t -> null != t.getAnnotation(" + ValidityUtils.describe(clazz) + ")");
        }
        return subject;
    }
}
