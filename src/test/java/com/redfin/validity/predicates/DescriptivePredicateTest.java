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
import java.util.function.Predicate;

final class DescriptivePredicateTest implements AbstractDescriptivePredicateContract<DescriptivePredicate> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test values & contract implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private static final String VALID_DESCRIPTION = "null != " + AbstractDescriptivePredicate.TOKEN;
    private static final Predicate<String> VALID_PREDICATE = t -> null != t;

    @Override
    public DescriptivePredicate<String> getInstance() {
        return new DescriptivePredicate<>(VALID_DESCRIPTION, VALID_PREDICATE);
    }

    @Override
    public DescriptivePredicate<String> getInstance(String description) {
        return new DescriptivePredicate<>(description, VALID_PREDICATE);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    void testCanInstantiateDescriptivePredicateWithValidArguments() {
        Assertions.assertNotNull(getInstance(),
                                 "Should be able to instantiate a descriptive predicate with valid arguments.");
    }

    @Test
    void testDescriptivePredicateConstructorThrowsExceptionForNullPredicate() {
        NullPointerException exception = Assertions.expectThrows(NullPointerException.class,
                                                                 () -> new DescriptivePredicate<>(VALID_DESCRIPTION, null));
        Assertions.assertEquals(ValidityUtils.nullArgumentMessage("predicate"),
                                exception.getMessage(),
                                "DescriptivePredicate should throw expected exception for a null predicate.");
    }

    @Test
    void testDescriptivePredicateHasExpectedPredicate() {
        DescriptivePredicate<String> predicate = getInstance();
        Assertions.assertAll("DescriptivePredicate should wrap the expected predicate.",
                             () -> Assertions.assertTrue(predicate.test("")),
                             () -> Assertions.assertTrue(predicate.test("hello")),
                             () -> Assertions.assertFalse(predicate.test(null)));
    }

    @Test
    void testDescriptivePredicateNegateHasExpectedPredicate() {
        DescriptivePredicate<String> predicate = getInstance().negate();
        Assertions.assertAll("DescriptivePredicate should wrap the expected predicate.",
                             () -> Assertions.assertFalse(predicate.test("")),
                             () -> Assertions.assertFalse(predicate.test("hello")),
                             () -> Assertions.assertTrue(predicate.test(null)));
    }

    @Test
    void testDescriptivePredicateNegateHasExpectedToString() {
        AbstractDescriptivePredicate predicate = new AbstractDescriptivePredicate(VALID_DESCRIPTION) { };
        Assertions.assertEquals(toStringHelper(predicate.getNegateDescription()),
                                getInstance().negate().toString(),
                                "DescriptivePredicate negate should return the expected toString.");
    }

    @Test
    void testDescriptivePredicateAndHasExpectedPredicate() {
        DescriptivePredicate<String> other = new DescriptivePredicate<>("!" + AbstractDescriptivePredicate.TOKEN + ".isEmpty()", t -> !t.isEmpty());
        DescriptivePredicate<String> predicate = getInstance().and(other);
        Assertions.assertAll("DescriptivePredicate should wrap the expected predicate.",
                             () -> Assertions.assertFalse(predicate.test(null)),
                             () -> Assertions.assertFalse(predicate.test("")),
                             () -> Assertions.assertTrue(predicate.test("hello")));
    }

    @Test
    void testDescriptivePredicateAndHasExpectedToString() {
        AbstractDescriptivePredicate predicate = new AbstractDescriptivePredicate(VALID_DESCRIPTION) { };
        String otherDescription = "!" + AbstractDescriptivePredicate.TOKEN + ".isEmpty()";
        Assertions.assertEquals(toStringHelper(predicate.getAndDescription(otherDescription)),
                                getInstance().and(getInstance(otherDescription)).toString(),
                                "DescriptivePredicate and should return the expected toString.");
    }

    @Test
    void testDescriptivePredicateOrHasExpectedPredicate() {
        DescriptivePredicate<String> other = new DescriptivePredicate<>("!" + AbstractDescriptivePredicate.TOKEN + ".isEmpty()", t -> !t.isEmpty());
        DescriptivePredicate<String> predicate = getInstance().or(other);
        Assertions.assertAll("DescriptivePredicate should wrap the expected predicate.",
                             () -> Assertions.assertTrue(predicate.test("")),
                             () -> Assertions.assertTrue(predicate.test("hello")));
        Assertions.assertThrows(NullPointerException.class,
                                () -> predicate.test(null));
    }

    @Test
    void testDescriptivePredicateOrHasExpectedToString() {
        AbstractDescriptivePredicate predicate = new AbstractDescriptivePredicate(VALID_DESCRIPTION) { };
        String otherDescription = "!" + AbstractDescriptivePredicate.TOKEN + ".isEmpty()";
        Assertions.assertEquals(toStringHelper(predicate.getOrDescription(otherDescription)),
                                getInstance().or(getInstance(otherDescription)).toString(),
                                "DescriptivePredicate or should return the expected toString.");
    }
}
