package com.gstratton.games.mysolitaire.models;

import java.util.ArrayList;
import java.util.List;

public class Klondike implements Game {

    private Deck deckOfCards;

    /**
     * The tableau is the set of 7 playing card piles where the main action of this game occurs.
     */
    private List<PlayingCardPile> tableau;

    private DrawPile drawPile;

    private List<FoundationPile> foundationPiles;

    public Klondike(){
        this.deckOfCards = Deck.create();
        this.tableau = new ArrayList<>();
        this.foundationPiles = new ArrayList<>();
    }

    public void setup() {
        this.deckOfCards.shuffle();

        // init the draw pile
        this.drawPile = new DrawPile(this.deckOfCards.getCards());

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
                this.tableau.get(i).addCards(drawPile.draw(1));
            }
        }

        // turn the top card on each pile face up
        for (PlayingCardPile pile : tableau) {
            pile.getStackOfCards().peek().setFaceUp();
        }

    }
}
