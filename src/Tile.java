
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tile  extends ImageView {
    private double height = 200;
    private double width = 200;
    private double onPressX = width/2;
    private double onPressY = height/2;
    private boolean canMove = false;
    private boolean isEmpty = false;
    private String name = "";



    public Tile(){
        //Initialize empty tile
        isEmpty = true;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "canMove=" + canMove +
                ", name='" + name + '\'' +
                '}';
    }

    public Tile(double x, double y, String name, String extension, boolean hasMove) {
        super(new Image("file:///C:/Users/Lenovo/Desktop/Github/puzzle-game/src/tiles/" + name + extension));
        this.canMove = hasMove;
        this.name = name;
        super.setX(x);
        super.setY(y);
        super.setFitHeight(height);
        super.setFitWidth(width);
        super.setPreserveRatio(true);
        if (hasMove) {
            super.setOnMousePressed(event -> {
                onPressX = event.getX() - super.getX();
                onPressY = event.getY() - super.getY();
                super.setFitHeight(height + 20);
                super.setFitWidth(width + 20);

            });

            super.setOnMouseDragged(event -> {
                super.setX(event.getX() - onPressX);
                super.setY(event.getY() - onPressY);
            });

        }else{




        }
    }

    public double getHeight() {
        return height;
    }
    public boolean isEmpty() {
        return isEmpty;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getOnPressX() {
        return onPressX;
    }

    public void setOnPressX(double onPressX) {
        this.onPressX = onPressX;
    }

    public double getOnPressY() {
        return onPressY;
    }

    public void setOnPressY(double onPressY) {
        this.onPressY = onPressY;
    }

    public boolean isCanMove() {
        return canMove;
    }





}
