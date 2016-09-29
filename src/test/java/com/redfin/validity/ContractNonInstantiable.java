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
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

/**
 * A contract to be implemented by test classes of types that are
 * intended to be non-instantiable classes.
 *
 * @param <T> the class type under test.
 */
interface ContractNonInstantiable<T> {

    /**
     * Method for implementing class to allow for inheriting tests.
     *
     * @return the class object for the type under test.
     */
    Class<T> getTestClass();

    @Test
    default void testNonInstantiableClassIsFinal() {
        Assertions.assertTrue(Modifier.isFinal(getTestClass().getModifiers()),
                              "A non-instantiable class should be declared as final.");
    }

    @Test
    default void testNonInstantiableClassDoesNotInherit() {
        Assertions.assertEquals(Object.class,
                                getTestClass().getSuperclass(),
                                "A non-instantiable class should only inherit from Object.");
    }

    @Test
    default void testNonInstantiableClassDoesNotImplement() {
        Assertions.assertEquals(0,
                                getTestClass().getInterfaces().length,
                                "A non-instantiable class should not implement any interfaces.");
    }

    @Test
    default void testNonInstantiableClassHasOnlyOneConstructor() {
        Assertions.assertEquals(1,
                                getTestClass().getDeclaredConstructors().length,
                                "A non-instantiable class should only have 1 constructor.");
    }

    @Test
    default void testNonInstantiableConstructorIsZeroArgumentConstructor() throws NoSuchMethodException {
        Assertions.assertNotNull(getTestClass().getDeclaredConstructor(),
                                 "A non-instantiable class should have a zero argument constructor.");
    }

    @Test
    default void testNonInstantiableClassConstructorIsPrivate() throws NoSuchMethodException {
        Assertions.assertTrue(Modifier.isPrivate(getTestClass().getDeclaredConstructor().getModifiers()),
                              "A non-instantiable class should have a private zero argument constructor.");
    }

    @Test
    default void testNonInstantiableClassConstructorThrowsError() {
        InvocationTargetException error = Assertions.expectThrows(InvocationTargetException.class, () -> {
            Constructor<T> constructor = getTestClass().getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        });
        Assertions.assertNotNull(error.getCause(),
                                 "The error from the constructor should be wrapped in a InvocationTargetException.");
        Assertions.assertEquals(ValidityUtils.nonInstantiableMessage(),
                                error.getCause().getMessage(),
                                "A non-instantiable class should throw the expected AssertionError from it's constructor.");
    }
}
