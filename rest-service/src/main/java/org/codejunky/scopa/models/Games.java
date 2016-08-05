package org.codejunky.scopa.models;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class Games {

	private ConcurrentMap<String, ComputerGame> games = new ConcurrentHashMap<>();

	public ComputerGame createComputerGame() {
		ComputerGame computerGame = new ComputerGame();
		games.put(computerGame.getGameId(), computerGame);
		return computerGame;
	}

	public ComputerGame getComputerGame(String gameId) {
		return games.get(gameId);
	}

}
