package vidmot.goldrush;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;

public class KarakterTest {

    @FXML
    private RadioMenuItem daisy;
    @FXML
    private RadioMenuItem mario;
    @FXML
    private RadioMenuItem peach;
    @FXML
    private RadioMenuItem luigi;
    private ToggleGroup leikmadur;

    @FXML
    private void initialize(){
        leikmadur = new ToggleGroup();
        daisy.setToggleGroup(leikmadur);
        mario.setToggleGroup(leikmadur);
        peach.setToggleGroup(leikmadur);
        luigi.setToggleGroup(leikmadur);
    }

    @FXML
    private void onVeljaLeikmann(ActionEvent actionEvent){
        if (leikmadur.getSelectedToggle() != null){
            RadioMenuItem valinn = (RadioMenuItem) leikmadur.getSelectedToggle();
            System.out.println("Þú valdir " + valinn.getText());
        } else {
            System.out.println("Enginn leikmaður valinn :(");
        }
    }
}
