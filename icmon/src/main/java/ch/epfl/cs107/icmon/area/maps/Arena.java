package ch.epfl.cs107.icmon.area.maps;

import ch.epfl.cs107.icmon.actor.Bulbizarre;
import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.Pokemon;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

public class Arena extends ICMonArea {
    @Override
    protected void createArea() {
        registerActor(new Background(this));
        registerActor(new Foreground(this));
        registerActor(new Door(this,"Town",new DiscreteCoordinates(20,15),new DiscreteCoordinates(4,1),new DiscreteCoordinates(5,1)));
        registerActor(new Bulbizarre(this, new DiscreteCoordinates(6,6)));
        registerActor(new Bulbizarre(this, new DiscreteCoordinates(6,6)));
        registerActor(new Bulbizarre(this, new DiscreteCoordinates(6,6)));
    }

    @Override
    public DiscreteCoordinates getPlayerSpawnPosition() {
        return new DiscreteCoordinates(5,5);
    }
    @Override
    public String getTitle() {
        return "arena";
    }

    @Override
    public boolean containsPosition(DiscreteCoordinates destinationCoordinates) {
        return false;
    }

    @Override
    public String toString() {
        return "Arena";}
}
