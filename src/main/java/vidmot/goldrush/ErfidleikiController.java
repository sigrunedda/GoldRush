package vidmot.goldrush;

import javafx.fxml.FXML;

/**
 * Klasinn sér um að breyta fjölda óvina eftir að erfiðleikasig er valið.
 * Valmyndin birtist eftir start-view.fxml þegar að hefja leik hnappur er valinn
 */
public class ErfidleikiController {

    private int fjoldiOvina;
    @FXML
    private final Leikbord leikbord;
    private static final ErfidleikiController instance = new ErfidleikiController();

    public ErfidleikiController() {
        this.leikbord = new Leikbord();
    }

    public static ErfidleikiController getInstance() {
        return instance;
    }

    public void setFjoldiOvina (int fjoldiOvina) {
        this.fjoldiOvina = fjoldiOvina;
    }

    /**
     * Tengir fjölda óvina við leikborðið þannig það er auðveldara að ná í mismunandi breytur eftir erfiðleikastig
     * @return fjoldiOvina
     */
    public int getFjoldiOvina() {
        return fjoldiOvina;
    }
    /**
     * Þegar að til baka hnappurinn er valinn þá skiptir ViewSwitcher aftur í start-view fxml
     */
    public void onTilbaka() {
        ViewSwitcher.switchTo(View.START);
    }
    /**
     * Þegar að auðvelt hnappurinn er valinn þá skiptir ViewSwitcher í karakter-view.fxml og fjöldi óvina verður 1
     */
    public void onAudvelt() {
        ViewSwitcher.switchTo(View.KARAKTER);
        setFjoldiOvina(1);
        leikbord.setFjoldiOvina(1);
    }
    /**
     * Þegar að miðlungs hnappurinn er valinn þá skiptir ViewSwitcher í karakter-view.fxml og fjöldi óvina verður 2
     */
    public void onMidlungs() {
        ViewSwitcher.switchTo(View.KARAKTER);
        setFjoldiOvina(2);
        leikbord.setFjoldiOvina(2);
    }
    /**
     * Þegar að erfitt hnappurinn er valinn þá skiptir ViewSwitcher í karakter-view.fxml og fjöldi óvina verður 3
     */
    public void onErfitt() {
        ViewSwitcher.switchTo(View.KARAKTER);
        setFjoldiOvina(3);
        leikbord.setFjoldiOvina(3);
    }

}
