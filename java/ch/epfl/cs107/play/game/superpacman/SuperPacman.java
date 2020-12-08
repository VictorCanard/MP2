package ch.epfl.cs107.play.game.superpacman;

import ch.epfl.cs107.play.game.rpg.RPG;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;
import ch.epfl.cs107.play.game.superpacman.area.*;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

import java.util.Collection;
import java.util.Collections;


public class SuperPacman extends RPG {
    private SuperPacmanPlayer player;
    private int areaIndex =0;
    public static int numberOfAreas = 3;
    private final String[] areaNames = {"superpacman/Level0", "superpacman/Level1", "superpacman/Level2"};
    private final DiscreteCoordinates[] startingPositions = {Level0.PLAYER_SPAWN_POSTION,
            Level1.PLAYER_SPAWN_POSTION, Level2.PLAYER_SPAWN_POSTION};

    private SuperPacmanArea[] areas = new SuperPacmanArea[numberOfAreas];
    private SuperPacmanBehavior[] behaviors = new SuperPacmanBehavior[numberOfAreas];



    @Override
    public String getTitle() {
        return "Super Pac-Man";
    }

    @Override
    public void update(float deltaTime) {

        if(player.isPassingADoor()){
            nextLevel();
        }

        if(((SuperPacmanArea)getCurrentArea()).isOn()){

            getCurrentArea().leaveAreaCells(player,player.getCurrentCells());

            nextLevel();

            getCurrentArea().enterAreaCells(player, Collections.singletonList(startingPositions[areaIndex]));

        }
        if(player.isInvulnerable()){
            player.scareGhosts();
        }


        super.update(deltaTime);
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



    public void initialiseBehaviours(){

        for(int i=0;i<areas.length;i++){

            behaviors[i] = new SuperPacmanBehavior(getWindow(),areaNames[i]);
            areas[i].setBehaviour(behaviors[i]);

        }
    }

    public void initialiseActors(int areaIndex){
        areas[areaIndex].registerActors();
    }


    public void initialisePlayer(){
        player = new SuperPacmanPlayer(areas[areaIndex],startingPositions[areaIndex]);
        initPlayer(player);
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {

        if (super.begin(window, fileSystem)) {

            createAreas();

            areaIndex= 0;

            setCurrentLevel(areaIndex);
            initialisePlayer();

            return true;
        }
        return false;
    }


}
