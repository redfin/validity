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
 * todo
 *
 * @param <X>
 */
public final class VerifiablePrimitiveByteArray<X extends Throwable> extends AbstractVerifiablePrimitiveArray<byte[], X> {

    private final byte[] actual;

    public VerifiablePrimitiveByteArray(byte[] actual, String description, FailedValidationHandler<X> failedValidationHandler) {
        super(description, failedValidationHandler);
        this.actual = actual;
    }

    @Override
    protected byte[] getActual() {
        return actual;
    }

    public byte[] isEmpty() throws X {
        if (null == actual || actual.length != 0) {
            fail("t -> t.length == 0");
        }
        return actual;
    }

    public byte[] isNotEmpty() throws X {
        if (null == actual || actual.length <= 0) {
            fail("t -> t.length > 0");
        }
        return actual;
    }

    public byte[] hasLengthOf(int length) throws X {
        if (null == actual || actual.length != length) {
            fail("t -> t.length == " + Descriptions.describe(length));
        }
        return actual;
    }

    public byte[] hasLengthOfAtLeast(int length) throws X {
        if (null == actual || actual.length < length) {
            fail("t -> t.length >= " + Descriptions.describe(length));
        }
        return actual;
    }

    public byte[] hasLengthOfAtMost(int length) throws X {
        if (null == actual || actual.length > length) {
            fail("t -> t.length <= " + Descriptions.describe(length));
        }
        return actual;
    }

    public byte[] contains(byte value) throws X {
        if (!containsHelper(value)) {
            fail("t -> t.contains(" + Descriptions.describe(value) + ")");
        }
        return actual;
    }

    public byte[] doesNotContain(byte value) throws X {
        if (containsHelper(value)) {
            fail("t -> !t.contains(" + Descriptions.describe(value) + ")");
        }
        return actual;
    }

    private boolean containsHelper(byte value) {
        if (null == actual) {
            return false;
        }
        for (byte next : actual) {
            if (Byte.compare(next, value) == 0) {
                return true;
            }
        }
        return false;
    }
}
