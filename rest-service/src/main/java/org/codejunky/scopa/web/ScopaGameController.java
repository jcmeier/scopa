package org.codejunky.scopa.web;

import java.util.List;

import org.codejunky.scopa.game.models.Player;
import org.codejunky.scopa.game.models.Table;
import org.codejunky.scopa.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api")
@RestController
public class ScopaGameController {

	@Autowired
	private Games games;

	@RequestMapping("start_computergame")
	public ComputerGame computerGame() {
		return games.createComputerGame();
	}

	@RequestMapping("play_card")
	public ComputerGame playCard(@RequestParam("gameId") String gameId,
								 @RequestParam("type") CardType type,
								 @RequestParam("value") CardValue value) {
		ComputerGame game = games.getComputerGame(gameId);
		game.putCard(type, value);
		game.computerTurn();
		return game;
	}

}
