package ch.epfl.cs107.icmon.actor;

import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

public class Nidoqueen extends Pokemon{
    public Nidoqueen(Area owner, DiscreteCoordinates coordinates, String pokemonName, int hpMax, int damage) {
        super(owner, Orientation.DOWN, coordinates, "nidoqueen", 10, 1);
    }
}
