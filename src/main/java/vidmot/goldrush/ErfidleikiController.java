package vidmot.goldrush;

import javafx.fxml.FXML;

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

    public int getFjoldiOvina() {
        return fjoldiOvina;
    }

    public void onTilbaka() {
        ViewSwitcher.switchTo(View.START);
    }

    public void onAudvelt() {
        ViewSwitcher.switchTo(View.KARAKTER);
        setFjoldiOvina(1);
        leikbord.setFjoldiOvina(1);
    }

    public void onMidlungs() {
        ViewSwitcher.switchTo(View.KARAKTER);
        setFjoldiOvina(2);
        leikbord.setFjoldiOvina(2);
    }

    public void onErfitt() {
        ViewSwitcher.switchTo(View.KARAKTER);
        setFjoldiOvina(3);
        leikbord.setFjoldiOvina(3);
    }

}
