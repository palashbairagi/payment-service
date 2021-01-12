package com.payment.exception;

import com.payment.enums.ErrorCode;
import com.payment.model.common.ErrorDto;
import com.payment.model.common.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

import static com.payment.enums.ErrorCode.REQUEST_MALFORMED;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PaymentExceptionHandler {

    private final ResponseResolver responseResolver;

    @ExceptionHandler(value = PaymentException.class)
    public ResponseEntity handlePaymentException(PaymentException paymentException) {
        log.error("{} exception thrown with message: {}", paymentException.getClass().getSimpleName(), paymentException.getMessage());
        ResponseEntity<ResponseDto<?>> responseEntity = responseResolver.resolve(paymentException);
        return new ResponseEntity(responseEntity.getBody(), responseEntity.getStatusCode());
    }

    // TODO: Handle other exceptions

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity exception(Exception exception) {
        log.error("{} exception thrown with message: {}", exception.getClass().getSimpleName(), exception.getMessage());
        ErrorDto errorDto = new ErrorDto(ErrorCode.SERVER_ERROR);
        return new ResponseEntity(ResponseDto.forError(errorDto), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
