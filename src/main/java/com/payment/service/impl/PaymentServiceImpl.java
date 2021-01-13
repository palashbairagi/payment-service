package com.payment.service.impl;

import com.payment.enums.ErrorCode;
import com.payment.enums.PaymentMethod;
import com.payment.exception.PaymentException;
import com.payment.mapper.PaymentMapper;
import com.payment.model.dto.PaymentDto;
import com.payment.model.entity.Payment;
import com.payment.repository.PaymentRepository;
import com.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PaymentServiceImpl implements PaymentService {

    private final PaymentMapper paymentMapper;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public PaymentDto addPayment(PaymentDto paymentRequestDto) {

        if(!paymentRequestDto.getPaymentMethod().equals(PaymentMethod.CREDIT_CARD.getKey()) &&
                !paymentRequestDto.getPaymentMethod().equals(PaymentMethod.DEBIT_CARD.getKey())) {
            log.error("Invalid Card Type");
            throw new PaymentException(ErrorCode.GENERAL_INPUT_ERROR);
        }

        Payment payment = paymentRepository.save(paymentMapper.map(paymentRequestDto));

        return paymentMapper.map(payment);
    }

    @Override
    public PaymentDto getPayment(Long orderId) {
        return null;
    }

    @Override
    public Void processRefund(Long orderId) {
        return null;
    }

}

