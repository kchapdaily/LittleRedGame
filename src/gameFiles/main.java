package gameFiles;

public class main {

    public static void main(String[] args) {
        View gameView = new View();
        Model gameModel = new Model(gameView);
        Controller gameController = new Controller(gameModel, gameView);

        gameController.playGame();
    }
}