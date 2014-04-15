package gameFiles;

import javax.swing.*;

public class Scene {

    // Private data members
    private String ID;
    private ImageIcon sceneImage;
    private String storyText;
    private String choiceText[];
    private String choiceSceneRedirect[];

    public Scene() {
        ID = "999";
        sceneImage = null;
        storyText = "";
        choiceText = new String[3];
        choiceSceneRedirect = new String[3];
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

    public String[] getChoiceText() {
        return this.choiceText;
    }

    public String getChoiceSceneRedirect(int choice) {
        return this.choiceSceneRedirect[choice];
    }
}
