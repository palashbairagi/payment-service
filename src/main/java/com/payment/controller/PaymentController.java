package com.payment.controller;

import com.payment.model.common.ResponseDto;
import com.payment.model.dto.PaymentDto;
import com.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/payment", consumes = "application/json")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PaymentController {

    private final PaymentService paymentService;

    // TODO: Validation

    @PostMapping
    public ResponseEntity<ResponseDto<List<PaymentDto>>> addPayments(@RequestBody List<PaymentDto> payment) {
        return new ResponseEntity<>(ResponseDto.forSuccess(paymentService.addPayments(payment)), HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ResponseDto<List<PaymentDto>>> getPayments(@PathVariable("orderId") Long orderId) {
        return new ResponseEntity<>(ResponseDto.forSuccess(paymentService.getPayments(orderId)), HttpStatus.OK);
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<ResponseDto<Void>> processRefund(@PathVariable("orderId") Long orderId) {
        return new ResponseEntity<>(ResponseDto.forSuccess(paymentService.processRefund(orderId)), HttpStatus.OK);
    }

}
