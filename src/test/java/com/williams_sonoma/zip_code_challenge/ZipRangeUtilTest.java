package com.williams_sonoma.zip_code_challenge;

import com.williams_sonoma.zip_code_challenge.exception.ZipException;
import com.williams_sonoma.zip_code_challenge.model.ZipRange;
import com.williams_sonoma.zip_code_challenge.Util.ZipRangeUtil;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasItems;

public class ZipRangeUtilTest {

  private static final ZipRangeUtil zipUtils = ZipRangeUtil.instance;

  @Test
  public void testoptimizeRangesExample1() throws ZipException {
    ZipRange[] input = new ZipRange[] {new ZipRange(94133, 94133), new ZipRange(94200, 94299), new ZipRange(94600, 94699)};
    ZipRange[] output = new ZipRange[] {new ZipRange(94133, 94133), new ZipRange(94200, 94299), new ZipRange(94600, 94699)};

    assertThat(zipUtils.optimizeRanges(input), hasItems(output));
  }

  @Test
  public void testoptimizeRangesExample2() throws ZipException {
    ZipRange[] input = new ZipRange[] {new ZipRange(94133, 94133), new ZipRange(94200, 94299), new ZipRange(94226, 94399)};
    ZipRange[] output = new ZipRange[] {new ZipRange(94133, 94133), new ZipRange(94200, 94399)};

    assertThat(zipUtils.optimizeRanges(input), hasItems(output));
  }

  @Test
  public void testoptimizeRangesExample3() throws ZipException {
    ZipRange[] input = new ZipRange[] {new ZipRange(94133, 94133), new ZipRange(94200, 94299), new ZipRange(94226, 94256)};
    ZipRange[] output = new ZipRange[] {new ZipRange(94133, 94133), new ZipRange(94200, 94299)};

    assertThat(zipUtils.optimizeRanges(input), hasItems(output));
  }
  @Test
  public void testoptimizeRangesExample4() throws ZipException {
    ZipRange[] input = new ZipRange[] { new ZipRange(94188, 94299), new ZipRange(94226, 94316),new ZipRange(94133, 94133)};
    ZipRange[] output = new ZipRange[] {new ZipRange(94133, 94133), new ZipRange(94188, 94316)};

    assertThat(zipUtils.optimizeRanges(input), hasItems(output));
  }

}
