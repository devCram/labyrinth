package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Created by Marvin Röck, Daniel Deuscher, Rehan App
 * Programmierprojekt Sommersemester 2016
 * Das Verrückte Labyrinth
 */
public class Rules implements ActionListener{

    /**
     * Attributes
     */
    private String longText = "Test";
    JFrame frame = createFrame();
    JButton back = addButtons("close");

    /**
     * Creates total GUI for Rules
     */
    public void createGui(){
        //--------------------------------------------------------------
        //create new Panel and set GridBagLayout to it
        JPanel panel = new JPanel(new GridBagLayout());
        //--------------------------------------------------------------

        //--------------------------------------------------------------
        //create new GridBagLayout and set layout (x=1 ; y=0)
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.weighty = 1;
        constraints.insets = new Insets(30,0,0,0);
        constraints.gridx = 0;
        constraints.gridx++;
        constraints.gridy = 0;
        //create new label and set to layout
        panel.add(setLabel("Das verrückte Labyrinth", 40), constraints);
        //--------------------------------------------------------------

        //--------------------------------------------------------------
        //layout set to (x=1 ; y=2)
        constraints.gridy = 2;
        //create new lable and set to layout
        panel.add(setLabel("Die Spielregeln: ", 30), constraints);
        //--------------------------------------------------------------

        //--------------------------------------------------------------
        //layout set to (x=1 ; y=3)
        constraints.gridy = 3;
        //create new scrollPanel and set to layout
        panel.add(setScrollPane("Das sind die Spielregeln: " + createLongText() ,20),constraints);
        //---------------------------------------------------------------

        //--------------------------------------------------------------
        //layout set to (x=1 ; y=4)
        constraints.gridy = 4;
        //add actionlistener to back button and add it to panel
        back.addActionListener(this);
        panel.add(back,constraints);
        //--------------------------------------------------------------

        //set panel to frame and add borderlayout to frame
        frame.add(panel, BorderLayout.NORTH);
    }

    /**
     *creates a specialized frame for this class
     * @return
     */
    public JFrame createFrame(){
        JFrame frame = new JFrame("DvL Spielregeln");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(1200, 700);
        frame.setLocation(300, 200);
        return frame;
    }

    /**
     * creates a specialized label and gets text and size as input
     * @param labelString
     * @param size
     * @return
     */
    public JLabel setLabel(String labelString, int size){
        JLabel label = new JLabel(labelString);
        label.setFont(new Font("Serif", Font.PLAIN, size));
        return label;
    }

    /**
     * creates a for Rule class specialized scrollable panel
     * @param text
     * @param sizeOfText
     * @return
     */
    public JScrollPane setScrollPane(String text, int sizeOfText){
        JTextArea textArea = new JTextArea(text);
        textArea.setFont(new Font("Serif", Font.PLAIN, sizeOfText));
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setPreferredSize(new Dimension(600,400));
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        return scroll;
    }

    /**
     * specialized button for rules class is created
     * @param text
     * @return
     */
    public JButton addButtons(String text){
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(200, 50));
        button.setFont(new Font("Serif", Font.PLAIN, 25));
        return button;
    }

    /**
     * creates test Text
     * @return
     */
    public String createLongText(){
        for (int i = 0; i < 1000; i++) {
            longText = longText + " Test ";
        }
        return longText;
    }

    /**
     * action Listener
     * @param ae
     */
    public void actionPerformed (ActionEvent ae){
        if(ae.getSource() == this.back){
            frame.dispose();
        }
    }
}
