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



import java.util.List;

public class Ghost extends MovableAreaEntity implements  Interactor, Interactable {

    protected Sprite[] ghostSprite;
    protected Animation ghostAnimation;

    private Sprite[] scaredSprite;
    private Animation scaredAnimation;

    protected DiscreteCoordinates positionRefuge;
    private ghostHandler interactionHandler;
    protected SuperPacmanPlayer player;

    protected final int ANIMATION_DURATION=8;
    protected final int FRAME_FOR_MOVE = 6;
    public final int GHOST_SCORE = 500;
    protected final int FIELD_OF_VIEW_RADIUS = 5;
    protected boolean isAfraid=false;

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

        scaredSprite = RPGSprite.extractSprites("superpacman/ghost.afraid", 2, 1, 1, this, 16, 16);
        scaredAnimation = new Animation(ANIMATION_DURATION / 2, scaredSprite);


    }

    public boolean getIsAfraid(){
        return isAfraid;
    }

    public boolean isAfraid(){
        return player.getIsInvulnerable();

    }


    @Override
    public void update(float deltaTime){

    }


    @Override
    public void draw(Canvas canvas) {
        if(isAfraid()){
            scaredAnimation.draw(canvas);
        }
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
