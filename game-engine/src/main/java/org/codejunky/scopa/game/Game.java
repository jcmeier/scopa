package org.codejunky.scopa.game;

import java.util.ArrayList;
import java.util.List;

import org.codejunky.scopa.game.models.*;
import org.codejunky.scopa.models.Card;
import org.codejunky.scopa.models.CardType;
import org.codejunky.scopa.models.CardValue;
import org.codejunky.scopa.models.Deck;



/**
 * A scopa game.
 * 
 * @author jan
 *
 */
public class Game {
	private List<Player> players;
	private Deck deck;
	private Table table;
	private int currentPlayer = 0;

	/**
	 * Initialise the Game with 2 or 4 players
	 *
	 * @param numPlayers
	 *            The number of players, which could be two or four
	 */
	public Game(NumPlayers numPlayers) {
		this(numPlayers, new Deck());
	}

	/**
	 * Initialise the Game with 2 or 4 players
	 * 
	 * @param numPlayers
	 *            The number of players, which could be two or four
	 */
	public Game(NumPlayers numPlayers, Deck deck) {
		this.deck = deck;
		table = new Table(deck.draw(4));
		players = new ArrayList<Player>(numPlayers.getValue());

		for (short i = 0; i < numPlayers.getValue(); i++) {
			players.add(new Player(String.valueOf(i), deck.draw(3)));
		}
	}

	/**
	 * Puts a card on the table
	 * 
	 * @param player
	 *            the player who puts the card
	 * @param card
	 *            the card which the users puts
	 */
	public TurnResult putCard(Player player, Card card) {
		if (!player.hasCard(card)) {
			throw new IllegalStateException("Player does not have the card");
		}

		if (players.indexOf(player) != currentPlayer) {
			throw new IllegalStateException("It is not the players turn");
		}

		// Remove the card the user played.
		player.removeCard(card);

		// Put the card on the table and earn some cards.
		List<Card> earnedCards = table.putCard(card);

		// Add the earned cards to the stack of the user.
		player.addCardsToStack(earnedCards);

		// When the table is empty after playing then the user scores a scopa.
		if (table.isEmpty()) {
			player.addScopa();
		}
		nextRound();
		return new TurnResult(card, earnedCards);
	}

	/**
	 * Returns all players of the game
	 * 
	 * @return the list with players of the game.
	 */
	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * Returns the table
	 * 
	 * @return the table of the game
	 */
	public Table getTable() {
		return table;
	}

	/**
	 * Returns whether the game is finished
	 * 
	 * @return true when the game is finished otherwise false
	 */
	public boolean isFinished() {
		boolean finished = deck.isEmpty();
		for (Player player: players) {
			finished &= player.getCards().isEmpty();
		}
		return finished;
	}


	/**
	 * Starts the next round of the game. All players get new cards
	 */
	private void nextRound() {
		if(isFinished()) {
			// The game is finished get all cards from the table
			players.get(0).addCardsToStack(table.getCards());
            table.clear();
            
		} else {
			for (Player player : players) {
				if (player.isHandEmpty() && !deck.isEmpty()) {
					player.setCards(deck.draw(3));
				}
			}
			if (currentPlayer == players.size() - 1) {
				currentPlayer = 0;

			}
			else {
				currentPlayer++;

			}
		}
	}


	/**
	 * Return the number of cards remaining
	 * 
	 * @return the number of cards remaining
	 */
	public int numberOfCardsRemaining() {
		return deck.getNumberOfCards();
	}

	public GameResults getResults() {
		Result[] gameResults = new Result[2];
		gameResults[0] = new Result();
		gameResults[1] = new Result();


		for(int i=0; i<players.size(); i++) {
			// Set the number of scopas
			gameResults[i].setNumScopa(players.get(i).getNumScopa());

			// Set the number of cards
			gameResults[i].setNumCards(players.get(i).getStack().size());


			// Set the number of coins
			long countCoins = players.get(i).getStack().stream().filter(card -> card.getType().equals(CardType.COINS)).count();
			gameResults[i].setCountCoins(countCoins);

			// Check who has the golden seven
			if(players.get(i).getStack().contains(new Card(CardType.COINS, CardValue.SEVEN))) {
				gameResults[i].setGoldenSeven(true);
			}
		}

		return new GameResults(gameResults);
	}
}
