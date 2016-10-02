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
import com.redfin.validity.predicates.DescriptiveDoublePredicate;
import com.redfin.validity.predicates.DescriptiveIntPredicate;
import com.redfin.validity.predicates.DescriptiveLongPredicate;
import com.redfin.validity.predicates.DescriptivePredicate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

final class ValidityUtilsTest implements NonInstantiableContract<ValidityUtils> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test values & contract implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public Class<ValidityUtils> getNonInstantiableClassObject() {
        return ValidityUtils.class;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    void testNullArgumentMessageThrowsExceptionForNullName() {
        NullPointerException exception = Assertions.expectThrows(NullPointerException.class,
                                                                 () -> ValidityUtils.nullArgumentMessage(null));
        Assertions.assertEquals("Cannot call nullArgumentMessage with a null argumentName",
                                exception.getMessage(),
                                "The null argument message method should throw the expected exception for a null argument name.");
    }

    @Test
    void testStringsGetTruncated() {
        StringBuilder first = new StringBuilder("a");
        StringBuilder second = new StringBuilder("\"");
        for (int i = 0; i < 99; i++) {
            first.append("a");
            second.append("a");
        }
        Assertions.assertEquals(second.append(" ...").toString(),
                                ValidityUtils.describe(first.toString()),
                                "Long strings should get truncated.");
    }

    // --------------------------------------------------------------
    // General description tests
    // --------------------------------------------------------------

    // - - - - - - - - - - - - - - - - - - - - - -
    // Primitive array descriptions
    // - - - - - - - - - - - - - - - - - - - - - -

    @Test
    void testDescribeReturnsExpectedResultForPrimityArrayBoolean() {
        boolean[] subject = { true };
        Assertions.assertEquals(Arrays.toString(subject),
                                ValidityUtils.describe(subject),
                                "Arrays should be described as per the arrays to string method.");
    }

    @Test
    void testDescribeReturnsExpectedResultForPrimityArrayByte() {
        byte[] subject = { 0 };
        Assertions.assertEquals(Arrays.toString(subject),
                                ValidityUtils.describe(subject),
                                "Arrays should be described as per the arrays to string method.");
    }

    @Test
    void testDescribeReturnsExpectedResultForPrimityArrayChar() {
        char[] subject = { 0 };
        Assertions.assertEquals(Arrays.toString(subject),
                                ValidityUtils.describe(subject),
                                "Arrays should be described as per the arrays to string method.");
    }

    @Test
    void testDescribeReturnsExpectedResultForPrimityArrayDouble() {
        double[] subject = { 0 };
        Assertions.assertEquals(Arrays.toString(subject),
                                ValidityUtils.describe(subject),
                                "Arrays should be described as per the arrays to string method.");
    }

    @Test
    void testDescribeReturnsExpectedResultForPrimityArrayFloat() {
        float[] subject = { 0 };
        Assertions.assertEquals(Arrays.toString(subject),
                                ValidityUtils.describe(subject),
                                "Arrays should be described as per the arrays to string method.");
    }

    @Test
    void testDescribeReturnsExpectedResultForPrimityArrayInt() {
        int[] subject = { 0 };
        Assertions.assertEquals(Arrays.toString(subject),
                                ValidityUtils.describe(subject),
                                "Arrays should be described as per the arrays to string method.");
    }

    @Test
    void testDescribeReturnsExpectedResultForPrimityArrayLong() {
        long[] subject = { 0 };
        Assertions.assertEquals(Arrays.toString(subject),
                                ValidityUtils.describe(subject),
                                "Arrays should be described as per the arrays to string method.");
    }

    @Test
    void testDescribeReturnsExpectedResultForPrimityArrayShort() {
        short[] subject = { 0 };
        Assertions.assertEquals(Arrays.toString(subject),
                                ValidityUtils.describe(subject),
                                "Arrays should be described as per the arrays to string method.");
    }

    // - - - - - - - - - - - - - - - - - - - - - -
    // Predicate descriptions
    // - - - - - - - - - - - - - - - - - - - - - -

    @Test
    void testDescribeIntPredicateReturnsExpectedStringForNonDescriptive() {
        IntPredicate predicate = t -> t > 0;
        Assertions.assertEquals(ValidityUtils.unknownPredicatePrefix() + predicate.toString(),
                                ValidityUtils.describe(predicate),
                                "Non descriptive predicate should be consistently described.");
    }

    @Test
    void testDescribeIntPredicateReturnsExpectedStringForDescriptive() {
        DescriptiveIntPredicate predicate = new DescriptiveIntPredicate(AbstractDescriptivePredicate.TOKEN, t -> t > 0);
        Assertions.assertEquals(predicate.toString(),
                                ValidityUtils.describe(predicate),
                                "Descriptive predicate should be described by their own toString method.");
    }

