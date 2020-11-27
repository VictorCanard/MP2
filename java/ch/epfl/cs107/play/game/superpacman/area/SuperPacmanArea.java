package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public abstract class SuperPacmanArea extends Area {
    DiscreteCoordinates PLAYER_SPAWN_POSITION;
    @Override
    public float getCameraScaleFactor() {
        return 15.f;
    }

    @Override
    public String getTitle() {
        return null;
    }

    //abstract public DiscreteCoordinates getSpawnPosition();


}
