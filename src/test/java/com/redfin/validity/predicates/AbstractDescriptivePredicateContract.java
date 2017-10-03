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

import com.redfin.validity.NotValueTypeContract;
import com.redfin.validity.ValidityUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * A test contract to be applied to sub classes of the {@link AbstractDescriptivePredicate} to requires
 * that they are adhering to the contracts of their super class.
 *
 * @param <T> the type of the sub class being tested.
 */
interface AbstractDescriptivePredicateContract <T extends AbstractDescriptivePredicate>
  extends NotValueTypeContract<T> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test contract requirements
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * @param description the string description to use for creating the abstract descriptive predicate.
     * @return a new {@link AbstractDescriptivePredicate} instance with the given description.
     */
    T getInstance(String description);

    default String toStringHelper(String description) {
        return AbstractDescriptivePredicate.VARIABLE + " -> " + description.replace(AbstractDescriptivePredicate.TOKEN, AbstractDescriptivePredicate.VARIABLE);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    default void testAbstractDescriptivePredicateConstructorThrowsExceptionForNullDescription() {
        NullPointerException exception = Assertions.assertThrows(NullPointerException.class,
                                                                 () -> getInstance(null));
        Assertions.assertEquals(ValidityUtils.nullArgumentMessage("description"),
                                exception.getMessage(),
                                "The AbstractDescriptivePredicate constructor should throw the expected exception for a null argument.");
    }

    @Test
    default void testAbstractDescriptivePredicateConstructorThrowsExceptionForNonTokenDescription() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                                                                     () -> getInstance("hello"));
        Assertions.assertEquals("A descriptive predicate must have the token in the description.",
                                exception.getMessage(),
                                "The AbstractDescriptivePredicate constructor should throw the expected exception for a description without the token.");
    }

    @Test
    default void testAbstractDescriptivePredicateGetNegatedDescriptionReturnsExpectedString() {
        String description = "null != " + AbstractDescriptivePredicate.TOKEN;
        Assertions.assertEquals(String.format("!(%s)", description),
                                getInstance(description).getNegateDescription(),
                                "The AbstractDescriptivePredicate getNegateDescription should return the expected string.");
    }

    @Test
    default void testAbstractDescriptivePredicateGetAndDescriptionReturnsExpectedString() {
        String description = "null != " + AbstractDescriptivePredicate.TOKEN;
        String otherDescription =  AbstractDescriptivePredicate.TOKEN + ".isEmpty()";
        Assertions.assertEquals(String.format("(%s) && (%s)", description, otherDescription),
                                getInstance(description).getAndDescription(otherDescription),
                                "The AbstractDescriptivePredicate getAndDescription should return the expected string.");
    }

    @Test
    default void testAbstractDescriptivePredicateGetOrDescriptionReturnsExpectedString() {
        String description = "null != " + AbstractDescriptivePredicate.TOKEN;
        String otherDescription = AbstractDescriptivePredicate.TOKEN + ".isEmpty()";
        Assertions.assertEquals(String.format("(%s) || (%s)", description, otherDescription),
                                getInstance(description).getOrDescription(otherDescription),
                                "The AbstractDescriptivePredicate getOrDescription should return the expected string.");
    }

    @Test
    default void testAbstractDescriptivePredicateToStringReturnsExpectedToString() {
        String description = "null != " + AbstractDescriptivePredicate.TOKEN;
        Assertions.assertEquals(AbstractDescriptivePredicate.VARIABLE + " -> " + description.replace(AbstractDescriptivePredicate.TOKEN, AbstractDescriptivePredicate.VARIABLE),
                                getInstance(description).toString(),
                                "The AbstractDescriptivePredicate toString should return the expected string.");
    }
}
