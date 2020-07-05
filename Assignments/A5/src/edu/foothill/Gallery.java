package edu.foothill;

import java.awt.HeadlessException;
import java.io.IOException;

import javax.swing.JFrame;

public class Gallery
{
    public static void main(String[] args) throws HeadlessException, IOException
    {
        // TODO: Your group needs to write ControlsFrame together
        ControlsFrame controls = new ControlsFrame("Gallery");
        // Make sure the entire application exits when the ControlsFrame is closed.
        controls.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controls.setSize(500, 250);
        controls.setVisible(true);

    }
}