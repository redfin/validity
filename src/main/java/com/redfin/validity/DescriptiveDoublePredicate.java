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

import java.util.function.DoublePredicate;

/**
 * todo
 */
public final class DescriptiveDoublePredicate extends AbstractDescriptive implements DoublePredicate {

    private final DoublePredicate predicate;

    public DescriptiveDoublePredicate(String description, DoublePredicate predicate) {
        super(description);
        if (null == predicate) {
            throw new NullPointerException(Messages.nullArgumentMessage("predicate"));
        }
        this.predicate = predicate;
    }

    @Override
    public boolean test(double value) {
        return predicate.test(value);
    }

    @Override
    public DescriptiveDoublePredicate negate() {
        return new DescriptiveDoublePredicate(getNegateDescription(), predicate.negate());
    }

    @Override
    public DescriptiveDoublePredicate and(DoublePredicate other) {
        return new DescriptiveDoublePredicate(getAndDescription(other), predicate.and(other));
    }

    @Override
    public DescriptiveDoublePredicate or(DoublePredicate other) {
        return new DescriptiveDoublePredicate(getOrDescription(other), predicate.or(other));
    }
}
