package ch.epfl.cs107.play.game.superpacman.actor.gates;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.superpacman.actor.collectables.Key;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanBehavior;
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
    public OneKeyGate(Area area, Orientation orientation, DiscreteCoordinates position, Key key1, SuperPacmanBehavior behavior) {
        super(area, orientation, position, behavior);
        this.key = key1;

        setSignalOff();
    }



    @Override
    public boolean takeCellSpace() {
        return key.isOff();
    }
}
