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
public interface AbstractVerifiableComparableNumberContract<X extends Throwable, E extends Number & Comparable<E>, T extends AbstractVerifiableComparableNumber<E, X>>
         extends AbstractVerifiableComparableContract<X, E, T> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test contract requirements
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * @return a zero subject.
     */
    E getZeroSubject();

    /**
     * @return a non-zero subject.
     */
    E getNonZeroSubject();

    /**
     * @return a strictly negative subject.
     */
    E getPositiveSubject();

    /**
     * @return a strictly negative subject.
     */
    E getNegativeSubject();

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    default void testIsZeroReturnsSubjectForZeroSubject() throws X {
        E subject = getZeroSubject();
        T verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject.equals(verifiable.isZero()),
                              "Should return the given subject for isZero with a zero subject.");
    }

    @Test
    default void testIsZeroThrowsForNonZeroSubject() {
        E subject = getNonZeroSubject();
        T verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(getThrowableClass(),
                                verifiable::isZero);
    }

    @Test
    default void testIsZeroThrowsForNullSubject() {
        T verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(getThrowableClass(),
                                verifiable::isZero);
    }

    @Test
    default void testIsNotZeroReturnsSubjectForNonZeroSubject() throws X {
        E subject = getNonZeroSubject();
        T verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject.equals(verifiable.isNotZero()),
                              "Should return the given subject for isNotZero with a non-zero subject.");
    }

    @Test
    default void testIsNotZeroThrowsForZeroSubject() {
        E subject = getZeroSubject();
        T verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(getThrowableClass(),
                                verifiable::isNotZero);
    }

    @Test
    default void testIsNotZeroThrowsForNullSubject() {
        T verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(getThrowableClass(),
                                verifiable::isNotZero);
    }

    @Test
    default void testIsStrictlyPositiveReturnsSubjectForPositiveSubject() throws X {
        E subject = getPositiveSubject();
        T verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject.equals(verifiable.isStrictlyPositive()),
                              "Should return the given subject for isStrictlyPositive with a positive subject.");
    }

    @Test
    default void testIsStrictlyPositiveThrowsForZeroSubject() {
        E subject = getZeroSubject();
        T verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(getThrowableClass(),
                                verifiable::isStrictlyPositive);
    }

    @Test
    default void testIsStrictlyPositiveThrowsForNegativeSubject() {
        E subject = getNegativeSubject();
        T verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(getThrowableClass(),
                                verifiable::isStrictlyPositive);
    }

    @Test
    default void testIsStrictlyPositiveThrowsForNullSubject() {
        T verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(getThrowableClass(),
                                verifiable::isStrictlyPositive);
    }

    @Test
    default void testIsStrictlyNegativeReturnsSubjectForNegativeSubject() throws X {
        E subject = getNegativeSubject();
        T verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject.equals(verifiable.isStrictlyNegative()),
                              "Should return the given subject for isStrictlyNegative with a negative subject.");
    }

    @Test
    default void testIsStrictlyNegativeThrowsForZeroSubject() {
        E subject = getZeroSubject();
        T verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(getThrowableClass(),
                                verifiable::isStrictlyNegative);
    }

    @Test
    default void testIsStrictlyNegativeThrowsForPositiveSubject() {
        E subject = getPositiveSubject();
        T verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(getThrowableClass(),
                                verifiable::isStrictlyNegative);
    }

    @Test
    default void testIsStrictlyNegativeThrowsForNullSubject() {
        T verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(getThrowableClass(),
                                verifiable::isStrictlyNegative);
    }
}
