package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvents;

public class RegisterEventAction implements Action{
    public ICMonEvents event;
    private ICMon.ICMonEventManager eventManager;
    public RegisterEventAction(ICMonEvents event, ICMon.ICMonEventManager eventManager){
        this.event=event;
        this.eventManager=eventManager;
    }
    @Override
    public void perform() {
        if(eventManager!=null){eventManager.registerEvent(event);}
    }
}
