package ch.epfl.cs107.play.game.superpacman;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Window;

import java.awt.*;

class PauseGraphics implements Graphics {
    private TextGraphics pauseText;
    private float width;
    private float height;
    private Vector anchor;

    public PauseGraphics(Window gameWindow){

        width = gameWindow.getScaledWidth();
        height = gameWindow.getScaledHeight();
        anchor = gameWindow.getTransform().getOrigin().sub(new Vector(width/2, height/2));

        pauseText = new TextGraphics(" PAUSED ",1f, Color.YELLOW, Color.BLUE,0.04f,false,false, anchor.add(new Vector(6.5f, height - 1.375f)));
    }
    @Override
    public void draw(Canvas canvas) {

        pauseText.draw(canvas);
    }


}
