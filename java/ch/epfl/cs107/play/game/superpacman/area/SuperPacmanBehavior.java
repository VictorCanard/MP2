package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.AreaGraph;
import ch.epfl.cs107.play.game.areagame.Cell;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.Wall;
import ch.epfl.cs107.play.game.superpacman.actor.collectables.Bonus;
import ch.epfl.cs107.play.game.superpacman.actor.collectables.Cherry;
import ch.epfl.cs107.play.game.superpacman.actor.collectables.Diamond;
import ch.epfl.cs107.play.game.superpacman.actor.collectables.Key;
import ch.epfl.cs107.play.game.superpacman.actor.gates.DiamondGate;
import ch.epfl.cs107.play.game.superpacman.actor.gates.OneKeyGate;
import ch.epfl.cs107.play.game.superpacman.actor.gates.TwoKeysGate;
import ch.epfl.cs107.play.game.superpacman.actor.ghosts.Blinky;
import ch.epfl.cs107.play.game.superpacman.actor.ghosts.Inky;
import ch.epfl.cs107.play.game.superpacman.actor.ghosts.Pinky;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

import java.util.Queue;

public class SuperPacmanBehavior extends AreaBehavior {
    private boolean[][] neighborhood;
    AreaGraph graph;
    private int height = getHeight();
    private int width = getWidth();

    public  Queue<Orientation> getShortestPath(DiscreteCoordinates currentPos, DiscreteCoordinates targetPos){
        Queue<Orientation> path = graph.shortestPath(currentPos, targetPos);
        return path;
    }

    public void setSignalOff(DiscreteCoordinates position){

        graph.setSignal(position, Logic.FALSE);
    }

    protected void registerActors (Area area){


        int height = getHeight();
        int width = getWidth();

        for(int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                DiscreteCoordinates position = new DiscreteCoordinates(x,y);

                switch (getType(getCell(x,y))){
                    case WALL:
                        neighborhood = getNeighborhood(x,y);
                        area.registerActor(new Wall(area,position,neighborhood));
                        break;
                    case FREE_WITH_CHERRY:
                        area.registerActor(new Cherry(area, Orientation.UP, position));
                        break;
                    case FREE_WITH_DIAMOND:
                        area.registerActor(new Diamond(area, Orientation.UP, position));
                        ((SuperPacmanArea)area).numberOfDiamonds ++;
                        break;
                    case FREE_WITH_BONUS:
                        area.registerActor(new Bonus(area,Orientation.UP, position));
                        break;



                    case FREE_WITH_BLINKY:
                        area.registerActor(new Blinky(area, Orientation.UP,position));
                        break;


                    case FREE_WITH_INKY:
                        area.registerActor(new Inky(area, Orientation.UP,position, this));
                        break;

                    case FREE_WITH_PINKY:
                        area.registerActor(new Pinky(area, Orientation.UP,position, this));



                }


            }
        }

