import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Board extends Pane {

    private ArrayList<Tile> tiles = new ArrayList<>();
    private double[][] rowColumns = {
            {0 , 0 , 20 ,800},{800, 0, 20, 820},
            {0 , 0 ,800 , 20},{0, 800, 800, 20},
            {200, 0, 20, 865},{400, 0, 20, 865},
            {600, 0, 20, 865},{0, 200, 865, 20},
            {0, 400, 865, 20},{0, 600, 865, 20}
    };

    private int[][] positions = {
            {10, 10},{210, 10},{410, 10},{610, 10},
            {10,210},{210,210},{410,210},{610,210},
            {10,410},{210,410},{410,410},{610,410},
            {10,610},{210,610},{410,608},{610,610}
    };
    private String [][] solution;
    private Ball ball;
    private PathElement[] animation;
    protected Button nextBtn = new Button("NEXT LEVEL");





    public Board(int level,String [][] solution,PathElement[] animation)
    {
        this.solution = solution;
        this.animation = animation;

        drawBoard();
        placeByLevel(level);
        handleMoves();
        this.ball = new Ball(90,80);
        getChildren().add(this.ball);
        this.setBackground(new Background(new BackgroundFill(Color.rgb(73,73,73), CornerRadii.EMPTY, Insets.EMPTY)));
    }


    private void handleMoves()
    {
        for(Tile tile:tiles){
           tile.setOnMouseReleased(event -> handleTileMove(tile,event));
        }
    }


    private void changeArrayPositions(int firstPosition,int secondPosition)
    {
        Tile firstTile = tiles.get(firstPosition);
        Tile secondTile = tiles.get(secondPosition);
        tiles.set(firstPosition,secondTile);
        tiles.set(secondPosition,firstTile);
    }

    private void drawBoard ()
    {
        for (double[] rectangle:rowColumns) {
            getChildren().add(new Rectangle(rectangle[0],rectangle[1],rectangle[2],rectangle[3]));
        }
    }

    //Return index of square that mouse on
    private int determineSquare(double x,double y)
    {
        int startX,startY,endX,endY;
        int square = 0;
        for (int i = 0;i<positions.length;i++) {
            int[] startPoints = positions[i];
            startX = startPoints[0];
            startY = startPoints[1];
            endX = startX + 200;
            endY = startY + 200;
            if(x > startX && x < endX && y > startY && y < endY){
                square = i;
            }
        }
        return tiles.get(square).isEmpty()?square:-1;
    }


    private boolean checkSolved()
    {
        boolean solved = true;
        for(String [] item:solution){
            int position = Integer.parseInt(item[0]);
            String value = item[1];
            if(!((tiles.get(position)).getName()).equals(value)){
                solved = false;
            }
        }
        return solved;
    }




    private void handleTileMove(Tile tile, MouseEvent event)
    {
        int finalI = tiles.indexOf(tile);
        if(!tile.isCanMove()){
            return;
        }

        double currentX = event.getX();
        double currentY = event.getY();

        tile.setFitHeight(tile.getHeight());
        tile.setFitWidth(tile.getWidth());

        int square = determineSquare(currentX,currentY);

        //Move there only if there is empty and tile is one square away (left,right,up,down)
        if(square != -1){
            boolean moved = true;

            if(finalI - 1 >= 0 && finalI - 1 == square && finalI % 4 != 0 ){//left from tile

                tile.setX(positions[finalI-1][0]);
                tile.setY(positions[finalI-1][1]);


            }else if(finalI + 1 <= tiles.size()-1 && finalI + 1 == square && (finalI -3)  % 4 != 0){//right from tile
                tile.setX(positions[finalI+1][0]);
                tile.setY(positions[finalI+1][1]);

            }else if(finalI - 4 >= 0 && finalI - 4 == square){//up from tile
                tile.setX(positions[finalI-4][0]);
                tile.setY(positions[finalI-4][1]);

            }else if(finalI + 4 <= tiles.size()-1 && finalI + 4 == square){//down from tile
                tile.setX(positions[finalI+4][0]);
                tile.setY(positions[finalI+4][1]);

            }else{//Back to normal position
                tile.setX(positions[finalI][0]);
                tile.setY(positions[finalI][1]);
                moved = false;
            }

            if(moved){
                changeArrayPositions(finalI,square);//if move is invalid,change positions of tiles in array
                if(checkSolved()){                    //then check is puzzle solved?
                    animateBall();
                    showNext();
                }
            }

        }else{//Back to normal position

            tile.setX(positions[finalI][0]);
            tile.setY(positions[finalI][1]);

        }

    }


    private void showNext(){
        nextBtn.setLayoutY(getWidth()/2);
        nextBtn.setLayoutX(getHeight()/2);
        getChildren().add(nextBtn);
    }

    private void finish(){


    }

    private void animateBall  (){
        this.ball.animate(this.animation,2);
    }

    private void placeByLevel(int level)
    {
        try (Scanner input = new Scanner(new File(System.getProperty("user.dir") + "/src/levels/level" + level + ".txt"))) {
            boolean hasMove;
            while (input.hasNextLine()) {
                hasMove = false;
                String[] words = (input.nextLine()).split(",");
                int[] position = positions[Integer.parseInt(words[0]) - 1];
                if(words[1].equals("Pipe") || words[2].equals("none")){
                    hasMove = true;
                }
                if(words[2].equals("Free")){
                    tiles.add(new Tile());
                    continue;
                }
                String fileName = words[1] + words[2];
                tiles.add(new Tile(position[0], position[1], fileName, ".png",hasMove));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (Tile tile : tiles) {
            getChildren().add(tile);
        }
    }
}
