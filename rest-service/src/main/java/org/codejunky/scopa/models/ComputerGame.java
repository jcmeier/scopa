package org.codejunky.scopa.models;

import java.util.List;

import org.apache.log4j.Logger;
import org.codejunky.scopa.GameBot;
import org.codejunky.scopa.IntelligentGameBot;
import org.codejunky.scopa.PlayFirstCardGameBot;
import org.codejunky.scopa.game.Game;
import org.codejunky.scopa.game.models.*;


public class ComputerGame {
    private static final Logger logger = Logger.getLogger(ComputerGame.class);

    private Game game;
	private String gameId;
	private TurnResult computerTurn;
	private TurnResult userTurn;

	public ComputerGame() {
		game = new Game(NumPlayers.TWO);
		gameId = RandomStringGenerator.getRandomString();
	}

	public void putCard(CardType type, CardValue value) {
		userTurn = game.putCard(game.getPlayers().get(0), new Card(type, value));

	}

	public void computerTurn() {
		GameBot gameBot = new IntelligentGameBot(game, 1);
		computerTurn = gameBot.play();
	}

	public String getGameId() {
		return gameId;
	}


	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public List<Card> getHand() {
		return game.getPlayers().get(0).getCards();
	}

	public int getNumberOfCardsOpponent() {
		return game.getPlayers().get(1).getCards().size();
	}

	public List<Card> getTable() {
		return game.getTable().getCards();
	}

	public List<Card> getStack() {
		return game.getPlayers().get(0).getStack();
	}

	public TurnResult getComputerTurn() {
		return computerTurn;
	}

	public boolean isFinished() {
		return game.isFinished();
	}

	public GameResults getResults() {
		return game.getResults();
	}

	public TurnResult getUserTurn() {
		return userTurn;
	}
}
