package org.codejunky.scopa.web;

import org.codejunky.scopa.models.ComputerGame;
import org.codejunky.scopa.models.Games;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.TestCase.assertNotNull;



/**
 * Created by jmeier on 15.07.16.
 */
public class ScopaGameControllerTest {

	@InjectMocks
	ScopaGameController scopaGameController;

	@Mock
	Games games;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateNewGame() {

		ComputerGame computerGame = scopaGameController.computerGame();

		assertNotNull(computerGame);

	}

}
