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

package com.redfin.external_package;

import com.redfin.validity.DefaultValidityFailedValidationExecutor;
import com.redfin.validity.FailedValidationExecutor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

/*
 * A separate package is used for this test since the default behavior
 * of the DefaultValidityFailedValidationExecutor supplied in the library
 * trims out lines from inside the Validity package.
 */

final class StackTraceTest {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test values
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private static final FailedValidationExecutor<IllegalArgumentException> FVE = new DefaultValidityFailedValidationExecutor<>(IllegalArgumentException::new);

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /*
     * NOTE - IMPORTANT:
     *
     * The formatting of this file MATTERS. The tests are relying on relative line placement
     * to requires that the first line in the returned stack trace is that of the original
     * caller to the failed validation executors. Be careful when editing.
     */

    @Test
    void testDefaultValidityFailedValidationExecutorRemovesValidityStackFrames() {
        Exception exception = new NullPointerException();
        Assumptions.assumeTrue(null != exception.getStackTrace() && exception.getStackTrace().length > 0,
                               "This test can only work if the JVM is filling in stack traces.");
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class,
                                                                  () -> FVE.fail("expected", "subject", () -> "message"));
        int firstLineNumber = exception.getStackTrace()[0].getLineNumber();
        Assertions.assertTrue(null != thrown.getStackTrace() && thrown.getStackTrace().length > 0,
                              "If the JVM is filling in stack traces the thrown exception should have a stack trace.");
        Assertions.assertEquals(firstLineNumber + 4,
                                thrown.getStackTrace()[0].getLineNumber(),
                                "Default stack trace should have the caller as the first line number.");
        Assertions.assertEquals(exception.getStackTrace()[0].getClassName(),
                                thrown.getStackTrace()[0].getClassName(),
                                "Default stack trace should have the caller as the first line");
        Assertions.assertTrue(thrown.getStackTrace().length > 1,
                              "Default stack trace should have more than the caller.");
    }
}
