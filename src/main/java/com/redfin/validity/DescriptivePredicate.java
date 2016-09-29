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
 * A class implementing the {@link Predicate} interface.
 * This allows for the {@link Predicate} behavior but with a nice, human-readable toString output.
 *
 * @param <T> the type that this predicate will test.
 */
public final class DescriptivePredicate<T> implements Predicate<T> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Constants
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * The String token that must be used in place of the variable in a
     * {@link DescriptivePredicate} description.
     */
    public static final String TOKEN = "{}";

    private static final String NEGATE_FORMAT = "!(%s)";
    private static final String AND_FORMAT = "(%s) && (%s)";
    private static final String OR_FORMAT = "(%s) || (%s)";

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Fields
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private final String description;
    private final Predicate<T> predicate;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Instance Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Create a new {@link DescriptivePredicate} instance with the given description
     * and predicate.
     *
     * @param description the String token-ized description for this instance.
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
        if (null == description) {
            throw new NullPointerException(ValidityUtils.nullArgumentMessage("description"));
        }
        if (!description.contains(TOKEN)) {
            throw new IllegalArgumentException("DescriptivePredicate descriptions must include the token.");
        }
        if (null == predicate) {
            throw new NullPointerException(ValidityUtils.nullArgumentMessage("predicate"));
        }
        this.description = description;
        this.predicate = predicate;
    }

    @Override
    public boolean test(T t) {
        return predicate.test(t);
    }

    @Override
    public DescriptivePredicate<T> negate() {
        return new DescriptivePredicate<>(String.format(NEGATE_FORMAT,
                                                        description),
                                          predicate.negate());
    }

    @Override
    public DescriptivePredicate<T> and(Predicate<? super T> other) {
        return new DescriptivePredicate<>(String.format(AND_FORMAT,
                                                        description,
                                                        describeOther(other)),
                                          predicate.and(other));
    }

    @Override
    public DescriptivePredicate<T> or(Predicate<? super T> other) {
        return new DescriptivePredicate<>(String.format(OR_FORMAT,
                                                        description,
                                                        describeOther(other)),
                                          predicate.and(other));
    }

    private static String describeOther(Predicate<?> other) {
        if (null == other) {
            throw new NullPointerException(ValidityUtils.nullArgumentMessage("other"));
        }
        if (other instanceof DescriptivePredicate) {
            return ((DescriptivePredicate<?>) other).description;
        }
        return ValidityUtils.unknownPredicatePrefix() + other.toString();
    }
}
