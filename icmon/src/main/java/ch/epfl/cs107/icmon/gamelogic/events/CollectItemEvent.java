package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.items.ICMonItem;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.actor.npc.NPCActor;
import ch.epfl.cs107.icmon.area.ICMonBehavior;
import ch.epfl.cs107.icmon.gamelogic.actions.Action;
import ch.epfl.cs107.icmon.gamelogic.actions.LogAction;
import ch.epfl.cs107.play.engine.actor.Dialog;

import java.util.ArrayList;
import java.util.List;

public class CollectItemEvent extends ICMonEvents{
    private  ICMonPlayer player;
    private ICMonItem item;
    private ICMon.ICMonEventManager eventManager;

    public CollectItemEvent(ICMonItem item, ICMonPlayer player, ICMon.ICMonEventManager eventManager){
        super(player,eventManager);
        this.item=item;
        this.eventManager=eventManager;
    }
    @Override
    public void update(float deltaTime) {
        if(item!=null && item.isCollected()){
            complete();
        }
    }



    public void interactWith(ICShopAssistant assistant , boolean isCellInteraction){
        LogAction message=new LogAction("This is an interaction between the player and ICShopAssistant based on events !");
        message.perform();
        if (player!=null){
            player.openDialog(new Dialog("collect_item_event_interaction_with_icshopassistant"));}
        LogAction messagefin=new LogAction("I heard that you were able to implement this step successfully. Congrats !");
        messagefin.perform();
    }


    @Override
    public void interactWith(ICMonPlayer player, boolean isCellInteraction) {

    }

    @Override
    public void interactWith(ICMonBehavior.ICMonCell other, boolean isCellInteraction) {

    }

    @Override
    public void interactWith(ICBall ball, boolean isCellInteraction) {

    }


}
