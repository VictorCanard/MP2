package ch.epfl.cs107.play.game.superpacman.actor.ghosts;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Path;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanBehavior;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.LinkedList;
import java.util.Queue;

class MovableGhost extends Ghost {
    protected SuperPacmanBehavior behavior;
    protected Path graphicPath;
    protected Queue<Orientation> path;
    protected DiscreteCoordinates targetPos;

    protected int randomX;
    protected int randomY;


    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */
    public MovableGhost(Area area, Orientation orientation, DiscreteCoordinates position, SuperPacmanBehavior behavior) {
        super(area, orientation, position);



        this.behavior = behavior;
        targetPos= GHOST_SPAWN_POSITION;
    }

    @Override
    public void draw(Canvas canvas) {
        if(path != null){
            graphicPath = new Path(this.getPosition(), new LinkedList<Orientation>(path));
            graphicPath.draw(canvas);
        }

        super.draw(canvas);

    }
    @Override
    public void update(float deltaTime) {

        desiredOrientation = getNextOrientation();
        currentAnimation.update(deltaTime);

        super.update(deltaTime);
    }

    protected Orientation getNextOrientation(){
        path = behavior.getShortestPath(getCurrentMainCellCoordinates(),targetPos);

        if(path != null){
            return path.poll();
        }
        else{
            return getRandomOrientation();
        }
    }


}
