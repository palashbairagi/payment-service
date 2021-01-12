package com.payment.enums;

public enum PaymentStatus {

    CONFIRMED,
    PENDING,
    CANCELLED;

    public String getKey() {
        return this.name();
    }

}
