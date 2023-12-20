package ch.epfl.cs107.icmon.actor;

import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;

public class Bulbizarre extends Pokemon{
    public Bulbizarre(Area owner,  DiscreteCoordinates coordinates) {
        super(owner, Orientation.DOWN, coordinates, "bulbizarre", 10, 1);
    }
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }
}
