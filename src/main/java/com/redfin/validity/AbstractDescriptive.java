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
public abstract class AbstractDescriptive {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Constants
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public static final String TOKEN = "{}";

    private static final String TO_STRING_PREFIX = "t -> ";
    private static final String UNKNOWN_PREFIX = "unknown predicate: ";
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
     * Create a new {@link AbstractDescriptive} instance with the given description.
     *
     * @param description the String token-ized description for this instance.
     *                    May not be null and must contain the token {@literal "{}"}.
     * @throws NullPointerException     if description is null.
     * @throws IllegalArgumentException if description does not contain {@literal "{}"}
     */
    public AbstractDescriptive(String description) {
        if (null == description) {
            throw new NullPointerException(Messages.nullArgumentMessage("description"));
        }
        if (!description.contains(TOKEN)) {
            throw new IllegalArgumentException("Cannot have a description that doesn't contain the token: " + TOKEN);
        }
        this.description = description;
    }

    protected String getDescription() {
        return description;
    }

    protected String getNegateDescription() {
        return String.format(NEGATE_FORMAT, description);
    }

    protected String getAndDescription(Object other) {
        return String.format(AND_FORMAT, description, getOtherDescription(other));
    }

    protected String getOrDescription(Object other) {
        return String.format(OR_FORMAT, description, getOtherDescription(other));
    }

    private String getOtherDescription(Object other) {
        if (null == other) {
            throw new NullPointerException(Messages.nullArgumentMessage("other"));
        }
        if (other instanceof AbstractDescriptive) {
            return ((AbstractDescriptive) other).getDescription();
        } else {
            return UNKNOWN_PREFIX + other;
        }
    }

    @Override
    public String toString() {
        return TO_STRING_PREFIX + description.replace(TOKEN, "t");
    }
}
