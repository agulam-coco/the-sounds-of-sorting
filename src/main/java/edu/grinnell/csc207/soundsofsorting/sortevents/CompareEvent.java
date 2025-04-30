package edu.grinnell.csc207.soundsofsorting.sortevents;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@code CompareEvent} logs a comparison a sort makes between two
 * indices in the array.
 *
 * @param <T> the type of elements in the array
 */
public class CompareEvent<T> implements SortEvent<T> {

    private final List<Integer> indices;

    /**
     * Constructs a CompareEvent for the given indices.
     *
     * @param firstIndex  the first index to compare
     * @param secondIndex the second index to compare
     */
    public CompareEvent(int firstIndex, int secondIndex) {
        indices = new ArrayList<>();
        indices.add(firstIndex);
        indices.add(secondIndex);
    }

    @Override
    public SortEvent<T> apply(T[] arr) {
        return this;
    }

    @Override
    public List<Integer> getAffectedIndices() {
        return indices;
    }

    @Override
    public boolean isEmphasized() {
        return false;
    }
}
