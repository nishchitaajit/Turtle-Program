package com.sdsu.Parse;

/*
 * Prints exceptions encountered during parsing the input text file.
 */

public class ParseException extends Exception {

  public ParseException(String message) {
    super(message);
  }

  public ParseException(String message, Throwable throwable) {
    super(message, throwable);
  }
}