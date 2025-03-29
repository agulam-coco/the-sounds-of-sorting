package edu.grinnell.csc207.soundsofsorting;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;

import javax.swing.JPanel;

/**
 * A drawing panel for visualizing the contents of a @NoteIndices object.
 */
public class ArrayPanel extends JPanel {

    @SuppressWarnings("unused")
    private NoteIndices notes;

    /**
     * Create a new <code>ArrayPanel</code> with the given notes and dimensions.
     *
     * @param notes the note indices
     * @param width the width of the panel
     * @param height the height of the panel
     */
    public ArrayPanel(NoteIndices notes, int width, int height) {
        this.notes = notes;
        this.setPreferredSize(new Dimension(width, height));
    }

    @Override
    public void paintComponent(Graphics g) {

        //get panel height and width
        //credit: https://stackoverflow.com/a/47796959
        double screenWidth = getWidth();
        double screenHeight = getHeight();

        //rectangle width and block heights
        double rectWidth = screenWidth / notes.getNotes().length;
        double blockHeight = ((1.0 / notes.getNotes().length) * screenHeight);

        //starting x index in canvas
        int currentX = 0;

        for (int index : notes.getNotes()) {
            //calculate height of rectangle rectangle
            double rectHeight = ((int) index + 1.0) * blockHeight;

            //taller rectangles become more blue
            //credit:https://stackoverflow.com/a/42855445
            g.setColor(new Color(0, (int) 255 - (int) ((rectHeight / screenHeight) * 255), (int) ((rectHeight / screenHeight) * 255)));

            g.fillRect(currentX, (int) (screenHeight - rectHeight),(int) rectWidth, (int) rectHeight);

            currentX += rectWidth;

        }

    }
}
