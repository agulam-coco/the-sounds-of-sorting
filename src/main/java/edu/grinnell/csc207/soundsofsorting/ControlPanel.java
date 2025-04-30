package edu.grinnell.csc207.soundsofsorting;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import edu.grinnell.csc207.soundsofsorting.sortevents.SortEvent;
import edu.grinnell.csc207.soundsofsorting.sorts.Sorts;

/**
 * The Control Panel houses the GUI for interacting with the Sounds of Sorting
 * application.
 */
public class ControlPanel extends JPanel {

    /** Frames per second to render at. */
    private static final int FPS = 20;

    /** MIDI values for the B minor pentatonic scale. */
    public static final int[] bMinorPentatonicValues = {
        46, 49, 51, 53, 56, 58, 61, 63, 65, 68, 70, 73, 75, 78, 82, 85, 87
    };

    /** MIDI values for the chromatic scale. */
    public static final int[] chromaticValues = {
        40, 41, 42, 43, 44, 45, 46, 47, 48, 49,
        50, 51, 52, 53, 54, 55, 56, 57, 58, 59,
        60, 61, 62, 63, 64, 65, 66, 67, 68, 69,
        70, 71, 72, 73, 74, 75, 76, 77, 78, 79
    };

    /**
     * Generates a list of sorting events using the specified algorithm.
     *
     * @param sort the name of the sort
     * @param arr the array to sort
     * @return list of sort events
     */
    private static List<SortEvent<Integer>> generateEvents(String sort, Integer[] arr) {
        switch (sort) {
            case "Selection": return Sorts.selectionSort(arr);
            case "Insertion": return Sorts.insertionSort(arr);
            case "Bubble": return Sorts.bubbleSort(arr);
            case "Merge": return Sorts.mergeSort(arr);
            case "Quick": return Sorts.quickSort(arr);
            case "Tim": return Sorts.timSort(arr);
            default: throw new IllegalArgumentException("Unknown sort type");
        }
    }

    /**
     * Generates the named scale.
     *
     * @param name name of the scale
     * @return the scale
     */
    public static Scale generateScale(String name) {
        switch (name) {
            case "Pentatonic": return new Scale(bMinorPentatonicValues);
            case "Chromatic": return new Scale(chromaticValues);
            default: throw new IllegalArgumentException("Unknown scale name");
        }
    }

    /**
     * Converts FPS to period in milliseconds.
     *
     * @param fps frames per second
     * @return milliseconds per frame
     */
    private static int toPeriod(int fps) {
        return 1000 / fps;
    }

    private Scale scale;
    private ArrayPanel panel;
    private boolean isSorting;

    /**
     * Constructs the ControlPanel.
     *
     * @param notes the NoteIndices to manage
     * @param panel the panel to repaint
     */
    public ControlPanel(NoteIndices notes, ArrayPanel panel) {
        this.scale = new Scale(bMinorPentatonicValues);
        notes.initializeAndShuffle(scale.size());
        this.panel = panel;

        JComboBox<String> sorts = new JComboBox<>(new String[]{
            "Selection", "Insertion", "Bubble", "Merge", "Quick", "Tim"
        });
        add(sorts);

        JComboBox<String> scales = new JComboBox<>(new String[]{
            "Pentatonic", "Chromatic"
        });
        add(scales);

        JButton makeScaleButton = new JButton("Make Scale");
        makeScaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isSorting) {
                    scale = generateScale((String) scales.getSelectedItem());
                    notes.initializeAndShuffle(scale.size());
                    ControlPanel.this.panel.repaint();
                }
            }
        });
        add(makeScaleButton);

        JButton playButton = new JButton("Play");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isSorting) {
                    return;
                }
                isSorting = true;
                Integer[] disposableCopy = notes.getNotes().clone();
                List<SortEvent<Integer>> events = generateEvents(
                    (String) sorts.getSelectedItem(), disposableCopy);

                List<SortEvent<Integer>> compareEvents = new ArrayList<>();
                for (SortEvent<Integer> event : events) {
                    if (!event.isEmphasized()) {
                        compareEvents.add(event);
                    }
                }
                events.removeAll(compareEvents);
                events.addAll(compareEvents);

                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    private int index = 0;
                    private Integer[] disposableCopy = notes.getNotes().clone();

                    @Override
                    public void run() {
                        if (index < events.size()) {
                            notes.clearAllHighlighted();
                            SortEvent<Integer> e = events.get(index++);
                            e.apply(notes.getNotes());

                            for (int affectedIndex : e.getAffectedIndices()) {
                                if (e.isEmphasized()) {
                                    scale.playNote(affectedIndex, true);
                                    notes.highlightNote(affectedIndex);
                                }
                            }
                            panel.repaint();
                        } else {
                            this.cancel();
                            panel.repaint();
                            isSorting = false;
                        }
                    }
                }, 0, toPeriod(FPS));
            }
        });
        add(playButton);
    }
}
