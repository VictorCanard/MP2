package ch.epfl.cs107.play.game.superpacman.actor.gates;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.superpacman.actor.collectables.Key;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanBehavior;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class TwoKeysGate extends Gate {
    private Key key1;
    private Key key2;

    public TwoKeysGate(Area area, Orientation orientation, DiscreteCoordinates position, Key key1, Key key2, SuperPacmanBehavior behavior) {
        super(area, orientation, position, behavior);
        this.key1 = key1;
        this.key2 = key2;

        setSignalOff();
    }


    @Override
    public boolean takeCellSpace() {
        return key1.isOff() || key2.isOff();
    }
}
