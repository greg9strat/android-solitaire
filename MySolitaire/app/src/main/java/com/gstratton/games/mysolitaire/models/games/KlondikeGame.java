package com.gstratton.games.mysolitaire.models.games;

import android.util.Log;

import com.gstratton.games.mysolitaire.models.Deck;
import com.gstratton.games.mysolitaire.models.DrawPile;
import com.gstratton.games.mysolitaire.models.FoundationPile;
import com.gstratton.games.mysolitaire.models.Game;
import com.gstratton.games.mysolitaire.models.PlayingCard;
import com.gstratton.games.mysolitaire.models.PlayingCardPile;
import com.gstratton.games.mysolitaire.models.Suits;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class KlondikeGame implements Game {

    private final static String LOG_TAG = KlondikeGame.class.getName();

    private Deck deckOfCards;

    /**
     * The tableau is the set of 7 playing card piles where the main action of this game occurs.
     */
    private List<PlayingCardPile> tableau;

    private DrawPile drawPile;
    private int drawCounter;
    private Stack<PlayingCard> discardPile;
    private List<FoundationPile> foundationPiles;

    public KlondikeGame(){
        this.deckOfCards = Deck.create();
        this.tableau = new ArrayList<>();
        this.foundationPiles = new ArrayList<>();
        this.discardPile = new Stack<>();
    }

    public int getDrawCounter() {return this.drawCounter;}
    public int getRemainingDrawCount() {return this.drawPile.getCardsRemaining();}
    public Stack<PlayingCard> getDiscardPile() {return this.discardPile;}
    public List<FoundationPile> getFoundationPiles() {return this.foundationPiles;}
    public List<PlayingCardPile> getTableau() {return this.tableau;}

    public void setup() {
        Log.i(LOG_TAG, "Setting up game...");
        this.reset();
        this.deckOfCards.shuffle();



        // create 4 foundation piles
        for(Suits suit : Suits.values()){
            this.foundationPiles.add(new FoundationPile(suit));
        }

        // create 7 tableau piles to play
        for (int i = 0; i < 7; i++) {
            this.tableau.add(new PlayingCardPile());
        }

        // deal cards to each pile
        for(int j = 0; j < 7; j++) {
            for (int i = j; i < 7; i++) {
                this.tableau.get(i).addCards(this.deckOfCards.draw(1));
            }
        }

        // turn the top card on each pile face up
        for (PlayingCardPile pile : tableau) {
            pile.getStackOfCards().peek().setFaceUp();
        }

        // init the draw pile with the cards that remain

        this.drawPile = new DrawPile(this.deckOfCards.getCards());

        // HACK: zero out the deck
        this.deckOfCards.draw(this.deckOfCards.getCards().size());

        // verify all cards have now been dealt
        if (!this.deckOfCards.getCards().isEmpty()) {
            Log.e(LOG_TAG, String.format("All cards weren't dealt. %d remain in deck.", this.deckOfCards.getCards().size()));
        }

        Log.i(LOG_TAG, "Setup finished");
    }

//    /**
//     * Draws a card from the deck, but is passed face-down.
//     *
//     */
//    public List<PlayingCard> deal() {
//        if (this.deckOfCards.getCards().isEmpty()) {
//            return null;
//        } else {
//            return this.deckOfCards.draw(1);
//        }
//    }

    public void draw(int numberOfCards) {
        if (this.drawPile != null) {

            // if the draw pile is empty, we should refill it
            if (this.drawPile.getCardsRemaining() < 1) {
                Log.d(LOG_TAG, "Draw pile empty, resetting it.");

                // TODO: something is still reversing the order of the cards - after resetting,
                //       then the user sees the cards in the stack in reverse order...
                //       For example, new deal; then click on deck 24 times (to exhaust it); it
                //       should go empty; and then on the next click, you'll see the most recent
                //       card that was pulled; and then the 2nd-most recent; and then...etc.
                Stack<PlayingCard> reversedStack = new Stack<>();
                for (PlayingCard card : this.discardPile) {
                    reversedStack.push(card);
                }
                this.drawPile.addCards(reversedStack);

                // empty the discard pile
                while(!this.discardPile.isEmpty()) {
                    this.discardPile.pop();
                }
            } else {
                Log.i(LOG_TAG, String.format("Drawing %d cards.", numberOfCards));
                List<PlayingCard> drawnCards = this.drawPile.draw(numberOfCards);

                // put the cards we just drew (above) in the discard pile
                for(PlayingCard drawnCard : drawnCards) {
                    this.discardPile.push(drawnCard);
                }

                this.drawCounter++;
                Log.i(LOG_TAG, String.format("Finished drawing cards. Counter now set: %d", this.drawCounter));
            }
        }
    }

    public void reset() {
        Log.d(LOG_TAG, "Resetting game.");
        this.deckOfCards = Deck.create();
        this.drawCounter = 0;
        this.tableau = new ArrayList<>();
        this.foundationPiles = new ArrayList<>();
        this.discardPile = new Stack<>();
        this.drawPile = null;
    }


}
