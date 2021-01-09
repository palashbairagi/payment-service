package com.payment.exception;

import com.payment.model.common.ErrorDto;
import com.payment.model.common.ErrorMappings;
import com.payment.model.common.HttpErrorResponse;
import com.payment.model.common.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Component
@Slf4j
public class ResponseResolver {

    private static final String ERROR_MAPPINGS_YAML = "error-mappings.yaml";

    private ErrorMappings errorMappings;

    public ResponseResolver() {
        loadErrorConfig();
    }

    private void loadErrorConfig() {
        InputStream inputStream = null;
        Yaml yaml = new Yaml(new Constructor(ErrorMappings.class));
        try {
            inputStream = new ClassPathResource(ERROR_MAPPINGS_YAML).getInputStream();
        } catch (IOException e) {
            log.warn("Unable to find {} ", ERROR_MAPPINGS_YAML);
            // TODO : Handle RTE in ExceptionHandler
            throw new RuntimeException("Unable to find error mapping", e);
        }

        errorMappings = yaml.load(inputStream);
    }

    public ResponseEntity<ResponseDto<?>> resolve(PaymentException exception) {

        HttpErrorResponse httpErrorResponse = populateErrorResponse(exception);
        ErrorDto error = httpErrorResponse.getErrorDto();

        if (log.isDebugEnabled()) {
            log.debug("Resolved Error Response: {}", httpErrorResponse);
        }

        return new ResponseEntity<>(ResponseDto.forError(error), httpErrorResponse.getHttpStatus());
    }

    private HttpErrorResponse populateErrorResponse(PaymentException exception) {

        ErrorDto errorDto = exception.getError();

        if (errorDto == null) {
            // TODO : Handle exception in Handler
            throw new IllegalArgumentException("Can not process empty exception");
        }

        Optional<ErrorMappings.ErrorMapping> optionalErrorResponse = errorMappings.getErrorMappings().stream()
                .filter(errorResponse -> errorResponse.getKey().equals(errorDto.getErrorCode().getKey()))
                .findAny();

        return optionalErrorResponse.map(errorMapping -> new HttpErrorResponse(HttpStatus.valueOf(errorMapping.getHttpStatus()),
                new ErrorDto(errorDto.getErrorCode(), errorMapping.getCode(), errorMapping.getMessage())))
                .orElseGet(() -> new HttpErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                new ErrorDto(errorDto.getErrorCode(), null, null)));
    }
}
