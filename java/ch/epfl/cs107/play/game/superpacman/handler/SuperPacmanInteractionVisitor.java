package ch.epfl.cs107.play.game.superpacman.handler;

import ch.epfl.cs107.play.game.areagame.actor.AutomaticallyCollectableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.rpg.handler.RPGInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.actor.Ghosts.Ghost;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;

public interface SuperPacmanInteractionVisitor extends RPGInteractionVisitor {

    default void interactWith(AutomaticallyCollectableAreaEntity automaticallyCollectableAreaEntity){ }

    default void interactWith(Ghost ghost) { }

}
