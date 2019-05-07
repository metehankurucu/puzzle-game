import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.*;
import javafx.util.Duration;

public class Ball  extends ImageView {
    private double height = 40;
    private double width = 40;

    public Ball(double x, double y) {
        super(new Image("file:///" + System.getProperty("user.dir") + "/src/tiles/ball.png"));
        super.setX(x);
        super.setY(y);
        super.setFitHeight(height);
        super.setFitWidth(width);
        super.setPreserveRatio(true);
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
}
