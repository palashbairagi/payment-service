package com.payment.service;

import com.payment.model.dto.PaymentDto;

public interface BillingAddressService {

    PaymentDto.BillingAddressDto addBillingAddress(PaymentDto.BillingAddressDto billingAddressDto);

}
