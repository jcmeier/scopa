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

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;


/**
 * Created by jan on 14.07.16.
 */
public class IntelligentGameBotTest {

    @Test
    public void testPlayJustFirstCardFromHand() {
        List<Card> cards = new ArrayList<Card>();
        // Cards on the table
        cards.add(new Card(CardType.COINS, CardValue.EIGHT));
        cards.add(new Card(CardType.COINS, CardValue.NINE));
        cards.add(new Card(CardType.COINS, CardValue.TEN));
        cards.add(new Card(CardType.CLUBS, CardValue.EIGHT));

        // First player cards
        cards.add(new Card(CardType.CLUBS, CardValue.ONE));
        cards.add(new Card(CardType.CLUBS, CardValue.FOUR));
        cards.add(new Card(CardType.COINS, CardValue.FOUR));

        // Second player cards
        addSecondPlayerCards(cards);

        TurnResult turnResult = createGameAndPlay(cards);

        assertEquals(new Card(CardType.CLUBS, CardValue.ONE), turnResult.getPlayedCard());
        assertTrue(turnResult.getEarnedCards().isEmpty());
    }

    @Test
    public void testPlayMatchingCard() {
        List<Card> cards = new ArrayList<Card>();
        // Cards on the table
        addCardsOnTable(cards);

        // First player cards
        cards.add(new Card(CardType.CLUBS, CardValue.SIX));
        cards.add(new Card(CardType.CLUBS, CardValue.SEVEN));
        cards.add(new Card(CardType.CLUBS, CardValue.FIVE));

        // Second player cards
        addSecondPlayerCards(cards);

        TurnResult turnResult = createGameAndPlay(cards);

        assertEquals(new Card(CardType.CLUBS, CardValue.FIVE), turnResult.getPlayedCard());

    }

    @Test
    public void testPlayAndPreferGoldCard() {
        List<Card> cards = new ArrayList<Card>();
        // Cards on the table
        addCardsOnTable(cards);

        // First player cards
        cards.add(new Card(CardType.CLUBS, CardValue.SIX));
        cards.add(new Card(CardType.CLUBS, CardValue.FIVE));
        cards.add(new Card(CardType.COINS, CardValue.FIVE));

        // Second player cards
        addSecondPlayerCards(cards);

        TurnResult turnResult = createGameAndPlay(cards);

        assertEquals(new Card(CardType.COINS, CardValue.FIVE), turnResult.getPlayedCard());

    }


    @Test
    public void testEarnGoldenSevenFromHand() {
        List<Card> cards = new ArrayList<Card>();
        // Cards on the table
        cards.add(new Card(CardType.CLUBS, CardValue.SIX));
        cards.add(new Card(CardType.CLUBS, CardValue.FIVE));
        cards.add(new Card(CardType.CLUBS, CardValue.SEVEN));
        cards.add(new Card(CardType.CLUBS, CardValue.FOUR));

        // First player cards
        cards.add(new Card(CardType.COINS, CardValue.SIX));
        cards.add(new Card(CardType.CLUBS, CardValue.FIVE));
        cards.add(new Card(CardType.COINS, CardValue.SEVEN));

        // Second player cards
        addSecondPlayerCards(cards);

        TurnResult turnResult = createGameAndPlay(cards);

        assertEquals(new Card(CardType.COINS, CardValue.SEVEN), turnResult.getPlayedCard());

    }

    @Test
    public void testEarnGoldenSevenFromTable() {
        List<Card> cards = new ArrayList<Card>();
        // Cards on the table
        cards.add(new Card(CardType.CLUBS, CardValue.SIX));
        cards.add(new Card(CardType.CLUBS, CardValue.FIVE));
        cards.add(new Card(CardType.COINS, CardValue.SEVEN));
        cards.add(new Card(CardType.CLUBS, CardValue.FOUR));

        // First player cards
        cards.add(new Card(CardType.COINS, CardValue.SIX));
        cards.add(new Card(CardType.CLUBS, CardValue.FIVE));
        cards.add(new Card(CardType.CLUBS, CardValue.SEVEN));

        // Second player cards
        addSecondPlayerCards(cards);

        TurnResult turnResult = createGameAndPlay(cards);

        assertEquals(new Card(CardType.CLUBS, CardValue.SEVEN), turnResult.getPlayedCard());
        assertTrue(turnResult.getEarnedCards().contains(new Card(CardType.COINS, CardValue.SEVEN)));
        assertEquals(2, turnResult.getEarnedCards().size());

    }

    private void addSecondPlayerCards(List<Card> cards) {
        cards.add(new Card(CardType.COINS, CardValue.ONE));
        cards.add(new Card(CardType.COINS, CardValue.TWO));
        cards.add(new Card(CardType.COINS, CardValue.THREE));
    }


    private void addCardsOnTable(List<Card> cards) {
        cards.add(new Card(CardType.CLUBS, CardValue.FIVE));
        cards.add(new Card(CardType.CLUBS, CardValue.TWO));
        cards.add(new Card(CardType.CLUBS, CardValue.THREE));
        cards.add(new Card(CardType.CLUBS, CardValue.FOUR));
    }

    private TurnResult createGameAndPlay(List<Card> cards) {
        Deck deck = new Deck(cards);
        Game game = new Game(NumPlayers.TWO, deck);
        IntelligentGameBot intellgentBot = new IntelligentGameBot(game, 0);
        return intellgentBot.play();
    }
}
