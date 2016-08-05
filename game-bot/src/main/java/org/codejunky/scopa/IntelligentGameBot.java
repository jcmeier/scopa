package org.codejunky.scopa;

import org.codejunky.scopa.game.Game;
import org.codejunky.scopa.game.models.Table;
import org.codejunky.scopa.models.Card;
import org.codejunky.scopa.models.CardType;
import org.codejunky.scopa.models.CardValue;

import java.util.List;
import java.util.stream.Collectors;


/**
 * This is a scopa game bot implementation with a strategy which is implemented in selectCard()
 * <p>
 * Created by jmeier on 15.07.16.
 */
public class IntelligentGameBot extends AbstractGameBot {
    private Table table;

    public IntelligentGameBot(Game game, int playerId) {
        super(game, playerId);
        table = game.getTable();
    }


    @Override
    protected Card selectCard() {

        // Just one card, play it
        if (cardsOnHand.size() == 1) {
            return cardsOnHand.get(0);
        }

        // Strategy: Check if we have the golden card on the and and play it if it earns
        if (hasGoldenSevenOnHand() && goldenSevenEarns()) {
            return Card.GOLDEN_SEVEN;
        }

        // Strategy: Check if the golden seven is on the table and play a seven it is on hand
        if (table.getCards().contains(Card.GOLDEN_SEVEN)) {
            List<Card> sevenValueCards = getCardsWithValueSeven();
            if (!sevenValueCards.isEmpty()) {
                return sevenValueCards.get(0);
            }
        }

        Card matchingCard = findMatchingCard();

        if (matchingCard != null) {
            return matchingCard;
        }

        // Nothing matches, so we just return the first card
        return cardsOnHand.get(0);
    }

    /**
     * Tries to find a matching card from the hand. If a golden card is found the card is prefered,
     * otherwise just the matching card is taken.
     *
     * @return
     */
    private Card findMatchingCard() {
        Card matchingCard = null;
        for (Card cardOnHand : cardsOnHand) {
            List<Card> cardsFromTable = table.findCardsFromTableForCard(cardOnHand);
            if (!cardsFromTable.isEmpty()) {
                matchingCard = cardOnHand;

                // Strategy: Prefer to play the golden cards on the hand.
                // So we give up to search for a better card.
                if (cardOnHand.getType().equals(CardType.COINS)) {
                    break;
                }

            }
        }
        return matchingCard;
    }

    private List<Card> getCardsWithValueSeven() {
        return cardsOnHand.stream().filter(card -> card.getValue().equals(CardValue.SEVEN)).collect(Collectors.toList());
    }

    /**
     * Returns true when the golden seven generates an Earning from the table
     *
     * @return true when golden seven can be played
     */
    private boolean goldenSevenEarns() {
        return !table.findCardsFromTableForCard(Card.GOLDEN_SEVEN).isEmpty();
    }

    private boolean hasGoldenSevenOnHand() {
        return cardsOnHand.stream()
                .anyMatch(card -> card.isGoldenSeven());
    }
}
