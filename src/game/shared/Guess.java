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
 * Issues:
 * 
 * Documentation Link:
 * N/A
 */
public class Guess {
	private ArrayList<Map<Integer, Character>> guess;
	
	private static final int NOT_IN_WORD = 0;
	private static final int IN_WRONG_POS = 1;
	private static final int IN_RIGHT_POS = 2;

	public Guess(String currentGuess, String word) {
		guess = new ArrayList<Map<Integer, Character>>();
		ArrayList<Character> wordAsList = getCharListFromString(word);
		
		for (int i = 0; i < currentGuess.length(); i++) {
			char currentChar = currentGuess.charAt(i);
			Map map = new HashMap<Integer, Character>();
			
			// character is not in word
			if (!wordAsList.contains(currentChar)) {
				map.put(NOT_IN_WORD, currentChar);
				guess.add(map);
			} 
			// character is in word but in wrong position
			else if (wordAsList.contains(currentChar) && wordAsList.get(i) != currentChar) {
				map.put(IN_WRONG_POS, currentChar);
				guess.add(map);
			} 
			// character is in word and in right position
			else if (wordAsList.contains(currentChar) && wordAsList.get(i) == currentChar) {
				map.put(IN_RIGHT_POS, currentChar);
				guess.add(map);
			}
		}
	}
	
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
