package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Window;

public abstract class SuperPacmanArea extends Area implements Logic {

    public static DiscreteCoordinates PLAYER_SPAWN_POSITION;
    private SuperPacmanBehavior behaviour;
    public int numberOfDiamonds = 0;

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

    public void createArea(){

    }
    public void registerActors(){
        behaviour.registerActors(this);
    }


    @Override
    public void update(float deltaTime) {

        super.update(deltaTime);
    }

    @Override
    public boolean isOn() {
        return numberOfDiamonds == 0;
    }

    @Override
    public boolean isOff(){
        return numberOfDiamonds != 0;
    }

    @Override
    public float getIntensity() {
        return 0;
    }

    /*
    @Override

    public boolean resume(Window window, FileSystem fileSystem){
        window.
        window.wait();
        fileSystem.wait();
    }

    @Override
    public void suspend(){
        while(){

        }

    }

     */



}
