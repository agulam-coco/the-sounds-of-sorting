package edu.grinnell.csc207.soundsofsorting;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * A drawing panel for visualizing the contents of a {@code NoteIndices} object.
 */
public class ArrayPanel extends JPanel {

    @SuppressWarnings("unused")
    private final NoteIndices notes;

    /**
     * Creates a new {@code ArrayPanel} with the given notes and dimensions.
     *
     * @param notes  the note indices
     * @param width  the width of the panel
     * @param height the height of the panel
     */
    public ArrayPanel(NoteIndices notes, int width, int height) {
        this.notes = notes;
        this.setPreferredSize(new Dimension(width, height));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Get panel dimensions
        double screenWidth = getWidth();
        double screenHeight = getHeight();

        // Calculate rectangle width and block height
        double rectWidth = screenWidth / notes.getNotes().length;
        double blockHeight = (1.0 / notes.getNotes().length) * screenHeight;

        // Starting x index on canvas
        int currentX = 0;

        for (int index : notes.getNotes()) {
            // Calculate height of rectangle
            double rectHeight = (index + 1.0) * blockHeight;

            // Taller rectangles become more blue
            int blue = (int) ((rectHeight / screenHeight) * 255);
            int green = 255 - blue;

            g.setColor(new Color(0, green, blue));
            g.fillRect(currentX, (int) (screenHeight - rectHeight), 
                    (int) rectWidth, (int) rectHeight);

            currentX += rectWidth;
        }
    }
}
