package org.codejunky.scopa.game.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.codejunky.scopa.models.Card;


/**
 * Represents the table of the game which holds 4 cards
 *
 * @author jan
 */
public class Table {
    private List<Card> cards;

    public Table(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public String toString() {
        String result = "--- TABLE ---\n";
        for (Card card : cards) {
            result += card + "\n";
        }
        return result;
    }

    /**
     * Puts a card on the table
     *
     * @param card the card to put on the table
     * @return list of cards which are return from the table
     */
    public List<Card> putCard(Card card) {
        // There is no matching card on the table. So we try to find a
        // combination that matches the value of the card.
        List<Card> result = findCardsFromTableForCard(card);

        if (result.isEmpty()) {
            // No matching cards found. We will put the card on the table
            cards.add(card);
        } else {
            // We have to add the card which got played to the result
            result.add(card);

            // There has been a matching card found. Remove it from the table.
            for (Card cardToRemove : result) {
                cards.remove(cardToRemove);

            }
        }

        return result;

    }

    /**
     * Returns a list of cards matching the card on the hand
     *
     * @param card
     * @return
     */
    public List<Card> findCardsFromTableForCard(Card card) {
        List<Card> result = new ArrayList<Card>();

        // First check if there is a matching card
        for (Card tableCard : cards) {
            if (tableCard.getValue() == card.getValue()) {
                result.add(tableCard);
                // Matching card is found so we abort
                break;
            }
        }

        if(result.isEmpty()) {
            result = findBestCombination(card, getCombinations());
        }

        return result;

    }

    /**
     * Returns all combinations of the cards on the table
     *
     * @return all combinations of the cards on the table
     */
    protected List<List<Card>> getCombinations() {
        List<List<Card>> combinations = new ArrayList<List<Card>>();
        for (int i = 0; i < cards.size(); i++) {
            List<Card> tmp = new ArrayList<Card>(cards);
            Card baseCard = tmp.remove(i);
            for (int j = 0; j < tmp.size(); j++) {
                for (int k = j; k < tmp.size(); k++) {
                    List<Card> subList = new ArrayList<Card>(tmp).subList(j, k + 1);
                    subList.add(baseCard);
                    combinations.add(subList);
                }
            }

        }
        return combinations;
    }

    /**
     * Finds the best combination which matches the cards. The best combination
     * is when the number of cards is maximum.
     *
     * @param card         the card to match
     * @param combinations all the combinations of the cards on the table.
     * @return the best matching combination. With the most cards
     */
    private List<Card> findBestCombination(Card card, List<List<Card>> combinations) {
        List<Card> result = new ArrayList<Card>();
        for (List<Card> combination : combinations) {
            int sum = 0;
            for (Card c : combination) {
                sum += c.getValue().getNumber();
            }
            if (sum == card.getValue().getNumber() && combination.size() > result.size()) {
                result = combination;
            }

        }
        return result;
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public void clear() {
        cards.clear();
    }
}
