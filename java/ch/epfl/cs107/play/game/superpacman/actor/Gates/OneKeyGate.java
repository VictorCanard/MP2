package ch.epfl.cs107.play.game.superpacman.actor.Gates;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.superpacman.actor.Key;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class OneKeyGate extends Gate{
    private Key key;

    /**
     * Default AreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity in the Area. Not null
     * @param position    (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public OneKeyGate(Area area, Orientation orientation, DiscreteCoordinates position, Key key1) {
        super(area, orientation, position);
        this.key = key1;
    }

    @Override
    public void update(float deltaTime) {
        if(key.getSignal().isOn()){
            unregisterActor();
        }
        super.update(deltaTime);
    }

    @Override
    public boolean takeCellSpace() {
        return key.getSignal().isOff();
    }
}
