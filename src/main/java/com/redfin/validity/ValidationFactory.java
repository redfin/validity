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

import com.redfin.validity.verifiers.arrays.VerifiableArray;
import com.redfin.validity.verifiers.arrays.VerifiableBooleanArray;
import com.redfin.validity.verifiers.arrays.VerifiableByteArray;
import com.redfin.validity.verifiers.arrays.VerifiableCharArray;
import com.redfin.validity.verifiers.arrays.VerifiableDoubleArray;
import com.redfin.validity.verifiers.arrays.VerifiableFloatArray;
import com.redfin.validity.verifiers.arrays.VerifiableIntArray;
import com.redfin.validity.verifiers.arrays.VerifiableLongArray;
import com.redfin.validity.verifiers.arrays.VerifiableShortArray;
import com.redfin.validity.verifiers.objects.VerifiableBoolean;
import com.redfin.validity.verifiers.objects.VerifiableByte;
import com.redfin.validity.verifiers.objects.VerifiableCharacter;
import com.redfin.validity.verifiers.objects.VerifiableClass;
import com.redfin.validity.verifiers.objects.VerifiableCollection;
import com.redfin.validity.verifiers.objects.VerifiableDouble;
import com.redfin.validity.verifiers.objects.VerifiableFloat;
import com.redfin.validity.verifiers.objects.VerifiableInteger;
import com.redfin.validity.verifiers.objects.VerifiableLong;
import com.redfin.validity.verifiers.objects.VerifiableObject;
import com.redfin.validity.verifiers.objects.VerifiableShort;
import com.redfin.validity.verifiers.objects.VerifiableString;
import com.redfin.validity.verifiers.primitives.VerifiablePrimitiveBoolean;
import com.redfin.validity.verifiers.primitives.VerifiablePrimitiveByte;
import com.redfin.validity.verifiers.primitives.VerifiablePrimitiveChar;
import com.redfin.validity.verifiers.primitives.VerifiablePrimitiveDouble;
import com.redfin.validity.verifiers.primitives.VerifiablePrimitiveFloat;
import com.redfin.validity.verifiers.primitives.VerifiablePrimitiveInt;
import com.redfin.validity.verifiers.primitives.VerifiablePrimitiveLong;
import com.redfin.validity.verifiers.primitives.VerifiablePrimitiveShort;
import java.util.Collection;

/**
 * A class that acts as a factory. It holds the information passed in
 * until a test subject for validation is given. At that point the correct
 * type of verifiable is returned depending upon the type of the subject.
 */
