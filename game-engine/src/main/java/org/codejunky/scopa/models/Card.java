package org.codejunky.scopa.models;

/**
 * This class Represents a card in the scopa game.
 * 
 * @author jan
 *
 */
public class Card {
	public static final Card GOLDEN_SEVEN = new Card(CardType.COINS, CardValue.SEVEN);

	private final CardType type;
	private final CardValue value;

	public Card(CardType type, CardValue value) {
		this.type = type;
		this.value = value;
	}

	public CardType getType() {
		return type;
	}

	public CardValue getValue() {
		return value;
	}

	public String toString() {
		return type.toString() + " -- " + value.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (!(obj instanceof Card)) {
			return false;
		}

		Card other = (Card) obj;
		return other.getValue() == value && other.getType() == type;
	}

	public boolean isGoldenSeven() {
		return equals(GOLDEN_SEVEN);
	}
}
