import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Button  extends ImageView {
    private double height;
    private double width;

    public Button(double x,double y,double height, double width, String name){
        this(x,y,height,width,new Image("file:///" + System.getProperty("user.dir") + "/src/images/" + name + ".png"));
    }

    public Button(double x, double y, double height, double width,Image image) {
        super(image);
        super.setX(x);
        super.setY(y);
        super.setFitHeight(height);
        super.setFitWidth(width);
        super.setPreserveRatio(true);
        this.height = height;
        this.width = width;
        handleClickReaction();
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
    //When clicked decrease 5 pixel,when released increase to normal height and width
    private void handleClickReaction(){
        super.setOnMousePressed(event -> {
            super.setFitHeight(height - 5);
            super.setFitWidth(width - 5);
        });
        super.setOnMouseReleased(event -> {
            super.setFitHeight(height + 5);
            super.setFitWidth(width + 5);
        });
    }
}
