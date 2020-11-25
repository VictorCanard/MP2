package ch.epfl.cs107.play.game.tutos.actor;

import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.actor.Text;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.awt.*;
import java.security.Key;
import java.util.Collections;
import java.util.List;

public class GhostPlayer extends MovableAreaEntity {
    private Sprite ghostSprite;
    private float niveauEnergie;
    private TextGraphics hpText;
    private float delta =0.5f;
    private final static int ANIMATION_DURATION=8;
    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */
    public GhostPlayer(Area area, Orientation orientation, DiscreteCoordinates position, String sprite) {
        super(area, orientation, position);


        ghostSprite = new Sprite(sprite,1.f,1.f, this);
        niveauEnergie=10;
        hpText = new TextGraphics(Integer.toString((int)niveauEnergie),0.4f, Color.BLUE);
        hpText.setParent( this);
        hpText.setAnchor(new Vector(-0.3f,0.1f));

        resetMotion();

    }

    public boolean isWeak(){
        return niveauEnergie<=0;
    }
    public void strengthen(){
        niveauEnergie=20;
    }

    public void update(float deltaTime){
        Keyboard keyboard = getOwnerArea().getKeyboard();

        moveOrientate(Orientation.DOWN, keyboard.get(Keyboard.DOWN));
        moveOrientate(Orientation.LEFT,keyboard.get(Keyboard.LEFT));
        moveOrientate(Orientation.RIGHT, keyboard.get(Keyboard.RIGHT));
        moveOrientate(Orientation.UP, keyboard.get(Keyboard.UP));

        if(niveauEnergie>0){
            niveauEnergie-=deltaTime;
            hpText.setText(Integer.toString((int)niveauEnergie));
        }
        else{
            niveauEnergie = 0.f;
        }
        super.update(deltaTime);

    }

    private void moveOrientate(Orientation orientation, Button b){
        if(b.isDown()){
            if(getOrientation().equals(orientation)){
                move(ANIMATION_DURATION);
            }
            else{
                orientate(orientation);
            }
        }

    }
    @Override
    public void draw(ch.epfl.cs107.play.window.Canvas canvas) {
        ghostSprite.draw(canvas);
        hpText.draw(canvas);

    }

    public void moveUp(){
        setCurrentPosition(getPosition().add(0.f,delta));
    }
    public void moveDown(){
        setCurrentPosition(getPosition().add(0.f,-delta));
    }
    public void moveLeft(){
        setCurrentPosition(getPosition().add(-delta,0.f));
    }
    public void moveRight(){
        setCurrentPosition(getPosition().add(delta,0.f));
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    @Override
    public boolean takeCellSpace() {
        return true;
    }

    @Override
    public boolean isCellInteractable() {
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return true;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {

    }
    public void enterArea(Area area, DiscreteCoordinates position){
        area.registerActor(this);
        area.setViewCandidate(this);
        setOwnerArea(area);
        setCurrentPosition(position.toVector());
        resetMotion();

    }
    public void leaveArea(){
        getOwnerArea().unregisterActor(this);

    }

}
