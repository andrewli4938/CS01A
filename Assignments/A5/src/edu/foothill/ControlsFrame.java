package edu.foothill;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import edu.foothill.ControlsFrame.PosterListener;

public class ControlsFrame extends JFrame
{
    private int posterIndex;
    private JButton setSizeButton;
    private JTextField widthTextArea, heightTextArea;
    private JLabel lblName, lblHeight, lblWidth;
    private ImageDisplay imageDisplay;
    private JPanel panelMain;
    private JComboBox cmboPosters;
    private String[] posterNames =
    { "Matrix", "Tron Legacy", "Ralph Breaks the Internet", "Deadpool", "The Interview", "Knives Out", "Creed 2",
            "Parasite", "1917", "Big Hero 6" };
    private String[] posterImagePaths =
    { "17072638696_f871731849_b.jpg", "4859886671_cef0598bf3_b.jpg", "RBTI.jpg", "deadpool.png", "interview.jpg",
            "knivesout.jpg", "creed.jpeg", "parasite.jpg", "ww1.jpg", "hero.jpeg" };

    // construct PosterDisplay Frame
    private JFrame jFramePosterDisplayer = new JFrame();

    public static BufferedImage getImageSize(String name)
    {
        BufferedImage img;
        try
        {
            img = ImageIO.read(new File(name));
            return img;
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public ControlsFrame(String title) throws HeadlessException, IOException
    {
        super(title);

        BorderLayout layout = new BorderLayout(5, 10);
        setLayout(layout);

        // set up buttons and text fields
        setSizeButton = new JButton("Set Size");
        widthTextArea = new JTextField(5);
        heightTextArea = new JTextField(5);
        lblName = new JLabel("Choose a poster from the dropdown");
        lblHeight = new JLabel("Height: ");
        lblWidth = new JLabel("Width: ");
        cmboPosters = new JComboBox<Object>(posterNames);
        panelMain = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));

        // set the control frame
        add(BorderLayout.NORTH, lblName);
        add(panelMain);
        panelMain.setBorder(new TitledBorder("Movie Poster"));
        panelMain.add(cmboPosters);
        panelMain.add(lblWidth);
        panelMain.add(widthTextArea);
        panelMain.add(lblHeight);
        panelMain.add(heightTextArea);
        panelMain.add(setSizeButton);

        // add the listener for the combo box
        PosterListener comboListener = new PosterListener();
        cmboPosters.addItemListener(comboListener);

        // add the listener for the set size button
        ButtonListener sizeListener = new ButtonListener();
        setSizeButton.addActionListener(sizeListener);

        // set the display frame
        imageDisplay = new ImageDisplay();
        imageDisplay.setImagePath("17072638696_f871731849_b.jpg");

        // get image size

        jFramePosterDisplayer = new JFrame();
        jFramePosterDisplayer.add(imageDisplay);
        BufferedImage i = ControlsFrame.getImageSize("17072638696_f871731849_b.jpg");
        jFramePosterDisplayer.setSize(i.getWidth(), i.getHeight());

        jFramePosterDisplayer.setLocationRelativeTo(null);
        jFramePosterDisplayer.setVisible(true);
    }

    class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if ( Integer.parseInt(widthTextArea.getText()) > 0 && Integer.parseInt(heightTextArea.getText()) > 0 )
            {
                jFramePosterDisplayer.setSize(Integer.parseInt(widthTextArea.getText()),
                        Integer.parseInt(heightTextArea.getText()));
            } else
            {
                JOptionPane.showMessageDialog(null, "Please enter an integer greater than 0 in both texts boxes.");
            }
            
        }
    }

    class PosterListener implements ItemListener
    {
        public void itemStateChanged(ItemEvent e)
        {
            posterIndex = cmboPosters.getSelectedIndex();
            imageDisplay.setImagePath(posterImagePaths[posterIndex]);

            BufferedImage i = ControlsFrame.getImageSize( posterImagePaths[posterIndex] );
            jFramePosterDisplayer.setSize(i.getWidth(), i.getHeight());

        }
    }
}
