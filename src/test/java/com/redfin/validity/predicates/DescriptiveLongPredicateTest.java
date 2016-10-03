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

package com.redfin.validity.predicates;

import com.redfin.validity.ValidityUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.function.LongPredicate;

final class DescriptiveLongPredicateTest implements AbstractDescriptivePredicateContract<DescriptiveLongPredicate> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test values & contract implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private static final String VALID_DESCRIPTION = "0 < " + AbstractDescriptivePredicate.TOKEN;
    private static final LongPredicate VALID_PREDICATE = t -> 0 < t;

    @Override
    public DescriptiveLongPredicate getNotValueTypeInstance() {
        return new DescriptiveLongPredicate(VALID_DESCRIPTION, VALID_PREDICATE);
    }

    @Override
    public DescriptiveLongPredicate getInstance(String description) {
        return new DescriptiveLongPredicate(description, VALID_PREDICATE);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    void testCanInstantiateDescriptiveLongPredicateWithValidArguments() {
        Assertions.assertNotNull(getNotValueTypeInstance(),
                                 "Should be able to instantiate a descriptive long predicate with valid arguments.");
    }

    @Test
    void testDescriptiveLongPredicateConstructorThrowsExceptionForNullPredicate() {
        NullPointerException exception = Assertions.expectThrows(NullPointerException.class,
                                                                 () -> new DescriptiveLongPredicate(VALID_DESCRIPTION, null));
        Assertions.assertEquals(ValidityUtils.nullArgumentMessage("predicate"),
                                exception.getMessage(),
                                "DescriptiveLongPredicate should throw expected exception for a null predicate.");
    }

    @Test
    void testDescriptiveLongPredicateHasExpectedPredicate() {
        LongPredicate predicate = getNotValueTypeInstance();
        Assertions.assertAll("DescriptiveLongPredicate should wrap the expected predicate.",
                             () -> Assertions.assertTrue(predicate.test(1)),
                             () -> Assertions.assertFalse(predicate.test(0)),
                             () -> Assertions.assertFalse(predicate.test(-1)));
    }

    @Test
    void testDescriptiveLongPredicateNegateHasExpectedPredicate() {
        LongPredicate predicate = getNotValueTypeInstance().negate();
        Assertions.assertAll("DescriptiveLongPredicate should wrap the expected predicate.",
                             () -> Assertions.assertFalse(predicate.test(1)),
                             () -> Assertions.assertTrue(predicate.test(0)),
                             () -> Assertions.assertTrue(predicate.test(-1)));
    }

    @Test
    void testDescriptiveLongPredicateNegateHasExpectedToString() {
        AbstractDescriptivePredicate predicate = new AbstractDescriptivePredicate(VALID_DESCRIPTION) { };
        Assertions.assertEquals(toStringHelper(predicate.getNegateDescription()),
                                getNotValueTypeInstance().negate().toString(),
                                "DescriptiveLongPredicate negate should return the expected toString.");
    }

    @Test
    void testDescriptiveLongPredicateAndHasExpectedPredicate() {
        LongPredicate other = new DescriptiveLongPredicate("10 > " + AbstractDescriptivePredicate.TOKEN, t -> 10 > t);
        LongPredicate predicate = getNotValueTypeInstance().and(other);
        Assertions.assertAll("DescriptiveLongPredicate should wrap the expected predicate.",
                             () -> Assertions.assertFalse(predicate.test(-1)),
                             () -> Assertions.assertFalse(predicate.test(0)),
                             () -> Assertions.assertTrue(predicate.test(1)),
                             () -> Assertions.assertTrue(predicate.test(9)),
                             () -> Assertions.assertFalse(predicate.test(10)));
    }

    @Test
    void testDescriptiveLongPredicateAndHasExpectedToString() {
        AbstractDescriptivePredicate predicate = new AbstractDescriptivePredicate(VALID_DESCRIPTION) { };
        String otherDescription = "10 > " + AbstractDescriptivePredicate.TOKEN;
        Assertions.assertEquals(toStringHelper(predicate.getAndDescription(otherDescription)),
                                getNotValueTypeInstance().and(getInstance(otherDescription)).toString(),
                                "DescriptiveLongPredicate and should return the expected toString.");
    }

    @Test
    void testDescriptiveLongPredicateOrHasExpectedPredicate() {
        LongPredicate other = new DescriptiveLongPredicate("10 > " + AbstractDescriptivePredicate.TOKEN, t -> 10 > t);
        LongPredicate predicate = getNotValueTypeInstance().or(other);
        Assertions.assertAll("DescriptiveLongPredicate should wrap the expected predicate.",
                             () -> Assertions.assertTrue(predicate.test(-1)),
                             () -> Assertions.assertTrue(predicate.test(0)),
                             () -> Assertions.assertTrue(predicate.test(1)),
                             () -> Assertions.assertTrue(predicate.test(9)),
                             () -> Assertions.assertTrue(predicate.test(10)));
    }

    @Test
    void testDescriptiveLongPredicateOrHasExpectedToString() {
        AbstractDescriptivePredicate predicate = new AbstractDescriptivePredicate(VALID_DESCRIPTION) { };
        String otherDescription = "10 > " + AbstractDescriptivePredicate.TOKEN;
        Assertions.assertEquals(toStringHelper(predicate.getOrDescription(otherDescription)),
                                getNotValueTypeInstance().or(getInstance(otherDescription)).toString(),
                                "DescriptiveLongPredicate or should return the expected toString.");
    }
}
