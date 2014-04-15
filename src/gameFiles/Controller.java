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

    private String getNextScene(int choiceNumber) {
        if (choiceNumber == -1) {
            System.out.println("What button did you just press?!?");
            return TITLE_SCENE_ID;
        }
        return model.getCurrentScene().getChoiceSceneRedirect(choiceNumber);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Controller: Received choice " + arg + " from View for Scene " + model.getCurrentScene().getID() + ".");
        String newSceneID = getNextScene((Integer) arg);
        model.loadNewScene(newSceneID);
    }
} //Controller
