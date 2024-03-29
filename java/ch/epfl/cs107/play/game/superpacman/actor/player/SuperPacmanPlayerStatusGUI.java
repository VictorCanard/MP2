package ch.epfl.cs107.play.game.superpacman.actor.player;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.awt.*;



class SuperPacmanPlayerStatusGUI implements Graphics {
    private int STARTING_HP;
    private  int MAX_HP;
    protected int currentHp;

    private SuperPacmanPlayer player;
    private int playerNumber;

    private ImageGraphics[] life;
    private TextGraphics scoreGraphics;
    public final static int DEPTH = 1;
    private Vector anchor;
    private float width;
    private float height;

    private final float[] scorePositionsX = new float[]{6.5f, 18f};
    private final float[] hpPositionsX = new float[]{0f, 24f};


    public SuperPacmanPlayerStatusGUI(SuperPacmanPlayer player, int playerNumber, int STARTING_HP, int MAX_HP){
        this.STARTING_HP = STARTING_HP;
        currentHp = STARTING_HP;
        this.MAX_HP=MAX_HP;

        life = new ImageGraphics[MAX_HP];

        this.player=player;
        this.playerNumber = playerNumber;
    }

    public int getCurrentHp(){
        return currentHp;
    }

    @Override
    public void draw(Canvas canvas) {

        width = canvas.getScaledWidth();
        height = canvas.getScaledHeight();
        anchor = canvas.getTransform().getOrigin().sub(new Vector(width/2, height/2));


        drawHP(canvas);

        drawScore(canvas);



    }

    public void drawScore(Canvas canvas){
        scoreGraphics = new TextGraphics("SCORE : "+ player.getScore(),1f, Color.YELLOW, Color.BLUE,0.04f,false,false, anchor.add(new Vector(scorePositionsX[playerNumber],height - 1.375f)));
        scoreGraphics.draw(canvas);
    }

    public void drawHP(Canvas canvas){
        for(int i =0; i<MAX_HP;i++){


            if(i<currentHp){
                drawRedHP(i);
            }
            else{
                drawGrayHP(i);
            }



            life[i].draw(canvas);
        }
    }
    public void drawRedHP(int displacement){
        life[displacement] = new ImageGraphics(ResourcePath.getSprite("superpacman/lifeDisplay"), 1.f, 1.f, new RegionOfInterest(0, 0, 64, 64), anchor.add(new Vector(hpPositionsX[playerNumber]+displacement+0.5f, height - 1.375f)), 1, DEPTH);
    }

    public void drawGrayHP(int displacement){
        life[displacement] = new ImageGraphics(ResourcePath.getSprite("superpacman/lifeDisplay"), 1.f, 1.f, new RegionOfInterest(64, 0, 64, 64), anchor.add(new Vector(hpPositionsX[playerNumber]+displacement+0.5f, height - 1.375f)), 1, DEPTH);
    }

}
