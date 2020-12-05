package ch.epfl.cs107.play.game.superpacman.handler;

import ch.epfl.cs107.play.game.areagame.actor.AutomaticallyCollectableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.game.rpg.handler.RPGInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;

public interface SuperPacmanInteractionVisitor extends RPGInteractionVisitor {

    default void interactWith(SuperPacmanPlayer superPacmanPlayer) {}

    default void interactWith(AutomaticallyCollectableAreaEntity automaticallyCollectableAreaEntitycollectableAreaEntity){

    }

    default void interactWith(CollectableAreaEntity collectableAreaEntity){

    }







}
