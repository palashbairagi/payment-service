package com.payment.enums;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum PaymentStatus {

    CONFIRMED,
    PENDING,
    CANCELLED;

    public String getKey() {
        return this.name();
    }

    public static boolean isValidPaymentStatus(final String paymentStatus) {

        return Arrays.stream(PaymentStatus.values())
                .map(PaymentStatus::name)
                .collect(Collectors.toSet())
                .contains(paymentStatus);

    }

}
