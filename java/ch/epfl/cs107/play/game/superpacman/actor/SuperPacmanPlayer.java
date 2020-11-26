package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.rpg.actor.Player;
import ch.epfl.cs107.play.game.superpacman.SuperPacman;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.game.tutosSolution.area.Tuto2Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class SuperPacmanPlayer extends Player {
    private int MOVING_SPEED = 6;
    private Sprite sprite;
    private float hp = 0;
    private TextGraphics message;
    private boolean isPassingADoor;
    private Orientation desiredOrientation;

    public enum Animation {
        LEFT_ANIMATION,
        RIGHT_ANIMATION,
        UP_ANIMATION,
        DOWN_ANIMATION;
    }

    /**
     * Default Player constructor
     *
     * @param area        (Area): Owner Area, not null
     */
    public SuperPacmanPlayer(SuperPacmanArea area) {
        super(area, Orientation.RIGHT, area.getSpawnPosition());
        sprite = new Sprite("yellowDot",1.f,1.f,this);

        message = new TextGraphics(Integer.toString((int)hp), 0.4f, Color.BLUE);
        message.setParent(this);
        message.setAnchor(new Vector(-0.3f, 0.1f));


        resetMotion();
    }

    public void update(float deltaTime) {
        if (hp > 0) {
            hp -=deltaTime;
            message.setText(Integer.toString((int)hp));
        }
        if (hp < 0) {
            hp = 0.f;
        }
        Keyboard keyboard= getOwnerArea().getKeyboard();

        moveOrientate(Orientation.LEFT, keyboard.get(Keyboard.LEFT));
        moveOrientate(Orientation.UP, keyboard.get(Keyboard.UP));
        moveOrientate(Orientation.RIGHT, keyboard.get(Keyboard.RIGHT));
        moveOrientate(Orientation.DOWN, keyboard.get(Keyboard.DOWN));

        super.update(deltaTime);

        /*
        List<DiscreteCoordinates> coords = getCurrentCells();
        if (coords != null) {
            for (DiscreteCoordinates c : coords) {
                if (((Tuto2Area)getOwnerArea()).isDoor(c)) setIsPassingADoor();
            }
        }
        */

    }
    private void moveOrientate(Orientation orientation, Button b){

        if(b.isDown()) {
            desiredOrientation = orientation;
        }

        if(isDisplacementOccurs() && getOwnerArea().canEnterAreaCells(this,Collections.singletonList(getCurrentMainCellCoordinates().jump(desiredOrientation.toVector())))){
            orientate(orientation);
            move(MOVING_SPEED);
        }

    }
    /**
     * Indicate the player just passed a door
     */
    protected void setIsPassingADoor(){ //
        isPassingADoor = true;
    }

    /**@return (boolean): true if the player is passing a door*/
    public boolean isPassingADoor(){
        return isPassingADoor;
    }

    public void resetDoorState() {
        isPassingADoor = false;
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
        message.draw(canvas);
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

            }
        }
}
