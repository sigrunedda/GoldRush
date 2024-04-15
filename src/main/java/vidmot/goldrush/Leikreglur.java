package vidmot.goldrush;

import javafx.fxml.FXML;

public class Leikreglur {
    @FXML
    private GoldController goldController;

    public void setGoldController(GoldController goldController) {
        this.goldController = goldController;
    }

    @FXML
    private void onHeim(){
        ViewSwitcher.switchTo(ViewSwitcher.getLastView());
        if (ViewSwitcher.getLastView() == View.LEIKBORD) {
            setGoldController(goldController);
            goldController.getLeikbord().stopOvinur();
        }
    }
}
