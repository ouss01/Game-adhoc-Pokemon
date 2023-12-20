package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFight;

public class PokemonFightEvent extends ICMonEvents {
    private ICMonFight pauseMenu;

    public PokemonFightEvent(ICMonPlayer player, ICMon.ICMonEventManager eventManager,ICMonFight pauseMenu) {
        super(player, eventManager);
        this.pauseMenu=pauseMenu;
    }

    @Override
    public void update(float deltaTime) {

    }
}
