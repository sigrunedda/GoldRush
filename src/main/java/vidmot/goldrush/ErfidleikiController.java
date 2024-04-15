package vidmot.goldrush;

import javafx.fxml.FXML;

public class ErfidleikiController {

    private int fjoldiOvina;
    @FXML
    private final Leikbord leikbord;
    private static final ErfidleikiController instance = new ErfidleikiController();

    /**
     * Smiður til að setja upp leikborð
     */
    public ErfidleikiController() {
        this.leikbord = new Leikbord();
    }

    /**
     * sækir tilfelli af ErfidleikiController
     * @return - tilfelli af ErfidleikiController
     */
    public static ErfidleikiController getInstance() {
        return instance;
    }

    public void setFjoldiOvina (int fjoldiOvina) {
        this.fjoldiOvina = fjoldiOvina;
    }

    /**
     * Stillir fjölda óvina í borðinu
     *
     * @return - skilar fjölda óvina
     */
    public int getFjoldiOvina() {
        return fjoldiOvina;
    }

    /**
     * Þegar ýtt er á "Tilbaka" hnappinn fer notandinn til baka á forsíðu
     */
    public void onTilbaka() {
        ViewSwitcher.switchTo(View.START);
    }

    /**
     * Ef notandinn velur "auðvelt" erfiðleikastig þá er óvinafjöldinn einn
     */
    public void onAudvelt() {
        ViewSwitcher.switchTo(View.KARAKTER);
        setFjoldiOvina(1);
        leikbord.setFjoldiOvina(1);
    }

    /**
     * Ef notandinn velur "miðlungs" erfiðleikastig þá er óvinafjöldinn tveir
     */
    public void onMidlungs() {
        ViewSwitcher.switchTo(View.KARAKTER);
        setFjoldiOvina(2);
        leikbord.setFjoldiOvina(2);
    }

    /**
     * Ef notandinn velur "erfitt" erfiðleikastig þá er óvinafjöldinn þrír
     */
    public void onErfitt() {
        ViewSwitcher.switchTo(View.KARAKTER);
        setFjoldiOvina(3);
        leikbord.setFjoldiOvina(3);
    }

}
