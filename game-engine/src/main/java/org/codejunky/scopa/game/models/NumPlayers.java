package org.codejunky.scopa.game.models;

/**
 * Number of players. In scopa there are two or four allowed
 * 
 * @author jan
 *
 */
public enum NumPlayers {
	TWO(2), FOUR(4);
	int value;

	private NumPlayers(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
