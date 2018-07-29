package com.gstratton.games.mysolitaire.models;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * One of the playing card piles in Klondike
 */
public class PlayingCardPile {

    private Stack<PlayingCard> stackOfCards;

    public PlayingCardPile() {
        this.stackOfCards = new Stack<>();
    }

    public void addCards(List<PlayingCard> cardsToAdd) {

        // going through the cards in order of the stack, apply rules
        if (canAddCards(cardsToAdd)) {
            for (PlayingCard card :
                    cardsToAdd) {
                this.stackOfCards.push(card);
            }
        } else {
            // cards are not added to this pile...
            // do we need to warn anyone?
        }
    }

    public boolean canAddCards(List<PlayingCard> cardsToAdd) {
        if (cardsToAdd != null && !cardsToAdd.isEmpty()) {
            // if the first card is face down, then we can skip to the next one
            for (PlayingCard card : cardsToAdd) {
                if (card.isFaceUp()) {
                    if (card.getRank() > this.stackOfCards.peek().getRank()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public Stack<PlayingCard> getStackOfCards() {
        return stackOfCards;
    }
}
