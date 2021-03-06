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
import com.redfin.validity.verifiers.objects.VerifiableDuration;
import com.redfin.validity.verifiers.objects.VerifiableFloat;
import com.redfin.validity.verifiers.objects.VerifiableInstant;
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

import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.function.Supplier;

/**
 * An abstract class that acts as a factory. It holds the information passed in
 * until a test subject for validation is given. At that point the correct
 * type of verifiable is returned depending upon the type of the subject.
 *
 * @param <X> the type to be thrown from failed validation.
 * @param <F> the type of the implementing sub-class.
 */
public abstract class AbstractVerifiableFactory<X extends Throwable,
            F extends AbstractVerifiableFactory<X, F>> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Fields
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private final Supplier<String> messageSupplier;
    private final FailedValidationExecutor<X> failedValidationExecutor;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Instance Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Create a new {@link AbstractVerifiableFactory} instance with the given message and failed validation
     * executor.
     *
     * @param messageSupplier          the {@link Supplier} of the String message to pre-pend the failure message with, if necessary.
     *                                 May not be null.
     * @param failedValidationExecutor the {@link FailedValidationExecutor} to use in case
     *                                 of failed validation.
     *                                 May not be null.
     *
     * @throws NullPointerException if messageSupplier or failedValidationExecutor is null.
     */
    public AbstractVerifiableFactory(Supplier<String> messageSupplier,
                                     FailedValidationExecutor<X> failedValidationExecutor) {
        if (null == messageSupplier) {
            throw new NullPointerException(ValidityUtils.nullArgumentMessage("messageSupplier"));
        }
        this.messageSupplier = messageSupplier;
        if (null == failedValidationExecutor) {
            throw new NullPointerException(ValidityUtils.nullArgumentMessage("failedValidationExecutor"));
        }
        this.failedValidationExecutor = failedValidationExecutor;
    }

    /**
     * @param message the String message prefix for the verifiable factory that is
     *                to be returned.
     *                May be null.
     *
     * @return a new instance of the verifiable factory with the given message and the
     * current failed validation executor.
     */
    public F withMessage(String message) {
        return withMessage(() -> message);
    }

    /**
     * @param messageSupplier the {@link Supplier} of the String message prefix for the verifiable
     *                        factory that is to be returned.
     *                        May be null.
     *
     * @return a new instance of the verifiable factory with the given message and the
     * current failed validation executor.
     */
    public F withMessage(Supplier<String> messageSupplier) {
        if (null == messageSupplier) {
            messageSupplier = () -> null;
        }
        return getFactory(messageSupplier, failedValidationExecutor);
    }

    /**
     * Get an instance of the verifiable factory with the given message
     * and failed validation executor. It is not required that it be a
     * new instance, but it must have the given message and validation executor.
     *
     * @param messageSupplier          the {@link Supplier} of the String message for the verifiable factory.
     *                                 May not be null.
     * @param failedValidationExecutor the failed validation executor for the factory.
     *                                 May not be null.
     *
     * @return an instance of the implementing subclass verifiable factory with
     * the given parameters.
     *
     * @throws IllegalArgumentException if messageSupplier or failedValidationExecutor are null.
     */
    protected abstract F getFactory(Supplier<String> messageSupplier,
                                    FailedValidationExecutor<X> failedValidationExecutor);

    /**
     * @return the message for this verifiable factory.
     */
    protected Supplier<String> getMessageSupplier() {
        return messageSupplier;
    }

    /**
     * @return the {@link FailedValidationExecutor} for this verifiable factory.
     */
    protected FailedValidationExecutor<X> getFailedValidationExecutor() {
        return failedValidationExecutor;
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
     * @param <E>     the type of the elements in the array.
     *
     * @return a {@link VerifiableArray} instance for the given subject.
     */
    public <E> VerifiableArray<E, X> that(E[] subject) {
        return new VerifiableArray<>(failedValidationExecutor, subject, messageSupplier);
    }

    /**
     * @param subject the object to perform validation on.
     *
     * @return a {@link VerifiableBooleanArray} instance for the given subject.
     */
    public VerifiableBooleanArray<X> that(boolean[] subject) {
        return new VerifiableBooleanArray<>(failedValidationExecutor, subject, messageSupplier);
    }

    /**
     * @param subject the object to perform validation on.
     *
     * @return a {@link VerifiableByteArray} instance for the given subject.
     */
    public VerifiableByteArray<X> that(byte[] subject) {
        return new VerifiableByteArray<>(failedValidationExecutor, subject, messageSupplier);
    }

    /**
     * @param subject the object to perform validation on.
     *
     * @return a {@link VerifiableCharArray} instance for the given subject.
     */
    public VerifiableCharArray<X> that(char[] subject) {
        return new VerifiableCharArray<>(failedValidationExecutor, subject, messageSupplier);
    }

    /**
     * @param subject the object to perform validation on.
     *
     * @return a {@link VerifiableDoubleArray} instance for the given subject.
     */
    public VerifiableDoubleArray<X> that(double[] subject) {
        return new VerifiableDoubleArray<>(failedValidationExecutor, subject, messageSupplier);
    }

    /**
     * @param subject the object to perform validation on.
     *
     * @return a {@link VerifiableFloatArray} instance for the given subject.
     */
    public VerifiableFloatArray<X> that(float[] subject) {
        return new VerifiableFloatArray<>(failedValidationExecutor, subject, messageSupplier);
    }

    /**
     * @param subject the object to perform validation on.
     *
     * @return a {@link VerifiableIntArray} instance for the given subject.
     */
    public VerifiableIntArray<X> that(int[] subject) {
        return new VerifiableIntArray<>(failedValidationExecutor, subject, messageSupplier);
    }

    /**
     * @param subject the object to perform validation on.
     *
     * @return a {@link VerifiableLongArray} instance for the given subject.
     */
    public VerifiableLongArray<X> that(long[] subject) {
        return new VerifiableLongArray<>(failedValidationExecutor, subject, messageSupplier);
    }

    /**
     * @param subject the object to perform validation on.
     *
     * @return a {@link VerifiableShortArray} instance for the given subject.
     */
    public VerifiableShortArray<X> that(short[] subject) {
        return new VerifiableShortArray<>(failedValidationExecutor, subject, messageSupplier);
    }

    // - - - - - - - - - - - - - - - - - - - - - -
    // Primitives
    // - - - - - - - - - - - - - - - - - - - - - -

    /**
     * @param subject the object to perform validation on.
     *
     * @return a {@link VerifiablePrimitiveBoolean} instance for the given subject.
     */
    public VerifiablePrimitiveBoolean<X> that(boolean subject) {
        return new VerifiablePrimitiveBoolean<>(failedValidationExecutor, subject, messageSupplier);
    }

    /**
     * @param subject the object to perform validation on.
     *
     * @return a {@link VerifiablePrimitiveByte} instance for the given subject.
     */
    public VerifiablePrimitiveByte<X> that(byte subject) {
        return new VerifiablePrimitiveByte<>(failedValidationExecutor, subject, messageSupplier);
    }

    /**
     * @param subject the object to perform validation on.
     *
     * @return a {@link VerifiablePrimitiveChar} instance for the given subject.
     */
    public VerifiablePrimitiveChar<X> that(char subject) {
        return new VerifiablePrimitiveChar<>(failedValidationExecutor, subject, messageSupplier);
    }

    /**
     * @param subject the object to perform validation on.
     *
     * @return a {@link VerifiablePrimitiveDouble} instance for the given subject.
     */
    public VerifiablePrimitiveDouble<X> that(double subject) {
        return new VerifiablePrimitiveDouble<>(failedValidationExecutor, subject, messageSupplier);
    }

    /**
     * @param subject the object to perform validation on.
     *
     * @return a {@link VerifiablePrimitiveFloat} instance for the given subject.
     */
    public VerifiablePrimitiveFloat<X> that(float subject) {
        return new VerifiablePrimitiveFloat<>(failedValidationExecutor, subject, messageSupplier);
    }

    /**
     * @param subject the object to perform validation on.
     *
     * @return a {@link VerifiablePrimitiveInt} instance for the given subject.
     */
    public VerifiablePrimitiveInt<X> that(int subject) {
        return new VerifiablePrimitiveInt<>(failedValidationExecutor, subject, messageSupplier);
    }

    /**
     * @param subject the object to perform validation on.
     *
     * @return a {@link VerifiablePrimitiveLong} instance for the given subject.
     */
    public VerifiablePrimitiveLong<X> that(long subject) {
        return new VerifiablePrimitiveLong<>(failedValidationExecutor, subject, messageSupplier);
    }

    /**
     * @param subject the object to perform validation on.
     *
     * @return a {@link VerifiablePrimitiveShort} instance for the given subject.
     */
    public VerifiablePrimitiveShort<X> that(short subject) {
        return new VerifiablePrimitiveShort<>(failedValidationExecutor, subject, messageSupplier);
    }

    // - - - - - - - - - - - - - - - - - - - - - -
    // Objects
    // - - - - - - - - - - - - - - - - - - - - - -

    // - - - - - - - - - - - - - - - - -
    // Boxed Primitive Types
    // - - - - - - - - - - - - - - - - -

    /**
     * @param subject the object to perform validation on.
     *
     * @return a {@link VerifiableBoolean} instance for the given subject.
     */
    public VerifiableBoolean<X> that(Boolean subject) {
        return new VerifiableBoolean<>(failedValidationExecutor, subject, messageSupplier);
    }

    /**
     * @param subject the object to perform validation on.
     *
     * @return a {@link VerifiableByte} instance for the given subject.
     */
    public VerifiableByte<X> that(Byte subject) {
        return new VerifiableByte<>(failedValidationExecutor, subject, messageSupplier);
    }

    /**
     * @param subject the object to perform validation on.
     *
     * @return a {@link VerifiableCharacter} instance for the given subject.
     */
    public VerifiableCharacter<X> that(Character subject) {
        return new VerifiableCharacter<>(failedValidationExecutor, subject, messageSupplier);
    }

    /**
     * @param subject the object to perform validation on.
     *
     * @return a {@link VerifiableDouble} instance for the given subject.
     */
    public VerifiableDouble<X> that(Double subject) {
        return new VerifiableDouble<>(failedValidationExecutor, subject, messageSupplier);
    }

    /**
     * @param subject the object to perform validation on.
     *
     * @return a {@link VerifiableFloat} instance for the given subject.
     */
    public VerifiableFloat<X> that(Float subject) {
        return new VerifiableFloat<>(failedValidationExecutor, subject, messageSupplier);
    }

    /**
     * @param subject the object to perform validation on.
     *
     * @return a {@link VerifiableInteger} instance for the given subject.
     */
    public VerifiableInteger<X> that(Integer subject) {
        return new VerifiableInteger<>(failedValidationExecutor, subject, messageSupplier);
    }

    /**
     * @param subject the object to perform validation on.
     *
     * @return a {@link VerifiableLong} instance for the given subject.
     */
    public VerifiableLong<X> that(Long subject) {
        return new VerifiableLong<>(failedValidationExecutor, subject, messageSupplier);
    }

    /**
     * @param subject the object to perform validation on.
     *
     * @return a {@link VerifiableShort} instance for the given subject.
     */
    public VerifiableShort<X> that(Short subject) {
        return new VerifiableShort<>(failedValidationExecutor, subject, messageSupplier);
    }

    // - - - - - - - - - - - - - - - - -
    // Time Object Types
    // - - - - - - - - - - - - - - - - -

    /**
     * @param subject the object to perform validation on.
     *
     * @return a {@link VerifiableDuration} instance for the given subject.
     */
    public VerifiableDuration<X> that(Duration subject) {
        return new VerifiableDuration<>(failedValidationExecutor, subject, messageSupplier);
    }

    /**
     * @param subject the object to perform validation on.
     *
     * @return a {@link VerifiableInstant} instance for the given subject.
     */
    public VerifiableInstant<X> that(Instant subject) {
        return new VerifiableInstant<>(failedValidationExecutor, subject, messageSupplier);
    }

    // - - - - - - - - - - - - - - - - -
    // Other Object Types
    // - - - - - - - - - - - - - - - - -

    /**
     * @param subject the object to perform validation on.
     * @param <T>     the class being validated.
     *
     * @return a {@link VerifiableClass} instance for the given subject.
     */
    public <T> VerifiableClass<T, X> that(Class<T> subject) {
        return new VerifiableClass<>(failedValidationExecutor, subject, messageSupplier);
    }

    /**
     * @param subject the object to perform validation on.
     * @param <E>     the type of the objects in the collection.
     * @param <T>     the type of the Collection (e.g. list, map, etc).
     *
     * @return a {@link VerifiableCollection} instance for the given subject.
     */
    public <E, T extends Collection<E>> VerifiableCollection<E, T, X> that(T subject) {
        return new VerifiableCollection<>(failedValidationExecutor, subject, messageSupplier);
    }

    /**
     * @param subject the object to perform validation on.
     *
     * @return a {@link VerifiableString} instance for the given subject.
     */
    public VerifiableString<X> that(String subject) {
        return new VerifiableString<>(failedValidationExecutor, subject, messageSupplier);
    }

    /**
     * This is the default object validation for subjects that
     * don't match any of the other pre-defined types.
     *
     * @param subject the object to perform validation on.
     * @param <T>     the type of the subject.
     *
     * @return a {@link VerifiableObject} instance for the given subject.
     */
    public <T> VerifiableObject<T, X> that(T subject) {
        return new VerifiableObject<>(failedValidationExecutor, subject, messageSupplier);
    }

    // --------------------------------------------------------------
    // Not a value type enforcement
    // --------------------------------------------------------------

    /**
     * @throws UnsupportedOperationException always.
     * @deprecated AbstractVerifiableFactory objects cannot be tested for equality.
     */
    @Deprecated
    @Override
    public final boolean equals(Object obj) {
        throw new UnsupportedOperationException("This class cannot be treated as a value and does not support the Object equals method");
    }

    /**
     * @throws UnsupportedOperationException always.
     * @deprecated AbstractVerifiableFactory objects cannot be hashed.
     */
    @Deprecated
    @Override
    public final int hashCode() {
        throw new UnsupportedOperationException("This class cannot be treated as a value and does not support the Object hashCode method");
    }
}
