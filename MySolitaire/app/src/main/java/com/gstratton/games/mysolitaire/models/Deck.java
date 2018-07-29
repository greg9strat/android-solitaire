package com.gstratton.games.mysolitaire.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Deck {

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

        for (Suits suit :
                Suits.values()) {
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
        // If running on Java 6 or older, use `new Random()` on RHS here
//        Random rnd = ThreadLocalRandom.current();
        Random rnd = new Random();
        for (int i = this.cards.size() - 1; i > 0; i--)
        {
            int randomIndex = rnd.nextInt(i + 1);
            // Simple swap
            PlayingCard a = this.cards.get(randomIndex);
            this.cards.set(randomIndex, this.cards.get(i));
            this.cards.set(i, a);
        }
    }

    /**
     * Draws a playing card from the deck.
     *
     * @param numberOfCards
     * @return
     */
    public List<PlayingCard> draw(int numberOfCards) {
        List<PlayingCard> cardsDrawn = new ArrayList<>();
        if (!this.cards.isEmpty()) {
            for (int i = 0; i < numberOfCards; i++) {
                cardsDrawn.add(this.cards.pop());
            }
        }
        return cardsDrawn;
    }
}
