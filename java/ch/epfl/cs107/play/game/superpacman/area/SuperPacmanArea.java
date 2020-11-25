package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;

public class SuperPacmanArea extends Area {
    @Override
    public float getCameraScaleFactor() {
        return 15.f;
    }

    @Override
    public String getTitle() {
        return null;
    }
}
