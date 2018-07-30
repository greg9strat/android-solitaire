package com.gstratton.games.mysolitaire.models;

import android.support.annotation.NonNull;
import android.util.Log;

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

    private final static String LOG_TAG = "DrawPile";
    private Stack<PlayingCard> cards;

    public DrawPile(@NonNull Stack<PlayingCard> cards){
        this.cards = new Stack<>();
        // take the cards from this stack, and manage on our own internal stack
        for(PlayingCard card: cards) {
            this.cards.push(card);
        }
    }

    /**
     * Draws 1 or more cards from this pile.
     *
     * @param numberOfCards The number of cards to be taken (drawn) from this pile.
     * @return The
     */
    public List<PlayingCard> draw(int numberOfCards) {
        Log.d(LOG_TAG, String.format("Attempting to pull %d cards from draw pile.", numberOfCards));
        if (this.cards.isEmpty()) {
            Log.i(LOG_TAG, "Draw pile is empty.");
            return new ArrayList<>();
        } else {
            List<PlayingCard> cardsDrawn = new ArrayList<>();
            int maxNumberOfCardsToDraw = getMaxNumberOfCardsToDraw(numberOfCards);
            for(int i = 0; i < maxNumberOfCardsToDraw; i++){
                PlayingCard c = this.cards.pop();
                c.setFaceUp();
                Log.d(LOG_TAG, String.format("Pulled %s from draw pile.", c.toString()));
                cardsDrawn.add(c);
            }
            Log.d(LOG_TAG, String.format("Pulled a total of %d cards this turn.", cardsDrawn.size()));
            return cardsDrawn;
        }
    }

    /**
     * Helper method that determins the most number of playing cards that could be drawn from a pile.
     * @param requestedNumberToDraw
     * @return
     */
    private int getMaxNumberOfCardsToDraw(int requestedNumberToDraw) {
        if (requestedNumberToDraw < 1 || this.cards.isEmpty()) {
            return 0;
        } else {
            if (this.cards.size() < requestedNumberToDraw) {
                return this.cards.size();
            } else {
                return requestedNumberToDraw;
            }
        }
    }

    public int getCardsRemaining() {
        return this.cards.size();
    }

    public void addCards(Stack<PlayingCard> cards) {
        if (cards != null && !cards.isEmpty()) {

            /*
                Note: A for loop that iterates through the stack using push/pop is going to reverse
                the order of the cards being added.

                So we need to add the cards
             */
            for (PlayingCard card : cards) {
                // in the real world, we take these cards and 'hide' them at this point
                card.setFaceDown();

                // take a copy of the card, and put in our stack locally
                this.cards.push(card);
            }
        }
    }
}
