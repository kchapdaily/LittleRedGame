package gameFiles;

import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer {

    private static final String TITLE_SCENE_ID = "000";
    // Private data members
    private Model model;

    // Overloaded Constructor with parameters for Model and View
    public Controller(Model m, View v) {
        model = m;
        v.addObserver(this);
    }

    public void playGame() {
        // Load Title Scene to start game
        model.loadNewScene(TITLE_SCENE_ID);
    }

    private String getNextScene(String userResponse) {
        int intSceneID = Integer.parseInt(model.getCurrentSceneID());
        switch (intSceneID) {
            case 0:
                if (userResponse.equalsIgnoreCase("A")) {
                } else if (userResponse.equalsIgnoreCase("B")) {
                } else if (userResponse.equalsIgnoreCase("C")) {
                } else {
                    return TITLE_SCENE_ID;
                }
            case 1:
                if (userResponse.equalsIgnoreCase("A")) {
                } else if (userResponse.equalsIgnoreCase("B")) {
                } else if (userResponse.equalsIgnoreCase("C")) {
                } else {
                    return TITLE_SCENE_ID;
                }
            case 2:
                if (userResponse.equalsIgnoreCase("A")) {
                } else if (userResponse.equalsIgnoreCase("B")) {
                } else if (userResponse.equalsIgnoreCase("C")) {
                } else {
                    return TITLE_SCENE_ID;
                }
            case 3:
                if (userResponse.equalsIgnoreCase("A")) {
                } else if (userResponse.equalsIgnoreCase("B")) {
                } else if (userResponse.equalsIgnoreCase("C")) {
                } else {
                    return TITLE_SCENE_ID;
                }
            default:
                System.out.println("Invalid Scene Accessed");
                return TITLE_SCENE_ID;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Controller: Received choice " + arg + " from View for Scene " + model.getCurrentSceneID() + ".");
        String newSceneID = getNextScene((String) arg);
        model.loadNewScene(newSceneID);
    }
} //Controller
