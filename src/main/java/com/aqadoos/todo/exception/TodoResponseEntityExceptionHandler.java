package com.aqadoos.todo.exception;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class TodoResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception e, WebRequest request) {
        TodoExceptionResponse todoExceptionResponse =
                new TodoExceptionResponse(new Date(), e.getMessage(), request.getDescription(false));
        ResponseEntity<Object> responseEntity =
                new ResponseEntity<Object>(todoExceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }

    @ExceptionHandler(TodoItemNotFoundException.class)
    public ResponseEntity<Object> handleTodoItemNotFoundException(Exception e, WebRequest request) {
        TodoExceptionResponse TodoExceptionResponse =
                new TodoExceptionResponse(new Date(), e.getMessage(), request.getDescription(false));
        ResponseEntity<Object> responseEntity =
                new ResponseEntity<Object>(TodoExceptionResponse, HttpStatus.NOT_FOUND);
        return responseEntity;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        TodoExceptionResponse TodoExceptionResponse =
                new TodoExceptionResponse(
                        new Date(),
                        StringUtils.collectionToCommaDelimitedString(ex.getBindingResult().getAllErrors()),
                        ex.getMessage());
        ResponseEntity<Object> responseEntity =
                new ResponseEntity<>(TodoExceptionResponse, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(
            TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        TodoExceptionResponse TodoExceptionResponse =
                new TodoExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        ResponseEntity<Object> responseEntity =
                new ResponseEntity<>(TodoExceptionResponse, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }
}