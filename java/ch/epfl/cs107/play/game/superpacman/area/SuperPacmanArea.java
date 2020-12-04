package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AutomaticallyCollectableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.superpacman.actor.Key;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

import java.util.List;

public abstract class SuperPacmanArea extends Area implements Logic {

    private SuperPacmanBehavior behaviour;
    public static int numberOfDiamonds = 0;

    @Override
    public float getCameraScaleFactor() {
        return 30.f;
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

    @Override
    public void update(float deltaTime) {

        super.update(deltaTime);
    }
    @Override
    public boolean isOff() {
        return numberOfDiamonds != 0;
    }

    @Override
    public float getIntensity() {
        return 0;
    }



}
