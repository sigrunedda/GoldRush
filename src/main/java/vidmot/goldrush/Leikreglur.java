package vidmot.goldrush;

import javafx.fxml.FXML;

public class Leikreglur {

    @FXML
    private void onHeim(){
        ViewSwitcher.switchTo(ViewSwitcher.getLastView());
    }
}
