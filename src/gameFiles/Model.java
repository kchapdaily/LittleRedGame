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
        boringFeature06RedirectTable[0] = "000";
        boringFeature06RedirectTable[1] = "001";
        boringFeature06RedirectTable[2] = "002";
        boringFeature06RedirectTable[3] = "003";
        boringFeature06RedirectTable[4] = "004";
        boringFeature06RedirectTable[5] = "005";
        boringFeature06RedirectTable[6] = "006";
        boringFeature06RedirectTable[7] = "007";
        boringFeature06RedirectTable[8] = "008";
        boringFeature06RedirectTable[9] = "009";
        boringFeature06RedirectTable[10] = "010";
        boringFeature06RedirectTable[11] = "011";
        boringFeature06RedirectTable[12] = "012";
        boringFeature06RedirectTable[13] = "013";
        boringFeature06RedirectTable[14] = "014";
        boringFeature06RedirectTable[15] = "015";
        boringFeature06RedirectTable[16] = "016";
        boringFeature06RedirectTable[17] = "017";
        boringFeature06RedirectTable[18] = "018";
        boringFeature06RedirectTable[19] = "019";
        boringFeature06RedirectTable[20] = "020";
        boringFeature06RedirectTable[21] = "021";
        boringFeature06RedirectTable[22] = "022";
        boringFeature06RedirectTable[23] = "023";
        boringFeature06RedirectTable[24] = "024";
        boringFeature06RedirectTable[25] = "025";
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
