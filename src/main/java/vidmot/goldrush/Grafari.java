package vidmot.goldrush;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class Grafari extends Rectangle {

    private KarakterController karController = KarakterController.getInstance();

    public Grafari(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("grafari-view.fxml"));
        fxmlLoader.setClassLoader(getClass().getClassLoader());
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        setLayoutX(50);
        setLayoutY(50);

        setImage();
    }

    public void setImage(){
        String selectedCharacter = karController.getSelectedCharacter();
        if (selectedCharacter != null) {
            Image image = new Image(getClass().getResourceAsStream("myndir/" + selectedCharacter + ".png"));
            setFill(new ImagePattern(image));
            System.out.println(selectedCharacter + " valin!");
        } else {
            System.out.println("Enginn karakter valinn :(");
            System.out.println(selectedCharacter + " valin!");
        }
    }
}

