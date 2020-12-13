package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.Signal;
import ch.epfl.cs107.play.signal.logic.Logic;

public class Level1 extends SuperPacmanArea {
    public static final DiscreteCoordinates PLAYER1_SPAWN_POSTION =new DiscreteCoordinates(15,6);
    public static final DiscreteCoordinates PLAYER2_SPAWN_POSTION = new DiscreteCoordinates(13,6);

    public String getTitle() {
        return "superpacman/Level1";
    }


    public void createArea() {
        DiscreteCoordinates pos = Level2.PLAYER1_SPAWN_POSTION;
        registerActor(new Door("superpacman/Level2", pos, Logic.TRUE, this, Orientation.DOWN,
                new DiscreteCoordinates(14,0), new DiscreteCoordinates(15,0) ));

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
