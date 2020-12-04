package ch.epfl.cs107.play.game.superpacman;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.RPG;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;
import ch.epfl.cs107.play.game.superpacman.area.*;
import ch.epfl.cs107.play.game.tutosSolution.actor.GhostPlayer;
import ch.epfl.cs107.play.game.tutosSolution.area.tuto2.Ferme;
import ch.epfl.cs107.play.game.tutosSolution.area.tuto2.Village;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class SuperPacman extends RPG {
    private SuperPacmanPlayer player;
    private int areaIndex;
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
        super.update(deltaTime);
    }

    private void createAreas(){
        addArea(new Level0());
        addArea(new Level1());
        addArea(new Level2());

        for(int i=0;i<areas.length;i++){
            behaviors[i] = new SuperPacmanBehavior(getWindow(),areaNames[i]);

        }

    }
    @Override
    public boolean begin(Window window, FileSystem fileSystem) {

        if (super.begin(window, fileSystem)) {

            createAreas();
            areaIndex = 2;
            areas[areaIndex] = (SuperPacmanArea) setCurrentArea(areaNames[areaIndex], true);
            areas[areaIndex].setBehaviour(behaviors[areaIndex]);
            areas[areaIndex].registerActors();

            player = new SuperPacmanPlayer(areas[areaIndex],startingPositions[areaIndex]);
            initPlayer(player);

            return true;
        }
        return false;
    }

}
