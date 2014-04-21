package gameFiles;

import java.util.Observable;

public class Model extends Observable{

    private Scene currentScene;
    private String boringFeature06RedirectTable[]; //contains all possible random redirects.

    public Model(View v) {
        currentScene = new Scene(); // Assign currentScene to null
        addObserver(v);             // Add view as an Observer of this model
//        ignoreList[0] = "";
        initializeSceneList();
    }

    private void initializeSceneList(){
        //TODO: edit boringFeature06RedirectTable to ensure an interesting result every run through
        boringFeature06RedirectTable = new String[26];
        boringFeature06RedirectTable[0] = "039";
        boringFeature06RedirectTable[1] = "057";
        boringFeature06RedirectTable[2] = "024";
        boringFeature06RedirectTable[3] = "020";
        boringFeature06RedirectTable[4] = "037";
        boringFeature06RedirectTable[5] = "017";
        boringFeature06RedirectTable[6] = "025";
        boringFeature06RedirectTable[7] = "041";
        boringFeature06RedirectTable[8] = "069";
        boringFeature06RedirectTable[9] = "029";
        boringFeature06RedirectTable[10] = "024";
        boringFeature06RedirectTable[11] = "060";
        boringFeature06RedirectTable[12] = "067";
        boringFeature06RedirectTable[13] = "044";
        boringFeature06RedirectTable[14] = "021";
        boringFeature06RedirectTable[15] = "023";
        boringFeature06RedirectTable[16] = "036";
        boringFeature06RedirectTable[17] = "049";
        boringFeature06RedirectTable[18] = "062";
        boringFeature06RedirectTable[19] = "043";
        boringFeature06RedirectTable[20] = "038";
        boringFeature06RedirectTable[21] = "058";
        boringFeature06RedirectTable[22] = "048";
        boringFeature06RedirectTable[23] = "066";
        boringFeature06RedirectTable[24] = "053";
        boringFeature06RedirectTable[25] = "070";
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void loadNewScene(String sceneID) {
        System.out.println("Model: Loading Scene " + sceneID + "...");

        if (sceneID == "woo"){
            //TODO: make sure random index is scaled correctly
            int index =(int)(Math.random()*(boringFeature06RedirectTable.length + 1));
            System.out.print("index: " + index + '\n');
            sceneID = boringFeature06RedirectTable[index];
            System.out.print("Too drunk, going to scene: " + sceneID + ".\n");
        }

        currentScene.loadScene(sceneID);

        // Pass the Scene to the View
        setChanged();
        notifyObservers(currentScene);
    }
} //Model
