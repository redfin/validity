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

import java.util.Collection;

/**
 * todo
 *
 * @param <X>
 */
public class ValidityFactory<X extends Throwable> {

    private final FailedValidationHandler<X> failedValidationHandler;
    private final String description;

    public ValidityFactory(FailedValidationHandler<X> failedValidationHandler, String description) {
        if (null == failedValidationHandler) {
            throw new NullPointerException(Messages.nullArgumentMessage("failedValidationHandler"));
        }
        this.failedValidationHandler = failedValidationHandler;
        this.description = description;
    }

    protected FailedValidationHandler<X> getFailedValidationHandler() {
        return failedValidationHandler;
    }

    protected String getDescription() {
        return description;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Verifiable Factory Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    // --------------------------------------------------------------
    // Verifiable Primitives
    // --------------------------------------------------------------

    public VerifiablePrimitiveShort<X> that(short actual) {
        return new VerifiablePrimitiveShort<>(actual, getDescription(), getFailedValidationHandler());
    }

    public VerifiablePrimitiveInteger<X> that(int actual) {
        return new VerifiablePrimitiveInteger<>(actual, getDescription(), getFailedValidationHandler());
    }

    public VerifiablePrimitiveLong<X> that(long actual) {
        return new VerifiablePrimitiveLong<>(actual, getDescription(), getFailedValidationHandler());
    }

    public VerifiablePrimitiveFloat<X> that(float actual) {
        return new VerifiablePrimitiveFloat<>(actual, getDescription(), getFailedValidationHandler());
    }

    public VerifiablePrimitiveDouble<X> that(double actual) {
        return new VerifiablePrimitiveDouble<>(actual, getDescription(), getFailedValidationHandler());
    }

    // --------------------------------------------------------------
    // Verifiable Primitive Arrays
    // --------------------------------------------------------------

    public VerifiablePrimitiveShortArray<X> that(short[] actual) {
        return new VerifiablePrimitiveShortArray<>(actual, getDescription(), getFailedValidationHandler());
    }

    public VerifiablePrimitiveIntegerArray<X> that(int[] actual) {
        return new VerifiablePrimitiveIntegerArray<>(actual, getDescription(), getFailedValidationHandler());
    }

    public VerifiablePrimitiveLongArray<X> that(long[] actual) {
        return new VerifiablePrimitiveLongArray<>(actual, getDescription(), getFailedValidationHandler());
    }

    public VerifiablePrimitiveFloatArray<X> that(float[] actual) {
        return new VerifiablePrimitiveFloatArray<>(actual, getDescription(), getFailedValidationHandler());
    }

    public VerifiablePrimitiveDoubleArray<X> that(double[] actual) {
        return new VerifiablePrimitiveDoubleArray<>(actual, getDescription(), getFailedValidationHandler());
    }

    // --------------------------------------------------------------
    // Verifiable Numbers
    // --------------------------------------------------------------

    public VerifiableNumberShort<X> that(Short actual) {
        return new VerifiableNumberShort<>(actual, getDescription(), getFailedValidationHandler());
    }

    public VerifiableNumberInteger<X> that(Integer actual) {
        return new VerifiableNumberInteger<>(actual, getDescription(), getFailedValidationHandler());
    }

    public VerifiableNumberLong<X> that(Long actual) {
        return new VerifiableNumberLong<>(actual, getDescription(), getFailedValidationHandler());
    }

    public VerifiableNumberFloat<X> that(Float actual) {
        return new VerifiableNumberFloat<>(actual, getDescription(), getFailedValidationHandler());
    }

    public VerifiableNumberDouble<X> that(Double actual) {
        return new VerifiableNumberDouble<>(actual, getDescription(), getFailedValidationHandler());
    }

    // --------------------------------------------------------------
    // Verifiable Objects
    // --------------------------------------------------------------

    public VerifiableString<X> that(String actual) {
        return new VerifiableString<>(actual, getDescription(), getFailedValidationHandler());
    }

    public <E, T extends Collection<E>> VerifiableCollection<E, T, X> that(T actual) {
        return new VerifiableCollection<>(actual, getDescription(), getFailedValidationHandler());
    }

    public <T> VerifiableObject<T, X> that(T actual) {
        return new VerifiableObject<>(actual, getDescription(), getFailedValidationHandler());
    }

    public <T> VerifiableObjectArray<T, X> that(T[] actual) {
        return new VerifiableObjectArray<>(actual, getDescription(), getFailedValidationHandler());
    }
}
