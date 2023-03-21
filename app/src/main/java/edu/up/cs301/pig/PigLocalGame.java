package edu.up.cs301.pig;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameState;

import android.util.Log;

import java.util.Random;

// dummy comment, to see if commit and push work from srvegdahl account

/**
 * class PigLocalGame controls the play of the game
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */
public class PigLocalGame extends LocalGame {

    private PigGameState pigGameState;

    /**
     * This ctor creates a new game state
     */
    public PigLocalGame() {
        pigGameState = new PigGameState();
    }

    /**
     * can the player with the given id take an action right now?
     */
    @Override
    protected boolean canMove(int playerIdx) {
        if(playerIdx == pigGameState.getPlayerID()) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * This method is called when a new action arrives from a player
     *
     * @return true if the action was taken or false if the action was invalid/illegal.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        if(action instanceof PigHoldAction) {
            if(pigGameState.getPlayerID() == 0) {
                pigGameState.setPlayer0Score(pigGameState.getPlayer0Score() + pigGameState.getRunningTotalScore());
                pigGameState.setRunningTotalScore(0);
                pigGameState.setPlayerID(1);
                return true;
            }
            if(pigGameState.getPlayerID() == 1) {
                pigGameState.setPlayer1Score(pigGameState.getPlayer1Score() + pigGameState.getRunningTotalScore());
                pigGameState.setRunningTotalScore(0);
                pigGameState.setPlayerID(0);
                return true;
            }
        }
        else if(action instanceof PigRollAction) {
            Random r = new Random();
            pigGameState.setDieValue(r.nextInt(5) +1);
            if(pigGameState.getDieValue() != 1) {
                pigGameState.setRunningTotalScore(pigGameState.getRunningTotalScore() + pigGameState.getDieValue());
                return true;
            }
            else {
                pigGameState.setRunningTotalScore(0);
                if(pigGameState.getPlayerID() == 1) {
                    pigGameState.setPlayerID(0); }
                else {
                    pigGameState.setPlayerID(1);
                }
            }
        }
        return false;
    }//makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        //TODO  You will implement this method
    }//sendUpdatedSate

    /**
     * Check if the game is over
     *
     * @return
     * 		a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    protected String checkIfGameOver() {
        //TODO  You will implement this method
        return null;
    }

}// class PigLocalGame
