package com.payment.model.dto;

import com.payment.model.common.Dto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PaymentDto extends Dto {

    private Long confirmationNumber;

    private LocalDate paymentDate;

    private String paymentMethod;

    private Long orderId;

    private String status;

    private BillingAddressDto billingAddress;

    @Data
    public static class BillingAddressDto extends Dto {

        private Long addressId;

        private String line1;

        private String line2;

        private String city;

        private String state;

        private String zip;

    }

}
