package edu.grinnell.csc207.soundsofsorting.sortevents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A <code>CopyEvent</code> logs a copy of a value into an index of the array.
 */
public class CopyEvent<T> implements SortEvent<T>{
   
    private int index;
    private T value;
    
    /**
     * Initialize Compare Event and add compared events to a list
     * @param value
     * @param index
     */
    public CopyEvent(int index, T value){
        this.value = value;
        this.index = index;
        
    }
    
    @Override
    public SortEvent<T> apply(T[] arr) {
        arr[getAffectedIndices().get(0)] = this.value;
       
        return this;
    }
    
    @Override
     public List<Integer> getAffectedIndices(){
         return Arrays.asList(index);
     }
    
    @Override
    public boolean isEmphasized(){
        //is emphasized
        return true;
    }
}
