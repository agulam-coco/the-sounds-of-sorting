package edu.grinnell.csc207.soundsofsorting;

/**
 * A collection of indices into a Scale object.
 * These indices are the subject of the various sorting algorithms
 * in the program.
 */
public class NoteIndices {
    
    private Integer[] notesArr;
    /**
     * @param n the size of the scale object that these indices map into
     */
    public NoteIndices(int n) {
        initializeAndShuffle(n);
    }
    
    /**
     * Reinitializes this collection of indices to map into a new scale object
     * of the given size.  The collection is also shuffled to provide an
     * initial starting point for the sorting process.
     * @param n the size of the scale object that these indices map into
     */
    public void initializeAndShuffle(int n) {
                notesArr = new Integer[n];

    }
    
    /** @return the indices of this NoteIndices object */
    public Integer[] getNotes() { 
        // TODO: fill me in!
        return null;
    }
    
    /**
     * Highlights the given index of the note array
     * @param index the index to highlight
     */
    public void highlightNote(int index) {
        // TODO: fill me in
    }
    
    /**
     * @param index the index to check
     * @return true if the given index is highlighted
     */
    public boolean isHighlighted(int index) {
        // TODO: fill me in
        return false;
    }
    
    /** Clears all highlighted indices from this collection */
    public void clearAllHighlighted() {
        // TODO: fill me in
    }
}
