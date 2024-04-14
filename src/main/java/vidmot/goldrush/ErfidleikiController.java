package vidmot.goldrush;

public class ErfidleikiController {

    public Leikbord leikbord;

    public void onTilbaka() {
        ViewSwitcher.switchTo(View.START);
    }

    public void setLeikbord(Leikbord leikbord){
        this.leikbord = leikbord;
    }

    public void onAudvelt() {
        leikbord = new Leikbord();
        ViewSwitcher.switchTo(View.KARAKTER);
        leikbord.setFjoldiOvina(1);
        System.out.println("setfjoldiOvina er " + leikbord.fjoldiOvina);
    }

    public void onMidlungs() {
        leikbord = new Leikbord();
        ViewSwitcher.switchTo(View.KARAKTER);
        leikbord.setFjoldiOvina(2);
        System.out.println("setfjoldiOvina er " + leikbord.fjoldiOvina);
    }

    public void onErfitt() {
        leikbord = new Leikbord();
        ViewSwitcher.switchTo(View.KARAKTER);
        leikbord.setFjoldiOvina(3);
        System.out.println("setfjoldiOvina er " + leikbord.fjoldiOvina);
    }

}