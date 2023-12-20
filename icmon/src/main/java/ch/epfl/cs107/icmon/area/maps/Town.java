package ch.epfl.cs107.icmon.area.maps;
import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.items.ICMonItem;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.gamelogic.actions.Action;
import ch.epfl.cs107.icmon.gamelogic.actions.LogAction;
import ch.epfl.cs107.icmon.gamelogic.events.CollectItemEvent;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvents;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.icmon.area.ICMonArea ;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.math.Vector;

import java.util.ArrayList;

/**
 * ???
 */
public final class Town extends ICMonArea {

    private ICMonPlayer player;
    private Action action;
    private ICMonItem item;
    private final String MessageDebut="CollecttItemEvent started !";
    private final String MessageFin="CollectItemEvent completed !";



    /**
     * ???
     * @return ???
     */
    @Override
    public DiscreteCoordinates getPlayerSpawnPosition() {
        return new DiscreteCoordinates(5, 5);
    }

    /**
     * ???
     */
    @Override
    protected void createArea() {
        registerActor(new Background(this));
        registerActor(new Foreground(this));
        registerActor(new ICBall(this,Orientation.DOWN,new DiscreteCoordinates(6,6),"items/icball"));
        registerActor(new ICShopAssistant("actors/assistant",this,Orientation.DOWN,new DiscreteCoordinates(8,8)));
        registerActor(new Door(this,new DiscreteCoordinates(15, 24),"lab",new DiscreteCoordinates(6,2)));
        //registerActor(new Door(this,new DiscreteCoordinates(4,2),"Arena",new DiscreteCoordinates(20,16)));
        registerActor(new Door(this,new DiscreteCoordinates(20,16),"arena",new DiscreteCoordinates(4,2)));
    }

    /**
     * ???
     * @return ???
     */
    @Override
    public String getTitle() {
        return "Town";
    }

    @Override
    public boolean containsPosition(DiscreteCoordinates destinationCoordinates) {
        return false;
    }
    @Override
    public String toString() {
        return "Town";
    }

}