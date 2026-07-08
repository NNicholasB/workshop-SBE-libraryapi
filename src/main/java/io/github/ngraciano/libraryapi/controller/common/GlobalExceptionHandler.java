package io.github.ngraciano.libraryapi.controller.common;

import io.github.ngraciano.libraryapi.controller.dto.ErrorField;
import io.github.ngraciano.libraryapi.controller.dto.ErrorResponse;
import io.github.ngraciano.libraryapi.exceptions.DuplicateEntryException;
import io.github.ngraciano.libraryapi.exceptions.OperationNotPermitted;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<ErrorField> listErrors = fieldErrors.stream().map(fe -> new ErrorField(fe.getField(), fe.getDefaultMessage())).collect(Collectors.toList());

        return new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Validation Error",listErrors);
    }

    @ExceptionHandler(DuplicateEntryException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleDuplicatedEntryException(DuplicateEntryException e){
       return ErrorResponse.conflict(e.getMessage());
    }

    @ExceptionHandler(OperationNotPermitted.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleOperationNotPermitted(OperationNotPermitted e){
        return ErrorResponse.responseDefault(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleErrorsNotTratament(RuntimeException e){
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),"unexpected error",List.of());
    }
}
