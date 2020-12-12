package ch.epfl.cs107.play.game.superpacman;

import ch.epfl.cs107.play.game.rpg.RPG;
import ch.epfl.cs107.play.game.superpacman.actor.player.SuperPacmanPlayer;

import ch.epfl.cs107.play.game.superpacman.area.*;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;




public class SuperPacman extends RPG {
    private SuperPacmanPlayer player;

    private Keyboard keyboard;
    private Button button;
    private boolean suspended = false;
    private Window pauseWindow;

    private int areaIndex =0;
    public static int numberOfAreas = 3;

    private final String[] areaNames = {"superpacman/Level0", "superpacman/Level1", "superpacman/Level2"};
    private DiscreteCoordinates[] startingPositions;

    private SuperPacmanArea[] areas = new SuperPacmanArea[numberOfAreas];
    private SuperPacmanBehavior[] behaviors = new SuperPacmanBehavior[numberOfAreas];



    @Override
    public String getTitle() {
        return "Super Pac-Man";
    }

    @Override
    public void update(float deltaTime) {

        keyboard= getCurrentArea().getKeyboard();

        if(player.isPassingADoor()){
            nextLevel();
        }

        /*if(((SuperPacmanArea)getCurrentArea()).isOn()){

            getCurrentArea().leaveAreaCells(player,player.getCurrentCells());

            nextLevel();

            getCurrentArea().enterAreaCells(player, Collections.singletonList(startingPositions[areaIndex]));

        }*/

        if(player.hasNoHp()){

            endGame();
        }

        //When tab is pressed the game pauses completely (Basically all updates are blocked by SuperPacman.java)

        if(keyboard.get(Keyboard.TAB).isPressed()){

            pauseGame();

        }

        if(!suspended){

            super.update(deltaTime);
        }

    }
    public void nextLevel(){

        areaIndex++;
        setCurrentLevel(areaIndex);
    }

    public void setCurrentLevel(int areaIndex){

        areas[areaIndex] = (SuperPacmanArea) setCurrentArea(areaNames[areaIndex], true);
        areas[areaIndex].createArea();
        initialiseActors(areaIndex);

    }

    private void createAreas(){

        areas[0] = new Level0();
        areas[1] = new Level1();
        areas[2] = new Level2();

        for (int i = 0; i < areas.length; i++) {

            addArea(areas[i]);

        }

        initialiseBehaviours();

    }

    private void initialiseStartingPositions(){

        startingPositions = new DiscreteCoordinates[]{Level0.PLAYER_SPAWN_POSTION, Level1.PLAYER_SPAWN_POSTION, Level2.PLAYER_SPAWN_POSTION};

    }

    private void initialiseBehaviours(){

        for(int i=0;i<areas.length;i++){

            behaviors[i] = new SuperPacmanBehavior(getWindow(),areaNames[i]);
            areas[i].setBehaviour(behaviors[i]);

        }
    }

    private void initialiseActors(int areaIndex){
        areas[areaIndex].registerActors();
    }


    private void initialisePlayer(){
        player = new SuperPacmanPlayer(areas[areaIndex],startingPositions[areaIndex]);
        initPlayer(player);
    }

    private void initialisePauseGraphics(){

    }


    @Override
    public boolean begin(Window window, FileSystem fileSystem) {

        if (super.begin(window, fileSystem)) {

            createAreas();

            initialiseStartingPositions();

            areaIndex= 1;

            setCurrentLevel(areaIndex);
            initialisePlayer();

            initialisePauseGraphics();

            return true;
        }
        return false;
    }

    private void pauseGame(){
        toggleSuspend();
        displayPauseGraphics();
    }

    private boolean toggleSuspend(){
        suspended = !suspended;
        return suspended ;
    }

    private void displayPauseGraphics(){
        //Resume
        //Try again
        //Quit Game which will call endGame();
    }

    private void endGame(){
        //Maybe add some graphics such as score...
        this.end(); //This end() doesnt do anything yet
    }





}
