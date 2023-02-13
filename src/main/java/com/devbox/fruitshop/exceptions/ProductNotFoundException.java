package com.devbox.fruitshop.exceptions;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.devbox.fruitshop.exceptions.model.ProblemDetailBuilder;
import java.util.List;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

public class ProductNotFoundException extends ErrorResponseException {
  public ProductNotFoundException(final Long id) {
    super(NOT_FOUND, getProblemDetail(id), null);
  }

  public ProductNotFoundException(final List<String> ids) {
    super(NOT_FOUND, getProblemDetail(ids), null);
  }

  private static ProblemDetail getProblemDetail(final Long id) {
    return new ProblemDetailBuilder(NOT_FOUND)
        .withDefaultInfo()
        .withTitle("Product not found")
        .withType("http://localhost:8080/errors/not-found")
        .withDetail("Product with id %s not found".formatted(id))
        .build();
  }

  private static ProblemDetail getProblemDetail(final List<String> ids) {
    final var formattedIds = String.join(", ", ids);
    return new ProblemDetailBuilder(NOT_FOUND)
        .withDefaultInfo()
        .withTitle("Product not found")
        .withType("http://localhost:8080/errors/not-found")
        .withDetail("Product with ids %s not found".formatted(formattedIds))
        .build();
  }
}
