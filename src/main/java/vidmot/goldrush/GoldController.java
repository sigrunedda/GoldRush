package vidmot.goldrush;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import vinnsla.goldrush.Leikur;

import java.util.Objects;
import java.util.Optional;

public class GoldController {
    public MenuBar menustyring;
    @FXML
    private MenuController menustyringController;
    @FXML
    private Label fxTimi;
    @FXML
    private Label fxStig;
    private int haestaStig = 0;
    private Timeline countUpTimeline;
    private int initialTimeInSeconds = 0;
    @FXML
    private Leikbord leikbord;
    private final Leikur leikur;

    /**
     * Smiður til að setja upp leikborð
     */
    public GoldController() {
        this.leikbord = new Leikbord();
        this.leikur = new Leikur();
    }

    /**
     * Upphafsstilling til að tengja saman leikbord og menustyring
     * við GoldController
     */
    @FXML
    public void initialize() {
        menustyringController.setGoldController(this);
        leikbord.setGoldController(this);

    }

    /**
     * Aðferð til að upphafsstilla tímann i leikborði
     */
    public void startCountUp() {
        initialTimeInSeconds = 0;
        updateCountLabel(initialTimeInSeconds);
        if (countUpTimeline != null) {
            countUpTimeline.stop();
            countUpTimeline.getKeyFrames().clear();
        }
        countUpTimeline = new Timeline();
        countUpTimeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> updateCountUp());
        countUpTimeline.getKeyFrames().add(keyFrame);
        countUpTimeline.play();
    }

    /**
     * Uppfærir tímann i leikborði
     */
    private void updateCountUp() {
        initialTimeInSeconds++;
        updateCountLabel(initialTimeInSeconds);
    }

    /**
     * Uppfærir tímann í leikborði og sýnir hann í leikborði
     * @param timeInSeconds - tími í sekúndum
     */
    protected void updateCountLabel(int timeInSeconds) {
        int minutes = timeInSeconds / 60;
        int seconds = timeInSeconds % 60;
        fxTimi.setText(String.format("%02d:%02d", minutes, seconds));
    }

    /**
     * Uppfærir stig i leikborði
     * @param points - stig
     */
    public void updatePoints(int points) {
        if (points != 0) {
            String currentPointsText = fxStig.getText();
            int currentPoints = Integer.parseInt(currentPointsText);
            int newPoints = currentPoints + points;

            if (newPoints > haestaStig) {
                haestaStig = newPoints;
                int haestaStigTime = initialTimeInSeconds + 1;
            }

            fxStig.setText(String.valueOf(newPoints));
        } else {
            fxStig.setText("0");
        }
    }

    /**
     * Ef leikmaður deyr í leikborði þá núllstillast stigin og tíminn.
     * Alert gluggi kemur upp
     * @param varstDrepinn - hvort leikmaður deyr í leikborði
     */
    public void leikLokid(String varstDrepinn) {
        leikur.leikLokid();
        countUpTimeline.stop();
        Platform.runLater(() -> synaAlert(varstDrepinn));
    }

    /**
     * Alert gluggi kemur upp með skilaboð um að leiknum sé lokið, stigafjölda
     * og tíma
     * @param s
     */
    private void synaAlert(String s) {
        int currentTime = initialTimeInSeconds;
        if (Integer.parseInt(fxStig.getText()) > haestaStig) {
            haestaStig = Integer.parseInt(fxStig.getText());
        }
        Alert alert = new AdvorunDialog("Leik lokið", s, "Stigin þín: " + fxStig.getText() + " | Hæsti stigafjöldi: " + haestaStig +
                "\nTiminn þinn: " + formatTime(currentTime));

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("myndir/Icon.jpg"))));

        Optional<ButtonType> u = alert.showAndWait();

        if (u.get().getButtonData().isCancelButton()) {
            hreinsaBord();
            updatePoints(0);
            ViewSwitcher.switchTo(View.START);
        } else if (u.get().getButtonData().isDefaultButton()) {
            hreinsaBord();
            leikbord.hefjaAfram();
            updatePoints(0);
        }
    }

    /**
     * Skilar leikborði
     * @return leikbord
     */
    public Leikbord getLeikbord() {
        return leikbord;
    }

    /**
     * Eyðir öllu af leikborði
     */
    public void hreinsaBord() {
        leikbord.hreinsaBord();
    }

    /**
     * Stillir tímann á rétt snið
     * @param timeInSeconds - tími í sekúndum
     * @return tíminn á réttu sniði
     */
    private String formatTime(int timeInSeconds) {
        int minutes = timeInSeconds / 60;
        int seconds = timeInSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
