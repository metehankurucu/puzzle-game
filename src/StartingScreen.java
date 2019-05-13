import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.ArrayList;

public class StartingScreen extends Pane {
    private boolean buttonsVisible = false;
    private ArrayList<LevelButton> buttons = new ArrayList<>();
    private boolean[] buttonTypes;
    private Button startBtn = new Button(275,450,250,250,"start");
    private Button backBtn = new Button(175,450,70,70,"back");


    public StartingScreen(){
        //Background Of Starting Screen
        this.setBackground(
                new Background(
                        new BackgroundImage(
                                new Image("file:///" + System.getProperty("user.dir") + "/src/images/bg.png"),
                                BackgroundRepeat.REPEAT,
                                BackgroundRepeat.REPEAT,
                                BackgroundPosition.CENTER,
                                new BackgroundSize(
                                        820,
                                        820,
                                        true,
                                        true,
                                        true,
                                        true)
                        )
                )
        );
        getChildren().addAll(startBtn,backBtn);
        initializeButtons();
        setUI(false);
        setButtonsVisible(false);
        backBtn.setVisible(false);
        startBtn.setOnMouseClicked(event -> {
            setButtonsVisible(true);
        });
        backBtn.setOnMouseClicked(event -> {
            setButtonsVisible(false);
        });
    }

    public void initializeButtons(){
        //Initialize Level Buttons
        int x;
        int y;
        for (int i = 1;i<6;i++){
            x =  i <= 2?175*(i+1):175*(i-2);
            y = i <= 2?450:550;
            buttons.add(new LevelButton(x,y ,70,70,i,false));
        }
    }
    //Set or Add to Pane
    public void setUI(boolean set){
        for (int i  = 0;i<buttons.size();i++){
            if(set){getChildren().set(i,buttons.get(i));}
            else{getChildren().add(i,buttons.get(i));}
        }
    }


    public boolean isButtonsVisible() {
        return buttonsVisible;
    }

    //Set visible of buttons
    public void setButtonsVisible(boolean buttonsVisible) {
        this.buttonsVisible = buttonsVisible;
        for (LevelButton btn:buttons){
            btn.setVisible(buttonsVisible);
        }
        this.backBtn.setVisible(buttonsVisible);
        this.startBtn.setVisible(!buttonsVisible);
    }

    public ArrayList<LevelButton> getButtons() {
        return buttons;
    }

    public void setButtons(ArrayList<LevelButton> buttons) {
        this.buttons = buttons;
    }

    public boolean[] getButtonTypes() {
        return buttonTypes;
    }

    //When a level is solved,change it with dark button
    public void setButtonTypes(boolean[] buttonTypes) {
        this.buttonTypes = buttonTypes;
        for (int i = 0;i<buttonTypes.length;i++){
            if(!(buttons.get(i).isType() == buttonTypes[i])){
                LevelButton oldBtn = buttons.get(i);
                buttons.set(i,new LevelButton(
                        oldBtn.getX(),
                        oldBtn.getY(),
                        oldBtn.getFitHeight(),
                        oldBtn.getFitWidth(),
                        oldBtn.getLevel(),
                        buttonTypes[i]
                    )
                );

            }
        }
        this.setUI(true);
        this.setButtonsVisible(false);
    }
}
