package com.payment.controller;

import com.payment.enums.ErrorCode;
import com.payment.exception.PaymentException;
import com.payment.model.common.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PaymentController {

    @GetMapping
    public ResponseDto getPaymentInfo() {

        throw new PaymentException(ErrorCode.REQUEST_MALFORMED);

    }

}
