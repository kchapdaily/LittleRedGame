package gameFiles;

import javax.swing.*;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

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
        String temp;

        try {
            java.net.URL url = getClass().getResource("res/txt/txtStory/" + this.ID + ".txt");
            BufferedReader in = new BufferedReader(new FileReader(url.getPath()));
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

        //for testing
        System.out.print("story text: " + storyText + '\n');
    }

    private void loadChoices() {
        LinkedList<String> buffer = new LinkedList<String>();
        String temp = "";
        String delim = "^";
        int choiceIndex = 0;
        int location = 0;

        try {
            java.net.URL url = getClass().getResource("res/txt/txtResponse/" + this.ID + ".txt");
            BufferedReader in = new BufferedReader(new FileReader(url.getPath()));
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

        //for testing
        /*for(int i = 0; i <=2; i++){
            System.out.print("choiceText[" + i + "]=" + choiceText[i] + '\n');
            System.out.print("choiceSceneRedirect[" + i + "]=" + choiceSceneRedirect[i] + '\n');
        }*/
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
