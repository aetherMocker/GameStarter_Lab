package edu.up.cs301.pig;

import android.util.Log;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.infoMsg.NotYourTurnInfo;

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
        if (info instanceof NotYourTurnInfo) {
            return;
        }

        PigGameState pigGameState = (PigGameState) info;

        if (pigGameState.getPlayerID() != this.playerNum) {
            return;
        } else {
            if (this.playerNum == 1) {
                if (pigGameState.getPlayer0Score() > 30 && pigGameState.getPlayer1Score() < 30) {
                    for (int i = 0; i < 4; i++) {
                        sleep(1000);
                        PigRollAction pra = new PigRollAction(this);
                        game.sendAction(pra);
                        if (pigGameState.getRunningTotalScore() > 50 - pigGameState.getPlayer1Score()) {
                            PigHoldAction pha = new PigHoldAction(this);
                            game.sendAction(pha);
                            break;
                        }
                    }
                    PigHoldAction pha = new PigHoldAction(this);
                    game.sendAction(pha);
                }
                else {
                    for (int i = 0; i < 3; i++) {
                        sleep(1000);
                        PigRollAction pra = new PigRollAction(this);
                        game.sendAction(pra);
                        if (pigGameState.getRunningTotalScore() > 50 - pigGameState.getPlayer1Score()) {
                            PigHoldAction pha = new PigHoldAction(this);
                            game.sendAction(pha);
                            break;
                        }
                    }
                    PigHoldAction pha = new PigHoldAction(this);
                    game.sendAction(pha);
                }
                Log.d("Computer Player", "played");
            } else {
                if (pigGameState.getPlayer1Score() > 30 && pigGameState.getPlayer0Score() < 30) {
                    for (int i = 0; i < 4; i++) {
                        sleep(1000);
                        PigRollAction pra = new PigRollAction(this);
                        game.sendAction(pra);
                        if (pigGameState.getRunningTotalScore() > 50 - pigGameState.getPlayer0Score()) {
                            PigHoldAction pha = new PigHoldAction(this);
                            game.sendAction(pha);
                            break;
                        }
                    }
                    PigHoldAction pha = new PigHoldAction(this);
                    game.sendAction(pha);
                }
                else {
                    for (int i = 0; i < 3; i++) {
                        sleep(1000);
                        PigRollAction pra = new PigRollAction(this);
                        game.sendAction(pra);
                        if (pigGameState.getRunningTotalScore() > 50 - pigGameState.getPlayer0Score()) {
                            PigHoldAction pha = new PigHoldAction(this);
                            game.sendAction(pha);
                            break;
                        }
                    }
                    PigHoldAction pha = new PigHoldAction(this);
                    game.sendAction(pha);
                }
                Log.d("Computer Player", "played");
            }
        }

    }//receiveInfo
}
