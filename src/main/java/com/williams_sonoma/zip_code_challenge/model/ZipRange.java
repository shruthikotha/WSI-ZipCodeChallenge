package com.williams_sonoma.zip_code_challenge.model;

import com.williams_sonoma.zip_code_challenge.exception.ZipException;

import java.util.Comparator;
import java.util.Objects;

/**
 * This class represents a 5-digit zip code range with
 * both upper and lower bounds.
 */
public class ZipRange implements Comparable<ZipRange> {

  private static final Comparator<ZipRange> comparator =
      Comparator
          .comparingInt(ZipRange::getLower)
          .thenComparingInt(ZipRange::getUpper);

  private int lower;
  private int upper;

  /**
   * Creates a zip code range with the specified bounds.
   *
   * @param lower the lower bound of the zip code range
   * @param upper the upper bound of the zip code range
   * @throws ZipException if the values provided are not a valid 5-digit zip code,
   *                      or if the bounds are reversed
   */
  public ZipRange(int lower, int upper) throws ZipException {
    validate(lower, upper);
    this.lower = lower;
    this.upper = upper;
  }

  /**
   * Return the lower bound of this zip code range.
   *
   * @return the lower bound of this zip code range
   */
  public int getLower() {
    return lower;
  }

  /**
   * Set the lower bound of this zip code range.
   *
   * @param lower the lower bound of this zip code range
   * @throws ZipException if the value provided is not a valid zip code,
   *                      or if the bounds are reversed
   */
  public void setLower(int lower) throws ZipException {
    validate(lower, upper);
    this.lower = lower;
  }

  /**
   * Return the upper bound of this zip code range.
   *
   * @return the upper bound of this zip code range
   */
  public int getUpper() {
    return upper;
  }

  /**
   * Set the upper bound of this zip code range.
   *
   * @param upper the upper bound of this zip code range
   * @throws ZipException if the value provided is not a valid zip code,
   *                      or if the bounds are reversed
   */
  public void setUpper(int upper) throws ZipException {
    validate(lower, upper);
    this.upper = upper;
  }

  /**
   * Validate that the provided values represent is a valid 5-digit zip code
   * range whose bounds fall between 00000 and 99999 and are ordered correctly
   * (i.e. the lower bound cannot be greater than the upper bound).
   *
   * @param lower the lower bound of the zip code range to be validated
   * @param upper the upper bound of the zip code range to be validated
   * @throws ZipException if the value provided is not a valid zip code,
   *                      or if the bounds are reversed
   */
  private void validate(int lower, int upper) throws ZipException {
    validate(lower);
    validate(upper);
    if (lower > upper) {
      throw new ZipException(String.format("[%1$05d,%2$05d] is not a valid zip range", lower, upper));
    }
  }

  /**
   * Validate that the provided value is a valid 5-digit zip code that
   * falls between 00000 and 99999.
   *
   * @param zip the zip code to be validated
   * @throws ZipException if the value provided is not a valid zip code
   */
  private void validate(int zip) throws ZipException {
    if (zip < 0 || zip > 99999) {
      throw new ZipException(String.format("%1$05d is not a valid 5-digit zip code.", zip));
    }
  }

  /**
   * Indicates whether some other object is "equal to" this zip code range.
   *
   * @param obj the reference object with which to compare
   * @return {@code true} if this object is the same as the reference object, {@code false} if not
   * @see Object#equals(Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof ZipRange)) return false;

    ZipRange that = (ZipRange) obj;
    return this.compareTo(that) == 0;
  }

  /**
   * Returns a hash code value for this zip code range based on its upper and lower bounds.
   *
   * @return a hash code value for this object.
   * @see Object#hashCode()
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.getLower(), this.getUpper());
  }

  /**
   * Returns a string representation of this zip code range.
   *
   * @return a string representation of the range in the form {@code [lower,upper]}
   * @see Object#toString()
   */
  @Override
  public String toString() {
    return String.format("[%1$05d,%2$05d]", this.getLower(), this.getUpper());
  }

  /**
   * Compares this zip code range with the specified range for order. Ordering
   * is determined first by the lower bound, then the upper bound.
   *
   * @param that the range to be compared with
   * @return a negative integer, zero, or a positive integer when this range
   * is less-than, equal-to, or greater-than (respectively) the specified range
   * @see Comparable#compareTo(Object)
   */
  @Override
  public int compareTo(ZipRange that) {
    return comparator.compare(this, that);
  }

}
