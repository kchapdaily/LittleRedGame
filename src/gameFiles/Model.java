package gameFiles;

import java.util.Observable;
import java.util.Random;

public class Model extends Observable{

    private Scene currentScene;
    private String ignoreList[];

    public Model(View v) {
        currentScene = new Scene(); // Assign currentScene to null
        addObserver(v);             // Add view as an Observer of this model
//        ignoreList[0] = "";
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void loadNewScene(String sceneID) {
        System.out.println("Model: Loading Scene " + sceneID + "...");

        if (sceneID == "woo"){
            //get a random scene
            //TODO: need to make sure this is scaled right, and need to get it in the form of 'xxx' instead of 'xx.x'
            sceneID=Double.toString((int)(Math.random()*71));
            System.out.print("Too drunk, going to scene: " + sceneID + ".\n");
        }

        currentScene.loadScene(sceneID);

        // Pass the Scene to the View
        setChanged();
        notifyObservers(currentScene);
    }
} //Model
