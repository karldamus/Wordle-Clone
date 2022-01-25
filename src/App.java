// Author: Karl Damus
// Date Created: Jan 24, 2022

import game.Game;

import java.io.IOException;

/**
 * Purpose:
 * quick blurb
 * Issues:
 * - issue 1
 * Documentation Link:
 * N/A
 */
public class App {
	public static void main(String[] args) throws IOException {
		Game game = new Game();
	}

	protected static void printf(String format, Object... args) {
		System.out.printf(format, args);
	}

	protected static void println(Object message) {
		System.out.println(message);
	}

	protected static void print(Object message) {
		System.out.print(message);
	}
}
