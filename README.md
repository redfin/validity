# Validity


## Why validate?

Say you have a class or method that has some invariants. You can either:

1) write an if statement to check each argument.
1) ignore argument validation and assume things will break later on
1) use an existing validation library to validate the argument (e.g. Guava's Preconditions, etc)

I would argue that:

1) is verbose, is inconsistent in what exceptions are thrown, and is inconsistent in what the messages say.
1) is not a best practice. By not validating arguments the failures can either happen in a different part of the program and can drastically increase the amount of time it takes to debug a failure. Or, even worse, the failure may *never* happen, but instead incorrect results can trickle out through the program.
1) that is nice, but none of the libraries provide all of the things I was looking for.

## What do I want from a validation library?

+ A fluent, highly human-readable syntax to allow for clean method chaining with smart IDE auto-completion.
+ Strongly typed so that validation only displayed methods that made sense for the type being validated.
+ The ability to customize the failure exceptions, the message, and easily add validation for more types.
+ Clean, informative failure messages without the user having to add a custom string.
+ The line that calls into the validation library be the first line of the stack trace if validation fails.

## By example

### Validation of an argument to a constructor with an if statement

```
@Test
public void showValidationExampleWithIf() {
    new ClassValidatedByIf(0);
}

private static final class ClassValidatedByIf {

    private final int i;

    private ClassValidatedByIf(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Need 'i' to be greater than 0.");
        }
        this.i = i;
    }
}
```
Results in the following exception:

```
java.lang.IllegalArgumentException: Need 'i' to be greater than 0.

at com.redfin.example.FooTest$ClassValidatedByIf.<init>(FooTest.java:24)
at com.redfin.example.FooTest$ClassValidatedByIf.<init>(FooTest.java:18)
at com.redfin.example.FooTest.showValidationExampleWithIf(FooTest.java:10)
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

### Validation of an argument to a constructor with Validity

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

## Asserts vs. Verify

Another component of Validity is to have a consistent experience while writing both production code and unit tests. These two types of code, though, have some different needs. You typically throw an AssertionError on failure during a test instead of, say, an IllegalArgumentException. Also, while it is nice to know the line that failed, if you are following best practices there should only be 1 or 2 assertions per test method and they will be directly in the test class. The Validity library, therefore, trims out all the extraneous stack frames from the thrown error so that only the caller shows up.

This code:
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

## Customization

The verifiable types, though, are all implemented with generics so that if a company or project wants to use the library but have different behavior than the default, they would simply need to implement a wrapper.

A wrapper like the `Validity` class itself can be created to create a `VerifiableFactory` class with different `FailedValidationExecutor` implementations that handle the creation and throwing of Throwable's on failure. Implementations of that interface are where the stack trimming portions of the library are implemented.

The `VerifiableFactory` class can be sub-classed to add new, custom, verifiable types.

## Installation

todo : upload the project to Maven Central and then add to this portion of the documentation.
