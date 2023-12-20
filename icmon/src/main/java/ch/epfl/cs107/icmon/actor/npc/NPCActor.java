package ch.epfl.cs107.icmon.actor.npc;

import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.actor.RPGSprite;
import ch.epfl.cs107.play.engine.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.window.Canvas;
import org.apache.commons.io.filefilter.FalseFileFilter;

import java.util.Collections;
import java.util.List;

public abstract class NPCActor extends ICMonActor implements Interactable {
    public NPCActor(Area owner, Orientation orientation, DiscreteCoordinates coordinates,String sprite_name) {
        super(owner, orientation, coordinates);
        sprite=new RPGSprite(sprite_name , 1, 1.3125f, this , new RegionOfInterest(0, 0, 16, 21));
    }
    private String sprite_name;
    private Sprite sprite;

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
    }

    public boolean takeCellSpace(){
        return true;
    }
    public boolean wantsViewInteraction(){
        return true;
    }
    public boolean wantsCellInteraction(){
        return false;
    }
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }
}
