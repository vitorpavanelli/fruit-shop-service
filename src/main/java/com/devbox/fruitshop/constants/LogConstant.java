package com.devbox.fruitshop.constants;

public final class LogConstant {
  private LogConstant() {}

  public static final String PRODUCT = "product";
  public static final String ORDER = "order";

  public static final class Service {
    private Service() {}

    public static final String INFO_CREATING = "Creating {}";
    public static final String DEBUG_CREATING = "Creating {}: {}";
    public static final String INFO_UPDATING = "Updating {} for id {}";
    public static final String DEBUG_UPDATING = "Updating {} for id {}: {}";
    public static final String INFO_DELETING = "Deleting {} for id {}";
  }

  public static final class GlobalExceptionHandler {
    private GlobalExceptionHandler() {}

    public static final String INFO_NOT_FOUND = "Not found";
    public static final String ERROR_VALIDATION_ERROR = "Validation error";
    public static final String ERROR_MESSAGE_NOT_READABLE = "Message not readable";
  }
}
