package games.test.data;

import org.newdawn.slick.Image;

import java.util.ArrayList;

public class NPCs {

    ArrayList<Image> pictures;
    ArrayList<String> names;

    public NPCs() {
        pictures = new ArrayList<Image>();
        names = new ArrayList<String>();
        names.add("unknown");
        try {
            pictures.add(new Image("images/characters/unknown.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addNPC(String name) {
        Image i = null;
        try {
            i = new Image("images/characters/"+name+".png");
        } catch (Exception e) {
            e.printStackTrace();
        }
        names.add(name);
        pictures.add(i);
    }

    public Image getNPCPicture(String name) {
        int i = 0;
        for (int j = 0; j < names.size(); j++) {
            if (names.get(j).equals(name))
                i = j;
        }
        return pictures.get(i);
    }

}
