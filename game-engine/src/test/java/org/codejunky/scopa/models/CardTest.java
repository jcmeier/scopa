package org.codejunky.scopa.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


/**
 * Created by jmeier on 10.06.16.
 */
public class CardTest {

	@Test
	public void testCardsEquals() {
		assertEquals(new Card(CardType.CLUBS, CardValue.EIGHT), new Card(CardType.CLUBS, CardValue.EIGHT));
	}

	@Test
	public void testCardsNotEquals() {
		assertNotEquals(new Card(CardType.CLUBS, CardValue.EIGHT), new Card(CardType.CLUBS, CardValue.FIVE));
	}


}
