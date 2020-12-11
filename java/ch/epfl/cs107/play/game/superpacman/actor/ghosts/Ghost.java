package ch.epfl.cs107.play.game.superpacman.actor.ghosts;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.*;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.actor.player.SuperPacmanPlayer;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ghost extends MovableAreaEntity implements  Interactor, Interactable {

    protected Sprite[][] ghostSprite;
    protected Animation[] ghostAnimation;
    protected Animation currentAnimation;

    protected Orientation desiredOrientation;

    protected Sprite[][] scaredSprite;
    protected Animation[] scaredAnimation;

    protected DiscreteCoordinates positionRefuge;
    private ghostHandler interactionHandler;
    protected SuperPacmanPlayer player;

    protected final int ANIMATION_DURATION=8;
    protected final int FRAME_FOR_MOVE = 16;
    public final int GHOST_SCORE = 500;
    protected final int FIELD_OF_VIEW_RADIUS = 5;
    public  DiscreteCoordinates GHOST_SPAWN_POSITION;

    protected List<DiscreteCoordinates> fieldOfView;
    protected int width;
    protected int height;
    protected int x;
    protected int y;

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

        GHOST_SPAWN_POSITION = position;

        interactionHandler = new ghostHandler();

        scaredSprite = RPGSprite.extractSprites("superpacman/ghost.afraid", 2, 1, 1, this, 16, 16, new Orientation[] {Orientation.DOWN , Orientation.LEFT , Orientation.UP, Orientation.RIGHT});
        scaredAnimation = Animation.createAnimations(ANIMATION_DURATION / 2, scaredSprite);

    }



    public boolean isAfraid(){

        return player.getIsInvulnerable();

    }


    protected Animation getNewCurrentAnimation(Orientation desiredOrientation, Animation[] currentAnimationTable){
        if(!isDisplacementOccurs()){
            if(getOwnerArea().canEnterAreaCells(this, Collections.singletonList(getCurrentMainCellCoordinates().jump(desiredOrientation.toVector())))){

                orientate(desiredOrientation);




            }
            currentAnimation.reset();
            move(FRAME_FOR_MOVE);

        }
        else{
            currentAnimation = currentAnimationTable[desiredOrientation.ordinal()];

        }
        return currentAnimation;
    }

    @Override
    public void draw(Canvas canvas) {

        getNewCurrentAnimation(desiredOrientation,scaredAnimation);
        currentAnimation.draw(canvas);

    }

    @Override
    public void update(float deltaTime){

        currentAnimation.update(deltaTime);
        super.update(deltaTime);
    }

    public void respawnGhost(){
        getOwnerArea().leaveAreaCells(this , getEnteredCells());
        this.abortCurrentMove();
        this.resetMotion();

        //Todo Ghost forgets the player

        this.setCurrentPosition(GHOST_SPAWN_POSITION.toVector());
        getOwnerArea().enterAreaCells(this, Collections.singletonList(GHOST_SPAWN_POSITION));
    }




    @Override
    public List<DiscreteCoordinates> getCurrentCells() {

        return Collections.singletonList(getCurrentMainCellCoordinates());

    }

    @Override
    public List<DiscreteCoordinates> getFieldOfViewCells() {

        fieldOfView = new ArrayList<DiscreteCoordinates>();

        x = getCurrentMainCellCoordinates().x;
        y = getCurrentMainCellCoordinates().y;


        width = getOwnerArea().getWidth();
        height = getOwnerArea().getHeight();

        fieldOfView.add(getCurrentMainCellCoordinates()); //The first item in the list is the ghost's position


        for (int i = -FIELD_OF_VIEW_RADIUS; i < FIELD_OF_VIEW_RADIUS; ++i) {
            for (int j = -FIELD_OF_VIEW_RADIUS; j < FIELD_OF_VIEW_RADIUS; ++j) {

                if(!(i+x<0 || i+x>=width || j+y<0 || j+y>=height)){

                    fieldOfView.add(new DiscreteCoordinates(i+x,j+y));

                }

            }
        }
        return fieldOfView;
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
        ((SuperPacmanInteractionVisitor)v).interactWith(this);
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


    public class ghostHandler implements SuperPacmanInteractionVisitor{
        @Override
        public void interactWith(SuperPacmanPlayer player) {
            Ghost.this.player = player; //Le fantome se souvient du joueur Pacman$


        }
    }

}
