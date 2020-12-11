package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.actor.*;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.rpg.actor.Player;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.rpg.actor.Sign;
import ch.epfl.cs107.play.game.superpacman.SuperPacman;
import ch.epfl.cs107.play.game.superpacman.actor.Collectables.Bonus;
import ch.epfl.cs107.play.game.superpacman.actor.Ghosts.Ghost;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.util.Collections;
import java.util.List;

public class SuperPacmanPlayer extends Player implements Interactable, Interactor {
    private int MOVING_SPEED = 6;
    private int MAX_HP = 5;
    private int DEFAULT_HP = 3;
    private int score = 0;
    private final int ANIMATION_DURATION = 4;
    private boolean isPassingADoor;
    private Orientation desiredOrientation;
    private Animation[] animations;
    private Sprite[][] sprites ;
    private Animation currentAnimation;
    private SuperPacmanPlayerStatusGUI statusGUI;
    private SuperPacmanPlayerHandler playerHandler;

    private boolean isInvulnerable = false;
    private float  invulnerableTimer = 0;


    /**
     * Default Player constructor
     *
     * @param area        (Area): Owner Area, not null
     */

    public SuperPacmanPlayer(SuperPacmanArea area, DiscreteCoordinates startingPos) {
        super(area, Orientation.RIGHT, startingPos);

        setOwnerArea(area);

        playerHandler = new SuperPacmanPlayerHandler();

        statusGUI = new SuperPacmanPlayerStatusGUI(this, DEFAULT_HP,MAX_HP);


        sprites = RPGSprite.extractSprites("superpacman/pacman", 4, 1, 1, this , 64, 64, new Orientation[] {Orientation.DOWN , Orientation.LEFT , Orientation.UP, Orientation.RIGHT});


        animations = Animation.createAnimations(ANIMATION_DURATION/4, sprites);
        currentAnimation = animations[1];//Initializes the first animation to the upward direction
        desiredOrientation = Orientation.UP;



        resetMotion();
    }

    public void update(float deltaTime) {

        invulnerabilityCheck(deltaTime);

        Keyboard keyboard= getOwnerArea().getKeyboard();

        updateDesiredOrientation(Orientation.LEFT, keyboard.get(Keyboard.LEFT));
        updateDesiredOrientation(Orientation.UP, keyboard.get(Keyboard.UP));
        updateDesiredOrientation(Orientation.RIGHT, keyboard.get(Keyboard.RIGHT));
        updateDesiredOrientation(Orientation.DOWN, keyboard.get(Keyboard.DOWN));

        getNewCurrentAnimation();

        currentAnimation.update(deltaTime);
        super.update(deltaTime);

    }

    private void invulnerabilityCheck(float deltaTime){
        if(invulnerableTimer <=0){
            isInvulnerable = false;
        }

        else{
            invulnerableTimer -=deltaTime;
        }
    }

    private void updateDesiredOrientation(Orientation orientation, Button b){

        if(b.isDown()) {
            desiredOrientation = orientation;
        }

    }

    private void getNewCurrentAnimation(){
        if(!isDisplacementOccurs()){
            if(getOwnerArea().canEnterAreaCells(this,Collections.singletonList(getCurrentMainCellCoordinates().jump(desiredOrientation.toVector())))){

                orientate(desiredOrientation);


                currentAnimation.reset();

            }
            move(MOVING_SPEED);
        }
        else{
            currentAnimation = animations[desiredOrientation.ordinal()];

        }
    }


    public int getScore(){
        return score;
    }



    public void setInvulnerable(float timer){
        invulnerableTimer = timer;
        isInvulnerable = true;
    }
    public boolean getIsInvulnerable(){
        return isInvulnerable;
    }

    /**
     * Indicate the player just passed a door
     */
    protected void setIsPassingADoor(){ //
        isPassingADoor = true;
    }

    /**@return (boolean): true if the player is passing a door*/


    @Override
    public void draw(Canvas canvas) {
        currentAnimation.draw(canvas);
        statusGUI.draw(canvas);

    }


    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    @Override
    public List<DiscreteCoordinates> getFieldOfViewCells() {
        return null;
    }

    @Override
    public boolean wantsCellInteraction() {
        return true;
    }

    @Override
    public boolean wantsViewInteraction() {
        return false;
    }

    @Override
    public void interactWith(Interactable other) {
        other.acceptInteraction(playerHandler);

    }

    @Override
    public boolean takeCellSpace() {
        return false;
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }

    @Override
    public boolean isViewInteractable() {
        return true;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((SuperPacmanInteractionVisitor)v).interactWith(this); }

    public class SuperPacmanPlayerHandler implements SuperPacmanInteractionVisitor {
        @Override
        public void interactWith(Door door) {
            setIsPassingADoor(door);

        }

        @Override
        public void interactWith(AutomaticallyCollectableAreaEntity entity){
            entity.collect();
            entity.specialAttribute();
            score += entity.addScore();
        }


       @Override

        public void interactWith(Bonus bonus) {
            SuperPacmanPlayer.this.isInvulnerable = true;
            interactWith((AutomaticallyCollectableAreaEntity)bonus);
        }



        @Override
        public void interactWith(Ghost ghost) {
            System.out.println("HELLO");
            if(isInvulnerable){

                score += ghost.GHOST_SCORE;

                getOwnerArea().leaveAreaCells(ghost , getEnteredCells());

                getOwnerArea().enterAreaCells(ghost, ghost.GHOST_SPAWN_POSITION);

            }
            else{
                getOwnerArea().leaveAreaCells(SuperPacmanPlayer.this , getEnteredCells());
                SuperPacmanPlayer.this.statusGUI.currentHp --;
                //getOwnerArea().enterAreaCells(SuperPacmanPlayer.this, ((SuperPacmanArea)getOwnerArea()).PLAYER_SPAWN_POSITION);

            }
        }
    }

}
