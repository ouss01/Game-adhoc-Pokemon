package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.area.ICMonBehavior;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvents;

public class StartEventAction implements Action {

    private ICMonEvents event;
    public StartEventAction(ICMonEvents event){
        this.event=event;
    }
    @Override
    public void perform() {
        event.start();
    }
}
