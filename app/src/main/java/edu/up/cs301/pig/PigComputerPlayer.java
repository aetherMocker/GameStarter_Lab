package edu.up.cs301.pig;

import java.util.Random;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.infoMsg.NotYourTurnInfo;
import edu.up.cs301.game.util.Tickable;

/**
 * An AI for Pig
 *
 * @author Andrew M. Nuxoll
 * @version August 2015
 */
public class PigComputerPlayer extends GameComputerPlayer {

    public String getName() { return this.name; }

    /**
     * ctor does nothing extra
     */
    public PigComputerPlayer(String name) {
        super(name);
    }

    /**
     * callback method--game's state has changed
     *
     * @param info
     * 		the information (presumably containing the game's state)
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        if (info instanceof NotYourTurnInfo) {
            return;
        }

        PigGameState pigGameState = (PigGameState) info;

        if (pigGameState.getPlayerID() != this.playerNum){
            return;
        }
        else {
            sleep(2000);
            Random r = new Random();
            int rand = r.nextInt(100);
            if (rand < 50) {
                PigHoldAction pha = new PigHoldAction(this);
                game.sendAction(pha);
            }
            else {
                PigRollAction pra = new PigRollAction(this);
                game.sendAction(pra);
            }
        }

    }//receiveInfo

}
