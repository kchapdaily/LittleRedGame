import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Observable;

/**
 * Created by kevin on 4/11/14.
 */
public class Model extends Observable{

    private static LinkedList<Scene> timeline = new LinkedList<Scene>();

    public Scene getScene(int i){
        if (i > timeline.size()){
            throw new ArrayIndexOutOfBoundsException("index " + i + " has no corresponding scene.");
        }

        else{
            return timeline.get(i);
        }
    }

    public void newScene(Scene s){
        if (timeline.contains(s)){
            throw new InputMismatchException("attempted to add duplicate scene");
        }

        else{
            timeline.add(s);
        }
    }

    public void evaluateGameState(){
        notifyObservers(/*changed timeline scene*/);
    }

} //Model
