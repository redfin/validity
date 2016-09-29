package com.redfin.validity.verifiable.objects;

import com.redfin.validity.FailedValidationHandler;
import com.redfin.validity.verifiable.AbstractVerifiableObject;

public abstract class AbstractVerifiableComparable<T extends Comparable<T>, X extends Throwable> extends AbstractVerifiableObject<T, X> {

    public AbstractVerifiableComparable(T actual, String description, FailedValidationHandler<X> failedValidationHandler) {
        super(actual, description, failedValidationHandler);
    }

    public T isComparableTo(T value) throws X {
        T actual = getActual();
        if (null == actual || actual.compareTo(value) != 0) {
            fail("t -> t.compareTo(" + describeType(value) + ") == 0");
        }
        return actual;
    }

    public T isNotComparableTo(T value) throws X {
        T actual = getActual();
        if (null == actual || actual.compareTo(value) == 0) {
            fail("t -> t.compareTo(" + describeType(value) + ") != 0");
        }
        return actual;
    }

    public T isGreaterThan(T value) throws X {
        T actual = getActual();
        if (null == actual || actual.compareTo(value) <= 0) {
            fail("t -> t.compareTo(" + describeType(value) + ") > 0");
        }
        return actual;
    }

    public T isGreaterThanOrEqualTo(T value) throws X {
        T actual = getActual();
        if (null == actual || actual.compareTo(value) < 0) {
            fail("t -> t.compareTo(" + describeType(value) + ") >= 0");
        }
        return actual;
    }

    public T isLessThan(T value) throws X {
        T actual = getActual();
        if (null == actual || actual.compareTo(value) >= 0) {
            fail("t -> t.compareTo(" + describeType(value) + ") < 0");
        }
        return actual;
    }

    public T isLessThanOrEqualTo(T value) throws X {
        T actual = getActual();
        if (null == actual || actual.compareTo(value) > 0) {
            fail("t -> t.compareTo(" + describeType(value) + ") <= 0");
        }
        return actual;
    }

    public T isAtLeast(T value) throws X {
        return isGreaterThanOrEqualTo(value);
    }

    public T isAtMost(T value) throws X {
        return isLessThanOrEqualTo(value);
    }
}
