package vidmot.goldrush;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Leikreglur {

    @FXML
    private void onHeim(ActionEvent event){
        ViewSwitcher.switchTo(View.START);
    }
}