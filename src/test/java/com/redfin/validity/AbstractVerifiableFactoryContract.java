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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings("ConstantConditions")
interface AbstractVerifiableFactoryContract<X extends Throwable,
                                            F extends AbstractVerifiableFactory<X, F>>
  extends NotValueTypeContract<AbstractVerifiableFactory<X, F>> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    default void testWithMessageReturnsDifferentInstance() {
        String message = "hello";
        Assertions.assertTrue(getNotValueTypeInstance().withMessage(message) != getNotValueTypeInstance().withMessage(message),
                              "AbstractVerifiableFactory should return a new instance for withMessage(String)");
    }

    @Test
    default void testWithMessageFactoryReturnsGivenMessage() {
        String message = "world";
        Assertions.assertEquals(message,
                                getNotValueTypeInstance().withMessage(message).getMessageSupplier().get(),
                                "AbstractVerifiableFactory should return given message.");
    }

    @Test
    default void testAbstractVerifiableFactoryWithMessageReturnsFactoryWithGivenExecutor() {
        AbstractVerifiableFactory<X, F> factory = getNotValueTypeInstance();
        FailedValidationExecutor<X> executor = factory.getFailedValidationExecutor();
        Assertions.assertTrue(executor == factory.withMessage("hello").getFailedValidationExecutor(),
                              "AbstractVerifiableFactory withMessage(String) should return factory with same validation executor.");
    }

    // --------------------------------------------------------------
    // Factory method tests
    // --------------------------------------------------------------

    /*
     * The following tests have some redundancy built-in to them. That is on purpose
     * to quickly catch any ambiguity issues with the method overloading done in the
     * AbstractVerifiableFactory class.
     */

    // - - - - - - - - - - - - - - - - - - - - - -
    // Arrays
    // - - - - - - - - - - - - - - - - - - - - - -

    @Test
    default void testReturnsExpectedVerifiableArray() {
        Integer[] subject = new Integer[0];
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableArray<?, ?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableArrayForNullSubject() {
        Integer[] subject = null;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableArray<?, ?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableBooleanArray() {
        boolean[] subject = new boolean[0];
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableBooleanArray<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableBooleanArrayForNullSubject() {
        boolean[] subject = null;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableBooleanArray<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableByteArray() {
        byte[] subject = new byte[0];
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableByteArray<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableByteArrayForNullSubject() {
        byte[] subject = null;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableByteArray<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableCharArray() {
        char[] subject = new char[0];
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableCharArray<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableCharArrayForNullSubject() {
        char[] subject = null;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableCharArray<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableDoubleArray() {
        double[] subject = new double[0];
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableDoubleArray<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableDoubleArrayForNullSubject() {
        double[] subject = null;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableDoubleArray<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableFloatArray() {
        float[] subject = new float[0];
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableFloatArray<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableFloatArrayForNullSubject() {
        float[] subject = null;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableFloatArray<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableIntArray() {
        int[] subject = new int[0];
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableIntArray<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableIntArrayForNullSubject() {
        int[] subject = null;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableIntArray<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableLongArray() {
        long[] subject = new long[0];
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableLongArray<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableLongArrayForNullSubject() {
        long[] subject = null;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableLongArray<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableShortArray() {
        short[] subject = new short[0];
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableShortArray<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableShortArrayForNullSubject() {
        short[] subject = null;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableShortArray<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    // - - - - - - - - - - - - - - - - - - - - - -
    // Primitives
    // - - - - - - - - - - - - - - - - - - - - - -

    @Test
    default void testReturnsExpectedVerifiablePrimitiveBoolean() {
        boolean subject = true;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiablePrimitiveBoolean<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiablePrimitiveByte() {
        byte subject = 0;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiablePrimitiveByte<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiablePrimitiveChar() {
        char subject = 0;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiablePrimitiveChar<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiablePrimitiveDouble() {
        double subject = 0;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiablePrimitiveDouble<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiablePrimitiveFloat() {
        float subject = 0;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiablePrimitiveFloat<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiablePrimitiveInt() {
        int subject = 0;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiablePrimitiveInt<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiablePrimitiveLong() {
        long subject = 0;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiablePrimitiveLong<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiablePrimitiveShort() {
        short subject = 0;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiablePrimitiveShort<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    // - - - - - - - - - - - - - - - - - - - - - -
    // Objects
    // - - - - - - - - - - - - - - - - - - - - - -

    @Test
    default void testReturnsExpectedVerifiableBoolean() {
        Boolean subject = true;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableBoolean<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableBooleanForNullSubject() {
        Boolean subject = null;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableBoolean<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableByte() {
        Byte subject = 0;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableByte<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableByteForNullSubject() {
        Byte subject = null;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableByte<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableCharacter() {
        Character subject = 0;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableCharacter<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableCharacterForNullSubject() {
        Character subject = null;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableCharacter<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableClass() {
        Class<?> subject = AbstractVerifiableFactoryContract.class;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableClass<?, ?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableClassForNullSubject() {
        Class<?> subject = null;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableClass<?, ?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableCollections() {
        Collection<String> subject = new ArrayList<>();
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableCollection<?, ?, ?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableCollectionsForNullSubject() {
        Collection<String> subject = null;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableCollection<?, ?, ?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableDouble() {
        Double subject = 0D;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableDouble<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableDoubleForNullSubject() {
        Double subject = null;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableDouble<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableFloat() {
        Float subject = 0F;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableFloat<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableInteger() {
        Integer subject = 0;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableInteger<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableIntegerForNullSubject() {
        Integer subject = null;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableInteger<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableLong() {
        Long subject = 0L;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableLong<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableLongForNullSubject() {
        Long subject = null;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableLong<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableObject() {
        Object subject = 0;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableObject<?, ?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableObjectForNullSubject() {
        Object subject = null;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableObject<?, ?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableShort() {
        Short subject = 0;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableShort<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableShortForNullSubject() {
        Short subject = null;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableShort<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableDuration() {
        Duration subject = Duration.ZERO;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableDuration<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableDurationForNullSubject() {
        Duration subject = null;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableDuration<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableInstant() {
        Instant subject = Instant.now();
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableInstant<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableInstantForNullSubject() {
        Instant subject = null;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableInstant<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableString() {
        String subject = "";
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableString<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }

    @Test
    default void testReturnsExpectedVerifiableStringForNullSubject() {
        String subject = null;
        Assertions.assertTrue(getNotValueTypeInstance().that(subject) instanceof VerifiableString<?>,
                              "AbstractVerifiableFactory should have returned a non-null object of the expected type.");
    }
}
