package com.informatorio.informatorio2021.controller;

import com.informatorio.informatorio2021.dto.ApiErrorDTO;
import com.informatorio.informatorio2021.exception.MethodArgumentNotValid;
import com.informatorio.informatorio2021.exception.ParamNotFound;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = {ParamNotFound.class})
    protected ResponseEntity<Object> handleParamNotFound(RuntimeException ex, WebRequest req){
        ApiErrorDTO errorDTO = new ApiErrorDTO(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                Arrays.asList("Parametro no encontrado")
        );
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST, req);
    }

    @ExceptionHandler(value = {MethodArgumentNotValid.class})
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest req){
        List<String> errores = new ArrayList<>();
        for (FieldError fieldError: ex.getBindingResult().getFieldErrors()){
            errores.add(fieldError.getField() + ":" + fieldError.getDefaultMessage());

        }
        for (ObjectError objectError : ex.getBindingResult().getGlobalErrors()){
            errores.add(objectError.getObjectName() + ":" + objectError.getDefaultMessage());
        }
        ApiErrorDTO apiErrorDTO = new ApiErrorDTO(
                HttpStatus.BAD_REQUEST,
                ex.getLocalizedMessage(),
                errores);
        return handleExceptionInternal(ex, apiErrorDTO, headers, apiErrorDTO.getStatus(), req);
    }

    //TODO: CONTINUAR DESDE AQUI 27/11 - 13:01
    @ExceptionHandler(value
            = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
