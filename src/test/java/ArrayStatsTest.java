import numberstatistics.ArrayStats;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;


class ArrayStatsTest {

    private final PrintStream ogOutStream = System.out;
    private ByteArrayOutputStream newOutStream;
    private ArrayStats arrayStats;
    private int[] arr;

    @BeforeEach
    void setUp() {
        newOutStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOutStream));
        arrayStats = new ArrayStats();
    }

    @AfterEach
    void tearDown() {
        arr = null;
        System.setOut(ogOutStream);
        newOutStream = null;
        arrayStats = null;
    }

    /*

    Basic tests ---------------------------------------------------------------------------------------------

    */
    @Test
    void getStatsBasicInputTest1() {
        arr = IntStream.range(0, 11).toArray();
        arrayStats.getStats(arr);
        assertEquals("Median: 5\nMean: 5\nMode: 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10\nRange: 10\n",
                newOutStream.toString());
    }

    @Test
    void getStatsBasicInputTest2() {
        arr = IntStream.range(1, 11).toArray();
        arrayStats.getStats(arr);
        assertEquals("Median: 5.5\nMean: 5.5\nMode: 1, 2, 3, 4, 5, 6, 7, 8, 9, 10\nRange: 9\n",
                newOutStream.toString());
    }

    @Test
    void getStatsBasicInputTest3() {
        arr = IntStream.range(0, 2).toArray();
        arrayStats.getStats(arr);
        assertEquals("Median: 0.5\nMean: 0.5\nMode: 0, 1\nRange: 1\n",
                newOutStream.toString());
    }

    @Test
    void getStatsBasicInputTest4() {
        arr = new int[]{1, 1, 1, 1, 1, 0, 0, 0, 0, 0};
        arrayStats.getStats(arr);
        assertEquals("Median: 0.5\nMean: 0.5\nMode: 0, 1\nRange: 1\n",
                newOutStream.toString());
    }

    @Test
    void getStatsBasicInputTest5() {
        arr = new int[]{5, 4, 4, 3, 3, 3, 2, 2, 2, 2, 1, 1, 1, 1, 1};
        arrayStats.getStats(arr);
        assertEquals("Median: 2\nMean: 2.333333333333333\nMode: 1\nRange: 4\n",
                newOutStream.toString());
    }

    @Test
    void getStatsBasicInputTest6() {
        arr = new int[]{0};
        arrayStats.getStats(arr);
        assertEquals("Median: 0\nMean: 0\nMode: 0\nRange: 0\n",
                newOutStream.toString());
    }

    /*

    Edge case tests ---------------------------------------------------------------------------------------------

    */
    @Test
    void getStatsEdgeCaseTest1() {
        // empty array
        arr = new int[]{};
        assertThrows(IllegalArgumentException.class,
                () -> arrayStats.getStats(arr));
    }

    @Test
    void getStatsEdgeCaseTest2() {
        // moderately large array, large ints
        arr = ArrayStatsReference.largeArrayLimitedValues;
        arrayStats.getStats(arr);
        assertEquals("Median: 0\nMean: -0.016000000000000264\nMode: -1\nRange: 2\n",
                newOutStream.toString());
    }

    @Test
    void getStatsEdgeCaseTest3() {
        // very large array, limited ints
        arr = new int[100_000];
        Arrays.fill(arr, Integer.MAX_VALUE);
        arrayStats.getStats(arr);
        assertEquals("Median: 2147483647\nMean: 2147483647\nMode: 2147483647\nRange: 0\n",
                newOutStream.toString());
    }

    @Test
    void getStatsEdgeCaseTest4() {
        // value extremes
        arr = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
        arrayStats.getStats(arr);
        assertEquals("Median: -0.5\nMean: -0.5\nMode: -2147483648, 2147483647\nRange: 4294967295\n",
                newOutStream.toString());
    }

    @Test
    void getStatsEdgeCaseTest5() {
        // null input
        arr = null;
        assertThrows(IllegalArgumentException.class,
                () -> arrayStats.getStats(arr));
    }

    @Test
    void getStatsEdgeCaseTest6() {
        // moderately large array input, single mode
        arr = ArrayStatsReference.largeArray;
        arrayStats.getStats(arr);
        assertEquals("Median: 1.142239845E8\nMean: 4.950797175149695E7\nMode: 906405170\nRange: 4293595186\n",
                newOutStream.toString());
    }

}