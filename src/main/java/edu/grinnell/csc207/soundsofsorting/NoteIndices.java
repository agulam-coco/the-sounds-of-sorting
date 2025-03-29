package edu.grinnell.csc207.soundsofsorting;

import java.util.Arrays;
import java.util.Collections;

/**
 * A collection of indices into a Scale object. These indices are the subject of
 * the various sorting algorithms in the program.
 */
public class NoteIndices {

    private Integer[] notesArr;
    private boolean[] highlightNotesArr;

    /**
     * @param n the size of the scale object that these indices map into
     */
    public NoteIndices(int n) {
        initializeAndShuffle(n);
    }

    /**
     * Reinitializes this collection of indices to map into a new scale object
     * of the given size. The collection is also shuffled to provide an initial
     * starting point for the sorting process.
     *
     * @param n the size of the scale object that these indices map into
     */
    //credit: https://stackoverflow.com/a/16000210
    public void initializeAndShuffle(int n) {
        notesArr = new Integer[n];

        //all initialized to false;
        highlightNotesArr = new boolean[n];

        for (int i = 0; i < notesArr.length; i++) {
            notesArr[i] = i;
        }
        Collections.shuffle(Arrays.asList(notesArr));

    }

    /**
     * @return the indices of this NoteIndices object
     */
    public Integer[] getNotes() {
        return notesArr;
    }

    /**
     * Highlights the given index of the note array
     *
     * @param index the index to highlight
     */
    public void highlightNote(int index) {
        highlightNotesArr[index] = true;
    }

    /**
     * @param index the index to check
     * @return true if the given index is highlighted
     */
    public boolean isHighlighted(int index) {
        return highlightNotesArr[index];
    }

    /**
     * Clears all highlighted indices from this collection
     */
    //credit: https://stackoverflow.com/a/10492426
    public void clearAllHighlighted() {
        Arrays.fill(highlightNotesArr, false);
    }
}
