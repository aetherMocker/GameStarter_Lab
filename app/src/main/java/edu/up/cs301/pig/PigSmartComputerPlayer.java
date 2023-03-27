package edu.up.cs301.pig;

import android.util.Log;

import java.util.Random;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;

public class PigSmartComputerPlayer extends GameComputerPlayer {
    /**
     * ctor does nothing extra
     */
    public PigSmartComputerPlayer(String name) {
        super(name);
    }

    /**
     * callback method--game's state has changed
     *
     * @param info the information (presumably containing the game's state)
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        PigGameState pigGameState = (PigGameState) info;

        if (pigGameState.getPlayerID() != this.playerNum) {
            return;
        } else {
            sleep(2000);
            Random r = new Random();
            if (this.playerNum == 1) {
                while (pigGameState.getRunningTotalScore() < 15) {
                    PigRollAction pra = new PigRollAction(this);
                    game.sendAction(pra);
                    PigHoldAction pha = new PigHoldAction(this);
                    game.sendAction(pha);
                    Log.d("Computer Player", "played");
                }
            } else {
                while (pigGameState.getRunningTotalScore() < 15) {
                    PigRollAction pra = new PigRollAction(this);
                    game.sendAction(pra);
                    PigHoldAction pha = new PigHoldAction(this);
                    game.sendAction(pha);
                    Log.d("Computer Player", "played");

                }

            }
        }

    }//receiveInfo
}
