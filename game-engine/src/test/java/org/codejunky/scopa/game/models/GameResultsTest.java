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
public class GameResultsTest {

    @Test
	public void testNoWinner() {
        Result[] results = new Result[]{new Result(), new Result()};
        GameResults gameResults = new GameResults(results);
        assertEquals(-1, gameResults.getWinner());

    }

    @Test
    public void testMoreCards() {
        Result result1 = new Result();
        result1.setNumCards(5);

        Result result2 = new Result();
        result2.setNumCards(6);
        Result[] results = new Result[]{result1, result2};
        GameResults gameResults = new GameResults(results);
        assertEquals(1, gameResults.getWinner());

    }

    @Test
    public void testSameNumberOfCards() {
        Result result1 = new Result();
        result1.setNumCards(5);

        Result result2 = new Result();
        result2.setNumCards(5);
        Result[] results = new Result[]{result1, result2};
        GameResults gameResults = new GameResults(results);
        assertEquals(-1, gameResults.getWinner());

    }

    @Test
    public void testMoreScopa() {
        Result result1 = new Result();
        result1.setNumScopa(5);

        Result result2 = new Result();
        result2.setNumScopa(6);
        Result[] results = new Result[]{result1, result2};
        GameResults gameResults = new GameResults(results);
        assertEquals(1, gameResults.getWinner());

    }


    @Test
    public void testSameScopa() {
        Result result1 = new Result();
        result1.setNumScopa(5);

        Result result2 = new Result();
        result2.setNumScopa(5);
        Result[] results = new Result[]{result1, result2};
        GameResults gameResults = new GameResults(results);
        assertEquals(-1, gameResults.getWinner());

    }

    @Test
    public void testGoldenSeven() {
        Result result = new Result();
        result.setGoldenSeven(true);
        Result[] results = new Result[]{new Result(), result};
        GameResults gameResults = new GameResults(results);
        assertEquals(1, gameResults.getWinner());

    }

    @Test
    public void testMostCoins() {
        Result result1 = new Result();
        result1.setCountCoins(5);

        Result result2 = new Result();
        result2.setCountCoins(6);
        Result[] results = new Result[]{result1, result2};

        GameResults gameResults = new GameResults(results);
        assertEquals(1, gameResults.getWinner());

    }

    @Test
    public void testSameCoins() {
        Result result1 = new Result();
        result1.setCountCoins(5);

        Result result2 = new Result();
        result2.setCountCoins(5);
        Result[] results = new Result[]{result1, result2};

        GameResults gameResults = new GameResults(results);
        assertEquals(-1, gameResults.getWinner());

    }


    @Test
    public void testSameResult() {
        Result result1 = new Result();
        result1.setNumCards(20);
        result1.setCountCoins(5);
        result1.setNumScopa(1);

        Result result2 = new Result();
        result2.setNumCards(20);
        result2.setCountCoins(5);
        result2.setNumScopa(1);
        Result[] results = new Result[]{result1, result2};

        GameResults gameResults = new GameResults(results);
        assertEquals(-1, gameResults.getWinner());

    }

    @Test
    public void testPlayer1WinsCards() {
        Result result1 = new Result();
        result1.setNumCards(18);
        result1.setCountCoins(5);
        result1.setNumScopa(1);

        Result result2 = new Result();
        result2.setNumCards(22);
        result2.setCountCoins(5);
        result2.setNumScopa(1);
        Result[] results = new Result[]{result1, result2};

        GameResults gameResults = new GameResults(results);
        assertEquals(1, gameResults.getWinner());
    }

    @Test
    public void testPlayer1WinsGoldenSeven() {
        Result result1 = new Result();
        result1.setNumCards(20);
        result1.setCountCoins(5);
        result1.setNumScopa(1);

        Result result2 = new Result();
        result2.setNumCards(20);
        result2.setCountCoins(5);
        result2.setNumScopa(1);
        result2.setGoldenSeven(true);

        Result[] results = new Result[]{result1, result2};

        GameResults gameResults = new GameResults(results);
        assertEquals(1, gameResults.getWinner());
    }
}
