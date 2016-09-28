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
import java.util.stream.Collectors;

/**
 * todo
 */
final class Messages {

    private static final String NON_INSTANTIABLE_MESSAGE = "Cannot instantiate this class";
    private static final String UNSUPPORTED_EQUALS_MESSAGE = "This class cannot be treated as a value and does not support the Object equals method.";
    private static final String UNSUPPORTED_HASH_CODE_MESSAGE = "This class cannot be treated as a value and does not support the Object hashCode method.";
    private static final String NULL_ARGUMENT_MESSAGE = "May not have null as the value for the argument: ";
    private static final String UNKNOWN_PREDICATE_PREFIX = "unknown predicate: ";

    static String nonInstantiableMessage() {
        return NON_INSTANTIABLE_MESSAGE;
    }

    static String unsupportedEqualsMessage() {
        return UNSUPPORTED_EQUALS_MESSAGE;
    }

    static String unsupportedHashCodeMessage() {
        return UNSUPPORTED_HASH_CODE_MESSAGE;
    }

    static String nullArgumentMessage(String argumentName) {
        if (null == argumentName) {
            throw new NullPointerException(NULL_ARGUMENT_MESSAGE + "argumentName");
        }
        return NULL_ARGUMENT_MESSAGE + argumentName;
    }

    private static final String NULL = "null";

    static String describe(short value) {
        return String.valueOf(value);
    }

    static String describe(short[] value) {
        return Arrays.toString(value);
    }

    static String describe(int value) {
        return String.valueOf(value);
    }

    static String describe(int[] value) {
        return Arrays.toString(value);
    }

    static String describe(long value) {
        return String.valueOf(value);
    }

    static String describe(long[] value) {
        return Arrays.toString(value);
    }

    static String describe(float value) {
        return String.valueOf(value);
    }

    static String describe(float[] value) {
        return Arrays.toString(value);
    }

    static String describe(double value) {
        return String.valueOf(value);
    }

    static String describe(double[] value) {
        return Arrays.toString(value);
    }

    static String describe(byte value) {
        return String.valueOf(value);
    }

    static String describe(byte[] value) {
        return Arrays.toString(value);
    }

    static String describe(boolean value) {
        return String.valueOf(value);
    }

    static String describe(boolean[] value) {
        return Arrays.toString(value);
    }

    static String describe(char value) {
        return String.valueOf(value);
    }

    static String describe(char[] value) {
        return Arrays.toString(value);
    }

    static String describe(String value) {
        return (null == value) ? NULL : "\"" + value + "\"";
    }

    static String describePredicate(DoublePredicate value) {
        return describePredicateHelper(value);
    }

    static String describePredicate(IntPredicate value) {
        return describePredicateHelper(value);
    }

    static String describePredicate(LongPredicate value) {
        return describePredicateHelper(value);
    }

    static String describePredicate(Predicate<?> value) {
        return describePredicateHelper(value);
    }

    static String describePredicate(AbstractDescriptivePredicate value) {
        return describePredicateHelper(value);
    }

    private static String describePredicateHelper(Object value) {
        if (null == value) {
            throw new NullPointerException(Messages.nullArgumentMessage("value"));
        }
        if (value instanceof AbstractDescriptivePredicate) {
            return ((AbstractDescriptivePredicate) value).getDescription();
        } else {
            return UNKNOWN_PREDICATE_PREFIX + value.toString();
        }
    }

    static <T> String describe(T value) {
        if (value instanceof Object[]) {
            return "[" + Arrays.stream((Object[]) value)
                               .map(Messages::describe)
                               .collect(Collectors.joining(",")) + "]";
        } else {
            return (null == value) ? NULL : value.toString();
        }
    }

    static String buildFailSatisfiesMessage(Object expected) {
        if (null == expected) {
            throw new NullPointerException(Messages.nullArgumentMessage("expected"));
        }
        if (expected instanceof AbstractDescriptivePredicate) {
            return expected.toString();
        } else {
            return UNKNOWN_PREDICATE_PREFIX + expected.toString();
        }
    }

    private Messages() {
        throw new AssertionError(Messages.nonInstantiableMessage());
    }
}
