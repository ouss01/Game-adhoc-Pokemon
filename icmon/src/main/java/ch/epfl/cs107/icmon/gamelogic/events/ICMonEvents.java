package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.items.ICMonItem;
import ch.epfl.cs107.icmon.gamelogic.actions.Action;
import ch.epfl.cs107.icmon.gamelogic.actions.RegisterEventAction;
import ch.epfl.cs107.icmon.gamelogic.actions.UnRegisterEventAction;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.engine.Updatable;

import java.util.ArrayList;
import java.util.List;

public abstract class ICMonEvents implements Updatable, ICMonInteractionVisitor {
    //ICMonPlayer mainPlayer = null;
    private ICMonItem item;
    public boolean started;
    public boolean completed;
    public boolean suspended;
    public boolean resumed;
    private List<Action> onStartActions = new ArrayList<>();
    private List<Action> onCompleteActions = new ArrayList<>();
    private List<Action> onSuspensionActions = new ArrayList<>();
    private List<Action> onResumeActions = new ArrayList<>();
    private ICMonPlayer player;
    private ICMonEvents event;
    private ICMon.ICMonEventManager eventManager;
    //public CollectItemEvent(ICMonPlayer player, ICMonItem item){
    // this.player=player;
    // this.item=item;
    //}

    public ICMonEvents(ICMonPlayer player, ICMon.ICMonEventManager eventManager){
        this.eventManager=eventManager;
        this.player=player;
        onStart(new RegisterEventAction(event,eventManager));
        onComplete(new UnRegisterEventAction(event,eventManager));
        onResume(new RegisterEventAction(event,eventManager));
        onSuspension((new UnRegisterEventAction(event,eventManager)));
    }
    public void start() {
        if(!started){
            executeActions(onStartActions);
            started=true;
        }
    }

    public void complete() {
        if(started && !completed){
            executeActions(onCompleteActions);
            completed=true;
        }
    }

    public void suspend() {
        if(started && completed && !suspended){
            executeActions(onSuspensionActions);
            suspended=true;
        }
    }

    public void resume() {
        if(started && !completed && suspended){
            executeActions(onResumeActions);
            resumed=true;
        }
    }

    public void onStart(Action action) {
        onStartActions.add(action);
    }

    public void onComplete(Action action) {
        onCompleteActions.add(action);
    }

    public void onSuspension(Action action) {
        onSuspensionActions.add(action);
    }


    public void onResume(Action action) {
        onResumeActions.add(action);
    }
    private void executeActions(List<Action> actions) {
        for (Action action : actions) {
            action.perform();
        }
    }
    public String getState(Action action){
        if(completed){
            return ("Action completed");
        }else if(suspended){
            return ("Action suspended");
        }else{
            return ("Action started");
        }
    }

}
