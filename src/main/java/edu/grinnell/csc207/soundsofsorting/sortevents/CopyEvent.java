package edu.grinnell.csc207.soundsofsorting.sortevents;

import java.util.ArrayList;
import java.util.List;

/**
 * A <code>CopyEvent</code> logs a copy of a value into an index of the array.
 */
public class CopyEvent<T> implements SortEvent<T>{
   
    private List<Integer> indeces;
    
    /**
     * Initialize Compare Event and add compared events to a list
     * @param firstIndex
     * @param secondIndex 
     */
    public CopyEvent(int firstIndex, int secondIndex){
        indeces = new ArrayList<>();
        indeces.add(firstIndex);
        indeces.add(secondIndex);
    }
    
    @Override
    public SortEvent<T> apply(T[] arr) {
        arr[getAffectedIndices().get(0)] = arr[getAffectedIndices().get(1)];
       
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
}
