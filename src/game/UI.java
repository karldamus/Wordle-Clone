// Author: Karl Damus
// Date Created: Jan 24, 2022

package game;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static game.shared.Constants.*;

/**
 * Purpose:
 * quick blurb
 * Issues:
 * - issue 1
 * Documentation Link:
 * N/A
 */
public class UI {
    private JFrame gameWindow;
    private JPanel guessPanel;
    private JPanel entryPanel;
    private JPanel[][] charBoxes;
    
    JTextField input;
    String inputText = "";
    
    public static void main(String[] args) {
        UI ui = new UI();
    }
    
    public UI() {
        setup();
    }
    
    public JLabel charLabel(String s) {
        JLabel label = new JLabel(s, SwingConstants.CENTER);
        label.setSize(CHAR_BOX_WIDTH, CHAR_BOX_HEIGHT);
        label.setForeground(WHITE);
        label.setText(s);
        label.setFont(new Font("Sans-Serif", Font.BOLD, 24));
        
        return label;
    }
    
    public void setup() {
        // main window
        gameWindow = new JFrame(GAME_TITLE); 
        gameWindow.setBackground(BACKGROUND);
        gameWindow.setMinimumSize(new Dimension(MIN_WINDOW_WIDTH, MIN_WINDOW_HEIGHT));
        gameWindow.setLayout(new BorderLayout());
        
        // guessPanel
        guessPanel = new JPanel();
        
        // guessPanel layout
        guessPanel.setLayout(new GridLayout(NUM_ALLOWED_GUESSES, WORD_LENGTH));
        guessPanel.setMaximumSize(new Dimension((int) (gameWindow.getWidth() * 0.97), (int) (gameWindow.getHeight() * 0.97)));
        
        // setup individual char boxes
        charBoxes = new JPanel[NUM_ALLOWED_GUESSES][WORD_LENGTH];
        
        for (int i = 0; i < NUM_ALLOWED_GUESSES; i++) {
            for (int j = 0; j < WORD_LENGTH; j++) {
                
                // create JPanel with specific sizing and colouring
                JPanel panel = new JPanel();
                panel.setSize(CHAR_BOX_WIDTH, CHAR_BOX_HEIGHT);
                panel.setBorder(J_LABEL_BORDER);
                panel.setBackground(BACKGROUND);
                panel.setLayout(new GridBagLayout());
                
                // create JLabel with specific sizing and colouring
                JLabel label = charLabel("");
                
                // add to guessPanel
                panel.add(label, new GridBagConstraints());
                charBoxes[i][j] = panel;
                guessPanel.add(charBoxes[i][j]);
            }
        }
        
        // setup entry panel
        entryPanel = new JPanel();
        
        input = new JTextField(30);
        JButton submit = new JButton("Enter Guess");
//        String text = "";
        
        submit.addActionListener(new ButtonListener());
        
        entryPanel.add(input);
        entryPanel.add(submit);
        
        // TODO: implement button listener for submit button
        // play around with global variables? 
        // think about how to link the Game data with the UI
        // need some way to get text from user in UI
        //      then process it inside of the Game class
        //      then return info from Game class to update 
        //      charBoxes[][] with appropriate letters and colours
        // might make the UI within the Game class ... idrk
        
//        submit.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                inputText = input.getText();
////                System.out.println(inputText);
//                
//                if (inputText.length() == WORD_LENGTH) {
//                    for (int i = 0; i < inputText.length(); i++) {
//                        JLabel newLabel = charLabel(String.valueOf(inputText.charAt(i)));
//                        charBoxes[0][i].remove(0);
//                        charBoxes[0][i].add(newLabel);
//                        
//                        charBoxes[0][i].revalidate();
//                        charBoxes[0][i].repaint();
//                        guessPanel.revalidate();
//                        guessPanel.repaint();
//                        gameWindow.revalidate();
//                        gameWindow.repaint();
//                        
//                    }
//                }
//            }
//        });
        
        // add components
        gameWindow.add(guessPanel, BorderLayout.CENTER);
        gameWindow.add(entryPanel, BorderLayout.PAGE_END);
        
        
        // finalize window
        gameWindow.pack();
        gameWindow.setVisible(true);
    }
    
}

//class ButtonListener implements ActionListener {
//    public void actionPerformed(ActionEvent e) {
//            String inputText = input.getText();
////                System.out.println(inputText);
//
//                if (inputText.length() == WORD_LENGTH) {
//                    for (int i = 0; i < inputText.length(); i++) {
//                        JLabel newLabel = charLabel(String.valueOf(inputText.charAt(i)));
//                        charBoxes[0][i].remove(0);
//                        charBoxes[0][i].add(newLabel);
//
//                        charBoxes[0][i].revalidate();
//                        charBoxes[0][i].repaint();
//                        guessPanel.revalidate();
//                        guessPanel.repaint();
//                        gameWindow.revalidate();
//                        gameWindow.repaint();
//
//                    }
//                }
//            }
//    }
//}


