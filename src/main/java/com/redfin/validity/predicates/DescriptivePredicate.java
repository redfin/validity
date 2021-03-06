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
import java.util.function.Predicate;

/**
 * A class implementing the {@link Predicate} interface.
 * This allows for the {@link Predicate} behavior but with a nice, human-readable toString output.
 *
 * @param <T> the type that this predicate will test.
 */
public final class DescriptivePredicate<T>
           extends AbstractDescriptivePredicate
        implements Predicate<T> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Fields
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private final Predicate<T> predicate;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Instance Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Create a new {@link DescriptivePredicate} instance with the given description
     * and predicate.<br>
     * <br>
     * Note that the predicate description must contain the {@link AbstractDescriptivePredicate#TOKEN}
     * and should not contain the {@link AbstractDescriptivePredicate#VARIABLE} as a variable name
     * already in the description as that can lead to confusion.
     *
     * @param description the String tokenized description for this instance.
     *                    May not be null and must contain the token {@literal "{}"}.
     *                    Note including a single {@literal "t"} in the description as a variable
     *                    may lead to confusion as that is the name of the argument used in the toString
     *                    method.
     * @param predicate   the {@link Predicate} that this {@link DescriptivePredicate} wraps.
     *                    May not be null.
     * @throws NullPointerException     if description or predicate are null.
     * @throws IllegalArgumentException if description does not contain {@literal "{}"}.
     */
    public DescriptivePredicate(String description, Predicate<T> predicate) {
        super(description);
        if (null == predicate) {
            throw new NullPointerException(ValidityUtils.nullArgumentMessage("predicate"));
        }
        this.predicate = predicate;
    }

    @Override
    public boolean test(T t) {
        return predicate.test(t);
    }

    @Override
    public DescriptivePredicate<T> negate() {
        return new DescriptivePredicate<>(getNegateDescription(),
                                          predicate.negate());
    }

    @Override
    public DescriptivePredicate<T> and(Predicate<? super T> other) {
        return new DescriptivePredicate<>(getAndDescription(AbstractDescriptivePredicate.describeOther(other)),
                                          predicate.and(other));
    }

    @Override
    public DescriptivePredicate<T> or(Predicate<? super T> other) {
        return new DescriptivePredicate<>(getOrDescription(AbstractDescriptivePredicate.describeOther(other)),
                                          predicate.or(other));
    }
}
