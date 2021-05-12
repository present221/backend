package pdm.clothshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class clothShopExceptionAdvice {

    @ExceptionHandler(clothShopException.class)
    public ResponseEntity<Object> handleExceptions(clothShopException exception, WebRequest webRequest) {
        ExceptionResponse response =
                new ExceptionResponse(exception.getErrorCode(),exception.getMessage(), LocalDateTime.now());
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        return entity;
    }

}
