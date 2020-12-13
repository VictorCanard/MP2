package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.game.rpg.actor.Sign;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.Signal;
import ch.epfl.cs107.play.signal.logic.Logic;

public class Level2 extends SuperPacmanArea {
    public static final DiscreteCoordinates PLAYER1_SPAWN_POSTION =new DiscreteCoordinates(15,29);
    public static final DiscreteCoordinates PLAYER2_SPAWN_POSTION = new DiscreteCoordinates(17,29);
    
    public String getTitle() {
        return "superpacman/Level2";
    }



    public void createArea() {

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
