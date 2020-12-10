package ch.epfl.cs107.play.game.superpacman.actor.Ghosts;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGraph;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Path;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanBehavior;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RandomGenerator;

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
    public Inky(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
    }

    @Override
    public void update(float deltaTime) {
        getNextOrientation();
        super.update(deltaTime);
    }

    public Orientation getNextOrientation(){
        int height = getOwnerArea().getHeight();
        int width = getOwnerArea().getWidth();
        DiscreteCoordinates tragetPos = null;
        Queue<Orientation> path;
        //recherche de case aléatoire
        if (this.getIsAfraid()) {
            do {
                int randomX = RandomGenerator.getInstance().nextInt(width);
                int randomY = RandomGenerator.getInstance().nextInt(height);
                tragetPos = new DiscreteCoordinates(randomX, randomY);

            }while (DiscreteCoordinates.distanceBetween(getCurrentMainCellCoordinates(),tragetPos) > MAX_DISTANCE_WHEN_SCARED);
        }

       /* path = SuperPacmanBehavior.getShortestPath(getCurrentMainCellCoordinates(),tragetPos); //pk contexte static ??

        return path.poll();*/

        return Orientation.DOWN; // en attendant de résoudre l'erreur de shortestPath
    }


}
