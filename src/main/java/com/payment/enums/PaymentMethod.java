package com.payment.enums;

public enum PaymentMethod {

    CREDIT_CARD,
    DEBIT_CARD;

    public String getKey() {
        return this.name();
    }
}
