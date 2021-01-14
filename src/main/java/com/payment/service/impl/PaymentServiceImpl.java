package com.payment.service.impl;

import com.payment.enums.ErrorCode;
import com.payment.enums.PaymentMethod;
import com.payment.enums.PaymentStatus;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PaymentServiceImpl implements PaymentService {

    private final PaymentMapper paymentMapper;
    private final PaymentRepository paymentRepository;

    @Override
    public List<PaymentDto> addPayments(List<PaymentDto> paymentRequestDto) {

        List<Payment> payments = paymentRequestDto.stream()
                .map(
                        paymentDto -> {

                            if (!PaymentMethod.isValidPaymentMethod(paymentDto.getPaymentMethod())) {
                                log.error("Invalid Card Type");
                                throw new PaymentException(ErrorCode.GENERAL_INPUT_ERROR);
                            }

                            return paymentMapper.map(paymentDto);
                        })
                .collect(Collectors.toList());

        List<Payment> newPayments = paymentRepository.saveAll(payments);

        return newPayments.stream()
                .map(paymentMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentDto> getPayments(Long orderId) {

        return paymentRepository.findByOrderId(orderId).stream()
                .map(paymentMapper::map)
                .collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Void processRefund(Long orderId) {

        Integer updatedRecords = paymentRepository.updatePaymentStatus(PaymentStatus.CANCELLED, orderId);

        if (updatedRecords == 0) {
            throw new PaymentException(ErrorCode.REQUEST_MALFORMED);
        }

        return null;
    }

}

