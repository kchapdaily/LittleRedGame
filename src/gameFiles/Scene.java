import java.awt.*;

/**
 * Created by kevin on 4/11/14.
 */
public class Scene {
    private int ID;
    private Image sceneImage;
    private String storyText;
    private String choices[];

    public Scene() {
        ID = 9999;
        sceneImage = null;
        storyText = "";
        choices = new String[2];
    }

    public void generateChoices(){

    }

    public void loadImages(){

    }

    public void setID(int x){
        //check to see if 'x' is inbounds
        this.ID = x;
    }

    public int getID(){
        return this.ID;
    }

    public void setSceneImage(/*something*/){
        //stuff
    }

    public Image getSceneImage(){
        return this.sceneImage;
    }

    public void setStoryText(String s){
        this.storyText = s;
    }

    public String getStoryText(){
        return this.storyText;
    }

    public void setChoices(String s){
        //stuff
    }

    public String[] getChoices(){
        return this.choices;
    }
}
