import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class Game extends Application {
    private int level = 4;
    private String [][][] solutions = {
            {
                    {"4","PipeVertical"},
                    {"8","PipeVertical"},
                    {"12","Pipe01"},
                    {"13","PipeHorizontal"}
            },
            {
                    {"4","PipeVertical"},
                    {"8","PipeVertical"},
                    {"12","Pipe01"},
                    {"13","PipeHorizontal"}
            },
            {
                    {"4","PipeVertical"},
                    {"8","PipeVertical"},
                    {"12","Pipe01"},
                    {"13","PipeHorizontal"}
            },
            {
                    {"8","Pipe01"},
                    {"9","PipeHorizontal"},
                    {"10","PipeHorizontal"},
                    {"11","Pipe00"}
            },
            {
                    {"4","PipeVertical"},
                    {"9","PipeHorizontal"},
                    {"10","PipeHorizontal"},
                    {"11","Pipe00"}
            }
    };
    private PathElement[][] animationPaths = {
            // animation paths of  1, 2, 3th levels
            {
                    new MoveTo(110,100),
                    new VLineTo(610),
                    new CubicCurveTo(115,610,115,710,205,710),
                    new HLineTo(725)
            },
            // animation paths of  4, 5th levels
            {
                    new MoveTo(110,100),
                    new VLineTo(410),
                    new CubicCurveTo(115,415,115,510,205,510),
                    new HLineTo(605),
                    new CubicCurveTo(720,510,710,400,710,400),
                    new VLineTo(275)
            }

    };

    private int[] animationIndexes = {0,0,0,1,1}; // indexes of animationPaths, which level in which index of animationPaths


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //new Board object. Initialize according to selected level with solution and animation path of it.
        Board board = new Board(this.level,this.solutions[level - 1],this.animationPaths[this.animationIndexes[level-1]]);
        Scene scene = new Scene(board,820,820);
        board.setVisible(true);
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        scene.setFill(Color.rgb(73,73,73));
        primaryStage.setTitle("Test Program");
        primaryStage.show();
    }
}
