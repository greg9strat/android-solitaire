package com.gstratton.games.mysolitaire.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Deck {

    private final static String LOG_TAG = "Deck";
    private Stack<PlayingCard> cards;

    private Deck(Stack<PlayingCard> cards) {
        this.cards = cards;
    }

    public Stack<PlayingCard> getCards() {
        return cards;
    }

    /**
     * Creates a new deck of 52 playing cards. Unshuffled.
     * @return
     */
    public static Deck create() {
        Stack<PlayingCard> cardsInDeck = new Stack<>();

        for (Suits suit : Suits.values()) {
            for(byte i = 1; i < 14; i++) {
                cardsInDeck.push(new PlayingCard(suit, i));
            }
        }

        return new Deck(cardsInDeck);
    }

    /**
     * Shuffles the cards in the current deck.
     */
    public void shuffle() {
        Log.i(LOG_TAG, "Shuffling deck.");

        Random rnd = new Random();

        Log.d(LOG_TAG, String.format("There are currently %d cards in the deck.", this.cards.size()));
        Log.d(LOG_TAG, String.format("The top card on the deck at start is: %s", this.cards.peek().toString()));

        for (int i = this.cards.size() - 1; i > 0; i--)
        {
            int randomIndex = rnd.nextInt(i + 1);
            // Simple swap
            PlayingCard a = this.cards.get(randomIndex);
            this.cards.set(randomIndex, this.cards.get(i));
            this.cards.set(i, a);
        }

        Log.d(LOG_TAG, String.format("Completed shuffle. There are %d cards in the deck", this.cards.size()));
        Log.d(LOG_TAG, String.format("The top card on the deck after shuffle is: %s", this.cards.peek().toString()));
    }

    /**
     * Draws a playing card from the deck.
     *
     * @param numberOfCards
     * @return
     */
    public List<PlayingCard> draw(int numberOfCards) {
        Log.i(LOG_TAG, String.format("Drawing card from deck. Requested %d", numberOfCards));
        List<PlayingCard> cardsDrawn = new ArrayList<>();
        if (!this.cards.isEmpty()) {
            for (int i = 0; i < numberOfCards; i++) {
                PlayingCard c = this.cards.pop();
                Log.d(LOG_TAG, String.format("Drew %s from deck.", c.toString()));
                cardsDrawn.add(c);
            }
        } else {
            Log.d(LOG_TAG, "Deck is empty - probably not what you expected.");
        }

        return cardsDrawn;
    }
}
