package gameFiles;

import javax.swing.*;

public class Scene {

    // Private data members
    private String ID;
    private ImageIcon sceneImage;
    private String storyText;
    private String choices[];

    public Scene() {
        ID = "999";
        sceneImage = null;
        storyText = "";
        choices = new String[2];
    }

    public void loadScene(String sceneID) {
        this.ID = sceneID;
        loadImage();
        loadStoryText();
        loadChoices();
    }

    private void loadImage() {
        String imagePath = "res/img/imgLabel/" + ID + ".jpg";
        java.net.URL imageURL = getClass().getResource(imagePath);
        if (imageURL != null) {
            sceneImage = new ImageIcon(imageURL);
        } else {
            System.err.println("Couldn't find file: " + imagePath);
            sceneImage = null;
        }
    }

    private void loadStoryText() {

    }

    private void loadChoices() {

    }

    public String getID() {
        return ID;
    }

    public ImageIcon getSceneImage() {
        return this.sceneImage;
    }

    public String getStoryText(){
        return this.storyText;
    }

    public String[] getChoices(){
        return this.choices;
    }
}
