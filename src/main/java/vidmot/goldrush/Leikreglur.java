package vidmot.goldrush;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Leikreglur {
    @FXML
    private GoldController goldController;

    public void setGoldController(GoldController goldController) {
        this.goldController = goldController;
    }

    @FXML
    private void onHeim(ActionEvent event){
        ViewSwitcher.switchTo(ViewSwitcher.getLastView());
        if (ViewSwitcher.getLastView() == View.LEIKBORD) {
            goldController.getLeikbord().stopOvinur();
        }
    }
}
