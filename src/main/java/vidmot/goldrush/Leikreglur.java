package vidmot.goldrush;

import javafx.fxml.FXML;

public class Leikreglur {
    /**
     * Klasinn sér um að stilla hvaða klasi stýrir leikreglur-view.fxml, GoldController
     */
    @FXML
    private GoldController goldController;

    public void setGoldController(GoldController goldController) {
        this.goldController = goldController;
    }

    /**
     * Ef að til baka hnappurinn er valin þá fer notandinn aftur á síðasta view.
     * Þannig ef að notandinn opnar leikreglur í miðjum leik þá fer hann til baka í leikinn
     * en ef leikreglur er valið frá start þá fer notandinn afur í start
     */
    @FXML
    private void onHeim(){
        ViewSwitcher.switchTo(ViewSwitcher.getLastView());
        if (ViewSwitcher.getLastView() == View.LEIKBORD) {
            setGoldController(goldController);
            goldController.getLeikbord().stopOvinur();
        }
    }
}
