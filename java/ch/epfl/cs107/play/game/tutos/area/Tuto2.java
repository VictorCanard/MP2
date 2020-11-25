package ch.epfl.cs107.play.game.tutos.area;

import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.tutos.actor.GhostPlayer;
import ch.epfl.cs107.play.game.tutos.actor.SimpleGhost;
import ch.epfl.cs107.play.game.tutos.area.tuto2.Ferme;
import ch.epfl.cs107.play.game.tutos.area.tuto2.Village;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class Tuto2 extends AreaGame{

    private SimpleGhost actor;
    private GhostPlayer player;

    final static float SCALE_FACTOR = 13.f;

    private DiscreteCoordinates origin;

    private void createAreas(){

        addArea(new Village());
        addArea(new Ferme());

    }


    public void end() {

    }

    @Override
    public void update(float deltaTime) {

        if(player.isWeak()){
            switchArea();
        }

        super.update(deltaTime);



    }

    @Override
    public String getTitle() {
        return "Tuto2";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {

            createAreas();
            //Creation du joueur
            setCurrentArea("zelda/Ferme",true);

            origin = new DiscreteCoordinates(2,10);

            player = new GhostPlayer(getCurrentArea(),Orientation.DOWN,origin,"ghost.1");


            getCurrentArea().registerActor(player);

            //Camera sur l'acteur
            getCurrentArea().setViewCandidate(player);

            return true;
        } else {
            return false;
        }

    }
    public void switchArea(){

        player.leaveArea();


        if(getCurrentArea().getTitle().equals("zelda/Ferme")){

            origin = new DiscreteCoordinates(5,15);
            setCurrentArea("zelda/Village", false);
        }
        else{
            origin = new DiscreteCoordinates(2,10);
            setCurrentArea("zelda/Ferme",false);

        }

        player.enterArea(getCurrentArea(),origin);

        player.strengthen();

    }
}
