package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvents;

public class UnRegisterEventAction implements Action{
    private ICMonEvents event ;
    private ICMon.ICMonEventManager eventManager ;
    public UnRegisterEventAction(ICMonEvents event , ICMon.ICMonEventManager eventManager){
        this.event = event ;
        this.eventManager = eventManager ;
    }

    @Override
    public void perform() {
        if (eventManager != null ){
            eventManager.unRegisterEvent(event);
        }


    }
}
