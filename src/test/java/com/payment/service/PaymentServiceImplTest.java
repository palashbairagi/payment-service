package com.payment.service;

import com.payment.enums.PaymentMethod;
import com.payment.enums.PaymentStatus;
import com.payment.exception.PaymentException;
import com.payment.mapper.PaymentMapper;
import com.payment.model.dto.PaymentDto;
import com.payment.model.entity.BillingAddress;
import com.payment.model.entity.Payment;
import com.payment.repository.PaymentRepository;
import com.payment.service.impl.PaymentServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceImplTest {

    @Spy
    PaymentMapper paymentMapper = Mappers.getMapper(PaymentMapper.class);

    @Mock
    PaymentRepository paymentRepository;

    @InjectMocks
    PaymentServiceImpl paymentService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = PaymentException.class)
    public void addPayments_invalidPaymentMethod_exception() {

        PaymentDto paymentDto = getPaymentDto();
        paymentDto.setPaymentMethod("DDA");

        List<PaymentDto> paymentDtos = new ArrayList<>();
        paymentDtos.add(paymentDto);

        paymentService.addPayments(paymentDtos);
    }

    @Test
    public void addPayment_validInput_success() {

        List<PaymentDto> paymentDtos = new ArrayList<>();
        paymentDtos.add(getPaymentDto());

        List<Payment> payments = new ArrayList<>();
        payments.add(getPayment());

        when(paymentRepository.saveAll(payments)).thenReturn(payments);
        when(paymentMapper.map(any(PaymentDto.class))).thenReturn(getPayment());
        when(paymentMapper.map(any(Payment.class))).thenReturn(getPaymentDto());


        paymentService.addPayments(paymentDtos);

        verify(paymentMapper, times(paymentDtos.size())).map(any(PaymentDto.class));
        verify(paymentRepository, times(1)).saveAll(payments);
        verify(paymentMapper, times(paymentDtos.size())).map(any(Payment.class));
    }

    private PaymentDto getPaymentDto() {

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setOrderId(1L);
        paymentDto.setAmount(12.0);
        paymentDto.setPaymentDate(LocalDate.now());
        paymentDto.setPaymentMethod("CREDIT_CARD");
        paymentDto.setConfirmationNumber(23L);

        PaymentDto.BillingAddress billingAddress = new PaymentDto.BillingAddress();
        billingAddress.setLine1("100 Elizabeth Dr");
        billingAddress.setLine2("Apt 1901");
        billingAddress.setCity("Pittsburgh");
        billingAddress.setState("PA");
        billingAddress.setZip("15220");

        paymentDto.setBillingAddress(billingAddress);

        return paymentDto;
    }

    private Payment getPayment() {

        Payment payment = new Payment();
        payment.setOrderId(1L);
        payment.setAmount(12.0);
        payment.setPaymentDate(LocalDate.now());
        payment.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        payment.setConfirmationNumber(23L);
        payment.setStatus(PaymentStatus.CONFIRMED);

        BillingAddress billingAddress = new BillingAddress();
        billingAddress.setAddressId(1L);
        billingAddress.setLine1("100 Elizabeth Dr");
        billingAddress.setLine2("Apt 1901");
        billingAddress.setCity("Pittsburgh");
        billingAddress.setState("PA");
        billingAddress.setZip("15220");

        payment.setBillingAddress(billingAddress);

        return payment;
    }
}
