package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public abstract class SuperPacmanArea extends Area {
    private DiscreteCoordinates PLAYER_SPAWN_POSITION;
    private SuperPacmanBehavior behaviour;

    @Override
    public float getCameraScaleFactor() {
        return 15.f;
    }

    @Override
    public String getTitle() {
        return null;
    }

    public void setBehaviour(SuperPacmanBehavior behaviour){
        this.behaviour  =behaviour;
        super.setBehavior(behaviour);
    }
    public void registerActors(){
        behaviour.registerActors(this);
    }




}
