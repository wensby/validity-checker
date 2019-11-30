package com.wensby.validity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LuhnAlgorithmTest {

  private LuhnAlgorithm luhnAlgorithm;

  @BeforeEach
  void setUp() {
    luhnAlgorithm = new LuhnAlgorithm();
  }

  @Test
  void isCheckDigitValid() {
    assertTrue(luhnAlgorithm.isCheckDigitValid("8112189876"));
    assertTrue(luhnAlgorithm.isCheckDigitValid("371449635398431"));
    assertTrue(luhnAlgorithm.isCheckDigitValid("7607280794"));
    assertTrue(luhnAlgorithm.isCheckDigitValid("5806152632"));
  }

  @Test
  void calculateCheckDigit() {
    assertThat(luhnAlgorithm.calculateCheckDigit("811218987"), is(6));
    assertThat(luhnAlgorithm.calculateCheckDigit("37144963539843"), is(1));
    assertThat(luhnAlgorithm.calculateCheckDigit("760728079"), is(4));
    assertThat(luhnAlgorithm.calculateCheckDigit("580615263"), is(2));
  }

  @Test
  void bruteCheckValidCheckDigits() {
    var random = new Random();
    for (int i = 0; i < 10000; i++) {
      var number = String.valueOf(random.nextInt(100000000));
      var checkDigit = luhnAlgorithm.calculateCheckDigit(number);
      var numberWithCheckDigit = number + checkDigit;
      assertTrue(luhnAlgorithm.isCheckDigitValid(numberWithCheckDigit));
    }
  }

  @Test
  void bruteCheckInvalidCheckDigits() {
    var random = new Random();
    for (int i = 0; i < 10000; i++) {
      var number = String.valueOf(random.nextInt(100000000));
      var checkDigit = luhnAlgorithm.calculateCheckDigit(number);
      var checkDigitShifted = (checkDigit + 1) % 10;
      var numberWithWrongCheckDigit = number + checkDigitShifted;
      assertFalse(luhnAlgorithm.isCheckDigitValid(numberWithWrongCheckDigit));
    }
  }
}