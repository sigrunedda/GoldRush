package vidmot.goldrush;

public class ErfidleikiController {

    public Leikbord leikbord;

    public void onTilbaka() {
        ViewSwitcher.switchTo(View.START);
    }

    public void onAudvelt() {
        leikbord = new Leikbord();
        ViewSwitcher.switchTo(View.KARAKTER);
        leikbord.setFjoldiOvina(1);
    }

    public void onMidlungs() {
        leikbord = new Leikbord();
        ViewSwitcher.switchTo(View.KARAKTER);
        leikbord.setFjoldiOvina(2);
    }

    public void onErfitt() {
        leikbord = new Leikbord();
        ViewSwitcher.switchTo(View.KARAKTER);
        leikbord.setFjoldiOvina(3);
    }

}

  /*private int fjoldiOvina;

    public void setFjoldiOvina (int fjoldiOvina) {
        this.fjoldiOvina = fjoldiOvina;
    }

    public int getFjoldiOvina() {
        return fjoldiOvina;
    }*/