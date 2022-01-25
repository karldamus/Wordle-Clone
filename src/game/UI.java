// Author: Karl Damus
// Date Created: Jan 24, 2022

package game;

import game.shared.Guess;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static game.shared.Constants.*;

public class UI {
    private JFrame gameWindow;
    private JPanel guessPanel;
    private JPanel entryPanel;
    private JPanel[][] charBoxes;
    
    JTextField input;
    volatile String inputText = "";

    /**
     * Create a UI object and setup the Swing components
     */
    public UI() {
        setup();
    }

    /**
     * Continuously update the UI while waiting for input and validating using the Game object
     * 
     * @param game the current Game object
     * 
     * @see Game
     */
    public void update(Game game) {
        System.out.println(game.getCurrentWord());
        
        int count = game.getNumGuesses(); // initialize a local count variable to avoid endless loop
        
        while (count < NUM_ALLOWED_GUESSES) {
            // await input
            while (inputText == "") {
                Thread.onSpinWait();
            }
            // check for invalid input
            if (game.invalidInput(inputText))
                continue;
            
            // create new guess
            Guess guess = new Guess(inputText.toUpperCase(), game.getCurrentWord());
            updateCharBox(game.getNumGuesses(), guess);
            
            // reset inputText and inputBox
            inputText = "";
            input.setText("");
    
            // update UI
            updatePanels();
            
            // increment counts, local and within game
            game.incNumGuesses();
            count++;
        }
    }

    /**
     * Create a new JLabel with appropriately styling
     * 
     * @param s the String value of the JLabel
     *          
     * @return the created JLabel
     */
    public JLabel charLabel(String s) {
        JLabel label = new JLabel(s, SwingConstants.CENTER);
        label.setSize(CHAR_BOX_WIDTH, CHAR_BOX_HEIGHT);
        label.setForeground(WHITE);
        label.setText(s);
        label.setFont(new Font("Sans-Serif", Font.BOLD, 24));
        
        return label;
    }

    /**
     * Setup all Java Swing components
     */
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
        
        // add button listener to detect input
        ButtonListener bl = new ButtonListener();
        input.addActionListener(bl);
        submit.addActionListener(bl);
        
        entryPanel.add(input);
        entryPanel.add(submit);
        
        // add components
        gameWindow.add(guessPanel, BorderLayout.CENTER);
        gameWindow.add(entryPanel, BorderLayout.PAGE_END);
        
        // finalize window
        gameWindow.pack();
        gameWindow.setVisible(true);
    }

    /**
     * Update all charBox JPanels in a given row with info from Guess object
     * @param row the current row ... based on how many number of guesses have been made
     * @param guess the new Guess object to get colour and character info for JLabels
     */
    public void updateCharBox(int row, Guess guess) {
        // loop through each map in the Guess ArrayList of maps
        for (int i = 0; i < guess.getGuess().size(); i++) {
            Integer key = (Integer) guess.getGuess().get(i).keySet().toArray()[0];
            Character value = (Character) guess.getGuess().get(i).values().toArray()[0];
            
            // get colour based on key value
            // see shared/Guess
            Color bgColour = switch (key) {
                case 0 -> DARK_GREY;
                case 1 -> YELLOW;
                case 2 -> GREEN;
                default -> BACKGROUND;
            };

            JLabel newLabel = charLabel(String.valueOf(value));
            
            // update colour, remove old JLabel, add new JLabel, and refresh
            charBoxes[row][i].setBackground(bgColour);
            charBoxes[row][i].remove(0);
            charBoxes[row][i].add(newLabel);
            charBoxes[row][i].revalidate();
            charBoxes[row][i].repaint();
        }
    }

    /**
     * Refresh the following JPanels:
     * - guessPanel
     * - entryPanel
     * - gameWindow
     */
    public void updatePanels() {
        guessPanel.revalidate();
        guessPanel.repaint();
        entryPanel.revalidate();
        entryPanel.repaint();
        gameWindow.revalidate();
        gameWindow.repaint();
    }

    /**
     * Custom ButtonListener class to enable user input
     * 
     * @see #input
     * @see #setup() 
     */
    public class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // update global inputText
            inputText = input.getText();
            System.out.println("From ButtonListener: " + inputText);
        }
    }
}

