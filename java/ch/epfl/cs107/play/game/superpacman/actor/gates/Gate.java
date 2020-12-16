package ch.epfl.cs107.play.game.superpacman.actor.gates;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGraph;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.actor.collectables.Key;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanBehavior;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

class Gate extends AreaEntity {
    private Key key1;
    private Key key2;
    private Sprite sprite;
    protected SuperPacmanBehavior behavior;
    protected DiscreteCoordinates position;

    /**
     * Default AreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity in the Area. Not null
     * @param position    (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public Gate(Area area, Orientation orientation, DiscreteCoordinates position, SuperPacmanBehavior behavior) {
        super(area, orientation, position);

        this.position=position;
        this.behavior = behavior;

        createGateSprites(orientation);


    }
    protected void setNode(){
        if (this.takeCellSpace()){
            behavior.signal(position);
        }
    }

    public void createGateSprites(Orientation orientation){
        if (orientation == Orientation.UP || orientation == Orientation.DOWN){
            sprite = new RPGSprite("superpacman/gate",1,1,this, new RegionOfInterest(0,0,64,64));
        }
        if (orientation == Orientation.RIGHT || orientation == Orientation.LEFT){
            sprite = new RPGSprite("superpacman/gate",1,1,this, new RegionOfInterest(0,64,64,64));
        }
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }


    @Override
    public void draw(Canvas canvas) {
        if(sprite != null && this.takeCellSpace()){
            sprite.draw(canvas);
        }

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
        return false;
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        v.interactWith(this);

    }
}
