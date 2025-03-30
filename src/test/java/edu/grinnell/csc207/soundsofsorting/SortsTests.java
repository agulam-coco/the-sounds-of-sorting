package edu.grinnell.csc207.soundsofsorting;

import edu.grinnell.csc207.soundsofsorting.sortevents.SortEvent;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

import edu.grinnell.csc207.soundsofsorting.sorts.Sorts;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        
        //Credit: Chat GPT
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

    public void testSort(Consumer<Integer[]> func) {
        Integer[] arr = makeTestArray();
        func.accept(arr);
        System.out.println(Arrays.toString(arr));

    }

    @Test
    public void testBubbleSort() {
        testSort(Sorts::bubbleSort);
        Integer[] original = makeTestArray();
        Integer[] temp = original.clone();

        List<SortEvent<Integer>> events = Sorts.bubbleSort(original);
        Sorts.eventSort(temp, events);

        assertEquals(0, Arrays.compare(original, temp), "Event sort failed with given events on arr");
    }

    @Test
    public void testInsertionSort() {
        testSort(Sorts::insertionSort);
        Integer[] original = makeTestArray();
        Integer[] temp = original.clone();

        List<SortEvent<Integer>> events = Sorts.insertionSort(original);
        Sorts.eventSort(temp, events);

        assertEquals(0, Arrays.compare(original, temp), "Event sort failed with given events on arr");
    }

    @Test
    public void testSelectionSort() {
        testSort(Sorts::selectionSort);
        Integer[] original = makeTestArray();
        Integer[] temp = original.clone();

        List<SortEvent<Integer>> events = Sorts.selectionSort(original);
        Sorts.eventSort(temp, events);

        assertEquals(0, Arrays.compare(original, temp), "Event sort failed with given events on arr");
    }

    @Test
    public void testMergeSort() {
        testSort(Sorts::mergeSort);
        Integer[] original = makeTestArray();
        Integer[] temp = original.clone();

        List<SortEvent<Integer>> events = Sorts.mergeSort(original);
        Sorts.eventSort(temp, events);

        assertEquals(0, Arrays.compare(original, temp), "Event sort failed with given events on arr");
    }

    @Test
    public void testQuickSort() {
        testSort(Sorts::quickSort);
        Integer[] original = makeTestArray();
        Integer[] temp = original.clone();

        List<SortEvent<Integer>> events = Sorts.quickSort(original);
        Sorts.eventSort(temp, events);

        assertEquals(0, Arrays.compare(original, temp), "Event sort failed with given events on arr");
    }

    @Test
    public void testTimSort() {
        testSort(Sorts::timSort);
        Integer[] original = makeTestArray();
        Integer[] temp = original.clone();

        List<SortEvent<Integer>> events = Sorts.timSort(original);
        Sorts.eventSort(temp, events);

        assertEquals(0, Arrays.compare(original, temp), "Event sort failed with given events on arr");
    }

}
