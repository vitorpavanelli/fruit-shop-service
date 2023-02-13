package com.devbox.fruitshop.exception.model;

import static java.time.ZoneOffset.UTC;

import java.net.URI;
import java.time.OffsetDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public final class ProblemDetailBuilder {
  private ProblemDetail problemDetail;

  public ProblemDetailBuilder(final HttpStatus httpStatus) {
    problemDetail = ProblemDetail.forStatus(httpStatus);
  }

  public ProblemDetailBuilder withTitle(final String title) {
    problemDetail.setTitle(title);

    return this;
  }

  public ProblemDetailBuilder withType(final String type) {
    problemDetail.setType(URI.create(type));

    return this;
  }

  public ProblemDetailBuilder withDetail(final String detail) {
    problemDetail.setDetail(detail);

    return this;
  }

  public ProblemDetailBuilder withProperty(final String property, final Object object) {
    problemDetail.setProperty(property, object);

    return this;
  }

  public ProblemDetailBuilder withDefaultInfo() {
    problemDetail.setProperty("errorCategory", "Generic");
    problemDetail.setProperty("timestamp", OffsetDateTime.now(UTC));

    return this;
  }

  public ProblemDetail build() {
    return problemDetail;
  }
}
