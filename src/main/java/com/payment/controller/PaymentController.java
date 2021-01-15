package com.payment.controller;

import com.payment.model.common.ResponseDto;
import com.payment.model.dto.PaymentDto;
import com.payment.service.PaymentService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/payment", consumes = "application/json")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "/v1/payment", tags = "Payment")
public class PaymentController {

    private final PaymentService paymentService;

    // TODO: Validation

    @ApiOperation(value = "Add New Payment")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Payment processed successfully"),
            @ApiResponse(code = 400, message = "Invalid Input"),
            @ApiResponse(code = 500, message = "Server Error")
    })
    @PostMapping
    public ResponseEntity<ResponseDto<List<PaymentDto>>> addPayments(
            @ApiParam(value = "List of Payment Dto", required = true)
            @RequestBody List<PaymentDto> payment) {
        return new ResponseEntity<>(ResponseDto.forSuccess(paymentService.addPayments(payment)), HttpStatus.OK);
    }

    @ApiOperation(value = "Get Payment Information")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returned List of payments successfully"),
            @ApiResponse(code = 400, message = "Invalid Input"),
            @ApiResponse(code = 500, message = "Server Error")
    })
    @GetMapping("/{orderId}")
    public ResponseEntity<ResponseDto<List<PaymentDto>>> getPayments(@PathVariable("orderId") Long orderId) {
        return new ResponseEntity<>(ResponseDto.forSuccess(paymentService.getPayments(orderId)), HttpStatus.OK);
    }

    @ApiOperation(value = "Process Refund")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Payment refunded successfully"),
            @ApiResponse(code = 400, message = "Invalid Input"),
            @ApiResponse(code = 500, message = "Server Error")
    })
    @PutMapping("/{orderId}")
    public ResponseEntity<ResponseDto<Void>> processRefund(@PathVariable("orderId") Long orderId) {
        return new ResponseEntity<>(ResponseDto.forSuccess(paymentService.processRefund(orderId)), HttpStatus.OK);
    }

}
