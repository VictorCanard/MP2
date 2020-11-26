package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level2 extends SuperPacmanArea {
    public static final DiscreteCoordinates PLAYER_SPAWN_POSTION =new DiscreteCoordinates(15,6);
    
    public String getTitle() {
        return "superpacman/Level2";
    }

    @Override
    public DiscreteCoordinates getSpawnPosition() {
        return PLAYER_SPAWN_POSITION;
    }

    protected void createArea() {
        registerActor(new Background(this));
        registerActor(new Foreground(this));
    }

}
