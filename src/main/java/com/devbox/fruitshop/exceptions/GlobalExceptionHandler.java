package com.devbox.fruitshop.exceptions;

import static com.devbox.fruitshop.constants.LogConstant.GlobalExceptionHandler.ERROR_MESSAGE_NOT_READABLE;
import static com.devbox.fruitshop.constants.LogConstant.GlobalExceptionHandler.ERROR_VALIDATION_ERROR;
import static com.devbox.fruitshop.constants.LogConstant.GlobalExceptionHandler.INFO_NOT_FOUND;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.devbox.fruitshop.exceptions.model.ProblemDetailBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public final class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
  private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(ProductNotFoundException.class)
  ProblemDetail handleNotFoundException(final ErrorResponseException exception) {
    LOGGER.info(INFO_NOT_FOUND, exception);
    return exception.getBody();
  }

  @ExceptionHandler(EmptyResultDataAccessException.class)
  ProblemDetail handleEmptyResultDataAccessException(
      final EmptyResultDataAccessException exception) {
    LOGGER.info(INFO_NOT_FOUND, exception);
    return new ProblemDetailBuilder(NOT_FOUND)
        .withDefaultInfo()
        .withTitle("Not Found")
        .withType("http://localhost:8080/errors/not-found")
        .withDetail(exception.getMessage())
        .build();
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      final MethodArgumentNotValidException ex,
      final HttpHeaders headers,
      final HttpStatusCode status,
      final WebRequest request) {
    LOGGER.error(ERROR_VALIDATION_ERROR, ex);

    final var errors =
        ex.getBindingResult().getAllErrors().stream()
            .map(
                error ->
                    error instanceof FieldError fieldError
                        ? fieldError.getField() + ": " + fieldError.getDefaultMessage()
                        : error.getObjectName() + ": " + error.getDefaultMessage())
            .toList();

    final var problemDetail =
        new ProblemDetailBuilder(BAD_REQUEST)
            .withDefaultInfo()
            .withTitle("Input constraint violation")
            .withType("http://localhost:8080/errors/bad-request")
            .withProperty("violations", errors)
            .build();

    return ResponseEntity.of(problemDetail).build();
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
      final HttpMessageNotReadableException ex,
      final HttpHeaders headers,
      final HttpStatusCode status,
      final WebRequest request) {
    LOGGER.error(ERROR_MESSAGE_NOT_READABLE, ex);

    final var problemDetail =
        new ProblemDetailBuilder(BAD_REQUEST)
            .withDefaultInfo()
            .withTitle("Input constraint violation")
            .withType("http://localhost:8080/errors/bad-request")
            .withDetail(ex.getMessage())
            .build();

    return ResponseEntity.of(problemDetail).build();
  }
}
