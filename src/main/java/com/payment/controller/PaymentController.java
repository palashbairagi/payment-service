package com.payment.controller;

import com.payment.model.common.ResponseDto;
import com.payment.model.dto.PaymentDto;
import com.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/payment", consumes = "application/json")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PaymentController {

    private final PaymentService paymentService;

    // TODO: Validation and add price column

    @PostMapping
    public ResponseEntity<ResponseDto<PaymentDto>> addPayment(@RequestBody PaymentDto payment) {
        return new ResponseEntity<>(ResponseDto.forSuccess(paymentService.addPayment(payment)), HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ResponseDto<PaymentDto>> getPayment(@PathVariable("orderId") Long orderId) {
        return new ResponseEntity<>(ResponseDto.forSuccess(paymentService.getPayment(orderId)), HttpStatus.OK);
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<ResponseDto<Void>> processRefund(@PathVariable("orderId") Long orderId) {
        return new ResponseEntity<>(ResponseDto.forSuccess(paymentService.processRefund(orderId)), HttpStatus.OK);
    }

}
