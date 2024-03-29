package ch.epfl.cs107.play.game.superpacman.actor.ghosts;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Path;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanBehavior;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RandomGenerator;
import ch.epfl.cs107.play.window.Canvas;

import java.util.LinkedList;
import java.util.Queue;

public class Pinky extends MovableGhost{
    public static final int MIN_WHEN_SCARED = 5;
    public static final int MAX_RANDOM_ATTEMPT = 200;


    private int randomAttempts =0;



    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */
    public Pinky(Area area, Orientation orientation, DiscreteCoordinates position, SuperPacmanBehavior behavior) {
        super(area, orientation, position, behavior);
        ghostSprite = RPGSprite.extractSprites("superpacman/ghost.pinky", 2, 1, 1, this, 16, 16,new Orientation[] {Orientation.DOWN , Orientation.LEFT , Orientation.UP, Orientation.RIGHT});

        ghostAnimation = Animation.createAnimations(ANIMATION_DURATION / 2, ghostSprite);

        currentAnimation = ghostAnimation[0];
    }





    @Override
    protected Orientation getNextOrientation(){

        //recherche de case aléatoire

        if(player == null){
            return getRandomOrientation();
        }
        else{
            if (isAfraid()) {
                do {

                    int randomX = RandomGenerator.getInstance().nextInt(width);
                    int randomY = RandomGenerator.getInstance().nextInt(height);
                    targetPos = new DiscreteCoordinates(randomX, randomY);
                    randomAttempts++;

                }while (DiscreteCoordinates.distanceBetween(getCurrentMainCellCoordinates(),targetPos) < MIN_WHEN_SCARED
                        && randomAttempts < MAX_RANDOM_ATTEMPT);
            }
            else{
                targetPos = player.getCurrentMainCellCoordinates();
            }
        }

        return super.getNextOrientation();

    }
}
