package com.gstratton.games.mysolitaire.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class DrawPileTest {
    Stack<PlayingCard> playingCardStack;

    @BeforeEach
    void setUp() {
        this.playingCardStack = new Stack<>();
    }

    @AfterEach
    void tearDown() {
        this.playingCardStack = null;
    }

    @Test
    void pileHasExpectedNumberOfCardsWhenInitialized() {
        playingCardStack.push(new PlayingCard(Suits.Clubs, Byte.valueOf("2")));
        playingCardStack.push(new PlayingCard(Suits.Hearts, Byte.valueOf("1")));
        playingCardStack.push(new PlayingCard(Suits.Diamonds, Byte.valueOf("9")));

        DrawPile actual = new DrawPile(playingCardStack);

        assertEquals(playingCardStack.size(), actual.getCardsRemaining());
    }

    @Test
    void drawPile_whenEmptyStackProvided_thenEmptyPile() {
        DrawPile actual = new DrawPile(playingCardStack);

        assertNotNull(actual);
        assertEquals(playingCardStack.size(), actual.getCardsRemaining());
    }

    @Test
    void drawPile_whenOriginalStackDestroyed_thenPileHasCards() {
        playingCardStack.push(new PlayingCard(Suits.Clubs, Byte.valueOf("2")));
        playingCardStack.push(new PlayingCard(Suits.Hearts, Byte.valueOf("1")));
        playingCardStack.push(new PlayingCard(Suits.Diamonds, Byte.valueOf("9")));

        DrawPile actual = new DrawPile(playingCardStack);

        assertNotNull(actual);

        // null out the original reference that was passed into constructor -
        // this ensures that if the original stack is modified, the draw pile manages it's own
        // stack of cards and is not affected by changes (basically, avoid passing references
        // to collections)
        playingCardStack = null;

        assertEquals(3, actual.getCardsRemaining());
    }

    @Nested
    class DrawMethodTests {
        private DrawPile getEmptyPile() {
            return new DrawPile(new Stack<>());
        }

        private DrawPile getPileWithMultipleCards() {
            playingCardStack.push(new PlayingCard(Suits.Clubs, Byte.valueOf("11")));
            playingCardStack.push(new PlayingCard(Suits.Hearts, Byte.valueOf("1")));
            playingCardStack.push(new PlayingCard(Suits.Clubs, Byte.valueOf("3")));
            playingCardStack.push(new PlayingCard(Suits.Spades, Byte.valueOf("8")));
            playingCardStack.push(new PlayingCard(Suits.Diamonds, Byte.valueOf("1")));

            return new DrawPile(playingCardStack);
        }

        private DrawPile pileToTest;

        @BeforeEach
        void setUp() {
            playingCardStack = new Stack<>();
        }

        @AfterEach
        void tearDown() {
            playingCardStack.clear();
        }

        @Test
        void draw_whenEmptyPile_thenEmptyCollection() {
            pileToTest = getEmptyPile();

            List<PlayingCard> actual = pileToTest.draw(1);

            assertNotNull(actual);
            assertTrue(actual.isEmpty(), "Collection should be empty.");
            assertTrue(pileToTest.getCardsRemaining() == 0, "Original pile should also be empty.");
        }

        @Test
        void draw_whenRequestIsLargerThanPile_thenReturnRemainingPile() {
            pileToTest = getPileWithMultipleCards();
            int expectedNumberOfCards = pileToTest.getCardsRemaining();
            int numberOfCardsToDraw = expectedNumberOfCards + 5;

            List<PlayingCard> actual = pileToTest.draw(numberOfCardsToDraw);

            assertNotNull(actual);
            assertEquals(expectedNumberOfCards, actual.size());

            // the pile should be empty - no cards left
            assertEquals(0, pileToTest.getCardsRemaining());

        }

        @Test
        void draw_whenRequestIsNegative_thenReturnEmptyCollection() {
            // arrange
            pileToTest = getPileWithMultipleCards();
            int numberOfCardsToDraw = -1;

            // act
            List<PlayingCard> actual = pileToTest.draw(numberOfCardsToDraw);

            // assert
            assertNotNull(actual);
            assertTrue(actual.isEmpty());
        }

        @Test
        void draw_whenRequestOneCardFromFullPile_thenReturnSingleCard() {
            // arrange
            pileToTest = getPileWithMultipleCards();
            int originalPileSize = pileToTest.getCardsRemaining();
            int numberOfCardsToDraw = 1;

            // act
            List<PlayingCard> actual = pileToTest.draw(numberOfCardsToDraw);

            // assert
            assertNotNull(actual);
            assertTrue(actual.size() == numberOfCardsToDraw);
            // the pile should have been reduced by the number of cards we just drew
            assertEquals(originalPileSize - numberOfCardsToDraw, pileToTest.getCardsRemaining());
        }

        @Test
        void draw_whenRequestMultipleCards_thenReturnMultiple() {
            // arrange
            pileToTest = getPileWithMultipleCards();
            int originalPileSize = pileToTest.getCardsRemaining();
            int numberOfCardsToDraw = 3;

            // act
            List<PlayingCard> actual = pileToTest.draw(numberOfCardsToDraw);

            // assert
            assertNotNull(actual);
            assertTrue(actual.size() == numberOfCardsToDraw);
            // the pile should have been reduced by the number of cards we just drew
            assertEquals(originalPileSize - numberOfCardsToDraw, pileToTest.getCardsRemaining());
        }

        @Test
        void draw_whenSequentialDraws_thenPileReduces() {
            // arrange
            pileToTest = getPileWithMultipleCards();
            int originalPileSize = pileToTest.getCardsRemaining();
            int singleDraw = 1;

            // act
            List<PlayingCard> actual1 = pileToTest.draw(singleDraw);
            List<PlayingCard> actual2 = pileToTest.draw(singleDraw);
            List<PlayingCard> actual3 = pileToTest.draw(singleDraw);

            // assert
            assertEquals(originalPileSize - 3, pileToTest.getCardsRemaining());

        }
    }

    @Nested
    class AddCardsTests {

        @BeforeEach
        void beforeEach() {}

        @AfterEach
        void afterEach() {}

         @Test
        void add_whenEmptyParameter_thenNothingChanges() {}

        void add_whenSingleCardProvided_thenPileIncreased() {}

        void add_whenNullParameter_thenNothingChanges() {}
    }

    @Test
    void reset() {
    }
}