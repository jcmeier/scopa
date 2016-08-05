package org.codejunky.scopa.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the full deck of a scopa game. It has 40 Cards.
 * 
 * @author jan
 *
 */
public class Deck {
	private List<Card> cards = new ArrayList<Card>(40);

	/**
	 * Creates a new deck containing 40 cards
	 */
	public Deck() {
		for (CardType cardType : CardType.values()) {

			for (CardValue cardValue : CardValue.values()) {
				cards.add(new Card(cardType, cardValue));
			}

		}
		Collections.shuffle(cards);
	}

	public Deck(List<Card> cards) {
		this.cards = cards;
	}

	public String toString() {
		String result = "";
		for (Card card : cards) {
			result += card.toString() + "\n";
		}
		return result;
	}

	/**
	 * Returns whether the deck is empty
	 * 
	 * @return true when the deck is empty otherwise false
	 */
	public boolean isEmpty() {
		return cards.isEmpty();
	}

	/**
	 * Draws the topmost card from the deck
	 * 
	 * @return the topmost card from the deck
	 */
	public List<Card> draw(int numberOfCards) {
		if(numberOfCards > 0 && numberOfCards > cards.size()) {
			throw new IllegalArgumentException("Number of cards should not below zero and not bigger than remaining cards");
		}
		List<Card> cardsToReturn = new ArrayList<Card>();
		for (int i = 0; i < numberOfCards; i++) {
			cardsToReturn.add(cards.remove(0));
		}
		return cardsToReturn;
	}


	/**
	 * Returns the number of cards in the deck
	 * 
	 * @return the number of cards in the deck
	 */
	public int getNumberOfCards() {
		return cards.size();
	}
}
