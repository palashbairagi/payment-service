package com.payment.service;

import com.payment.model.dto.PaymentDto;

public interface PaymentService {

    PaymentDto addPayment(PaymentDto paymentDto);

    PaymentDto getPayment(Long orderId);

    Void processRefund(Long orderId);

}
