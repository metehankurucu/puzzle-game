import javafx.scene.image.Image;

public class LevelButton  extends Button {
    private int level;
    private boolean type;



    public LevelButton(double x, double y, double height, double width, int level, boolean type) {
        super(x,y,height,width,new Image("file:///" + System.getProperty("user.dir") + "/src/images/level" + level + (type?"done":"") + ".png"));
        this.level = level;
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }
}
