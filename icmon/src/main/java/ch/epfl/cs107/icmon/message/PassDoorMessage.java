package ch.epfl.cs107.icmon.message;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

import javax.swing.text.Position;

public class PassDoorMessage implements GamePlayMessage{
    private Door door;
    private ICMonPlayer player;
    private ICMon.ICMonGameState gameState;
    private DiscreteCoordinates entryPoint;
    public PassDoorMessage(Door door, ICMonPlayer player, ICMon.ICMonGameState gameState){
        this.door=door;
        this.player=player;
        this.gameState=gameState;
        this.entryPoint=entryPoint;
    }




    @Override
    public void process() {

        player.leaveArea();
        ICMonArea destinationArea = (ICMonArea) gameState.switchArea(door.getDestinationAreaName());

        // Use the stored entry point if available; otherwise, use the player's spawn position
        DiscreteCoordinates destinationCoordinates = door.getDestinationCoordinates();

        // Extract the class name from the toString() representation
        String destinationAreaInfo = (destinationArea != null) ? destinationArea.toString() : "Unknown";
        int atIndex = destinationAreaInfo.lastIndexOf('@');
        destinationAreaInfo = (atIndex != -1) ? destinationAreaInfo.substring(0, atIndex) : destinationAreaInfo;

        // Initialize entryPoint to null
        DiscreteCoordinates entryPoint = null;

        // Use the player's spawn position as the default entry point
        entryPoint = (entryPoint != null && destinationArea.containsPosition(entryPoint))
                ? entryPoint
                : (destinationCoordinates != null && destinationArea.containsPosition(destinationCoordinates))
                ? destinationCoordinates
                : destinationArea.getPlayerSpawnPosition();

        System.err.println("rakah zabour om l area " + destinationAreaInfo);

        // Enter the area with the determined entry point
        player.enterArea(destinationArea, entryPoint);

        // Store the entry point for future use
        this.entryPoint = entryPoint;  // Assuming entryPoint is a field in your class

        // player.getFieldOfViewCells(); // Uncomment if needed

    }




}
