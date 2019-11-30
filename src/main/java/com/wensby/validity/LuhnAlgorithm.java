package com.wensby.validity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class LuhnAlgorithm {

  public int calculateCheckDigit(String number) {
    validateOnlyDigits(number);
    var sum = luhnDigitSum(number, 2);
    return sum * 9 % 10;
  }

  public boolean isCheckDigitValid(String number) {
    validateOnlyDigits(number);
    var sum = luhnDigitSum(number, 1);
    return sum % 10 == 0;
  }

  private void validateOnlyDigits(String number) {
    if (number == null || !number.matches("^[0-9]+$")) {
      throw new IllegalArgumentException("Expected only digits, got: " + number);
    }
  }

  private int luhnDigitSum(String number, int startingFactor) {
    var digits = getDigits(number);
    var digitsReversed = reversed(digits);
    var sum = 0;
    for (int i = 0; i < digitsReversed.size(); i++) {
      var factor = (startingFactor - 1 + i) % 2 + 1;
      var product = digitsReversed.get(i) * factor;
      sum += product < 10 ? product : sum(getDigits(product));
    }
    return sum;
  }

  private List<Integer> getDigits(String number) {
    return number.chars()
        .map(Character::getNumericValue)
        .boxed()
        .collect(toList());
  }

  private List<Integer> reversed(List<Integer> digits) {
    var clone = new ArrayList<>(digits);
    Collections.reverse(clone);
    return clone;
  }

  private int sum(List<Integer> numbers) {
    return numbers.stream().mapToInt(Integer::intValue).sum();
  }

  private List<Integer> getDigits(int number) {
    return getDigits(String.valueOf(number));
  }
}
