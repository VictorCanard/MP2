package ch.epfl.cs107.play.game.superpacman;

import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.rpg.RPG;
import ch.epfl.cs107.play.game.rpg.actor.Player;
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

    private TextGraphics pauseGraphics;
    private TextGraphics startingGraphics;
    private Vector startingButtonPosition;

    private int numberOfPlayers = 1;
    private boolean isMultiplayer = false;
    private Keyboard keyboard;

    private boolean gameEnded = false;
    private boolean isFirstTime = true;
    private boolean suspended = false;

    private Window window;

    private float width;
    private float height;
    private int totalScore=0;
    private TextGraphics totalScoreText;
    private TextGraphics gameOverText;
    private Vector anchor;


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

        if(gameEnded){
            if(isFirstTime){
                initialiseEndOfGameGraphics();
                isFirstTime=false;
            }


            drawEndOfGameGraphics();
        }
        else{
            keyboard = getCurrentArea().getKeyboard();

            for (int i = 0; i < numberOfPlayers; i++) {

                if(players[i].hasNoHp()){

                    endGame();
                }
            }
            if(isMultiplayer){
                if((players[0].isPassingADoor())) {

                    players[1].leaveArea();

                    nextLevel();

                    players[1].enterArea(getCurrentArea(),startingPositions[1][areaIndex]);


                }
                if(players[1].isPassingADoor()){

                    players[0].leaveArea();

                    nextLevel();

                    players[0].enterArea(getCurrentArea(),startingPositions[1][areaIndex]);

                }
            }





            //When tab is pressed the game pauses completely (Basically all updates are blocked by SuperPacman.java)

            if(keyboard.get(Keyboard.TAB).isPressed()){

                pauseGame();

            }

            if(!suspended){

                super.update(deltaTime);
            }
            else{

                pauseGraphics.draw(window);

            }
        }





        }



    public void nextLevel(){

        areaIndex++;
        setCurrentLevel(areaIndex);

        for (int i = 0; i <numberOfPlayers ; i++) {
            players[i].resetInvulnerable();
        }

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
        players[0] = new SuperPacmanPlayer(areas[areaIndex], 0,startingPositions[0][areaIndex], player1Touchpad, player1Image);
        initPlayer(players[0]);


        if(isMultiplayer){
            player2Touchpad = new int[]{keyboard.S, keyboard.A,keyboard.W, keyboard.D};
            player2Image = "superpacman/pacmanSilver";
            players[1] = new SuperPacmanPlayer(areas[areaIndex], 1, startingPositions[1][areaIndex], player2Touchpad, player2Image);
            initPlayer(players[1]);


        }




    }


    private void initialiseGraphics(){
        width = window.getScaledWidth();
        height = window.getScaledHeight();
        anchor = window.getTransform().getOrigin().sub(new Vector(width/2, height/2));

        initialisePauseGraphics();
    }

    private void initialisePauseGraphics(){
        pauseGraphics = new TextGraphics("GAME IS PAUSED ",1f, Color.BLACK, Color.BLUE,0.04f,false,false, anchor.add(new Vector(7.5f, height - 1.375f)));

    }


    private void startingWindow(){
        startingButtonPosition = anchor.add(new Vector(7.5f, height - 1.375f));
        startingGraphics =  new TextGraphics("PRESS ENTER TO START",1f, Color.BLACK, Color.BLUE,0.04f,false,false,startingButtonPosition );

        do{
            keyboard= window.getKeyboard();
            startingGraphics.draw(window);

        }while((keyboard.get(Keyboard.ENTER).isUp()));
    }
    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        //Todo: reset frameRate to original

        if (super.begin(window, fileSystem)) {

            this.window = window;

            initialiseGraphics();

            //startingWindow();
            // Extension to greet the player before starting the game:
            // unfortunately the while loop lasts forever

            createAreas();

            initialiseStartingPositions();

            areaIndex= 0; //Level 0

            setCurrentLevel(areaIndex);

            isMultiplayer= true; //Change this attribute to False to deactivate Multiplayer mode

            if(isMultiplayer){
                numberOfPlayers = 2;
            }

            initialisePlayers();

            return true;
        }
        return false;
    }

    private void pauseGame(){
        toggleSuspend();

    }

    private void toggleSuspend(){
        suspended = !suspended;
    }


    private void endGame(){
        //Maybe add some graphics such as score...
        gameEnded = true;

    }


    private void initialiseEndOfGameGraphics(){


        for (int i = 0; i < numberOfPlayers; i++) {
            totalScore+= players[i].getScore();
        }
        gameOverText = new TextGraphics("GAME OVER",1f, Color.BLUE, Color.YELLOW,0.01f,true,false, anchor.add(new Vector(7.5f, height - 5f)));

        totalScoreText = new TextGraphics("TOTAL SCORE:  \n"+totalScore,1f, Color.YELLOW, Color.BLUE,0.04f,false,false, anchor.add(new Vector(7.5f, height - 1.375f)));

    }
    private void drawEndOfGameGraphics(){

        gameOverText.draw(window);
        totalScoreText.draw(window);

    }








}
