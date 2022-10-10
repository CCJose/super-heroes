package com.superheroes.handler.exception;

import com.superheroes.handler.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SuperheroesException extends RuntimeException {

  private final ErrorCode errorCode;
}
