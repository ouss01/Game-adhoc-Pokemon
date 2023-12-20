package ch.epfl.cs107.icmon.message;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.gamelogic.actions.IncludePauseMenu;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvents;
import ch.epfl.cs107.icmon.gamelogic.events.PokemonFightEvent;

public class SuspendWithEvent implements GamePlayMessage{
    private PokemonFightEvent fight;
    private ICMonEvents event;
    private ICMon.ICMonGameState gameState;
    public SuspendWithEvent(ICMonEvents event,ICMon.ICMonGameState gameState){
        this.event=event;
        this.gameState=gameState;
    }
    @Override
    public void process() {
        //System.out.println("suspension des événements en cours à cause d’un événement combat");
        if (event instanceof IncludePauseMenu){
        }
    }
}
