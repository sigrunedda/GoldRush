package vidmot.goldrush;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.Objects;

public class Grafari extends Rectangle {

    private final KarakterController karController = KarakterController.getInstance();

    /**
     * Smiður til að upphafsstilla grafara
     */
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

    /**
     * Setur mynd á grafara
     */
    public void setImage(){
        String selectedCharacter = karController.getSelectedCharacter();
        if (selectedCharacter != null) {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("myndir/" + selectedCharacter + ".png")));
            setFill(new ImagePattern(image));
            System.out.println(selectedCharacter + " valin!");
        } else {
            System.out.println("Enginn karakter valinn :(");
            System.out.println(selectedCharacter + " valin!");
        }
    }
}

