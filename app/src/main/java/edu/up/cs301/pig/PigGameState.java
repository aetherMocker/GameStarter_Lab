package edu.up.cs301.pig;

import edu.up.cs301.game.infoMsg.GameState;

public class PigGameState extends GameState {

    private int playerID;
    private int player0Score;
    private int player1Score;
    private int runningTotalScore;
    private int dieValue;

    public PigGameState() {
        playerID = 0;
        player0Score = 0;
        player1Score = 0;
        runningTotalScore = 0;
        dieValue = 1;
    }

    public PigGameState(PigGameState pgs) {
        this.playerID = pgs.playerID;
        this.player0Score = pgs.player0Score;
        this.player1Score = pgs.player1Score;
        this.runningTotalScore = pgs.runningTotalScore;
        this.dieValue = pgs.dieValue;
    }



}
