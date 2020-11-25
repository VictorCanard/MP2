package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level1 extends SuperPacmanArea{
    private DiscreteCoordinates PLAYER_SPAWN_POSTION =new DiscreteCoordinates(15,6);
    public String getTitle() {
        return "superpacman/Level1";
    }

    @Override
    public DiscreteCoordinates getSpawnPosition() {
        return PLAYER_SPAWN_POSITION;
    }

    protected void createArea() {

    }
}
