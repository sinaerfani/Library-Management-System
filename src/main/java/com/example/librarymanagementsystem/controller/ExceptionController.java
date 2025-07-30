package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.dto.ValidationDto;
import com.example.librarymanagementsystem.exception.RuleException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionController {
    private MessageSource messageSource;

    public ExceptionController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    @ExceptionHandler(RuleException.class)
    public ResponseEntity<ValidationDto> runTimeExceptionHandler(RuleException ruleException) {
        return ResponseEntity.status(
                HttpStatus.CONFLICT
        ).body(new ValidationDto(ruleException.getMessage()
                , messageSource.getMessage(ruleException.getMessage(), null,
                LocaleContextHolder.getLocale())
                , HttpStatus.CONFLICT.value()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ValidationDto> runTimeExceptionHandler(RuntimeException runtimeException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ValidationDto("exception.error"
                        , messageSource.getMessage("exception.error", null,
                        LocaleContextHolder.getLocale()), HttpStatus.CONFLICT.value()));

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationDto>> runTimeExceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<FieldError> allErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();
        List<ValidationDto> validationDtos = allErrors.stream().map(error -> new ValidationDto(error.getDefaultMessage(),
                        messageSource.getMessage(error.getDefaultMessage(), null,
                                LocaleContextHolder.getLocale()), HttpStatus.CONFLICT.value()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(validationDtos);
    }
}
