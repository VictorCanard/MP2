package ch.epfl.cs107.play.game.tutos.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.tutos.Tuto2Behavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public class Tuto2Area extends Area {

    public boolean begin(Window window, FileSystem filesystem){
        if (super.begin(window,filesystem)){
            setBehavior((new Tuto2Behavior(window,getTitle())));
            createArea();
            return true;
        }
        else{
            return false;
        }

    }


    protected void createArea() {

    }

    @Override
    public float getCameraScaleFactor() {
        return Tuto2.SCALE_FACTOR;
    }

    @Override
    public String getTitle() {
        return "Tuto2";
    }
}
