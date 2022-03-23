package {api.packagePath};

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import {packagePath}.model.Result;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Result handler(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        return Result.error(fieldError.getField() + fieldError.getDefaultMessage());
    }

    @ExceptionHandler({Exception.class})
    public Result handler(Exception ex) {
        log.error("出现未知异常：", ex);
        return Result.error(500, ex.getMessage());
    }
}
