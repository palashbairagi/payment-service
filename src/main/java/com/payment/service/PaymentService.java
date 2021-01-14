package com.payment.service;

import com.payment.model.dto.PaymentDto;

import java.util.List;

public interface PaymentService {

    List<PaymentDto> addPayments(List<PaymentDto> paymentDto);

    List<PaymentDto> getPayments(Long orderId);

    Void processRefund(Long orderId);

}
