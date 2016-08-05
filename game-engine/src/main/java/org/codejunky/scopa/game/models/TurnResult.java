package org.codejunky.scopa.game.models;

import org.codejunky.scopa.models.Card;

import java.util.List;

/**
 * This class represents a turn in the game where a card is played
 * and a list of cards is earned.
 *
 * Created by jan on 13.07.16.
 */
public class TurnResult {
    private Card playedCard;
    private List<Card> earnedCards;

    public TurnResult(Card playedCard, List<Card> earnedCards) {
        this.playedCard = playedCard;
        this.earnedCards = earnedCards;
    }

    public Card getPlayedCard() {
        return playedCard;
    }

    public List<Card> getEarnedCards() {
        return earnedCards;
    }

}
