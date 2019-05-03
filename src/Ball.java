
import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.*;
import javafx.util.Duration;

public class Ball  extends ImageView {
    private double height = 40;
    private double width = 40;
    private double onPressX = width/2;
    private double onPressY = height/2;


    public Ball(double x, double y) {
        super(new Image("file:///" + System.getProperty("user.dir") + "/src/tiles/ball.png"));
        super.setX(x);
        super.setY(y);
        super.setFitHeight(height);
        super.setFitWidth(width);
        super.setPreserveRatio(true);

            super.setOnMousePressed(event -> {
                onPressX = event.getX() - super.getX();
                onPressY = event.getY() - super.getY();
            });

            super.setOnMouseDragged(event -> {
                super.setX(event.getX() - onPressX);
                super.setY(event.getY() - onPressY);
            });

    }

    protected void animate(PathElement paths[],int seconds){
        Path animationPath = new Path();
        for (PathElement path:paths) {
            animationPath.getElements().add(path);
        }
        PathTransition transition = new PathTransition();
        transition.setNode(this);
        transition.setDuration(Duration.seconds(seconds));
        transition.setCycleCount(1);
        transition.setPath(animationPath);
        transition.play();
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






}
