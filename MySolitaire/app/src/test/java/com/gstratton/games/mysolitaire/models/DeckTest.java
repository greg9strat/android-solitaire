package com.gstratton.games.mysolitaire.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getCards() {
    }

    @Nested
    class DeckCreationTests {

        private Deck deckTest;

        @BeforeEach void beforeEach() {
            deckTest = null;
        }

        @Test
        void create() {
            final int expectedNumberOfCardsInDeck = 52;

            deckTest = Deck.create();

            // assert
            assertNotNull(deckTest);
            assertEquals(expectedNumberOfCardsInDeck, deckTest.getCards().size());

            // ensure that a new deck contains unique cards (no duplicates)
            Set<?> uniqueSet = new HashSet<>(deckTest.getCards());
            assertEquals(expectedNumberOfCardsInDeck, uniqueSet.size());
        }

    }

    @Nested
    class DeckShuffleTests {

        @BeforeEach void beforeEach(){}
        @AfterEach void afterEach(){}

        @Test
        void shuffle() {
        }

    }

    @Nested
    class DeckDrawTests {
        private Deck deck;
        private List<PlayingCard> actualResult;

        @BeforeEach void beforeEach(){
            deck = Deck.create();
            actualResult = null;
        }
        @AfterEach void afterEach(){
            deck = null;
        }

        @Test
        void whenDraw_thenEmptyCollection() {
            for (int i = 0; i < 53; i++) {
                deck = Deck.create();
                actualResult = deck.draw(i);
                assertEquals(i, actualResult.size());
            }
        }
    }

}