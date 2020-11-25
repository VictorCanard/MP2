package ch.epfl.cs107.play.game.tutos.area.tuto1;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.tutos.actor.SimpleGhost;
import ch.epfl.cs107.play.game.tutos.area.SimpleArea;
import ch.epfl.cs107.play.math.Vector;

import java.util.logging.SimpleFormatter;

public class Ferme extends SimpleArea {
    private SimpleGhost actor;
    @Override
    protected void createArea() {
        this.registerActor(new Background(this));

        actor = new SimpleGhost(new Vector(18,7),"ghost.2");
        this.registerActor(actor);
    }

    @Override
    public String getTitle() {
        return "zelda/Ferme";
    }
}
