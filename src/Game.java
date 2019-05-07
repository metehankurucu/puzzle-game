import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class Game extends Application {
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

    private int level = 4;
    private boolean levelSelected = false;
    private Stage screen;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.screen = primaryStage;


        // Start Scene for choosing level
        StartingScreen starting = new StartingScreen();
        Button btn1 = new Button("1");
        btn1.setLayoutX(200);
        btn1.setLayoutY(200);

        starting.getChildren().add(btn1);
        Scene startScene = new Scene(starting,820,820);
        starting.setVisible(true);
        screen.setResizable(true);
        startScene.setFill(Color.rgb(73,73,73));
        screen.setTitle("Puzzle Game");

        screen.setScene(startScene);
        screen.show();

        btn1.setOnAction(e -> {
            this.levelSelected = true;
            this.level = 1;
            this.switchToGame();
        });







    }


    /**
     * Main Board Scene
     * new Board object. Initialize according to selected level with solution and animation path of it.
     */
    private void switchToGame(){
        Board board = new Board(this.level,this.solutions[level - 1],this.animationPaths[this.animationIndexes[level-1]]);
        Scene gameScene = new Scene(board,820,820);
        board.setVisible(true);
        gameScene.setFill(Color.rgb(73,73,73));
        screen.setScene(gameScene);
        board.nextBtn.setOnAction(event -> {
            this.level++;
            switchToGame();
        });
    }












}
