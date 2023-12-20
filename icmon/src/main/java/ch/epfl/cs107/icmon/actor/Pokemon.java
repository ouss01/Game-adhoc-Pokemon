package ch.epfl.cs107.icmon.actor;

import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.actor.RPGSprite;
import ch.epfl.cs107.play.engine.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

/**
 * ???
 *
 * @author Hamza REMMAL (hamza.remmal@epfl.ch)
 */
public abstract class Pokemon extends ICMonActor implements ICMonFightableActor {
    private String pokemonName;
    private int hp;
    private int hpMax;
    private int damage;
    private Sprite sprite;

    public Pokemon(Area owner, Orientation orientation, DiscreteCoordinates coordinates,String pokemonName,int hpMax,int damage) {
        super(owner, orientation, coordinates);
        this.pokemonName=pokemonName;
        this.hp=hpMax;
        this.hpMax=hpMax;
        this.damage=damage;
        sprite=new RPGSprite("pokemon/" + pokemonName, 1, 1, this);
    }
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    @Override
    public boolean takeCellSpace() {
        return true;
    }

    public void draw(Canvas canvas) {
        sprite.draw(canvas);
    }
    /**
     * @author Hamza REMMAL (hamza.remmal@epfl.ch)
     */
    public final class PokemonProperties {

        public String name(){
            return null;
        }

        public float hp(){
            return 0f;
        }

        public float maxHp(){
            return 0f;
        }

        public int damage(){
            return 0;
        }

    }

}