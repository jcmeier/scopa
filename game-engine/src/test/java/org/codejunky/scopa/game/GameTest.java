package org.codejunky.scopa.game;

import org.codejunky.scopa.game.Game;
import org.codejunky.scopa.game.models.NumPlayers;
import org.codejunky.scopa.game.models.Player;
import org.codejunky.scopa.game.models.Table;
import org.codejunky.scopa.models.Card;
import org.codejunky.scopa.models.CardType;
import org.codejunky.scopa.models.CardValue;
import org.codejunky.scopa.models.Deck;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;



/**
 * Created by jmeier on 10.06.16.
 */
public class GameTest {
	private Game objectUnderTest;
	private Player player;

	@Before
	public void init() {
		List<Card> cards = new ArrayList<Card>();
		cards.add(new Card(CardType.CLUBS, CardValue.ONE));
		cards.add(new Card(CardType.CLUBS, CardValue.ONE));
		cards.add(new Card(CardType.CLUBS, CardValue.ONE));
		cards.add(new Card(CardType.CLUBS, CardValue.ONE));

		// First hand
		cards.add(new Card(CardType.CLUBS, CardValue.FOUR));
		cards.add(new Card(CardType.CLUBS, CardValue.THREE));
		cards.add(new Card(CardType.CLUBS, CardValue.FIVE));

		// Second hand
		cards.add(new Card(CardType.CLUBS, CardValue.THREE));
		cards.add(new Card(CardType.CLUBS, CardValue.ONE));
		cards.add(new Card(CardType.CLUBS, CardValue.ONE));

		Deck deck = new Deck(cards);
		objectUnderTest = new Game(NumPlayers.TWO, deck);
		player = objectUnderTest.getPlayers().get(0);
	}

	@Test
	public void testGameInitialisedCorrectly() {
		assertEquals(2, objectUnderTest.getPlayers().size());
		assertEquals(4, objectUnderTest.getTable().getCards().size());
		assertEquals(3, objectUnderTest.getPlayers().get(0).getCards().size());
		assertEquals(3, objectUnderTest.getPlayers().get(1).getCards().size());
		assertEquals(0, objectUnderTest.getPlayers().get(0).getStack().size());
		assertEquals(0, objectUnderTest.getPlayers().get(1).getStack().size());
	}

	@Test
	public void testTableInitialisedCorrectly() {
		Table table = objectUnderTest.getTable();
		assertEquals(4, table.getCards().size());


	}

	@Test
	public void testPlayCardAndScopa() {
		objectUnderTest.putCard(player, new Card(CardType.CLUBS, CardValue.FOUR));

		assertEquals(1, player.getNumScopa());
		assertEquals(5, player.getStack().size());
	}

	@Test
	public void testPlayCardAndGetThree() {
		objectUnderTest.putCard(player, new Card(CardType.CLUBS, CardValue.THREE));

		assertEquals(0, player.getNumScopa());
		assertEquals(2, player.getCards().size());
		assertEquals(4, player.getStack().size());
		assertEquals(1, objectUnderTest.getTable().getCards().size());
	}

	@Test
	public void testPlayCardAndGetNone() {
		objectUnderTest.putCard(player, new Card(CardType.CLUBS, CardValue.FIVE));

		assertEquals(0, player.getNumScopa());
		assertEquals(0, player.getStack().size());
		assertEquals(2, player.getCards().size());
		assertEquals(5, objectUnderTest.getTable().getCards().size());
	}

	@Test(expected = IllegalStateException.class)
	public void testPlayingTwoTimesSamePlayer() {
		objectUnderTest.putCard(player, new Card(CardType.CLUBS, CardValue.FOUR));
		objectUnderTest.putCard(player, new Card(CardType.CLUBS, CardValue.THREE));

	}

	@Test
	public void testGameIsFinished() {
		Player player1 = objectUnderTest.getPlayers().get(0);
		Player player2 = objectUnderTest.getPlayers().get(1);

		objectUnderTest.putCard(player1, new Card(CardType.CLUBS, CardValue.FOUR));
		objectUnderTest.putCard(player2, new Card(CardType.CLUBS, CardValue.THREE));

		objectUnderTest.putCard(player1, new Card(CardType.CLUBS, CardValue.THREE));
		objectUnderTest.putCard(player2, new Card(CardType.CLUBS, CardValue.ONE));

		objectUnderTest.putCard(player1, new Card(CardType.CLUBS, CardValue.FIVE));
		objectUnderTest.putCard(player2, new Card(CardType.CLUBS, CardValue.ONE));

		assertTrue(objectUnderTest.isFinished());
	}

}
