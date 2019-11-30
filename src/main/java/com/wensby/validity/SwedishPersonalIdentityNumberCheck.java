package com.wensby.validity;

import static java.util.Objects.requireNonNull;

public class SwedishPersonalIdentityNumberCheck implements ValidityCheck<String> {

  private final LuhnAlgorithm luhnAlgorithm;

  public SwedishPersonalIdentityNumberCheck(LuhnAlgorithm luhnAlgorithm) {
    this.luhnAlgorithm = requireNonNull(luhnAlgorithm);
  }

  @Override
  public String getCriteriaDescription() {
    return "valid swedish personal number";
  }

  public boolean check(String candidate) {
    if (candidate == null || !candidate.matches("^[0-9]{6,8}[+-]?[0-9]{4}$")) {
      return false;
    }
    var onlyDigits = candidate.replaceAll("[^0-9]", "");
    var shortForm = onlyDigits.substring(onlyDigits.length() - 10);
    return luhnAlgorithm.isCheckDigitValid(shortForm);
  }
}