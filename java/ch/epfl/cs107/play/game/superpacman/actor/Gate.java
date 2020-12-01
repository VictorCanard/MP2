package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.signal.Signal;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public class Gate extends AreaEntity {
    Logic signal;
    Sprite sprite;
    Sprite[] sprites;
    /**
     * Default AreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity in the Area. Not null
     * @param position    (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public Gate(Area area, Orientation orientation, DiscreteCoordinates position, Logic signal) {
        super(area, orientation, position);
        this.signal = signal;
        sprites = RPGSprite.extractSprites("superpacman/gate", 2, 1, 1, this, 64, 64);
        if (orientation == Orientation.UP || orientation == Orientation.DOWN){
            sprite = new RPGSprite("superpacman/gate",1,1,this, new RegionOfInterest(0,0,64,64));
        }
        if (orientation == Orientation.RIGHT || orientation == Orientation.LEFT){
            sprite = new RPGSprite("superpacman/gate",1,1,this, new RegionOfInterest(0,64,64,64));
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if(sprite != null)
            sprite.draw(canvas);
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    @Override
    public boolean takeCellSpace() {
        return signal.isOff();
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {

    }
}
