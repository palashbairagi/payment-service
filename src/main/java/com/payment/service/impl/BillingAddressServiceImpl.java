package com.payment.service.impl;

import com.payment.mapper.PaymentMapper;
import com.payment.model.dto.PaymentDto;
import com.payment.model.entity.BillingAddress;
import com.payment.repository.BillingAddressRepository;
import com.payment.service.BillingAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BillingAddressServiceImpl implements BillingAddressService {

    private final PaymentMapper paymentMapper;
    private final BillingAddressRepository billingAddressRepository;

    @Override
    public PaymentDto.BillingAddressDto addBillingAddress(PaymentDto.BillingAddressDto billingAddressDto) {

        BillingAddress billingAddress = paymentMapper.map(billingAddressDto);
        return paymentMapper.map(billingAddressRepository.save(billingAddress));
    }

}
