package com.wensby.validity;

import java.util.ArrayList;
import java.util.List;

public class ValidityCheckerBuilder<T> {

  private final List<ValidityCheck<T>> validityChecks;

  public ValidityCheckerBuilder() {
    this.validityChecks = new ArrayList<>();
  }

  public ValidityCheckerBuilder<T> add(ValidityCheck<T> validityCheck) {
    validityChecks.add(validityCheck);
    return this;
  }

  public ValidityChecker<T> build() {
    return new ValidityChecker<>(validityChecks);
  }
}
