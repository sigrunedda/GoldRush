package vidmot.goldrush;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.Random;

public class Ovinur extends Rectangle {

    private static final String FXML_SKRA_OVIN = "vidmot/goldrush/ovinur-view.fxml";

    private static final Random random = new Random();

    @FXML
    private int HREYFING;   //hve mikil hreyfing er á óvininum í random


    public Ovinur(){
        lesa(FXML_SKRA_OVIN);
        setWidth(30);
        setHeight(50);
        Image Star = new Image(getClass().getResourceAsStream("myndir/Ovinur.png"));
        setFill(new ImagePattern(Star));
    }

    public boolean isCollidingWithGrafari(Grafari grafari){
        Bounds ovinurBounds = this.getBoundsInParent();
        Bounds grafariBounds = grafari.getBoundsInParent();

        return ovinurBounds.intersects(grafariBounds);
    }

    public void setjaABord(Leikbord leikbord) {
        leikbord.getChildren().add(this);
    }

    protected void lesa(String fxmlSkra) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlSkra));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void afram(){
        Leikbord leikbord = (Leikbord) this.getParent();
    }
}
