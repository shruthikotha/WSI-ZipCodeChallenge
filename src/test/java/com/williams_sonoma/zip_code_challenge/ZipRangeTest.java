package com.williams_sonoma.zip_code_challenge;

import com.williams_sonoma.zip_code_challenge.exception.ZipException;
import com.williams_sonoma.zip_code_challenge.model.ZipRange;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;

public class ZipRangeTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void testConstructorLowerLow() throws ZipException {
    thrown.expect(ZipException.class);
    thrown.expectMessage(is(equalTo("-0025 is not a valid 5-digit zip code.")));

    new ZipRange(-25, 0);
  }

  @Test
  public void testConstructorLowerHigh() throws ZipException {
    thrown.expect(ZipException.class);
    thrown.expectMessage(is(equalTo("700000 is not a valid 5-digit zip code.")));

    new ZipRange(700000, 0);
  }

  @Test
  public void testConstructorUpperLow() throws ZipException {
    thrown.expect(ZipException.class);
    thrown.expectMessage(is(equalTo("-0025 is not a valid 5-digit zip code.")));

    new ZipRange(0, -25);
  }

  @Test
  public void testConstructorUpperHigh() throws ZipException {
    thrown.expect(ZipException.class);
    thrown.expectMessage(is(equalTo("100000 is not a valid 5-digit zip code.")));

    new ZipRange(0, 100000);
  }

  @Test
  public void testConstructorAtBounds() throws ZipException {
    ZipRange range = new ZipRange(0, 99999);

    assertThat(range.getLower(), is(equalTo(0)));
    assertThat(range.getUpper(), is(equalTo(99999)));
  }

  @Test
  public void testConstructorWithinBounds() throws ZipException {
    ZipRange range = new ZipRange(93152, 97852);

    assertThat(range.getLower(), is(equalTo(93152)));
    assertThat(range.getUpper(), is(equalTo(97852)));
  }

  @Test
  public void testConstructorUpperAndLowerReversed() throws ZipException {
    thrown.expect(ZipException.class);
    thrown.expectMessage(is(equalTo("[97852,93152] is not a valid zip range")));

    new ZipRange(97852, 93152);
  }

  @Test
  public void testSetLowerLow() throws ZipException {
    thrown.expect(ZipException.class);
    thrown.expectMessage(is(equalTo("-0001 is not a valid 5-digit zip code.")));

    ZipRange range = new ZipRange(0, 99999);
    range.setLower(-1);
  }

  @Test
  public void testSetLowerHigh() throws ZipException {
    thrown.expect(ZipException.class);
    thrown.expectMessage(is(equalTo("100000 is not a valid 5-digit zip code.")));

    ZipRange range = new ZipRange(0, 99999);
    range.setLower(100000);
  }

  @Test
  public void testSetUpperLow() throws ZipException {
    thrown.expect(ZipException.class);
    thrown.expectMessage(is(equalTo("-0001 is not a valid 5-digit zip code.")));

    ZipRange range = new ZipRange(0, 99999);
    range.setUpper(-1);
  }

  @Test
  public void testSetUpperHigh() throws ZipException {
    thrown.expect(ZipException.class);
    thrown.expectMessage(is(equalTo("100000 is not a valid 5-digit zip code.")));

    ZipRange range = new ZipRange(0, 99999);
    range.setUpper(100000);
  }

  @Test
  public void testSetAtBounds() throws ZipException {
    ZipRange range = new ZipRange(95661, 95765);
    range.setLower(0);
    range.setUpper(99999);

    assertThat(range.getLower(), is(equalTo(0)));
    assertThat(range.getUpper(), is(equalTo(99999)));
  }

  @Test
  public void testSetWithinBounds() throws ZipException {
    ZipRange range = new ZipRange(0, 99999);
    range.setLower(95661);
    range.setUpper(95765);

    assertThat(range.getLower(), is(equalTo(95661)));
    assertThat(range.getUpper(), is(equalTo(95765)));
  }

  @Test
  public void testSetLowerAndUpperReversed() throws ZipException {
    thrown.expect(ZipException.class);
    thrown.expectMessage(is(equalTo("[95765,95661] is not a valid zip range")));

    ZipRange range = new ZipRange(0, 99999);
    range.setLower(95765);
    range.setUpper(95661);
  }

  @Test
  public void testEquals() throws ZipException {
    ZipRange range1 = new ZipRange(0, 99999);
    ZipRange range2 = new ZipRange(95661, 95765);
    ZipRange range3 = new ZipRange(0, 99999);

    assertThat(range1, is(equalTo(range1)));
    assertThat(range1, is(not(equalTo(range2))));
    assertThat(range1, is(equalTo(range3)));

    assertThat(range2, is(not(equalTo(range1))));
    assertThat(range2, is(equalTo(range2)));
    assertThat(range2, is(not(equalTo(range3))));

    assertThat(range3, is(equalTo(range1)));
    assertThat(range3, is(not(equalTo(range2))));
    assertThat(range3, is(equalTo(range3)));

    assertThat(range1, is(not(equalTo(0))));
    assertThat(range1, is(not(equalTo(95661))));
    assertThat(range1, is(not(equalTo(95765))));
    assertThat(range1, is(not(equalTo(99999))));

    assertThat(range2, is(not(equalTo(0))));
    assertThat(range2, is(not(equalTo(95661))));
    assertThat(range2, is(not(equalTo(95765))));
    assertThat(range2, is(not(equalTo(99999))));

    assertThat(range3, is(not(equalTo(0))));
    assertThat(range3, is(not(equalTo(95661))));
    assertThat(range3, is(not(equalTo(95765))));
    assertThat(range3, is(not(equalTo(99999))));
  }

  @Test
  public void testHashCode() throws ZipException {
    ZipRange range1 = new ZipRange(0, 99999);
    ZipRange range2 = new ZipRange(95661, 95765);
    ZipRange range3 = new ZipRange(0, 99999);

    assertThat(range1.hashCode(), is(equalTo(range1.hashCode())));
    assertThat(range1.hashCode(), is(not(equalTo(range2.hashCode()))));
    assertThat(range1.hashCode(), is(equalTo(range3.hashCode())));

    assertThat(range2.hashCode(), is(not(equalTo(range1.hashCode()))));
    assertThat(range2.hashCode(), is(equalTo(range2.hashCode())));
    assertThat(range2.hashCode(), is(not(equalTo(range3.hashCode()))));

    assertThat(range3.hashCode(), is(equalTo(range1.hashCode())));
    assertThat(range3.hashCode(), is(not(equalTo(range2.hashCode()))));
    assertThat(range3.hashCode(), is(equalTo(range3.hashCode())));
  }

  @Test
  public void testToString() throws ZipException {
    ZipRange range1 = new ZipRange(0, 99999);
    ZipRange range2 = new ZipRange(95661, 95765);
    ZipRange range3 = new ZipRange(0, 99999);

    assertThat(range1.toString(), is(equalTo("[00000,99999]")));
    assertThat(range2.toString(), is(equalTo("[95661,95765]")));
    assertThat(range3.toString(), is(equalTo("[00000,99999]")));
  }

  @Test
  public void testCompareTo() throws ZipException {
    ZipRange range1 = new ZipRange(0, 99999);
    ZipRange range2 = new ZipRange(95661, 95765);
    ZipRange range3 = new ZipRange(0, 99999);
    ZipRange range4 = new ZipRange(10000, 11000);
    ZipRange range5 = new ZipRange(10000, 12000);
    ZipRange range6 = new ZipRange(20000, 21000);

    assertThat(range1, is(equalTo(range1)));
    assertThat(range1, is(lessThan(range2)));
    assertThat(range1, is(equalTo(range3)));
    assertThat(range1, is(lessThan(range4)));
    assertThat(range1, is(lessThan(range5)));
    assertThat(range1, is(lessThan(range6)));

    assertThat(range2, is(greaterThan(range1)));
    assertThat(range2, is(equalTo(range2)));
    assertThat(range2, is(greaterThan(range3)));
    assertThat(range2, is(greaterThan(range4)));
    assertThat(range2, is(greaterThan(range5)));
    assertThat(range2, is(greaterThan(range6)));

    assertThat(range3, is(equalTo(range1)));
    assertThat(range3, is(lessThan(range2)));
    assertThat(range3, is(equalTo(range3)));
    assertThat(range3, is(lessThan(range4)));
    assertThat(range3, is(lessThan(range5)));
    assertThat(range3, is(lessThan(range6)));

    assertThat(range4, is(greaterThan(range1)));
    assertThat(range4, is(lessThan(range2)));
    assertThat(range4, is(greaterThan(range3)));
    assertThat(range4, is(equalTo(range4)));
    assertThat(range4, is(lessThan(range5)));
    assertThat(range4, is(lessThan(range6)));

    assertThat(range5, is(greaterThan(range1)));
    assertThat(range5, is(lessThan(range2)));
    assertThat(range5, is(greaterThan(range3)));
    assertThat(range5, is(greaterThan(range4)));
    assertThat(range5, is(equalTo(range5)));
    assertThat(range5, is(lessThan(range6)));

    assertThat(range6, is(greaterThan(range1)));
    assertThat(range6, is(lessThan(range2)));
    assertThat(range6, is(greaterThan(range3)));
    assertThat(range6, is(greaterThan(range4)));
    assertThat(range6, is(greaterThan(range5)));
    assertThat(range6, is(equalTo(range6)));
  }

}
