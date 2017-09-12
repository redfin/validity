[![Build Status](https://travis-ci.org/redfin/validity.svg?branch=master)](https://travis-ci.org/redfin/validity)
[![License](http://img.shields.io/:license-apache-brightgreen.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

# Validity

## What did we want from a validation library?

+ A fluent, highly readable syntax via method chaining.
+ Strongly typed so that only methods that make sense for the type being validated show up in IDE auto-completion.
+ The value being validated should be returned on successful validation to allow for one line validation and assignment.
+ The ability to customize the failure exceptions, the message, and easily add validation for more types.
+ Clean, informative failure messages without the user having to add a custom string.
+ The line that calls into the validation library should be the first line of the stack trace if validation fails.

## Installation

To install, you can simply include the dependency from Maven Central:

```xml
<dependency>
    <groupId>com.redfin</groupId>
    <artifactId>validity</artifactId>
    <version>3.0.0</version>
</dependency>
```

## Thread-safety and best practices

Many of the internal classes in the Validity library are immutable by default, but it cannot be counted upon, especially if a custom FailedValidationExecutor is supplied.
The internal validation objects are not intended to be stored or shared.
The intended use is to create them and immediately call a terminal operation on them to either return the subject being validated or throw an exception or error.

The validation also does not make any copies of the subject being validated.
If the subject is mutable, it may have it's inner state changed in a way that would cause validation to fail after it's been validated. It is best practice when validating arguments to make a defensive copy and validating the copy to avoid an invariant being broken.
An example of this would be if a list is handed to a method that verifies the list is not empty but then an external pointer to that list calls the clear method on it.
This is not a concern when validating immutable types or primitive types.

Be careful when using primitive boolean validation that they return the given subject, not a true value when validation passes.
For example, `validate.that(false).isFalse()` will return `false`, not true.
If the validation were to fail then it wouldn't return false, but would rather throw an exception.

For best effect, you should statically import the two static `Validity` method entry points.
```java
import static com.redfin.validity.Validity.validate;
import static com.redfin.validity.Validity.withMessage;
```

## Customization

The verifiable types are implemented with generics so that if a company or project wants to use the library but have different behavior than the default, they can.

A static class (like the `Validity` class itself) can be created that returns e a `VerifiableFactory` class with different `FailedValidationExecutor` implementations that handle the creation and throwing of Throwable's on failure. Implementations of that interface are where the stack trimming portions of the library are implemented.
The `VerifiableFactory` class can be sub-classed to add new, custom, verifiable types or to customize the entry point for specific throwable types on validation failure.

## Descriptive Predicates

If you would like to validate an argument that doesn't have a built in type but don't want to go so far as to define custom validation types, each of the verifiable objects that are pre-defined also take in a predicate for one of the method types.
This allows for any type of subject to be validated (though without smart auto completion and missing a bit of the default information from the failure messages).
However, there are descriptive predicate classes defined that bridge the gap of being used like a predicate while having a nice, human-readable toString output.

```java
Predicate<String> predicate = new DescriptivePredicate<>("null != {}", t -> null != t);
predicate.toString();
```
The output of the toString method above would be:
```java
t -> null != t
```

### example

```java

import static com.redfin.validity.Validity.validate;

public final class Foo {

    private final int i;

    public Foo(int i) {
        this.i = validate().that(i).isStrictlyPositive();
    }
}

public final class FooTest {

    @Test
    public void testInstantiateWithZero() {
        new ClassValidatedByValidity(0);
    }
}
```
Results in an exception similar to the following:
```
java.lang.IllegalArgumentException: Subject failed validation
    expected : t -> t > 0
     subject : <0>

    at com.redfin.example.FooTest.testInstantiateWithZero(FooTest.java:35)
    at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
    at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
    at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
    at java.lang.reflect.Method.invoke(Method.java:498)
    ... more lines below, truncated for space
```
