package org.codejunky.scopa.game.models;

import java.util.ArrayList;
import java.util.List;

import org.codejunky.scopa.models.Card;



/**
 * A Scopa game player with three cards
 * 
 * @author jan
 *
 */
public class Player {
	private String name;
	private List<Card> hand;
	private List<Card> stack;
	private int numScopa = 0;

	public Player(String name, List<Card> hand) {
		this.name = name;
		this.hand = hand;
		this.stack = new ArrayList<Card>();
	}

	public List<Card> getCards() {
		return hand;
	}

	public void setCards(List<Card> cards) {
		hand = cards;
	}

	public void playCard(Card card) {
		hand.remove(card);

	}

	public String toString() {
		String result = "Player " + name + "\n";
		short i = 0;
		for (Card card : hand) {
			result += i + ": " + card + "\n";
			i++;
		}

		result += "Stack: \n";
		for (Card card : stack) {
			result += card.toString() + "\n";
		}

		return result;
	}

	/**
	 * Check whether the player has the cad
	 * 
	 * @param card
	 *            to check
	 * @return true or false whether the player has the card
	 */
	public boolean hasCard(Card card) {
		return hand.contains(card);
	}

	/**
	 * Removes a card from the player
	 * 
	 * @param card
	 *            to remove from the player
	 */
	public void removeCard(Card card) {
		hand.remove(card);
	}

	/**
	 * Adds the list of cards to the stack of player
	 * 
	 * @param cards
	 *            to add to the stack of the player
	 */
	public void addCardsToStack(List<Card> cards) {
		stack.addAll(cards);
	}

	/**
	 * Increments the number of scopas the user has earned
	 */
	public void addScopa() {
		numScopa++;
	}

	/**
	 * Returns the number of scopas the user has earned.
	 * 
	 * @return number of scopas the user has earned
	 */
	public int getNumScopa() {
		return numScopa;
	}

	/**
	 * Returns whether the hand of the player is empty
	 * 
	 * @return true when the hand is empty, false otherwise
	 */
	public boolean isHandEmpty() {
		return hand.isEmpty();
	}

	public String getName() {
		return name;
	}


	public List<Card> getStack() {
		return stack;
	}

}
