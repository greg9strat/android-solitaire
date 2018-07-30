package com.gstratton.games.mysolitaire.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class PlayingCard implements Parcelable, Comparable<PlayingCard> {

//    private const String[] NUMBERS = ['A','1','2','3','4','5','6','7','8','9','10','J','Q','K']

    private Suits suit;

    // the number of the card
    private byte rank;

    private boolean isFaceUp;


    public PlayingCard(Suits suit, byte rank){
        this.suit = suit;

        // all cards are face down, by default
        this.isFaceUp = false;

        if (rank < 1 || rank > 13) {
            throw new IndexOutOfBoundsException();
        }
        this.rank = rank;

    }

    public Suits getSuit() {return this.suit;}

    public byte getRank() {return this.rank;}

    public boolean isFaceUp() {return this.isFaceUp;}
    public void setFaceUp() {this.isFaceUp = true;}
    public void setFaceDown() {this.isFaceUp = false;}


    @Override
    public String toString() {
        String cardName;

        switch (this.rank) {
            case 1:
                cardName = "Ace";
                break;
            case 2:
                cardName = "Two";
                break;
            case 3:
                cardName = "Three";
                break;
            case 4:
                cardName = "Four";
                break;
            case 5:
                cardName = "Five";
                break;
            case 6:
                cardName = "Six";
                break;
            case 7:
                cardName = "Seven";
                break;
            case 8:
                cardName = "Eight";
                break;
            case 9:
                cardName = "Nine";
                break;
            case 10:
                cardName = "Ten";
                break;
            case 11:
                cardName = "Jack";
                break;
            case 12:
                cardName = "Queen";
                break;
            case 13:
                cardName = "King";
                break;
            default:
                throw new IndexOutOfBoundsException("Unknown card rank");
        }

        return String.format("%s of %s", cardName, this.getSuit().name());
    }

    //#region Parcelable implementation
    protected PlayingCard(Parcel in) {
        rank = in.readByte();
        this.suit = Suits.valueOf(in.readString());
        isFaceUp = in.readByte() != 0;
    }

    public static final Creator<PlayingCard> CREATOR = new Creator<PlayingCard>() {
        @Override
        public PlayingCard createFromParcel(Parcel in) {
            return new PlayingCard(in);
        }

        @Override
        public PlayingCard[] newArray(int size) {
            return new PlayingCard[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(rank);
        parcel.writeString(suit.name());
        parcel.writeByte((byte) (isFaceUp ? 1 : 0));
    }

    //#endregion

    @Override
    public int compareTo(@NonNull PlayingCard playingCard) {
        return Byte.compare(this.getRank(), playingCard.getRank());
    }

    /**
     * Calculates a hash for this instance.
     *
     * @return
     * @implNote Note: the private field {@boolean isFaceUp} was purposely omitted from this hash.
     *           The uniqueness of this object doesn't differ if a playing card is facing a
     *           particular direction. An Ace of Spaces is still an Ace of Spades, regardless of
     *           where it points.
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.suit)
                .append(this.rank)
                .toHashCode();
    }

    /**
     * Comparator for playing cards. Equivalence is based on basic properties: suit and rank.
     *
     * @param obj
     * @return
     * @implNote Similar to the hashCode() implementation, this omits the trivial property isFaceUp.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PlayingCard) {
            final PlayingCard other = (PlayingCard)obj;
            return new EqualsBuilder()
                    .append(this.suit, other.suit)
                    .append(this.rank, other.rank)
                    .isEquals();

        } else {
            return false;
        }

    }
}
