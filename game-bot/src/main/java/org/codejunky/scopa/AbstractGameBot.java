package org.codejunky.scopa;

import org.codejunky.scopa.game.Game;
import org.codejunky.scopa.game.models.Player;
import org.codejunky.scopa.game.models.TurnResult;
import org.codejunky.scopa.models.Card;

import java.util.List;



/**
 * Created by jmeier on 15.07.16.
 */
public abstract class AbstractGameBot implements GameBot {

	protected Game game;
	protected int playerId;

	protected List<Card> cardsOnHand;

	public AbstractGameBot(Game game, int playerId) {
		this.game = game;
		this.playerId = playerId;
	}

	public TurnResult play() {
		Player player = game.getPlayers().get(playerId);
		cardsOnHand = player.getCards();

		if(cardsOnHand.size() > 0) {
			return game.putCard(player, selectCard());
		}

		throw new IllegalStateException("Computer has no remaining card");
	}


	/**
	 * Returns the card which should be played by the Gamebot
	 * @return
	 */
	protected abstract Card selectCard();
}
