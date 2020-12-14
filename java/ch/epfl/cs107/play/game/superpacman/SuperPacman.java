package ch.epfl.cs107.play.game.superpacman;

import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.rpg.RPG;
import ch.epfl.cs107.play.game.superpacman.actor.player.SuperPacmanPlayer;

import ch.epfl.cs107.play.game.superpacman.area.*;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.TextAlign;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.play.window.swing.SwingWindow;

import java.awt.*;


public class SuperPacman extends RPG {
    private SuperPacmanPlayer[] players;

    private int[] player1Touchpad;
    private int[] player2Touchpad;

    private String player1Image;
    private String player2Image;

    private PauseGraphics pauseGraphics;

    private int numberOfPlayers;

    private Keyboard keyboard;
    private Button button;
    private boolean suspended = false;
    private Window window;


    private int areaIndex =0;
    public static int numberOfAreas = 3;

    private final String[] areaNames = {"superpacman/Level0", "superpacman/Level1", "superpacman/Level2"};
    private DiscreteCoordinates[][] startingPositions;

    private SuperPacmanArea[] areas = new SuperPacmanArea[numberOfAreas];
    private SuperPacmanBehavior[] behaviors = new SuperPacmanBehavior[numberOfAreas];



    @Override
    public String getTitle() {
        return "Super Pac-Man";
    }

    @Override
    public void update(float deltaTime) {

        keyboard= getCurrentArea().getKeyboard();

        for (int i = 0; i < numberOfPlayers; i++) {

            if(players[i].isPassingADoor()){
                nextLevel();
            }
            if(players[i].hasNoHp()){

                endGame();
            }
        }


        /*if(((SuperPacmanArea)getCurrentArea()).isOn()){

            getCurrentArea().leaveAreaCells(player,player.getCurrentCells());

            nextLevel();

            getCurrentArea().enterAreaCells(player, Collections.singletonList(startingPositions[areaIndex]));

        }*/


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

        startingPositions = new DiscreteCoordinates[][]
                {{Level0.PLAYER1_SPAWN_POSTION,
                Level1.PLAYER1_SPAWN_POSTION,
                Level2.PLAYER1_SPAWN_POSTION},

                {Level0.PLAYER2_SPAWN_POSTION,
                Level1.PLAYER2_SPAWN_POSTION,
                Level2.PLAYER2_SPAWN_POSTION}};

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


    private void initialisePlayers(){
        players = new SuperPacmanPlayer[numberOfPlayers];

        player1Touchpad = new int[]{keyboard.DOWN, keyboard.LEFT,keyboard.UP, keyboard.RIGHT};
        player1Image = "superpacman/pacman";
        players[0] = new SuperPacmanPlayer(areas[areaIndex], 0,startingPositions[areaIndex][0], player1Touchpad, player1Image);
        initPlayer(players[0]);



        player2Touchpad = new int[]{keyboard.S, keyboard.A,keyboard.W, keyboard.D};
        //player2Image = "superpaman/pacmanSilver";
        player2Image= "superpacman/pacman";
        players[1] = new SuperPacmanPlayer(areas[areaIndex], 1, startingPositions[areaIndex][1], player2Touchpad, player2Image);
        initPlayer(players[1]);



    }

    private void initialisePauseGraphics(){


        pauseGraphics = new PauseGraphics(window);




    }


    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        //Todo: reset frameRate to original

        if (super.begin(window, fileSystem)) {


            createAreas();

            initialiseStartingPositions();

            areaIndex= 0;

            setCurrentLevel(areaIndex);

            numberOfPlayers = 2;
            initialisePlayers();

            this.window = window;
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
        //Todo call the pauseGraphics draw method on the canvas?

        //Resume
        //Try again
        //Quit Game which will call endGame();
    }

    private void endGame(){
        //Maybe add some graphics such as score...
        this.end(); //This end() doesnt do anything yet
    }





}
