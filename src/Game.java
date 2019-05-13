import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class Game extends Application {
    //solutions of level (by index)
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
    private int level = 1;
    private Stage screen;
    private StartingScreen startingScreen;
    //Level status of game according to user(solved -> true,not solved -> false)
    private boolean[] levelStatus = {false,false,false,false,false};

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.screen = primaryStage;
        switchToStart(true);
    }

    private void switchToStart(boolean initial){
        // Start Scene for choosing level
        startingScreen = new StartingScreen();
        Scene startScene = new Scene(startingScreen,820,820);
        startingScreen.setVisible(true);
        screen.setResizable(false);
        startScene.setFill(Color.rgb(73,73,73));
        screen.setTitle("Puzzle Game");
        screen.setScene(startScene);
        screen.show();
        if(!initial){
            startingScreen.setButtonTypes(levelStatus);
            startingScreen.setButtonsVisible(false);
        }
        this.handleLevelButtons();

    }



    private void handleLevelButtons(){
        //Every level button handled by foreach loop
        for (LevelButton btn:startingScreen.getButtons()) {
            btn.setOnMouseClicked(event -> {
                //Only will solve 1. level or next one from last solved
                if(getLastSolved() + 2 >= btn.getLevel()){
                    this.level = btn.getLevel();
                    this.switchToGame();
                }

            });
        }
    }

    //Return index of last solved level or if does not solved return index 0
    private int getLastSolved(){
        int index = -1;
        for (int i = 0;i<levelStatus.length;i++) {
            if(levelStatus[i]){
                index = i;
            }
        }
        return index;
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
        board.nextBtn.setOnMouseClicked(event -> {
            this.levelStatus[level - 1] = true;
            this.level++;
            switchToGame();
        });
        board.backBtn.setOnMouseClicked(event -> {
            this.levelStatus[level - 1] = true;
            switchToStart(false);
        });
    }

}
