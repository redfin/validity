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

package com.redfin.validity.verifiable.objects;

import com.redfin.validity.FailedValidationHandler;

/**
 * todo
 *
 * @param <T>
 * @param <X>
 */
public abstract class AbstractVerifiableNumber<T extends Number & Comparable<T>, X extends Throwable> extends AbstractVerifiableComparable<T, X> {

    public AbstractVerifiableNumber(T actual, String description, FailedValidationHandler<X> failedValidationHandler) {
        super(actual, description, failedValidationHandler);
    }

    protected abstract T getZero();

    public T isZero() throws X {
        return isComparableTo(getZero());
    }

    public T isNotZero() throws X {
        return isNotComparableTo(getZero());
    }

    public T isStrictlyPositive() throws X {
        return isGreaterThan(getZero());
    }

    public T isStrictlyNegative() throws X {
        return isLessThan(getZero());
    }
}
