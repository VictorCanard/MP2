package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.*;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.Signal;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public class Key extends AutomaticallyCollectableAreaEntity implements Interactable, Logic {
    private Sprite sprite;
    private Logic signal = Logic.FALSE;

    /**
     * Default CollectableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */
    public Key(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        sprite = new RPGSprite("superpacman/key",1,1,this);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    public Logic getSignal() {
        return signal;
    }

    @Override
    public int addScore() {
        return 0;
    }

    @Override
    public void draw(Canvas canvas) {
        if(sprite != null)
            sprite.draw(canvas);
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
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
    public void collect() {
        signal = Logic.TRUE;
        super.collect();
    }

    @Override
    public boolean isOn() {
        return signal == Logic.TRUE;
    }

    @Override
    public boolean isOff() {
        return signal == Logic.FALSE;
    }

    @Override
    public float getIntensity() {
        return 0;
    }
}
