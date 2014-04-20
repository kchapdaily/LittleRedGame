package gameFiles;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.LinkedList;

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

        for (int i = 0; i < 3; i++){
            choiceText[i] = "";
            choiceSceneRedirect[i] = "";
        }
    }

    public void loadScene(String sceneID) {
        this.ID = sceneID;
        loadImage();
        loadStoryText();
        loadChoices();
    }

    private void loadImage() {
        String imagePath = "res/img/imgLabel/" + this.ID + ".jpg";
        java.net.URL imageURL = getClass().getResource(imagePath);
        if (imageURL != null) {
            sceneImage = new ImageIcon(imageURL);
        } else {
            System.err.println("Couldn't find file: " + imagePath);
            sceneImage = null;
        }
    }

    private void loadStoryText() {
        String temp;
        storyText = "";

        try {
            InputStream isr = getClass().getResourceAsStream("res/txt/txtStory/" + this.ID + ".txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(isr));
            temp = in.readLine();

            while (temp != null){
                storyText = storyText + ' ' +temp;
                temp = in.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadChoices() {
        LinkedList<String> buffer = new LinkedList<String>();
        String temp = "";
        String delim = "^";
        int choiceIndex = 0;
        int location = 0;

        for (int i = 0; i <=2; i++){
            choiceText[i] = "";
            choiceSceneRedirect[i] = ";";
        }

        try {
            InputStream isr = getClass().getResourceAsStream("res/txt/txtResponse/" + this.ID + ".txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(isr));
            temp = in.readLine();

            while (temp != null){
                buffer.add(temp);
                temp = in.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(!buffer.isEmpty()){
            temp = buffer.pop();
            location = temp.indexOf(delim);
            choiceText[choiceIndex] = temp.substring(0, location);
            choiceSceneRedirect[choiceIndex] = temp.substring(location + 1, temp.length());
            choiceIndex++;
        }
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
