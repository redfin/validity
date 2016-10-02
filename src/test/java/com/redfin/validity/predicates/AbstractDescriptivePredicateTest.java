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

final class AbstractDescriptivePredicateTest {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    void testDescribeOtherThrowsExceptionForNull() {
        String message = ValidityUtils.nullArgumentMessage("other");
        NullPointerException exception = Assertions.expectThrows(NullPointerException.class,
                                                                 () -> AbstractDescriptivePredicate.describeOther(null));
        Assertions.assertEquals(message,
                                exception.getMessage(),
                                "Passing null to describe other should throw the expected exception.");
    }

    @Test
    void testDescribeOtherReturnsExpectedDescriptionForAbstractDescriptiveObject() {
        String description = "hello, {}";
        AbstractDescriptivePredicate predicate = new AbstractDescriptivePredicate(description) { };
        Assertions.assertEquals(description,
                                AbstractDescriptivePredicate.describeOther(predicate),
                                "AbstractDescriptivePredicate describe other should return the description from a descriptive predicate.");
    }

    @Test
    void testDescribeOtherReturnsExpectedDescriptionForOtherObject() {
        String other = "hello";
        Assertions.assertEquals(ValidityUtils.unknownPredicatePrefix() + other,
                                AbstractDescriptivePredicate.describeOther(other),
                                "AbstractDescriptivePredicate describe other should return the expected value for a non-descriptive predicate.");
    }
}
