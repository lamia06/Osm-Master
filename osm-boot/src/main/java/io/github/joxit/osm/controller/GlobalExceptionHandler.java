package io.github.joxit.osm.controller;

import io.github.joxit.osm.model.Tile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * C'est ici que vous devez gérer les exceptions qui sont levés. Utilisation de ControllerAdvice et ExceptionHandler.
 */
@ControllerAdvice

public class GlobalExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);


  @ExceptionHandler(Exception.class)
  public ResponseEntity<JSONError> handledException(Exception e) {
    LOGGER.warn(e.getMessage());
    return new ResponseEntity<>(new JSONError(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
  }

  private static class JSONError {
    private int status;
    private String message;

    private JSONError(HttpStatus httpStatus, String message) {
      this.status = httpStatus.value();
      this.message = message;
    }

    public int getStatus() {
      return status;
    }

    public String getMessage() {
      return message;
    }
  }

}
