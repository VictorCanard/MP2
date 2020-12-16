package ch.epfl.cs107.play.game.superpacman.actor.gates;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanBehavior;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class DiamondGate extends Gate {

    public DiamondGate(Area area, Orientation orientation, DiscreteCoordinates position, SuperPacmanBehavior behavior) {
        super(area, orientation, position, behavior);

        createGateSprites(orientation);

        setSignalOff();


    }

    @Override
    public boolean takeCellSpace() {
        return ((SuperPacmanArea)getOwnerArea()).isOff() ;
    }
}
