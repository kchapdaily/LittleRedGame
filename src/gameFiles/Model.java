import java.util.Observable;

/**
 * Created by kevin on 4/11/14.
 */
public class Model extends Observable{

    private Scene[] sceneBuffer;
    private int sceneBufferID;

    public Model() {
        sceneBuffer = new Scene[2];
        sceneBuffer[0] = null;
        sceneBuffer[1] = null;

        sceneBufferID = 0;

    }

    public void evaluateGameState(){
        notifyObservers(/*changed timeline scene*/);
    }

    public void swapSceneBuffer(){
        sceneBuffer[sceneBufferID] = null;
        sceneBufferID = (sceneBufferID + 1) % 2;
    }

} //Model
