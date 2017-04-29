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

package com.redfin.validity.verifiers;

import com.redfin.validity.NotValueTypeContract;
import com.redfin.validity.ValidityUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * A test contract that validates that the class under test is
 * maintaining the contract of its {@link AbstractVerifiableComparable} super class.
 *
 * @param <X> the throwable type of the class under test.
 * @param <E> the generic type of the class under test.
 * @param <T> the type of the class under test.
 */
public interface AbstractVerifiableComparableContract<X extends Throwable, E extends Comparable<E>, T extends AbstractVerifiableComparable<E, X>> extends NotValueTypeContract<T>, AbstractVerifiableObjectContract<X, E, T> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test contract requirements
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * @return an instance that is comparable to {@link #getSubject()}.
     */
    E getComparableSubject();

    /**
     * @return an instance that is not comparable to {@link #getSubject()}.
     */
    E getNonComparableSubject();

    /**
     * @return an instance that is less than {@link #getSubject()}.
     */
    E getLessThanSubject();

    /**
     * @return an instance that is greater than {@link #getSubject()}.
     */
    E getGreaterThanSubject();

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    default void testIsComparableToReturnsSubjectForComparable() throws X {
        E subject = getSubject();
        T verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.isComparableTo(getComparableSubject()),
                              "Should return the given subject for isComparableTo with comparable other.");
    }

    @Test
    default void testIsComparableToThrowsForNonComparable() {
        E subject = getSubject();
        T verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(getThrowableClass(),
                                () -> verifiable.isComparableTo(getNonComparableSubject()));
    }

    @Test
    default void testIsComparableToThrowsForNullSubject() {
        T verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(getThrowableClass(),
                                () -> verifiable.isComparableTo(getNonComparableSubject()));
    }

    @Test
    default void testIsComparableToThrowsForNullOther() {
        E subject = getSubject();
        T verifiable = getVerifiableInstance(subject);
        NullPointerException exception = Assertions.assertThrows(NullPointerException.class,
                                                                 () -> verifiable.isComparableTo(null));
        Assertions.assertEquals(ValidityUtils.nullArgumentMessage("other"),
                                exception.getMessage(),
                                "Should have the expected exception.");
    }

    @Test
    default void testIsNotComparableToReturnsSubjectForNonComparable() throws X {
        E subject = getSubject();
        T verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.isNotComparableTo(getNonComparableSubject()),
                              "Should return the given subject for isNotComparableTo with non-comparable other.");
    }

    @Test
    default void testIsNotComparableToThrowsForComparable() {
        E subject = getSubject();
        T verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(getThrowableClass(),
                                () -> verifiable.isNotComparableTo(getComparableSubject()));
    }

    @Test
    default void testIsNotComparableToThrowsForNullSubject() {
        T verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(getThrowableClass(),
                                () -> verifiable.isNotComparableTo(getNonComparableSubject()));
    }

    @Test
    default void testIsNotComparableToThrowsForNullOther() {
        E subject = getSubject();
        T verifiable = getVerifiableInstance(subject);
        NullPointerException exception = Assertions.assertThrows(NullPointerException.class,
                                                                 () -> verifiable.isNotComparableTo(null));
        Assertions.assertEquals(ValidityUtils.nullArgumentMessage("other"),
                                exception.getMessage(),
                                "Should have the expected exception.");
    }

    @Test
    default void testIsGreaterThanReturnsSubjectForLessThanOther() throws X {
        E subject = getSubject();
        T verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.isGreaterThan(getLessThanSubject()),
                              "Should return the given subject for isGreaterThan with less than other.");
    }

    @Test
    default void testIsGreaterThanThrowsForEqualOther() {
        E subject = getSubject();
        T verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(getThrowableClass(),
                                () -> verifiable.isGreaterThan(getEqualSubject()));
    }

    @Test
    default void testIsGreaterThanThrowsForGreaterThanOther() {
        E subject = getSubject();
        T verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(getThrowableClass(),
                                () -> verifiable.isGreaterThan(getGreaterThanSubject()));
    }

    @Test
    default void testIsGreaterThanThrowsForNullSubject() {
        T verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(getThrowableClass(),
                                () -> verifiable.isGreaterThan(getEqualSubject()));
    }

    @Test
    default void testIsGreaterThanThrowsForNullOther() {
        E subject = getSubject();
        T verifiable = getVerifiableInstance(subject);
        NullPointerException exception = Assertions.assertThrows(NullPointerException.class,
                                                                 () -> verifiable.isGreaterThan(null));
        Assertions.assertEquals(ValidityUtils.nullArgumentMessage("other"),
                                exception.getMessage(),
                                "Should have the expected exception.");
    }

    @Test
    default void testIsGreaterThanOrEqualToReturnsSubjectForLessThanOther() throws X {
        E subject = getSubject();
        T verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.isGreaterThanOrEqualTo(getLessThanSubject()),
                              "Should return the given subject for isGreaterThanOrEqualTo with less than other.");
    }

    @Test
    default void testIsGreaterThanOrEqualToReturnsSubjectForEqualOther() throws X {
        E subject = getSubject();
        T verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.isGreaterThanOrEqualTo(getEqualSubject()),
                              "Should return the given subject for isGreaterThanOrEqualTo with equal other.");
    }

    @Test
    default void testIsGreaterThanOrEqualToThrowsForGreaterThanOther() {
        E subject = getSubject();
        T verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(getThrowableClass(),
                                () -> verifiable.isGreaterThanOrEqualTo(getGreaterThanSubject()));
    }

    @Test
    default void testIsGreaterThanOrEqualToThrowsForNullSubject() {
        T verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(getThrowableClass(),
                                () -> verifiable.isGreaterThanOrEqualTo(getLessThanSubject()));
    }

    @Test
    default void testIsGreaterThanOrEqualToThrowsForNullOther() {
        E subject = getSubject();
        T verifiable = getVerifiableInstance(subject);
        NullPointerException exception = Assertions.assertThrows(NullPointerException.class,
                                                                 () -> verifiable.isGreaterThanOrEqualTo(null));
        Assertions.assertEquals(ValidityUtils.nullArgumentMessage("other"),
                                exception.getMessage(),
                                "Should have the expected exception.");
    }

    @Test
    default void testIsAtLeastReturnsSubjectForLessThanOther() throws X {
        E subject = getSubject();
        T verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.isAtLeast(getLessThanSubject()),
                              "Should return the given subject for isGreaterThanOrEqualTo with less than other.");
    }

    @Test
    default void testIsAtLeastReturnsSubjectForEqualOther() throws X {
        E subject = getSubject();
        T verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.isAtLeast(getEqualSubject()),
                              "Should return the given subject for isGreaterThanOrEqualTo with equal other.");
    }

    @Test
    default void testIsAtLeastThrowsForGreaterThanOther() {
        E subject = getSubject();
        T verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(getThrowableClass(),
                                () -> verifiable.isAtLeast(getGreaterThanSubject()));
    }

    @Test
    default void testIsAtLeastThrowsForNullSubject() {
        T verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(getThrowableClass(),
                                () -> verifiable.isAtLeast(getLessThanSubject()));
    }

    @Test
    default void testIsAtLeastThrowsForNullOther() {
        E subject = getSubject();
        T verifiable = getVerifiableInstance(subject);
        NullPointerException exception = Assertions.assertThrows(NullPointerException.class,
                                                                 () -> verifiable.isAtLeast(null));
        Assertions.assertEquals(ValidityUtils.nullArgumentMessage("other"),
                                exception.getMessage(),
                                "Should have the expected exception.");
    }

    @Test
    default void testIsLessThanReturnsSubjectForGreaterThanOther() throws X {
        E subject = getSubject();
        T verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.isLessThan(getGreaterThanSubject()),
                              "Should return the given subject for isLessThan with less than other.");
    }

    @Test
    default void testIsLessThanThrowsForEqualOther() {
        E subject = getSubject();
        T verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(getThrowableClass(),
                                () -> verifiable.isLessThan(getEqualSubject()));
    }

    @Test
    default void testIsLessThanThrowsForLessThanOther() {
        E subject = getSubject();
        T verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(getThrowableClass(),
                                () -> verifiable.isLessThan(getLessThanSubject()));
    }

    @Test
    default void testIsLessThanThrowsForNullSubject() {
        T verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(getThrowableClass(),
                                () -> verifiable.isLessThan(getEqualSubject()));
    }

    @Test
    default void testIsLessThanThrowsForNullOther() {
        E subject = getSubject();
        T verifiable = getVerifiableInstance(subject);
        NullPointerException exception = Assertions.assertThrows(NullPointerException.class,
                                                                 () -> verifiable.isLessThan(null));
        Assertions.assertEquals(ValidityUtils.nullArgumentMessage("other"),
                                exception.getMessage(),
                                "Should have the expected exception.");
    }

    @Test
    default void testIsLessThanOrEqualToReturnsSubjectForGreaterThanOther() throws X {
        E subject = getSubject();
        T verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.isLessThanOrEqualTo(getGreaterThanSubject()),
                              "Should return the given subject for isLessThanOrEqualTo with less than other.");
    }

    @Test
    default void testIsLessThanOrEqualToReturnsSubjectForEqualOther() throws X {
        E subject = getSubject();
        T verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.isLessThanOrEqualTo(getEqualSubject()),
                              "Should return the given subject for isLessThanOrEqualTo with equal other.");
    }

    @Test
    default void testIsLessThanOrEqualToThrowsForLessThanOther() {
        E subject = getSubject();
        T verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(getThrowableClass(),
                                () -> verifiable.isLessThanOrEqualTo(getLessThanSubject()));
    }

    @Test
    default void testIsLessThanOrEqualToThrowsForNullSubject() {
        T verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(getThrowableClass(),
                                () -> verifiable.isLessThanOrEqualTo(getGreaterThanSubject()));
    }

    @Test
    default void testIsLessThanOrEqualToThrowsForNullOther() {
        E subject = getSubject();
        T verifiable = getVerifiableInstance(subject);
        NullPointerException exception = Assertions.assertThrows(NullPointerException.class,
                                                                 () -> verifiable.isLessThanOrEqualTo(null));
        Assertions.assertEquals(ValidityUtils.nullArgumentMessage("other"),
                                exception.getMessage(),
                                "Should have the expected exception.");
    }

    @Test
    default void testIsAtMostReturnsSubjectForGreaterThanOther() throws X {
        E subject = getSubject();
        T verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.isAtMost(getGreaterThanSubject()),
                              "Should return the given subject for isAtMost with greater than other.");
    }

    @Test
    default void testIsAtMostReturnsSubjectForEqualOther() throws X {
        E subject = getSubject();
        T verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.isAtMost(getEqualSubject()),
                              "Should return the given subject for isAtMost with equal other.");
    }

    @Test
    default void testIsAtMostThrowsForLessThanOther() {
        E subject = getSubject();
        T verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(getThrowableClass(),
                                () -> verifiable.isAtMost(getLessThanSubject()));
    }

    @Test
    default void testIsAtMostThrowsForNullSubject() {
        T verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(getThrowableClass(),
                                () -> verifiable.isAtMost(getGreaterThanSubject()));
    }

    @Test
    default void testIsAtMostThrowsForNullOther() {
        E subject = getSubject();
        T verifiable = getVerifiableInstance(subject);
        NullPointerException exception = Assertions.assertThrows(NullPointerException.class,
                                                                 () -> verifiable.isAtMost(null));
        Assertions.assertEquals(ValidityUtils.nullArgumentMessage("other"),
                                exception.getMessage(),
                                "Should have the expected exception.");
    }
}
