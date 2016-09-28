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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Contract to be implemented by tests for classes inheriting from
 * the {@link AbstractDescriptivePredicate} class.
 *
 * @param <T> the type that is being tested. Must be a subclass of
 *            {@link AbstractDescriptivePredicate}.
 */
interface ContractAbstractDescriptivePredicate<T extends AbstractDescriptivePredicate> extends ContractNotValueType<T> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test Values & Helpers
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    String DESCRIPTION = "null != " + AbstractDescriptivePredicate.TOKEN;
    String OTHER_DESCRIPTION = "!" + AbstractDescriptivePredicate.TOKEN + ".isEmpty()";

    /**
     * Method for implementing class to allow for inheriting tests.
     *
     * @param description the String description for the abstract descriptive predicate.
     * @return the abstract descriptive predicate with the given description.
     */
    T getAbstractDescriptivePredicateInstance(String description);

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test Cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    default void testAbstractDescriptivePredicateConstructorWithNullArgumentThrowsExpectedException() {
        NullPointerException exception = Assertions.expectThrows(NullPointerException.class, () -> getAbstractDescriptivePredicateInstance(null));
        Assertions.assertEquals(exception.getMessage(),
                                Descriptions.nullArgumentMessage("description"),
                                "Exception for a null description should have the expected message.");
    }

    @Test
    default void testAbstractDescriptivePredicateConstructorWithInvalidArgumentThrowsExpectedException() {
        IllegalArgumentException exception = Assertions.expectThrows(IllegalArgumentException.class, () -> getAbstractDescriptivePredicateInstance("hello"));
        Assertions.assertEquals(exception.getMessage(),
                                "Cannot have a description that doesn't contain the token: " + AbstractDescriptivePredicate.TOKEN,
                                "Exception for an invalid description should have the expected message.");
    }

    @Test
    default void testAbstractDescriptivePredicateCanInstantiateWithValidDescription() {
        Assertions.assertNotNull(getAbstractDescriptivePredicateInstance(DESCRIPTION),
                                 "Should be able to instantiate an AbstractDescriptivePredicate with a valid description.");
    }

    @Test
    default void testAbstractDescriptivePredicateReturnsGivenDescription() {
        Assertions.assertEquals(DESCRIPTION,
                                getAbstractDescriptivePredicateInstance(DESCRIPTION).getDescription(),
                                "AbstractDescriptivePredicate should return the given description.");
    }

    @Test
    default void testAbstractDescriptiveToStringReturnsExpectedResult() {
        Assertions.assertEquals("t -> " + DESCRIPTION.replace(AbstractDescriptivePredicate.TOKEN, "t"),
                                getAbstractDescriptivePredicateInstance(DESCRIPTION).toString(),
                                "AbstractDescriptivePredicate should return the expected toString value.");
    }

    @Test
    default void testAbstractDescriptivePredicateReturnsExpectedNegatedString() {
        Assertions.assertEquals(String.format("!(%s)", DESCRIPTION),
                                getAbstractDescriptivePredicateInstance(DESCRIPTION).getDescriptionForNegate(),
                                "AbstractDescriptivePredicate should return the expected negated description.");
    }

    @Test
    default void testAbstractDescriptivePredicateReturnsExpectedAndString() {
        Assertions.assertEquals(String.format("(%s) && (%s)", DESCRIPTION, OTHER_DESCRIPTION),
                                getAbstractDescriptivePredicateInstance(DESCRIPTION).getDescriptionForAnd(OTHER_DESCRIPTION),
                                "AbstractDescriptivePredicate should return the expected and description.");
    }

    @Test
    default void testAbstractDescriptivePredicateReturnsExpectedOrString() {
        Assertions.assertEquals(String.format("(%s) || (%s)", DESCRIPTION, OTHER_DESCRIPTION),
                                getAbstractDescriptivePredicateInstance(DESCRIPTION).getDescriptionForOr(OTHER_DESCRIPTION),
                                "AbstractDescriptivePredicate should return the expected and description.");
    }
}
