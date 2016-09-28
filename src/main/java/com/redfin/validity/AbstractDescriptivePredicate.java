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

/**
 * Base class for the descriptive predicate objects.
 */
public abstract class AbstractDescriptivePredicate {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Constants
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * The String token that should represent the variable in the predicate.
     * This allows clean, human-readable toString values from descriptive
     * predicates even when there is predicate composition. Any description
     * given to the {@link AbstractDescriptivePredicate} must contain at least one
     * instance of this token.
     */
    public static final String TOKEN = "{}";

    private static final String TO_STRING_PREFIX = "t -> ";
    private static final String NEGATE_FORMAT = "!(%s)";
    private static final String AND_FORMAT = "(%s) && (%s)";
    private static final String OR_FORMAT = "(%s) || (%s)";

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Fields
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private final String description;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Instance Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Create a new {@link AbstractDescriptivePredicate} instance with the given description.
     *
     * @param description the String token-ized description for this instance.
     *                    May not be null and must contain the token {@literal "{}"}.
     *                    Note including a single {@literal "t"} in the description as a variable
     *                    may lead to confusion as that is the name of the argument used in the toString
     *                    method.
     * @throws NullPointerException     if description is null.
     * @throws IllegalArgumentException if description does not contain {@literal "{}"}.
     */
    public AbstractDescriptivePredicate(String description) {
        if (null == description) {
            throw new NullPointerException(Messages.nullArgumentMessage("description"));
        }
        if (!description.contains(TOKEN)) {
            throw new IllegalArgumentException("Cannot have a description that doesn't contain the token: " + TOKEN);
        }
        this.description = description;
    }

    /**
     * @return the given String description for this instance.
     */
    protected String getDescription() {
        return description;
    }

    /**
     * @return a new String description that represents the negated version of the
     * current predicate.
     */
    protected String getDescriptionForNegate() {
        return String.format(NEGATE_FORMAT, description);
    }

    /**
     * @param otherDescription the String description of the other predicate to use in an "AND" composition.
     * @return a new String description that represents an "AND" composition between
     * this predicate and the other.
     */
    protected String getDescriptionForAnd(String otherDescription) {
        return String.format(AND_FORMAT, description, otherDescription);
    }

    /**
     * @param otherDescription the String description of the other predicate to use in an "OR" composition.
     * @return a new String description that represents an "OR" composition between
     * this predicate and the other.
     */
    protected String getDescriptionForOr(String otherDescription) {
        return String.format(OR_FORMAT, description, otherDescription);
    }

    /**
     * Create and return a human-readable String of the form:<br>
     * <br>
     * {@code "t -> description"}
     * <br>
     * where "description" is the current predicate description and which has
     * had all instances of the token {#TOKEN} replaced with the value of {@literal "t"}.<br>
     * <br>
     * For instance, if the description is {@literal "null != {}"}, then the toString value
     * would be:<br>
     * <br>
     * {@code "t -> null != t"}
     * <br>
     *
     * @return the human-readable String in the required format for the current description.
     */
    @Override
    public String toString() {
        return TO_STRING_PREFIX + description.replace(TOKEN, "t");
    }

    /**
     * @throws UnsupportedOperationException always
     * @deprecated {@link Object#equals(Object)} is not supported for descriptive predicates.
     */
    @Deprecated
    @Override
    public final boolean equals(Object obj) {
        throw new UnsupportedOperationException(Messages.unsupportedEqualsMessage());
    }

    /**
     * @throws UnsupportedOperationException always
     * @deprecated {@link Object#hashCode()} is not supported for verifiable objects.
     */
    @Deprecated
    @Override
    public final int hashCode() {
        throw new UnsupportedOperationException(Messages.unsupportedHashCodeMessage());
    }
}
