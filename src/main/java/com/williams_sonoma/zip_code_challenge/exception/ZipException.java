package com.williams_sonoma.zip_code_challenge.exception;

/**
 * This exception is used to represent issues with zipcodes and zip
 * code ranges
 */
public class ZipException extends Exception {

  /**
   * Creates a zip code exception with the given message.
   *
   * @param message the message
   */
  public ZipException(String message) {
    super(message);
  }

}
