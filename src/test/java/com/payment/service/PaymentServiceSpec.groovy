package com.payment.service

import com.payment.enums.PaymentMethod
import com.payment.enums.PaymentStatus
import com.payment.exception.PaymentException
import com.payment.mapper.PaymentMapper
import com.payment.model.dto.PaymentDto
import com.payment.model.entity.BillingAddress
import com.payment.model.entity.Payment
import com.payment.repository.PaymentRepository
import com.payment.service.impl.PaymentServiceImpl
import spock.lang.Specification

import java.time.LocalDate

class PaymentServiceSpec extends Specification {

    PaymentMapper paymentMapper
    PaymentRepository paymentRepository
    PaymentServiceImpl paymentService

    def setup() {

        paymentMapper = Mock(PaymentMapper)
        paymentRepository = Mock(PaymentRepository)
        paymentService = new PaymentServiceImpl(paymentMapper, paymentRepository)

    }

    def getInvalidInputDto() {
        def invalidInput = new ArrayList<PaymentDto>()
        PaymentDto paymentDto = getPaymentDto()
        paymentDto.setPaymentMethod("INVALID")
        invalidInput.add(paymentDto)
        return invalidInput
    }

    def getPaymentDto() {
        PaymentDto paymentDto = new PaymentDto()
        paymentDto.setOrderId(1L)
        paymentDto.setAmount(12.0)
        paymentDto.setPaymentDate(LocalDate.now())
        paymentDto.setPaymentMethod("CREDIT_CARD")
        paymentDto.setConfirmationNumber(23L)

        PaymentDto.BillingAddress billingAddress = new PaymentDto.BillingAddress()
        billingAddress.setLine1("100 Elizabeth Dr")
        billingAddress.setLine2("Apt 1901")
        billingAddress.setCity("Pittsburgh")
        billingAddress.setState("PA")
        billingAddress.setZip("15220")

        paymentDto.setBillingAddress(billingAddress)
        paymentDto
    }

    def getPaymentDtos() {
        List<PaymentDto> paymentDtos = new ArrayList<>()
        paymentDtos.add(getPaymentDto())
        paymentDtos
    }

    def getPayment() {

        Payment payment = new Payment()
        payment.setOrderId(1L)
        payment.setAmount(12.0)
        payment.setPaymentDate(LocalDate.now())
        payment.setPaymentMethod(PaymentMethod.CREDIT_CARD)
        payment.setConfirmationNumber(23L)
        payment.setStatus(PaymentStatus.CONFIRMED)

        BillingAddress billingAddress = new BillingAddress()
        billingAddress.setAddressId(1L)
        billingAddress.setLine1("100 Elizabeth Dr")
        billingAddress.setLine2("Apt 1901")
        billingAddress.setCity("Pittsburgh")
        billingAddress.setState("PA")
        billingAddress.setZip("15220")

        payment.setBillingAddress(billingAddress)

        return payment
    }

    def getPayments() {
        List<Payment> payments = new ArrayList<>()
        payments.add(getPayment())
        payments
    }

    def "addPayment | invalidPaymentMethod | throwException "() {

        when:
        paymentService.addPayments(getInvalidInputDto())

        then:
        def ex = thrown(PaymentException)
        ex.getError().getErrorCode().getKey() == "GENERAL_INPUT_ERROR"

    }

    def "addPayment | validPaymentMethod | success "() {

        given:
        paymentRepository.saveAll(*_) >> getPayments()
        paymentMapper.map(_ as Payment) >> getPaymentDto()
        paymentMapper.map(_ as PaymentDto) >> getPayment()

        when:
        def result = paymentService.addPayments(getPaymentDtos())
        then:
        result.get(0).getPaymentMethod() == PaymentMethod.CREDIT_CARD.toString()

    }

}
