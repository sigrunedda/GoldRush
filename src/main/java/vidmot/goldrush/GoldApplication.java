package vidmot.goldrush;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

/**
 * Klasinn sér um að ræsa leikinn og leikurinn byrjar á start-view fxml
 * Hér stillt letrið og icon myndina fyrir vinstra efra hornið
 */
public class GoldApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        var scene = new Scene(new Pane());
        ViewSwitcher.setScene(scene);
        ViewSwitcher.switchTo(View.START);
        stage.setScene(scene);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("myndir/Icon.jpg"))));

        // loada leturgerðir
        Font.loadFont(Objects.requireNonNull(getClass().getResource("/vidmot/goldrush/letur/PixeloidSans.ttf")).toExternalForm(), 12);
        Font.loadFont(Objects.requireNonNull(getClass().getResource("/vidmot/goldrush/letur/PixeloidSans-Bold.ttf")).toExternalForm(), 16);
        String myStyles = """
                         .button, .texti, .label {
                           -fx-font-family: 'Pixeloid Sans';
                         }
                         .titill {
                           -fx-font-family: 'Pixeloid Sans Bold';
                         }
                         """;
        File cssFile = File.createTempFile("demo", "css");
        cssFile.deleteOnExit();
        Files.writeString(cssFile.toPath(), myStyles, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        String cssURL = cssFile.toURI().toString();
        scene.getStylesheets().add(cssURL);

        stage.setTitle("Gold Rush 2.0");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
