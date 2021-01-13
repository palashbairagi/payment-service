package com.payment.mapper;

import com.payment.model.dto.PaymentDto;
import com.payment.model.entity.Payment;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface PaymentMapper {

    Payment map(PaymentDto paymentDto);

    PaymentDto map(Payment payment);

}
