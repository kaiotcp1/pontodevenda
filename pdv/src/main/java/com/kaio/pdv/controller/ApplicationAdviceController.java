package com.kaio.pdv.controller;

import com.kaio.pdv.dto.ResponseDTO;
import com.kaio.pdv.exception.InvalidOperationException;
import com.kaio.pdv.exception.NoItemException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApplicationAdviceController {

    @ExceptionHandler(NoItemException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDTO handlerNoItemException(NoItemException exception) {
        String messageError = exception.getMessage();
        return new ResponseDTO(messageError);
    }

    @ExceptionHandler(InvalidOperationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDTO handlerInvalidOperationException(InvalidOperationException exception) {
        String messageError = exception.getMessage();
        return new ResponseDTO(messageError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDTO handlerValidationException(MethodArgumentNotValidException exception) {
        List<String> erros = new ArrayList<>();

        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            erros.add(errorMessage);
        });
        return new ResponseDTO(erros);
    }

}
