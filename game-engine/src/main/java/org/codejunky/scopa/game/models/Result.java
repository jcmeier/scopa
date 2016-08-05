package org.codejunky.scopa.game.models;

/**
 * Created by jmeier on 30.06.16.
 */
public class Result {
	private int numScopa;
	private long countCoins;
	private int numCards;
	private boolean goldenSeven;
	private int numPoints;

	public long getCountCoins() {
		return countCoins;
	}


	public void setCountCoins(long countCoins) {
		this.countCoins = countCoins;
	}


	public int getNumScopa() {
		return numScopa;
	}


	public void setNumScopa(int numScopa) {
		this.numScopa = numScopa;
	}


	public int getNumCards() {
		return numCards;
	}


	public void setNumCards(int numCards) {
		this.numCards = numCards;
	}


	public void setGoldenSeven(boolean goldenSeven) {
		this.goldenSeven = goldenSeven;
	}


	public boolean isGoldenSeven() {
		return goldenSeven;
	}


	public int getNumPoints() {
		return numPoints;
	}


	public void setNumPoints(int numPoints) {
		this.numPoints = numPoints;
	}
}
