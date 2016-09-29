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

import com.redfin.validity.verifiable.arrays.VerifiableObjectArray;
import com.redfin.validity.verifiable.arrays.VerifiablePrimitiveBooleanArray;
import com.redfin.validity.verifiable.arrays.VerifiablePrimitiveByteArray;
import com.redfin.validity.verifiable.arrays.VerifiablePrimitiveCharArray;
import com.redfin.validity.verifiable.arrays.VerifiablePrimitiveDoubleArray;
import com.redfin.validity.verifiable.arrays.VerifiablePrimitiveFloatArray;
import com.redfin.validity.verifiable.arrays.VerifiablePrimitiveIntegerArray;
import com.redfin.validity.verifiable.arrays.VerifiablePrimitiveLongArray;
import com.redfin.validity.verifiable.arrays.VerifiablePrimitiveShortArray;
import com.redfin.validity.verifiable.objects.VerifiableBoolean;
import com.redfin.validity.verifiable.objects.VerifiableByte;
import com.redfin.validity.verifiable.objects.VerifiableCharacter;
import com.redfin.validity.verifiable.objects.VerifiableCollection;
import com.redfin.validity.verifiable.objects.VerifiableDouble;
import com.redfin.validity.verifiable.objects.VerifiableFloat;
import com.redfin.validity.verifiable.objects.VerifiableInteger;
import com.redfin.validity.verifiable.objects.VerifiableLong;
import com.redfin.validity.verifiable.objects.VerifiableShort;
import com.redfin.validity.verifiable.objects.VerifiableObject;
import com.redfin.validity.verifiable.objects.VerifiableString;
import java.util.Collection;

/**
 * todo
 *
 * @param <X>
 */
public class ValidityFactory<X extends Throwable> {

    private final FailedValidationHandler<X> failedValidationHandler;
    private final String description;


    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Instance Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public ValidityFactory(FailedValidationHandler<X> failedValidationHandler, String description) {
        if (null == failedValidationHandler) {
            throw new NullPointerException(ValidityUtils.nullArgumentMessage("failedValidationHandler"));
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

    // --------------------------------------------------------------
    // VerifiableFactory Methods
    // --------------------------------------------------------------

    // - - - - - - - - - - - - - - - - - - - - - -
    // Verifiable Arrays
    // - - - - - - - - - - - - - - - - - - - - - -

    public VerifiablePrimitiveBooleanArray<X> that(boolean[] actual) {
        return new VerifiablePrimitiveBooleanArray<>(actual, getDescription(), getFailedValidationHandler());
    }

    public VerifiablePrimitiveByteArray<X> that(byte[] actual) {
        return new VerifiablePrimitiveByteArray<>(actual, getDescription(), getFailedValidationHandler());
    }

    public VerifiablePrimitiveCharArray<X> that(char[] actual) {
        return new VerifiablePrimitiveCharArray<>(actual, getDescription(), getFailedValidationHandler());
    }

    public VerifiablePrimitiveDoubleArray<X> that(double[] actual) {
        return new VerifiablePrimitiveDoubleArray<>(actual, getDescription(), getFailedValidationHandler());
    }

    public VerifiablePrimitiveFloatArray<X> that(float[] actual) {
        return new VerifiablePrimitiveFloatArray<>(actual, getDescription(), getFailedValidationHandler());
    }

    public VerifiablePrimitiveIntegerArray<X> that(int[] actual) {
        return new VerifiablePrimitiveIntegerArray<>(actual, getDescription(), getFailedValidationHandler());
    }

    public VerifiablePrimitiveLongArray<X> that(long[] actual) {
        return new VerifiablePrimitiveLongArray<>(actual, getDescription(), getFailedValidationHandler());
    }

    public VerifiablePrimitiveShortArray<X> that(short[] actual) {
        return new VerifiablePrimitiveShortArray<>(actual, getDescription(), getFailedValidationHandler());
    }

    public <T> VerifiableObjectArray<T, X> that(T[] actual) {
        return new VerifiableObjectArray<>(actual, getDescription(), getFailedValidationHandler());
    }

    // - - - - - - - - - - - - - - - - - - - - - -
    // Verifiable Objects
    // - - - - - - - - - - - - - - - - - - - - - -

    public VerifiableBoolean<X> that(Boolean actual) {
        return new VerifiableBoolean<>(actual, getDescription(), getFailedValidationHandler());
    }

    public VerifiableByte<X> that(Byte actual) {
        return new VerifiableByte<>(actual, getDescription(), getFailedValidationHandler());
    }

    public VerifiableCharacter<X> that(Character actual) {
        return new VerifiableCharacter<>(actual, getDescription(), getFailedValidationHandler());
    }

    public <E, T extends Collection<E>> VerifiableCollection<E, T, X> that(T actual) {
        return new VerifiableCollection<>(actual, getDescription(), getFailedValidationHandler());
    }

    public VerifiableDouble<X> that(Double actual) {
        return new VerifiableDouble<>(actual, getDescription(), getFailedValidationHandler());
    }

    public VerifiableFloat<X> that(Float actual) {
        return new VerifiableFloat<>(actual, getDescription(), getFailedValidationHandler());
    }

    public VerifiableInteger<X> that(Integer actual) {
        return new VerifiableInteger<>(actual, getDescription(), getFailedValidationHandler());
    }

    public VerifiableLong<X> that(Long actual) {
        return new VerifiableLong<>(actual, getDescription(), getFailedValidationHandler());
    }

    public <T> VerifiableObject<T, X> that(T actual) {
        return new VerifiableObject<>(actual, getDescription(), getFailedValidationHandler());
    }

    public VerifiableShort<X> that(Short actual) {
        return new VerifiableShort<>(actual, getDescription(), getFailedValidationHandler());
    }

    public VerifiableString<X> that(String actual) {
        return new VerifiableString<>(actual, getDescription(), getFailedValidationHandler());
    }
}
