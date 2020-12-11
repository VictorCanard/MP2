package ch.epfl.cs107.play.game.superpacman.handler;

import ch.epfl.cs107.play.game.rpg.handler.RPGInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;

public interface GhostInteractionVisitor extends SuperPacmanInteractionVisitor {
    default void interactWith(SuperPacmanPlayer player){}
}
