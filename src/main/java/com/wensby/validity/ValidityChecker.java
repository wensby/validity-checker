package com.wensby.validity;

import java.util.List;

public class ValidityChecker<T> {

  private final List<ValidityCheck<T>> validityChecks;

  public ValidityChecker(List<ValidityCheck<T>> validityChecks) {
    this.validityChecks = List.copyOf(validityChecks);
  }

  public void validate(final T candidate) {
    var failingCheck = validityChecks.stream()
        .filter(validityCheck -> !validityCheck.check(candidate))
        .findFirst();
    if (failingCheck.isPresent()) {
      var criteriaDescription = failingCheck.get().getCriteriaDescription();
      throw new FailedValidityCheckException(candidate, criteriaDescription);
    }
  }
}