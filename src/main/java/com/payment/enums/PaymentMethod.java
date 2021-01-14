package com.payment.enums;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum PaymentMethod {

    CREDIT_CARD,
    DEBIT_CARD;

    public String getKey() {
        return this.name();
    }

    public static boolean isValidPaymentMethod(final String paymentMethod) {

        return Arrays.stream(PaymentMethod.values())
                .map(PaymentMethod::name)
                .collect(Collectors.toSet())
                .contains(paymentMethod);

    }

}
