package com.payment.exception;

import com.payment.model.common.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PaymentExceptionHandler {

    private final ResponseResolver responseResolver;

    @ExceptionHandler(value = PaymentException.class)
    public ResponseEntity handlePaymentException(PaymentException paymentException) {

        ResponseEntity<ResponseDto<?>> responseEntity = responseResolver.resolve(paymentException);
        return new ResponseEntity(responseEntity.getBody(), responseEntity.getStatusCode());

    }

    // TODO: Handle other exceptions

}
