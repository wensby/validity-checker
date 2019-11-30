package com.wensby.validity;

public class FailedValidityCheckException extends RuntimeException {

  public FailedValidityCheckException(Object candidate, String criteriaDescription) {
    super("Candidate '" + candidate + "' did not fulfill criteria: " + criteriaDescription);
  }
}
