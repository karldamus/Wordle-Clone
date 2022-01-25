package game.shared;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public interface Constants {
	// UI
	String GAME_TITLE = "A Wordle Clone by Karl Damus";
	
	// COLOURS
	Color WHITE = Color.decode("#ffffff");
	Color GREEN = Color.decode("#538d4e");
	Color YELLOW = Color.decode("#b59f3b");
	Color LIGHT_GREY = Color.decode("#818384");
	Color DARK_GREY = Color.decode("#3a3a3c");
	Color BACKGROUND = Color.decode("#000000");
	
	// BORDERS
	Border J_LABEL_BORDER = BorderFactory.createLineBorder(WHITE, 5);
	
	// SIZING
	int CHAR_BOX_HEIGHT = 100; 
	int CHAR_BOX_WIDTH = 100;
	int MIN_WINDOW_HEIGHT = 700;
	int MIN_WINDOW_WIDTH = 500;
	
	
	// BACKEND
	int WORD_LENGTH = 5;
	int NUM_ALLOWED_GUESSES = 6;
	
	// FILES
	String WORDS_LIST = "src/game/txt/words.txt";
	
	// DEV ZONE
	boolean DEV_MODE = true;
}
