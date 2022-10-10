package com.superheroes.handler;

import com.superheroes.handler.exception.SuperheroesException;
import com.superheroes.handler.model.ApiResponseError;
import com.superheroes.util.MessageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class SuperheroesExceptionHandler {

  private final MessageHelper messageHelper;

  @ExceptionHandler({SuperheroesException.class})
  public ResponseEntity<ApiResponseError> handleSuperheroesException(
          SuperheroesException superheroesException) {
    log.error(superheroesException.getErrorCode().getMessage());
    return buildResponse(superheroesException);
  }

  private ResponseEntity<ApiResponseError> buildResponse(SuperheroesException exception) {
    return new ResponseEntity<>(
        new ApiResponseError(
            exception.getErrorCode().getHttpCode(),
            exception.getErrorCode().getErrorCode(),
            exception.getErrorCode().getMessage(),
            messageHelper.translate(exception.getErrorCode().getMessage())),
        exception.getErrorCode().getHttpCode());
  }
}
