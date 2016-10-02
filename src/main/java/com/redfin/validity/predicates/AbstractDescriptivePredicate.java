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

/**
 * A class containing a description for the actual descriptive predicate sub types.
 * This allows for the predicates to have a nice, human-readable toString output.
 */
public abstract class AbstractDescriptivePredicate {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Constants
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * The String token that must be part of the description for
     * an {@link AbstractDescriptivePredicate} instance. This is stored
     * in the predicate to allow for composition of predicates and then
     * converted by the {@link #toString()} to give a nice, human-readable
     * string value for the predicate. For example, given a predicate with
     * the following description:<br>
     * <br>
     * {@code "null != {}"}<br>
     * <br>
     * The toString would return:<br>
     * <br>
     * {@code "t -> null != t"}<br>
     * <br>
     * The value of "t" is from the the {@link #VARIABLE} value.
     */
    public static final String TOKEN = "{}";

    /**
     * The String variable name that will replace all instances of the {@link #TOKEN}
     * String when the {@link #toString()} method is called on a descriptive predicate.
     */
    public static final String VARIABLE = "t";

    private static final String NEGATE_FORMAT = "!(%s)";
    private static final String AND_FORMAT = "(%s) && (%s)";
    private static final String OR_FORMAT = "(%s) || (%s)";
    private static final String TO_STRING_PREFIX = "t -> ";

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Fields
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private final String description;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Instance Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Create a new {@link AbstractDescriptivePredicate} instance with the given description.<br>
     * <br>
     * Note that the predicate description must contain the {@link #TOKEN} and should not
     * contain the {@link #VARIABLE} as a variable name already in the description as that
     * can lead to confusion.
     *
     * @param description the String description for this predicate instance.
     *                    May not be null and must contain the TOKEN.
     * @throws NullPointerException     if description is null.
     * @throws IllegalArgumentException if description does not contain {@link #TOKEN}.
     */
    public AbstractDescriptivePredicate(String description) {
        if (null == description) {
            throw new NullPointerException(ValidityUtils.nullArgumentMessage("description"));
        }
        if (!description.contains(TOKEN)) {
            throw new IllegalArgumentException("A descriptive predicate must have the token in the description.");
        }
        this.description = description;
    }

    /**
     * @return the formatted new description of the negated version of
     * this description.
     */
    protected final String getNegateDescription() {
        return String.format(NEGATE_FORMAT, description);
    }

    /**
     * @param otherDescription the String description of the other predicate.
     * @return the formatted new description of the AND composition of this
     * description and the other description.
     */
    protected final String getAndDescription(String otherDescription) {
        return String.format(AND_FORMAT, description, otherDescription);
    }

    /**
     * @param otherDescription the String description of the other predicate.
     * @return the formatted new description of the OR composition of this
     * description and the other description.
     */
    protected final String getOrDescription(String otherDescription) {
        return String.format(OR_FORMAT, description, otherDescription);
    }

    @Override
    public final String toString() {
        return TO_STRING_PREFIX + description.replace(TOKEN, "t");
    }

    /**
     * @throws UnsupportedOperationException always.
     * @deprecated descriptive predicate objects cannot be tested for equality.
     */
    @Deprecated
    @Override
    public final boolean equals(Object obj) {
        throw new UnsupportedOperationException("This class cannot be treated as a value and does not support the Object equals method");
    }

    /**
     * @throws UnsupportedOperationException always.
     * @deprecated descriptive predicate objects cannot be hashed.
     */
    @Deprecated
    @Override
    public final int hashCode() {
        throw new UnsupportedOperationException("This class cannot be treated as a value and does not support the Object hashCode method");
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Static Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Describe the other object (assuming it to be a predicate type).
     *
     * @param other the object to be described as a predicate.
     *              May not be null.
     * @return the String description (as a predicate) of other.
     * @throws NullPointerException if other is null.
     */
    protected static String describeOther(Object other) {
        if (null == other) {
            throw new NullPointerException(ValidityUtils.nullArgumentMessage("other"));
        }
        if (other instanceof AbstractDescriptivePredicate) {
            return ((AbstractDescriptivePredicate) other).description;
        }
        return ValidityUtils.unknownPredicatePrefix() + other.toString();
    }
}
