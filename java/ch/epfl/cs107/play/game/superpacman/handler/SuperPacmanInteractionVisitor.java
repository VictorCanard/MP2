package ch.epfl.cs107.play.game.superpacman.handler;

import ch.epfl.cs107.play.game.areagame.actor.AutomaticallyCollectableAreaEntity;
import ch.epfl.cs107.play.game.rpg.handler.RPGInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.actor.collectables.Bonus;
import ch.epfl.cs107.play.game.superpacman.actor.ghosts.Ghost;
import ch.epfl.cs107.play.game.superpacman.actor.player.SuperPacmanPlayer;

public interface SuperPacmanInteractionVisitor extends RPGInteractionVisitor {

    default void interactWith(AutomaticallyCollectableAreaEntity automaticallyCollectableAreaEntity){ }

    default void interactWith(Ghost ghost) { }


    default void interactWith(Bonus bonus) {

    }

    default void interactWith(SuperPacmanPlayer player){}
}
