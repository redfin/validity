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

import com.redfin.validity.predicates.AbstractDescriptivePredicate;
import java.util.Arrays;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

/**
 * Static class containing various utilities for the Validity library.
 */
public final class ValidityUtils {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Constants
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private static final String NON_INSTANTIABLE_MESSAGE = "Cannot instantiate this class";
    private static final String NULL_ARGUMENT_MESSAGE = "May not have null as the value for the argument: ";
    private static final String NULL_THROWABLE_FROM_HANDLER = "A null throwable was returned from the validation handler";
    private static final String UNKNOWN_PREDICATE_PREFIX = "unknown predicate: ";
    private static final String NULL = "null";
    private static final String TRUNCATE_FORMAT = "%s ...";

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Static Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * @return the String prefix for unknown predicate descriptions and toString methods.
     */
    public static String unknownPredicatePrefix() {
        return UNKNOWN_PREDICATE_PREFIX;
    }

    /**
     * @return the String message for non-instantiable errors.
     */
    public static String nonInstantiableMessage() {
        return NON_INSTANTIABLE_MESSAGE;
    }

    /**
     * @return the String message for a null throwable being returned from
     * a failed validation handler's throwable function.
     */
    public static String nullThrowableFromFunction() {
        return NULL_THROWABLE_FROM_HANDLER;
    }

    /**
     * @param argumentName the String argument name that was null.
     *                     May not be null.
     * @return the String message for exceptions for null arguments.
     * @throws NullPointerException if argumentName is null.
     */
    public static String nullArgumentMessage(String argumentName) {
        if (null == argumentName) {
            throw new NullPointerException("Cannot call nullArgumentMessage with a null argumentName");
        }
        return NULL_ARGUMENT_MESSAGE + argumentName;
    }

    // --------------------------------------------------------------
    // Describe Methods
    // --------------------------------------------------------------

    /*
     * These mostly just return the String valueOf or toString
     * methods for the various types. But by consistently using
     * these methods, we can wrap or decorate the values
     * later and it also switches between deep array and regular
     * array toString as well.
     */

    // - - - - - - - - - - - - - - - - - - - - - -
    // Primitive Arrays
    // - - - - - - - - - - - - - - - - - - - - - -

    /**
     * @param value the value to convert into a String.
     * @return a String representation of the given value.
     */
    public static String describe(boolean[] value) {
        return transformDescription(Arrays.toString(value));
    }

    /**
     * @param value the value to convert into a String.
     * @return a String representation of the given value.
     */
    public static String describe(byte[] value) {
        return transformDescription(Arrays.toString(value));
    }

    /**
     * @param value the value to convert into a String.
     * @return a String representation of the given value.
     */
    public static String describe(char[] value) {
        return transformDescription(Arrays.toString(value));
    }

    /**
     * @param value the value to convert into a String.
     * @return a String representation of the given value.
     */
    public static String describe(double[] value) {
        return transformDescription(Arrays.toString(value));
    }

    /**
     * @param value the value to convert into a String.
     * @return a String representation of the given value.
     */
    public static String describe(float[] value) {
        return transformDescription(Arrays.toString(value));
    }

    /**
     * @param value the value to convert into a String.
     * @return a String representation of the given value.
     */
    public static String describe(int[] value) {
        return transformDescription(Arrays.toString(value));
    }

    /**
     * @param value the value to convert into a String.
     * @return a String representation of the given value.
     */
    public static String describe(long[] value) {
        return transformDescription(Arrays.toString(value));
    }

    /**
     * @param value the value to convert into a String.
     * @return a String representation of the given value.
     */
    public static String describe(short[] value) {
        return transformDescription(Arrays.toString(value));
    }

    // - - - - - - - - - - - - - - - - - - - - - -
    // Predicates
    // - - - - - - - - - - - - - - - - - - - - - -

    /**
     * @param value the value to convert into a String.
     * @return a String representation of the given value.
     */
    public static String describe(IntPredicate value) {
        return describePredicate(value);
    }

    /**
     * @param value the value to convert into a String.
     * @return a String representation of the given value.
     */
    public static String describe(LongPredicate value) {
        return describePredicate(value);
    }

    /**
     * @param value the value to convert into a String.
     * @return a String representation of the given value.
     */
    public static String describe(DoublePredicate value) {
        return describePredicate(value);
    }

    /**
     * @param value the value to convert into a String.
     * @return a String representation of the given value.
     */
    public static String describe(Predicate<?> value) {
        return describePredicate(value);
    }

    private static String describePredicate(Object obj) {
        if (obj instanceof AbstractDescriptivePredicate) {
            return transformDescription(obj.toString());
        } else if (null != obj) {
            return transformDescription(UNKNOWN_PREDICATE_PREFIX + obj.toString());
        } else {
            return transformDescription(UNKNOWN_PREDICATE_PREFIX + NULL);
        }
    }

    // - - - - - - - - - - - - - - - - - - - - - -
    // Other Objects
    // - - - - - - - - - - - - - - - - - - - - - -

    /**
     * Describing a String will wrap it in quotation marks to delineate more clearly
     * the empty string, the string "null" versus an actual null string, etc.
     *
     * @param value the value to convert into a String.
     * @return a String representation of the given value.
     */
    public static String describe(String value) {
        return transformDescription((null == value) ? NULL : "\"" + value + "\"");
    }

    /**
     * @param value the value to convert into a String.
     * @param <T> the type of the object to describe.
     * @return a String representation of the given value.
     */
    public static <T> String describe(T value) {
        // Check for an array and use the Streams API to allow for recursive
        // conversion to a human-friendly string. The Arrays.toString method
        // only handles a single level so without this multi-dimensional arrays
        // of objects are not cleanly handled.
        if (value instanceof Object[]) {
            return transformDescription(Arrays.deepToString((Object[]) value));
        } else {
            return transformDescription((null == value) ? NULL : value.toString());
        }
    }

    /*
     * Transform all descriptions.
     *
     * Guard against an array or object with an insanely long string
     * that will make the thrown message un-readable.
     */

    private static String transformDescription(String value) {
        if (value != null && value.length() > 100) {
            return String.format(TRUNCATE_FORMAT, value.substring(0, 100));
        } else {
            return value;
        }
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Instance Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Force the class to be non-instantiable
     *
     * @throws AssertionError always.
     */
    private ValidityUtils() {
        throw new AssertionError(ValidityUtils.nonInstantiableMessage());
    }
}
