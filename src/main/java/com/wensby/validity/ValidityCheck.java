package com.wensby.validity;

public interface ValidityCheck<T> {

  String getCriteriaDescription();

  boolean check(T candidate);
}