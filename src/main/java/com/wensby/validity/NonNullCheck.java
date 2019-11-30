package com.wensby.validity;

public class NonNullCheck implements ValidityCheck<String> {

  @Override
  public String getCriteriaDescription() {
    return "not null";
  }

  @Override
  public boolean check(String candidate) {
    return candidate != null;
  }
}
