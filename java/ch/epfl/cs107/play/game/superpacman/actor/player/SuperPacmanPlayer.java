package ch.epfl.cs107.play.game.superpacman.actor.player;

import ch.epfl.cs107.play.game.areagame.actor.*;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.rpg.actor.Player;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.actor.collectables.Bonus;
import ch.epfl.cs107.play.game.superpacman.actor.ghosts.Ghost;
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
    private boolean speedBoost=false;
    private final int ANIMATION_DURATION = 4;

    private Keyboard keyboard;
    private int[]  keyboardButtons;

    public boolean isPassingADoor;
    private Orientation desiredOrientation;
    private Animation[] animations;

    private Sprite[][] sprites ;
    private Animation currentAnimation;
    private SuperPacmanPlayerStatusGUI statusGUI;
    private SuperPacmanPlayerHandler playerHandler;

    private boolean isInvulnerable = false;
    private float  invulnerableTimer = 0;

    private DiscreteCoordinates spawnPosition;


    /**
     * Default Player constructor
     *
     * @param area        (Area): Owner Area, not null
     */

    public SuperPacmanPlayer(SuperPacmanArea area, int playerNumber, DiscreteCoordinates startingPos, int[] keyboardButtons, String playerImage) {
        super(area, Orientation.UP, startingPos);

        setOwnerArea(area);

        spawnPosition = startingPos;

        playerHandler = new SuperPacmanPlayerHandler();

        statusGUI = new SuperPacmanPlayerStatusGUI(this, playerNumber,DEFAULT_HP,MAX_HP);

        this.keyboardButtons = keyboardButtons;

        sprites = RPGSprite.extractSprites(playerImage, 4, 1, 1, this , 64, 64, new Orientation[] {Orientation.DOWN , Orientation.LEFT , Orientation.UP, Orientation.RIGHT});
        animations = Animation.createAnimations(ANIMATION_DURATION/4, sprites);
        currentAnimation = animations[1];//Initializes the first animation to the upward direction

        desiredOrientation = Orientation.UP;

        resetMotion();
    }

    public void update(float deltaTime) {

        invulnerabilityCheck(deltaTime);



        updateDesiredOrientations();

        setNewCurrentAnimation();

        currentAnimation.update(deltaTime);
        super.update(deltaTime);

    }

    private void updateDesiredOrientations(){
        keyboard= getOwnerArea().getKeyboard();

        updateDesiredOrientation(Orientation.DOWN, keyboard.get(keyboardButtons[0]));
        updateDesiredOrientation(Orientation.LEFT, keyboard.get(keyboardButtons[1]));
        updateDesiredOrientation(Orientation.UP, keyboard.get(keyboardButtons[2]));
        updateDesiredOrientation(Orientation.RIGHT, keyboard.get(keyboardButtons[3]));

    }

    private void invulnerabilityCheck(float deltaTime){
        if(invulnerableTimer <=0){
            speedBoost = false;
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

    private void setNewCurrentAnimation(){
        if(!isDisplacementOccurs()){
            if(getOwnerArea().canEnterAreaCells(this,Collections.singletonList(getCurrentMainCellCoordinates().jump(desiredOrientation.toVector())))){

                orientate(desiredOrientation);




            }

            movePacman(speedBoost);

            currentAnimation.reset();
        }
        else{
            currentAnimation = animations[desiredOrientation.ordinal()];

        }
    }

    @Override
    public DiscreteCoordinates getCurrentMainCellCoordinates() {

        return super.getCurrentMainCellCoordinates();
    }

    private void movePacman(boolean speedBoost){
        if(speedBoost){
            move(MOVING_SPEED/2);
        }
        else{
            move(MOVING_SPEED);
        }
    }
    private void respawnPacman(){
        getOwnerArea().leaveAreaCells(SuperPacmanPlayer.this , getEnteredCells());
        this.abortCurrentMove();
        this.resetMotion();

        this.statusGUI.currentHp--;

        this.setCurrentPosition(spawnPosition.toVector());
        getOwnerArea().enterAreaCells(SuperPacmanPlayer.this, Collections.singletonList(spawnPosition));
    }


    public int getScore(){
        return score;
    }

    public boolean hasNoHp(){
        return statusGUI.getCurrentHp() <= 0;
    }

    public void setInvulnerable(float timer){

        invulnerableTimer = timer;
        isInvulnerable = true;
        speedBoost=true;
    }
    public boolean getIsInvulnerable(){
        return isInvulnerable;
    }

    public void resetInvulnerable(){
        invulnerableTimer=0;
        isInvulnerable = false;
        speedBoost=false;
    }



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
        ((SuperPacmanInteractionVisitor)v).interactWith(this);
    }

    //Handler d'intercation
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
            SuperPacmanPlayer.this.setInvulnerable(bonus.INVULNERABLE_TIMER); //rend le player invulnérable
            interactWith((AutomaticallyCollectableAreaEntity)bonus);
        }


        @Override
        public void interactWith(Ghost ghost) {

            if(getIsInvulnerable()){

                ghost.respawnGhost();

                score += ghost.GHOST_SCORE;


            }
            else{

                SuperPacmanPlayer.this.respawnPacman();

            }
        }
    }

}
