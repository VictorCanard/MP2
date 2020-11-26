package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.Cell;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.actor.Wall;
import ch.epfl.cs107.play.game.tutosSolution.Tuto2Behavior;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class SuperPacmanBehavior extends AreaBehavior {

    protected void registerActors (Area area){

        int height = getHeight();
        int width = getWidth();
        for(int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if(getCell(x,y).equals(WALLTYPE)){
                    area.registerActor(new Wall(area,new DiscreteCoordinates(x,y),getNeighborhood()));
                }
            }
        }


        //Il faut iterer sur les cellules je pense et si la cellule est un mur alors il faut appeler cette fonction avec comme params la position du mur dans l'image behavior
    }
    public boolean[][] getNeighborhood(){ //retourne voisinage en 3x3, true si la case contient un mur, faux sinon

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

        public static SuperPacmanBehavior.SuperPacmanCellType toType(int type){
            for(SuperPacmanBehavior.SuperPacmanCellType ict : SuperPacmanBehavior.SuperPacmanCellType.values()){
                if(ict.type == type)
                    return ict;
            }
            // When you add a new color, you can print the int value here before assign it to a type
            System.out.println(type);
            return NONE;
        }
    }
    public SuperPacmanBehavior(Window window, String name){
        super(window, name);
        int height = getHeight();
        int width = getWidth();
        for(int y = 0; y < height; y++) {
            for (int x = 0; x < width ; x++) {
                SuperPacmanBehavior.SuperPacmanCellType color = SuperPacmanBehavior.SuperPacmanCellType.toType(getRGB(height-1-y, x));
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
         * @param type (EnigmeCellType), not null
         */
        public  SuperPacmanCell(int x, int y, SuperPacmanBehavior.SuperPacmanCellType type){
            super(x, y);
            this.type = type;
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


