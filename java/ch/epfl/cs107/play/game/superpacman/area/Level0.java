package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level0 extends SuperPacmanArea{
    private DiscreteCoordinates PLAYER_SPAWN_POSTION = new DiscreteCoordinates(10,1);
    public String getTitle() {
        return "superpacman/Level0";
    }

    @Override
    public DiscreteCoordinates getSpawnPosition() {
        return PLAYER_SPAWN_POSITION;
    }

    protected void createArea() {

    }

}
