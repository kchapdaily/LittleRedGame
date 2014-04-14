package gameFiles;

import java.util.InputMismatchException;
import java.util.LinkedList;

/**
 * Created by kevin on 4/11/14.
 */
public class Model {
    private static LinkedList<Scene> timeline = new LinkedList<Scene>();

    public Scene getScene(int i) {
        if (i > timeline.size()) {
            throw new ArrayIndexOutOfBoundsException("that index has no corresponding scene.");
        } else {
            return timeline.get(i);
        }
    }

    private void newScene(Scene s) {
        if (timeline.contains(s)) {
            throw new InputMismatchException("that scene already exists");
        } else {
            timeline.add(s);
        }
    }

}
