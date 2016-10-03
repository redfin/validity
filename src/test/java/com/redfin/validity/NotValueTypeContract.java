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
 * A test contract that applies tests on classes that should
 * not be considered a value type. This means classes whose instances should
 * not support the {@link Object#equals(Object)} and the {@link Object#hashCode()}
 * methods.
 *
 * @param <T> the class that is being tested.
 */
public interface NotValueTypeContract<T> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test contract requirements
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * @return the class object of the class being tested.
     */
    T getNotValueTypeInstance();

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    default void testNotValueTypeInstanceThrowsExceptionForEqualsWithNonNullValue() {
        Assertions.assertThrows(UnsupportedOperationException.class,
                                () -> getNotValueTypeInstance().equals(null));
    }

    @Test
    default void testNotValueTypeInstanceThrowsExceptionForEqualsWithSelf() {
        T instance = getNotValueTypeInstance();
        Assertions.assertThrows(UnsupportedOperationException.class,
                                () -> instance.equals(instance));
    }

    @Test
    default void testNotValueTypeThrowsExceptionForHashCode() {
        Assertions.assertThrows(UnsupportedOperationException.class,
                                () -> getNotValueTypeInstance().hashCode());
    }
}
