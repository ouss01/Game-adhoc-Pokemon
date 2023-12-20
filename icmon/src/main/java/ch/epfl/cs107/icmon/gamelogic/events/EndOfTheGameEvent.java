package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.actor.npc.NPCActor;
import ch.epfl.cs107.icmon.area.ICMonBehavior;
import ch.epfl.cs107.play.engine.actor.Dialog;

public class EndOfTheGameEvent extends ICMonEvents {
    private  ICMonPlayer player;
    public EndOfTheGameEvent(ICMonPlayer player, ICMon.ICMonEventManager eventManager){
        super(player,eventManager);
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void interactWith(ICMonPlayer player, boolean isCellInteraction) {
        player.openDialog(new Dialog("end_of_game_event_interaction_with_icshopassistant"));
    }

    @Override
    public void interactWith(ICMonBehavior.ICMonCell other, boolean isCellInteraction) {

    }

    @Override
    public void interactWith(ICBall ball, boolean isCellInteraction) {

    }

    /*@Override
    public void interactWith(ICShopAssistant assistant, boolean isCellInteraction) {
        System.out.println("I heard that you were able to implement this step successfully. Congrats !");

    }*/


}