    @Test
    void testDescribeIntPredicateReturnsExpectedStringForNull() {
        Assertions.assertEquals(ValidityUtils.unknownPredicatePrefix() + "null",
                                ValidityUtils.describe((DescriptiveIntPredicate) null),
                                "Non descriptive, null predicate should be consistently described.");
    }

    @Test
    void testDescribeLongPredicateReturnsExpectedStringForNonDescriptive() {
        LongPredicate predicate = t -> t > 0;
        Assertions.assertEquals(ValidityUtils.unknownPredicatePrefix() + predicate.toString(),
                                ValidityUtils.describe(predicate),
                                "Non descriptive predicate should be consistently described.");
    }

    @Test
    void testDescribeLongPredicateReturnsExpectedStringForDescriptive() {
        DescriptiveLongPredicate predicate = new DescriptiveLongPredicate(AbstractDescriptivePredicate.TOKEN, t -> t > 0);
        Assertions.assertEquals(predicate.toString(),
                                ValidityUtils.describe(predicate),
                                "Descriptive predicate should be described by their own toString method.");
    }

    @Test
    void testDescribeLongPredicateReturnsExpectedStringForNull() {
        Assertions.assertEquals(ValidityUtils.unknownPredicatePrefix() + "null",
                                ValidityUtils.describe((DescriptiveLongPredicate) null),
                                "Non descriptive, null predicate should be consistently described.");
    }

    @Test
    void testDescribeDoublePredicateReturnsExpectedStringForNonDescriptive() {
        DoublePredicate predicate = t -> t > 0;
        Assertions.assertEquals(ValidityUtils.unknownPredicatePrefix() + predicate.toString(),
                                ValidityUtils.describe(predicate),
                                "Non descriptive predicate should be consistently described.");
    }

    @Test
    void testDescribeDoublePredicateReturnsExpectedStringForDescriptive() {
        DescriptiveDoublePredicate predicate = new DescriptiveDoublePredicate(AbstractDescriptivePredicate.TOKEN, t -> t > 0);
        Assertions.assertEquals(predicate.toString(),
                                ValidityUtils.describe(predicate),
                                "Descriptive predicate should be described by their own toString method.");
    }

    @Test
    void testDescribeDoublePredicateReturnsExpectedStringForNull() {
        Assertions.assertEquals(ValidityUtils.unknownPredicatePrefix() + "null",
                                ValidityUtils.describe((DescriptiveDoublePredicate) null),
                                "Non descriptive, null predicate should be consistently described.");
    }

    @Test
    void testDescribePredicateReturnsExpectedStringForNonDescriptive() {
        Predicate<String> predicate = t -> null != t;
        Assertions.assertEquals(ValidityUtils.unknownPredicatePrefix() + predicate.toString(),
                                ValidityUtils.describe(predicate),
                                "Non descriptive predicate should be consistently described.");
    }

    @Test
    void testDescribePredicateReturnsExpectedStringForDescriptive() {
        DescriptivePredicate<String> predicate = new DescriptivePredicate<>(AbstractDescriptivePredicate.TOKEN, t -> null != t);
        Assertions.assertEquals(predicate.toString(),
                                ValidityUtils.describe(predicate),
                                "Descriptive predicate should be described by their own toString method.");
    }

    @Test
    void testDescribePredicateReturnsExpectedStringForNull() {
        Assertions.assertEquals(ValidityUtils.unknownPredicatePrefix() + "null",
                                ValidityUtils.describe((DescriptivePredicate<String>) null),
                                "Non descriptive, null predicate should be consistently described.");
    }

    // - - - - - - - - - - - - - - - - - - - - - -
    // Object descriptions
    // - - - - - - - - - - - - - - - - - - - - - -

    @Test
    void testDescribeStringReturnsExpectedString() {
        String subject = "hello";
        Assertions.assertEquals("\"" + subject + "\"",
                                ValidityUtils.describe(subject),
                                "Described strings should have quotation marks added around them/");
    }

    @Test
    void testDescribeObjectReturnsExpectedString() {
        Object subject = new Object();
        Assertions.assertEquals(subject.toString(),
                                ValidityUtils.describe(subject),
                                "Described objects should return their toString value.");
    }

    @Test
    void testDescribeObjectArrayReturnsExpectedString() {
        Integer[] arr = {1, 2};
        Assertions.assertEquals("[1, 2]",
                                ValidityUtils.describe(arr),
                                "Described object arrays should use arrays toString value");
    }

    @Test
    void testDescribeMultiDimensionalArrayReturnsExpectedString() {
        Integer[][] arr = {{1, 2}, {3, 4}};
        Assertions.assertEquals("[[1, 2], [3, 4]]",
                                ValidityUtils.describe(arr),
                                "Described multi-dimensional arrays should use arrays deep to string value");
    }
}
