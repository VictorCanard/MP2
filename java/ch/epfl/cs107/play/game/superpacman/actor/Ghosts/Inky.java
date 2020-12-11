package ch.epfl.cs107.play.game.superpacman.actor.Ghosts;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGraph;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Path;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanBehavior;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RandomGenerator;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Queue;

public class Inky extends Ghost{
    public static final int MAX_DISTANCE_WHEN_SCARED = 5;
    public static final int MAX_DISTANCE_WHEN_NOT_SCARED = 10;
    //private Sprite sprite;

    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */
    public Inky(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        ghostSprite = RPGSprite.extractSprites("superpacman/ghost.inky", 8, 1, 1, this, 16, 16,new Orientation[] {Orientation.DOWN , Orientation.LEFT , Orientation.UP, Orientation.RIGHT});
        ghostAnimation = Animation.createAnimations(ANIMATION_DURATION / 2, ghostSprite);
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

        orientate(getNextOrientation());
        if (isAfraid()){
            move(FRAME_FOR_MOVE*2);
        }
        else {
            move(FRAME_FOR_MOVE);
        }

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
            do {
                int randomX = RandomGenerator.getInstance().nextInt(width);
                int randomY = RandomGenerator.getInstance().nextInt(height);
                targetPos = new DiscreteCoordinates(randomX, randomY);

            }while (DiscreteCoordinates.distanceBetween(getCurrentMainCellCoordinates(),targetPos) > MAX_DISTANCE_WHEN_NOT_SCARED);
        }

       /* path = SuperPacmanBehavior.getShortestPath(getCurrentMainCellCoordinates(),tragetPos); //pk contexte static ??

        return path.poll();*/

        return Orientation.DOWN; // en attendant de résoudre l'erreur de shortestPath
    }


}
