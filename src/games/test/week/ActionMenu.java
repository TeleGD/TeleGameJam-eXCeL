package games.test.week;


import games.test.data.GameData;
import games.test.data.Project;
import games.test.week.actions.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

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
    private Image select;
    private Image theWeek;
    private int width;
    private int height;
    private float smallScale;
    private boolean isOver;
    private int current_day;
    // song
    Sound selection_song;

    public ActionMenu(GameData data) {
        initImages();
        isOver = false;

        // song
        try {
            selection_song = new Sound("songs/selection.wav");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initImages() {

        try {
            this.background =  new Image("images/home.png");
            this.clubs = new Image("images/clubs.png");
            this.sleep = new Image("images/sleep.png");
            this.socialize = new Image("images/socialize.png");
            this.study = new Image("images/study.png");
            this.dateTime = new Image("images/dateTime.png");
            this.select = new Image("images/select.png");
            this.theWeek = new Image("images/theWeek.png");

        }catch(Exception e){
            System.out.printf("PROBLEM !");
        }
    }

    public void reset() {
        current_day = 0;
        isOver = false;
    }

    public void setWeek(Week week) {
        this.week = week;
    }

    public void update(GameContainer container, StateBasedGame game, int delta) {
    }

    public void render(GameContainer container, StateBasedGame game, Graphics context) {
        if(this.background != null){

            // taille de la fenêtre
            width = container.getWidth();
            height = container.getHeight();

            // Affichage des projets
            if ( data != null) {
                ArrayList<Project> projects = data.getPlayer().getProjects();
                int nb_projets = projects.size();
                int icon_width = (int)(0.1*width);
                int spacing = (int)(0.03*width);
                int x0 = width / 2 - (nb_projets*(icon_width+spacing) - spacing) / 2;
                for (int i = 0; i < nb_projets; i++) {
                    projects.get(i).getIcon().draw(x0 + i*(icon_width+spacing), 0, icon_width, icon_width);
                    projects.get(i).getActiveHeart().draw(x0 + i*(icon_width+spacing) + (int)(0.01*width), (int)(1.1*icon_width), spacing, spacing);
                }
            }

            // Affichage des icones (jours de la semaine, boutons)

            float b = this.background.getWidth();
            this.scale = width/b ;
            this.background.draw(0,0,this.scale);

            smallScale = scale/2;
            int h = (int) height*2/3;
            int step = (int) width/100;
            int w2 = (int) (study.getWidth()*smallScale);
            int h2 = (int) (study.getHeight()*smallScale);
            int w = (int) (width - w2*3 - 2*step) /2;

            this.clubs.draw(w,h,smallScale);
            this.socialize.draw(w+w2+step,h,smallScale);
            this.study.draw(w+2*w2+2*step,h,smallScale);
            this.sleep.draw(w,h+h2+step,smallScale);
            this.dateTime.draw(w+w2+step,h+h2+step,smallScale);

            int midW =(int)(width-theWeek.getWidth()*scale)/2;
            int midH =(int)(height-theWeek.getHeight()*scale)/2;
            int step2 = (int) (theWeek.getWidth()*scale)/7;
            int midW2 = (int) (midW + step2/2 - (select.getWidth()*smallScale)/2);
            this.theWeek.draw(midW, midH, scale);

            this.select.draw(midW2+current_day*step2,midH-select.getHeight()*smallScale,smallScale);

        }
    }

    public void keyPressed(int key, char c) {

    }

    public void mousePressed(int button, int x, int y) {

        int h = (int) height*2/3;
        int step = (int) width/100;
        int w2 = (int) (study.getWidth()*smallScale);
        int h2 = (int) (study.getHeight()*smallScale);
        int w = (int) (width - w2*3 - 2*step) /2;

        if (isClicked(w, h,w2, h2, x, y)) {

            // on a cliqué sur Club
            ActionEvent action = new Clubs();
            week.setActionofDayIndex(current_day,action);

            selection_song.play();
            current_day ++;

        } else if (isClicked(w+w2+step, h,w2, h2, x, y)) {
            // Socialize

            ActionEvent action = new Socialize();
            week.setActionofDayIndex(current_day,action);

            selection_song.play();
            current_day ++;

        } else if (isClicked(w+2*w2+2*step, h,w2, h2, x, y)) {
            // Study

            ActionEvent action = new Study();
            week.setActionofDayIndex(current_day,action);

            selection_song.play();
            current_day++;

        }else if (isClicked(w,h+h2+step,w2,h2,x,y)) {
            // Sleep

            ActionEvent action = new Sleep();
            week.setActionofDayIndex(current_day,action);

            selection_song.play();
            current_day++;

        }else if(isClicked(w+w2+step,h+h2+step,w2,h2,x,y)){
                // Date Time
                System.out.println("CLIC date time not implemented");

//            ActionEvent action = new Date();
//            week.setActionofDayIndex(current_day,action);

        } else {

        }

        // fin du menu
        if (current_day >= Week.nb_days) {
            isOver = true;
        }

    }

public boolean isClicked(int x0, int y0, int width, int height, int x, int y) {
        if (x >= x0 && x < x0+width && y >= y0 && y < y0+height)
        return true;
        else
        return false;
        }

public boolean isOver() {
        return isOver;
        }

}
