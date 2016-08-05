package org.codejunky.scopa.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;



/**
 * Created by jmeier on 10.06.16.
 */
public class DeckTest {
	private Deck objectUnderTest;
	@Before
	public void init() {
		objectUnderTest = new Deck();
	}

	@Test
	public void testDeckInitialised_ok() {
		assertEquals(objectUnderTest.getNumberOfCards(), 40);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeckIllegalArgument() {
		objectUnderTest.draw(41);
	}

}
