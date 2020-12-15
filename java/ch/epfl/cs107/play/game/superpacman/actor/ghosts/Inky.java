package ch.epfl.cs107.play.game.superpacman.actor.ghosts;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.SuperPacman;
import ch.epfl.cs107.play.game.superpacman.actor.player.SuperPacmanPlayer;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanBehavior;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RandomGenerator;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Queue;

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
    public Inky(Area area, Orientation orientation, DiscreteCoordinates position, SuperPacmanBehavior behavior) {
        super(area, orientation, position);

        ghostSprite = RPGSprite.extractSprites("superpacman/ghost.inky", 8, 1, 1, this, 16, 16,new Orientation[] {Orientation.DOWN , Orientation.LEFT , Orientation.UP, Orientation.RIGHT});
        ghostAnimation = Animation.createAnimations(ANIMATION_DURATION / 2, ghostSprite);

        currentAnimation = ghostAnimation[0];

        this.behavior = behavior;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (!isAfraid()){
            if(ghostAnimation != null)
                ghostAnimation[0].draw(canvas);
        }
    }

    @Override
    public void update(float deltaTime) {

        desiredOrientation = getNextOrientation();
        if (isAfraid()){
            move(FRAME_FOR_MOVE*2);
        }
        else {
            move(FRAME_FOR_MOVE);
        }

        currentAnimation.update(deltaTime);
        super.update(deltaTime);
    }

    public Orientation getNextOrientation(){
        int height = getOwnerArea().getHeight();
        int width = getOwnerArea().getWidth();
        DiscreteCoordinates targetPos = null;
        Queue<Orientation> path;
        //recherche de case aléatoire
        if (isAfraid()) {
            do {
                int randomX = RandomGenerator.getInstance().nextInt(width);
                int randomY = RandomGenerator.getInstance().nextInt(height);
                targetPos = new DiscreteCoordinates(randomX, randomY);

            }while (DiscreteCoordinates.distanceBetween(getCurrentMainCellCoordinates(),targetPos) > MAX_DISTANCE_WHEN_SCARED);
        }

        if (!isAfraid()) {
            if (player == null) {
                do {
                    return getRandomOrientation();
                    /*int randomX = RandomGenerator.getInstance().nextInt(width);
                    int randomY = RandomGenerator.getInstance().nextInt(height);
                    targetPos = new DiscreteCoordinates(randomX, randomY);

                     */

                } while (DiscreteCoordinates.distanceBetween(getCurrentMainCellCoordinates(), targetPos) > MAX_DISTANCE_WHEN_NOT_SCARED);
            }

        }
        else {

            targetPos = player.getCurrentMainCellCoordinates();
        }


        path = behavior.getShortestPath(getCurrentMainCellCoordinates(),targetPos); //pk contexte static ??

        return path.poll();


    }


}
