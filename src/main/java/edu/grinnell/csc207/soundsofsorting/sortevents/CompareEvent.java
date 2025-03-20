package edu.grinnell.csc207.soundsofsorting.sortevents;

import java.util.ArrayList;
import java.util.List;

/**
 * A <code>CompareEvent</code> logs a comparison a sort makes between two
 * indices in the array.
 */
public class CompareEvent<T> implements SortEvent<T> {
    
    private List<Integer> indeces;
    
    /**
     * Initialize Compare Event and add compared events to a list
     * @param firstIndex
     * @param secondIndex 
     */
    public CompareEvent(int firstIndex, int secondIndex){
        indeces = new ArrayList<>();
        indeces.add(firstIndex);
        indeces.add(secondIndex);
    }
    
    //Does nothing to the array
    @Override
    public void apply(T[] arr) {
        //DO NOTHING
    }
    
    @Override
     public List<Integer> getAffectedIndices(){
         return indeces;
     }
    
    @Override
    public boolean isEmphasized(){
        //not emphasized
        return false;
    }
}
