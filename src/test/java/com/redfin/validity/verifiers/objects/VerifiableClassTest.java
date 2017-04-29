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

package com.redfin.validity.verifiers.objects;

import com.redfin.validity.DefaultValidityFailedValidationExecutor;
import com.redfin.validity.FailedValidationExecutor;
import com.redfin.validity.verifiers.AbstractVerifiableObjectContract;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

final class VerifiableClassTest implements AbstractVerifiableObjectContract<IllegalArgumentException, Class<IllegalArgumentException>, VerifiableClass<IllegalArgumentException, IllegalArgumentException>> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test values & contract implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public Class<IllegalArgumentException> getSubject() {
        return IllegalArgumentException.class;
    }

    @Override
    public Class<IllegalArgumentException> getEqualSubject() {
        return IllegalArgumentException.class;
    }

    @Override
    public Class<IllegalArgumentException> getNonEqualSubject() {
        return null;
    }

    @Override
    public Class<IllegalArgumentException> getThrowableClass() {
        return IllegalArgumentException.class;
    }

    @Override
    public FailedValidationExecutor<IllegalArgumentException> getFailedValidationExecutor() {
        return new DefaultValidityFailedValidationExecutor<>(IllegalArgumentException::new);
    }

    @Override
    public VerifiableClass<IllegalArgumentException, IllegalArgumentException> getVerifiableInstance(FailedValidationExecutor<IllegalArgumentException> failedValidationExecutor, Class<IllegalArgumentException> subject, String message) {
        return new VerifiableClass<>(failedValidationExecutor, subject, message);
    }

    private <T> VerifiableClass<T, IllegalArgumentException> getInstance(Class<T> clazz) {
        return new VerifiableClass<>(getFailedValidationExecutor(), clazz, "message");
    }

    @Tag("foo")
    private static class FooTest { }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    void testIsAssignableToProperClassReturnsSubject() {
        Class<Object> subject = Object.class;
        VerifiableClass<?, IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.isAssignableFrom(IllegalArgumentException.class),
                              "VerifiableClass should return the given subject instance for assignable class.");
    }

    @Test
    void testIsAssignableToThrowsExceptionForNotAssignableClass() {
        Class<?> subject = getSubject();
        VerifiableClass<?, IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isAssignableFrom(AssertionError.class));
    }

    @Test
    void testIsAssignableToThrowsExceptionForNullSubject() {
        VerifiableClass<?, IllegalArgumentException> verifiable = getInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.isAssignableFrom(IllegalArgumentException.class));
    }

    @Test
    void testHasAnnotationReturnsSubjectForPresentAnnotation() {
        Class<FooTest> subject = FooTest.class;
        VerifiableClass<FooTest, IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertTrue(subject == verifiable.hasAnnotation(Tag.class),
                              "A VerifiableClass should return the given subject when the subject has the given annotation.");
    }

    @Test
    void testHasAnnotationThrowsForNonPresentAnnotation() {
        Class<FooTest> subject = FooTest.class;
        VerifiableClass<FooTest, IllegalArgumentException> verifiable = getInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasAnnotation(Deprecated.class));
    }

    @Test
    void testHasAnnotationThrowsForNullSubject() {
        VerifiableClass<?, IllegalArgumentException> verifiable = getInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasAnnotation(Deprecated.class));
    }
}
