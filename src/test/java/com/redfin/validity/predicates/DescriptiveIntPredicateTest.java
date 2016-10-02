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
import java.util.function.IntPredicate;

final class DescriptiveIntPredicateTest implements AbstractDescriptivePredicateContract<DescriptiveIntPredicate> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test values & contract implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private static final String VALID_DESCRIPTION = "0 < " + AbstractDescriptivePredicate.TOKEN;
    private static final IntPredicate VALID_PREDICATE = t -> 0 < t;

    @Override
    public DescriptiveIntPredicate getInstance() {
        return new DescriptiveIntPredicate(VALID_DESCRIPTION, VALID_PREDICATE);
    }

    @Override
    public DescriptiveIntPredicate getInstance(String description) {
        return new DescriptiveIntPredicate(description, VALID_PREDICATE);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    void testCanInstantiateDescriptiveIntPredicateWithValidArguments() {
        Assertions.assertNotNull(getInstance(),
                                 "Should be able to instantiate a descriptive int predicate with valid arguments.");
    }

    @Test
    void testDescriptiveIntPredicateConstructorThrowsExceptionForNullPredicate() {
        NullPointerException exception = Assertions.expectThrows(NullPointerException.class,
                                                                 () -> new DescriptiveIntPredicate(VALID_DESCRIPTION, null));
        Assertions.assertEquals(ValidityUtils.nullArgumentMessage("predicate"),
                                exception.getMessage(),
                                "DescriptiveIntPredicate should throw expected exception for a null predicate.");
    }

    @Test
    void testDescriptiveIntPredicateHasExpectedPredicate() {
        IntPredicate predicate = getInstance();
        Assertions.assertAll("DescriptiveIntPredicate should wrap the expected predicate.",
                             () -> Assertions.assertTrue(predicate.test(1)),
                             () -> Assertions.assertFalse(predicate.test(0)),
                             () -> Assertions.assertFalse(predicate.test(-1)));
    }

    @Test
    void testDescriptiveIntPredicateNegateHasExpectedPredicate() {
        IntPredicate predicate = getInstance().negate();
        Assertions.assertAll("DescriptiveIntPredicate should wrap the expected predicate.",
                             () -> Assertions.assertFalse(predicate.test(1)),
                             () -> Assertions.assertTrue(predicate.test(0)),
                             () -> Assertions.assertTrue(predicate.test(-1)));
    }

    @Test
    void testDescriptiveIntPredicateNegateHasExpectedToString() {
        AbstractDescriptivePredicate predicate = new AbstractDescriptivePredicate(VALID_DESCRIPTION) { };
        Assertions.assertEquals(toStringHelper(predicate.getNegateDescription()),
                                getInstance().negate().toString(),
                                "DescriptiveIntPredicate negate should return the expected toString.");
    }

    @Test
    void testDescriptiveIntPredicateAndHasExpectedPredicate() {
        IntPredicate other = new DescriptiveIntPredicate("10 > " + AbstractDescriptivePredicate.TOKEN, t -> 10 > t);
        IntPredicate predicate = getInstance().and(other);
        Assertions.assertAll("DescriptiveIntPredicate should wrap the expected predicate.",
                             () -> Assertions.assertFalse(predicate.test(-1)),
                             () -> Assertions.assertFalse(predicate.test(0)),
                             () -> Assertions.assertTrue(predicate.test(1)),
                             () -> Assertions.assertTrue(predicate.test(9)),
                             () -> Assertions.assertFalse(predicate.test(10)));
    }

    @Test
    void testDescriptiveIntPredicateAndHasExpectedToString() {
        AbstractDescriptivePredicate predicate = new AbstractDescriptivePredicate(VALID_DESCRIPTION) { };
        String otherDescription = "10 > " + AbstractDescriptivePredicate.TOKEN;
        Assertions.assertEquals(toStringHelper(predicate.getAndDescription(otherDescription)),
                                getInstance().and(getInstance(otherDescription)).toString(),
                                "DescriptiveIntPredicate and should return the expected toString.");
    }

    @Test
    void testDescriptiveIntPredicateOrHasExpectedPredicate() {
        IntPredicate other = new DescriptiveIntPredicate("10 > " + AbstractDescriptivePredicate.TOKEN, t -> 10 > t);
        IntPredicate predicate = getInstance().or(other);
        Assertions.assertAll("DescriptiveIntPredicate should wrap the expected predicate.",
                             () -> Assertions.assertTrue(predicate.test(-1)),
                             () -> Assertions.assertTrue(predicate.test(0)),
                             () -> Assertions.assertTrue(predicate.test(1)),
                             () -> Assertions.assertTrue(predicate.test(9)),
                             () -> Assertions.assertTrue(predicate.test(10)));
    }

    @Test
    void testDescriptiveIntPredicateOrHasExpectedToString() {
        AbstractDescriptivePredicate predicate = new AbstractDescriptivePredicate(VALID_DESCRIPTION) { };
        String otherDescription = "10 > " + AbstractDescriptivePredicate.TOKEN;
        Assertions.assertEquals(toStringHelper(predicate.getOrDescription(otherDescription)),
                                getInstance().or(getInstance(otherDescription)).toString(),
                                "DescriptiveIntPredicate or should return the expected toString.");
    }
}
