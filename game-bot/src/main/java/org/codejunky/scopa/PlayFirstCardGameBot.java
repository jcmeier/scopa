package org.codejunky.scopa;
import org.codejunky.scopa.game.Game;
import org.codejunky.scopa.game.models.Player;
import org.codejunky.scopa.game.models.TurnResult;
import org.codejunky.scopa.models.Card;

import java.util.List;
import java.util.Optional;


/**
 * Created by jmeier on 28.06.16.
 */
public class PlayFirstCardGameBot extends AbstractGameBot implements GameBot {

	public PlayFirstCardGameBot(Game game, int playerId) {
		super(game, playerId);
	}


	@Override
	protected Card selectCard() {
		return cardsOnHand.get(0);
	}
}
