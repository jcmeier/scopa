package org.codejunky.scopa;

import org.codejunky.scopa.game.Game;
import org.codejunky.scopa.game.models.NumPlayers;
import org.codejunky.scopa.game.models.TurnResult;
import org.codejunky.scopa.models.Card;
import org.codejunky.scopa.models.CardType;
import org.codejunky.scopa.models.CardValue;
import org.codejunky.scopa.models.Deck;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by jan on 14.07.16.
 */
public class PlayFirstCardGameBotTest {

    @Test
    public void testPlayCard() {
        List<Card> cards = new ArrayList<Card>();
        // Cards on the table
        cards.add(new Card(CardType.CLUBS, CardValue.ONE));
        cards.add(new Card(CardType.CLUBS, CardValue.TWO));
        cards.add(new Card(CardType.CLUBS, CardValue.THREE));
        cards.add(new Card(CardType.CLUBS, CardValue.FOUR));

        // First player cards
        cards.add(new Card(CardType.CLUBS, CardValue.FIVE));
        cards.add(new Card(CardType.CLUBS, CardValue.SIX));
        cards.add(new Card(CardType.CLUBS, CardValue.SEVEN));

        // Second player cards
        cards.add(new Card(CardType.COINS, CardValue.ONE));
        cards.add(new Card(CardType.COINS, CardValue.TWO));
        cards.add(new Card(CardType.COINS, CardValue.THREE));

        Deck deck = new Deck(cards);
        Game game = new Game(NumPlayers.TWO, deck);
        PlayFirstCardGameBot playFirstCardGameBot = new PlayFirstCardGameBot(game, 0);
        TurnResult turnResult = playFirstCardGameBot.play();

        assertEquals(new Card(CardType.CLUBS, CardValue.FIVE), turnResult.getPlayedCard());

    }
}
