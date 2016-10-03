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
     * @return an instance of {@link AbstractVerifiableComparable}.
     */
    T getAbstractVerifiableComparable();

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    // todo
}
