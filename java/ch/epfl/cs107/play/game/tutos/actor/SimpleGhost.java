package ch.epfl.cs107.play.game.tutos.actor;

import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.Positionable;
import ch.epfl.cs107.play.math.TextAlign;
import ch.epfl.cs107.play.math.Vector;

import java.awt.*;

public class SimpleGhost extends Entity {
    private Sprite ghostSprite;
    private float niveauEnergie;
    private TextGraphics hpText;
    private Vector vect;
    private float delta = 0.05f;

    public SimpleGhost(Vector position, String spriteName){

        super(position);

        ghostSprite = new Sprite(spriteName,1.f,1.f, this);
        niveauEnergie=10;
        hpText = new TextGraphics(Integer.toString((int)niveauEnergie),0.4f, Color.BLUE);
        hpText.setParent( this);
        hpText.setAnchor(new Vector(-0.3f,0.1f));

    }
    public boolean isWeak(){
        return niveauEnergie<=0;
    }
    public void strengthen(){
        niveauEnergie=20;
    }

    public void update(float deltaTime){
        if(niveauEnergie>0){
            niveauEnergie-=deltaTime;
            hpText.setText(Integer.toString((int)niveauEnergie));

        }
        else{
            niveauEnergie = 0.f;
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
}
