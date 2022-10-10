package com.superheroes.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
  SUPERHERO_NOT_FOUND(1_000, HttpStatus.NOT_FOUND, "superhero.not_found");

  private final int errorCode;
  private final HttpStatus httpCode;
  private final String message;
}
