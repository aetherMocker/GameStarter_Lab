package edu.up.cs301.pig;

import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.R;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.infoMsg.StartGameInfo;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View.OnClickListener;

/**
 * A GUI for a human to play Pig. This default version displays the GUI but is incomplete
 *
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */
public class PigHumanPlayer extends GameHumanPlayer implements OnClickListener {

	/* instance variables */

    // These variables will reference widgets that will be modified during play
    private TextView    playerScoreTextView = null;
    private TextView    playerScoreText     = null;
    private TextView    oppScoreTextView    = null;
    private TextView    oppScoreText        = null;
    private TextView    turnTotalTextView   = null;
    private TextView    messageTextView     = null;
    private ImageButton dieImageButton      = null;
    private Button      holdButton          = null;

    // the android activity that we are running
    private GameMainActivity myActivity;

    /**
     * constructor does nothing extra
     */
    public PigHumanPlayer(String name) {
        super(name);
    }

    /**
     * Returns the GUI's top view object
     *
     * @return
     * 		the top object in the GUI's view heirarchy
     */
    public View getTopView() {
        return myActivity.findViewById(R.id.top_gui_layout);
    }

    /**
     * callback method when we get a message (e.g., from the game)
     *
     * @param info
     * 		the message
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void receiveInfo(GameInfo info) {
        if(info instanceof PigGameState) {
            if(this.playerNum == 1) {
                playerScoreTextView.setText("" + ((PigGameState) info).getPlayer1Score());
                oppScoreTextView.setText("" + ((PigGameState) info).getPlayer0Score());
                messageTextView.setText(this.name + " is Player 1. \n");
                playerScoreText.setText(this.name + "'s Score: ");
                oppScoreText.setText(this.allPlayerNames[0] + "'s Score: ");
            }
            else if(this.playerNum == 0) {
                playerScoreTextView.setText("" + ((PigGameState) info).getPlayer0Score());
                oppScoreTextView.setText("" + ((PigGameState) info).getPlayer1Score());
                messageTextView.setText(this.name + " is Player 0. \n");
                playerScoreText.setText(this.name + "'s Score: ");
                oppScoreText.setText(this.allPlayerNames[1] + "'s Score: ");
            }
            turnTotalTextView.setText("" + ((PigGameState) info).getRunningTotalScore());
            messageTextView.append(((PigGameState) info).getRunningTotalScore() + " points will be added to Player " + ((PigGameState) info).getPlayerID() + "'s score. \n");

            if(((PigGameState) info).getPlayerID() == this.playerNum) {
                dieImageButton.setBackgroundColor(Color.RED);
                messageTextView.append("It's Player 1's turn.\n");
            }
            if(((PigGameState) info).getPlayerID() != this.playerNum) {
                dieImageButton.setBackgroundColor(Color.GRAY);
                messageTextView.append("It's Player 0's turn.\n");
            }


            if(((PigGameState) info).getDieValue() == 1) {
                dieImageButton.setImageResource(R.drawable.face1);
                messageTextView.append("Oh no! The die rolls 1! Player " + ((PigGameState) info).getPlayerID() + " ended their turn. \n");
            }
            else if(((PigGameState) info).getDieValue() == 2) {
                dieImageButton.setImageResource(R.drawable.face2);
                messageTextView.append("Die rolls 2.\n");
            }
            else if(((PigGameState) info).getDieValue() == 3) {
                dieImageButton.setImageResource(R.drawable.face3);
                messageTextView.append("Die rolls 3.\n");
            }
            else if(((PigGameState) info).getDieValue() == 4) {
                dieImageButton.setImageResource(R.drawable.face4);
                messageTextView.append("Die rolls 4.\n");
            }
            else if(((PigGameState) info).getDieValue() == 5) {
                dieImageButton.setImageResource(R.drawable.face5);
                messageTextView.append("Die rolls 5.\n");
            }
            else if(((PigGameState) info).getDieValue() == 6) {
                dieImageButton.setImageResource(R.drawable.face6);
                messageTextView.append("Die rolls 6.\n");
            }
        }
        else {
            flash(Color.RED, 50);
        }
    }//receiveInfo

    /**
     * this method gets called when the user clicks the die or hold button. It
     * creates a new PigRollAction or PigHoldAction and sends it to the game.
     *
     * @param button
     * 		the button that was clicked
     */
    public void onClick(View button) {
        if (button instanceof ImageButton) {
            PigRollAction pra = new PigRollAction(this);
            game.sendAction(pra);
        }
        else if (button instanceof Button) {
            PigHoldAction pha = new PigHoldAction(this);
            game.sendAction(pha);
        }
    }// onClick

    /**
     * callback method--our game has been chosen/rechosen to be the GUI,
     * called from the GUI thread
     *
     * @param activity
     * 		the activity under which we are running
     */
    public void setAsGui(GameMainActivity activity) {

        // remember the activity
        myActivity = activity;

        // Load the layout resource for our GUI
        activity.setContentView(R.layout.pig_layout);

        //Initialize the widget reference member variables
        this.playerScoreTextView = (TextView)activity.findViewById(R.id.yourScoreValue);
        this.oppScoreTextView    = (TextView)activity.findViewById(R.id.oppScoreValue);
        this.turnTotalTextView   = (TextView)activity.findViewById(R.id.turnTotalValue);
        this.messageTextView     = (TextView)activity.findViewById(R.id.messageTextView);
        this.dieImageButton      = (ImageButton)activity.findViewById(R.id.dieButton);
        this.holdButton          = (Button)activity.findViewById(R.id.holdButton);

        //Listen for button presses
        dieImageButton.setOnClickListener(this);
        holdButton.setOnClickListener(this);

    }//setAsGui

}// class PigHumanPlayer
