package games.test.week;


import games.test.data.GameData;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Image;

import app.AppLoader;

public class ActionMenu {

    private GameData data;
    private Week week;
// Rendering :
    private float scale;
    private Image background;
    private Image clubs;
    private Image sleep;
    private Image socialize;
    private Image study;
    private Image dateTime;


    public ActionMenu(GameData data) {
        initImages();
    }

    public void setWeek(Week week) {
        this.week = week;
    }

    public void initImages() {

        try {
            this.background =  new Image("images/home.png");
        }catch(Exception e){
            System.out.printf("PROBLEM !");
        }
    }

    public void update(GameContainer container, StateBasedGame game, int delta) {
    }

    public void render(GameContainer container, StateBasedGame game, Graphics context) {
        if(this.background != null){
            float a = container.getWidth();
            float b = this.background.getWidth();
            this.scale = a/b ;
            this.background.draw(0,0,this.scale);
        }

    }

    public void keyPressed(int key, char c) {

    }

    public void mousePressed(int button, int x, int y) {

    }

}
