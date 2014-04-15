package gameFiles;

import java.util.Observable;

public class Model extends Observable{

    private Scene currentScene;

    public Model(View v) {
        currentScene = new Scene(); // Assign currentScene to null
        addObserver(v);             // Add view as an Observer of this model
    }

    public String getCurrentSceneID() {
        return currentScene.getID();
    }

    public void loadNewScene(String sceneID) {
        System.out.println("Model: Loading Scene " + sceneID + "...");
        currentScene.loadScene(sceneID);

        // Pass the Scene to the View
        setChanged();
        notifyObservers(currentScene);
    }
} //Model
