package ch.epfl.cs107.play.game.superpacman.actor.Ghosts;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.*;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.SuperPacman;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;
import ch.epfl.cs107.play.game.superpacman.handler.GhostInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import static ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer.isInvulnerable;

import java.util.List;

public class Ghost extends MovableAreaEntity implements  Interactor, Interactable {

    private Sprite[] ghostSprite;
    private Animation ghostAnimation;
    private DiscreteCoordinates positionRefuge;
    private ghostHandler interactionHandler;
    private SuperPacmanPlayer player;

    private final int ANIMATION_DURATION=8;
    private final int GHOST_SCORE = 500;
    private final int FIELD_OF_VIEW_RADIUS = 5;
    private boolean isAfraid=false;

    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */
    public Ghost(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);

        interactionHandler = new ghostHandler();


    }

    public void isAfraid(){
        this.isAfraid = isInvulnerable();

    }
    public void drawAfraid(){
        ghostSprite = RPGSprite.extractSprites("superpacman/ghost.afraid", 2, 1, 1, this, 16, 16);
        ghostAnimation = new Animation(ANIMATION_DURATION / 2, ghostSprite);
    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public Transform getTransform() {
        return null;
    }

    @Override
    public Vector getVelocity() {
        return null;
    }

    @Override
    public boolean takeCellSpace() {
        return false;
    }

    @Override
    public boolean isCellInteractable() {
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((GhostInteractionVisitor)v).interactWith(this);
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return null;
    }

    @Override
    public List<DiscreteCoordinates> getFieldOfViewCells() {
        return null;
    }

    @Override
    public boolean wantsCellInteraction() {
        return false;
    }

    @Override
    public boolean wantsViewInteraction() {
        return true;
    }

    @Override
    public void interactWith(Interactable other) {
        other.acceptInteraction(interactionHandler);
    }

    public class ghostHandler implements GhostInteractionVisitor{
        @Override
        public void interactWith(SuperPacmanPlayer player) {
            Ghost.this.player = player; //Le fantome se souvient du joueur Pacman

        }
    }

}
