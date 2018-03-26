package com.williams_sonoma.zip_code_challenge.Util;

import com.williams_sonoma.zip_code_challenge.exception.ZipException;
import com.williams_sonoma.zip_code_challenge.model.ZipRange;

import java.util.*;



public class ZipRangeUtil{
    /**
     * A singleton for use when not using frameworks like spring for Dependency Injection
     */
    public static final ZipRangeUtil instance = new ZipRangeUtil();

    /**
     * A convenience method for merging zip code ranges by passing one or
     * more {@link ZipRange} objects as varargs or an array.
     *
     * @param ranges the zip code ranges to be merged
     * @return a list of merged zip code ranges
     * @throws ZipException if an error occurred while merging zip code ranges
     * @see #optimizeRanges(List)
     */
    public List<ZipRange> optimizeRanges (ZipRange... ranges) throws ZipException {
        return optimizeRanges(new LinkedList<>(Arrays.asList(ranges)));
    }

    /**
     * <p>Given a list of {@link ZipRange}, this method produces the minimum
     * number of ranges required to represent the same ranges as the input.
     * Ranges may be provided in any  order and may or may not overlap.
     * Overlapping ranges will be merged to the minimum number of ranges.</p>
     * <p>
     *
     * @param ranges the zip code ranges to be merged
     * @return the reduced list of merged zip code ranges, sorted in ascending order
     * @throws ZipException                  if an error occurred while merging zip code ranges
     * @throws UnsupportedOperationException if the list is not modifiable or does not support removal
     */
    public List<ZipRange> optimizeRanges(List<ZipRange> ranges) throws ZipException {
        // by sorting the ranges first, we can merge them in O(n log n) instead of O(n^2)
        Collections.sort(ranges);
        ZipRange range = null;
        Iterator<ZipRange> iter = ranges.iterator();
        while (iter.hasNext()) {
            if (range == null) {
           //if there was no previous range to compare with, we know it doesn't need merging so keep going
                range = iter.next();
            } else {
                ZipRange next = iter.next();
                if (range.getUpper() >= next.getLower()) {
          // if the ranges overlap, or is inclusive of existing range, merge them and remove the extraneous entry
                    range.setUpper(Math.max(range.getUpper(), next.getUpper()));
                    iter.remove();
                }
                else{
          // if the ranges did not overlap, move to the next range and keep going
                    range = next;
                }
            }
        }
        return ranges;
    }
}
