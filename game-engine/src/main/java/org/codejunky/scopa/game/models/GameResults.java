package org.codejunky.scopa.game.models;

/**
 * Created by jmeier on 30.06.16.
 */
public class GameResults {
	private Result[] results;
	private int winner = -1;

	public GameResults(Result[] results) {
		this.results = results;
		calculateWinner();
	}

	private void calculateWinner() {
		int[] num_points = new int[results.length];

		int max_cards_player = -1;
		int max_cards = 0;

		int max_golden_cards_player = -1;
		long max_golden_cards = 0;

		for(int i=0; i<results.length; i++) {

			// Get the card winner
			int numCards = results[i].getNumCards();
			if(numCards > max_cards) {
				max_cards_player = i;
				max_cards = numCards;
			} else if(numCards == max_cards) {
				max_cards_player = -1;
				max_cards = numCards;
			}

			long numGold = results[i].getCountCoins();
			if(numGold > max_golden_cards) {
				max_golden_cards_player = i;
				max_golden_cards = numGold;

			} else if(numGold == max_golden_cards) {
				max_golden_cards_player = -1;
				max_golden_cards = numGold;
			}


			num_points[i] += results[i].getNumScopa();
			num_points[i] += results[i].isGoldenSeven() ? 1 : 0;


		}
		if(max_cards_player > -1) {
			num_points[max_cards_player] += 1;
		}

		if(max_golden_cards_player > -1) {
			num_points[max_golden_cards_player] += 1;
		}

		int max_points = -1;
		for(int i=0; i<num_points.length; i++) {
			results[i].setNumPoints(num_points[i]);
			if(num_points[i] > max_points) {
				max_points = num_points[i];
				winner = i;
			} else if(num_points[i] == max_points) {
				winner = -1;
			}
		}


	}

	public Result[] getResults() {
		return results;
	}


	public int getWinner() {
		return winner;
	}
}
