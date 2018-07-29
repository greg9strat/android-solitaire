package com.gstratton.games.mysolitaire.models;

import android.support.annotation.NonNull;

import java.util.Stack;

/**
 * A foundation pile is the place where you have to start with an Ace, and then
 * following suit, build up the pile until all cards of that suit - in order of rank -
 * are in it.
 *
 * Once a card is played on a foundation, it can't be removed.
 *
 * NOTE: Some computer versions of Klondike Solitaire allow you to move cards back and
 * forth between the foundations and the tableau. This alternative rule makes the game
 * a bit easier to win.
 */
public class FoundationPile {

    private Stack<PlayingCard> cards;
    private Suits foundationPileSuit;

    /**
     * Instantiates a new foundation pile.
     *
     * @param suit The suit for cards in this pile.
     */
    public FoundationPile(Suits suit) {
        this.cards = new Stack<>();
        this.foundationPileSuit = suit;
    }

    /**
     *
     * @param cardToAdd Playing card to check if valid for this pile.
     * @return True, if it can be added. False if not.
     */
    public boolean canAddCard(@NonNull PlayingCard cardToAdd) {

        // suits must match
        if (cardToAdd.getSuit() != this.foundationPileSuit) {
            return false;
        }

        // if nothing is currently in our pile, and the card to add is an Ace
        if (this.cards.isEmpty()) {
            if (cardToAdd.getRank() > 1) {
                // cannot add anything other than an Ace to this stack right now
                return false;
            }
        } else {
            // is the card being added the next in sequence?
            if (cardToAdd.getRank() != this.cards.peek().getRank() + 1) {
                return false;
            }
        }

        return true;
    }

    public void addCard(@NonNull PlayingCard cardToAdd) {
        if (this.canAddCard(cardToAdd)) {
            this.cards.push(cardToAdd);
        }

    }
}
