package ch.epfl.cs107.play.game.areagame.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public abstract class AutomaticallyCollectableAreaEntity extends CollectableAreaEntity {
    /**
         * Default AreaEntity constructor
         *
         * @param area        (Area): Owner area. Not null
         * @param orientation (Orientation): Initial orientation of the entity in the Area. Not null
         * @param position    (DiscreteCoordinate): Initial position of the entity in the Area. Not null
         */

    public AutomaticallyCollectableAreaEntity(Area area, Orientation orientation, DiscreteCoordinates position) {
            super(area, orientation, position);
        }

    public void collect(){
       getOwnerArea().unregisterActor(this);
    }

}
