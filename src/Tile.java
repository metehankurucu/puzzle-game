
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tile  extends ImageView {
    private double height = 200;
    private double width = 200;
    private double onPressX = width/2;
    private double onPressY = height/2;
    private boolean canMove;



    public Tile(){
        //Initialize empty tile
    }

    public Tile(double x,double y,String fileName,boolean hasMove) {
        super(new Image("file:///C:/Users/Lenovo/Desktop/Github/puzzle-game/src/tiles/" + fileName));
        this.canMove = hasMove;
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

        }
    }

    public double getHeight() {
        return height;
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
