package edu.grinnell.csc207.soundsofsorting.sortevents;

import java.util.Arrays;
import java.util.List;

/**
 * A {@code CopyEvent} logs a copy of a value into an index of the array.
 *
 * @param <T> the type of elements in the array
 */
public class CopyEvent<T> implements SortEvent<T> {

    private final int index;
    private final T value;

    /**
     * Constructs a CopyEvent with the given index and value.
     *
     * @param index the index to copy the value into
     * @param value the value to be copied
     */
    public CopyEvent(int index, T value) {
        this.value = value;
        this.index = index;
    }

    @Override
    public SortEvent<T> apply(T[] arr) {
        arr[getAffectedIndices().get(0)] = this.value;
        return this;
    }

    @Override
    public List<Integer> getAffectedIndices() {
        return Arrays.asList(index);
    }

    @Override
    public boolean isEmphasized() {
        return true;
    }
}
