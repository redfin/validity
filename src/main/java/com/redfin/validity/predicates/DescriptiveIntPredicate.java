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
import java.util.function.IntPredicate;

/**
 * A class implementing the {@link IntPredicate} interface.
 * This allows for the {@link IntPredicate} behavior but with a nice, human-readable toString output.
 */
public final class DescriptiveIntPredicate extends AbstractDescriptivePredicate implements IntPredicate {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Fields
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private final IntPredicate predicate;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Instance Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Create a new {@link DescriptiveLongPredicate} instance with the given description
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
     * @param predicate   the {@link IntPredicate} that this {@link DescriptiveLongPredicate} wraps.
     *                    May not be null.
     * @throws NullPointerException     if description or predicate are null.
     * @throws IllegalArgumentException if description does not contain {@literal "{}"}.
     */
    public DescriptiveIntPredicate(String description, IntPredicate predicate) {
        super(description);
        if (null == predicate) {
            throw new NullPointerException(ValidityUtils.nullArgumentMessage("predicate"));
        }
        this.predicate = predicate;
    }

    @Override
    public boolean test(int value) {
        return predicate.test(value);
    }

    @Override
    public DescriptiveIntPredicate negate() {
        return new DescriptiveIntPredicate(getNegateDescription(),
                                           predicate.negate());
    }

    @Override
    public DescriptiveIntPredicate and(IntPredicate other) {
        return new DescriptiveIntPredicate(getAndDescription(AbstractDescriptivePredicate.describeOther(other)),
                                           predicate.and(other));
    }

    @Override
    public DescriptiveIntPredicate or(IntPredicate other) {
        return new DescriptiveIntPredicate(getOrDescription(AbstractDescriptivePredicate.describeOther(other)),
                                           predicate.or(other));
    }
}
