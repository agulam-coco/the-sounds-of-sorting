package edu.grinnell.csc207.soundsofsorting;

import edu.grinnell.csc207.soundsofsorting.sortevents.SortEvent;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import edu.grinnell.csc207.soundsofsorting.sorts.Sorts;

public class SortsTests {

    /**
     * @param <T> the carrier type of the array
     * @param arr the array to check
     * @return true if <code>arr</code> is sorted.
     */
    public static <T extends Comparable<? super T>> boolean sorted(T[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].compareTo(arr[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    public static Integer[] makeTestArray() {
        return new Integer[]{
            312, 47, 129, 406, 75, 281, 168, 432, 90, 254,
            379, 136, 481, 219, 68, 323, 157, 493, 110, 302,
            208, 45, 191, 348, 224, 373, 104, 298, 456, 30,
            121, 361, 5, 194, 77, 155, 412, 239, 281, 34,
            98, 220, 144, 321, 176, 426, 391, 65, 349, 490,
            123, 411, 187, 235, 83, 150, 266, 93, 364, 7,
            283, 371, 13, 457, 321, 311, 209, 447, 273, 231,
            58, 168, 329, 478, 214, 125, 143, 360, 189, 470,
            248, 66, 134, 170, 21, 380, 492, 138, 109, 255,
            92, 419, 41, 259, 70, 401, 274, 179, 228, 276,
            106, 245, 296, 362, 232, 263, 448, 186, 158, 330,
            38, 284, 213, 26, 99, 405, 56, 142, 341, 20,
            351, 369, 324, 295, 82, 428, 37, 172, 198, 63,
            311, 397, 17, 393, 256, 413, 402, 15, 85, 59,
            120, 3, 19, 400, 497, 124, 350, 165, 250, 84,
            199, 164, 282, 248, 210, 152, 472, 386, 293, 289,
            133, 55, 174, 174, 407, 358, 287, 354, 275, 64,
            410, 16, 369, 319, 372, 253, 175, 79, 364, 359,
            278, 415, 112, 377, 270, 87, 356, 126, 320, 111,
            203, 67, 154, 72, 231, 142, 489, 368, 349, 381,
            300, 219, 386, 305, 190, 216, 180, 71, 101, 114,
            62, 205, 260, 135, 407, 193, 217, 292, 107, 431,
            436, 1, 206, 29, 333, 343, 316, 337, 138, 52,
            8, 321, 182, 411, 248, 437, 202, 400, 94, 367,
            386, 499, 352, 225, 195, 347, 6, 441, 51, 171,
            367, 261, 494, 299, 305, 267, 450, 400, 86, 108,
            118, 22, 197, 149, 438, 242, 376, 177, 33, 73,
            160, 57, 137, 207, 74, 439, 352, 184, 260, 151,
            44, 412, 119, 350, 248, 409, 36, 11, 487, 116,
            115, 362, 227, 327, 267, 466, 331, 203, 102, 126,
            345, 490, 396, 267, 141, 349, 239, 392, 14, 320,
            438, 438, 196, 316, 161, 248, 422, 273, 117, 246,
            225, 273, 179, 109, 434, 314, 466, 69, 313, 100,
            269, 328, 215, 91, 367, 277, 316, 437, 85, 296,
            156, 321, 144, 244, 489, 490, 88, 335, 394, 183,
            175, 355, 346, 431, 301, 317, 97, 204, 362, 218,
            449, 61, 83, 403, 432, 340, 105, 420, 292, 102,
            408, 147, 395, 2, 438, 212, 152, 254, 367, 243,
            262, 129, 198, 385, 103, 28, 463, 313, 88, 226,
            0, 266, 243, 137, 398, 4, 166, 420, 473, 195
        };
    }

    public void testSort(Function<Integer[], List<SortEvent<Integer>>> func) {
        Integer[] arr = makeTestArray();
        Integer[] temp = arr.clone();

        List<SortEvent<Integer>> events = func.apply(arr);
        Sorts.eventSort(temp, events);

        assertEquals(0, Arrays.compare(arr, temp), "Event sort failed with given events on arr");
    }

    @Test
    public void testBubbleSort() {
        testSort(Sorts::bubbleSort);
    }

    @Test
    public void testInsertionSort() {
        testSort(Sorts::insertionSort);
    }

    @Test
    public void testSelectionSort() {
        testSort(Sorts::selectionSort);
    }

    @Test
    public void testMergeSort() {
        testSort(Sorts::mergeSort);
    }

    @Test
    public void testQuickSort() {
        testSort(Sorts::quickSort);
    }

    @Test
    public void testTimSort() {
        testSort(Sorts::timSort);
    }

    // Helper method to test all sort functions on a given array
    private void testAllSorts(Integer[] original) {
        List<Function<Integer[], List<SortEvent<Integer>>>> sortFunctions = List.of(
                Sorts::bubbleSort,
                Sorts::insertionSort,
                Sorts::selectionSort,
                Sorts::mergeSort,
                Sorts::quickSort,
                Sorts::timSort
        );

        for (Function<Integer[], List<SortEvent<Integer>>> func : sortFunctions) {
            Integer[] arr = original.clone();
            Integer[] temp = arr.clone();
            List<SortEvent<Integer>> events = func.apply(arr);
            Sorts.eventSort(temp, events);

            Integer[] expected = original.clone();
            Arrays.sort(expected);
            assertTrue(sorted(arr), "Array is not sorted by sort function.");
            assertEquals(Arrays.asList(expected), Arrays.asList(temp), "Event sort did not match expected.");
        }
    }

    @Test
    public void testEmptyArray() {
        Integer[] arr = new Integer[0];
        testAllSorts(arr);
    }

    @Test
    public void testSingleElementArray() {
        Integer[] arr = new Integer[]{42};
        testAllSorts(arr);
    }

    @Test
    public void testAlreadySortedArray() {
        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        testAllSorts(arr);
    }

    @Test
    public void testReverseSortedArray() {
        Integer[] arr = new Integer[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        testAllSorts(arr);
    }

    @Test
    public void testAllEqualElements() {
        Integer[] arr = new Integer[50];
        Arrays.fill(arr, 7);
        testAllSorts(arr);
    }

    @Test
    public void testWithDuplicates() {
        Integer[] arr = new Integer[]{5, 1, 3, 1, 2, 5, 4, 3, 2, 1, 4, 5};
        testAllSorts(arr);
    }

    @Test
    public void testWithNegativeNumbers() {
        Integer[] arr = new Integer[]{-5, -1, -10, -3, 0, 2, -7, 4, -2};
        testAllSorts(arr);
    }

    @Test
    public void testWithMinMaxValues() {
        Integer[] arr = new Integer[]{Integer.MAX_VALUE, 0, Integer.MIN_VALUE, 100, -100, Integer.MAX_VALUE};
        testAllSorts(arr);
    }

    @Test
    public void testAlternatingHighLowValues() {
        Integer[] arr = new Integer[]{10, 1, 9, 2, 8, 3, 7, 4, 6, 5};
        testAllSorts(arr);
    }
}
