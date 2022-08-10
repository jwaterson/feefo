package numberstatistics;

import org.apache.commons.math3.stat.StatUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A class whose methods provide statistics about given arrays
 *
 * @author Josh Waterson
 */
public class ArrayStats {

    /**
     * Outputs basic statistics (median, mean, mode and range) about
     * the input array to the console.
     *
     * @param arr input array
     */
    public void getStats(int[] arr) {
        if (Objects.isNull(arr) || arr.length == 0) {
            throw new IllegalArgumentException("Cannot get stats for empty or null array");
        }

        double[] doubleArr = Arrays.stream(arr).asDoubleStream().toArray();

        double median = StatUtils.percentile(doubleArr, 50);
        double mean = StatUtils.mean(doubleArr);
        int[] mode = Arrays.stream(StatUtils.mode(doubleArr)).mapToInt(d -> (int) d).toArray();
        long range = (long) (StatUtils.max(doubleArr) - StatUtils.min(doubleArr));

        System.out.printf("Median: %s\nMean: %s\nMode: %s\nRange: %d\n",
                formatDouble(median),
                formatDouble(mean),
                IntStream.of(mode)
                        .mapToObj(Integer::toString)
                        .collect(Collectors.joining(", ")),
                range);
    }

    /**
     * Helper method to output formatted representation of double value.
     *
     * @param d     double to be formatted
     * @return      formatted String of input double value
     */
    private String formatDouble(double d) {
        return String.format(d % 1.0 != 0 ? "%s" : "%.0f", d);
    }

    /**
     * Helper method for use in demonstrating sample code in main
     *
     * @return      potentially large array
     */
    private static int[] generateSomeHugeArray() {
        int[] arr1 = new int[1000]; // limited to 10000 for convenience only
        for (int i = 0; i < arr1.length; i++) {
//            arr1[i] = new Random().nextInt() * (i % 2 == 0 ? 1 : -1);
            arr1[i] = new Random().nextInt(-1, 2);
        }
        return arr1;
    }

    public static void main(String[] args) {
        int[] myBigArray = generateSomeHugeArray();
        System.out.println(Arrays.toString(myBigArray));
        ArrayStats arrayManipulator = new ArrayStats();
        arrayManipulator.getStats(myBigArray);

    }
}
