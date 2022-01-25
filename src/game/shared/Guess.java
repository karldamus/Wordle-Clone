// Author: Karl Damus
// Date Created: Jan 24, 2022

package game.shared;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Purpose:
 * Create an array list of maps (integer => character)
 *      where the integer meanings are as follows:
 *          <li>0 => character is not in the word</li>
 *          <li>1 => character is in the word but not that spot</li>
 *          <li>2 => character is in the word and in that spot</li>
 * <br>         
 * Issues:
 *      <li>first-check superiority ... see to-do</li>
 */
public class Guess {
	private ArrayList<Map<Integer, Character>> guess;
	
	private static final int NOT_IN_WORD = 0;
	private static final int IN_WRONG_POS = 1;
	private static final int IN_RIGHT_POS = 2;

	/**
	 * Create a Guess object based on the correct word
	 * @param currentGuess the String value of the user's guess
	 * @param word the current word that needs to be guessed
	 */
	public Guess(String currentGuess, String word) {
		guess = new ArrayList<Map<Integer, Character>>(); // initialize the guess ArrayList
		ArrayList<Character> wordAsList = getCharListFromString(word); // get charList from the word to guess

		/**
		 * Loop through each character in the currentGuess
		 */
		for (int i = 0; i < currentGuess.length(); i++) {
			char currentChar = currentGuess.charAt(i); // get character at index i
			Map<Integer, Character> map = new HashMap<>(); // initialize a new Map for the guess ArrayList
			
			// CASE 0: if character is not in word
			if (!wordAsList.contains(currentChar)) {
				map.put(NOT_IN_WORD, currentChar);
				guess.add(map);
			} 
			// CASE 1: if character is in word but in wrong position
			else if (wordAsList.contains(currentChar) && wordAsList.get(i) != currentChar) {
				// duplicate letter check
				int duplicates = 0;
				int numGuessesOfSameLetter = 0;
				
				// check how many duplicates are in the word
				for (Character c : wordAsList) {
					if (String.valueOf(c).equalsIgnoreCase(String.valueOf(currentChar)))
						duplicates++;
				}
				// check how many times the character has been guessed
				for (int k = 0; k < guess.size(); k++) {
					if (guess.get(k).containsValue(currentChar))
						numGuessesOfSameLetter++;
				}
				
				// TODO: check duplicates after to avoid first-check superiority
				
				// if number of guesses hasn't reached the 
				// total number of duplicates
				if (numGuessesOfSameLetter < duplicates) {
					map.put(IN_WRONG_POS, currentChar);
					guess.add(map);
				} 
				// else, duplicates are all filled up
				else {
					map.put(NOT_IN_WORD, currentChar);
					guess.add(map);
				}
			} 
			// CASE 2: if character is in word and in right position
			else if (wordAsList.contains(currentChar) && wordAsList.get(i) == currentChar) {
				map.put(IN_RIGHT_POS, currentChar);
				guess.add(map);
			}
		}
	}

	/**
	 * Given a String, generate an ArrayList of Characters
	 * @param s the input String
	 * @return ArrayList of Characters from the input String
	 */
	public ArrayList<Character> getCharListFromString(String s) {
		ArrayList<Character> chars = new ArrayList<>();
		
		for (char c : s.toCharArray()) {
			chars.add(c);
		}
		
		return chars;
	}
	
	public ArrayList<Map<Integer, Character>> getGuess() {
		return guess;
	}
}
