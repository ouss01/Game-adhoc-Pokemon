package ch.epfl.cs107.icmon;
import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.items.ICMonItem;
import ch.epfl.cs107.icmon.area.maps.Lab;
import ch.epfl.cs107.icmon.area.maps.Arena;
import ch.epfl.cs107.icmon.gamelogic.actions.LogAction;
import ch.epfl.cs107.icmon.gamelogic.actions.RegisterEventAction;
import ch.epfl.cs107.icmon.gamelogic.actions.RegisterinAreaAction;
import ch.epfl.cs107.icmon.gamelogic.actions.StartEventAction;
import ch.epfl.cs107.icmon.gamelogic.events.CollectItemEvent;
import ch.epfl.cs107.icmon.gamelogic.events.EndOfTheGameEvent;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvents;
import ch.epfl.cs107.icmon.message.GamePlayMessage;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.areagame.AreaGame;
import ch.epfl.cs107.play.engine.actor.Actor;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.engine.actor.OrientedAnimation;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.icmon.area.maps.Town ;
import ch.epfl.cs107.play.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.areagame.actor.AreaEntity;

import java.util.ArrayList;
import java.util.List;


public class ICMon extends AreaGame {

    /** ??? */
    private ICMonActor ball;
    private RegisterinAreaAction action;
    private ICMonItem item;
    public final static float CAMERA_SCALE_FACTOR = 13.f;
    /** ??? */
    private final String[] areas = {"Town"};
    /** ??? */
    private ICMonPlayer player;
    /** ??? */
    private int areaIndex;
    private List<ICMonEvents>events=new ArrayList<>();
    private  List<ICMonEvents>completedEvents=new ArrayList<>();
    private  List<ICMonEvents>newEvents=new ArrayList<>();
    private RegisterinAreaAction registerActor;
    private ICMon.ICMonGameState gameState;
    private ICMon.ICMonEventManager eventManager;
    private ArrayList<GamePlayMessage> mailbox=new ArrayList<>();


    /**
     * ???
     */
    private void createAreas() {
        addArea(new Town());
        addArea(new Lab() {
            @Override
            public boolean containsPosition(DiscreteCoordinates destinationCoordinates) {
                return false;
            }
        });
        addArea(new Arena());
    }

    /**
     * ???
     * @param window (Window): display context. Not null
     * @param fileSystem (FileSystem): given file system. Not null
     * @return ???
     */
    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            createAreas();
            initArea(areas[0]);
            ICMonItem ball=new ICBall(getCurrentArea(), Orientation.DOWN,new DiscreteCoordinates(6,6),"items/ICBall");
            registerActor= new RegisterinAreaAction(ball,getCurrentArea());
            registerActor.perform();
            CollectItemEvent collectItemEvent=new CollectItemEvent(ball,player,eventManager);
            events.add(collectItemEvent);
            collectItemEvent.interactWith(player,true);
            collectItemEvent.onStart(new LogAction("CollecttItemEvent started !"));
            collectItemEvent.onComplete(new LogAction("CollectItemEvent completed !"));
            collectItemEvent.start();
            //ball.perform();
            EndOfTheGameEvent endEvent = new EndOfTheGameEvent(player,eventManager);
            endEvent.onStart(new LogAction("the second event has started !"));
            collectItemEvent.onStart(new RegisterEventAction(endEvent, eventManager));
            endEvent.onStart(new LogAction("I heard that you were able to implement this step successfully. Congrats !"));
            collectItemEvent.onComplete(new StartEventAction(endEvent));
            //player.setGameState(gameState);
            //setCurrentArea("Town", true);
            return true;
        }
        return false;
    }

    @Override
    public String getTitle() {
        return "ICMon";
    }
    @Override
    public void end() {
    }
    /**
     * ???
     * @param areaKey ???
     */
    private void initArea(String areaKey) {
        ICMonArea area = (ICMonArea) setCurrentArea(areaKey, true);
        DiscreteCoordinates coords = area.getPlayerSpawnPosition();
        gameState=new ICMonGameState();
        player = new ICMonPlayer((ICMonArea)getCurrentArea(), Orientation.DOWN, new DiscreteCoordinates(5, 5), "Player",gameState);
        player.enterArea(getCurrentArea(), new DiscreteCoordinates(5, 5));
        player.centerCamera();
        //ball=new ICBall(getCurrentArea(), Orientation.DOWN,new DiscreteCoordinates(6,6),"items/ICBall");

    }


    /**
     * ???
     * @param deltaTime elapsed time since last update, in seconds, non-negative
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        Keyboard keyboard= getCurrentArea().getKeyboard();
        if(keyboard.get(keyboard.R).isPressed()){
            begin(getWindow(),getFileSystem());
            newEvents.clear();
            completedEvents.clear();
            events.clear();
        }
        for (GamePlayMessage message : mailbox) {
            message.process();
        }
        mailbox.clear();
        for (ICMonEvents event : events) {
            if(event!=null) {
                // if (event.completed){
                //   completedEvents.add(event);
                // events.remove(event);
                event.update(deltaTime);}}
        events.addAll(newEvents);
        events.remove(completedEvents);
        newEvents.clear();
        completedEvents.clear();
    }

    public class ICMonGameState{
        private ICMonGameState(){}
        public void acceptInteraction(Interactable interactable , boolean isCellInteraction){
            for(var event : ICMon.this.events)
                interactable.acceptInteraction(event , isCellInteraction);
        }
        public Area switchArea(String areaName){
            return setCurrentArea(areaName,false);
        }
        public void send(GamePlayMessage message){
            mailbox.add(message);
        }

    }
    public class  ICMonEventManager{
        public void registerEvent (ICMonEvents event ) {
            if (event != null){newEvents.add(event);}
        }
        public void unRegisterEvent (ICMonEvents event ){
            if (event != null){
                completedEvents.add(event);
            }
        }

    }
}