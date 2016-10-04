[![Build Status](https://travis-ci.org/redfin/validity.svg?branch=master)](https://travis-ci.org/redfin/validity)
[![License](http://img.shields.io/:license-apache-brightgreen.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

# Validity

## What did we want from a validation library?

+ A fluent, highly human-readable syntax to allow for clean method chaining.
+ Strongly typed so that only methods that make sense for the type being validated show up in IDE auto-completion.
+ The value being validated should be returned on successful validation to allow for one line validation and assignment.
+ The ability to customize the failure exceptions, the message, and easily add validation for more types.
+ Clean, informative failure messages without the user having to add a custom string.
+ The line that calls into the validation library should be the first line of the stack trace if validation fails.

## Thread-safety and best practices

Many of the internal classes in the Validity library are immutable by default, but it cannot be counted upon, especially if a custom FailedValidationExecutor is supplied.
The internal validation objects are not intended to be stored or shared.
The intended use is to create them and immediately call a terminal operation on them to either return the subject being validated or throw an exception or error.

The validation also does not make any copies of the subject being validated.
If the subject is mutable, it may have it's inner state changed in a way that would cause validation to fail after it's been validated. It is best practice when validating arguments to make a defensive copy and validating the copy to avoid an invariant being broken.
An example of this would be if a list is handed to a method that verifies the list is not empty but then an external pointer to that list calls the clear method on it.
This is not a concern when validating immutable types or primitive types.

## Customization

The verifiable types are implemented with generics so that if a company or project wants to use the library but have different behavior than the default, they can.

A static class (like the `Validity` class itself) can be created that returns e a `VerifiableFactory` class with different `FailedValidationExecutor` implementations that handle the creation and throwing of Throwable's on failure. Implementations of that interface are where the stack trimming portions of the library are implemented.
The `VerifiableFactory` class can be sub-classed to add new, custom, verifiable types.

## Descriptive Predicates

If you would like to validate an argument that doesn't have a built in type but don't want to go so far as to define custom validation types, each of the verifiable objects that are pre-defined also take in a predicate for one of the method types.
This allows for any type of subject to be validated (though without smart auto completion and missing a bit of the default information from the failure messages).
However, there are descriptive predicate classes defined that bridge the gap of being used like a predicate while having a nice, human-readable toString output.

```
Predicate<String> predicate = new DescriptivePredicate<>("null != {}", t -> null != t);
predicate.toString();
```
The output of the toString method above would be:
```
t -> null != t
```

## Asserts vs. Verify

Another component of Validity is to have a consistent experience while writing both production code and unit tests.
These two types of code, though, have some different needs.
With argument validation, you may want failures to throw an IllegalArgumentException and to show the full stack trace (up to when validation was called) so you know which part of the program is passing in incorrect data.
On the other hand, with test assertions you typically throw an AssertionError on failure.
Also, while it is nice to know the line that failed, there should only be 1 or 2 assertions per test method and which will be directly called from the test method itself so the stack trace is nearly always mostly just noise from the test running framework itself.

The Validity library, therefore, handles both cases.
When using the `Validity.verify()` type methods, it will throw an `IllegalArgumentException` on failure and will only trim the library stack frames out of the exception.
When using the `Validity.asserts()` type methods, it will throw an `AssertionError` on failure which trims all lines except the caller from the exception.

Note that this behavior is completely customizable, if desired, by defining a custom wrapper and `FailedValidationExecutor` implementation.

### Verify example

```
@Test
public void showValidationExampleValidityWay() {
    new ClassValidatedByValidity(0);
}

private static final class ClassValidatedByValidity {

    private final int i;

    private ClassValidatedByValidity(int i) {
        this.i = Validity.verify().that(i).isStrictlyPositive();
    }
}
```
Results in the following exception:
```
java.lang.IllegalArgumentException: Subject failed validation
    expected : t -> t > 0
     subject : <0>

    at com.redfin.example.FooTest$ClassValidatedByValidity.<init>(FooTest.java:35)
    at com.redfin.example.FooTest$ClassValidatedByValidity.<init>(FooTest.java:30)
    at com.redfin.example.FooTest.showValidationExampleValidityWay(FooTest.java:15)
    at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
    at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
    at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
    at java.lang.reflect.Method.invoke(Method.java:498)
    at org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:252)
    at org.junit.jupiter.engine.execution.ExecutableInvoker.invoke(ExecutableInvoker.java:114)
    at org.junit.jupiter.engine.descriptor.MethodTestDescriptor.lambda$invokeTestMethod$7(MethodTestDescriptor.java:210)
    at org.junit.jupiter.engine.execution.ThrowableCollector.execute(ThrowableCollector.java:40)
    at org.junit.jupiter.engine.descriptor.MethodTestDescriptor.invokeTestMethod(MethodTestDescriptor.java:206)
    at org.junit.jupiter.engine.descriptor.MethodTestDescriptor.execute(MethodTestDescriptor.java:155)
    at org.junit.jupiter.engine.descriptor.MethodTestDescriptor.execute(MethodTestDescriptor.java:63)
    at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.lambda$execute$0(HierarchicalTestExecutor.java:81)
    at org.junit.platform.engine.support.hierarchical.SingleTestExecutor.executeSafely(SingleTestExecutor.java:66)
    at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:77)
    at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.lambda$execute$0(HierarchicalTestExecutor.java:88)
    at org.junit.platform.engine.support.hierarchical.SingleTestExecutor.executeSafely(SingleTestExecutor.java:66)
    at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:77)
    at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.lambda$execute$0(HierarchicalTestExecutor.java:88)
    at org.junit.platform.engine.support.hierarchical.SingleTestExecutor.executeSafely(SingleTestExecutor.java:66)
    at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:77)
    at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:51)
    at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:43)
    at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:124)
    at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:84)
    at com.intellij.junit5.JUnit5IdeaTestRunner.startRunnerWithArgs(JUnit5IdeaTestRunner.java:42)
    at com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:262)
    at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:84)
    at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
    at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
    at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
    at java.lang.reflect.Method.invoke(Method.java:498)
    at com.intellij.rt.execution.application.AppMain.main(AppMain.java:147)
```

### Asserts example

```
@Test
public void testAsserts() {
    Validity.asserts().that("hello").startsWith("w");
}
```
Results in the following error (that's the full stack trace):
```
java.lang.AssertionError: Subject failed validation
    expected : t -> t.startsWith("w")
     subject : <hello>

	at com.redfin.example.FooTest.testAsserts(FooTest.java:41)
```

## Installation

todo : upload the project to Maven Central and then add to this portion of the documentation.
