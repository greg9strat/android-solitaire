package com.gstratton.games.mysolitaire.models.games;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gstratton.games.mysolitaire.R;
import com.gstratton.games.mysolitaire.models.PlayingCard;
import com.gstratton.games.mysolitaire.models.PlayingCardPile;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayKlondike2Activity extends AppCompatActivity {

    private final static String LOG_TAG = "PlayKlondikeAct";

    private KlondikeGame game;

    @BindView(R.id.txtCardsRemainingCount)
    TextView cardsRemainingCount;

    @BindView(R.id.txtDrawCount)
    TextView drawCount;

    @BindView(R.id.pile1)
    ImageView pile1;

    @BindView(R.id.pile2)
    ImageView pile2;

    @BindView(R.id.pile3)
    ImageView pile3;

    @BindView(R.id.pile4)
    ImageView pile4;

    @BindView(R.id.pile5)
    ImageView pile5;

    @BindView(R.id.pile6)
    ImageView pile6;

    @BindView(R.id.pile7)
    ImageView pile7;

    @BindView(R.id.txtVisibleCard_DrawPile)
    TextView visibleDrawPile;

    @BindView(R.id.txtPile1)
    TextView txtPile1;

    @BindView(R.id.txtPile2)
    TextView txtPile2;
    @BindView(R.id.txtPile3)
    TextView txtPile3;
    @BindView(R.id.txtPile4)
    TextView txtPile4;
    @BindView(R.id.txtPile5)
    TextView txtPile5;
    @BindView(R.id.txtPile6)
    TextView txtPile6;
    @BindView(R.id.txtPile7)
    TextView txtPile7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "Creating new activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_klondike);
        this.game = new KlondikeGame();

        ButterKnife.bind(this);
    }

    public void newDeal(View view) {
        this.game.setup();
        updateCounts();
    }

    public void drawCard(View view) {
        this.game.draw(1);
        updateCounts();
    }

    public void updateCounts() {
        this.drawCount.setText(String.valueOf(this.game.getDrawCounter()));
        this.cardsRemainingCount.setText(String.valueOf(this.game.getRemainingDrawCount()));
        try {
            PlayingCard pulledCard = this.game.getDiscardPile().peek();
            if (pulledCard != null) {
                this.visibleDrawPile.setText(pulledCard.toString());
            }
        } catch (EmptyStackException e) {
            // no cards left to draw from, deck was likely addCards
            this.visibleDrawPile.setText(R.string.empty);
        }

        List<PlayingCardPile> tableau = game.getTableau();
        if (!tableau.isEmpty()) {
            txtPile1.setText(getTableauPileString(tableau.get(0).getStackOfCards()));
            txtPile2.setText(getTableauPileString(tableau.get(1).getStackOfCards()));
            txtPile3.setText(getTableauPileString(tableau.get(2).getStackOfCards()));
            txtPile4.setText(getTableauPileString(tableau.get(3).getStackOfCards()));
            txtPile5.setText(getTableauPileString(tableau.get(4).getStackOfCards()));
            txtPile6.setText(getTableauPileString(tableau.get(5).getStackOfCards()));
            txtPile7.setText(getTableauPileString(tableau.get(6).getStackOfCards()));
        }
    }

    public String getTableauPileString(Stack<PlayingCard> tableauStack) {
        if (tableauStack == null || tableauStack.isEmpty()) {
            return getString(R.string.empty);
        } else {
            // set the text to the top card
            return tableauStack.peek().toString();
        }
    }

}
