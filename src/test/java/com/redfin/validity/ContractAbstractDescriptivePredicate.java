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
import java.util.function.Predicate;

/**
 * Contract to be implemented by tests for classes inheriting from
 * the {@link AbstractDescriptivePredicate} class.
 *
 * @param <T> the instance type that is being tested.
 */
interface ContractAbstractDescriptivePredicate<T extends AbstractDescriptivePredicate> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test Values & Helpers
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    String DESCRIPTION = "null != " + AbstractDescriptivePredicate.TOKEN;
    String OTHER_DESCRIPTION = "!" + AbstractDescriptivePredicate.TOKEN + ".isEmpty()";

    T getInstance(String description);

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test Cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    default void testAbstractDescriptivePredicateConstructorWithNullArgumentThrowsExpectedException() {
        NullPointerException exception = Assertions.expectThrows(NullPointerException.class, () -> getInstance(null));
        Assertions.assertEquals(exception.getMessage(),
                                Messages.nullArgumentMessage("description"),
                                "Exception for a null description should have the expected message.");
    }

    @Test
    default void testAbstractDescriptivePredicateConstructorWithInvalidArgumentThrowsExpectedException() {
        IllegalArgumentException exception = Assertions.expectThrows(IllegalArgumentException.class, () -> getInstance("hello"));
        Assertions.assertEquals(exception.getMessage(),
                                "Cannot have a description that doesn't contain the token: " + AbstractDescriptivePredicate.TOKEN,
                                "Exception for an invalid description should have the expected message.");
    }

    @Test
    default void testAbstractDescriptivePredicateCanInstantiateWithValidDescription() {
        Assertions.assertNotNull(getInstance(DESCRIPTION),
                                 "Should be able to instantiate an AbstractDescriptivePredicate with a valid description.");
    }

    @Test
    default void testAbstractDescriptiveToStringReturnsExpectedResult() {
        Assertions.assertEquals("t -> " + DESCRIPTION.replace(AbstractDescriptivePredicate.TOKEN, "t"),
                                getInstance(DESCRIPTION).toString(),
                                "AbstractDescriptivePredicate should return the expected toString value.");
    }

    @Test
    default void testAbstractDescriptivePredicateReturnsExpectedNegatedString() {
        Assertions.assertEquals(String.format("!(%s)", DESCRIPTION),
                                getInstance(DESCRIPTION).getDescriptionForNegate(),
                                "AbstractDescriptivePredicate should return the expected negated description.");
    }

    @Test
    default void testAbstractDescriptivePredicateReturnsExpectedAndString() {
        Assertions.assertEquals(String.format("(%s) && (%s)", DESCRIPTION, OTHER_DESCRIPTION),
                                getInstance(DESCRIPTION).getDescriptionForAnd(getInstance(OTHER_DESCRIPTION)),
                                "AbstractDescriptivePredicate should return the expected and description.");
    }

    @Test
    default void testAbstractDescriptivePredicateReturnsExpectedOrString() {
        Assertions.assertEquals(String.format("(%s) || (%s)", DESCRIPTION, OTHER_DESCRIPTION),
                                getInstance(DESCRIPTION).getDescriptionForOr(getInstance(OTHER_DESCRIPTION)),
                                "AbstractDescriptivePredicate should return the expected or description.");
    }

    @Test
    default void testAbstractDescriptivePredicateGetOtherDescriptionThrowsExpectedExceptionForNull() {
        NullPointerException exception = Assertions.expectThrows(NullPointerException.class, () -> AbstractDescriptivePredicate.describePredicate(null));
        Assertions.assertEquals(Messages.nullArgumentMessage("other"),
                                exception.getMessage(),
                                "AbstractDescriptivePredicate should throw an exception for a null describePredicate argument.");
    }

    @Test
    default void testAbstractDescriptivePredicateGetOtherDescriptionReturnsExpectedValueForDescriptive() {
        Assertions.assertEquals(DESCRIPTION,
                                AbstractDescriptivePredicate.describePredicate(getInstance(DESCRIPTION)),
                                "AbstractDescriptivePredicate should return the expected description for descriptive predicate.");
    }

    @Test
    default void testAbstractDescriptivePredicateGetOtherDescriptionReturnsExpectedValueForJavaPredicate() {
        Predicate<String> predicate = t -> null != t;
        Assertions.assertEquals("unknown predicate: " + predicate,
                                AbstractDescriptivePredicate.describePredicate(predicate),
                                "AbstractDescriptivePredicate should return the expected description for plain Java predicate.");
    }
}
