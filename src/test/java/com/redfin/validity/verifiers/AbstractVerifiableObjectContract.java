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

import com.redfin.validity.FailedValidationExecutor;
import com.redfin.validity.NotValueTypeContract;
import com.redfin.validity.ValidityUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * A test contract that validates that the class under test is
 * maintaining the contract of its {@link AbstractVerifiableObject} super class.
 *
 * @param <X> the throwable type of the class under test.
 * @param <E> the generic type of the class under test.
 * @param <T> the type of the class under test.
 */
public interface AbstractVerifiableObjectContract<X extends Throwable, E, T extends AbstractVerifiableObject<E, X>> extends NotValueTypeContract<T> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test contract requirements
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * @return a test subject for the verifiable object under test.
     */
    E getSubject();

    /**
     * @return a new subject that is equal to {@link #getSubject()}.
     */
    E getEqualSubject();

    /**
     * @return a new subject that is not equal to that returned by {@link #getSubject()}.
     */
    E getNonEqualSubject();

    @Override
    default T getNotValueTypeInstance() {
        return getVerifiableInstance(getSubject());
    }

    /**
     * @param subject the subject for the verifiable instance.
     * @return the verifiable instance being tested.
     */
    default T getVerifiableInstance(E subject) {
        return getVerifiableInstance(getFailedValidationExecutor(), subject, "message");
    }

    /**
     * @param failedValidationExecutor the failed validation executor.
     * @param subject                  the subject of the verifiable object.
     * @param message                  the String message of the verifiable object.
     * @return an instance of {@link AbstractVerifiableObject}.
     */
    T getVerifiableInstance(FailedValidationExecutor<X> failedValidationExecutor, E subject, String message);

    /**
     * @return the class object of the throwable type for the validation executor.
     */
    Class<X> getThrowableClass();

    /**
     * @return the {@link FailedValidationExecutor} for the tests.
     */
    FailedValidationExecutor<X> getFailedValidationExecutor();

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    // --------------------------------------------------------------
    // Constructor tests
    // --------------------------------------------------------------

    @Test
    default void testCanInstantiateWithValidArguments() {
        Assertions.assertNotNull(getVerifiableInstance(getFailedValidationExecutor(), getSubject(), "message"),
                                 "Should be able to instantiate an AbstractVerifiableObject with valid arguments.");
    }

    @Test
    default void testCanInstantiateWithNullSubject() {
        Assertions.assertNotNull(getVerifiableInstance(getFailedValidationExecutor(), getSubject(), null),
                                 "Should be able to instantiate an AbstractVerifiableObject with a null subject.");
    }

    @Test
    default void testCanInstantiateWithNullMessage() {
        Assertions.assertNotNull(getVerifiableInstance(getFailedValidationExecutor(), null, "message"),
                                 "Should be able to instantiate an AbstractVerifiableObject with a null message.");
    }

    @Test
    default void testAbstractVerifiableObjectConstructorThrowsExceptionForNullValidationExecutor() {
        NullPointerException exception = Assertions.assertThrows(NullPointerException.class,
                                                                 () -> getVerifiableInstance(null, getSubject(), "message"));
        Assertions.assertEquals(ValidityUtils.nullArgumentMessage("failedValidationExecutor"),
                                exception.getMessage(),
                                "Should throw an exception for a null validation executor.");
    }

    // --------------------------------------------------------------
    // Getter tests
    // --------------------------------------------------------------

    @Test
    default void testGetFailedValidationExecutorReturnsGivenInstance() {
        FailedValidationExecutor<X> failedValidationExecutor = getFailedValidationExecutor();
        Assertions.assertTrue(failedValidationExecutor == getVerifiableInstance(failedValidationExecutor, getSubject(), "message").getFailedValidationExecutor(),
                              "An AbstractVerifiableObject should return the given validation executor instance.");
    }

    @Test
    default void testGetFailedValidationExecutorReturnsGivenSubject() {
        E subject = getSubject();
        Assertions.assertTrue(subject == getVerifiableInstance(getFailedValidationExecutor(), subject, "message").getSubject(),
                              "An AbstractVerifiableObject should return the given subject.");
    }

    @Test
    default void testGetFailedValidationExecutorReturnsGivenMessage() {
        String message = "message";
        Assertions.assertTrue(message == getVerifiableInstance(getFailedValidationExecutor(), getSubject(), message).getMessage(),
                              "An AbstractVerifiableObject should return the given subject.");
    }

    // --------------------------------------------------------------
    // Verification method tests
    // --------------------------------------------------------------

    @Test
    default void testIsNullReturnsSubjectForNullSubject() throws X {
        AbstractVerifiableObject<E, X> verifiable = getVerifiableInstance(getFailedValidationExecutor(), null, "message");
        Assertions.assertTrue(null == verifiable.isNull(),
                              "An AbstractVerifiableObject should return null for isNull with null subject.");
    }

    @Test
    default void testIsNullThrowsForNonNullSubject() throws X {
        E subject = getSubject();
        AbstractVerifiableObject<E, X> verifiable = getVerifiableInstance(getFailedValidationExecutor(), subject, "message");
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isNull);
    }

    @Test
    default void testIsNotNullReturnsSubjectForNonNullSubject() throws X {
        E subject = getSubject();
        AbstractVerifiableObject<E, X> verifiable = getVerifiableInstance(getFailedValidationExecutor(), subject, "message");
        Assertions.assertTrue(subject == verifiable.isNotNull(),
                              "An AbstractVerifiableObject should return subject for isNotNull with non-null subject.");

    }

    @Test
    default void testIsNotNullThrowsForNullSubject() throws X {
        AbstractVerifiableObject<E, X> verifiable = getVerifiableInstance(getFailedValidationExecutor(), null, "message");
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isNotNull);
    }

    @Test
    default void testIsEqualReturnsSubjectForSameInstance() throws X {
        E subject = getSubject();
        AbstractVerifiableObject<E, X> verifiable = getVerifiableInstance(getFailedValidationExecutor(), subject, "message");
        Assertions.assertTrue(subject == verifiable.isEqualTo(subject),
                              "An AbstractVerifiableObject should return the given subject for isEqual with same instance.");
    }

    @Test
    default void testIsEqualReturnsSubjectForEqual() throws X {
        E subject = getSubject();
        AbstractVerifiableObject<E, X> verifiable = getVerifiableInstance(getFailedValidationExecutor(), subject, "message");
        Assertions.assertTrue(subject == verifiable.isEqualTo(getEqualSubject()),
                              "An AbstractVerifiableObject should return the given subject for isEqual with equal object.");
    }

    @Test
    default void testIsEqualThrowsForNonEqual() throws X {
        E subject = getSubject();
        AbstractVerifiableObject<E, X> verifiable = getVerifiableInstance(getFailedValidationExecutor(), subject, "message");
        Assertions.assertThrows(getThrowableClass(),
                                () -> verifiable.isEqualTo(getNonEqualSubject()));
    }

    @Test
    default void testIsEqualThrowsForNullSubject() throws X {
        AbstractVerifiableObject<E, X> verifiable = getVerifiableInstance(getFailedValidationExecutor(), null, "message");
        Assertions.assertThrows(getThrowableClass(),
                                () -> verifiable.isEqualTo(getEqualSubject()));
    }

    @Test
    default void testIsNotEqualThrowsForSameInstance() throws X {
        E subject = getSubject();
        AbstractVerifiableObject<E, X> verifiable = getVerifiableInstance(getFailedValidationExecutor(), subject, "message");
        Assertions.assertThrows(getThrowableClass(),
                                () -> verifiable.isNotEqualTo(subject));
    }

    @Test
    default void testIsNotEqualThrowsForEqual() throws X {
        E subject = getSubject();
        AbstractVerifiableObject<E, X> verifiable = getVerifiableInstance(getFailedValidationExecutor(), subject, "message");
        Assertions.assertThrows(getThrowableClass(),
                                () -> verifiable.isNotEqualTo(getEqualSubject()));
    }

    @Test
    default void testIsNotEqualReturnsSubjectForNonEqual() throws X {
        E subject = getSubject();
        AbstractVerifiableObject<E, X> verifiable = getVerifiableInstance(getFailedValidationExecutor(), subject, "message");
        Assertions.assertTrue(subject == verifiable.isNotEqualTo(getNonEqualSubject()),
                              "An AbstractVerifiableObject should return the given subject for isNotEqual with not equal object.");
    }

    @Test
    default void testIsNotEqualThrowsForNullSubject() throws X {
        AbstractVerifiableObject<E, X> verifiable = getVerifiableInstance(getFailedValidationExecutor(), null, "message");
        Assertions.assertThrows(getThrowableClass(),
                                () -> verifiable.isNotEqualTo(getEqualSubject()));
    }


    @Test
    default void testSatisfiesReturnsSubjectForSatisfiedPredicate() throws X {
        E subject = getSubject();
        AbstractVerifiableObject<E, X> verifiable = getVerifiableInstance(getFailedValidationExecutor(), subject, "message");
        Assertions.assertTrue(subject == verifiable.satisfies(t -> null != t),
                              "An AbstractVerfiableObject should return the given subject for satisfied predicate.");
    }

    @Test
    default void testSatisfiesThrowsForNonSatisfiedPredicate() throws X {
        E subject = getSubject();
        AbstractVerifiableObject<E, X> verifiable = getVerifiableInstance(getFailedValidationExecutor(), subject, "message");
        Assertions.assertThrows(getThrowableClass(),
                                () -> verifiable.satisfies(t -> null == t));
    }

    @Test
    default void testSatisfiesThrowsForNullPredicate() throws X {
        E subject = getSubject();
        AbstractVerifiableObject<E, X> verifiable = getVerifiableInstance(getFailedValidationExecutor(), subject, "message");
        NullPointerException exception = Assertions.assertThrows(NullPointerException.class,
                                                                 () -> verifiable.satisfies(null));
        Assertions.assertEquals(ValidityUtils.nullArgumentMessage("expected"),
                                exception.getMessage(),
                                "An AbstractVerifiableObject should throw exception for null predicate to satisfies.");
    }

    // --------------------------------------------------------------
    // Overridden object method tests
    // --------------------------------------------------------------

    @Test
    default void testAbstractVerifiableObjectHasExpectedToString() {
        E subject = getSubject();
        AbstractVerifiableObject<E, X> verifiable = getVerifiableInstance(getFailedValidationExecutor(), subject, "message");
        Assertions.assertEquals(verifiable.getClass()
                                          .getSimpleName() + ": <" + ValidityUtils.describe(subject) + ">",
                                verifiable.toString(),
                                "An AbstractVerifiableObject should return the expected String for it's toString method.");
    }
}
