package ch.epfl.cs107.play.game.superpacman.actor.ghosts;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Path;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanBehavior;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class MovableGhost extends Ghost {
    protected SuperPacmanBehavior behavior;
    protected Path graphicPath;
    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */
    public MovableGhost(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (!isAfraid()){
            if(ghostAnimation != null)
                ghostAnimation[0].draw(canvas);
        }
    }


}
