package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public abstract class SuperPacmanArea extends Area {

    private SuperPacmanBehavior behaviour;

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

        for(Interactor interactor : ){ //Ebauche de la facon de gerer les interactions

            if(interactor.wantsCellInteraction()){
                behaviour.cellInteractionOf(interactor);
            }
        }
        super.update(deltaTime);
    }
}
