package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public class Level0 extends SuperPacmanArea{
    public static final DiscreteCoordinates PLAYER_SPAWN_POSTION = new DiscreteCoordinates(10,1);
    public String getTitle() {
        return "superpacman/Level0";
    }



    protected void createArea() {
        DiscreteCoordinates pos = Level1.PLAYER_SPAWN_POSTION;
        registerActor(new Door("superpacman/Level1", pos, Logic.TRUE, this, Orientation.UP,
                new DiscreteCoordinates(5,9), new DiscreteCoordinates(6,9) ));
        registerActor(new Background(this));
        registerActor(new Foreground(this));
    }

}
