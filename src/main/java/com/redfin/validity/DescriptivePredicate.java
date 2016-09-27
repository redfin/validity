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

import java.util.function.Predicate;

/**
 * todo
 *
 * @param <T>
 */
public final class DescriptivePredicate<T> extends AbstractDescriptive implements Predicate<T> {

    private final Predicate<T> predicate;

    public DescriptivePredicate(String description, Predicate<T> predicate) {
        super(description);
        if (null == predicate) {
            throw new NullPointerException(Messages.nullArgumentMessage("predicate"));
        }
        this.predicate = predicate;
    }

    @Override
    public boolean test(T t) {
        return predicate.test(t);
    }

    @Override
    public DescriptivePredicate<T> negate() {
        return new DescriptivePredicate<>(getNegateDescription(), predicate.negate());
    }

    @Override
    public DescriptivePredicate<T> and(Predicate<? super T> other) {
        return new DescriptivePredicate<>(getAndDescription(other), predicate.and(other));
    }

    @Override
    public DescriptivePredicate<T> or(Predicate<? super T> other) {
        return new DescriptivePredicate<>(getOrDescription(other), predicate.or(other));
    }
}
