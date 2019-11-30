package com.wensby.validity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidityCheckerTest {

  private ValidityChecker<String> checker;

  @BeforeEach
  void setUp() {
    checker = new ValidityCheckerBuilder<String>()
        .add(new NonNullCheck())
        .add(new SwedishPersonalIdentityNumberCheck(new LuhnAlgorithm()))
        .build();
  }

  @Test
  void throwsInvalidException_whenPersonalNumberInvalid() {
    assertThrows(FailedValidityCheckException.class, () -> checker.validate(null));
    assertThrows(FailedValidityCheckException.class, () -> checker.validate("a"));
    assertThrows(FailedValidityCheckException.class, () -> checker.validate("7607280795"));
    assertThrows(FailedValidityCheckException.class, () -> checker.validate("760728.0794"));
    assertThrows(FailedValidityCheckException.class, () -> checker.validate("7607280-794"));
    assertThrows(FailedValidityCheckException.class, () -> checker.validate("76-0728-0794"));
  }

  @Test
  void passesValidPersonalNumbers() {
    checker.validate("7607280794");
    checker.validate("5806152632");
    checker.validate("760728-0794");
    checker.validate("580615-2632");
    checker.validate("197607280794");
    checker.validate("195806152632");
    checker.validate("19760728-0794");
    checker.validate("19580615-2632");
  }
}