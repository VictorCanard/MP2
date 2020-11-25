package ch.epfl.cs107.play.game.tutos;

import ch.epfl.cs107.play.game.areagame.AreaGame;

import ch.epfl.cs107.play.game.tutos.actor.SimpleGhost;
import ch.epfl.cs107.play.game.tutos.area.tuto1.Ferme;
import ch.epfl.cs107.play.game.tutos.area.tuto1.Village;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class Tuto1 extends AreaGame {
    private SimpleGhost player;
    private SimpleGhost actor;

    private Keyboard keyboard;

    private Button keyUp;
    private Button keyDown;
    private Button keyLeft;
    private Button keyRight;

    private void createAreas(){

        addArea(new Village());
        addArea(new Ferme());

    }


    public void end() {

    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        keyboard = getWindow().getKeyboard();

        keyRight = keyboard.get(Keyboard.RIGHT);
        keyLeft = keyboard.get(Keyboard.LEFT);
        keyDown = keyboard.get(Keyboard.DOWN);
        keyUp = keyboard.get(Keyboard.UP);

        if(keyUp.isDown()){
          player.moveUp();
        }
        if(keyDown.isDown()){
            player.moveDown();
        }
        if(keyLeft.isDown()){
            player.moveLeft();
        }
        if(keyRight.isDown()){
            player.moveRight();
        }

        if(player.isWeak()){
            switchArea();
        }


    }

    @Override
    public String getTitle() {
        return "Tuto1";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {


            player = new SimpleGhost(new Vector(18,7),"ghost.1");

            createAreas();
            setCurrentArea("zelda/Village", true);


            getCurrentArea().registerActor(player);

            getCurrentArea().setViewCandidate(player);

            return true;
        } else {
            return false;
        }

    }
    public void switchArea(){

        getCurrentArea().unregisterActor(player);

        player.strengthen();

        if(getCurrentArea().getTitle().equals("zelda/Ferme")){

            setCurrentArea("zelda/Village", true);
        }
        else{
            setCurrentArea("zelda/Ferme", true);

        }

        getCurrentArea().registerActor(player);

        getCurrentArea().setViewCandidate(player);

    }
}