public class ValidationFactory<X extends Throwable> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Fields
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private final String message;
    private final FailedValidationExecutor<X> failedValidationExecutor;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Instance Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Create a new {@link ValidationFactory} instance with the given message and failed validation
     * executor.
     *
     * @param message                  the String message to pre-pend the failure message with, if necessary.
     *                                 May be null.
     * @param failedValidationExecutor the {@link FailedValidationExecutor} to use in case
     *                                 of failed validation.
     *                                 May not be null.
     * @throws NullPointerException if failedValidationExecutor is null.
     */
    public ValidationFactory(String message, FailedValidationExecutor<X> failedValidationExecutor) {
        this.message = message;
        if (null == failedValidationExecutor) {
            throw new NullPointerException(ValidityUtils.nullArgumentMessage("failedValidationExecutor"));
        }
        this.failedValidationExecutor = failedValidationExecutor;
    }

    // --------------------------------------------------------------
    // Factory Methods
    // --------------------------------------------------------------

    /*
     * Return the correct type of verifiable instance depending
     * upon the type of the subject to be validated.
     */

    // - - - - - - - - - - - - - - - - - - - - - -
    // Arrays
    // - - - - - - - - - - - - - - - - - - - - - -

    /**
     * @param subject the object to perform validation on.
     * @param <E> the type of the elements in the array.
     * @return a {@link VerifiableArray} instance for the given subject.
     */
    public <E> VerifiableArray<E, X> that(E[] subject) {
        return new VerifiableArray<>(failedValidationExecutor, subject, message);
    }

    /**
     * @param subject the object to perform validation on.
     * @return a {@link VerifiableBooleanArray} instance for the given subject.
     */
    public VerifiableBooleanArray<X> that(boolean[] subject) {
        return new VerifiableBooleanArray<>(failedValidationExecutor, subject, message);
    }

    /**
     * @param subject the object to perform validation on.
     * @return a {@link VerifiableByteArray} instance for the given subject.
     */
    public VerifiableByteArray<X> that(byte[] subject) {
        return new VerifiableByteArray<>(failedValidationExecutor, subject, message);
    }

    /**
     * @param subject the object to perform validation on.
     * @return a {@link VerifiableCharArray} instance for the given subject.
     */
    public VerifiableCharArray<X> that(char[] subject) {
        return new VerifiableCharArray<>(failedValidationExecutor, subject, message);
    }

    /**
     * @param subject the object to perform validation on.
     * @return a {@link VerifiableDoubleArray} instance for the given subject.
     */
    public VerifiableDoubleArray<X> that(double[] subject) {
        return new VerifiableDoubleArray<>(failedValidationExecutor, subject, message);
    }

    /**
     * @param subject the object to perform validation on.
     * @return a {@link VerifiableFloatArray} instance for the given subject.
     */
    public VerifiableFloatArray<X> that(float[] subject) {
        return new VerifiableFloatArray<>(failedValidationExecutor, subject, message);
    }

    /**
     * @param subject the object to perform validation on.
     * @return a {@link VerifiableIntArray} instance for the given subject.
     */
    public VerifiableIntArray<X> that(int[] subject) {
        return new VerifiableIntArray<>(failedValidationExecutor, subject, message);
    }

    /**
     * @param subject the object to perform validation on.
     * @return a {@link VerifiableLongArray} instance for the given subject.
     */
    public VerifiableLongArray<X> that(long[] subject) {
        return new VerifiableLongArray<>(failedValidationExecutor, subject, message);
    }

    /**
     * @param subject the object to perform validation on.
     * @return a {@link VerifiableShortArray} instance for the given subject.
     */
    public VerifiableShortArray<X> that(short[] subject) {
        return new VerifiableShortArray<>(failedValidationExecutor, subject, message);
    }

    // - - - - - - - - - - - - - - - - - - - - - -
    // Primitives
    // - - - - - - - - - - - - - - - - - - - - - -

    /**
     * @param subject the object to perform validation on.
     * @return a {@link VerifiablePrimitiveBoolean} instance for the given subject.
     */
    public VerifiablePrimitiveBoolean<X> that(boolean subject) {
        return new VerifiablePrimitiveBoolean<>(failedValidationExecutor, subject, message);
    }

    /**
     * @param subject the object to perform validation on.
     * @return a {@link VerifiablePrimitiveByte} instance for the given subject.
     */
    public VerifiablePrimitiveByte<X> that(byte subject) {
        return new VerifiablePrimitiveByte<>(failedValidationExecutor, subject, message);
    }

    /**
     * @param subject the object to perform validation on.
     * @return a {@link VerifiablePrimitiveChar} instance for the given subject.
     */
    public VerifiablePrimitiveChar<X> that(char subject) {
        return new VerifiablePrimitiveChar<>(failedValidationExecutor, subject, message);
    }

    /**
     * @param subject the object to perform validation on.
     * @return a {@link VerifiablePrimitiveDouble} instance for the given subject.
     */
    public VerifiablePrimitiveDouble<X> that(double subject) {
        return new VerifiablePrimitiveDouble<>(failedValidationExecutor, subject, message);
    }

    /**
     * @param subject the object to perform validation on.
     * @return a {@link VerifiablePrimitiveFloat} instance for the given subject.
     */
    public VerifiablePrimitiveFloat<X> that(float subject) {
        return new VerifiablePrimitiveFloat<>(failedValidationExecutor, subject, message);
    }

    /**
     * @param subject the object to perform validation on.
     * @return a {@link VerifiablePrimitiveInt} instance for the given subject.
     */
    public VerifiablePrimitiveInt<X> that(int subject) {
        return new VerifiablePrimitiveInt<>(failedValidationExecutor, subject, message);
    }

    /**
     * @param subject the object to perform validation on.
     * @return a {@link VerifiablePrimitiveLong} instance for the given subject.
     */
    public VerifiablePrimitiveLong<X> that(long subject) {
        return new VerifiablePrimitiveLong<>(failedValidationExecutor, subject, message);
    }

    /**
     * @param subject the object to perform validation on.
     * @return a {@link VerifiablePrimitiveShort} instance for the given subject.
     */
    public VerifiablePrimitiveShort<X> that(short subject) {
        return new VerifiablePrimitiveShort<>(failedValidationExecutor, subject, message);
    }

    // - - - - - - - - - - - - - - - - - - - - - -
    // Objects
    // - - - - - - - - - - - - - - - - - - - - - -

    /**
     * @param subject the object to perform validation on.
     * @return a {@link VerifiableBoolean} instance for the given subject.
     */
    public VerifiableBoolean<X> that(Boolean subject) {
        return new VerifiableBoolean<>(failedValidationExecutor, subject, message);
    }

    /**
     * @param subject the object to perform validation on.
     * @return a {@link VerifiableByte} instance for the given subject.
     */
    public VerifiableByte<X> that(Byte subject) {
        return new VerifiableByte<>(failedValidationExecutor, subject, message);
    }

    /**
     * @param subject the object to perform validation on.
     * @return a {@link VerifiableCharacter} instance for the given subject.
     */
    public VerifiableCharacter<X> that(Character subject) {
        return new VerifiableCharacter<>(failedValidationExecutor, subject, message);
    }

    /**
     * @param subject the object to perform validation on.
     * @param <T> the class being validated.
     * @return a {@link VerifiableClass} instance for the given subject.
     */
    public <T> VerifiableClass<T, X> that(Class<T> subject) {
        return new VerifiableClass<>(failedValidationExecutor, subject, message);
    }

    /**
     * @param subject the object to perform validation on.
     * @param <E> the type of the objects in the collection.
     * @param <T> the type of the Collection (e.g. list, map, etc).
     * @return a {@link VerifiableCollection} instance for the given subject.
     */
    public <E, T extends Collection<E>> VerifiableCollection<E, T, X> that(T subject) {
        return new VerifiableCollection<>(failedValidationExecutor, subject, message);
    }

    /**
     * @param subject the object to perform validation on.
     * @return a {@link VerifiableDouble} instance for the given subject.
     */
    public VerifiableDouble<X> that(Double subject) {
        return new VerifiableDouble<>(failedValidationExecutor, subject, message);
    }

    /**
     * @param subject the object to perform validation on.
     * @return a {@link VerifiableFloat} instance for the given subject.
     */
    public VerifiableFloat<X> that(Float subject) {
        return new VerifiableFloat<>(failedValidationExecutor, subject, message);
    }

    /**
     * @param subject the object to perform validation on.
     * @return a {@link VerifiableInteger} instance for the given subject.
     */
    public VerifiableInteger<X> that(Integer subject) {
        return new VerifiableInteger<>(failedValidationExecutor, subject, message);
    }

    /**
     * @param subject the object to perform validation on.
     * @return a {@link VerifiableLong} instance for the given subject.
     */
    public VerifiableLong<X> that(Long subject) {
        return new VerifiableLong<>(failedValidationExecutor, subject, message);
    }

    /**
     * This is the default object validation for subjects that
     * don't match any of the other pre-defined types.
     *
     * @param subject the object to perform validation on.
     * @param <T>     the type of the subject.
     * @return a {@link VerifiableObject} instance for the given subject.
     */
    public <T> VerifiableObject<T, X> that(T subject) {
        return new VerifiableObject<>(failedValidationExecutor, subject, message);
    }

    /**
     * @param subject the object to perform validation on.
     * @return a {@link VerifiableShort} instance for the given subject.
     */
    public VerifiableShort<X> that(Short subject) {
        return new VerifiableShort<>(failedValidationExecutor, subject, message);
    }

    /**
     * @param subject the object to perform validation on.
     * @return a {@link VerifiableString} instance for the given subject.
     */
    public VerifiableString<X> that(String subject) {
        return new VerifiableString<>(failedValidationExecutor, subject, message);
    }
}
