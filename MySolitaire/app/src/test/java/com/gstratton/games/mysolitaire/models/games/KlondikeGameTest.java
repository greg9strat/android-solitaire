package com.gstratton.games.mysolitaire.models.games;

import com.gstratton.games.mysolitaire.models.PlayingCard;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class KlondikeGameTest {

    private KlondikeGame game;

    @BeforeEach
    void setUp() {
        this.game = new KlondikeGame();
    }

    @AfterEach
    void tearDown() {
        this.game = null;
    }

    @Test
    void draw() {
        game.setup();

        // get a clone of the discard pile, before touching it
        Stack originalDiscardPile = (Stack)game.getDiscardPile().clone();

        while(game.getRemainingDrawCount() > 0) {
            game.draw(1);
        }

        // calling this last one will cause it to reset
        game.draw(1);

        Stack resetDiscardPile = (Stack)game.getDiscardPile().clone();

        assertNotNull(game.getDiscardPile());

        for (int i = 0; i < originalDiscardPile.size(); i++) {
            assertEquals(originalDiscardPile.get(i), resetDiscardPile.get(i), "Cards do not match at position " + i);
        }
    }
}