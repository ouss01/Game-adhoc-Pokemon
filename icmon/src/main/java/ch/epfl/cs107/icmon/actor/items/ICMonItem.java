package ch.epfl.cs107.icmon.actor.items;

import ch.epfl.cs107.play.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.actor.RPGSprite;
import ch.epfl.cs107.play.engine.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;

import java.awt.*;

public abstract class ICMonItem extends CollectableAreaEntity {

    public ICMonItem(Area area, Orientation orientation, DiscreteCoordinates position,String spriteName) {
        super(area, orientation, position);
        orientate(Orientation.DOWN);
        this.spriteName=spriteName;
        item=new RPGSprite(spriteName , 1f, 1f, this);
    }
    private Sprite sprite;
    private String spriteName;
    private RPGSprite item;


    @Override
    public boolean takeCellSpace() {
        return true;
    }

    @Override
    public boolean isCellInteractable() {
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }
    public void draw(Canvas canvas) {
        item.draw(canvas);
    }
}
