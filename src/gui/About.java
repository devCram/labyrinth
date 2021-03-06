package gui;

/**
 * Created by Marvin Röck, Daniel Deuscher, Rehan App
 * Programmierprojekt Sommersemester 2016
 * Das Verrückte Labyrinth
 */
import properties.GameProperty;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

public class About implements ActionListener {

    /**
     * Attributes
     */
    JFrame frame = createFrame();
    JButton back = addButtons("Zurück");

    /**
     * Total GUI is created
     */
    public void createGui(){
        GameProperty gameProperty = new GameProperty();
        Properties properties = gameProperty.getProperties();
        String developer = properties.getProperty("developer");
        String url = properties.getProperty("url");
        String date = properties.getProperty("date");

        //-------------------------------------------------------------------
        //Panel is container of the GUI Elements (-> a GridBagLayout is used)
        JPanel panel = new JPanel(new GridBagLayout());
        //-------------------------------------------------------------------

        //--------------------------------------------------------------------------------------
        //Layout for Header is set (x=0 ; y=0 ; width = 2columns ; marginRight=30)
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.weighty = 1;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(30,0,0,0);
        constraints.gridx = 0;
        constraints.gridy = 0;
        //Label is created and set to the defined Layout
        panel.add(setLabel("About", 30), constraints);
        //--------------------------------------------------------------------------------------

        //--------------------------------------------------------------------------------------
        //Layout for Developers is set (x=0 ; y=1 ; width = 1column)
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridwidth=1;
        constraints.gridy++;
        constraints.insets = new Insets(30,0,0,0);
        panel.add(setLabel("Developers : ", 25), constraints);
        //(x=1 ; y=1)
        constraints.gridx++;
        panel.add(setLabel(developer, 25),constraints);
        //--------------------------------------------------------------------------------------

        //--------------------------------------------------------------------------------------
        //Layout for Developers is set (x=0 ; y=2 ; width = 1column)
        constraints.gridx=0;
        constraints.gridwidth=1;
        constraints.gridy++;
        panel.add(setLabel("Url : ", 25), constraints);
        //(x=1 ; y=2)
        constraints.gridx++;
        panel.add(setLabel(url, 25),constraints);
        //--------------------------------------------------------------------------------------

        //--------------------------------------------------------------------------------------
        constraints.gridx=0;
        //Layout for Developers is set (x=0 ; y=3 ; width=1column)
        constraints.gridwidth=1;
        constraints.gridy++;
        panel.add(setLabel("Date : ", 25), constraints);
        //(x=1 ; y=2)
        constraints.gridx++;
        panel.add(setLabel(date, 25),constraints);
        //--------------------------------------------------------------------------------------

        //--------------------------------------------------------------------------------------
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.weighty = 1;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(45,0,0,0);
        constraints.gridx = 0;
        constraints.gridy = 4;
        //Layout for Developers is set (x=0 ; y=4 ; width=1column)
        back.addActionListener(this);
        panel.add(back, constraints);
        //--------------------------------------------------------------------------------------

        //-------------------------------------------------------------------------------------------------------------------
        //The whole Panel, which contains the GUI Elements on its Layout is added to the frame with a Borderlayout
        frame.add(panel, BorderLayout.NORTH);
        //-------------------------------------------------------------------------------------------------------------------
    }

    /** CREATE FRAME
     * Creates a specialized frame for the class AddPlayer
     * @return
     */
    public JFrame createFrame(){
        JFrame frame = new JFrame("About Properties");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(600, 400);
        frame.setLocation(300, 200);
        return frame;
    }

    /** SET LABEL
     * Creates a Label, gets the name and the size as input
     * @param labelString
     * @param size
     * @return
     */
    public JLabel setLabel(String labelString, int size){
        JLabel label = new JLabel(labelString);
        label.setFont(new Font("Serif", Font.PLAIN, size));
        return label;
    }

    /** ADD BUTTON
     * Creates a Button gets the text of it as input
     * @param text
     * @return
     */
    public JButton addButtons(String text){
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(250, 50));
        button.setFont(new Font("Serif", Font.PLAIN, 25));
        return button;
    }

    /** ACTION LISTENER
     * actionlistener method for the back button
     * @param ae
     */
    public void actionPerformed (ActionEvent ae){
        if(ae.getSource() == this.back){
            frame.dispose();
        }
    }
}
