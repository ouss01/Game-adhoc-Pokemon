package ch.epfl.cs107.icmon.actor;

import ch.epfl.cs107.icmon.gamelogic.actions.Action;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;

import java.util.ArrayList;
import java.util.List;

public class Door extends ICMonActor{
    private String destinationAreaName;
    private DiscreteCoordinates mainCellPosition;
    private DiscreteCoordinates[] occupiedCells ;
    private DiscreteCoordinates arrivalCoordinates;
    public Door(Area owner, DiscreteCoordinates arrivalCoordinates,String destinationAreaName,DiscreteCoordinates mainCellPosition) {
        super(owner, Orientation.UP, arrivalCoordinates);
        this.mainCellPosition=mainCellPosition;
        this.destinationAreaName=destinationAreaName;
        this.occupiedCells=new DiscreteCoordinates[0];
    }
    public Door(Area owner,String destinationAreaName, DiscreteCoordinates arrivalCoordinates,DiscreteCoordinates mainCellPosition,DiscreteCoordinates... occupiedCells){
        super(owner, Orientation.UP, mainCellPosition);
        this.destinationAreaName=destinationAreaName;
        this.occupiedCells= occupiedCells;
        this.arrivalCoordinates=arrivalCoordinates;
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        ArrayList<DiscreteCoordinates> allCells=new java.util.ArrayList<>(List.of(occupiedCells));
        allCells.add(this.getCurrentMainCellCoordinates());
        return allCells;

    }
    public void draw(Canvas canvas){}
    public DiscreteCoordinates getDestinationCoordinates(){
        return mainCellPosition;
    }
    public String getDestinationAreaName(){return destinationAreaName;}
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this , isCellInteraction);
    }
    public boolean takeCellSpace(){return false;}
    @Override
    public boolean isCellInteractable() {
        return true;
    }


    @Override
    public boolean isViewInteractable() {
        return false;
    }

}
