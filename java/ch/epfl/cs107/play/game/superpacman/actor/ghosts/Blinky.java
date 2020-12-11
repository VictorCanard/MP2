package ch.epfl.cs107.play.game.superpacman.actor.ghosts;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RandomGenerator;
import ch.epfl.cs107.play.window.Canvas;

public class Blinky extends Ghost{
    private final int MAX = 4;

    private int randomInt;



    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */
    public Blinky(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);

        ghostSprite = RPGSprite.extractSprites("superpacman/ghost.blinky", 2, 1, 1, this, 16, 16, new Orientation[] {Orientation.UP , Orientation.RIGHT , Orientation.DOWN, Orientation.LEFT});
        ghostAnimation = Animation.createAnimations(ANIMATION_DURATION / 2, ghostSprite);

        currentAnimation = ghostAnimation[0];

    }

    @Override
    public void update(float deltaTime) {

        desiredOrientation = getNextOrientation();

        currentAnimation =getNewCurrentAnimation(desiredOrientation, ghostAnimation);

        currentAnimation.update(deltaTime);

        super.update(deltaTime);
    }


    private Orientation getNextOrientation(){
        randomInt = RandomGenerator.getInstance().nextInt(MAX);

        return Orientation.fromInt(randomInt);

    }

    public void draw(Canvas canvas){

        if(player != null){
            if(isAfraid()){
                super.draw(canvas);
            }
            else{
                currentAnimation.draw(canvas);
            }
        }

        else{
            currentAnimation.draw(canvas);
        }





    }
}
