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

import java.util.Arrays;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

/**
 * A static class containing methods to convert other values into Strings. This enables
 * decorating certain value types and a consistent formatting to be applied, if desired.
 */
public final class Descriptions {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Constants
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private static final String NON_INSTANTIABLE_MESSAGE = "Cannot instantiate this class";
    private static final String UNSUPPORTED_EQUALS_MESSAGE = "This class cannot be treated as a value and does not support the Object equals method";
    private static final String UNSUPPORTED_HASH_CODE_MESSAGE = "This class cannot be treated as a value and does not support the Object hashCode method";
    private static final String NULL_ARGUMENT_MESSAGE = "May not have null as the value for the argument: ";
    private static final String UNKNOWN_PREDICATE_PREFIX = "unknown predicate: ";
    private static final String NULL = "null";

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Static Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    // --------------------------------------------------------------
    // Validity Scoped Methods
    // --------------------------------------------------------------

    /**
     * @return the String prefix for unknown predicate descriptions and toString methods.
     */
    static String unknownPredicatePrefix() {
        return UNKNOWN_PREDICATE_PREFIX;
    }

    /**
     * @return the String message for non-instantiable errors.
     */
    static String nonInstantiableMessage() {
        return NON_INSTANTIABLE_MESSAGE;
    }

    /**
     * @return the String message for unsupported equals method exceptions.
     */
    static String unsupportedEqualsMessage() {
        return UNSUPPORTED_EQUALS_MESSAGE;
    }

    /**
     * @return the String message for unsupported hashCode method exceptions.
     */
    static String unsupportedHashCodeMessage() {
        return UNSUPPORTED_HASH_CODE_MESSAGE;
    }

    /**
     * @param argumentName the String argument name that was null.
     *                     May not be null.
     * @return the String message for exceptions for null arguments.
     * @throws NullPointerException if argumentName is null.
     */
    static String nullArgumentMessage(String argumentName) {
        if (null == argumentName) {
            throw new NullPointerException("Cannot call nullArgumentMessage with a null argumentName");
        }
        return NULL_ARGUMENT_MESSAGE + argumentName;
    }

    // --------------------------------------------------------------
    // Public Methods
    // --------------------------------------------------------------

    /**
     * @param value the value to convert into a String.
     * @return a String representation of the given value.
     */
    public static String describe(short value) {
        return String.valueOf(value);
    }

    /**
     * @param value the value to convert into a String.
     * @return a String representation of the given value.
     */
    public static String describe(short[] value) {
        return Arrays.toString(value);
    }

    /**
     * @param value the value to convert into a String.
     * @return a String representation of the given value.
     */
    public static String describe(int value) {
        return String.valueOf(value);
    }

    /**
     * @param value the value to convert into a String.
     * @return a String representation of the given value.
     */
    public static String describe(int[] value) {
        return Arrays.toString(value);
    }

    /**
     * @param value the value to convert into a String.
     * @return a String representation of the given value.
     */
    public static String describe(long value) {
        return String.valueOf(value);
    }

    /**
     * @param value the value to convert into a String.
     * @return a String representation of the given value.
     */
    public static String describe(long[] value) {
        return Arrays.toString(value);
    }

    /**
     * @param value the value to convert into a String.
     * @return a String representation of the given value.
     */
    public static String describe(float value) {
        return String.valueOf(value);
    }

    /**
     * @param value the value to convert into a String.
     * @return a String representation of the given value.
     */
    public static String describe(float[] value) {
        return Arrays.toString(value);
    }

    /**
     * @param value the value to convert into a String.
     * @return a String representation of the given value.
     */
    public static String describe(double value) {
        return String.valueOf(value);
    }

    /**
     * @param value the value to convert into a String.
     * @return a String representation of the given value.
     */
    public static String describe(double[] value) {
        return Arrays.toString(value);
    }

    /**
     * @param value the value to convert into a String.
     * @return a String representation of the given value.
     */
    public static String describe(byte value) {
        return String.valueOf(value);
    }

    /**
     * @param value the value to convert into a String.
     * @return a String representation of the given value.
     */
    public static String describe(byte[] value) {
        return Arrays.toString(value);
    }

    /**
     * @param value the value to convert into a String.
     * @return a String representation of the given value.
     */
    public static String describe(boolean value) {
        return String.valueOf(value);
    }

    /**
     * @param value the value to convert into a String.
     * @return a String representation of the given value.
     */
    public static String describe(boolean[] value) {
        return Arrays.toString(value);
    }

    /**
     * @param value the value to convert into a String.
     * @return a String representation of the given value.
     */
    public static String describe(char value) {
        return String.valueOf(value);
    }

    /**
     * @param value the value to convert into a String.
     * @return a String representation of the given value.
     */
    public static String describe(char[] value) {
        return Arrays.toString(value);
    }

    /**
     * @param value the value to convert into a String.
     * @return a String representation of the given value.
     */
    public static String describe(String value) {
        return (null == value) ? NULL : "\"" + value + "\"";
    }

    /**
     * @param value the value to convert into a String.
     * @return a String representation of the given value.
     */
    public static String describe(DoublePredicate value) {
        return describePredicateHelper(value);
    }

    /**
     * @param value the value to convert into a String.
     * @return a String representation of the given value.
     */
    public static String describe(IntPredicate value) {
        return describePredicateHelper(value);
    }

    /**
     * @param value the value to convert into a String.
     * @return a String representation of the given value.
     */
    public static String describe(LongPredicate value) {
        return describePredicateHelper(value);
    }

    /**
     * @param value the value to convert into a String.
     * @return a String representation of the given value.
     */
    public static String describe(Predicate<?> value) {
        return describePredicateHelper(value);
    }

    private static String describePredicateHelper(Object value) {
        if (value instanceof AbstractDescriptivePredicate) {
            return value.toString();
        } else {
            return (null == value) ? NULL : UNKNOWN_PREDICATE_PREFIX + value.toString();
        }
    }

    /**
     * @param value the value to convert into a String.
     * @return a String representation of the given value.
     */
    public static <T> String describe(T value) {
        // Check for an array and use the Streams API to allow for recursive
        // conversion to a human-friendly string. The Arrays.toString method
        // only handles a single level so without this multi-dimensional arrays
        // of objects are not cleanly handled.
        if (value instanceof Object[]) {
            return Arrays.deepToString((Object[]) value);
        } else {
            return (null == value) ? NULL : value.toString();
        }
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Private Constructor
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Force the class to be non-instantiable
     *
     * @throws AssertionError always.
     */
    private Descriptions() {
        throw new AssertionError(Descriptions.nonInstantiableMessage());
    }
}
