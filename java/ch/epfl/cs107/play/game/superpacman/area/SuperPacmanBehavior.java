package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.Cell;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.actor.Wall;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;


import java.util.Arrays;

public class SuperPacmanBehavior extends AreaBehavior {

    protected void registerActors (Area area){

        int height = getHeight();
        int width = getWidth();
        for(int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                SuperPacmanCellType type = SuperPacmanCellType.toType(getRGB(height -1 -y, x));
                if(type.equals(SuperPacmanCellType.WALL)){ //problème, pas d'accès à WALL, pk ?
                    //cells near the position x, y (true = a wall, false = not a wall) (default : false)
                    boolean[][] neighborhood = new boolean[3][3];
                    for(int i = -1; i <= 1; ++i) {
                        for(int j = -1; j <= 1; ++j) {
                            if (SuperPacmanCellType.toType(getRGB(height-1-y+j, x+i)).equals(SuperPacmanCellType.WALL)) {
                                neighborhood[i+1][j+1] = true;
                            }
                        }
                    }
                    area.registerActor(new Wall(area,new DiscreteCoordinates(x,y),neighborhood));
                }
            }
        }

    }

    public enum SuperPacmanCellType{
        NONE(0),                    // never used as real content
        WALL(-16777216),            //black
        FREE_WITH_DIAMOND(-1),      //white
        FREE_WITH_BLINKY(-65536),   //red
        FREE_WITH_PINKY(-157237),   //pink
        FREE_WITH_INKY(-16724737),  //cyan
        FREE_WITH_CHERRY(-36752),   //light red
        FREE_WITH_BONUS(-16478723), //light blue
        FREE_EMPTY(-6118750);       // sort of gray

        final int type;

        SuperPacmanCellType(int type){
            this.type = type;
        }

        public static SuperPacmanCellType toType(int type){
            for(SuperPacmanCellType ict : SuperPacmanCellType.values()){
                if(ict.type == type)
                    return ict;
            }
            // When you add a new color, you can print the int value here before assign it to a type
            System.out.println(type);
            return NONE;
        }
    }

    /**
     * Default SuperPacmanBehavior Constructor
     * @param window (Window), not null
     * @param name (String): Name of the Behavior, not null
     */
    public SuperPacmanBehavior(Window window, String name){
        super(window, name);
        int height = getHeight();
        int width = getWidth();
        for(int y = 0; y < height; y++) {
            for (int x = 0; x < width ; x++) {
                SuperPacmanCellType color = SuperPacmanCellType.toType(getRGB(height-1-y, x));
                setCell(x,y, new SuperPacmanCell(x,y,color));
            }
        }


    }


    public class SuperPacmanCell extends Cell {
        /// Type of the cell following the enum
        private final SuperPacmanBehavior.SuperPacmanCellType type;

        /**
         * Default Tuto2Cell Constructor
         * @param x (int): x coordinate of the cell
         * @param y (int): y coordinate of the cell
         * @param cellType (EnigmeCellType), not null
         */
        public  SuperPacmanCell(int x, int y, SuperPacmanBehavior.SuperPacmanCellType cellType){
            super(x, y);
            type = cellType;
        }

       /* public boolean isDoor() {
            return type == SuperPacmanBehavior.SuperPacmanCellType.DOOR;
        }*/

        @Override
        protected boolean canLeave(Interactable entity) {
            return true;
        }

        @Override
        protected boolean canEnter(Interactable entity) {
            return !entity.takeCellSpace();
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


