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
 * A test contract that applies to classes that should be static only
 * (e.g. they should not be allowed to be instantiated).
 *
 * @param <T> the class that is being tested.
 */
public interface NonInstantiableContract<T> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test contract requirements
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * @return the class object of the class being tested.
     */
    Class<T> getNonInstantiableClassObject();

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    default void testNonInstantiableClassHasOnlyOneConstructor() {
        Assertions.assertEquals(1,
                                getNonInstantiableClassObject().getDeclaredConstructors().length,
                                "A non instantiable class should only have 1 constructor.");
    }

    @Test
    default void testNonInstantiableClassHasOnlySingleArgumentConstructor() throws NoSuchMethodException {
        Assertions.assertNotNull(getNonInstantiableClassObject().getDeclaredConstructor(),
                                 "A non instantiable class should only have the no argument constructor.");
    }

    @Test
    default void testNonInstantiableClassSingleConstructorIsPrivate() throws NoSuchMethodException {
        Assertions.assertTrue(Modifier.isPrivate(getNonInstantiableClassObject().getDeclaredConstructor().getModifiers()),
                              "A non instantiable class should only have a private constructor.");
    }

    @Test
    default void testNonInstantiableClassThrowsErrorIfConstructorIsCalled() {
        AssertionError error = Assertions.expectThrows(AssertionError.class,
                                                       () -> {
                                                           try {
                                                               Constructor<T> constructor = getNonInstantiableClassObject().getDeclaredConstructor();
                                                               constructor.setAccessible(true);
                                                               constructor.newInstance();
                                                           } catch (Throwable thrown) {
                                                               // A constructor error is wrapped, unwrap it
                                                               if (thrown instanceof InvocationTargetException) {
                                                                   throw thrown.getCause();
                                                               }
                                                               throw thrown;
                                                           }
                                                       });
        Assertions.assertEquals(ValidityUtils.nonInstantiableMessage(),
                                error.getMessage(),
                                "A non instantiable class should throw the expected error if the construct is called");
    }
}
