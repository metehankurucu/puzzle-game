
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import sun.tools.java.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Board extends Pane {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
    }
    private ArrayList<Tile> tiles = new ArrayList<>();
    private double[][] rowColumns = {
            {0, 0, 20, 800},{800, 0, 20, 820},{0, 0, 800, 20},
            {0, 800, 800, 20}, {200, 0, 20, 865},{400, 0, 20, 865},{600, 0, 20, 865},
            {0, 200, 865, 20},{0, 400, 865, 20},{0, 600, 865, 20}
    };

    private int[][] positions = {
            {10,10},{210,10},{410,10},{610,10},
            {10,210},{210,210},{410,210},{610,210},
            {10,410},{210,410},{410,410},{610,410},
            {10,610},{210,610},{410,605},{610,610}
    };


    private void placeByLevel(int level){


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
                String fileName = words[1] + words[2] + ".png";
                tiles.add(new Tile(position[0], position[1], fileName,hasMove));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (Tile tile : tiles) {
            getChildren().add(tile);
        }
    }


    public Board(int level) throws Exception {
        drawBoard();
        placeByLevel(level);
        handleMoves();

    }


    private void handleMoves(){
        for(int i = 0;i<tiles.size();i++){
           Tile tile = tiles.get(i);
            int finalI = i;
            tile.setOnMouseReleased(event -> {
                if(!tile.isCanMove()){
                    return;
                }
                double currentX = event.getX();
                double currentY = event.getY();
                tile.setFitHeight(tile.getHeight());
                tile.setFitWidth(tile.getWidth());

                int square = determineSquare(currentX,currentY);
                //+1 -1 -4 +4 for i if exist
                //If only tile is one square away (left,right,top,bottom)


                if(finalI - 1 >= 0 && finalI - 1 == square && finalI % 4 != 0 ){//left from tile
                    System.out.println("Bir solda");

                    tile.setX(positions[finalI-1][0]);
                    tile.setY(positions[finalI-1][1]);

                }else if(finalI + 1 <= tiles.size()-1 && finalI + 1 == square && finalI  % 3 != 0){//right from tile
                    System.out.println("Bir sağda");

                    tile.setX(positions[finalI+1][0]);
                    tile.setY(positions[finalI+1][1]);

                }else if(finalI - 4 >= 0 && finalI - 4 == square){//up from tile

                    tile.setX(positions[finalI-4][0]);
                    tile.setY(positions[finalI-4][1]);

                    System.out.println("Bir yukarda");
                }else if(finalI + 4 <= tiles.size()-1 && finalI + 4 == square){//down from tile

                    System.out.println("Bir aşağıda");

                    tile.setX(positions[finalI+4][0]);
                    tile.setY(positions[finalI+4][1]);
                }else{




                }











           });

        }
    }






    private void drawBoard () {
        for (double[] rectangle:rowColumns) {
            getChildren().add(new Rectangle(rectangle[0],rectangle[1],rectangle[2],rectangle[3]));
        }
    }


    private int determineSquare(double x,double y){
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

        return square;
    }
}







