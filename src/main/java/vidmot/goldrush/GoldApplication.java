package vidmot.goldrush;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class GoldApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        var scene = new Scene(new Pane());
        ViewSwitcher.setScene(scene);
        ViewSwitcher.switchTo(View.START);
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("myndir/Icon.jpg")));
        stage.setTitle("Gold Rush 2.0");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}