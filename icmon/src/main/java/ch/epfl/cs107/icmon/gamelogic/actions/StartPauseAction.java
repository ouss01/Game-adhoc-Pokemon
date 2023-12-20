package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.play.engine.PauseMenu;

public class StartPauseAction {
    PauseMenu pauseMenu;
    ICMon.ICMonGameState gameState;
    public StartPauseAction(PauseMenu pauseMenu,ICMon.ICMonGameState gameState){
        this.pauseMenu=pauseMenu;
        this.gameState=gameState;
    }
    public void setPauseMenu(){}
}
