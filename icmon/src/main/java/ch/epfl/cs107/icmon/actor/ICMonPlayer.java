package ch.epfl.cs107.icmon.actor;
import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.actor.npc.NPCActor;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.icmon.gamelogic.actions.LogAction;
import ch.epfl.cs107.icmon.gamelogic.events.PokemonFightEvent;
import ch.epfl.cs107.icmon.message.PassDoorMessage;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.area.ICMonBehavior;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.actor.Interactor;
import ch.epfl.cs107.play.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.engine.actor.Dialog;
import ch.epfl.cs107.play.engine.actor.OrientedAnimation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import org.apache.commons.io.filefilter.FalseFileFilter;

import javax.swing.text.Position;
import java.util.Collections;
import java.util.List;

import static ch.epfl.cs107.icmon.area.ICMonBehavior.AllowedWalkingType.FEET;
import static ch.epfl.cs107.icmon.area.ICMonBehavior.AllowedWalkingType.SURF;

public final class ICMonPlayer extends ICMonActor implements Interactor {

    /** ??? */
    private final static int MOVE_DURATION = 8;

    private OrientedAnimation AnimationOnLand =new OrientedAnimation("actors/player" , 8/2, Orientation.DOWN , this);
    private OrientedAnimation AnimatiomInWater =new OrientedAnimation("actors/player_water" , 8/2, Orientation.DOWN , this);
    private OrientedAnimation currentAnimation=new OrientedAnimation("actors/player" , 8/2, Orientation.DOWN , this);
    //private final TextGraphics message;

    //private final Sprite sprite;
    //private float hp;
    private final ICMonPlayerInteractionHandler handler;
    private ICMon.ICMonGameState gameState;
    private Dialog dialog=new Dialog("CollecttItemEvent started !");
    private boolean isInDialog;
    /**
     * ???
     * @param owner ???
     * @param orientation ???
     * @param coordinates ???
     * @param spriteName ???
     */
    public ICMonPlayer(Area owner, Orientation orientation, DiscreteCoordinates coordinates, String spriteName,ICMon.ICMonGameState gameState) {
        super(owner, orientation, coordinates);
        resetMotion();
        this.gameState=gameState;
        handler=new ICMonPlayerInteractionHandler();
    }

   // public void setGameState(ICMon.ICMonGameState gameState) {
     //   this.gameState = gameState;
    //}

    /**
     * ???
     * @param deltaTime elapsed time since last update, in seconds, non-negative
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Keyboard keyboard = getOwnerArea().getKeyboard();
        moveIfPressed(Orientation.LEFT, keyboard.get(Keyboard.LEFT));
        moveIfPressed(Orientation.UP, keyboard.get(Keyboard.UP));
        moveIfPressed(Orientation.RIGHT, keyboard.get(Keyboard.RIGHT));
        moveIfPressed(Orientation.DOWN, keyboard.get(Keyboard.DOWN));
        if (isDisplacementOccurs()){
            currentAnimation.update(deltaTime);
        }else{
            currentAnimation.reset();

        }
        if((keyboard.get(Keyboard.SPACE).isPressed())&&(isInDialog)){
            dialog.update(deltaTime);
            if((dialog.isCompleted())&&(dialog!=null)){
                isInDialog= false;
                dialog=null;
            }
        }

    }

    /**
     * ???
     * @param canvas target, not null
     */
    //@Override
    //public void draw(ch.epfl.cs107.play.window.Canvas canvas) {
    //sprite.draw(canvas);
    //message.draw(canvas);}

    @Override
    public void draw(Canvas canvas) {
        currentAnimation.draw(canvas);
        if (!isInDialog){
            dialog.draw(canvas);}
    }


    /**
     * ???
     * @return ???
     */
    @Override
    public boolean takeCellSpace() {
        return true;
    }

    /**
     * ???
     * @return ???
     */
    @Override
    public boolean isCellInteractable() {
        return true;
    }

    /**
     * ???
     * @return ???
     */
    @Override
    public boolean isViewInteractable() {
        return true;
    }

    /**
     * ???
     */
    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    @Override
    public List<DiscreteCoordinates> getFieldOfViewCells() {
        return Collections.singletonList
                (getCurrentMainCellCoordinates().jump(getOrientation().toVector()));
    }

    @Override
    public boolean wantsCellInteraction() {
        return true;
    }

    @Override
    public boolean wantsViewInteraction() {
        Keyboard keyboard= getOwnerArea().getKeyboard();
        return keyboard.get(keyboard.L).isPressed();//&&(dialog)
    }

    @Override
    public void interactWith(Interactable other, boolean isCellInteraction) {
        //this.gameState.acceptInteraction(other,isCellInteraction);
        other.acceptInteraction(handler , isCellInteraction);
        gameState.acceptInteraction(other, isCellInteraction);
    }


    /**
     * ???
     * @param v (AreaInteractionVisitor) : the visitor
     * @param isCellInteraction ???
     */
    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this , isCellInteraction);
    }

    /**
     * Orientate and Move this player in the given orientation if the given button is down
     *
     * @param orientation (Orientation): given orientation, not null
     * @param b           (Button): button corresponding to the given orientation, not null
     */
    private void moveIfPressed(Orientation orientation, Button b) {
        if (b.isDown()) {
            if (!isDisplacementOccurs()) {
                orientate(orientation);
                move(MOVE_DURATION);
                currentAnimation.orientate(orientation);
            }
        }
    }

    /**
     * Leave an area by unregister this player
     */
    public void leaveArea() {
        getOwnerArea().unregisterActor(this);
    }

    /**
     * ???
     * @param area     (Area): initial area, not null
     * @param position (DiscreteCoordinates): initial position, not null
     */
    public void enterArea(Area area, DiscreteCoordinates position) {
        area.registerActor(this);
        area.setViewCandidate(this);
        setOwnerArea(area);
        setCurrentPosition(position.toVector());
        resetMotion();
    }

    /**
     * ???
     * @return ???
     */

    /**
     * Center the camera on the player
     */
    public void centerCamera() {
        getOwnerArea().setViewCandidate(this);

    }

    public void openDialog(Dialog dialog) {
         this.dialog=dialog;
         isInDialog=true;
    }
    public void fight(ICMonFightableActor fightableActor){

    }

    public void enterArea(ICMonArea destinationArea, Position entryPosition) {
    }

    private class ICMonPlayerInteractionHandler implements ICMonInteractionVisitor {

        @Override
        public void interactWith(ICBall ball, boolean isCellInteraction) {
            if (!isCellInteraction) {
                ball.collect();
            }
        }
        @Override
        public void interactWith(Door door, boolean isCellInteraction) {
            if (isCellInteraction){
                PassDoorMessage message = new PassDoorMessage(door,ICMonPlayer.this,gameState);
                gameState.send(message);
            }
        }


        @Override
        public void interactWith(ICShopAssistant assistant, boolean isCellInteraction) {
            ICMonInteractionVisitor.super.interactWith(assistant,isCellInteraction);
        }
        public void interactWith(Interactable interactable , boolean isCellInteraction){
            interactable.acceptInteraction(handler,isCellInteraction);
            gameState.acceptInteraction(interactable,isCellInteraction);

        }

        @Override
        public void interactWith(ICMonPlayer player, boolean isCellInteraction) {
        }

        @Override
        public void interactWith(ICMonBehavior.ICMonCell other, boolean isCellInteraction) {
            if (isCellInteraction) {
                if (other.getType() == FEET) {
                    currentAnimation=AnimationOnLand;
                } else if (other.getType() == SURF) {
                    currentAnimation=AnimatiomInWater;
                }
            }
        }

    }}


