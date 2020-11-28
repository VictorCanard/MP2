package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.awt.*;

class SuperPacmanPlayerStatusGUI implements Graphics {
    private int STARTING_HP =0;
    private  int MAX_HP=0;

    private ImageGraphics life;
    private TextGraphics scoreGraphics;
    public final static int DEPTH = 5;
    private Vector anchor;
    private float width;
    private float height;

    public SuperPacmanPlayerStatusGUI(int STARTING_HP, int MAX_HP){
        this.STARTING_HP = STARTING_HP;
        this.MAX_HP=MAX_HP;
    }
    @Override
    public void draw(Canvas canvas) {
        width = canvas.getScaledWidth();
        height = canvas.getScaledHeight();
        anchor = canvas.getTransform().getOrigin().sub(new Vector(width/2, height/2));

        for(int i =0; i<MAX_HP;i++){

            if(i<STARTING_HP){
                life = new ImageGraphics(ResourcePath.getSprite("superpacman/lifeDisplay"), 1.f, 1.f, new RegionOfInterest(0, 0, 64, 64), anchor.add(new Vector(i*64, height - 1.375f)), 1, DEPTH);
            }
            else{
                life = new ImageGraphics(ResourcePath.getSprite("superpacman/lifeDisplay"), 1.f, 1.f, new RegionOfInterest(64, 0, 64, 64), anchor.add(new Vector(i*64, height - 1.375f)), 1, DEPTH);
            }

            life.draw(canvas);
        }

        scoreGraphics = new TextGraphics("SCORE : "+SuperPacmanPlayer.score,15.f, Color.YELLOW, Color.BLACK,5f,false,false,anchor.add(new Vector(MAX_HP*64, height - 1.375f)));
        scoreGraphics.draw(canvas);


    }
}