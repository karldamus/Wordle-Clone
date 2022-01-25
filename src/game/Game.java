// Author: Karl Damus
// Date Created: Jan 24, 2022

package game;

import game.shared.Guess;
import static game.shared.Constants.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Purpose:
 * Generate the Wordle Application for user
 * Issues:
 * 
 * Documentation Link:
 * N/A
 */
public class Game {
	private  ArrayList<String> availableWords;
	private  String currentWord;
	private  ArrayList<Guess> guesses;
	private int numGuesses;

	/**
	 * Generate a new game
	 * @throws IOException FileNotFound; see Game.setUp 
	 */
	public Game() throws IOException {
		setUp();
		UI ui = new UI();
		ui.update(this);
	}

	/**
	 * Check if the given guess is invalid. Conditions:
	 *      <li>guess String length != Constants.WORD_LENGTH</li>
	 *      
	 * @param guess the given guess/input
	 *                 
	 * @return true if invalid; else false
	 */
	public boolean invalidInput(String guess) {
		if (guess.length() != WORD_LENGTH)
			return true;
		
		return false;
	}

	/**
	 * Setup global attributes for use by the Game
	 * @throws IOException if the file is not found, see Game.loadWordsArray
	 */
	private void setUp() throws IOException {
		loadWordsArray();
		currentWord = getWord();
		guesses = new ArrayList<Guess>();
		numGuesses = 0;
	}

	/**
	 * Load the words from file words.game.txt (WORDS_LIST) into availableWords
	 * @throws IOException if the file is not found, see Constants.WORDS_LIST
	 */
	private void loadWordsArray() throws IOException {
		availableWords = new ArrayList<>(); // init ArrayList of Strings
		BufferedReader r = new BufferedReader(new FileReader(WORDS_LIST)); // reader for file
		
		// loop through file r and add all lines to availableWords
		for (String line = r.readLine(); line != null; line = r.readLine()) {
			availableWords.add(line.toUpperCase());
		}
	}

	/**
	 * Get a random word from the list of availableWords
	 * @return a random word to be guessed
	 */
	private String getWord() throws IOException {
		// check to see if words array is empty
		// if empty, load array and continue
		if (availableWords.size() == 0) {
			loadWordsArray();
		}
		
		// generate random index position of availableWords
		Random rand = new Random();
		int randomWordIndex = rand.nextInt(availableWords.size()) - 1;
		
		// remove and return the random word 
		// to avoid repeated words in same session
		return availableWords.remove(randomWordIndex);
	}
	
	public String getCurrentWord() {
		return currentWord;
	}
	
	public int getNumGuesses() {
		return numGuesses;
	}
	
	public void incNumGuesses() {
		numGuesses += 1;
	}
	
	public void addGuess(Guess guess) {
		guesses.add(guess);
	}
	
	protected  void println(Object message) {
		if (DEV_MODE)
			System.out.println(message);
	}
}
