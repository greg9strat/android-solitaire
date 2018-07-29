package com.gstratton.games.mysolitaire.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * A draw pile is a stack of cards, usually started from what's left over after dealing, and
 * used during play to pull cards from.
 *
 * "Drawing" a card is pulling cards from this pile. Depending on the rules of the game, you
 * can draw 1 or more cards at a time.
 */
public class DrawPile {

    private Stack<PlayingCard> cards;

    public DrawPile(Stack<PlayingCard> cards){
        this.cards = cards;
    }

    public List<PlayingCard> draw(int numberOfCards) {
        if (this.cards.isEmpty()) {
            return new ArrayList<>();
        } else {
            List<PlayingCard> cardsDrawn = new ArrayList<>();
            for(int i = 0; i < numberOfCards; i++){
                cardsDrawn.add(this.cards.pop());
            }
            return cardsDrawn;
        }
    }
}
