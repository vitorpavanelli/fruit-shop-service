package com.devbox.fruitshop.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.devbox.fruitshop.exception.model.ProblemDetailBuilder;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

public class ProductNotFoundException extends ErrorResponseException {
  public ProductNotFoundException(final Long id) {
    super(NOT_FOUND, getProblemDetail(id), null);
  }

  private static ProblemDetail getProblemDetail(final Long id) {
    return new ProblemDetailBuilder(NOT_FOUND)
        .withDefaultInfo()
        .withTitle("Product not found")
        .withType("http://localhost:8080/errors/not-foundt")
        .withDetail("Product with id %s not found".formatted(id))
        .build();
  }
}
