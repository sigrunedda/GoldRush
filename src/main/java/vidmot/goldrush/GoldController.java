package vidmot.goldrush;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import vinnsla.goldrush.Klukka;
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
    @FXML
    private Leikbord leikbord;
    private final Leikur leikur;
    private Klukka klukka;

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
        klukka = new Klukka(this, fxTimi);
    }

    /**
     * Aðferð til að upphafsstilla tímann i leikborði
     */
    public void startCountUp() {
        klukka.startCountUp();
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
        klukka.stopCountUp();
        synaAlert(varstDrepinn);
    }

    /**
     * Alert gluggi kemur upp með skilaboð um að leiknum sé lokið, stigafjölda
     * og tíma
     * @param s
     */
    private void synaAlert(String s) {
        int currentTime = klukka.getCurrentTimeInSeconds();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Leik lokið");
        alert.setHeaderText(s);
        alert.setContentText("Stigin þín: " + fxStig.getText() + " | Hæsti stigafjöldi: " + haestaStig +
                "\nTiminn þinn: " + formatTime(currentTime));

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("myndir/Icon.jpg"))));

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            hreinsaBord();
            updatePoints(0);
            ViewSwitcher.switchTo(View.START);
        } else {
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
