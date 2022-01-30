package com.alten.hotel.model.validation;

public abstract class ValidationStep<T> {

    protected ValidationStep<T> next;

    public ValidationStep(ValidationStep<T> next) {
        this.next = next;
    }

    public abstract Boolean verify(T toValidate);
}
