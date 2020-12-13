package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.Signal;
import ch.epfl.cs107.play.signal.logic.Logic;

public class Level0 extends SuperPacmanArea {
    public static final DiscreteCoordinates PLAYER1_SPAWN_POSTION = new DiscreteCoordinates(10,1);
    public static final DiscreteCoordinates PLAYER2_SPAWN_POSTION = new DiscreteCoordinates(12,1);


    public String getTitle() {
        return "superpacman/Level0";
    }
    

    public void createArea() {
        DiscreteCoordinates pos = Level1.PLAYER1_SPAWN_POSTION;

        registerActor(new Door("superpacman/Level1", pos, Logic.TRUE, this, Orientation.UP,
                new DiscreteCoordinates(5,9), new DiscreteCoordinates(6,9) ));

    }



    public boolean isOn() { //REDEFINE THIS METHOD TO MAKE LEVEL0 LIGHTUP WHEN KEY IS COLLECTED
        return false;
    }

    @Override
    public int getHeight() {
        return super.getHeight();
    }

    @Override
    public int getWidth() {
        return super.getWidth();
    }
}
