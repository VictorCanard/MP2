package ch.epfl.cs107.play.game.tutos;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.Cell;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

import java.lang.reflect.Type;

public class Tuto2Behavior extends AreaBehavior {

    /**
     * Default AreaBehavior Construcor
     *
     * @param window (Window): graphic context, not null
     * @param name   (String): name of the behavior image, not null
     */
    public Tuto2Behavior(Window window, String name) {

        super(window, name);



        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                Tuto2CellType color = Tuto2CellType.toType(getRGB(getHeight()-1-y,x));
                setCell(x,y,new Tuto2Cell(x,y,color));

            }
        }

    }

    public enum Tuto2CellType{
        NULL(0, false),
        WALL(-16777216, false),
        IMPASSABLE(-8750470, false),
        INTERACT(-256, true),
        DOOR(-195580, true),
        WALKABLE(-1, true),;

        final int type;
        final boolean isWalkable;



        Tuto2CellType(int type, boolean isWalkable){
            this.type = type;
            this.isWalkable=isWalkable;
        }
        static Tuto2CellType toType(int type){
            for (Tuto2CellType val: Tuto2CellType.values()) {
                if(type == val.type){
                    return val;
                }
            }
            return null;
        }

    }
    public class Tuto2Cell extends Cell{
        private final Tuto2CellType type;

        /**
         * Default Cell constructor
         *
         * @param x (int): x-coordinate of this cell
         * @param y (int): y-coordinate of this cell
         */
        public Tuto2Cell(int x, int y, Tuto2CellType type) {
            super(x, y);
            this.type = type;
        }

        @Override
        protected boolean canLeave(Interactable entity) {
            return true;
        }

        @Override
        protected boolean canEnter(Interactable entity) {
            return type.isWalkable;
        }

        @Override
        public boolean isCellInteractable() {
            return true;
        }

        @Override
        public boolean isViewInteractable() {
            return false;
        }

        @Override
        public void acceptInteraction(AreaInteractionVisitor v) {

        }
    }
}

