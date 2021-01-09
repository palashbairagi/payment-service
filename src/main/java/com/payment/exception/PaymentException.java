package com.payment.exception;

import com.payment.enums.ErrorCode;
import com.payment.model.common.ErrorDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentException extends RuntimeException {

    private ErrorDto error;

    public PaymentException(ErrorCode errorCode) {
        this.error = new ErrorDto(errorCode);
    }

}
