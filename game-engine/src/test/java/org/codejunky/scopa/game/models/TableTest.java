package org.codejunky.scopa.game.models;

import org.codejunky.scopa.models.Card;
import org.codejunky.scopa.models.CardType;
import org.codejunky.scopa.models.CardValue;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;



/**
 * Created by jmeier on 10.06.16.
 */
public class TableTest {
	private Table objectUnderTest;

	@Before
	public void init() {
		List<Card> cards = new LinkedList<Card>();
		// Cards for the table
		cards.add(new Card(CardType.CLUBS, CardValue.ONE));
		cards.add(new Card(CardType.CLUBS, CardValue.ONE));
		cards.add(new Card(CardType.CLUBS, CardValue.ONE));
		cards.add(new Card(CardType.CLUBS, CardValue.ONE));

		objectUnderTest = new Table(cards);
	}

	@Test
	public void testTablePutCardEmpty() {
		List<Card> cardsFromTable = objectUnderTest.putCard(new Card(CardType.CLUBS, CardValue.FOUR));

		assertTrue(objectUnderTest.isEmpty());
		assertEquals(5, cardsFromTable.size());
	}

	@Test
	public void testTablePutCardAdded() {
		objectUnderTest.putCard(new Card(CardType.CLUBS, CardValue.FIVE));
		assertEquals(5, objectUnderTest.getCards().size());
	}

	@Test
	public void testTablePutCardAndGetPartOfIt() {
		Card card = new Card(CardType.CLUBS, CardValue.THREE);
		List<Card> cardsFromTable = objectUnderTest.putCard(card);

		assertTrue(cardsFromTable.contains(card));
		assertEquals(4, cardsFromTable.size());
		assertEquals(1, objectUnderTest.getCards().size());
	}

	@Test
	public void testTakeOneOfTwo() {
		List<Card> cards = new LinkedList<Card>();
		// Cards for the table
		cards.add(new Card(CardType.CUPS, CardValue.ONE));
		cards.add(new Card(CardType.CLUBS, CardValue.THREE));
		cards.add(new Card(CardType.CLUBS, CardValue.FOUR));
		cards.add(new Card(CardType.COINS, CardValue.FOUR));

		Table table = new Table(cards);

		List<Card> returnedCards = table.putCard(new Card(CardType.SWORDS, CardValue.FOUR));
		assertEquals(2, returnedCards.size());
		Card card = returnedCards.get(0);
		assertEquals(CardValue.FOUR, card.getValue());
		card = returnedCards.get(1);
		assertEquals(CardValue.FOUR, card.getValue());
	}

	@Test
	public void testTakeTwoFourAndSix() {
		Card cardSix = new Card(CardType.COINS, CardValue.SIX);
        Card cardOne = new Card(CardType.CUPS, CardValue.ONE);
        Card cardFive = new Card(CardType.CLUBS, CardValue.FIVE);

        List<Card> cards = new LinkedList<Card>();
		// Cards for the table
		cards.add(new Card(CardType.SWORDS, CardValue.FOUR));
		cards.add(cardOne);
        cards.add(new Card(CardType.CUPS, CardValue.SEVEN));
        cards.add(cardFive);
		cards.add(new Card(CardType.CUPS, CardValue.SEVEN));

		Table table = new Table(cards);


		List<Card> returnedCards = table.putCard(cardSix);
		assertEquals(3, returnedCards.size());
        assertTrue(returnedCards.contains(cardSix));
        assertTrue(returnedCards.contains(cardOne));
        assertTrue(returnedCards.contains(cardFive));
	}


	@Test
	public void testCombinationsJustTwo() {
		List<Card> cards = new LinkedList<Card>();
		cards.add(new Card(CardType.CUPS, CardValue.ONE));
		cards.add(new Card(CardType.CUPS, CardValue.TWO));
		Table table = new Table(cards);
		List<List<Card>> combinations = table.getCombinations();
		assertEquals(2, combinations.size());
	}

	@Test
	public void testCombinationsThree() {
		List<Card> cards = new LinkedList<Card>();
		cards.add(new Card(CardType.CUPS, CardValue.ONE));
		cards.add(new Card(CardType.CUPS, CardValue.TWO));
		cards.add(new Card(CardType.CUPS, CardValue.THREE));
		Table table = new Table(cards);
		List<List<Card>> combinations = table.getCombinations();
		assertEquals(9, combinations.size());
	}
}
