package edu.grinnell.csc207.soundsofsorting.sortevents;

import java.util.ArrayList;
import java.util.List;

/**
 * A <code>SwapEvent</code> logs a swap between two indices of the array.
 */
public class SwapEvent<T> implements SortEvent<T> {
    // TODO: implement me
    private List<Integer> indeces;
    
    /**
     * Initialize Compare Event and add compared events to a list
     * @param firstIndex
     * @param secondIndex 
     */
    public SwapEvent(int firstIndex, int secondIndex){
        indeces = new ArrayList<>();
        indeces.add(firstIndex);
        indeces.add(secondIndex);
    }
    
    /**
     *
     * @param arr
     * @return 
     */
    @Override
    public SortEvent<T> apply(T[] arr) {
        
        swap(arr,getAffectedIndices().get(0),getAffectedIndices().get(1));
        
        return this;
    }
    
    @Override
     public List<Integer> getAffectedIndices(){
         return indeces;
     }
    
    @Override
    public boolean isEmphasized(){
        //is emphasized
        return true;
    }
        /**
     * Swaps indices <code>i</code> and <code>j</code> of array
     * <code>arr</code>.
     *
     * @param <T> the carrier type of the array
     * @param arr the array to swap
     * @param i the first index to swap
     * @param j the second index to swap
     */
    public static <T> void swap(T[] arr, int i, int j) {
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
