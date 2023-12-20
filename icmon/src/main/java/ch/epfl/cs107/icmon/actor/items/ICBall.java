package ch.epfl.cs107.icmon.actor.items;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.engine.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public class ICBall extends ICMonItem implements Interactable  {

    public  ICMonItem ball;
    private Sprite sprite;
   public ICBall(Area area, Orientation orientation, DiscreteCoordinates position, String spriteName) {
      super(area, orientation, position, spriteName);
   }

   @Override
   public List<DiscreteCoordinates> getCurrentCells() {
      return Collections.singletonList(getCurrentMainCellCoordinates());
   }

   @Override
   public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
      ((ICMonInteractionVisitor) v).interactWith(this , isCellInteraction);
   }
   @Override
   public boolean isCellInteractable() {
      return false ;
   }

   @Override
   public boolean isViewInteractable() {
      return true   ;
   }


}