        switch (area.getTitle()){ //positionement des clés et des gates
            case "superpacman/Level0":
                Key key = new Key(area,Orientation.UP,new DiscreteCoordinates(3,4));

                area.registerActor(key);

                area.registerActor(new OneKeyGate(area,Orientation.RIGHT,new DiscreteCoordinates(5,8), key, this));
                area.registerActor(new OneKeyGate(area,Orientation.LEFT,new DiscreteCoordinates(6,8),key, this));

                break;
            case "superpacman/Level1":
                area.registerActor(new DiamondGate(area,Orientation.RIGHT, new DiscreteCoordinates(14,3), this));
                area.registerActor(new DiamondGate(area,Orientation.RIGHT, new DiscreteCoordinates(15,3), this));
                break;
            case "superpacman/Level2":
                Key key1 = new Key(area,Orientation.UP, new DiscreteCoordinates(3,16));
                Key key2 = new Key(area,Orientation.UP, new DiscreteCoordinates(3,16));
                Key key3 = new Key(area,Orientation.UP, new DiscreteCoordinates(2,8));
                Key key4 = new Key(area,Orientation.UP, new DiscreteCoordinates(27,8));

                area.registerActor(key1);
                area.registerActor(key2);
                area.registerActor(key3);
                area.registerActor(key4);

                area.registerActor(new OneKeyGate(area, Orientation.RIGHT, new DiscreteCoordinates(8,14), key1, this));
                area.registerActor(new OneKeyGate(area, Orientation.DOWN, new DiscreteCoordinates(5,12), key1, this));
                area.registerActor(new OneKeyGate(area, Orientation.RIGHT, new DiscreteCoordinates(8,10), key1, this));
                area.registerActor(new OneKeyGate(area, Orientation.RIGHT, new DiscreteCoordinates(8,8), key1, this));


                area.registerActor(new OneKeyGate(area, Orientation.RIGHT, new DiscreteCoordinates(21,14), key2, this));
                area.registerActor(new OneKeyGate(area, Orientation.DOWN, new DiscreteCoordinates(24,12), key2, this));
                area.registerActor(new OneKeyGate(area, Orientation.RIGHT, new DiscreteCoordinates(21,10), key2, this));
                area.registerActor(new OneKeyGate(area, Orientation.RIGHT, new DiscreteCoordinates(21,8), key2, this));

                area.registerActor(new TwoKeysGate(area, Orientation.RIGHT, new DiscreteCoordinates(10,2), key3, key4, this));
                area.registerActor(new TwoKeysGate(area, Orientation.RIGHT, new DiscreteCoordinates(19,2), key3, key4, this));
                area.registerActor(new TwoKeysGate(area, Orientation.RIGHT, new DiscreteCoordinates(12,8), key3, key4, this));
                area.registerActor(new TwoKeysGate(area, Orientation.RIGHT, new DiscreteCoordinates(17,8), key3, key4, this));

                area.registerActor(new DiamondGate(area,Orientation.RIGHT, new DiscreteCoordinates(14,3), this));
                area.registerActor(new DiamondGate(area,Orientation.RIGHT, new DiscreteCoordinates(15,3), this));
                break;



        }

    }



    protected boolean[][] getNeighborhood(int x, int y){
        neighborhood = new boolean[3][3];

        neighborhood[1][1] = true; //Center of the matrix where we will place a wall

         //Pieces where we can check in a 3x3 region
                for (int i = -1; i < 2; ++i) {
                    for (int j = -1; j < 2; ++j) {

                        if(!(i+x<0 || i+x>=getWidth() || j+y<0 || j+y>=getHeight())){
                            if (getType(getCell(i + x, j + y)).equals(SuperPacmanCellType.WALL)) {
                                neighborhood[i + 1][1-j] = true;
                            }
                        }

                    }
                }


        return neighborhood;
    }
    protected SuperPacmanCellType getType (Cell cell){
        return ((SuperPacmanCell)cell).getCellType();

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

        graph = new AreaGraph();



        for(int y = 0; y < height; y++) {
            for (int x = 0; x < width ; x++) {

                SuperPacmanCellType color = SuperPacmanCellType.toType(getRGB(height-1-y, x));
                setCell(x,y, new SuperPacmanCell(x,y,color));

            }
        }
        for(int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                if (getType(getCell(x,y)) != SuperPacmanCellType.WALL){
                    graph.addNode(new DiscreteCoordinates(x,y),hasLeftEdge(x,y),hasUpEdge(x,y),
                            hasRightEdge(x,y), hasDownEdge(x,y));
                }
            }
        }


    }



    public boolean hasLeftEdge(int x, int y){


        return x > 0 && getType(getCell(x-1,y)) != SuperPacmanCellType.WALL;
    }

    public boolean hasRightEdge(int x, int y){

        return x < getWidth() && getType(getCell(x+1,y)) != SuperPacmanCellType.WALL;
    }

    public boolean hasUpEdge(int x, int y){
        return y<getHeight()-1 && getType(getCell(x,y+1)) != SuperPacmanCellType.WALL;
    }

    public boolean hasDownEdge(int x, int y){

        return y>0 && getType(getCell(x,y-1)) != SuperPacmanCellType.WALL;
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

        public SuperPacmanCellType getCellType() {
            return type;
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

            return !this.hasNonTraversableContent();

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


