package ch.epfl.cs107.play.game.superpacman.actor.Ghosts;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Inky extends MovableGhost{
    public static final int MAX_DISTANCE_WHEN_SCARED = 5;
    public static final int MAX_DISTANCE_WHEN_NOT_SCARED = 10;

    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */
    public Inky(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    public void move(){
        //int height = getOwnerArea().getHeight();
        //recherche de case aléatoire
        /*if (this.getIsAfraid()){
            do {

            }
        }*/
    }
}
