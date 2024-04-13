package vidmot.goldrush;

public class ErfidleikiController {

    private int fjoldiOvina;

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
    }

    public void onMidlungs() {
        ViewSwitcher.switchTo(View.KARAKTER);
        setFjoldiOvina(2);
    }

    public void onErfitt() {
        ViewSwitcher.switchTo(View.KARAKTER);
        setFjoldiOvina(3);
    }

}
