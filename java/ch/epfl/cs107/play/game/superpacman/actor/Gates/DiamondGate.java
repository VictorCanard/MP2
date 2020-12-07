package ch.epfl.cs107.play.game.superpacman.actor.Gates;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class DiamondGate extends Gate {

    public DiamondGate(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);

        createGateSprites(orientation);

    }

    @Override
    public boolean takeCellSpace() {
        return ((SuperPacmanArea)getOwnerArea()).numberOfDiamonds != 0;
    }
}
