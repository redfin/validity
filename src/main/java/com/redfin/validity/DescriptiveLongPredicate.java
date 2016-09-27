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

import java.util.function.LongPredicate;

/**
 * todo
 */
public final class DescriptiveLongPredicate extends AbstractDescriptive implements LongPredicate {

    private final LongPredicate predicate;

    public DescriptiveLongPredicate(String description, LongPredicate predicate) {
        super(description);
        if (null == predicate) {
            throw new NullPointerException(Messages.nullArgumentMessage("predicate"));
        }
        this.predicate = predicate;
    }

    @Override
    public boolean test(long value) {
        return predicate.test(value);
    }

    @Override
    public DescriptiveLongPredicate negate() {
        return new DescriptiveLongPredicate(getNegateDescription(), predicate.negate());
    }

    @Override
    public DescriptiveLongPredicate and(LongPredicate other) {
        return new DescriptiveLongPredicate(getAndDescription(other), predicate.and(other));
    }

    @Override
    public DescriptiveLongPredicate or(LongPredicate other) {
        return new DescriptiveLongPredicate(getOrDescription(other), predicate.or(other));
    }
}
