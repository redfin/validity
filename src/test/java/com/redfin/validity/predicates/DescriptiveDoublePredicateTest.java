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
import java.util.function.DoublePredicate;

final class DescriptiveDoublePredicateTest implements AbstractDescriptivePredicateContract<DescriptiveDoublePredicate> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test values & contract implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private static final String VALID_DESCRIPTION = "0 < " + AbstractDescriptivePredicate.TOKEN;
    private static final DoublePredicate VALID_PREDICATE = t -> 0 < t;

    @Override
    public DescriptiveDoublePredicate getNotValueTypeInstance() {
        return new DescriptiveDoublePredicate(VALID_DESCRIPTION, VALID_PREDICATE);
    }

    @Override
    public DescriptiveDoublePredicate getInstance(String description) {
        return new DescriptiveDoublePredicate(description, VALID_PREDICATE);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    void testCanInstantiateDescriptiveDoublePredicateWithValidArguments() {
        Assertions.assertNotNull(getNotValueTypeInstance(),
                                 "Should be able to instantiate a descriptive double predicate with valid arguments.");
    }

    @Test
    void testDescriptiveDoublePredicateConstructorThrowsExceptionForNullPredicate() {
        NullPointerException exception = Assertions.assertThrows(NullPointerException.class,
                                                                 () -> new DescriptiveDoublePredicate(VALID_DESCRIPTION, null));
        Assertions.assertEquals(ValidityUtils.nullArgumentMessage("predicate"),
                                exception.getMessage(),
                                "DescriptiveDoublePredicate should throw expected exception for a null predicate.");
    }

    @Test
    void testDescriptiveDoublePredicateHasExpectedPredicate() {
        DoublePredicate predicate = getNotValueTypeInstance();
        Assertions.assertAll("DescriptiveDoublePredicate should wrap the expected predicate.",
                             () -> Assertions.assertTrue(predicate.test(1.0)),
                             () -> Assertions.assertFalse(predicate.test(0.0)),
                             () -> Assertions.assertFalse(predicate.test(-1.0)));
    }

    @Test
    void testDescriptiveDoublePredicateNegateHasExpectedPredicate() {
        DoublePredicate predicate = getNotValueTypeInstance().negate();
        Assertions.assertAll("DescriptiveDoublePredicate should wrap the expected predicate.",
                             () -> Assertions.assertFalse(predicate.test(1.0)),
                             () -> Assertions.assertTrue(predicate.test(0.0)),
                             () -> Assertions.assertTrue(predicate.test(-1.0)));
    }

    @Test
    void testDescriptiveDoublePredicateNegateHasExpectedToString() {
        AbstractDescriptivePredicate predicate = new AbstractDescriptivePredicate(VALID_DESCRIPTION) { };
        Assertions.assertEquals(toStringHelper(predicate.getNegateDescription()),
                                getNotValueTypeInstance().negate().toString(),
                                "DescriptiveDoublePredicate negate should return the expected toString.");
    }

    @Test
    void testDescriptiveDoublePredicateAndHasExpectedPredicate() {
        DoublePredicate other = new DescriptiveDoublePredicate("10 > " + AbstractDescriptivePredicate.TOKEN, t -> 10 > t);
        DoublePredicate predicate = getNotValueTypeInstance().and(other);
        Assertions.assertAll("DescriptiveDoublePredicate should wrap the expected predicate.",
                             () -> Assertions.assertFalse(predicate.test(-1.0)),
                             () -> Assertions.assertFalse(predicate.test(0.0)),
                             () -> Assertions.assertTrue(predicate.test(1.0)),
                             () -> Assertions.assertTrue(predicate.test(9.0)),
                             () -> Assertions.assertFalse(predicate.test(10.0)));
    }

    @Test
    void testDescriptiveDoublePredicateAndHasExpectedToString() {
        AbstractDescriptivePredicate predicate = new AbstractDescriptivePredicate(VALID_DESCRIPTION) { };
        String otherDescription = "10 > " + AbstractDescriptivePredicate.TOKEN;
        Assertions.assertEquals(toStringHelper(predicate.getAndDescription(otherDescription)),
                                getNotValueTypeInstance().and(getInstance(otherDescription)).toString(),
                                "DescriptiveDoublePredicate and should return the expected toString.");
    }

    @Test
    void testDescriptiveDoublePredicateOrHasExpectedPredicate() {
        DoublePredicate other = new DescriptiveDoublePredicate("10 > " + AbstractDescriptivePredicate.TOKEN, t -> 10 > t);
        DoublePredicate predicate = getNotValueTypeInstance().or(other);
        Assertions.assertAll("DescriptiveDoublePredicate should wrap the expected predicate.",
                             () -> Assertions.assertTrue(predicate.test(-1.0)),
                             () -> Assertions.assertTrue(predicate.test(0.0)),
                             () -> Assertions.assertTrue(predicate.test(1.0)),
                             () -> Assertions.assertTrue(predicate.test(9.0)),
                             () -> Assertions.assertTrue(predicate.test(10.0)));
    }

    @Test
    void testDescriptiveDoublePredicateOrHasExpectedToString() {
        AbstractDescriptivePredicate predicate = new AbstractDescriptivePredicate(VALID_DESCRIPTION) { };
        String otherDescription = "10 > " + AbstractDescriptivePredicate.TOKEN;
        Assertions.assertEquals(toStringHelper(predicate.getOrDescription(otherDescription)),
                                getNotValueTypeInstance().or(getInstance(otherDescription)).toString(),
                                "DescriptiveDoublePredicate or should return the expected toString.");
    }
}
