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
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

final class VerifiableCollectionTest
 implements AbstractVerifiableObjectContract<IllegalArgumentException, List<String>, VerifiableCollection<String, List<String>, IllegalArgumentException>> {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test values & contract implementations
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public VerifiableCollection<String, List<String>, IllegalArgumentException> getNotValueTypeInstance() {
        return getVerifiableInstance(new ArrayList<>());
    }

    @Override
    public FailedValidationExecutor<IllegalArgumentException> getFailedValidationExecutor() {
        return new DefaultValidityFailedValidationExecutor<>(IllegalArgumentException::new);
    }

    @Override
    public Class<IllegalArgumentException> getThrowableClass() {
        return IllegalArgumentException.class;
    }

    @Override
    public List<String> getSubject() {
        List<String> list = new ArrayList<>();
        list.add("hello");
        return list;
    }

    @Override
    public List<String> getEqualSubject() {
        List<String> list = new ArrayList<>();
        list.add("hello");
        return list;
    }

    @Override
    public List<String> getNonEqualSubject() {
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        return list;
    }

    @Override
    public VerifiableCollection<String, List<String>, IllegalArgumentException> getVerifiableInstance(FailedValidationExecutor<IllegalArgumentException> failedValidationExecutor,
                                                                                                      List<String> subject,
                                                                                                      Supplier<String> messageSupplier) {
        return new VerifiableCollection<>(failedValidationExecutor, subject, messageSupplier);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Test cases
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    void testIsEmptyReturnsSubjectForEmptySubject() {
        List<String> subject = new ArrayList<>();
        VerifiableCollection<?, ?, IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.isEmpty(),
                              "VerifiableCollection should return it's subject for isEmpty when subject is empty.");
    }

    @Test
    void testIsEmptyThrowsForNonEmptySubject() {
        List<String> subject = new ArrayList<>();
        subject.add("hello");
        VerifiableCollection<?, ?, IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isEmpty);
    }

    @Test
    void testIsEmptyThrowsForNullSubject() {
        VerifiableCollection<?, ?, IllegalArgumentException> verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isEmpty);
    }

    @Test
    void testIsNotEmptyReturnsThrowsForEmptySubject() {
        List<String> subject = new ArrayList<>();
        VerifiableCollection<?, ?, IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isNotEmpty);
    }

    @Test
    void testIsNotEmptyReturnsSubjectForNonEmptySubject() {
        List<String> subject = new ArrayList<>();
        subject.add("hello");
        VerifiableCollection<?, ?, IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.isNotEmpty(),
                              "VerifiableCollection should return it's subject for isNotEmpty when subject is not empty.");
    }

    @Test
    void testIsNotEmptyThrowsForNullSubject() {
        VerifiableCollection<?, ?, IllegalArgumentException> verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                verifiable::isNotEmpty);
    }

    @Test
    void testHasSizeOfReturnsSubjectWithMatchingSize() {
        List<String> subject = new ArrayList<>();
        subject.add("hello");
        VerifiableCollection<?, ?, IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.hasSizeOf(1),
                              "VerifiableCollection should return it's subject for hasSizeOf when subject matches the given size.");
    }

    @Test
    void testHasSizeOfThrowsForSubjectWithNonMatchingSize() {
        List<String> subject = new ArrayList<>();
        subject.add("hello");
        VerifiableCollection<?, ?, IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasSizeOf(2));
    }

    @Test
    void testHasSizeOfThrowsForNullSubject() {
        VerifiableCollection<?, ?, IllegalArgumentException> verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasSizeOf(1));
    }

    @Test
    void testHasSizeOfAtLeastReturnsSubjectWithMatchingSize() {
        List<String> subject = new ArrayList<>();
        subject.add("hello");
        VerifiableCollection<?, ?, IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.hasSizeOfAtLeast(1),
                              "VerifiableCollection should return it's subject for hasSizeOfAtLeast when subject matches the given size.");
    }

    @Test
    void testHasSizeOfAtLeastReturnsSubjectWithGreaterSize() {
        List<String> subject = new ArrayList<>();
        subject.add("hello");
        subject.add("world");
        VerifiableCollection<?, ?, IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.hasSizeOfAtLeast(1),
                              "VerifiableCollection should return it's subject for hasSizeOfAtLeast when subject matches the given size.");
    }

    @Test
    void testHasSizeOfAtLeastThrowsForSubjectWithNonMatchingSize() {
        List<String> subject = new ArrayList<>();
        subject.add("hello");
        VerifiableCollection<?, ?, IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasSizeOfAtLeast(2));
    }

    @Test
    void testHasSizeOfAtLeastThrowsForNullSubject() {
        VerifiableCollection<?, ?, IllegalArgumentException> verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasSizeOfAtLeast(1));
    }

    @Test
    void testHasSizeOfAtMostReturnsSubjectWithMatchingSize() {
        List<String> subject = new ArrayList<>();
        subject.add("hello");
        VerifiableCollection<?, ?, IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.hasSizeOfAtMost(1),
                              "VerifiableCollection should return it's subject for hasSizeOfAtMost when subject matches the given size.");
    }

    @Test
    void testHasSizeOfAtMostReturnsSubjectWithGreaterSize() {
        List<String> subject = new ArrayList<>();
        subject.add("hello");
        subject.add("world");
        VerifiableCollection<?, ?, IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.hasSizeOfAtMost(2),
                              "VerifiableCollection should return it's subject for hasSizeOfAtMost when subject matches the given size.");
    }

    @Test
    void testHasSizeOfAtMostThrowsForSubjectWithNonMatchingSize() {
        List<String> subject = new ArrayList<>();
        subject.add("hello");
        VerifiableCollection<?, ?, IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasSizeOfAtMost(0));
    }

    @Test
    void testHasSizeOfAtMostThrowsForNullSubject() {
        VerifiableCollection<?, ?, IllegalArgumentException> verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.hasSizeOfAtMost(1));
    }

    @Test
    void testContainsReturnsSubjectForContainingSubject() {
        List<String> subject = new ArrayList<>();
        String element = "hello";
        subject.add(element);
        VerifiableCollection<String, List<String>, IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.contains(element),
                              "VerifiableCollection should return subject for contains with containing subject.");
    }

    @Test
    void testContainsThrowsExceptionForNonContainingSubject() {
        List<String> subject = new ArrayList<>();
        String element = "hello";
        VerifiableCollection<String, List<String>, IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.contains(element));
    }

    @Test
    void testContainsThrowsExceptionForNullSubject() {
        String element = "hello";
        VerifiableCollection<String, List<String>, IllegalArgumentException> verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.contains(element));
    }

    @Test
    void testDoesNotContainThrowsForContainingSubject() {
        List<String> subject = new ArrayList<>();
        String element = "hello";
        subject.add(element);
        VerifiableCollection<String, List<String>, IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.doesNotContain(element));

    }

    @Test
    void testDoesNotContainReturnsSubjectForNonContainingSubject() {
        List<String> subject = new ArrayList<>();
        VerifiableCollection<String, List<String>, IllegalArgumentException> verifiable = getVerifiableInstance(subject);
        Assertions.assertTrue(subject == verifiable.doesNotContain("hello"),
                              "VerifiableCollection should return subject for doesNotContain with non-containing subject.");
    }

    @Test
    void testDoesNotContainThrowsExceptionForNullSubject() {
        VerifiableCollection<String, List<String>, IllegalArgumentException> verifiable = getVerifiableInstance(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> verifiable.contains("hello"));
    }
}
