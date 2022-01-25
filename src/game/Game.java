package game;// Author: Karl Damus
// Date Created: Jan 24, 2022

//import shared.Guess;

import game.shared.Guess;
import static game.shared.Constants.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


/**
 * Purpose:
 * Generate the Wordle Application for user
 * Issues:
 * 
 * Documentation Link:
 * N/A
 */
public class Game {
	/**
	 * CONSTANTS
	 */
//	private  String WORDS_LIST = "src/game.txt/words.game.txt";
	
	private  ArrayList<String> availableWords;
	private  String currentWord;
	private  ArrayList<Guess> guesses;
	private int numGuesses;
	
	public Game() throws IOException {
		setUp();
//		update();
		UI ui = new UI();
		ui.update(this);
	}
	
	public void update() {
		println(currentWord);
		
		while (numGuesses <= NUM_ALLOWED_GUESSES) {
			String currentGuess = requestInput();
			
			if (invalidInput(currentGuess)) {
				alertUser();
				continue;
			}
			
			Guess guess = new Guess(currentGuess, currentWord);
			guesses.add(guess);
			
			numGuesses += 1;
		}
	}
	
	private String requestInput() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter a guess: ");

		String guess = scanner.nextLine();
		
		return guess.toUpperCase();
	}
	
	private void alertUser() {
		System.out.println("ALERT");
	}
	
	public boolean invalidInput(String guess) {
		if (guess.length() != WORD_LENGTH)
			return true;
		
		return false;
	}
	
	private void setUp() throws IOException {
		loadWordsArray();
		currentWord = getWord();
		guesses = new ArrayList<Guess>();
		numGuesses = 0;
	}

	/**
	 * Load the words from file words.game.txt (WORDS_LIST) into availableWords
	 * @throws IOException
	 */
	private void loadWordsArray() throws IOException {
		availableWords = new ArrayList<>();
		BufferedReader r = new BufferedReader(new FileReader(WORDS_LIST));
		
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
