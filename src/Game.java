import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game extends Application {
    int level = 1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        Board board = new Board(1);


        Scene scene = new Scene(board,820,820);
        board.setVisible(true);
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        scene.setFill(Color.rgb(73,73,73));
        primaryStage.setTitle("Test Program");
        primaryStage.show();
    }
}
